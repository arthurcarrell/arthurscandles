package com.asteristired.arthurscandles.Effects;

import com.asteristired.arthurscandles.mixin.MobEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.selectors.TargetSelector;

import java.util.ArrayList;
import java.util.List;

import static com.asteristired.arthurscandles.ArthursCandles.LOGGER;
import static com.asteristired.arthurscandles.ArthursCandles.MOD_ID;

// backported from a 1.21.4 mod that I never finished

public class CalmStatusEffect extends StatusEffect {

    private final Identifier EFFECT_IDENTIFIER = Identifier.of(MOD_ID, "calm");

    public CalmStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL, // whether beneficial or harmful for entities
                0x99FF33
        ); // color in HEX
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof MobEntity mob && entity.hasStatusEffect(ModEffects.CALM)) {
            GoalSelector goalAccessor = ((MobEntityAccessor) mob).getGoalSelector();
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof MeleeAttackGoal);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof ZombieAttackGoal);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof ActiveTargetGoal<?>);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof RevengeGoal);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof AttackGoal);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof AttackWithOwnerGoal);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof BowAttackGoal<?>);
            goalAccessor.getGoals().removeIf(goal -> goal.getGoal() instanceof CrossbowAttackGoal<?>);
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof MobEntity mob) {
            GoalSelector goalAccessor = ((MobEntityAccessor) mob).getGoalSelector();

            // tell the game they aren't attacking
            mob.setAttacking(false);
            mob.setTarget(null);

            // all goals
            List<Goal> goals = new ArrayList<>();
            for (PrioritizedGoal goal : ((MobEntityAccessor) mob).getGoalSelector().getGoals()) {
                goals.add(goal.getGoal());
            }

            goalAccessor.add(0, new SwimGoal(mob));
            goalAccessor.add(0, new WanderAroundGoal((PathAwareEntity) mob, 1.0));
            goalAccessor.add(3, new LookAroundGoal(mob));
            goalAccessor.add(4, new LookAtEntityGoal(mob, PlayerEntity.class, 8.0f));
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if (entity instanceof MobEntity mob) {
            mob.setTarget(null);

            // clear all goals and targets
            ((MobEntityAccessor) mob).getGoalSelector().clear(goal -> true);
            ((MobEntityAccessor) mob).getTargetSelector().clear(goal -> true);

            // reinvoke all goals
            ((MobEntityAccessor) mob).invokeInitGoals();

        }
    }
}

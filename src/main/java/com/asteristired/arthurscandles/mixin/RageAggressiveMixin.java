package com.asteristired.arthurscandles.mixin;


import com.asteristired.arthurscandles.Effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class RageAggressiveMixin {

    @Shadow
    private @Nullable LivingEntity target;

    @Inject(method = "createMobAttributes", at = @At("RETURN"), cancellable = true)
    private static void addAttackDamage(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.5); // Default attack value for passive mobs
        cir.setReturnValue(builder);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void Aggro(CallbackInfo ci) {
        MobEntity mob = (MobEntity) (Object) this;

        if (!(mob instanceof PathAwareEntity)) {
            return;
        }

        if (mob.getWorld().isClient) { return; }

        if (mob.hasStatusEffect(ModEffects.RAGE)) {
            MobEntityAccessor accessor = (MobEntityAccessor) mob;

            // if the mob doesn't have attack AI, then add it.
            if (accessor.getGoalSelector().getGoals().stream().noneMatch(g -> g.getGoal() instanceof MeleeAttackGoal)) {
                accessor.getGoalSelector().add(1, new MeleeAttackGoal((PathAwareEntity) mob, 1.2, true));
            }

            ServerWorld world = (ServerWorld) mob.getWorld();
            LivingEntity target = null;
            float distance = Float.MAX_VALUE;

            for (LivingEntity potentialTarget : world.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(mob.getBlockPos()).expand(30),
                    e -> e != mob)) { // Avoid attacking itself

                // check if it's a player, and if so, are they in Survival or Adventure mode.
                if (potentialTarget instanceof PlayerEntity player) {
                    if (player.isCreative()) continue;
                    if (player.isSpectator()) continue;
                }



                if ((potentialTarget.distanceTo(mob) < distance) && mob.canSee(potentialTarget)) {
                    distance = potentialTarget.distanceTo(mob);
                    target = potentialTarget;
                }

            }

            if (mob.getTarget() == null || mob.getTarget().isDead()) {
                if (target != null) {
                    mob.setTarget(target);
                }
            }
        }
    }
}
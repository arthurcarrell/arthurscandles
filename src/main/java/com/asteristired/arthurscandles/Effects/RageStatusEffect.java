package com.asteristired.arthurscandles.Effects;

import com.asteristired.arthurscandles.mixin.MobEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

import static com.asteristired.arthurscandles.ArthursCandles.LOGGER;
import static com.asteristired.arthurscandles.ArthursCandles.MOD_ID;

public class RageStatusEffect extends StatusEffect {
    public RageStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL, // whether beneficial or harmful for entities
                0xBE2525
        ); // color in HEX
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        LOGGER.info("entity applied rage to: {}", entity.getName().getString());
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }


    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
    }
}

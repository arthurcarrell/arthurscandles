package com.asteristired.arthurscandles.BlockEntities;

import com.asteristired.arthurscandles.Effects.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class RagingCandleBlockEntity extends BlockEntity {
    private static final BooleanProperty LIT = BooleanProperty.of("lit");
    private static final int RADIUS = 20;
    public RagingCandleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RAGING_CANDLE, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (state.get(LIT)) {
            List<LivingEntity> livingEntities = world.getEntitiesByClass(LivingEntity.class, Box.of(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), RADIUS, RADIUS, RADIUS), LivingEntity -> true);

            for (LivingEntity entity : livingEntities) {
                if (!entity.hasStatusEffect(ModEffects.RAGE)) {
                    entity.addStatusEffect(new StatusEffectInstance(ModEffects.RAGE, 200, 0, true, false, true));
                }
            }
        }
    }


}

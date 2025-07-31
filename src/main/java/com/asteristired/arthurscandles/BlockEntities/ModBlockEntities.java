package com.asteristired.arthurscandles.BlockEntities;

import com.asteristired.arthurscandles.Blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import static com.asteristired.arthurscandles.ArthursCandles.MOD_ID;

public class ModBlockEntities {
    public static BlockEntityType<CalmingCandleBlockEntity> CALMING_CANDLE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(MOD_ID, "calming_candle"),
            FabricBlockEntityTypeBuilder.create(
                    CalmingCandleBlockEntity::new,
                    ModBlocks.CALMING_CANDLE
            ).build()
    );

    public static BlockEntityType<RagingCandleBlockEntity> RAGING_CANDLE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(MOD_ID, "raging_candle"),
            FabricBlockEntityTypeBuilder.create(
                    RagingCandleBlockEntity::new,
                    ModBlocks.RAGING_CANDLE
            ).build()
    );

    public static void Initalise() {}
}

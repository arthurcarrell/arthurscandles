package com.asteristired.arthurscandles.Blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.asteristired.arthurscandles.ArthursCandles.MOD_ID;

public class ModBlocks {


    public static Block CALMING_CANDLE = Register(new CalmingCandle(AbstractBlock.Settings.create().nonOpaque().strength(0.5f)), "calming_candle");
    public static BlockItem CALMING_CANDLE_ITEM = Register(new BlockItem(CALMING_CANDLE, new Item.Settings().rarity(Rarity.UNCOMMON)), "calming_candle");

    public static Block RAGING_CANDLE = Register(new RagingCandle(AbstractBlock.Settings.create().nonOpaque().strength(0.5f)), "raging_candle");
    public static BlockItem RAGING_CANDLE_ITEM = Register(new BlockItem(RAGING_CANDLE, new Item.Settings().rarity(Rarity.UNCOMMON)), "raging_candle");

    public static Block Register(Block block, String id) {
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, id), block);
    }

    public static BlockItem Register(BlockItem block, String id) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, id), block);
    }

    public static void AddToCreativeMenu() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(itemGroup -> {
            itemGroup.addAfter(Items.PINK_CANDLE, ModBlocks.CALMING_CANDLE_ITEM);
            itemGroup.addAfter(ModBlocks.CALMING_CANDLE_ITEM, ModBlocks.RAGING_CANDLE_ITEM);
        });
    }

    public static void Initalise() {}

}

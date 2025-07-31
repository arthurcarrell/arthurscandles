package com.asteristired.arthurscandles;

import com.asteristired.arthurscandles.BlockEntities.ModBlockEntities;
import com.asteristired.arthurscandles.Blocks.ModBlocks;
import com.asteristired.arthurscandles.Effects.ModEffects;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArthursCandles implements ModInitializer {

    public static final String MOD_ID = "arthurscandles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {

        // initalise all modules
        ModBlocks.Initalise();
        ModBlocks.AddToCreativeMenu();
        ModBlockEntities.Initalise();
        ModEffects.Initialise();
    }
}

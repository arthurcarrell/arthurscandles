package com.asteristired.arthurscandles.Effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static com.asteristired.arthurscandles.ArthursCandles.MOD_ID;

public class ModEffects {

    public static final StatusEffect CALM = Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "calm"),
            new CalmStatusEffect());

    public static final StatusEffect RAGE = Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "rage"),
            new RageStatusEffect());

    public static void Initialise() {
        // dummy function that is called in order to initalise a class.
    }
}

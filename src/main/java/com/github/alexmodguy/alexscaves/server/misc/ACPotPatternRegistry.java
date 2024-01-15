package com.github.alexmodguy.alexscaves.server.misc;

import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.google.common.collect.ImmutableMap;
import com.min01.archaeology.init.ArchaeologyRegistryKey;
import com.min01.archaeology.misc.DecoratedPotPatterns;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ACPotPatternRegistry {
    public static final ResourceKey<String> DINOSAUR = create("dinosaur_pottery_pattern");
    public static final ResourceKey<String> FOOTPRINT = create("footprint_pottery_pattern");
    public static final ResourceKey<String> GUARDIAN = create("guardian_pottery_pattern");
    public static final ResourceKey<String> HERO = create("hero_pottery_pattern");
    
    public static void registerACPotPatterns() {
    	ImmutableMap.Builder<Item, ResourceKey<String>> itemsToPot = new ImmutableMap.Builder<>();
        itemsToPot.putAll(DecoratedPotPatterns.ITEM_TO_POT_TEXTURE);
        itemsToPot.put(ACItemRegistry.DINOSAUR_POTTERY_SHERD.get(), DINOSAUR);
        itemsToPot.put(ACItemRegistry.FOOTPRINT_POTTERY_SHERD.get(), FOOTPRINT);
        itemsToPot.put(ACItemRegistry.GUARDIAN_POTTERY_SHERD.get(), GUARDIAN);
        itemsToPot.put(ACItemRegistry.HERO_POTTERY_SHERD.get(), HERO);
        DecoratedPotPatterns.ITEM_TO_POT_TEXTURE = itemsToPot.build();
    }
    
    private static ResourceKey<String> create(String p_272919_) {
 	   ResourceKey<String> key = ResourceKey.create(ArchaeologyRegistryKey.DECORATED_POT_PATTERNS, new ResourceLocation(p_272919_));
 	   return key;
    }
}

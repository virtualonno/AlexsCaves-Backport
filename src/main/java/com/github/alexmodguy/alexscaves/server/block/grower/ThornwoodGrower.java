package com.github.alexmodguy.alexscaves.server.block.grower;

import com.github.alexmodguy.alexscaves.AlexsCaves;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ThornwoodGrower extends AbstractTreeGrower {

    public static final ResourceKey<ConfiguredFeature<?, ?>> THORNWOOD_TREE = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(AlexsCaves.MODID, "thornwood_tree"));

    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getHolderOrThrow(THORNWOOD_TREE);
    }
}

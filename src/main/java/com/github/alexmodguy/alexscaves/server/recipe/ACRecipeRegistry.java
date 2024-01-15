package com.github.alexmodguy.alexscaves.server.recipe;

import com.github.alexmodguy.alexscaves.AlexsCaves;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ACRecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AlexsCaves.MODID);

    public static final RegistryObject<RecipeSerializer<?>> CAVE_MAP = DEF_REG.register("cave_map", () -> new SimpleRecipeSerializer<>(RecipeCaveMap::new));
}

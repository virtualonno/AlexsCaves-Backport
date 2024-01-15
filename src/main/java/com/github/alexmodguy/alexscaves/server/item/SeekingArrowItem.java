package com.github.alexmodguy.alexscaves.server.item;

import com.github.alexmodguy.alexscaves.server.entity.item.SeekingArrowEntity;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SeekingArrowItem extends ArrowItem {
    public SeekingArrowItem() {
        super(new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES));
    }

    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity livingEntity) {
        return new SeekingArrowEntity(level, livingEntity);
    }
}

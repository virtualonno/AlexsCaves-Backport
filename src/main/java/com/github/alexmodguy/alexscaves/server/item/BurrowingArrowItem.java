package com.github.alexmodguy.alexscaves.server.item;

import com.github.alexmodguy.alexscaves.server.entity.item.BurrowingArrowEntity;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BurrowingArrowItem extends ArrowItem {
    public BurrowingArrowItem() {
        super(new Properties().tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
    }

    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity livingEntity) {
        return new BurrowingArrowEntity(level, livingEntity);
    }
}

package com.github.alexmodguy.alexscaves.server.entity.util;

import net.minecraft.world.entity.Entity;

public interface ShakesScreen {

    default boolean canFeelShake(Entity player) {
        return player.isOnGround();
    }

    float getScreenShakeAmount(float partialTicks);
}

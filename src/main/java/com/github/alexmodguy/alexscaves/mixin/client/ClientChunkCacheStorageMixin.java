package com.github.alexmodguy.alexscaves.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.world.entity.player.Player;

@Mixin(ClientChunkCache.Storage.class)
public class ClientChunkCacheStorageMixin {

    @Inject(
            method = {"Lnet/minecraft/client/multiplayer/ClientChunkCache$Storage;inRange(II)Z"},
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void ac_inRange(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        if(Minecraft.getInstance().cameraEntity != null && !(Minecraft.getInstance().cameraEntity instanceof Player)){
            cir.setReturnValue(true);
        }
    }
}

package com.github.alexmodguy.alexscaves.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.alexmodguy.alexscaves.server.entity.util.MinecartAccessor;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.MinecartSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.vehicle.AbstractMinecart;

@Mixin(MinecartSoundInstance.class)
public abstract class MinecartSoundInstanceMixin extends AbstractTickableSoundInstance {

    @Shadow
    @Final
    private AbstractMinecart minecart;

    protected MinecartSoundInstanceMixin(SoundEvent soundEvent, SoundSource soundSource, RandomSource randomSource) {
        super(soundEvent, soundSource, randomSource);
    }

    @Inject(
            method = {"Lnet/minecraft/client/resources/sounds/MinecartSoundInstance;tick()V"},
            remap = true,
            at = @At(value = "HEAD"),
            cancellable = true
    )
    public void ac_tick(CallbackInfo ci) {
        if (((MinecartAccessor) minecart).isOnMagLevRail()) {
            volume = 0.0F;
            ci.cancel();
        }
    }
}

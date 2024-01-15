package com.github.alexmodguy.alexscaves.compat.jei;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class NuclearFurnaceDrawable implements IDrawable {

    private static final ResourceLocation TEXTURE = new ResourceLocation(AlexsCaves.MODID, "textures/gui/nuclear_furnace.png");

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public int getHeight() {
        return 60;
    }

    @Override
    public void draw(PoseStack guiGraphics, int xOffset, int yOffset) {
        int i = xOffset;
        int j = yOffset;
        RenderSystem.enableTexture();
        RenderSystem.setShaderTexture(0, TEXTURE);
        Screen.blit(guiGraphics, i, j, 5, 15, getWidth(), getHeight(), 256, 256);
        int ticks = Minecraft.getInstance().player.tickCount;
        int cookPixels = (int) Math.ceil(24 * ((ticks + 40) % 20 / 20F));
        int fillAnimateTime = ticks % 100;
        if(fillAnimateTime < 70){
            int barrelPixels = (int) Math.ceil(14 * (fillAnimateTime / 70F));
            Minecraft.getInstance().screen.blit(guiGraphics, i + 33, j + 21 + (14 - barrelPixels), 192, (14 - barrelPixels), 15, barrelPixels);
            int wastePixels = 5;
            Minecraft.getInstance().screen.blit(guiGraphics, i + 8, j + 2 + (52 - wastePixels), 176, 32  + (52 - wastePixels), 16, wastePixels);
        }
        Minecraft.getInstance().screen.blit(guiGraphics, i + 86, j + 20, 176, 14, cookPixels, 17);
        Minecraft.getInstance().screen.blit(guiGraphics, i + 63, j + 21, 176, 0, 14, 14);

    }
}
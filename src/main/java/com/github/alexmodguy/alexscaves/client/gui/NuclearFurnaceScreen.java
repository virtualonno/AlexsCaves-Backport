package com.github.alexmodguy.alexscaves.client.gui;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.inventory.NuclearFurnaceMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NuclearFurnaceScreen extends AbstractContainerScreen<NuclearFurnaceMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(AlexsCaves.MODID, "textures/gui/nuclear_furnace.png");
    public NuclearFurnaceScreen(NuclearFurnaceMenu furnaceMenu, Inventory inventory, Component component) {
        super(furnaceMenu, inventory, component);
        this.inventoryLabelY = this.imageHeight - 92;
        this.titleLabelX = this.imageHeight/2 + 5;
        this.titleLabelY = 5;

    }

    protected void renderLabels(PoseStack guiGraphics, int mouseX, int mouseY) {
        drawString(guiGraphics, this.font, this.title, this.titleLabelX - (this.font.width(this.title) / 2), this.titleLabelY, 4210752);
        drawString(guiGraphics, this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752);
    }

    public void render(PoseStack guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack guiGraphics, float f, int x, int y) {
        this.renderBackground(guiGraphics);
        int i = this.leftPos;
        int j = this.topPos;
        RenderSystem.enableTexture();
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(guiGraphics, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if(!menu.getSlot(1).hasItem()){
            blit(guiGraphics, i + 67, j + 53, 176, 84, 16, 16);
        }
        if(!menu.getSlot(2).hasItem()){
            blit(guiGraphics, i + 37, j + 53, 192, 84, 16, 16);
        }
        float wasteAmount =  menu.getWasteScale();
        if(wasteAmount > 0.0F){
            int wastePixels = (int) Math.ceil(52 * wasteAmount);
            blit(guiGraphics, i + 13, j + 17 + (52 - wastePixels), 176, 32  + (52 - wastePixels), 16, wastePixels);
        }
        float barrelAmount = menu.getBarrelScale();
        if(barrelAmount > 0.0F){
            int barrelPixels = (int) Math.ceil(14 * barrelAmount);
            blit(guiGraphics, i + 38, j + 36 + (14 - barrelPixels), 192, (14 - barrelPixels), 15, barrelPixels);
        }
        float fissionAmount = menu.getFissionScale();
        if(fissionAmount > 0.0F){
            int fissionPixels = (int) Math.ceil(14 * fissionAmount);
            blit(guiGraphics, i + 68, j + 36 + (14 - fissionPixels), 176, (14 - fissionPixels), 14, fissionPixels);
        }
        float cookAmount = menu.getCookScale();
        if(cookAmount > 0.0F){
            int cookPixels = (int) Math.ceil(24 * cookAmount);
            blit(guiGraphics, i + 90, j + 35, 176, 14, cookPixels, 17);
        }
    }
}

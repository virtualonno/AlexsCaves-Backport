package com.github.alexmodguy.alexscaves.mixin.client;


import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.alexmodguy.alexscaves.client.gui.ACAdvancementTabs;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.util.Mth;

@Mixin(AdvancementTab.class)
public class AdvancementTabMixin {

    @Shadow
    private boolean centered;

    @Shadow
    private double scrollX;

    @Shadow
    private double scrollY;

    @Shadow
    private int maxX;

    @Shadow
    private int minX;

    @Shadow
    private int maxY;

    @Shadow
    private int minY;

    @Shadow
    @Final
    private DisplayInfo display;

    @Shadow
    @Final
    private AdvancementWidget root;

    @Shadow
    @Final
    private Map<Advancement, AdvancementWidget> widgets;


    @Inject(
            method = {"Lnet/minecraft/client/gui/screens/advancements/AdvancementTab;drawContents(Lcom/mojang/blaze3d/vertex/PoseStack;)V"},
            remap = true,
            cancellable = true,
            at = @At(value = "HEAD")
    )
    private void ac_drawContents(PoseStack guiGraphics, CallbackInfo ci) {
        if (ACAdvancementTabs.isAlexsCavesWidget(root.advancement)) {
            ci.cancel();
            Screen.enableScissor(0, 0, 0 + 234, 0 + 113);
            guiGraphics.pushPose();
            guiGraphics.translate((float) 0, (float) 0, 0.0F);
            if (!this.centered) {
                this.scrollX = (double) (117 - (this.maxX + this.minX) / 2);
                this.scrollY = (double) (56 - (this.maxY + this.minY) / 2);
                this.centered = true;
            }
            int width = this.maxX - this.minX;
            int height = this.maxY - this.minY;
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            ACAdvancementTabs.setDimensions(width, height);
            ACAdvancementTabs.renderTabBackground(guiGraphics, 0, 0, this.display, this.scrollX, this.scrollY);
            this.root.drawConnectivity(guiGraphics, i, j, true);
            this.root.drawConnectivity(guiGraphics, i, j, false);
            this.root.draw(guiGraphics, i, j);
            guiGraphics.popPose();
            Screen.disableScissor();
        }
    }

    @Inject(
            method = {"Lnet/minecraft/client/gui/screens/advancements/AdvancementTab;drawTooltips(Lcom/mojang/blaze3d/vertex/PoseStack;IIII)V"},
            remap = true,
            at = @At(value = "HEAD")
    )
    private void ac_drawTooltips(PoseStack guiGraphics, int mouseX, int mouseY, int topX, int topY, CallbackInfo ci) {
        if (ACAdvancementTabs.isAlexsCavesWidget(root.advancement)) {
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            ACAdvancementTabs.Type hoverType = null;
            if (mouseX > 0 && mouseX < 234 && mouseY > 0 && mouseY < 113) {
                for (AdvancementWidget advancementwidget : this.widgets.values()) {
                    if (advancementwidget.isMouseOver(i, j, mouseX, mouseY)) {
                        if (ACAdvancementTabs.Type.isTreeNodeUnlocked(advancementwidget)) {
                            hoverType = ACAdvancementTabs.Type.forAdvancement(advancementwidget.advancement);
                        }
                    }
                }
            }
            if (hoverType != null) {
                ACAdvancementTabs.setHoverType(hoverType);
            }
        }
    }
}

package com.github.alexmodguy.alexscaves.client.gui;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.StringDecomposer;

public class SpelunkeryTableWordButton extends AbstractWidget {

    public static final ResourceLocation TEXTURE = new ResourceLocation(AlexsCaves.MODID, "textures/gui/spelunkery_table.png");
    private SpelunkeryTableScreen parent;

    private Font font;
    private Component glyphText;
    private Component normalText;

    public SpelunkeryTableWordButton(SpelunkeryTableScreen parent, Font font, int x, int y, int height, int width, Component text) {
        super(x + parent.getGuiLeft(), y + parent.getGuiTop(), height, width, text);
        this.parent = parent;
        this.font = font;
        this.normalText = text.plainCopy().withStyle(Style.EMPTY);
        this.glyphText = text.copy().withStyle(SpelunkeryTableScreen.GLYPH_FONT);
    }

    @Override
    public void renderButton(PoseStack guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!parent.hasTablet()) {
            return;
        }
        float revealWordsAmount = parent.getRevealWordsAmount(Minecraft.getInstance().getFrameTime());
        int textColor = active ? 4210752 : 0XBFBFBF;
        int revealTextColor = parent.isTargetWord(this) ? parent.getHighlightColor() : 0XBFBFBF;
        int alpha = (int) ((1F - revealWordsAmount) * 255);
        int r = (int) (textColor >> 16 & 255);
        int g = (int) (textColor >> 8 & 255);
        int b = (int) (textColor & 255);
        int revealAlpha = (int) (revealWordsAmount * 255);
        int revealR = (int) (revealTextColor >> 16 & 255);
        int revealG = (int) (revealTextColor >> 8 & 255);
        int revealB = (int) (revealTextColor & 255);
        if (alpha >= 1) {
            drawEquidistantWord(font, guiGraphics, this.glyphText, this.x, this.y, FastColor.ARGB32.color(alpha, r, g, b));
        }
        if (revealAlpha >= 1) {
            drawEquidistantWord(font, guiGraphics, this.normalText, this.x, this.y, FastColor.ARGB32.color(revealAlpha, revealR, revealG, revealB));
        }
    }

    @Override
	public void updateNarration(NarrationElementOutput output) {

    }
    
    public void onClick(double x, double y) {
        if (parent.hasPaper()) {
            parent.onClickWord(this);
            this.active = false;
        }
    }

    public void playDownSound(SoundManager soundManager) {
    }

    public Component getNormalText() {
        return normalText;
    }

    public void renderTranslationText(int tickCount, int textColor, PoseStack guiGraphics, Font font, float magnifyingXMin, float magnifyingXMax, float magnifyingYMin, float magnifyingYMax) {
        if (!this.active) {
            guiGraphics.pushPose();
            enableScissor((int) magnifyingXMin, (int) magnifyingYMin, (int) magnifyingXMax, (int) magnifyingYMax);
            float age = (float) (Math.sin((tickCount + Minecraft.getInstance().getFrameTime()) * 0.2F) + 1F) * 0.5F;
            int alpha = (int) (Mth.clamp(age, 0.1F, 1F) * 255);
            int r = (int) (textColor >> 16 & 255);
            int g = (int) (textColor >> 8 & 255);
            int b = (int) (textColor & 255);
            drawEquidistantWord(font, guiGraphics, this.normalText, this.x, this.y, FastColor.ARGB32.color(alpha, r, g, b));
            disableScissor();
            guiGraphics.popPose();
        }
    }

    private void drawEquidistantWord(Font font, PoseStack guiGraphics, Component component, int x, int y, int color) {
        int letterWidth = 6;
        StringDecomposer.iterateFormatted(component, Style.EMPTY, (position, style, j) -> {
            drawString(guiGraphics, font, Component.literal(String.valueOf((char) j)).withStyle(style), x + letterWidth * position, y, color);
            return true;
        });
    }

}

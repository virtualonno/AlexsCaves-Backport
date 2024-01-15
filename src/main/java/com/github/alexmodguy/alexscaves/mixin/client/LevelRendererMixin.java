package com.github.alexmodguy.alexscaves.mixin.client;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.block.EnergizedGalenaBlock;
import com.github.alexmodguy.alexscaves.server.level.biome.ACBiomeRegistry;
import com.github.alexthe666.citadel.client.shader.PostEffectRegistry;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;

@Mixin(value = LevelRenderer.class, priority = 800)
public abstract class LevelRendererMixin {

    @Shadow
    private ClientLevel level;
    @Shadow
    private int ticks;
    private int aclastCameraChunkX;
    private int aclastCameraChunkY;
    private int aclastCameraChunkZ;
    @Shadow
    @Nullable
    private ViewArea viewArea;

    @Shadow @Final private Minecraft minecraft;

    @Shadow protected abstract void renderEndSky(PoseStack p_109781_);

    @Shadow protected abstract boolean doesMobEffectBlockSky(Camera p_234311_);

    @Shadow @Nullable private VertexBuffer skyBuffer;

    @Shadow @Final private static ResourceLocation SUN_LOCATION;

    @Shadow @Final private static ResourceLocation MOON_LOCATION;

    @Shadow @Nullable private VertexBuffer starBuffer;

    @Shadow @Nullable private VertexBuffer darkBuffer;
    
    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;initOutline()V",
            at = @At("TAIL"))
    private void ac_initOutline(CallbackInfo ci) {
        PostEffectRegistry.onInitializeOutline();
    }

    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;resize(II)V",
            at = @At("TAIL"))
    private void ac_resize(int x, int y, CallbackInfo ci) {
        PostEffectRegistry.resize(x, y);
    }

    /*@Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;renderLevel(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lcom/mojang/math/Matrix4f;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBuffers;bufferSource()Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;",
                    shift = At.Shift.BEFORE
            ))
    private void ac_renderLevel_beforeEntities(PoseStack poseStack, float f, long l, boolean b, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci) {
        PostEffectRegistry.copyDepth(this.minecraft.getMainRenderTarget());
    }*/

    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;renderLevel(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lcom/mojang/math/Matrix4f;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;endOutlineBatch()V",
                    shift = At.Shift.BEFORE
            ))
    private void ac_renderLevel_process(PoseStack poseStack, float f, long l, boolean b, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci) {
        PostEffectRegistry.processEffects(this.minecraft.getMainRenderTarget(), f);
    }

    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;renderLevel(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lcom/mojang/math/Matrix4f;)V",
            at = @At(
                    value = "TAIL"
            ))
    private void ac_renderLevel_end(PoseStack poseStack, float f, long l, boolean b, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci) {
        PostEffectRegistry.blitEffects();
    }
    
    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;setupRender(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/culling/Frustum;ZZ)V",
            at = @At(
                    value = "HEAD"
            ),
            allow = 1)
    private void ac_setupRender(Camera camera, Frustum frustum, boolean b1, boolean b2, CallbackInfo ci) {
        if (Minecraft.getInstance().cameraEntity != null && Minecraft.getInstance().cameraEntity != Minecraft.getInstance().player) { // fixes chunks being too far to load when not the player
            double d0 = Minecraft.getInstance().cameraEntity.getX();
            double d1 = Minecraft.getInstance().cameraEntity.getY();
            double d2 = Minecraft.getInstance().cameraEntity.getZ();
            int i = SectionPos.posToSectionCoord(d0);
            int j = SectionPos.posToSectionCoord(d1);
            int k = SectionPos.posToSectionCoord(d2);
            if (this.aclastCameraChunkX != i || this.aclastCameraChunkY != j || this.aclastCameraChunkZ != k) {
                this.aclastCameraChunkX = i;
                this.aclastCameraChunkY = j;
                this.aclastCameraChunkZ = k;
                viewArea.repositionCamera(d0, d2);
            }
        }
    }

    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;renderSky(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/math/Matrix4f;FLnet/minecraft/client/Camera;ZLjava/lang/Runnable;)V",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)
    //have to completely override this method for compatibility reasons
    private void ac_renderSky(PoseStack poseStack, Matrix4f matrix4f2, float partialTick, Camera camera, boolean foggy, Runnable runnable, CallbackInfo ci) {
        //AC CODE START
        float override = ACBiomeRegistry.calculateBiomeSkyOverride(Minecraft.getInstance().cameraEntity);
        if(!AlexsCaves.CLIENT_CONFIG.biomeSkyOverrides.get() || override <= 0.0F){
           return;
        }
        ci.cancel();
        // AC CODE END
        if (level.effects().renderSky(level, ticks, partialTick, poseStack, camera, matrix4f2, foggy, runnable))
            return;
        runnable.run();
        if (!foggy) {
            FogType fogtype = camera.getFluidInCamera();
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !this.doesMobEffectBlockSky(camera)) {
                if (this.minecraft.level.effects().skyType() == DimensionSpecialEffects.SkyType.END) {
                    this.renderEndSky(poseStack);
                } else if (this.minecraft.level.effects().skyType() == DimensionSpecialEffects.SkyType.NORMAL) {
                    Vec3 vec3 = this.level.getSkyColor(this.minecraft.gameRenderer.getMainCamera().getPosition(), partialTick);
                    float f = (float)vec3.x;
                    float f1 = (float)vec3.y;
                    float f2 = (float)vec3.z;
                    FogRenderer.levelFogColor();
                    BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                    RenderSystem.depthMask(false);
                    RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.skyBuffer.bind();
                    this.skyBuffer.drawWithShader(poseStack.last().pose(), matrix4f2, shaderinstance);
                    VertexBuffer.unbind();
                    RenderSystem.enableBlend();
                    float[] afloat = this.level.effects().getSunriseColor(this.level.getTimeOfDay(partialTick), partialTick);

                    // AC CODE START
                    //remove sunrises inside cave biomes.
                    if (afloat != null && afloat.length >= 4 && AlexsCaves.CLIENT_CONFIG.biomeSkyOverrides.get()) {
                        afloat[3] = afloat[3] * (1F - override);
                    }
                    // AC CODE END

                    if (afloat != null) {
                        RenderSystem.setShader(GameRenderer::getPositionColorShader);
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        poseStack.pushPose();
                        poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                        float f3 = Mth.sin(this.level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
                        poseStack.mulPose(Vector3f.ZP.rotationDegrees(f3));
                        poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                        float f4 = afloat[0];
                        float f5 = afloat[1];
                        float f6 = afloat[2];
                        Matrix4f matrix4f = poseStack.last().pose();
                        bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                        bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
                        int i = 16;

                        for(int j = 0; j <= 16; ++j) {
                            float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                            float f8 = Mth.sin(f7);
                            float f9 = Mth.cos(f7);
                            bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                        }

                        BufferUploader.drawWithShader(bufferbuilder.end());
                        poseStack.popPose();
                    }

                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    poseStack.pushPose();
                    // AC CODE START
                    //use the "rain level" to hide the sun and moon in the cave biomes.
                    float rainLevel = this.level.getRainLevel(partialTick);

                    rainLevel = Math.max(override, rainLevel);

                    float f11 = 1.0F - rainLevel;
                    // AC CODE END
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
                    poseStack.mulPose(Vector3f.XP.rotationDegrees(this.level.getTimeOfDay(partialTick) * 360.0F));
                    Matrix4f matrix4f1 = poseStack.last().pose();
                    float f12 = 30.0F;
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderTexture(0, SUN_LOCATION);
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
                    BufferUploader.drawWithShader(bufferbuilder.end());
                    f12 = 20.0F;
                    RenderSystem.setShaderTexture(0, MOON_LOCATION);
                    int k = this.level.getMoonPhase();
                    int l = k % 4;
                    int i1 = k / 4 % 2;
                    float f13 = (float)(l + 0) / 4.0F;
                    float f14 = (float)(i1 + 0) / 2.0F;
                    float f15 = (float)(l + 1) / 4.0F;
                    float f16 = (float)(i1 + 1) / 2.0F;
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
                    bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
                    BufferUploader.drawWithShader(bufferbuilder.end());
                    float f10 = this.level.getStarBrightness(partialTick) * f11;
                    if (f10 > 0.0F) {
                        RenderSystem.setShaderColor(f10, f10, f10, f10);
                        FogRenderer.setupNoFog();
                        this.starBuffer.bind();
                        this.starBuffer.drawWithShader(poseStack.last().pose(), matrix4f2, GameRenderer.getPositionShader());
                        VertexBuffer.unbind();
                        runnable.run();
                    }

                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                    poseStack.popPose();
                    RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
                    double horizonHeight = this.level.getLevelData().getHorizonHeight(this.level);
                    double d0 = this.minecraft.player.getEyePosition(partialTick).y - horizonHeight;
                    if (d0 < 0.0D) {
                        poseStack.pushPose();
                        poseStack.translate(0.0F, 12.0F, 0.0F);
                        this.darkBuffer.bind();
                        this.darkBuffer.drawWithShader(poseStack.last().pose(), matrix4f2, shaderinstance);
                        VertexBuffer.unbind();
                        poseStack.popPose();
                    }

                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.depthMask(true);
                }
            }
        }
    }
    @Inject(method = "Lnet/minecraft/client/renderer/LevelRenderer;getLightColor(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true)
    private static void ac_getLightColor(BlockAndTintGetter level, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (state.getBlock() instanceof EnergizedGalenaBlock) {
            int i = level.getBrightness(LightLayer.SKY, pos);
            int j = level.getBrightness(LightLayer.BLOCK, pos);
            int k = state.getLightEmission(level, pos) - 1;
            if (j < k) {
                j = k;
            }
            cir.setReturnValue(i << 20 | (j) << 4);
        }
    }
}

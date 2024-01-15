package com.github.alexmodguy.alexscaves.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.client.event.ClientEvents;
import com.github.alexmodguy.alexscaves.client.gui.NuclearFurnaceScreen;
import com.github.alexmodguy.alexscaves.client.gui.SpelunkeryTableScreen;
import com.github.alexmodguy.alexscaves.client.gui.book.CaveBookScreen;
import com.github.alexmodguy.alexscaves.client.model.baked.BakedModelShadeLayerFullbright;
import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.client.particle.AcidBubbleParticle;
import com.github.alexmodguy.alexscaves.client.particle.AcidDropParticle;
import com.github.alexmodguy.alexscaves.client.particle.AmberMonolithParticle;
import com.github.alexmodguy.alexscaves.client.particle.BigSplashEffectParticle;
import com.github.alexmodguy.alexscaves.client.particle.BigSplashParticle;
import com.github.alexmodguy.alexscaves.client.particle.BioPopParticle;
import com.github.alexmodguy.alexscaves.client.particle.DeepOneMagicParticle;
import com.github.alexmodguy.alexscaves.client.particle.FallingGuanoParticle;
import com.github.alexmodguy.alexscaves.client.particle.FalloutParticle;
import com.github.alexmodguy.alexscaves.client.particle.FerrouslimeParticle;
import com.github.alexmodguy.alexscaves.client.particle.FlyParticle;
import com.github.alexmodguy.alexscaves.client.particle.ForsakenSonarParticle;
import com.github.alexmodguy.alexscaves.client.particle.ForsakenSpitParticle;
import com.github.alexmodguy.alexscaves.client.particle.GalenaDebrisParticle;
import com.github.alexmodguy.alexscaves.client.particle.GammaroachParticle;
import com.github.alexmodguy.alexscaves.client.particle.HazmatBreatheParticle;
import com.github.alexmodguy.alexscaves.client.particle.MagnetLightningParticle;
import com.github.alexmodguy.alexscaves.client.particle.MagneticCavesAmbientParticle;
import com.github.alexmodguy.alexscaves.client.particle.MagneticFlowParticle;
import com.github.alexmodguy.alexscaves.client.particle.MagneticOrbitParticle;
import com.github.alexmodguy.alexscaves.client.particle.MothDustParticle;
import com.github.alexmodguy.alexscaves.client.particle.MushroomCloudParticle;
import com.github.alexmodguy.alexscaves.client.particle.NuclearSirenSonarParticle;
import com.github.alexmodguy.alexscaves.client.particle.ProtonParticle;
import com.github.alexmodguy.alexscaves.client.particle.QuarryBorderLightningParticle;
import com.github.alexmodguy.alexscaves.client.particle.RadgillSplashParticle;
import com.github.alexmodguy.alexscaves.client.particle.RaygunBlastParticle;
import com.github.alexmodguy.alexscaves.client.particle.ResistorShieldLightningParticle;
import com.github.alexmodguy.alexscaves.client.particle.SmallExplosionParticle;
import com.github.alexmodguy.alexscaves.client.particle.StunStarParticle;
import com.github.alexmodguy.alexscaves.client.particle.TeslaBulbLightningParticle;
import com.github.alexmodguy.alexscaves.client.particle.TubeWormParticle;
import com.github.alexmodguy.alexscaves.client.particle.UnderzealotMagicParticle;
import com.github.alexmodguy.alexscaves.client.particle.VentSmokeParticle;
import com.github.alexmodguy.alexscaves.client.particle.VoidBeingCloudParticle;
import com.github.alexmodguy.alexscaves.client.particle.VoidBeingEyeParticle;
import com.github.alexmodguy.alexscaves.client.particle.VoidBeingTendrilParticle;
import com.github.alexmodguy.alexscaves.client.particle.WatcherAppearanceParticle;
import com.github.alexmodguy.alexscaves.client.particle.WaterFoamParticle;
import com.github.alexmodguy.alexscaves.client.particle.WaterTremorParticle;
import com.github.alexmodguy.alexscaves.client.render.ACInternalShaders;
import com.github.alexmodguy.alexscaves.client.render.blockentity.AbyssalAltarBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.AmberMonolithBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.AmbersolBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.BeholderBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.CopperValveBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.HologramProjectorBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.MagnetBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.NuclearFurnaceBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.QuarryBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.SirenLightBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.blockentity.TelsaBulbBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.AlexsCavesBoatRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.BoundroidRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.BoundroidWinchRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.BrainiacRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.BurrowingArrowRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.CorrodentRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.DarkArrowRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.DeepOneKnightRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.DeepOneMageRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.DeepOneRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.DesolateDaggerRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.EmptyRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.FallingTreeBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.FerrouslimeRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.FloaterRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.ForsakenRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.GammaroachRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.GloomothRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.GossamerWormRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.GrottoceratopsRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.HullbreakerRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.LanternfishRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.LimestoneSpearRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.MagneticWeaponRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.MagnetronRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.MineGuardianAnchorRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.MineGuardianRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.MovingMetalBlockRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.NotorRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.NuclearBombRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.NucleeperRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.QuarrySmasherRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.RadgillRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.RaycatRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.RelicheirusRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.SeaPigRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.SeekingArrowRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.SubmarineRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.SubterranodonRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.TeletorRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.ThrownWasteDrumEntityRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.TremorsaurusRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.TrilocarisRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.TripodfishRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.UnderzealotRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.VallumraptorRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.VesperRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.WatcherRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.WaterBoltRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.WaveRenderer;
import com.github.alexmodguy.alexscaves.client.render.entity.layer.ClientLayerRegistry;
import com.github.alexmodguy.alexscaves.client.render.item.ACArmorRenderProperties;
import com.github.alexmodguy.alexscaves.client.render.item.ACItemRenderProperties;
import com.github.alexmodguy.alexscaves.client.sound.BoundroidSound;
import com.github.alexmodguy.alexscaves.client.sound.CorrodentSound;
import com.github.alexmodguy.alexscaves.client.sound.FerrouslimeSound;
import com.github.alexmodguy.alexscaves.client.sound.GalenaGauntletSound;
import com.github.alexmodguy.alexscaves.client.sound.HologramProjectorSound;
import com.github.alexmodguy.alexscaves.client.sound.MagnetSound;
import com.github.alexmodguy.alexscaves.client.sound.NotorHologramSound;
import com.github.alexmodguy.alexscaves.client.sound.NuclearFurnaceSound;
import com.github.alexmodguy.alexscaves.client.sound.NuclearSirenSound;
import com.github.alexmodguy.alexscaves.client.sound.NucleeperSound;
import com.github.alexmodguy.alexscaves.client.sound.QuarrySmasherSound;
import com.github.alexmodguy.alexscaves.client.sound.RaygunSound;
import com.github.alexmodguy.alexscaves.client.sound.ResistorShieldSound;
import com.github.alexmodguy.alexscaves.client.sound.SubmarineSound;
import com.github.alexmodguy.alexscaves.client.sound.UnderzealotSound;
import com.github.alexmodguy.alexscaves.server.CommonProxy;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.block.AcidBlock;
import com.github.alexmodguy.alexscaves.server.block.ActivatedByAltar;
import com.github.alexmodguy.alexscaves.server.block.blockentity.ACBlockEntityRegistry;
import com.github.alexmodguy.alexscaves.server.block.blockentity.HologramProjectorBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.blockentity.MagnetBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearFurnaceBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearSirenBlockEntity;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.item.QuarrySmasherEntity;
import com.github.alexmodguy.alexscaves.server.entity.item.SubmarineEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.BoundroidEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.CorrodentEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.FerrouslimeEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.NotorEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.NucleeperEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.UnderzealotEntity;
import com.github.alexmodguy.alexscaves.server.inventory.ACMenuRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.CaveInfoItem;
import com.github.alexmodguy.alexscaves.server.item.CaveMapItem;
import com.github.alexmodguy.alexscaves.server.item.GazingPearlItem;
import com.github.alexmodguy.alexscaves.server.item.HolocoderItem;
import com.github.alexmodguy.alexscaves.server.item.RemoteDetonatorItem;
import com.github.alexmodguy.alexscaves.server.item.TotemOfPossessionItem;
import com.github.alexmodguy.alexscaves.server.misc.ACKeybindRegistry;
import com.github.alexmodguy.alexscaves.server.misc.ACPotPatternRegistry;
import com.github.alexmodguy.alexscaves.server.misc.ACSoundRegistry;
import com.github.alexthe666.citadel.client.shader.PostEffectRegistry;
import com.google.common.collect.ImmutableList;
import com.min01.archaeology.blockentity.DecoratedPotRenderer;
import com.min01.archaeology.misc.DecoratedPotPatterns;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    private static final List<String> FULLBRIGHTS = ImmutableList.of("alexscaves:ambersol#", "alexscaves:radrock_uranium_ore#", "alexscaves:acidic_radrock#", "alexscaves:uranium_rod#axis=x", "alexscaves:uranium_rod#axis=y", "alexscaves:uranium_rod#axis=z", "alexscaves:block_of_uranium#", "alexscaves:abyssal_altar#active=true", "alexscaves:abyssmarine_", "alexscaves:peering_coprolith#", "alexscaves:forsaken_idol#", "alexscaves:magnetic_light#");
    public static final ResourceLocation BOMB_FLASH = new ResourceLocation(AlexsCaves.MODID, "textures/misc/bomb_flash.png");
    public static final ResourceLocation WATCHER_EFFECT = new ResourceLocation(AlexsCaves.MODID, "textures/misc/watcher_effect.png");
    public static final ResourceLocation IRRADIATED_SHADER = new ResourceLocation(AlexsCaves.MODID, "shaders/post/irradiated.json");
    public static final ResourceLocation HOLOGRAM_SHADER = new ResourceLocation(AlexsCaves.MODID, "shaders/post/hologram.json");
    public static final RandomSource random = RandomSource.create();
    public static int lastTremorTick = -1;
    public static float[] randomTremorOffsets = new float[3];
    public static List<UUID> blockedEntityRenders = new ArrayList<>();
    public static Map<ClientLevel, List<BlockPos>> blockedParticleLocations = new HashMap<>();
    public static Map<LivingEntity, Vec3[]> darknessTrailPosMap = new HashMap<>();
    public static Map<LivingEntity, Integer> darknessTrailPointerMap = new HashMap<>();
    public static int muteNonNukeSoundsFor = 0;
    public static int renderNukeFlashFor = 0;
    public static float prevNukeFlashAmount = 0;
    public static float nukeFlashAmount = 0;
    public static float prevPossessionStrengthAmount = 0;
    public static float possessionStrengthAmount = 0;
    public static int renderNukeSkyDarkFor = 0;
    public static float masterVolumeNukeModifier = 0.0F;
    public static final Int2ObjectMap<AbstractTickableSoundInstance> ENTITY_SOUND_INSTANCE_MAP = new Int2ObjectOpenHashMap<>();
    public static final Map<BlockEntity, AbstractTickableSoundInstance> BLOCK_ENTITY_SOUND_INSTANCE_MAP = new HashMap<>();
    private final ACItemRenderProperties isterProperties = new ACItemRenderProperties();
    private final ACArmorRenderProperties armorProperties = new ACArmorRenderProperties();
    public static boolean spelunkeryTutorialComplete;
    public static boolean hasACSplashText = false;
    public static CameraType lastPOV = CameraType.FIRST_PERSON;
    public static int shaderLoadAttemptCooldown = 0;

    public static Vec3 lastBiomeLightColor = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmount = 0;
    public static Vec3 lastBiomeLightColorPrev = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmountPrev = 0;

    public void commonInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupParticles);
        bus.addListener(this::onTextureStitchEvent);
        bus.addListener(this::registerKeybinds);
        bus.addListener(this::onItemColors);
    }
    
    public void onTextureStitchEvent(TextureStitchEvent.Pre event)
    {
    	event.addSprite(DecoratedPotPatterns.location(ACPotPatternRegistry.DINOSAUR)); 
    	event.addSprite(DecoratedPotPatterns.location(ACPotPatternRegistry.FOOTPRINT)); 
    	event.addSprite(DecoratedPotPatterns.location(ACPotPatternRegistry.GUARDIAN)); 
    	event.addSprite(DecoratedPotPatterns.location(ACPotPatternRegistry.HERO));
    	DecoratedPotRenderer.DECORATED_POT_MATERIALS.put(ACPotPatternRegistry.DINOSAUR, createDecoratedPotMaterial(ACPotPatternRegistry.DINOSAUR));
    	DecoratedPotRenderer.DECORATED_POT_MATERIALS.put(ACPotPatternRegistry.FOOTPRINT, createDecoratedPotMaterial(ACPotPatternRegistry.FOOTPRINT));
    	DecoratedPotRenderer.DECORATED_POT_MATERIALS.put(ACPotPatternRegistry.GUARDIAN, createDecoratedPotMaterial(ACPotPatternRegistry.GUARDIAN));
    	DecoratedPotRenderer.DECORATED_POT_MATERIALS.put(ACPotPatternRegistry.HERO, createDecoratedPotMaterial(ACPotPatternRegistry.HERO));
    }
    
    private static Material createDecoratedPotMaterial(ResourceKey<String> p_272805_) {
 	   return new Material(DecoratedPotRenderer.DECORATED_POT_SHEET, DecoratedPotPatterns.location(p_272805_));
    }

    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ClientLayerRegistry::addLayers);
        bus.addListener(this::bakeModels);
        bus.addListener(this::registerShaders);
        EntityRenderers.register(ACEntityRegistry.BOAT.get(), (context) -> {
            return new AlexsCavesBoatRenderer(context, false);
        });
        EntityRenderers.register(ACEntityRegistry.CHEST_BOAT.get(), (context) -> {
            return new AlexsCavesBoatRenderer(context, true);
        });
        BlockEntityRenderers.register(ACBlockEntityRegistry.MAGNET.get(), MagnetBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.TESLA_BULB.get(), TelsaBulbBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.HOLOGRAM_PROJECTOR.get(), HologramProjectorBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.QUARRY.get(), QuarryBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.AMBERSOL.get(), AmbersolBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.AMBER_MONOLITH.get(), AmberMonolithBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.NUCLEAR_FURNACE.get(), NuclearFurnaceBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.SIREN_LIGHT.get(), SirenLightBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.ABYSSAL_ALTAR.get(), AbyssalAltarBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.COPPER_VALVE.get(), CopperValveBlockRenderer::new);
        BlockEntityRenderers.register(ACBlockEntityRegistry.BEHOLDER.get(), BeholderBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MOVING_METAL_BLOCK.get(), MovingMetalBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TELETOR.get(), TeletorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MAGNETIC_WEAPON.get(), MagneticWeaponRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MAGNETRON.get(), MagnetronRenderer::new);
        EntityRenderers.register(ACEntityRegistry.BOUNDROID.get(), BoundroidRenderer::new);
        EntityRenderers.register(ACEntityRegistry.BOUNDROID_WINCH.get(), BoundroidWinchRenderer::new);
        EntityRenderers.register(ACEntityRegistry.FERROUSLIME.get(), FerrouslimeRenderer::new);
        EntityRenderers.register(ACEntityRegistry.NOTOR.get(), NotorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.QUARRY_SMASHER.get(), QuarrySmasherRenderer::new);
        EntityRenderers.register(ACEntityRegistry.SEEKING_ARROW.get(), SeekingArrowRenderer::new);
        EntityRenderers.register(ACEntityRegistry.SUBTERRANODON.get(), SubterranodonRenderer::new);
        EntityRenderers.register(ACEntityRegistry.VALLUMRAPTOR.get(), VallumraptorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GROTTOCERATOPS.get(), GrottoceratopsRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TRILOCARIS.get(), TrilocarisRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TREMORSAURUS.get(), TremorsaurusRenderer::new);
        EntityRenderers.register(ACEntityRegistry.RELICHEIRUS.get(), RelicheirusRenderer::new);
        EntityRenderers.register(ACEntityRegistry.FALLING_TREE_BLOCK.get(), FallingTreeBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.LIMESTONE_SPEAR.get(), LimestoneSpearRenderer::new);
        EntityRenderers.register(ACEntityRegistry.NUCLEAR_EXPLOSION.get(), EmptyRenderer::new);
        EntityRenderers.register(ACEntityRegistry.NUCLEAR_BOMB.get(), NuclearBombRenderer::new);
        EntityRenderers.register(ACEntityRegistry.NUCLEEPER.get(), NucleeperRenderer::new);
        EntityRenderers.register(ACEntityRegistry.RADGILL.get(), RadgillRenderer::new);
        EntityRenderers.register(ACEntityRegistry.BRAINIAC.get(), BrainiacRenderer::new);
        EntityRenderers.register(ACEntityRegistry.THROWN_WASTE_DRUM.get(), ThrownWasteDrumEntityRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GAMMAROACH.get(), GammaroachRenderer::new);
        EntityRenderers.register(ACEntityRegistry.RAYCAT.get(), RaycatRenderer::new);
        EntityRenderers.register(ACEntityRegistry.CINDER_BRICK.get(), (context) -> {
            return new ThrownItemRenderer<>(context, 1.25F, false);
        });
        EntityRenderers.register(ACEntityRegistry.LANTERNFISH.get(), LanternfishRenderer::new);
        EntityRenderers.register(ACEntityRegistry.SEA_PIG.get(), SeaPigRenderer::new);
        EntityRenderers.register(ACEntityRegistry.SUBMARINE.get(), SubmarineRenderer::new);
        EntityRenderers.register(ACEntityRegistry.HULLBREAKER.get(), HullbreakerRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GOSSAMER_WORM.get(), GossamerWormRenderer::new);
        EntityRenderers.register(ACEntityRegistry.TRIPODFISH.get(), TripodfishRenderer::new);
        EntityRenderers.register(ACEntityRegistry.DEEP_ONE.get(), DeepOneRenderer::new);
        EntityRenderers.register(ACEntityRegistry.INK_BOMB.get(), (context) -> {
            return new ThrownItemRenderer<>(context, 1.25F, false);
        });
        EntityRenderers.register(ACEntityRegistry.DEEP_ONE_KNIGHT.get(), DeepOneKnightRenderer::new);
        EntityRenderers.register(ACEntityRegistry.DEEP_ONE_MAGE.get(), DeepOneMageRenderer::new);
        EntityRenderers.register(ACEntityRegistry.WATER_BOLT.get(), WaterBoltRenderer::new);
        EntityRenderers.register(ACEntityRegistry.WAVE.get(), WaveRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MINE_GUARDIAN.get(), MineGuardianRenderer::new);
        EntityRenderers.register(ACEntityRegistry.MINE_GUARDIAN_ANCHOR.get(), MineGuardianAnchorRenderer::new);
        EntityRenderers.register(ACEntityRegistry.DEPTH_CHARGE.get(), (context) -> {
            return new ThrownItemRenderer<>(context, 1.75F, true);
        });
        EntityRenderers.register(ACEntityRegistry.FLOATER.get(), FloaterRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GUANO.get(), (context) -> {
            return new ThrownItemRenderer<>(context, 1.25F, false);
        });
        EntityRenderers.register(ACEntityRegistry.FALLING_GUANO.get(), FallingBlockRenderer::new);
        EntityRenderers.register(ACEntityRegistry.GLOOMOTH.get(), GloomothRenderer::new);
        EntityRenderers.register(ACEntityRegistry.UNDERZEALOT.get(), UnderzealotRenderer::new);
        EntityRenderers.register(ACEntityRegistry.WATCHER.get(), WatcherRenderer::new);
        EntityRenderers.register(ACEntityRegistry.CORRODENT.get(), CorrodentRenderer::new);
        EntityRenderers.register(ACEntityRegistry.VESPER.get(), VesperRenderer::new);
        EntityRenderers.register(ACEntityRegistry.FORSAKEN.get(), ForsakenRenderer::new);
        EntityRenderers.register(ACEntityRegistry.BEHOLDER_EYE.get(), EmptyRenderer::new);
        EntityRenderers.register(ACEntityRegistry.DESOLATE_DAGGER.get(), DesolateDaggerRenderer::new);
        EntityRenderers.register(ACEntityRegistry.BURROWING_ARROW.get(), BurrowingArrowRenderer::new);
        EntityRenderers.register(ACEntityRegistry.DARK_ARROW.get(), DarkArrowRenderer::new);
        Sheets.addWoodType(ACBlockRegistry.PEWEN_WOOD_TYPE);
        Sheets.addWoodType(ACBlockRegistry.THORNWOOD_WOOD_TYPE);
        ItemProperties.register(ACItemRegistry.CAVE_MAP.get(), new ResourceLocation("filled"), (stack, level, living, j) -> {
            return CaveMapItem.isFilled(stack) ? 1F : 0F;
        });
        ItemProperties.register(ACItemRegistry.CAVE_MAP.get(), new ResourceLocation("loading"), (stack, level, living, j) -> {
            return CaveMapItem.isLoading(stack) ? 1F : 0F;
        });
        ItemProperties.register(ACItemRegistry.HOLOCODER.get(), new ResourceLocation("bound"), (stack, level, living, j) -> {
            return HolocoderItem.isBound(stack) ? 1.0F : 0.0F;
        });
        ItemProperties.register(ACItemRegistry.DINOSAUR_NUGGET.get(), new ResourceLocation("nugget"), (stack, level, living, j) -> {
            return (stack.getCount() % 4) / 4F;
        });
        ItemProperties.register(ACItemRegistry.LIMESTONE_SPEAR.get(), new ResourceLocation("throwing"), (stack, level, living, j) -> {
            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ACItemRegistry.REMOTE_DETONATOR.get(), new ResourceLocation("active"), (stack, level, living, j) -> {
            return RemoteDetonatorItem.isActive(stack) ? 1.0F : 0.0F;
        });
        ItemProperties.register(ACItemRegistry.MAGIC_CONCH.get(), new ResourceLocation("tooting"), (stack, level, living, j) -> {
            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ACItemRegistry.ORTHOLANCE.get(), new ResourceLocation("charging"), (stack, level, living, j) -> {
            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ACItemRegistry.TOTEM_OF_POSSESSION.get(), new ResourceLocation("totem"), (stack, level, living, j) -> {
            return TotemOfPossessionItem.isBound(stack) ? living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.5F : 0.0F;
        });
        blockedParticleLocations.clear();
        PostEffectRegistry.registerEffect(IRRADIATED_SHADER);
        PostEffectRegistry.registerEffect(HOLOGRAM_SHADER);
        MenuScreens.register(ACMenuRegistry.SPELUNKERY_TABLE_MENU.get(), SpelunkeryTableScreen::new);
        MenuScreens.register(ACMenuRegistry.NUCLEAR_FURNACE_MENU.get(), NuclearFurnaceScreen::new);
        hasACSplashText = random.nextInt(300) == 0;
    }

    public void setupParticles(RegisterParticleProvidersEvent registry) {
        AlexsCaves.LOGGER.debug("Registered particle factories");
        registry.register(ACParticleRegistry.SCARLET_MAGNETIC_ORBIT.get(), new MagneticOrbitParticle.ScarletFactory());
        registry.register(ACParticleRegistry.AZURE_MAGNETIC_ORBIT.get(), new MagneticOrbitParticle.AzureFactory());
        registry.register(ACParticleRegistry.SCARLET_MAGNETIC_FLOW.get(), new MagneticFlowParticle.ScarletFactory());
        registry.register(ACParticleRegistry.AZURE_MAGNETIC_FLOW.get(), new MagneticFlowParticle.AzureFactory());
        registry.register(ACParticleRegistry.TESLA_BULB_LIGHTNING.get(), new TeslaBulbLightningParticle.Factory());
        registry.register(ACParticleRegistry.MAGNET_LIGHTNING.get(), new MagnetLightningParticle.Factory());
        registry.register(ACParticleRegistry.GALENA_DEBRIS.get(), GalenaDebrisParticle.Factory::new);
        registry.register(ACParticleRegistry.MAGNETIC_CAVES_AMBIENT.get(), new MagneticCavesAmbientParticle.Factory());
        registry.register(ACParticleRegistry.FERROUSLIME.get(), FerrouslimeParticle.Factory::new);
        registry.register(ACParticleRegistry.QUARRY_BORDER_LIGHTING.get(), new QuarryBorderLightningParticle.Factory());
        registry.register(ACParticleRegistry.AZURE_SHIELD_LIGHTNING.get(), new ResistorShieldLightningParticle.AzureFactory());
        registry.register(ACParticleRegistry.SCARLET_SHIELD_LIGHTNING.get(), new ResistorShieldLightningParticle.ScarletFactory());
        registry.register(ACParticleRegistry.FLY.get(), FlyParticle.Factory::new);
        registry.register(ACParticleRegistry.WATER_TREMOR.get(), WaterTremorParticle.Factory::new);
        registry.register(ACParticleRegistry.AMBER_MONOLITH.get(), AmberMonolithParticle.Factory::new);
        registry.register(ACParticleRegistry.AMBER_EXPLOSION.get(), SmallExplosionParticle.AmberFactory::new);
        registry.register(ACParticleRegistry.STUN_STAR.get(), new StunStarParticle.Factory());
        registry.register(ACParticleRegistry.ACID_BUBBLE.get(), AcidBubbleParticle.Factory::new);
        registry.register(ACParticleRegistry.BLACK_VENT_SMOKE.get(), VentSmokeParticle.BlackFactory::new);
        registry.register(ACParticleRegistry.WHITE_VENT_SMOKE.get(), VentSmokeParticle.WhiteFactory::new);
        registry.register(ACParticleRegistry.GREEN_VENT_SMOKE.get(), VentSmokeParticle.GreenFactory::new);
        registry.register(ACParticleRegistry.MUSHROOM_CLOUD.get(), new MushroomCloudParticle.Factory());
        registry.register(ACParticleRegistry.MUSHROOM_CLOUD_SMOKE.get(), SmallExplosionParticle.NukeFactory::new);
        registry.register(ACParticleRegistry.MUSHROOM_CLOUD_EXPLOSION.get(), SmallExplosionParticle.NukeFactory::new);
        registry.register(ACParticleRegistry.PROTON.get(), new ProtonParticle.Factory());
        registry.register(ACParticleRegistry.FALLOUT.get(), FalloutParticle.Factory::new);
        registry.register(ACParticleRegistry.GAMMAROACH.get(), GammaroachParticle.Factory::new);
        registry.register(ACParticleRegistry.HAZMAT_BREATHE.get(), HazmatBreatheParticle.Factory::new);
        registry.register(ACParticleRegistry.RADGILL_SPLASH.get(), RadgillSplashParticle.Factory::new);
        registry.register(ACParticleRegistry.ACID_DROP.get(), AcidDropParticle.Factory::new);
        registry.register(ACParticleRegistry.NUCLEAR_SIREN_SONAR.get(), NuclearSirenSonarParticle.Factory::new);
        registry.register(ACParticleRegistry.RAYGUN_EXPLOSION.get(), SmallExplosionParticle.RaygunFactory::new);
        registry.register(ACParticleRegistry.RAYGUN_BLAST.get(), RaygunBlastParticle.Factory::new);
        registry.register(ACParticleRegistry.TUBE_WORM.get(), new TubeWormParticle.Factory());
        registry.register(ACParticleRegistry.DEEP_ONE_MAGIC.get(), DeepOneMagicParticle.Factory::new);
        registry.register(ACParticleRegistry.WATER_FOAM.get(), WaterFoamParticle.Factory::new);
        registry.register(ACParticleRegistry.BIG_SPLASH.get(), new BigSplashParticle.Factory());
        registry.register(ACParticleRegistry.BIG_SPLASH_EFFECT.get(), BigSplashEffectParticle.Factory::new);
        registry.register(ACParticleRegistry.MINE_EXPLOSION.get(), SmallExplosionParticle.MineFactory::new);
        registry.register(ACParticleRegistry.BIO_POP.get(), BioPopParticle.Factory::new);
        registry.register(ACParticleRegistry.WATCHER_APPEARANCE.get(), new WatcherAppearanceParticle.Factory());
        registry.register(ACParticleRegistry.VOID_BEING_CLOUD.get(), new VoidBeingCloudParticle.Factory());
        registry.register(ACParticleRegistry.VOID_BEING_TENDRIL.get(), new VoidBeingTendrilParticle.Factory());
        registry.register(ACParticleRegistry.VOID_BEING_EYE.get(), new VoidBeingEyeParticle.Factory());
        registry.register(ACParticleRegistry.UNDERZEALOT_MAGIC.get(), UnderzealotMagicParticle.Factory::new);
        registry.register(ACParticleRegistry.UNDERZEALOT_EXPLOSION.get(), SmallExplosionParticle.UnderzealotFactory::new);
        registry.register(ACParticleRegistry.FALLING_GUANO.get(), FallingGuanoParticle.Factory::new);
        registry.register(ACParticleRegistry.MOTH_DUST.get(), MothDustParticle.Factory::new);
        registry.register(ACParticleRegistry.FORSAKEN_SPIT.get(), ForsakenSpitParticle.Factory::new);
        registry.register(ACParticleRegistry.FORSAKEN_SONAR.get(), ForsakenSonarParticle.Factory::new);
        registry.register(ACParticleRegistry.FORSAKEN_SONAR_LARGE.get(), ForsakenSonarParticle.LargeFactory::new);
    }

    public void onItemColors(RegisterColorHandlersEvent.Item event) {
        AlexsCaves.LOGGER.info("loaded in item colorizer");
        event.register((stack, colorIn) -> colorIn != 1 ? -1 : CaveInfoItem.getBiomeColorOf(Minecraft.getInstance().level, stack), ACItemRegistry.CAVE_TABLET.get());
        event.register((stack, colorIn) -> colorIn != 1 ? -1 : CaveInfoItem.getBiomeColorOf(Minecraft.getInstance().level, stack), ACItemRegistry.CAVE_CODEX.get());
        event.register((stack, colorIn) -> colorIn != 0 ? -1 : GazingPearlItem.getPearlColor(stack), ACItemRegistry.GAZING_PEARL.get());
    }

    private void bakeModels(final ModelEvent.BakingCompleted e) {
        if (AlexsCaves.CLIENT_CONFIG.emissiveBlockModels.get()) {
            long time = System.currentTimeMillis();
            for (ResourceLocation id : e.getModels().keySet()) {
                if (FULLBRIGHTS.stream().anyMatch(str -> id.toString().startsWith(str))) {
                    e.getModels().put(id, new BakedModelShadeLayerFullbright(e.getModels().get(id)));
                }
            }
            AlexsCaves.LOGGER.info("Loaded emissive block models in {} ms", System.currentTimeMillis() - time);

        }
    }

    private void registerShaders(final RegisterShadersEvent e) {
        try {
            e.registerShader(new ShaderInstance(e.getResourceManager(), new ResourceLocation(AlexsCaves.MODID, "rendertype_ferrouslime_gel"), DefaultVertexFormat.NEW_ENTITY), ACInternalShaders::setRenderTypeFerrouslimeGelShader);
            e.registerShader(new ShaderInstance(e.getResourceManager(), new ResourceLocation(AlexsCaves.MODID, "rendertype_hologram"), DefaultVertexFormat.POSITION_COLOR), ACInternalShaders::setRenderTypeHologramShader);
            e.registerShader(new ShaderInstance(e.getResourceManager(), new ResourceLocation(AlexsCaves.MODID, "rendertype_irradiated"), DefaultVertexFormat.POSITION_COLOR_TEX), ACInternalShaders::setRenderTypeIrradiatedShader);
            e.registerShader(new ShaderInstance(e.getResourceManager(), new ResourceLocation(AlexsCaves.MODID, "rendertype_bubbled"), DefaultVertexFormat.NEW_ENTITY), ACInternalShaders::setRenderTypeBubbledShader);
            e.registerShader(new ShaderInstance(e.getResourceManager(), new ResourceLocation(AlexsCaves.MODID, "rendertype_sepia"), DefaultVertexFormat.NEW_ENTITY), ACInternalShaders::setRenderTypeSepiaShader);
            AlexsCaves.LOGGER.info("registered internal shaders");
        } catch (IOException exception) {
            AlexsCaves.LOGGER.error("could not register internal shaders");
            exception.printStackTrace();
        }
    }

    private void registerKeybinds(RegisterKeyMappingsEvent e) {
        e.register(ACKeybindRegistry.KEY_SPECIAL_ABILITY);
    }

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }

    public void blockRenderingEntity(UUID id) {
        blockedEntityRenders.add(id);
    }

    public void releaseRenderingEntity(UUID id) {
        blockedEntityRenders.remove(id);
    }

    public void setVisualFlag(int flag) {
    }

    public float getNukeFlashAmount(float partialTicks) {
        return prevNukeFlashAmount + (nukeFlashAmount - prevNukeFlashAmount) * partialTicks;
    }

    public float getPossessionStrengthAmount(float partialTicks) {
        return prevPossessionStrengthAmount + (possessionStrengthAmount - prevPossessionStrengthAmount) * partialTicks;
    }

    public boolean checkIfParticleAt(SimpleParticleType simpleParticleType, BlockPos at) {
        if (!blockedParticleLocations.containsKey(Minecraft.getInstance().level)) {
            blockedParticleLocations.clear();
            blockedParticleLocations.put(Minecraft.getInstance().level, new ArrayList<>());
        }
        if (simpleParticleType == ACParticleRegistry.TUBE_WORM.get()) {
            List blocked = blockedParticleLocations.get(Minecraft.getInstance().level);
            if (blocked.contains(at)) {
                return false;
            } else {
                blocked.add(new BlockPos(at));
                return true;
            }
        }
        return true;
    }

    public void removeParticleAt(BlockPos at) {
        if (!blockedParticleLocations.containsKey(Minecraft.getInstance().level)) {
            blockedParticleLocations.clear();
            blockedParticleLocations.put(Minecraft.getInstance().level, new ArrayList<>());
        }
        blockedParticleLocations.get(Minecraft.getInstance().level).remove(at);
    }


    public boolean isKeyDown(int keyType) {
        if (keyType == -1) {
            return Minecraft.getInstance().options.keyLeft.isDown() || Minecraft.getInstance().options.keyRight.isDown() || Minecraft.getInstance().options.keyUp.isDown() || Minecraft.getInstance().options.keyDown.isDown() || Minecraft.getInstance().options.keyJump.isDown();
        }
        if (keyType == 0) {
            return Minecraft.getInstance().options.keyJump.isDown();
        }
        if (keyType == 1) {
            return Minecraft.getInstance().options.keySprint.isDown();
        }
        if (keyType == 2) {
            return ACKeybindRegistry.KEY_SPECIAL_ABILITY.isDown();
        }
        if (keyType == 3) {
            return Minecraft.getInstance().options.keyAttack.isDown();
        }
        if (keyType == 4) {
            return Minecraft.getInstance().options.keyShift.isDown();
        }
        return false;
    }

    @Override
    public Object getISTERProperties() {
        return isterProperties;
    }

    @Override
    public Object getArmorProperties() {
        return armorProperties;
    }

    public float getPartialTicks() {
        return Minecraft.getInstance().getPartialTick();
    }

    public void setSpelunkeryTutorialComplete(boolean completedTutorial) {
        this.spelunkeryTutorialComplete = completedTutorial;
    }

    public boolean isSpelunkeryTutorialComplete() {
        return this.spelunkeryTutorialComplete;
    }

    public void setRenderViewEntity(Player player, Entity entity) {
        boolean flag = entity != Minecraft.getInstance().getCameraEntity();
        if (player == Minecraft.getInstance().player && Minecraft.getInstance().getCameraEntity() == Minecraft.getInstance().player) {
            lastPOV = Minecraft.getInstance().options.getCameraType();
            Minecraft.getInstance().setCameraEntity(entity);
            Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
        }
        if (flag) {
            Minecraft.getInstance().levelRenderer.allChanged();
        }
    }

    public void resetRenderViewEntity(Player player) {
        boolean flag = Minecraft.getInstance().player != Minecraft.getInstance().getCameraEntity();
        if (player == Minecraft.getInstance().player) {
            Minecraft.getInstance().level = (ClientLevel) Minecraft.getInstance().player.level;
            Minecraft.getInstance().setCameraEntity(Minecraft.getInstance().player);
            Minecraft.getInstance().options.setCameraType(lastPOV);
        }
        if (flag) {
            Minecraft.getInstance().levelRenderer.allChanged();
        }
    }

    @Override
    public void playWorldSound(@Nullable Object soundEmitter, byte type) {
        if (soundEmitter instanceof Entity entity && !entity.level.isClientSide) {
            return;
        }
        switch (type) {
            case 0:
                if (soundEmitter instanceof NuclearSirenBlockEntity nuclearSiren) {
                    NuclearSirenSound sound;
                    AbstractTickableSoundInstance old = BLOCK_ENTITY_SOUND_INSTANCE_MAP.get(nuclearSiren);
                    if (old == null || !(old instanceof NuclearSirenSound nuclearSirenSound && nuclearSirenSound.isSameBlockEntity(nuclearSiren)) || old.isStopped()) {
                        sound = new NuclearSirenSound(nuclearSiren);
                        BLOCK_ENTITY_SOUND_INSTANCE_MAP.put(nuclearSiren, sound);
                    } else {
                        sound = (NuclearSirenSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 1:
                if (soundEmitter instanceof NucleeperEntity nucleeper) {
                    NucleeperSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(nucleeper.getId());
                    if (old == null || !(old instanceof NucleeperSound nucleeperSound && nucleeperSound.isSameEntity(nucleeper))) {
                        sound = new NucleeperSound(nucleeper);
                        ENTITY_SOUND_INSTANCE_MAP.put(nucleeper.getId(), sound);
                    } else {
                        sound = (NucleeperSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 2:
                if (soundEmitter instanceof NotorEntity notor) {
                    NotorHologramSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(notor.getId());
                    if (old == null || !(old instanceof NotorHologramSound hologramSound && hologramSound.isSameEntity(notor))) {
                        sound = new NotorHologramSound(notor);
                        ENTITY_SOUND_INSTANCE_MAP.put(notor.getId(), sound);
                    } else {
                        sound = (NotorHologramSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 3:
                if (soundEmitter instanceof HologramProjectorBlockEntity hologramProjector) {
                    HologramProjectorSound sound;
                    AbstractTickableSoundInstance old = BLOCK_ENTITY_SOUND_INSTANCE_MAP.get(hologramProjector);
                    if (old == null || !(old instanceof HologramProjectorSound hologramSound && hologramSound.isSameBlockEntity(hologramProjector)) || old.isStopped()) {
                        sound = new HologramProjectorSound(hologramProjector);
                        BLOCK_ENTITY_SOUND_INSTANCE_MAP.put(hologramProjector, sound);
                    } else {
                        sound = (HologramProjectorSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 4:
                if (soundEmitter instanceof MagnetBlockEntity magnet) {
                    MagnetSound sound;
                    AbstractTickableSoundInstance old = BLOCK_ENTITY_SOUND_INSTANCE_MAP.get(magnet);
                    if (old == null || !(old instanceof MagnetSound magnetSound && magnetSound.isSameBlockEntity(magnet)) || old.isStopped()) {
                        sound = new MagnetSound(magnet);
                        BLOCK_ENTITY_SOUND_INSTANCE_MAP.put(magnet, sound);
                    } else {
                        sound = (MagnetSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 5:
                if (soundEmitter instanceof UnderzealotEntity underzealot) {
                    UnderzealotSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(underzealot.getId());
                    if (old == null || !(old instanceof UnderzealotSound underzealotSound && underzealotSound.isSameEntity(underzealot))) {
                        sound = new UnderzealotSound(underzealot);
                        ENTITY_SOUND_INSTANCE_MAP.put(underzealot.getId(), sound);
                    } else {
                        sound = (UnderzealotSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 6:
                if (soundEmitter instanceof CorrodentEntity corrodent) {
                    CorrodentSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(corrodent.getId());
                    if (old == null || !(old instanceof CorrodentSound corrodentSound && corrodentSound.isSameEntity(corrodent))) {
                        sound = new CorrodentSound(corrodent);
                        ENTITY_SOUND_INSTANCE_MAP.put(corrodent.getId(), sound);
                    } else {
                        sound = (CorrodentSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 7:
                if (soundEmitter instanceof NuclearFurnaceBlockEntity nuclearFurnace) {
                    NuclearFurnaceSound sound;
                    AbstractTickableSoundInstance old = BLOCK_ENTITY_SOUND_INSTANCE_MAP.get(nuclearFurnace);
                    if (old == null || !(old instanceof NuclearFurnaceSound furnaceSound && furnaceSound.isSameBlockEntity(nuclearFurnace)) || old.isStopped()) {
                        sound = new NuclearFurnaceSound(nuclearFurnace);
                        BLOCK_ENTITY_SOUND_INSTANCE_MAP.put(nuclearFurnace, sound);
                    } else {
                        sound = (NuclearFurnaceSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 8:
                if (soundEmitter instanceof LivingEntity livingEntity) {
                    RaygunSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(livingEntity.getId());
                    if (old == null || !(old instanceof RaygunSound raygunSound && raygunSound.isSameEntity(livingEntity))) {
                        sound = new RaygunSound(livingEntity);
                        ENTITY_SOUND_INSTANCE_MAP.put(livingEntity.getId(), sound);
                    } else {
                        sound = (RaygunSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 9:
                if (soundEmitter instanceof LivingEntity livingEntity) {
                    ResistorShieldSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(livingEntity.getId());
                    if (old == null || !(old instanceof ResistorShieldSound resistorShieldSound && resistorShieldSound.isSameEntity(livingEntity) && !resistorShieldSound.isAzure())) {
                        sound = new ResistorShieldSound(livingEntity, false);
                        ENTITY_SOUND_INSTANCE_MAP.put(livingEntity.getId(), sound);
                    } else {
                        sound = (ResistorShieldSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 10:
                if (soundEmitter instanceof LivingEntity livingEntity) {
                    ResistorShieldSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(livingEntity.getId());
                    if (old == null || !(old instanceof ResistorShieldSound resistorShieldSound && resistorShieldSound.isSameEntity(livingEntity) && resistorShieldSound.isAzure())) {
                        sound = new ResistorShieldSound(livingEntity, true);
                        ENTITY_SOUND_INSTANCE_MAP.put(livingEntity.getId(), sound);
                    } else {
                        sound = (ResistorShieldSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 11:
                if (soundEmitter instanceof LivingEntity livingEntity) {
                    GalenaGauntletSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(livingEntity.getId());
                    if (old == null || !(old instanceof GalenaGauntletSound gauntletSound && gauntletSound.isSameEntity(livingEntity))) {
                        sound = new GalenaGauntletSound(livingEntity);
                        ENTITY_SOUND_INSTANCE_MAP.put(livingEntity.getId(), sound);
                    } else {
                        sound = (GalenaGauntletSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 12:
                if (soundEmitter instanceof BoundroidEntity boundroid) {
                    BoundroidSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(boundroid.getId());
                    if (old == null || !(old instanceof BoundroidSound boundroidSound && boundroidSound.isSameEntity(boundroid))) {
                        sound = new BoundroidSound(boundroid);
                        ENTITY_SOUND_INSTANCE_MAP.put(boundroid.getId(), sound);
                    } else {
                        sound = (BoundroidSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 13:
                if (soundEmitter instanceof FerrouslimeEntity ferrouslime) {
                    FerrouslimeSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(ferrouslime.getId());
                    if (old == null || !(old instanceof FerrouslimeSound ferrouslimeSound && ferrouslimeSound.isSameEntity(ferrouslime))) {
                        sound = new FerrouslimeSound(ferrouslime);
                        ENTITY_SOUND_INSTANCE_MAP.put(ferrouslime.getId(), sound);
                    } else {
                        sound = (FerrouslimeSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 14:
                if (soundEmitter instanceof QuarrySmasherEntity quarrySmasher) {
                    QuarrySmasherSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(quarrySmasher.getId());
                    if (old == null || !(old instanceof QuarrySmasherSound quarrySmasherSound && quarrySmasherSound.isSameEntity(quarrySmasher))) {
                        sound = new QuarrySmasherSound(quarrySmasher);
                        ENTITY_SOUND_INSTANCE_MAP.put(quarrySmasher.getId(), sound);
                    } else {
                        sound = (QuarrySmasherSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
            case 15:
                if (soundEmitter instanceof SubmarineEntity submarine) {
                    SubmarineSound sound;
                    AbstractTickableSoundInstance old = ENTITY_SOUND_INSTANCE_MAP.get(submarine.getId());
                    if (old == null || !(old instanceof SubmarineSound submarineSound && submarineSound.isSameEntity(submarine))) {
                        sound = new SubmarineSound(submarine);
                        ENTITY_SOUND_INSTANCE_MAP.put(submarine.getId(), sound);
                    } else {
                        sound = (SubmarineSound) old;
                    }
                    if (!Minecraft.getInstance().getSoundManager().isActive(sound) && sound.canPlaySound()) {
                        Minecraft.getInstance().getSoundManager().queueTickingSound(sound);
                    }
                }
                break;
        }
    }

    public void playWorldEvent(int messageId, Level level, BlockPos pos) {
        if (messageId == 0 && AcidBlock.doesBlockCorrode(level.getBlockState(pos))) {
            level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, ACSoundRegistry.ACID_CORROSION.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.4F + 0.8F, false);
        }
        if(messageId == 1 && level.getBlockState(pos).getBlock() instanceof ActivatedByAltar){
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, ACSoundRegistry.ABYSSMARINE_GLOW_ON.get(), SoundSource.BLOCKS, 1.5F, level.random.nextFloat() * 0.4F + 0.8F, false);
        }
        if(messageId == 2 && level.getBlockState(pos).getBlock() instanceof ActivatedByAltar){
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, ACSoundRegistry.ABYSSMARINE_GLOW_OFF.get(), SoundSource.BLOCKS, 1.5F, level.random.nextFloat() * 0.4F + 0.8F, false);
        }
        if(messageId == 3 && level.getBlockState(pos).is(ACBlockRegistry.DRAIN.get())){
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, ACSoundRegistry.DRAIN_START.get(), SoundSource.BLOCKS, 1.5F, level.random.nextFloat() * 0.4F + 0.8F, false);
        }
        if(messageId == 4 && level.getBlockState(pos).is(ACBlockRegistry.DRAIN.get())){
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, ACSoundRegistry.DRAIN_STOP.get(), SoundSource.BLOCKS, 1.5F, level.random.nextFloat() * 0.4F + 0.8F, false);
        }
    }

    public void clearSoundCacheFor(Entity entity) {
        ENTITY_SOUND_INSTANCE_MAP.remove(entity.getId());
    }

    public void clearSoundCacheFor(BlockEntity entity) {
        BLOCK_ENTITY_SOUND_INSTANCE_MAP.remove(entity);
    }

    public Vec3 getDarknessTrailPosFor(LivingEntity living, int pointer, float partialTick) {
        if (living.isRemoved()) {
            partialTick = 1.0F;
        }
        Vec3[] trailPositions = darknessTrailPosMap.get(living);
        if (trailPositions == null || !darknessTrailPointerMap.containsKey(living)) {
            return living.position();
        }
        int trailPointer = darknessTrailPointerMap.get(living);
        int i = trailPointer - pointer & 63;
        int j = trailPointer - pointer - 1 & 63;
        Vec3 d0 = trailPositions[j];
        Vec3 d1 = trailPositions[i].subtract(d0);
        return d0.add(d1.scale(partialTick));
    }


    public int getPlayerTime() {
        return Minecraft.getInstance().player == null ? 0 : Minecraft.getInstance().player.tickCount;
    }

    public void preScreenRender(float partialTick) {
        float screenEffectIntensity = Minecraft.getInstance().options.screenEffectScale().get().floatValue();
        float watcherPossessionStrength = getPossessionStrengthAmount(partialTick);
        float nukeFlashAmount = getNukeFlashAmount(partialTick);
        if (nukeFlashAmount > 0 && (AlexsCaves.CLIENT_CONFIG.nuclearBombFlash.get())) {
            int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
            int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, nukeFlashAmount * screenEffectIntensity);
            RenderSystem.setShaderTexture(0, ClientProxy.BOMB_FLASH);
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.vertex(0.0D, screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
            bufferbuilder.vertex(screenWidth, screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
            bufferbuilder.vertex(screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
            tesselator.end();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        if (watcherPossessionStrength > 0) {
            int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
            int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, watcherPossessionStrength * screenEffectIntensity);
            RenderSystem.setShaderTexture(0, ClientProxy.WATCHER_EFFECT);
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.vertex(0.0D, screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
            bufferbuilder.vertex(screenWidth, screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
            bufferbuilder.vertex(screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
            tesselator.end();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public boolean isFirstPersonPlayer(Entity entity) {
        return entity.equals(Minecraft.getInstance().cameraEntity) && Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }


    public void openBookGUI(ItemStack itemStackIn) {
        Minecraft.getInstance().setScreen(new CaveBookScreen());
    }

    @Override
    public Vec3 getCameraRotation() {
        return Vec3.ZERO;
    }
}


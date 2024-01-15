package com.github.alexmodguy.alexscaves.server.block;

import java.util.function.Supplier;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.block.fluid.ACFluidRegistry;
import com.github.alexmodguy.alexscaves.server.block.grower.AncientTreeGrower;
import com.github.alexmodguy.alexscaves.server.block.grower.PewenGrower;
import com.github.alexmodguy.alexscaves.server.block.grower.ThornwoodGrower;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.BlockItemWithISTER;
import com.github.alexmodguy.alexscaves.server.item.BlockItemWithScaffolding;
import com.github.alexmodguy.alexscaves.server.item.BlockItemWithSupplierLore;
import com.github.alexmodguy.alexscaves.server.item.RadioactiveBlockItem;
import com.github.alexmodguy.alexscaves.server.item.RadioactiveOnDestroyedBlockItem;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;
import com.github.alexthe666.citadel.item.BlockItemWithSupplier;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ACBlockRegistry {

    public static final BlockBehaviour.Properties GALENA_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(3.5F, 10.0F).sound(SoundType.DEEPSLATE);
    public static final BlockBehaviour.Properties ENERGIZED_GALENA_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(3F, 10.0F).lightLevel(state -> 5).sound(SoundType.DEEPSLATE);
    public static final BlockBehaviour.Properties LIMESTONE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_YELLOW).requiresCorrectToolForDrops().strength(1.2F, 4.5F).sound(SoundType.DRIPSTONE_BLOCK);
    public static final BlockBehaviour.Properties PEWEN_LOG_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties PEWEN_PLANKS_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties RADROCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().strength(4F, 11.0F).sound(ACSoundTypes.RADROCK);
    public static final BlockBehaviour.Properties CINDER_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5F, 20.0F).sound(ACSoundTypes.CINDER_BLOCK);
    public static final BlockBehaviour.Properties RADON_LAMP_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().lightLevel(state -> 15).strength(2F, 11.0F).sound(SoundType.GLASS);
    public static final BlockBehaviour.Properties SMOOTH_BONE_PROPERTIES = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.BONE_BLOCK);
    public static final BlockBehaviour.Properties ABYSSMARINE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.5F, 10.0F).sound(SoundType.DEEPSLATE);
    public static final BlockBehaviour.Properties GUANOSTONE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_CYAN).requiresCorrectToolForDrops().strength(1.3F, 2.0F).sound(SoundType.BASALT);
    public static final BlockBehaviour.Properties COPROLITH_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(1.75F, 4.0F).sound(SoundType.CALCITE);
    public static final BlockBehaviour.Properties POROUS_COPROLITH_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(1.75F, 4.0F).sound(SoundType.CALCITE).noOcclusion();
    public static final BlockBehaviour.Properties PEERING_COPROLITH_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(1.75F, 4.0F).sound(ACSoundTypes.PEERING_COPROLITH).noOcclusion();
    public static final BlockBehaviour.Properties THORNWOOD_LOG_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties THORNWOOD_PLANKS_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    public static final WoodType PEWEN_WOOD_TYPE = WoodType.register(WoodType.create("alexscaves:pewen"));
    public static final WoodType THORNWOOD_WOOD_TYPE = WoodType.register(WoodType.create("alexscaves:thornwood"));

    public static final DeferredRegister<Block> DEF_REG = DeferredRegister.create(ForgeRegistries.BLOCKS, AlexsCaves.MODID);
    public static final RegistryObject<Block> SPELUNKERY_TABLE = registerBlockAndItem("spelunkery_table", () -> new SpelunkeryTableBlock(), 10);
    public static final RegistryObject<Block> GALENA = registerBlockAndItem("galena", () -> new Block(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_STAIRS = registerBlockAndItem("galena_stairs", () -> new StairBlock(GALENA.get().defaultBlockState(), GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_SLAB = registerBlockAndItem("galena_slab", () -> new SlabBlock(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_WALL = registerBlockAndItem("galena_wall", () -> new WallBlock(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> PACKED_GALENA = registerBlockAndItem("packed_galena", () -> new Block(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_BRICKS = registerBlockAndItem("galena_bricks", () -> new Block(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_BRICK_STAIRS = registerBlockAndItem("galena_brick_stairs", () -> new StairBlock(GALENA_BRICKS.get().defaultBlockState(), GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_BRICK_SLAB = registerBlockAndItem("galena_brick_slab", () -> new SlabBlock(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_BRICK_WALL = registerBlockAndItem("galena_brick_wall", () -> new WallBlock(GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_PILLAR = registerBlockAndItem("galena_pillar", () -> new GalenaPillarBlock(), 8);
    public static final RegistryObject<Block> GALENA_IRON_ORE = registerBlockAndItem("galena_iron_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).sound(SoundType.DEEPSLATE)), 8);
    public static final RegistryObject<Block> ENERGIZED_GALENA_NEUTRAL = registerBlockAndItem("energized_galena_neutral", () -> new EnergizedGalenaBlock(ENERGIZED_GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> ENERGIZED_GALENA_SCARLET = registerBlockAndItem("energized_galena_scarlet", () -> new EnergizedGalenaBlock(ENERGIZED_GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> ENERGIZED_GALENA_AZURE = registerBlockAndItem("energized_galena_azure", () -> new EnergizedGalenaBlock(ENERGIZED_GALENA_PROPERTIES), 8);
    public static final RegistryObject<Block> GALENA_SPIRE = registerBlockAndItem("galena_spire", () -> new GalenaSpireBlock(), 8);
    public static final RegistryObject<Block> TESLA_BULB = registerBlockAndItem("tesla_bulb", () -> new TeslaBulbBlock(), 8);
    public static final RegistryObject<Block> METAL_SWARF = registerBlockAndItem("metal_swarf", () -> new FallingBlockWithColor(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.6F).sound(ACSoundTypes.METAL_SWARF), 0X404253), 16);
    public static final RegistryObject<Block> SCRAP_METAL = registerBlockAndItem("scrap_metal", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5F, 15.0F).sound(ACSoundTypes.SCRAP_METAL)), 16);
    public static final RegistryObject<Block> SCRAP_METAL_PLATE = registerBlockAndItem("scrap_metal_plate", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5F, 15.0F).sound(ACSoundTypes.SCRAP_METAL)), 16);
    public static final RegistryObject<Block> METAL_REBAR = registerBlockAndItem("metal_rebar", () -> new RebarBlock(), 16);
    public static final RegistryObject<Block> METAL_SCAFFOLDING = registerBlockAndItem("metal_scaffolding", () -> new MetalScaffoldingBlock(), 17);
    public static final RegistryObject<Block> MAGNETIC_ACTIVATOR = registerBlockAndItem("magnetic_activator", () -> new MagneticActivatorBlock(), 8);
    public static final RegistryObject<Block> SCARLET_NEODYMIUM_NODE = registerBlockAndItem("scarlet_neodymium_node", () -> new NeodymiumNodeBlock(false), 8);
    public static final RegistryObject<Block> AZURE_NEODYMIUM_NODE = registerBlockAndItem("azure_neodymium_node", () -> new NeodymiumNodeBlock(true), 8);
    public static final RegistryObject<Block> SCARLET_NEODYMIUM_PILLAR = registerBlockAndItem("scarlet_neodymium_pillar", () -> new NeodymiumPillarBlock(false), 8);
    public static final RegistryObject<Block> AZURE_NEODYMIUM_PILLAR = registerBlockAndItem("azure_neodymium_pillar", () -> new NeodymiumPillarBlock(true), 8);
    public static final RegistryObject<Block> BLOCK_OF_SCARLET_NEODYMIUM = registerBlockAndItem("block_of_scarlet_neodymium", () -> new NeodymiumOreBlock(false), 8);
    public static final RegistryObject<Block> BLOCK_OF_AZURE_NEODYMIUM = registerBlockAndItem("block_of_azure_neodymium", () -> new NeodymiumOreBlock(true), 8);
    public static final RegistryObject<Block> SCARLET_MAGNET = registerBlockAndItem("scarlet_magnet", () -> new MagnetBlock(false), 8);
    public static final RegistryObject<Block> AZURE_MAGNET = registerBlockAndItem("azure_magnet", () -> new MagnetBlock(true), 8);
    public static final RegistryObject<Block> HEART_OF_IRON = registerBlockAndItem("heart_of_iron", () -> new HeartOfIronBlock(), 8);
    public static final RegistryObject<Block> HOLOGRAM_PROJECTOR = registerBlockAndItem("hologram_projector", () -> new HologramProjectorBlock(), 8);
    public static final RegistryObject<Block> MAGNETIC_LIGHT = registerBlockAndItem("magnetic_light", () -> new MagneticLightBlock(), 8);
    public static final RegistryObject<Block> MAGNETIC_LEVITATION_RAIL = registerBlockAndItem("magnetic_levitation_rail", () -> new MagneticLevitationRailBlock(), 8);
    public static final RegistryObject<Block> QUARRY = registerBlockAndItem("quarry", () -> new QuarryBlock(), 8);
    public static final RegistryObject<Block> LIMESTONE = registerBlockAndItem("limestone", () -> new Block(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> LIMESTONE_STAIRS = registerBlockAndItem("limestone_stairs", () -> new StairBlock(LIMESTONE.get().defaultBlockState(), LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> LIMESTONE_SLAB = registerBlockAndItem("limestone_slab", () -> new SlabBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> LIMESTONE_WALL = registerBlockAndItem("limestone_wall", () -> new WallBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> LIMESTONE_PILLAR = registerBlockAndItem("limestone_pillar", () -> new RotatedPillarBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> LIMESTONE_CHISELED = registerBlockAndItem("limestone_chiseled", () -> new DirectionalFacingBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> SMOOTH_LIMESTONE = registerBlockAndItem("smooth_limestone", () -> new SmoothLimestoneBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> SMOOTH_LIMESTONE_STAIRS = registerBlockAndItem("smooth_limestone_stairs", () -> new StairBlock(SMOOTH_LIMESTONE.get().defaultBlockState(), LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> SMOOTH_LIMESTONE_SLAB = registerBlockAndItem("smooth_limestone_slab", () -> new SlabBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> SMOOTH_LIMESTONE_WALL = registerBlockAndItem("smooth_limestone_wall", () -> new WallBlock(LIMESTONE_PROPERTIES), 11);
    public static final RegistryObject<Block> CAVE_PAINTING_AMBERSOL = registerBlockAndItem("cave_painting_ambersol", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_DARK = registerBlockAndItem("cave_painting_dark", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_FOOTPRINT = registerBlockAndItem("cave_painting_footprint", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_FOOTPRINTS = registerBlockAndItem("cave_painting_footprints", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_TREE_STARS = registerBlockAndItem("cave_painting_tree_stars", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_PEWEN = registerBlockAndItem("cave_painting_pewen", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_TRILOCARIS = registerBlockAndItem("cave_painting_trilocaris", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_GROTTOCERATOPS = registerBlockAndItem("cave_painting_grottoceratops", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_GROTTOCERATOPS_FRIEND = registerBlockAndItem("cave_painting_grottoceratops_friend", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_DINO_NUGGETS = registerBlockAndItem("cave_painting_dino_nuggets", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_VALLUMRAPTOR_CHEST = registerBlockAndItem("cave_painting_vallumraptor_chest", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_VALLUMRAPTOR_FRIEND = registerBlockAndItem("cave_painting_vallumraptor_friend", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_RELICHEIRUS = registerBlockAndItem("cave_painting_relicheirus", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_RELICHEIRUS_SLASH = registerBlockAndItem("cave_painting_relicheirus_slash", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_ENDERMAN = registerBlockAndItem("cave_painting_enderman", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_PORTAL = registerBlockAndItem("cave_painting_portal", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_SUBTERRANODON = registerBlockAndItem("cave_painting_subterranodon", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_SUBTERRANODON_RIDE = registerBlockAndItem("cave_painting_subterranodon_ride", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_TREMORSAURUS = registerBlockAndItem("cave_painting_tremorsaurus", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_TREMORSAURUS_FRIEND = registerBlockAndItem("cave_painting_tremorsaurus_friend", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_1 = registerBlockAndItem("cave_painting_mystery_1", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_2 = registerBlockAndItem("cave_painting_mystery_2", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_3 = registerBlockAndItem("cave_painting_mystery_3", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_4 = registerBlockAndItem("cave_painting_mystery_4", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_5 = registerBlockAndItem("cave_painting_mystery_5", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_6 = registerBlockAndItem("cave_painting_mystery_6", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_7 = registerBlockAndItem("cave_painting_mystery_7", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_8 = registerBlockAndItem("cave_painting_mystery_8", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> CAVE_PAINTING_MYSTERY_9 = registerBlockAndItem("cave_painting_mystery_9", () -> new CavePaintingBlock(), 12);
    public static final RegistryObject<Block> AMBER = registerBlockAndItem("amber", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).noOcclusion().requiresCorrectToolForDrops().strength(0.3F, 2.0F).sound(ACSoundTypes.AMBER)), 11);
    public static final RegistryObject<Block> AMBERSOL = registerBlockAndItem("ambersol", () -> new AmbersolBlock(), 11);
    public static final RegistryObject<Block> AMBERSOL_LIGHT = DEF_REG.register("ambersol_light", () -> new AmbersolLightBlock(BlockBehaviour.Properties.of(Material.AIR).noOcclusion().strength(-1.0F, 3600000.8F).noLootTable().noOcclusion().lightLevel(((state -> 15)))));
    public static final RegistryObject<Block> AMBER_MONOLITH = registerBlockAndItem("amber_monolith", () -> new AmberMonolithBlock(), 11);
    public static final RegistryObject<Block> SUBTERRANODON_EGG = registerBlockAndItem("subterranodon_egg", () -> new MultipleDinosaurEggsBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.METAL).randomTicks(), ACEntityRegistry.SUBTERRANODON, 4), 11);
    public static final RegistryObject<Block> VALLUMRAPTOR_EGG = registerBlockAndItem("vallumraptor_egg", () -> new MultipleDinosaurEggsBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.METAL).randomTicks(), ACEntityRegistry.VALLUMRAPTOR, 4), 11);
    public static final RegistryObject<Block> GROTTOCERATOPS_EGG = registerBlockAndItem("grottoceratops_egg", () -> new DinosaurEggBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.METAL).randomTicks(), ACEntityRegistry.GROTTOCERATOPS, 8, 10), 11);
    public static final RegistryObject<Block> TREMORSAURUS_EGG = registerBlockAndItem("tremorsaurus_egg", () -> new DinosaurEggBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.METAL).randomTicks(), ACEntityRegistry.TREMORSAURUS, 10, 16), 11);
    public static final RegistryObject<Block> RELICHEIRUS_EGG = registerBlockAndItem("relicheirus_egg", () -> new DinosaurEggBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.METAL).randomTicks(), ACEntityRegistry.RELICHEIRUS, 14, 16), 11);
    public static final RegistryObject<Block> DINOSAUR_CHOP = registerBlockAndItem("dinosaur_chop", () -> new DinosaurChopBlock(3, 0.2F), 11);
    public static final RegistryObject<Block> COOKED_DINOSAUR_CHOP = registerBlockAndItem("cooked_dinosaur_chop", () -> new DinosaurChopBlock(7, 0.35F), 11);
    public static final RegistryObject<Block> CARMINE_FROGLIGHT = registerBlockAndItem("carmine_froglight", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.FROGLIGHT, MaterialColor.COLOR_YELLOW).strength(0.3F).lightLevel((state) -> 15).sound(SoundType.FROGLIGHT)), 11);
    public static final RegistryObject<Block> PEWEN_LOG = registerBlockAndItem("pewen_log", () -> new StrippableLogBlock(PEWEN_LOG_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_WOOD = registerBlockAndItem("pewen_wood", () -> new StrippableLogBlock(PEWEN_LOG_PROPERTIES), 11);
    public static final RegistryObject<Block> STRIPPED_PEWEN_LOG = registerBlockAndItem("stripped_pewen_log", () -> new RotatedPillarBlock(PEWEN_LOG_PROPERTIES), 11);
    public static final RegistryObject<Block> STRIPPED_PEWEN_WOOD = registerBlockAndItem("stripped_pewen_wood", () -> new RotatedPillarBlock(PEWEN_LOG_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_PLANKS = registerBlockAndItem("pewen_planks", () -> new Block(PEWEN_PLANKS_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_PLANKS_STAIRS = registerBlockAndItem("pewen_stairs", () -> new StairBlock(PEWEN_PLANKS.get().defaultBlockState(), PEWEN_PLANKS_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_PLANKS_SLAB = registerBlockAndItem("pewen_slab", () -> new SlabBlock(PEWEN_PLANKS_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_PLANKS_FENCE = registerBlockAndItem("pewen_fence", () -> new FenceBlock(PEWEN_PLANKS_PROPERTIES), 11);
    public static final RegistryObject<Block> PEWEN_SIGN = DEF_REG.register("pewen_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), PEWEN_WOOD_TYPE));
    public static final RegistryObject<Block> PEWEN_WALL_SIGN = DEF_REG.register("pewen_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), PEWEN_WOOD_TYPE));
    //public static final RegistryObject<Block> PEWEN_HANGING_SIGN = DEF_REG.register("pewen_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F), PEWEN_WOOD_TYPE));
    //public static final RegistryObject<Block> PEWEN_WALL_HANGING_SIGN = DEF_REG.register("pewen_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(PEWEN_HANGING_SIGN.get()), PEWEN_WOOD_TYPE));
    public static final RegistryObject<Block> PEWEN_PRESSURE_PLATE = registerBlockAndItem("pewen_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PEWEN_PLANKS.get()).noCollission().strength(0.5F).sound(SoundType.WOOD)), 11);
    public static final RegistryObject<Block> PEWEN_TRAPDOOR = registerBlockAndItem("pewen_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), 11);
    public static final RegistryObject<Block> PEWEN_BUTTON = registerBlockAndItem("pewen_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), 11);
    public static final RegistryObject<Block> PEWEN_FENCE_GATE = registerBlockAndItem("pewen_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(PEWEN_PLANKS.get()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), 11);
    public static final RegistryObject<Block> PEWEN_DOOR = DEF_REG.register("pewen_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(PEWEN_PLANKS.get()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> PEWEN_BRANCH = registerBlockAndItem("pewen_branch", () -> new PewenBranchBlock(), 11);
    public static final RegistryObject<Block> PEWEN_PINES = registerBlockAndItem("pewen_pines", () -> new PewenPinesBlock(), 11);
    public static final RegistryObject<Block> POTTED_PEWEN_PINES = DEF_REG.register("potted_pewen_pines", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PEWEN_PINES, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> PEWEN_SAPLING = registerBlockAndItem("pewen_sapling", () -> new SaplingBlock(new PewenGrower(), BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), 11);
    public static final RegistryObject<Block> POTTED_PEWEN_SAPLING = DEF_REG.register("potted_pewen_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PEWEN_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> FIDDLEHEAD = registerBlockAndItem("fiddlehead", () -> new FiddleheadBlock());
    public static final RegistryObject<Block> POTTED_FIDDLEHEAD = DEF_REG.register("potted_fiddlehead", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FIDDLEHEAD, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> CURLY_FERN = registerBlockAndItem("curly_fern", () -> new DoublePlantWithRotationBlock(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), 11);
    public static final RegistryObject<Block> POTTED_CURLY_FERN = DEF_REG.register("potted_curly_fern", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CURLY_FERN, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> FLYTRAP = registerBlockAndItem("flytrap", () -> new FlytrapBlock(), 11);
    public static final RegistryObject<Block> POTTED_FLYTRAP = DEF_REG.register("potted_flytrap", () -> new PottedFlytrapBlock());
    public static final RegistryObject<Block> CYCAD = registerBlockAndItem("cycad", () -> new CycadBlock(), 11);
    public static final RegistryObject<Block> POTTED_CYCAD = DEF_REG.register("potted_cycad", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CYCAD, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> ARCHAIC_VINE = registerBlockAndItem("archaic_vine", () -> new ArchaicVineBlock(), 11);
    public static final RegistryObject<Block> ARCHAIC_VINE_PLANT = DEF_REG.register("archaic_vine_plant", () -> new ArchaicVinePlantBlock());
    public static final RegistryObject<Block> ANCIENT_LEAVES = registerBlockAndItem("ancient_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.GRASS).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), 11);
    public static final RegistryObject<Block> ANCIENT_SAPLING = registerBlockAndItem("ancient_sapling", () -> new SaplingBlock(new AncientTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), 11);
    public static final RegistryObject<Block> POTTED_ANCIENT_SAPLING = DEF_REG.register("potted_ancient_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ANCIENT_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> TREE_STAR = registerBlockAndItem("tree_star", () -> new TreeStarBlock(), 11);
    public static final RegistryObject<Block> FERN_THATCH = registerBlockAndItem("fern_thatch", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_GREEN).strength(0.5F).sound(SoundType.GRASS).noOcclusion()), 11);
    public static final RegistryObject<Block> PRIMAL_MAGMA = registerBlockAndItem("primal_magma", () -> new PrimalMagmaBlock());
    public static final RegistryObject<Block> FLOOD_BASALT = registerBlockAndItem("flood_basalt", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(3.0F, 100.0F).sound(ACSoundTypes.FLOOD_BASALT)));
    public static final RegistryObject<Block> VOLCANIC_CORE = registerBlockAndItem("volcanic_core", () -> new VolcanicCoreBlock(), 7);
    public static final RegistryObject<Block> RADROCK = registerBlockAndItem("radrock", () -> new Block(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_STAIRS = registerBlockAndItem("radrock_stairs", () -> new StairBlock(RADROCK.get().defaultBlockState(), RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_SLAB = registerBlockAndItem("radrock_slab", () -> new SlabBlock(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_WALL = registerBlockAndItem("radrock_wall", () -> new WallBlock(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_BRICKS = registerBlockAndItem("radrock_bricks", () -> new Block(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_BRICK_STAIRS = registerBlockAndItem("radrock_brick_stairs", () -> new StairBlock(RADROCK_BRICKS.get().defaultBlockState(), RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_BRICK_SLAB = registerBlockAndItem("radrock_brick_slab", () -> new SlabBlock(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_BRICK_WALL = registerBlockAndItem("radrock_brick_wall", () -> new WallBlock(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_CHISELED = registerBlockAndItem("radrock_chiseled", () -> new Block(RADROCK_PROPERTIES), 13);
    public static final RegistryObject<Block> RADROCK_URANIUM_ORE = registerBlockAndItem("radrock_uranium_ore", () -> new RadrockUraniumOreBlock(), 4);
    public static final RegistryObject<Block> ACIDIC_RADROCK = registerBlockAndItem("acidic_radrock", () -> new AcidicRadrockBlock(), 13);
    public static final RegistryObject<Block> GEOTHERMAL_VENT = registerBlockAndItem("geothermal_vent", () -> new GeothermalVentBlock(), 22);
    public static final RegistryObject<Block> GEOTHERMAL_VENT_MEDIUM = registerBlockAndItem("geothermal_vent_medium", () -> new ThinGeothermalVentBlock(12), 22);
    public static final RegistryObject<Block> GEOTHERMAL_VENT_THIN = registerBlockAndItem("geothermal_vent_thin", () -> new ThinGeothermalVentBlock(8), 22);
    public static final RegistryObject<LiquidBlock> ACID = DEF_REG.register("acid", () -> new AcidBlock(ACFluidRegistry.ACID_FLUID_SOURCE, BlockBehaviour.Properties.of(Material.WATER, MaterialColor.COLOR_LIGHT_GREEN).noCollission().strength(100.0F).lightLevel(state -> 7).emissiveRendering((state, world, pos) -> false).noLootTable()));
    public static final RegistryObject<Block> UNDERWEED = registerBlockAndItem("underweed", () -> new CavePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.GRASS).noOcclusion().noCollission()), 13);
    public static final RegistryObject<Block> POTTED_UNDERWEED = DEF_REG.register("potted_underweed", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UNDERWEED, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> METAL_BARREL = registerBlockAndItem("metal_barrel", () -> new MetalBarrelBlock(), 18);
    public static final RegistryObject<Block> WASTE_DRUM = registerBlockAndItem("waste_drum", () -> new WasteDrumBlock(), 5);
    public static final RegistryObject<Block> RUSTY_SCRAP_METAL = registerBlockAndItem("rusty_scrap_metal", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5F, 15.0F).sound(ACSoundTypes.SCRAP_METAL)), 13);
    public static final RegistryObject<Block> RUSTY_SCRAP_METAL_PLATE = registerBlockAndItem("rusty_scrap_metal_plate", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(5F, 15.0F).sound(ACSoundTypes.SCRAP_METAL)), 13);
    public static final RegistryObject<Block> RUSTY_BARREL = registerBlockAndItem("rusty_barrel", () -> new MetalBarrelBlock(), 13);
    public static final RegistryObject<Block> RUSTY_REBAR = registerBlockAndItem("rusty_rebar", () -> new RebarBlock(), 13);
    public static final RegistryObject<Block> RUSTY_SCAFFOLDING = registerBlockAndItem("rusty_scaffolding", () -> new MetalScaffoldingBlock(), 14);
    public static final RegistryObject<Block> URANIUM_ROD = registerBlockAndItem("uranium_rod", () -> new UraniumRodBlock(), 13);
    public static final RegistryObject<Block> BLOCK_OF_URANIUM = registerBlockAndItem("block_of_uranium", () -> new UraniumFullBlock(), 4);
    public static final RegistryObject<Block> NUCLEAR_BOMB = registerBlockAndItem("nuclear_bomb", () -> new NuclearBombBlock(), 13);
    public static final RegistryObject<Block> UNREFINED_WASTE = registerBlockAndItem("unrefined_waste", () -> new UnrefinedWasteBlock(), 4);
    public static final RegistryObject<Block> NUCLEAR_FURNACE_COMPONENT = registerBlockAndItem("nuclear_furnace_component", () -> new NuclearFurnaceComponentBlock(), 13);
    public static final RegistryObject<Block> NUCLEAR_FURNACE = DEF_REG.register("nuclear_furnace", () -> new NuclearFurnaceBlock());
    public static final RegistryObject<Block> SULFUR = registerBlockAndItem("sulfur", () -> new SulfurBlock(), 13);
    public static final RegistryObject<Block> SULFUR_BUD_SMALL = registerBlockAndItem("sulfur_bud_small", () -> new SulfurBudBlock(6, 4), 13);
    public static final RegistryObject<Block> SULFUR_BUD_MEDIUM = registerBlockAndItem("sulfur_bud_medium", () -> new SulfurBudBlock(6, 8), 13);
    public static final RegistryObject<Block> SULFUR_BUD_LARGE = registerBlockAndItem("sulfur_bud_large", () -> new SulfurBudBlock(6, 12), 13);
    public static final RegistryObject<Block> SULFUR_CLUSTER = registerBlockAndItem("sulfur_cluster", () -> new SulfurBudBlock(6, 14), 13);
    public static final RegistryObject<Block> CINDER_BLOCK = registerBlockAndItem("cinder_block", () -> new Block(CINDER_BLOCK_PROPERTIES), 13);
    public static final RegistryObject<Block> CINDER_BLOCK_STAIRS = registerBlockAndItem("cinder_block_stairs", () -> new StairBlock(CINDER_BLOCK.get().defaultBlockState(), CINDER_BLOCK_PROPERTIES), 13);
    public static final RegistryObject<Block> CINDER_BLOCK_SLAB = registerBlockAndItem("cinder_block_slab", () -> new SlabBlock(CINDER_BLOCK_PROPERTIES), 13);
    public static final RegistryObject<Block> CINDER_BLOCK_WALL = registerBlockAndItem("cinder_block_wall", () -> new WallBlock(CINDER_BLOCK_PROPERTIES), 13);
    public static final RegistryObject<Block> HAZMAT_BLOCK = registerBlockAndItem("hazmat_block", () -> new HazmatBlock(), 13);
    public static final RegistryObject<Block> SIREN_LIGHT = registerBlockAndItem("siren_light", () -> new SirenLightBlock(), 15);
    public static final RegistryObject<Block> NUCLEAR_SIREN = registerBlockAndItem("nuclear_siren", () -> new NuclearSirenBlock(), 13);
    public static final RegistryObject<Block> WHITE_RADON_LAMP = registerBlockAndItem("radon_lamp_white", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> ORANGE_RADON_LAMP = registerBlockAndItem("radon_lamp_orange", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> MAGENTA_RADON_LAMP = registerBlockAndItem("radon_lamp_magenta", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> LIGHT_BLUE_RADON_LAMP = registerBlockAndItem("radon_lamp_light_blue", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> YELLOW_RADON_LAMP = registerBlockAndItem("radon_lamp_yellow", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> LIME_RADON_LAMP = registerBlockAndItem("radon_lamp_lime", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> PINK_RADON_LAMP = registerBlockAndItem("radon_lamp_pink", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> GRAY_RADON_LAMP = registerBlockAndItem("radon_lamp_gray", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> LIGHT_GRAY_RADON_LAMP = registerBlockAndItem("radon_lamp_light_gray", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> CYAN_RADON_LAMP = registerBlockAndItem("radon_lamp_cyan", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> PURPLE_RADON_LAMP = registerBlockAndItem("radon_lamp_purple", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> BLUE_RADON_LAMP = registerBlockAndItem("radon_lamp_blue", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> BROWN_RADON_LAMP = registerBlockAndItem("radon_lamp_brown", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> GREEN_RADON_LAMP = registerBlockAndItem("radon_lamp_green", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> RED_RADON_LAMP = registerBlockAndItem("radon_lamp_red", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> BLACK_RADON_LAMP = registerBlockAndItem("radon_lamp_black", () -> new Block(RADON_LAMP_PROPERTIES), 13);
    public static final RegistryObject<Block> ABYSSMARINE = registerBlockAndItem("abyssmarine", () -> new Block(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_STAIRS = registerBlockAndItem("abyssmarine_stairs", () -> new StairBlock(ABYSSMARINE.get().defaultBlockState(), ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_SLAB = registerBlockAndItem("abyssmarine_slab", () -> new SlabBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_WALL = registerBlockAndItem("abyssmarine_wall", () -> new WallBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_BRICKS = registerBlockAndItem("abyssmarine_bricks", () -> new GlowingAbyssmarineBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_BRICK_STAIRS = registerBlockAndItem("abyssmarine_brick_stairs", () -> new AbyssmarineStairBlock(ABYSSMARINE_BRICKS.get().defaultBlockState(), ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_BRICK_SLAB = registerBlockAndItem("abyssmarine_brick_slab", () -> new AbyssmarineSlabBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_BRICK_WALL = registerBlockAndItem("abyssmarine_brick_wall", () -> new AbyssmarineWallBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_PILLAR = registerBlockAndItem("abyssmarine_pillar", () -> new AbyssmarinePillarBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSMARINE_TILES = registerBlockAndItem("abyssmarine_tiles", () -> new GlowingAbyssmarineBlock(ABYSSMARINE_PROPERTIES), 19);
    public static final RegistryObject<Block> ABYSSAL_ALTAR = registerBlockAndItem("abyssal_altar", () -> new AbyssalAltarBlock(), 19);
    public static final RegistryObject<Block> MUCK = registerBlockAndItem("muck", () -> new MuckBlock(BlockBehaviour.Properties.of(Material.STONE, DyeColor.LIGHT_GRAY).strength(0.5F).sound(SoundType.FROGSPAWN)), 19);
    public static final RegistryObject<Block> TUBE_WORM = registerBlockAndItem("tube_worm", () -> new TubeWormBlock(), 19);
    public static final RegistryObject<Block> HOLLOW_BONE = registerBlockAndItem("hollow_bone", () -> new HollowBoneBlock(), 19);
    public static final RegistryObject<Block> THIN_BONE = registerBlockAndItem("thin_bone", () -> new ThinBoneBlock(), 19);
    public static final RegistryObject<Block> BONE_NODULE = registerBlockAndItem("bone_nodule", () -> new BoneNoduleBlock(), 19);
    public static final RegistryObject<Block> BONE_RIBS = registerBlockAndItem("bone_ribs", () -> new BoneRibsBlock(), 19);
    public static final RegistryObject<Block> BALEEN_BONE = registerBlockAndItem("baleen_bone", () -> new BaleenBoneBlock(), 19);
    public static final RegistryObject<Block> SMOOTH_BONE = registerBlockAndItem("smooth_bone", () -> new Block(SMOOTH_BONE_PROPERTIES), 19);
    public static final RegistryObject<Block> SMOOTH_BONE_STAIRS = registerBlockAndItem("smooth_bone_stairs", () -> new StairBlock(SMOOTH_BONE.get().defaultBlockState(), SMOOTH_BONE_PROPERTIES), 19);
    public static final RegistryObject<Block> SMOOTH_BONE_SLAB = registerBlockAndItem("smooth_bone_slab", () -> new SlabBlock(SMOOTH_BONE_PROPERTIES), 19);
    public static final RegistryObject<Block> SMOOTH_BONE_WALL = registerBlockAndItem("smooth_bone_wall", () -> new WallBlock(SMOOTH_BONE_PROPERTIES), 19);
    public static final RegistryObject<Block> BONE_WORMS = registerBlockAndItem("bone_worms", () -> new BoneWormsBlock(), 19);
    public static final RegistryObject<Block> PING_PONG_SPONGE = registerBlockAndItem("ping_pong_sponge", () -> new PingPongSpongeBlock(), 19);
    public static final RegistryObject<Block> DUSK_ANEMONE = registerBlockAndItem("dusk_anemone", () -> new OceanFloraBlock(), 19);
    public static final RegistryObject<Block> TWILIGHT_ANEMONE = registerBlockAndItem("twilight_anemone", () -> new OceanFloraBlock(), 19);
    public static final RegistryObject<Block> MIDNIGHT_ANEMONE = registerBlockAndItem("midnight_anemone", () -> new OceanFloraBlock(), 19);
    public static final RegistryObject<Block> MUSSEL = registerBlockAndItem("mussel", () -> new MusselBlock(), 19);
    public static final RegistryObject<Block> BLOCK_OF_PEARL = registerBlockAndItem("block_of_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_WHITE).strength(3.5F).sound(SoundType.AMETHYST)), 19);
    public static final RegistryObject<Block> BIOLUMINESCENT_TORCH = DEF_REG.register("bioluminescent_torch", () -> new BioluminescentTorch());
    public static final RegistryObject<Block> BIOLUMINESCENT_WALL_TORCH = DEF_REG.register("bioluminescent_wall_torch", () -> new BioluminescentWallTorch());
    public static final RegistryObject<Block> DRAIN = registerBlockAndItem("drain", () -> new DrainBlock(), 19);
    public static final RegistryObject<Block> DEPTH_GLASS = registerBlockAndItem("depth_glass", () -> new DepthGlassBlock(), 19);
    public static final RegistryObject<Block> COPPER_VALVE = registerBlockAndItem("copper_valve", () -> new CopperValveBlock(), 20);
    public static final RegistryObject<Block> ENIGMATIC_ENGINE = registerBlockAndItem("enigmatic_engine", () -> new EnigmaticEngineBlock(), 21);
    public static final RegistryObject<Block> GUANO_BLOCK = registerBlockAndItem("guano_block", () -> new GuanoBlock(), 23);
    public static final RegistryObject<Block> GUANO_LAYER = registerBlockAndItem("guano_layer", () -> new GuanoLayerBlock(), 23);
    public static final RegistryObject<Block> GUANOSTONE = registerBlockAndItem("guanostone", () -> new Block(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_STAIRS = registerBlockAndItem("guanostone_stairs", () -> new StairBlock(GUANOSTONE.get().defaultBlockState(), GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_SLAB = registerBlockAndItem("guanostone_slab", () -> new SlabBlock(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_WALL = registerBlockAndItem("guanostone_wall", () -> new WallBlock(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_BRICKS = registerBlockAndItem("guanostone_bricks", () -> new Block(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_BRICK_STAIRS = registerBlockAndItem("guanostone_brick_stairs", () -> new StairBlock(GUANOSTONE_BRICKS.get().defaultBlockState(), GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_BRICK_SLAB = registerBlockAndItem("guanostone_brick_slab", () -> new SlabBlock(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_BRICK_WALL = registerBlockAndItem("guanostone_brick_wall", () -> new WallBlock(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_CHISELED = registerBlockAndItem("guanostone_chiseled", () -> new Block(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_TILES = registerBlockAndItem("guanostone_tiles", () -> new Block(GUANOSTONE_PROPERTIES), 23);
    public static final RegistryObject<Block> GUANOSTONE_REDSTONE_ORE = registerBlockAndItem("guanostone_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).sound(SoundType.BASALT)), 23);
    public static final RegistryObject<Block> COPROLITH = registerBlockAndItem("coprolith", () -> new Block(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> COPROLITH_STAIRS = registerBlockAndItem("coprolith_stairs", () -> new StairBlock(COPROLITH.get().defaultBlockState(), COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> COPROLITH_SLAB = registerBlockAndItem("coprolith_slab", () -> new SlabBlock(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> COPROLITH_WALL = registerBlockAndItem("coprolith_wall", () -> new WallBlock(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> SMOOTH_COPROLITH = registerBlockAndItem("smooth_coprolith", () -> new Block(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> SMOOTH_COPROLITH_STAIRS = registerBlockAndItem("smooth_coprolith_stairs", () -> new StairBlock(SMOOTH_COPROLITH.get().defaultBlockState(), COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> SMOOTH_COPROLITH_SLAB = registerBlockAndItem("smooth_coprolith_slab", () -> new SlabBlock(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> SMOOTH_COPROLITH_WALL = registerBlockAndItem("smooth_coprolith_wall", () -> new WallBlock(COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> COPROLITH_COAL_ORE = registerBlockAndItem("coprolith_coal_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).sound(SoundType.CALCITE)), 23);
    public static final RegistryObject<Block> POROUS_COPROLITH = registerBlockAndItem("porous_coprolith", () -> new Block(POROUS_COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> PEERING_COPROLITH = registerBlockAndItem("peering_coprolith", () -> new Block(PEERING_COPROLITH_PROPERTIES), 23);
    public static final RegistryObject<Block> FORSAKEN_IDOL = registerBlockAndItem("forsaken_idol", () -> new ForsakenIdolBlock(), 23);
    public static final RegistryObject<Block> THORNWOOD_LOG = registerBlockAndItem("thornwood_log", () -> new StrippableLogBlock(THORNWOOD_LOG_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_BRANCH = registerBlockAndItem("thornwood_branch", () -> new ThornwoodBranchBlock(), 23);
    public static final RegistryObject<Block> POTTED_THORNWOOD_BRANCH = DEF_REG.register("potted_thornwood_branch", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, THORNWOOD_BRANCH, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> THORNWOOD_WOOD = registerBlockAndItem("thornwood_wood", () -> new StrippableLogBlock(THORNWOOD_LOG_PROPERTIES), 23);
    public static final RegistryObject<Block> STRIPPED_THORNWOOD_LOG = registerBlockAndItem("stripped_thornwood_log", () -> new RotatedPillarBlock(THORNWOOD_LOG_PROPERTIES), 23);
    public static final RegistryObject<Block> STRIPPED_THORNWOOD_WOOD = registerBlockAndItem("stripped_thornwood_wood", () -> new RotatedPillarBlock(THORNWOOD_LOG_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_PLANKS = registerBlockAndItem("thornwood_planks", () -> new Block(THORNWOOD_PLANKS_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_PLANKS_STAIRS = registerBlockAndItem("thornwood_stairs", () -> new StairBlock(THORNWOOD_PLANKS.get().defaultBlockState(), THORNWOOD_PLANKS_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_PLANKS_SLAB = registerBlockAndItem("thornwood_slab", () -> new SlabBlock(THORNWOOD_PLANKS_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_PLANKS_FENCE = registerBlockAndItem("thornwood_fence", () -> new FenceBlock(THORNWOOD_PLANKS_PROPERTIES), 23);
    public static final RegistryObject<Block> THORNWOOD_SIGN = DEF_REG.register("thornwood_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).noCollission().strength(1.0F).sound(SoundType.WOOD), THORNWOOD_WOOD_TYPE));
    public static final RegistryObject<Block> THORNWOOD_WALL_SIGN = DEF_REG.register("thornwood_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).noCollission().strength(1.0F).sound(SoundType.WOOD), THORNWOOD_WOOD_TYPE));
    //public static final RegistryObject<Block> THORNWOOD_HANGING_SIGN = DEF_REG.register("thornwood_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F), THORNWOOD_WOOD_TYPE));
    //public static final RegistryObject<Block> THORNWOOD_WALL_HANGING_SIGN = DEF_REG.register("thornwood_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(THORNWOOD_HANGING_SIGN.get()), THORNWOOD_WOOD_TYPE));
    public static final RegistryObject<Block> THORNWOOD_PRESSURE_PLATE = registerBlockAndItem("thornwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(THORNWOOD_PLANKS.get()).noCollission().strength(0.5F).sound(SoundType.WOOD)), 23);
    public static final RegistryObject<Block> THORNWOOD_TRAPDOOR = registerBlockAndItem("thornwood_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), 23);
    public static final RegistryObject<Block> THORNWOOD_BUTTON = registerBlockAndItem("thornwood_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(THORNWOOD_PLANKS.get()).noCollission().strength(0.5F).sound(SoundType.WOOD)), 23);
    public static final RegistryObject<Block> THORNWOOD_FENCE_GATE = registerBlockAndItem("thornwood_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(THORNWOOD_PLANKS.get()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), 23);
    public static final RegistryObject<Block> THORNWOOD_DOOR = DEF_REG.register("thornwood_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(THORNWOOD_PLANKS.get()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> THORNWOOD_SAPLING = registerBlockAndItem("thornwood_sapling", () -> new CaveSaplingBlock(new ThornwoodGrower(), BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), 23);
    public static final RegistryObject<Block> POTTED_THORNWOOD_SAPLING = DEF_REG.register("potted_thornwood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, THORNWOOD_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> MOTH_BALL = registerBlockAndItem("moth_ball", () -> new MothBallBlock(), 23);
    public static final RegistryObject<Block> BEHOLDER = registerBlockAndItem("beholder", () -> new BeholderBlock(), 24);

    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block) {
        return registerBlockAndItem(name, block, 0);
    }

    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, int itemType) {
        RegistryObject<Block> blockObj = DEF_REG.register(name, block);
        ACItemRegistry.DEF_REG.register(name, getBlockSupplier(itemType, blockObj));
        return blockObj;
    }

    private static Supplier<? extends BlockItemWithSupplier> getBlockSupplier(int itemType, RegistryObject<Block> blockObj) {
        switch (itemType) {
            default:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties());
            case 1:
                return () -> new BlockItemWithSupplierLore(blockObj, new Item.Properties());
            case 2:
                return () -> new BlockItemWithScaffolding(blockObj, new Item.Properties());
            case 3:
                return () -> new BlockItemWithISTER(blockObj, new Item.Properties());
            case 4:
                return () -> new RadioactiveBlockItem(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES), 0.001F);
            case 5:
                return () -> new RadioactiveOnDestroyedBlockItem(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES), 0.01F);
            case 6:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().rarity(Rarity.UNCOMMON));
            case 7:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().fireResistant());
            case 8:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES));
            case 9:
                return () -> new BlockItemWithScaffolding(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES));
            case 10:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties()
                		.tab(ACCreativeTabRegistry.MAGNETIC_CAVES)
                		.tab(ACCreativeTabRegistry.PRIMORDIAL_CAVES)
                		.tab(ACCreativeTabRegistry.TOXIC_CAVES)
                		.tab(ACCreativeTabRegistry.ABYSSAL_CHASM)
                		.tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
            case 11:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.PRIMORDIAL_CAVES));
            case 12:
                return () -> new BlockItemWithSupplierLore(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.PRIMORDIAL_CAVES));
            case 13:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 14:
                return () -> new BlockItemWithScaffolding(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 15:
                return () -> new BlockItemWithISTER(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 16:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES).tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 17:
                return () -> new BlockItemWithScaffolding(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES).tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 18:
                return () -> new BlockItemWithSupplierLore(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.MAGNETIC_CAVES).tab(ACCreativeTabRegistry.TOXIC_CAVES));
            case 19:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.ABYSSAL_CHASM));
            case 20:
                return () -> new BlockItemWithISTER(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.ABYSSAL_CHASM));
            case 21:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().rarity(Rarity.UNCOMMON).tab(ACCreativeTabRegistry.ABYSSAL_CHASM));
            case 22:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.TOXIC_CAVES).tab(ACCreativeTabRegistry.ABYSSAL_CHASM));
            case 23:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
            case 24:
                return () -> new BlockItemWithISTER(blockObj, new Item.Properties().tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
        }
    }

    public static void setup() {
        FlowerPotBlock flowerPotBlock = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPotBlock.addPlant(FLYTRAP.getId(), POTTED_FLYTRAP);
        flowerPotBlock.addPlant(CURLY_FERN.getId(), POTTED_CURLY_FERN);
        flowerPotBlock.addPlant(CYCAD.getId(), POTTED_CYCAD);
        flowerPotBlock.addPlant(PEWEN_SAPLING.getId(), POTTED_PEWEN_SAPLING);
        flowerPotBlock.addPlant(PEWEN_PINES.getId(), POTTED_PEWEN_PINES);
        flowerPotBlock.addPlant(FIDDLEHEAD.getId(), POTTED_FIDDLEHEAD);
        flowerPotBlock.addPlant(ANCIENT_SAPLING.getId(), POTTED_ANCIENT_SAPLING);
        flowerPotBlock.addPlant(UNDERWEED.getId(), POTTED_UNDERWEED);
        flowerPotBlock.addPlant(THORNWOOD_BRANCH.getId(), POTTED_THORNWOOD_BRANCH);
        flowerPotBlock.addPlant(THORNWOOD_SAPLING.getId(), POTTED_THORNWOOD_SAPLING);
    }
}

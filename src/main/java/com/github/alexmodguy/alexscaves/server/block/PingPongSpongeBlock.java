package com.github.alexmodguy.alexscaves.server.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PingPongSpongeBlock extends BushBlock implements LiquidBlockContainer {
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 16, 10);
    public static final VoxelShape SHAPE_TOP = Block.box(3, 0, 3, 13, 16, 13);

    public PingPongSpongeBlock() {
        super(Properties.of(Material.SPONGE, DyeColor.WHITE).dynamicShape().instabreak().lightLevel(blockstate -> 5).sound(SoundType.WET_GRASS).offsetType(OffsetType.XZ));
        this.registerDefaultState(this.defaultBlockState().setValue(TOP, Boolean.valueOf(true)));
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos blockPos) {
        return true;
    }

    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos blockPos) {
        return 1.0F;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        VoxelShape shape = state.getValue(TOP) ? SHAPE_TOP : SHAPE;
        return shape.move(vec3.x, vec3.y, vec3.z);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public long getSeed(BlockState blockState, BlockPos pos) {
        return Mth.getSeed(pos.getX(), 0, pos.getZ());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK) || state.getBlock() == this;
    }

    @Override
    public float getMaxHorizontalOffset() {
        return 0.4F;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        BlockState prev = super.updateShape(state, direction, state1, levelAccessor, blockPos, blockPos1);
        if (!prev.isAir()) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        if (prev.getBlock() == this) {
            if (levelAccessor.getBlockState(blockPos.above()).getBlock() == this) {
                prev = prev.setValue(TOP, false);
            } else {
                prev = prev.setValue(TOP, true);
            }
        }
        return prev;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState above = levelaccessor.getBlockState(blockpos.above());
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? this.defaultBlockState().setValue(TOP, above.getBlock() != this) : null;
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(TOP);
    }

    public FluidState getFluidState(BlockState p_154537_) {
        return Fluids.WATER.getSource(false);
    }


    public boolean canPlaceLiquid(BlockGetter p_154505_, BlockPos p_154506_, BlockState p_154507_, Fluid p_154508_) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor p_154520_, BlockPos p_154521_, BlockState p_154522_, FluidState p_154523_) {
        return false;
    }

    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
        return false;
    }
}

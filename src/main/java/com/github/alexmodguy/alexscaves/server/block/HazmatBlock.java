package com.github.alexmodguy.alexscaves.server.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class HazmatBlock extends RotatedPillarBlock {

    public HazmatBlock() {
        super(Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(3.5F, 12.0F).sound(ACSoundTypes.HAZMAT_BLOCK));
    }

    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        return BlockPathTypes.UNPASSABLE_RAIL;
    }

    @Override
    public BlockPathTypes getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, BlockPathTypes originalType) {
        return BlockPathTypes.UNPASSABLE_RAIL;
    }
}

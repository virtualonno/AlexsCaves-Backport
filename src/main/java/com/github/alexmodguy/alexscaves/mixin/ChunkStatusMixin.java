package com.github.alexmodguy.alexscaves.mixin;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.alexmodguy.alexscaves.server.generation.IMultiNoiseBiomeSourceAccessor;
import com.mojang.datafixers.util.Either;

import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

@Mixin({ ChunkStatus.class })
public class ChunkStatusMixin {
	@Inject(at = { @At("HEAD") }, remap = true, cancellable = true, method = {"Lnet/minecraft/world/level/chunk/ChunkStatus;generate(Ljava/util/concurrent/Executor;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager;Lnet/minecraft/server/level/ThreadedLevelLightEngine;Ljava/util/function/Function;Ljava/util/List;Z)Ljava/util/concurrent/CompletableFuture;"})
	private void ac_fillFromNoise(Executor p_223280_, ServerLevel serverLevel, ChunkGenerator chunkGenerator, StructureTemplateManager p_223283_, ThreadedLevelLightEngine p_223284_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223285_, List<ChunkAccess> p_223286_, boolean p_223287_, CallbackInfoReturnable<CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> cir) {
		BiomeSource biomeSource = chunkGenerator.getBiomeSource();
		if (biomeSource instanceof IMultiNoiseBiomeSourceAccessor) {
			IMultiNoiseBiomeSourceAccessor multiNoiseBiomeSourceAccessor = (IMultiNoiseBiomeSourceAccessor) biomeSource;
			multiNoiseBiomeSourceAccessor.setLastSampledSeed(serverLevel.getSeed());
			multiNoiseBiomeSourceAccessor.setLastSampledDimension(serverLevel.dimension());
		}
	}
}

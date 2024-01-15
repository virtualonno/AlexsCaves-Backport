package com.github.alexmodguy.alexscaves.server.generation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public interface IMultiNoiseBiomeSourceAccessor {
	
	void setLastSampledSeed(long seed);
	
	void setLastSampledDimension(ResourceKey<Level> paramResourceKey);
}

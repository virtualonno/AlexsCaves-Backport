package com.github.alexmodguy.alexscaves.server.misc;

import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ACCreativeTabRegistry {

	public static final CreativeModeTab MAGNETIC_CAVES = new CreativeModeTab("alexscaves.magnetic_caves") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ACBlockRegistry.SCARLET_MAGNET.get());
		}
	};
	
	public static final CreativeModeTab PRIMORDIAL_CAVES = new CreativeModeTab("alexscaves.primordial_caves") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ACBlockRegistry.FLYTRAP.get());
		}
	};
	
	public static final CreativeModeTab TOXIC_CAVES = new CreativeModeTab("alexscaves.toxic_caves") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ACBlockRegistry.WASTE_DRUM.get());
		}
	};
	
	public static final CreativeModeTab ABYSSAL_CHASM = new CreativeModeTab("alexscaves.abyssal_chasm") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ACItemRegistry.SUBMARINE.get());
		}
	};
	
	public static final CreativeModeTab FORLORN_HOLLOWS = new CreativeModeTab("alexscaves.forlorn_hollows") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ACBlockRegistry.PEERING_COPROLITH.get());
		}
	};
}

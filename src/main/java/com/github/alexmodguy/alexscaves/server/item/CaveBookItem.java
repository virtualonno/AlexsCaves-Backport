package com.github.alexmodguy.alexscaves.server.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CaveBookItem extends Item {
    public CaveBookItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)
        		.tab(ACCreativeTabRegistry.MAGNETIC_CAVES)
        		.tab(ACCreativeTabRegistry.PRIMORDIAL_CAVES)
        		.tab(ACCreativeTabRegistry.TOXIC_CAVES)
        		.tab(ACCreativeTabRegistry.ABYSSAL_CHASM)
        		.tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStackIn = playerIn.getItemInHand(handIn);
        if (playerIn instanceof ServerPlayer) {
            ServerPlayer serverplayerentity = (ServerPlayer) playerIn;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemStackIn);
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }
        playerIn.swing(handIn);
        if (worldIn.isClientSide) {
            AlexsCaves.PROXY.openBookGUI(itemStackIn);
        }
        return new InteractionResultHolder(InteractionResult.PASS, itemStackIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.alexscaves.cave_book.desc").withStyle(ChatFormatting.GRAY));
    }


}

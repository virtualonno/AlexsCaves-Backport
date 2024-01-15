package com.github.alexmodguy.alexscaves.server.item;

import java.util.UUID;

import javax.annotation.Nullable;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;

public class DivingArmorItem extends ArmorItem {
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private Multimap<Attribute, AttributeModifier> attributeMap;

    public DivingArmorItem(ArmorMaterial armorMaterial, EquipmentSlot slot) {
        super(armorMaterial, slot, new Properties().tab(ACCreativeTabRegistry.ABYSSAL_CHASM));
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) AlexsCaves.PROXY.getArmorProperties());
    }

    private void buildAttributes(ArmorMaterial materialIn) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[slot.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", materialIn.getDefenseForSlot(this.slot), AttributeModifier.Operation.ADDITION));
        if (this == ACItemRegistry.DIVING_LEGGINGS.get()) {
            builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "Swim speed", 0.5D, AttributeModifier.Operation.ADDITION));
        }
        if (this == ACItemRegistry.DIVING_CHESTPLATE.get()) {
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", materialIn.getToughness(), AttributeModifier.Operation.ADDITION));
        }
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        attributeMap = builder.build();
    }


    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (getMaterial() == ACItemRegistry.DIVING_SUIT_ARMOR_MATERIAL && equipmentSlot == this.slot) {
            if (attributeMap == null) {
                buildAttributes(ACItemRegistry.DIVING_SUIT_ARMOR_MATERIAL);
            }
            return attributeMap;
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (slot == EquipmentSlot.LEGS) {
            return AlexsCaves.MODID + ":textures/armor/diving_suit_1.png";
        } else {
            return AlexsCaves.MODID + ":textures/armor/diving_suit_0.png";
        }
    }
}

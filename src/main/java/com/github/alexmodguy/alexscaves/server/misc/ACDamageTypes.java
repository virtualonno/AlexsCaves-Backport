package com.github.alexmodguy.alexscaves.server.misc;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ACDamageTypes {
    public static DamageSource causeAcidDamage(RegistryAccess registryAccess) {
        return new DamageSourceRandomMessages("acid", 1);
    }

    public static DamageSource causeRadiationDamage(RegistryAccess registryAccess) {
        return new DamageSourceRandomMessages("radiation", 2);
    }

    public static DamageSource causeNukeDamage(RegistryAccess registryAccess) {
        return new DamageSourceRandomMessages("nuke", 4);
    }

    public static DamageSource causeRaygunDamage(RegistryAccess registryAccess, Entity source) {
        return new DamageSourceRandomMessages("raygun", source, 1);
    }

    public static DamageSource causeForsakenSonicBoomDamage(RegistryAccess registryAccess, Entity source) {
        return new DamageSourceRandomMessages("forsaken_sonic_boom", source, 2);
    }

    public static DamageSource causeDesolateDaggerDamage(RegistryAccess registryAccess, Entity source) {
        return new DamageSourceRandomMessages("desolate_dagger", source, 1);
    }

    public static DamageSource causeDarkArrowDamage(RegistryAccess registryAccess, Entity source) {
        return new DamageSourceRandomMessages("dark_arrow", source, 1);
    }


    private static class DamageSourceRandomMessages extends EntityDamageSource {

        private int messageCount;

        public DamageSourceRandomMessages(String message, int messageCount) {
            this(message, null, messageCount);
            this.messageCount = messageCount;
        }
        
        public DamageSourceRandomMessages(String message, Entity entity, int messageCount) {
            super(message, entity);
            this.messageCount = messageCount;
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity attacked) {
            int type = attacked.getRandom().nextInt(this.messageCount);
            String s = "death.attack." + this.getMsgId() + "_" + type;
            Entity entity = this.getDirectEntity() == null ? this.getEntity() : this.getDirectEntity();
            if (entity != null) {
                return Component.translatable(s + ".entity", attacked.getDisplayName(), entity.getDisplayName());
            }else{
                return Component.translatable(s, attacked.getDisplayName());
            }
        }
    }
}

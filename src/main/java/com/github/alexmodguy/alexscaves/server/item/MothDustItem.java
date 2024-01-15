package com.github.alexmodguy.alexscaves.server.item;

import java.util.function.Predicate;

import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;
import com.github.alexmodguy.alexscaves.server.misc.ACTagRegistry;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MothDustItem extends Item {

    public MothDustItem() {
        super(new Item.Properties().tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity living, int time) {
        if (living instanceof Player player) {
            int i = this.getUseDuration(itemStack) - time;
            float strength = getPowerForTime(i);
            float distance = strength * 5.0F;
            HitResult realHitResult = getHitResultOnViewVector(living, this::canBeHitByProjectile, distance);
            Vec3 vec31 = realHitResult.getLocation();
            for(int j = 0; j < Math.ceil(distance * 3); j++){
                Vec3 vec32 = vec31.add(level.random.nextFloat() - 0.5F, level.random.nextFloat() - 0.5F, level.random.nextFloat() - 0.5F);
                Vec3 vec34 = player.getEyePosition().add(level.random.nextFloat() - 0.5F, level.random.nextFloat() - 0.5F, level.random.nextFloat() - 0.5F);
                Vec3 vec33 = vec32.subtract(player.getEyePosition()).normalize().scale(strength * 5F);
                level.addParticle(ACParticleRegistry.MOTH_DUST.get(), vec34.x, vec34.y, vec34.z, vec33.x, vec33.y, vec33.z);
            }
            if(realHitResult instanceof EntityHitResult hitEntityResult && hitEntityResult.getEntity() instanceof LivingEntity target){
                if(target.canBeSeenAsEnemy()){
                    AABB hitBox = new AABB(vec31.add(-32, -32, -32), vec31.add(32, 32, 32));
                    for(Entity entity : level.getEntities(target, hitBox, this::canBeHitByProjectile)){
                        if(!target.is(entity) && !target.isAlliedTo(entity) && !entity.isAlliedTo(target) && !entity.isPassengerOfSameVehicle(target)){
                            if(entity.getType().is(ACTagRegistry.MOTH_DUST_ENRAGES) && entity instanceof Mob mob){
                                mob.setTarget(target);
                                mob.setLastHurtByMob(target);
                            }
                        }
                    }

                }
            }
            if(!player.isCreative()){
                itemStack.shrink(1);
            }
        }
    }
    
    public boolean canBeHitByProjectile(Entity entity) {
        return entity.isAlive() && entity.isPickable();
     }

    public static HitResult getHitResultOnViewVector(Entity p_278281_, Predicate<Entity> p_278306_, double p_278293_) {
    	Vec3 vec3 = p_278281_.getViewVector(0.0F).scale(p_278293_);
        Level level = p_278281_.level;
        Vec3 vec31 = p_278281_.getEyePosition();
        return getHitResult(vec31, p_278281_, p_278306_, vec3, level);
    }

    private static HitResult getHitResult(Vec3 p_278237_, Entity p_278320_, Predicate<Entity> p_278257_, Vec3 p_278342_, Level p_278321_) {
    	Vec3 vec3 = p_278237_.add(p_278342_);
    	HitResult hitresult = p_278321_.clip(new ClipContext(p_278237_, vec3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, p_278320_));
    	if (hitresult.getType() != HitResult.Type.MISS) {
    		vec3 = hitresult.getLocation();
    	}

    	HitResult hitresult1 = ProjectileUtil.getEntityHitResult(p_278321_, p_278320_, p_278237_, vec3, p_278320_.getBoundingBox().expandTowards(p_278342_).inflate(1.0D), p_278257_);
    	if (hitresult1 != null) {
    		hitresult = hitresult1;
    	}

    	return hitresult;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public static float getPowerForTime(int i) {
        float f = (float) i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
}

package com.github.alexmodguy.alexscaves.server.item;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.entity.item.DarkArrowEntity;
import com.github.alexmodguy.alexscaves.server.misc.ACCreativeTabRegistry;
import com.github.alexmodguy.alexscaves.server.misc.ACSoundRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

public class DreadbowItem extends ProjectileWeaponItem {

    public static final int LOAD_TIME = 40;
    public DreadbowItem() {
        super(new Item.Properties().rarity(ACItemRegistry.RARITY_DEMONIC).durability(500).tab(ACCreativeTabRegistry.FORLORN_HOLLOWS));
    }

    @Nullable
    public static EntityType getTypeOfArrow(ItemStack itemStackIn) {
        if(itemStackIn.getTag() != null && itemStackIn.getTag().contains("LastUsedArrowType")) {
            String str = itemStackIn.getTag().getString("LastUsedArrowType");
            return ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(str));
        }
        return null;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) AlexsCaves.PROXY.getISTERProperties());
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        ItemStack ammo = player.getProjectile(itemstack);
        boolean flag = player.isCreative();
        if(flag || !ammo.isEmpty()){
            AbstractArrow lastArrow = createArrow(player, itemstack, ItemStack.EMPTY);
            EntityType lastArrowType = lastArrow == null ? EntityType.ARROW : lastArrow.getType();
            itemstack.getOrCreateTag().putString("LastUsedArrowType", ForgeRegistries.ENTITY_TYPES.getKey(lastArrowType).toString());
            player.startUsingItem(interactionHand);
            return InteractionResultHolder.consume(itemstack);
        }else{
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean held) {
        super.inventoryTick(stack, level, entity, i, held);
        boolean using = entity instanceof LivingEntity living && living.getUseItem().equals(stack);
        int useTime = getUseTime(stack);
        if (level.isClientSide) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.getInt("PrevUseTime") != tag.getInt("UseTime")) {
                tag.putInt("PrevUseTime", getUseTime(stack));
            }
            if (using && useTime < LOAD_TIME) {
                setUseTime(stack, useTime + 1);
            }
            if (!using && useTime > 0.0F) {
                setUseTime(stack, Math.max(0, useTime - 5));
            }
            if(using){
                Vec3 particlePos = entity.position().add((level.random.nextFloat() - 0.5F) * 2.5F, 0F, (level.random.nextFloat() - 0.5F) * 2.5F);
                level.addParticle(ACParticleRegistry.UNDERZEALOT_MAGIC.get(), particlePos.x, particlePos.y, particlePos.z, entity.getX(), entity.getY(0.5F), entity.getZ());
            }
        }
    }

    public static int getUseTime(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null ? compoundtag.getInt("UseTime") : 0;
    }

    public static void setUseTime(ItemStack stack, int useTime) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("PrevUseTime", getUseTime(stack));
        tag.putInt("UseTime", useTime);
    }

    public static float getLerpedUseTime(ItemStack stack, float f) {
        CompoundTag compoundtag = stack.getTag();
        float prev = compoundtag != null ? (float) compoundtag.getInt("PrevUseTime") : 0F;
        float current = compoundtag != null ? (float) compoundtag.getInt("UseTime") : 0F;
        return prev + f * (current - prev);
    }

    public static float getPullingAmount(ItemStack itemStack, float partialTicks){
        return Math.min(getLerpedUseTime(itemStack, partialTicks) / (float) LOAD_TIME, 1F);
    }



    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(int i) {
        float f = (float) i / (float)LOAD_TIME;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i1) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(itemStack) - i1;
            float f = getPowerForTime(i);
            if (f > 0.1D) {
                player.playSound(ACSoundRegistry.DREADBOW_RELEASE.get());
                ItemStack ammoStack = player.getProjectile(itemStack);
                AbstractArrow abstractArrow = createArrow(player, itemStack, ammoStack);
                if(abstractArrow != null){
                    float maxDist = 128 * f;
                    HitResult realHitResult = getHitResultOnViewVector(player, this::canBeHitByProjectile, maxDist);
                    if(realHitResult.getType() == HitResult.Type.MISS){
                        realHitResult = getHitResultOnViewVector(player, this::canBeHitByProjectile, f * 42);
                    }
                    BlockPos mutableSkyPos = new BlockPos.MutableBlockPos(realHitResult.getLocation().x, realHitResult.getLocation().y + 1.5, realHitResult.getLocation().z);
                    int maxFallHeight = 15;
                    int k = 0;
                    while(mutableSkyPos.getY() < level.getMaxBuildHeight() && level.isEmptyBlock(mutableSkyPos) && k < maxFallHeight){
                        mutableSkyPos = mutableSkyPos.above();
                        k++;
                    }
                    boolean darkArrows = isConvertibleArrow(abstractArrow);
                    int maxArrows = darkArrows ? 30 : 8;
                    abstractArrow.pickup = AbstractArrow.Pickup.ALLOWED;
                    for(int j = 0; j < Math.ceil(maxArrows * f); j++){
                        if(darkArrows){
                            abstractArrow = new DarkArrowEntity(level, livingEntity);
                        }
                        Vec3 vec3 = Vec3.atCenterOf(mutableSkyPos).add(level.random.nextFloat() * 16 - 8, level.random.nextFloat() * 4 - 2, level.random.nextFloat() * 16 - 8);
                        int clearTries = 0;
                        while (clearTries < 6 && !level.isEmptyBlock(new BlockPos(vec3)) && level.getFluidState(new BlockPos(vec3)).isEmpty()){
                            clearTries++;
                            vec3 = Vec3.atCenterOf(mutableSkyPos).add(level.random.nextFloat() * 16 - 8, level.random.nextFloat() * 4 - 2, level.random.nextFloat() * 16 - 8);
                        }
                        if(!level.isEmptyBlock(new BlockPos(vec3)) && level.getFluidState(new BlockPos(vec3)).isEmpty()){
                            vec3 = Vec3.atCenterOf(mutableSkyPos);
                        }
                        abstractArrow.setPos(vec3);
                        Vec3 vec31 = realHitResult.getLocation().subtract(vec3);
                        float randomness = (darkArrows ? 20F : 5F) + level.random.nextFloat() * 10F;
                        if(level.random.nextFloat() < 0.25F){
                            randomness = level.random.nextFloat();
                        }
                        abstractArrow.shoot(vec31.x, vec31.y, vec31.z, 0.5F + 1.5F * level.random.nextFloat(),  randomness);
                        level.addFreshEntity(abstractArrow);
                        abstractArrow = createArrow(player, itemStack, ammoStack);
                        abstractArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    if(darkArrows){
                        Vec3 vec3 = realHitResult.getLocation();
                        level.playSound((Player)null, vec3.x, vec3.y, vec3.z, ACSoundRegistry.DREADBOW_RAIN.get(), SoundSource.PLAYERS, 12.0F, 1.0F);
                    }
                    if(!player.isCreative()){
                        ammoStack.shrink(1);
                        itemStack.hurtAndBreak(1, player, (player1) -> {
                            player1.broadcastBreakEvent(player1.getUsedItemHand());
                        });
                    }
                }
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
    
    private AbstractArrow createArrow(Player player, ItemStack bowStack, ItemStack ammoIn) {
        ItemStack ammo = ammoIn.isEmpty() ? player.getProjectile(bowStack) : ammoIn;
        ArrowItem arrowitem = (ArrowItem)(ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
        AbstractArrow abstractArrow =  arrowitem.createArrow(player.level, ammo, player);
        return abstractArrow;
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !oldStack.is(ACItemRegistry.DREADBOW.get()) || !newStack.is(ACItemRegistry.DREADBOW.get());
    }
    public static boolean isConvertibleArrow(Entity arrowEntity){
        return arrowEntity instanceof Arrow arrow && arrow.getColor() == -1;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 64;
    }
}

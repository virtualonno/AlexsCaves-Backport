package com.github.alexmodguy.alexscaves.server.entity.item;

import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.util.AlexsCavesBoat;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class AlexsCavesBoatEntity extends Boat implements AlexsCavesBoat {

    public AlexsCavesBoatEntity(EntityType type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    public AlexsCavesBoatEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ACEntityRegistry.BOAT.get(), level);
        this.setBoundingBox(this.makeBoundingBox());
    }

    public AlexsCavesBoatEntity(Level level, double x, double y, double z) {
        this(ACEntityRegistry.BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public AlexsCavesBoatEntity(Level level, Vec3 location, AlexsCavesBoat.Type type) {
        this(level, location.x, location.y, location.z);
        this.setACBoatType(type);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putString("ACBoatType", getACBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("ACBoatType")) {
            this.entityData.set(DATA_ID_TYPE, AlexsCavesBoat.Type.byName(nbt.getString("ACBoatType")).ordinal());
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean isOnGround, BlockState state, BlockPos pos) {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
            if (isOnGround) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != Boat.Status.ON_LAND) {
                        this.resetFallDistance();
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && !this.isRemoved()) {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 3; ++i) {
                                this.spawnAtLocation(this.getACBoatType().getPlankSupplier().get());
                            }

                            for (int j = 0; j < 2; ++j) {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.resetFallDistance();
            } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance -= (float) y;
            }
        }
    }

    @Override
    public Item getDropItem() {
        return getACBoatType().getDropSupplier().get();
    }

    public void setACBoatType(AlexsCavesBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public AlexsCavesBoat.Type getACBoatType() {
        return AlexsCavesBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    public void setType(Boat.Type p_38333_) {
    }

    @Override
    public Boat.Type getBoatType() {
        return Boat.Type.OAK;
    }

}
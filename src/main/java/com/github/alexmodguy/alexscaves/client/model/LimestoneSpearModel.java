package com.github.alexmodguy.alexscaves.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

import net.minecraft.world.entity.Entity;

public class LimestoneSpearModel extends AdvancedEntityModel {
    private final AdvancedModelBox spear;
    private final AdvancedModelBox spear2;

    public LimestoneSpearModel() {
        texWidth = 32;
        texHeight = 32;

        spear = new AdvancedModelBox(this);
        spear.setRotationPoint(0.0F, 15.0F, 0.0F);
        spear.setTextureOffset(0, 0).addBox(-0.5F, -20.0F, -0.5F, 1.0F, 29.0F, 1.0F, 0.0F, false);
        spear.setTextureOffset(12, 0).addBox(-1.0F, -18.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        spear.setTextureOffset(4, 0).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        spear.setTextureOffset(4, 19).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 12.0F, 1.0F, 0.25F, false);
        spear.setTextureOffset(8, 10).addBox(0.0F, -18.0F, -5.5F, 0.0F, 2.0F, 5.0F, 0.0F, false);
        spear.setTextureOffset(14, 1).addBox(0.0F, -15.0F, -3.5F, 0.0F, 2.0F, 3.0F, 0.0F, false);
        spear.setTextureOffset(4, 0).addBox(0.0F, -18.0F, 0.5F, 0.0F, 2.0F, 5.0F, 0.0F, false);
        spear.setTextureOffset(8, 14).addBox(0.0F, -15.0F, 0.5F, 0.0F, 2.0F, 3.0F, 0.0F, false);
        spear.setTextureOffset(4, 0).addBox(0.0F, -27.0F, -3.5F, 0.0F, 8.0F, 7.0F, 0.0F, false);

        spear2 = new AdvancedModelBox(this);
        spear2.setRotationPoint(0.0F, -23.0F, 0.0F);
        spear.addChild(spear2);
        setRotateAngle(spear2, 0.0F, -1.5708F, 0.0F);
        spear2.setTextureOffset(4, 0).addBox(0.0F, -4.0F, -3.5F, 0.0F, 8.0F, 7.0F, 0.0F, false);
        this.updateDefaultPose();
    }


    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(spear);
    }


    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(spear, spear2);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float explode, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }
}
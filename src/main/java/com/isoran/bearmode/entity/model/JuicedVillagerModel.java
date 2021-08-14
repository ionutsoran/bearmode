package com.isoran.bearmode.entity.model;

import com.isoran.bearmode.entity.JuicedVillagerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;

public class JuicedVillagerModel<T extends JuicedVillagerEntity> extends EntityModel<T> {
    private  ModelRenderer head;
    private final ModelRenderer headwear;
    private final ModelRenderer headwear2;
    private final ModelRenderer nose;
    private final ModelRenderer body;
    private final ModelRenderer bodywear;
    private final ModelRenderer arms;
    private  ModelRenderer arms_rotation;
    private  ModelRenderer arms_right;
    private ModelRenderer arms_biceps_rotation;
    private ModelRenderer arms_hand_rotaion;
    private ModelRenderer arms_biceps_right;
    private ModelRenderer arms_hand_right;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;

    public JuicedVillagerModel() {
        texWidth = 128;
        texHeight = 128;

        head = new ModelRenderer(this);
        head.setPos(0.0F, -15.0F, 0.0F);
        head.texOffs(0, 43).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        headwear = new ModelRenderer(this);
        headwear.setPos(-0.5F, 9.5F, -3.5F);
        headwear.texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        headwear2 = new ModelRenderer(this);
        headwear2.setPos(-0.5F, 10.5F, -4.5F);
        headwear2.texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, 4.0F, -5.0F);
        nose.texOffs(18, 6).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        head.addChild(nose);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.8F, -0.9F);
        body.texOffs(0, 0).addBox(-7.0F, -10.8F, -4.1F, 14.0F, 9.0F, 10.0F, 0.0F, false);
        body.texOffs(68, 64).addBox(-6.0F, -8.8F, -5.1F, 12.0F, 6.0F, 1.0F, 0.0F, false);
        body.texOffs(38, 0).addBox(-6.0F, -1.8F, -3.1F, 12.0F, 2.0F, 8.0F, 0.0F, false);
        body.texOffs(40, 11).addBox(-5.0F, 0.2F, -3.1F, 10.0F, 7.0F, 8.0F, 0.0F, false);
        body.texOffs(32, 51).addBox(-5.0F, 7.2F, -2.1F, 10.0F, 4.0F, 8.0F, 0.0F, false);

        bodywear = new ModelRenderer(this);
        bodywear.setPos(1.5F, 11.5F, -3.5F);
        bodywear.texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setPos(-0.1602F, -4.1082F, -0.988F);


        arms_rotation = new ModelRenderer(this);
        arms_rotation.setPos(0.0F, 0.0F, 0.0F);
        arms.addChild(arms_rotation);
        arms_rotation.texOffs(0, 19).addBox(7.1602F, -6.8918F, -5.012F, 7.0F, 10.0F, 12.0F, 0.0F, false);
        arms_rotation.texOffs(56, 64).addBox(14.1602F, -5.8918F, -4.012F, 1.0F, 8.0F, 10.0F, 0.0F, false);
        arms_rotation.texOffs(52, 26).addBox(7.1602F, -7.8918F, -4.012F, 6.0F, 1.0F, 10.0F, 0.0F, false);
        arms_rotation.texOffs(36, 77).addBox(6.1602F, -6.8918F, -4.012F, 1.0F, 1.0F, 10.0F, 0.0F, false);

        arms_biceps_rotation = new ModelRenderer(this);
        arms_biceps_rotation.setPos(0.0F, 0.0F, 0.0F);
        arms_rotation.addChild(arms_biceps_rotation);
        setRotationAngle(arms_biceps_rotation, -0.1745F, 0.0F, 0.0F);
        arms_biceps_rotation.texOffs(28, 63).addBox(7.0267F, 1.7743F, -2.9736F, 6.0F, 6.0F, 8.0F, 0.0F, false);

        arms_hand_rotaion = new ModelRenderer(this);
        arms_hand_rotaion.setPos(0.0F, 0.0F, 0.0F);
        arms_rotation.addChild(arms_hand_rotaion);
        setRotationAngle(arms_hand_rotaion, -0.5236F, 0.0F, 0.0F);
        arms_hand_rotaion.texOffs(74, 20).addBox(8.1602F, 6.1082F, -0.012F, 4.0F, 10.0F, 6.0F, 0.0F, false);

        arms_right = new ModelRenderer(this);
        arms_right.setPos(0.0F, 0.0F, 0.0F);
        arms_rotation.addChild(arms_right);
        arms_right.texOffs(26, 29).addBox(-13.8398F, -6.8918F, -5.012F, 7.0F, 10.0F, 12.0F, 0.0F, false);
        arms_right.texOffs(68, 0).addBox(-14.8398F, -5.8918F, -4.012F, 1.0F, 8.0F, 10.0F, 0.0F, false);
        arms_right.texOffs(58, 53).addBox(-12.8398F, -7.8918F, -4.012F, 6.0F, 1.0F, 10.0F, 0.0F, false);
        arms_right.texOffs(80, 8).addBox(-6.8398F, -6.8918F, -4.012F, 1.0F, 1.0F, 10.0F, 0.0F, false);

        arms_biceps_right = new ModelRenderer(this);
        arms_biceps_right.setPos(0.0F, 0.0F, 0.0F);
        arms_right.addChild(arms_biceps_right);
        setRotationAngle(arms_biceps_right, -0.1745F, 0.0F, 0.0F);
        arms_biceps_right.texOffs(0, 61).addBox(-13.7075F, 1.6438F, -2.9567F, 6.0F, 6.0F, 8.0F, 0.0F, false);

        arms_hand_right = new ModelRenderer(this);
        arms_hand_right.setPos(0.0F, 0.0F, 0.0F);
        arms_right.addChild(arms_hand_right);
        setRotationAngle(arms_hand_right, -0.5236F, 0.0F, 0.0F);
        arms_hand_right.texOffs(64, 37).addBox(-12.8398F, 6.1082F, -0.012F, 4.0F, 10.0F, 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(2.5F, 18.0F, 0.0F);
        left_leg.texOffs(0, 75).addBox(-2.5F, -6.0F, -2.0F, 5.0F, 12.0F, 4.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setPos(-2.5F, 18.0F, 0.0F);
        right_leg.texOffs(18, 77).addBox(-2.5F, -6.0F, -2.0F, 5.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        boolean flag = false;
        if (entity instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity)entity).getUnhappyCounter() > 0;
        }

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        if (flag) {
            this.head.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.head.xRot = 0.4F;
        } else {
            this.head.zRot = 0.0F;
        }

//        this.arms.y = 3.0F;
//        this.arms.z = -1.0F;
//        this.arms.xRot = -0.75F;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.left_leg.yRot = 0.0F;
        this.right_leg.yRot = 0.0F;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        headwear.render(matrixStack, buffer, packedLight, packedOverlay);
        headwear2.render(matrixStack, buffer, packedLight, packedOverlay);
       // nose.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        bodywear.render(matrixStack, buffer, packedLight, packedOverlay);
        arms.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
        int i = p_212843_1_.getAttackAnimationTick();
        if (i > 0) {
            this.arms.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - p_212843_4_, 10.0F);
            //this.arm1.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - p_212843_4_, 10.0F);
        } else {
                this.arms.xRot = (-0.2F + 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;
                //this.arm1.xRot = (-0.2F - 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;
            }
    }
}

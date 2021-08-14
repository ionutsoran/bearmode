package com.isoran.bearmode.entity.model;

import com.isoran.bearmode.entity.BuffaloEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BuffaloModel<T extends BuffaloEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer legBackRight;
    private final ModelRenderer legBackLeft;

    public BuffaloModel() {
        texHeight = 45;
        texWidth = 71;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 3.0F, -4.0F);
        head.texOffs(1, 2).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
        head.texOffs(26, 3).addBox(3.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(26, 2).addBox(-4.0F, -6.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 7.5F, 1.5F);
        body.texOffs(0, 28).addBox(-5.0F, -7.5F, -5.5F, 10.0F, 7.0F, 10.0F, 0.0F, false);
        body.texOffs(30, 10).addBox(-4.0F, -5.5F, -4.5F, 8.0F, 12.0F, 8.0F, 0.0F, false);
        setRotationAngle(body, -1.5708F, 0.0F, 0.0F);

        legFrontRight = new ModelRenderer(this);
        legFrontRight.setPos(-3.0F, 12.5F, -4.0F);
        legFrontRight.texOffs(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        legFrontLeft = new ModelRenderer(this);
        legFrontLeft.setPos(3.0F, 12.5F, -4.0F);
        legFrontLeft.texOffs(63, 32).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        legBackRight = new ModelRenderer(this);
        legBackRight.setPos(-3.0F, 11.0F, 7.0F);
        legBackRight.texOffs(63, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

        legBackLeft = new ModelRenderer(this);
        legBackLeft.setPos(3.0F, 11.0F, 7.0F);
        legBackLeft.texOffs(63, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.body.xRot = ((float)Math.PI / 2F);
        this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

}

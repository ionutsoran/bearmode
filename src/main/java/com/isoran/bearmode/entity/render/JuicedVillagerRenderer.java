package com.isoran.bearmode.entity.render;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.entity.JuicedVillagerEntity;
import com.isoran.bearmode.entity.model.JuicedVillagerModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class JuicedVillagerRenderer extends MobRenderer<JuicedVillagerEntity, JuicedVillagerModel<JuicedVillagerEntity>>
{
    public JuicedVillagerRenderer(EntityRendererManager rendererManagerIn)
    {
        super(rendererManagerIn, new JuicedVillagerModel<>(), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(JuicedVillagerEntity entity)
    {
        return new ResourceLocation(Bearmode.MOD_ID, "textures/entity/juiced_villager.png");
    }

    protected void setupRotations(JuicedVillagerEntity entity, MatrixStack stack, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.setupRotations(entity, stack, p_225621_3_, p_225621_4_, p_225621_5_);
        if (!((double)entity.animationSpeed < 0.01D)) {
            float f = 13.0F;
            float f1 = entity.animationPosition - entity.animationSpeed * (1.0F - p_225621_5_) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            stack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}

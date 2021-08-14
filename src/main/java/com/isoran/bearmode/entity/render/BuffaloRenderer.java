package com.isoran.bearmode.entity.render;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.entity.BuffaloEntity;
import com.isoran.bearmode.entity.model.BuffaloModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BuffaloRenderer extends MobRenderer<BuffaloEntity, BuffaloModel<BuffaloEntity>>
{
    public BuffaloRenderer(EntityRendererManager rendererManagerIn)
    {
        super(rendererManagerIn, new BuffaloModel<>(), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(BuffaloEntity entity)
    {
        return new ResourceLocation(Bearmode.MOD_ID, "textures/entity/buffalo.png");
    }
}

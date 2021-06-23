package com.isoran.bearmode.setup;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.block.ModBlocks;
import com.isoran.bearmode.block.ModFluids;
import com.isoran.bearmode.container.ModContainers;
import com.isoran.bearmode.particles.BearParticle;
import com.isoran.bearmode.particles.ModParticles;
import com.isoran.bearmode.screens.PipeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bearmode.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy{
    @Override
    public void init() {
        RenderTypeLookup.setRenderLayer(ModBlocks.ZUCCINI_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.translucent());
        Minecraft.getInstance().particleEngine.register(ModParticles.TIMISOREANA_PARTICLE.get(),new BearParticle.Factory()); //.Factory(Color.FIREBRICK));
        RenderTypeLookup.setRenderLayer(ModBlocks.PIPE.get(), RenderType.translucent());
        ScreenManager.register(ModContainers.PIPE_CONTAINER.get(), PipeScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().level;
    }
}
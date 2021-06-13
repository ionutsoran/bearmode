package com.isoran.bearmode.util;

import com.isoran.bearmode.Bearmode;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Bearmode.MOD_ID);

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Bearmode.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, Bearmode.MOD_ID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Bearmode.MOD_ID);

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES
            = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Bearmode.MOD_ID);

    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        FLUIDS.register(eventBus);
        PARTICLE_TYPES.register(eventBus);
    }
}

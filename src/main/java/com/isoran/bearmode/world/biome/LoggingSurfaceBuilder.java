package com.isoran.bearmode.world.biome;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.Random;

public class LoggingSurfaceBuilder<C extends ISurfaceBuilderConfig, S extends SurfaceBuilder<C>> extends SurfaceBuilder<C>
{

    public LoggingSurfaceBuilder(Codec<C> codec) {
        super(codec);
    }

    @Override
    public void apply(Random random, IChunk chunk, Biome biome,
                      int p_205610_4_, int p_205610_5_, int p_205610_6_, double p_205610_7_,
                      BlockState defaultBlock, BlockState defaultFluid, int p_205610_11_,
                      long p_205610_12_, C p_205610_14_) {

    }
}

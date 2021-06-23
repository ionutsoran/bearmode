package com.isoran.bearmode.tileentity;

import com.isoran.bearmode.block.ModBlocks;
import com.isoran.bearmode.util.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities
{
    public static final RegistryObject<TileEntityType<PipeTile>> PIPE_TILE_ENTITY
            =Registration.TILE_ENTITY_TYPE.register("pipe_tile_entity", () -> TileEntityType.Builder.of(
            () -> new PipeTile(), ModBlocks.PIPE.get()).build(null));

    public static void register() {}
}

package com.isoran.bearmode.container;

import com.isoran.bearmode.util.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers
{
    public static final RegistryObject<ContainerType<PipeContainer>> PIPE_CONTAINER
            = Registration.CONTAINERS.register("pipe_container",
            () -> IForgeContainerType.create(
                    ((windowId , inv, data) -> {
                        BlockPos pos= data.readBlockPos();
                        World world = inv.player.getCommandSenderWorld();
                        return new PipeContainer(windowId, world, pos, inv, inv.player);
                    })
            ));

    public static void register() {}
}

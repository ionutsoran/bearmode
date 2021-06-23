package com.isoran.bearmode.block;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.util.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import java.util.function.Supplier;


public class ModBlocks {

    public static final RegistryObject<Block> COPPER_BLOCK = register("copper_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(3f,3f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(3f,3f)
                    .sound(SoundType.STONE)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> COPPER_STAIRS = register("copper_stairs",
            () -> new StairsBlock(() -> ModBlocks.COPPER_BLOCK.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> COPPER_FENCE =
            register("copper_fence", () -> new FenceBlock(AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> COPPER_FENCE_GATE =
            register("copper_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE =
            register("copper_pressure_plate", () -> new PressurePlateBlock(
                    PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> COPPER_SLAB =
            register("copper_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> COPPER_BUTTON =
            register("copper_button", () -> new StoneButtonBlock(AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> ZUCCINI_CROP =
            Registration.BLOCKS.register("zuccini_crop",
                    () -> new ZucciniCrop(AbstractBlock.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> TIMISOREANA =
            register("timisoreana", () -> new Timisoreana(
                    AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(4f)
                    .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> PIPE =
            register("pipe", () -> new Pipe(
                    2.0F, 2.0F, 16.0F, 16.0F, 24.0F,
                    AbstractBlock.Properties.of(Material.GLASS).strength(4f)));

    public static void register() {}

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block){

        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().tab(Bearmode.COURSE_TAB)));

        return toReturn;
    }
}

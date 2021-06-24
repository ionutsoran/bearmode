package com.isoran.bearmode.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class RedwoodSapling extends BushBlock implements IGrowable
{
    public static final IntegerProperty STAGE = BlockStateProperties.AGE_1;

    private final Supplier<Tree> tree;

    public RedwoodSapling(Supplier<Tree> treeIn, Properties properties) {
        super(properties);
        this.tree = treeIn;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double)worldIn.random.nextFloat() < 0.5F;
    }

    @Override
    public void performBonemeal(ServerWorld server, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(STAGE) == 0)
        {
            server.setBlock(pos, state.cycle(STAGE), 4); //state.cycle
        }
        else
        {
            if(!ForgeEventFactory.saplingGrowTree(server, rand, pos))
            {
                return;
            }

            this.tree.get().growTree(server, server.getChunkSource().getGenerator(), pos, state, rand);
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(STAGE);
    }

    @Override
    public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(state, worldIn, pos, rand);
        if(!worldIn.isAreaLoaded(pos, 1))
        {
            return ;
        }

        if(worldIn.getLightEmission(pos.above()) >= 9 && rand.nextInt(7) == 0)
        {
            this.performBonemeal((ServerWorld) worldIn, rand, pos, state);
        }
    }
}

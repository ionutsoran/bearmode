package com.isoran.bearmode.block;

import com.isoran.bearmode.particles.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;
import java.util.stream.Stream;

public class Timisoreana extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.FACING;

    private static final VoxelShape SHAPE_NSWE = Stream.of(
            Block.box(7, 3, 7, 9, 5, 9),
            VoxelShapes.join(
                    Block.box(5, 1, 7, 6, 6, 9),
                    VoxelShapes.join(
                            Block.box(6, 1, 6, 10, 6, 10),
                            VoxelShapes.join(
                                    Block.box(10, 1, 7, 11, 6, 9),
                                    VoxelShapes.join(
                                            Block.box(7, 1, 10, 9, 6, 11),
                                            Block.box(7, 1, 5, 9, 6, 6),
                                            IBooleanFunction.OR),
                                    IBooleanFunction.OR),
                            IBooleanFunction.OR),
                    IBooleanFunction.OR),
            VoxelShapes.join(
                    Block.box(5, 6, 7, 6, 8, 9),
                    VoxelShapes.join(
                            Block.box(6, 6, 6, 10, 8, 10),
                            VoxelShapes.join(
                                    Block.box(10, 6, 7, 11, 8, 9),
                                    VoxelShapes.join(
                                            Block.box(7, 6, 10, 9, 8, 11),
                                            Block.box(7, 6, 5, 9, 8, 6),
                                            IBooleanFunction.OR),
                                    IBooleanFunction.OR),
                            IBooleanFunction.OR),
                    IBooleanFunction.OR),
            VoxelShapes.join(
                    Block.box(9, 8, 7, 10, 9, 9),
                    VoxelShapes.join(
                            Block.box(6, 8, 7, 7, 9, 9),
                            Block.box(7, 8, 6, 9, 9, 10),
                            IBooleanFunction.OR),
                    IBooleanFunction.OR),
            Block.box(7, 9, 7, 9, 10, 9),
            VoxelShapes.join(
                    Block.box(9, 10, 7, 10, 11, 9),
                    VoxelShapes.join(
                            Block.box(6, 10, 7, 7, 11, 9),
                            Block.box(7, 10, 6, 9, 11, 10),
                            IBooleanFunction.OR),
                    IBooleanFunction.OR),
            VoxelShapes.join(
                    Block.box(10, 0, 7, 11, 1, 9),
                    VoxelShapes.join(
                            Block.box(5, 0, 7, 6, 1, 9),
                            VoxelShapes.join(
                                    Block.box(7, 0, 5, 9, 1, 6),
                                    Block.box(7, 0, 10, 9, 1, 11),
                                    IBooleanFunction.OR),
                            IBooleanFunction.OR),
                    IBooleanFunction.OR)
    ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();

    public Timisoreana(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        //return super.getShape(state, worldIn, pos, context);
        return SHAPE_NSWE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) {

        //just made a custom particle effect to see if it work
        //btw it just adds on top of vanilla parcticles
        //pay attention to the model.json to see if particles key is set correctly
        Random random = new Random();
        final float half_blocksize = 0.5f;

        for(int i=0;i<30;i++)
        {
            manager.createParticle(
                    ModParticles.TIMISOREANA_PARTICLE.get(),
                    pos.getX() + half_blocksize + random.nextFloat() * 2 - 1,
                    pos.getY()  + half_blocksize + random.nextFloat() * 2 - 1,
                    pos.getZ() + half_blocksize + random.nextFloat() * 2 - 1,
                    0.01f,
                    0.01f,
                    0.01f);
        }

        return false;
    }
}

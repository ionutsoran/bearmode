package com.isoran.bearmode.block;

import com.isoran.bearmode.container.PipeContainer;
import com.isoran.bearmode.tileentity.ModTileEntities;
import com.isoran.bearmode.tileentity.PipeTile;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


import javax.annotation.Nullable;
import java.util.*;

public class Pipe extends Block {

    private final Object2IntMap<BlockState> stateToIndex = new Object2IntOpenHashMap<>();

    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty EAST = SixWayBlock.EAST;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;
    public static final BooleanProperty UP = SixWayBlock.UP;
    public static final BooleanProperty DOWN = SixWayBlock.DOWN;

    protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION_H =
            SixWayBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_199775_0_) -> {
        return p_199775_0_.getKey().getAxis().isHorizontal();
    }).collect(Util.toMap());

    protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION_V =
            SixWayBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_199775_0_) -> {
        return p_199775_0_.getKey().getAxis().isVertical();
    }).collect(Util.toMap());

    protected final VoxelShape[] shapeByIndex;

//    private static final VoxelShape SHAPE_NSWE = VoxelShapes.join(
//            Block.box(6, 5, 0, 10, 9, 16),
//            VoxelShapes.empty(),
//            IBooleanFunction.OR);

    public Pipe(float p_i48420_1_, float p_i48420_2_, float p_i48420_3_, float p_i48420_4_, float p_i48420_5_, AbstractBlock.Properties properties) {
        //super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, properties);
        super(properties);

        //this.collisionShapeByIndex = this.makeShapes(p_i48420_1_, p_i48420_2_, p_i48420_5_, 0.0F, p_i48420_5_);
        this.shapeByIndex = this.makeShapes(p_i48420_1_, p_i48420_2_, p_i48420_3_, 0.0F, p_i48420_4_);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, Boolean.valueOf(false))
                .setValue(EAST, Boolean.valueOf(false))
                .setValue(SOUTH, Boolean.valueOf(false))
                .setValue(WEST, Boolean.valueOf(false))
                .setValue(UP, Boolean.valueOf(false))
                .setValue(DOWN, Boolean.valueOf(false)));

        for(BlockState blockstate : this.stateDefinition.getPossibleStates()) {
            LOGGER.info("Stuff_a: " + blockstate.toString());
            this.getAABBIndex(blockstate);
            LOGGER.info("Stuff_b: " + this.getAABBIndex(blockstate));
        }


    }

    protected VoxelShape[] makeShapes(float p_196408_1_, float p_196408_2_, float p_196408_3_, float p_196408_4_, float p_196408_5_) {
        //useful comments
        //1 in binary 00000001
        //LEFT SHIFT0 indexFor: 1 00001101 SOUTH 1
        //LEFT SHIFT1 indexFor: 2 00001111 WEST  2
        //LEFT SHIFT2 indexFor: 4 00000100 NORTH 3
        //LEFT SHIFT3 indexFor: 8 00001100 EAST  4

        //3D
        //LEFT SHIFT2 indexFor: 4 00000100 NORTH 3
        //LEFT SHIFT0 indexFor: 1 00001101 SOUTH 4
        //LEFT SHIFT1 indexFor: 2 00001111 WEST  5
        //LEFT SHIFT3 indexFor: 8 00001100 EAST  6
        VoxelShape voxelshape_default = Block.box(6, 6, 6, 10, 10, 10);

        //TODO Something is not right here investigate!
        VoxelShape voxelshape_D = Block.box(6, 6, 6, 16, 10, 10);//E
        VoxelShape voxelshape_S = Block.box(6, 6, 0, 10, 10, 10);//N
        VoxelShape voxelshape_U = Block.box(0, 6, 6, 10, 10, 10);//W
        VoxelShape voxelshape_N = Block.box(6, 6, 6, 10, 10, 16);//S
        VoxelShape voxelshape_W = Block.box(6, 6, 6, 10, 16, 10);//D
        VoxelShape voxelshape_E = Block.box(6, 0, 6, 10, 10, 10);//U
//        VoxelShape[] avoxelshape = new VoxelShape[]{
//                VoxelShapes.empty(),//0
//                voxelshape_S,//1
//                voxelshape_W,//2
//                VoxelShapes.or(voxelshape_S,voxelshape_W),//3
//                voxelshape_N,//4
//                VoxelShapes.or(voxelshape_N, voxelshape_S),//5
//                VoxelShapes.or(voxelshape_N, voxelshape_W),//6
//                VoxelShapes.or(voxelshape_W, voxelshape_S, voxelshape_N), //7
//                voxelshape_E,//8
//                VoxelShapes.or(voxelshape_E, voxelshape_S),//9
//                VoxelShapes.or(voxelshape_E, voxelshape_W),//10
//                VoxelShapes.or(voxelshape_W, voxelshape_S, voxelshape_E),//11
//                VoxelShapes.or(voxelshape_N, voxelshape_E),//12
//                VoxelShapes.or(voxelshape_N, voxelshape_S, voxelshape_E),//13
//                VoxelShapes.or(voxelshape_N, voxelshape_W, voxelshape_E),//14
//                VoxelShapes.or(voxelshape_N, voxelshape_S, voxelshape_E, voxelshape_W),//15
//        };


//        for(int i = 0; i < avoxelshape.length; ++i) {
//            avoxelshape[i] = VoxelShapes.or(voxelshape_default, avoxelshape[i]);
//        }

        VoxelShape[] avoxelshape = new VoxelShape[64];

        generate_all_VoxelShapes(avoxelshape,voxelshape_N, voxelshape_W, voxelshape_S,
                voxelshape_E,voxelshape_D,voxelshape_U);

        avoxelshape[0] = voxelshape_default;

        return avoxelshape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return this.shapeByIndex[this.getAABBIndex(state)];
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if(!world.isClientSide())
        {
           TileEntity tileEntity = world.getBlockEntity(blockPos);
           if(tileEntity instanceof PipeTile)
           {
               INamedContainerProvider containerProvider = new INamedContainerProvider() {
                   @Override
                   public ITextComponent getDisplayName() {
                       return new TranslationTextComponent("screen.bearmode.pipe");
                   }

                   @Nullable
                   @Override
                   public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                       return new PipeContainer(i, world, blockPos, playerInventory, playerEntity);
                   }
               };
               NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
           }
           else
           {
               throw new IllegalStateException("Our container provider is missing!");
           }
        }

        return ActionResultType.SUCCESS;
    }

    public boolean connectsTo(BlockState p_220111_1_, boolean p_220111_2_, Direction p_220111_3_) {
        Block block = p_220111_1_.getBlock();
        return block.is(Blocks.HOPPER) || block.is(ModBlocks.PIPE.get()) || block.is(Blocks.CHEST.getBlock());
    }

    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        IBlockReader iblockreader = blockItemUseContext.getLevel();
        BlockPos blockpos = blockItemUseContext.getClickedPos();
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockPos blockpos5 = blockpos.above();
        BlockPos blockpos6 = blockpos.below();
        BlockState blockstate = iblockreader.getBlockState(blockpos1);
        BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
        BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
        BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
        BlockState blockstate4 = iblockreader.getBlockState(blockpos5);
        BlockState blockstate5 = iblockreader.getBlockState(blockpos6);
        return super.getStateForPlacement(blockItemUseContext)
                .setValue(NORTH, Boolean.valueOf(
                        this.connectsTo(
                                blockstate,
                                blockstate.isFaceSturdy(iblockreader, blockpos1, Direction.SOUTH),
                                Direction.SOUTH)))
                .setValue(EAST, Boolean.valueOf(
                        this.connectsTo(
                                blockstate1,
                                blockstate1.isFaceSturdy(iblockreader, blockpos2, Direction.WEST),
                                Direction.WEST)))
                .setValue(SOUTH, Boolean.valueOf(
                        this.connectsTo(
                                blockstate2,
                                blockstate2.isFaceSturdy(iblockreader, blockpos3, Direction.NORTH),
                                Direction.NORTH)))
                .setValue(WEST, Boolean.valueOf(
                        this.connectsTo(
                                blockstate3,
                                blockstate3.isFaceSturdy(iblockreader, blockpos4, Direction.EAST),
                                Direction.EAST)))
                .setValue(UP, Boolean.valueOf(
                        this.connectsTo(
                                blockstate4,
                                blockstate4.isFaceSturdy(iblockreader, blockpos5, Direction.UP),
                                Direction.UP)))
                .setValue(DOWN, Boolean.valueOf(
                        this.connectsTo(
                                blockstate5,
                                blockstate5.isFaceSturdy(iblockreader, blockpos6, Direction.DOWN),
                                Direction.DOWN)));

    }

    public BlockState rotate(BlockState state, Rotation p_185499_2_) {
        switch(p_185499_2_) {
            case CLOCKWISE_180:
                return state
                        .setValue(NORTH, state.getValue(SOUTH))
                        .setValue(EAST, state.getValue(WEST))
                        .setValue(SOUTH, state.getValue(NORTH))
                        .setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return state
                        .setValue(NORTH, state.getValue(EAST))
                        .setValue(EAST, state.getValue(SOUTH))
                        .setValue(SOUTH, state.getValue(WEST))
                        .setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90:
                return state
                        .setValue(NORTH, state.getValue(WEST))
                        .setValue(EAST, state.getValue(NORTH))
                        .setValue(SOUTH, state.getValue(EAST))
                        .setValue(WEST, state.getValue(SOUTH));
            default:
                return state;
        }
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        switch(mirror) {
            case LEFT_RIGHT:
                return state
                        .setValue(NORTH, state.getValue(SOUTH))
                        .setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK:
                return state
                        .setValue(EAST, state.getValue(WEST))
                        .setValue(WEST, state.getValue(EAST));
            default:
                return super.mirror(state, mirror);
        }
    }

    private static int indexFor(Direction direction) {
       // LOGGER.info("2D LEFT SHIFT" + direction.get2DDataValue());
        LOGGER.info("3D LEFT SHIFT" + direction.get3DDataValue());
        return 1 << direction.get3DDataValue();
    }

    protected int getAABBIndex(BlockState blockState) {
        return this.stateToIndex.computeIntIfAbsent(blockState, (p_223007_0_) -> {
            LOGGER.info("Ce ma intere: " + p_223007_0_);
            int i = 0;
            if (p_223007_0_.getValue(NORTH)) {
                LOGGER.info(p_223007_0_.getValue(NORTH));
                LOGGER.info("NORTH: indexFor: " + indexFor(Direction.NORTH));
                i |= indexFor(Direction.NORTH);
            }

            if (p_223007_0_.getValue(EAST)) {
                LOGGER.info(p_223007_0_.getValue(EAST));
                LOGGER.info("EAST: indexFor: " + indexFor(Direction.EAST));
                i |= indexFor(Direction.EAST);
            }

            if (p_223007_0_.getValue(SOUTH)) {
                LOGGER.info(p_223007_0_.getValue(SOUTH));
                LOGGER.info("SOUTH: indexFor: " + indexFor(Direction.SOUTH));
                i |= indexFor(Direction.SOUTH);
            }

            if (p_223007_0_.getValue(WEST)) {
                LOGGER.info(p_223007_0_.getValue(WEST));
                LOGGER.info("WEST: indexFor: " + indexFor(Direction.WEST));
                i |= indexFor(Direction.WEST);
            }

            if (p_223007_0_.getValue(UP)) {
                LOGGER.info(p_223007_0_.getValue(UP));
                LOGGER.info("UP: indexFor: " + indexFor(Direction.UP));
                i |= indexFor(Direction.UP);
            }

            if (p_223007_0_.getValue(DOWN)) {
                LOGGER.info(p_223007_0_.getValue(DOWN));
                LOGGER.info("DOWN: indexFor: " + indexFor(Direction.DOWN));
                i |= indexFor(Direction.DOWN);
            }

            return i;
        });
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntities.PIPE_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    public BlockState updateShape(BlockState blockstate1, Direction direction,
                                  BlockState blockstate2, IWorld world,
                                  BlockPos blockpos1, BlockPos blockpos2) {

        if (direction.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
            return blockstate1.setValue(PROPERTY_BY_DIRECTION_H.get(direction),
                    Boolean.valueOf(connectsTo(blockstate2, true, direction)));

        if (direction.getAxis().getPlane() == Direction.Plane.VERTICAL)
            return blockstate1.setValue(PROPERTY_BY_DIRECTION_V.get(direction),
                    Boolean.valueOf(connectsTo(blockstate2, true, direction)));

        return super.updateShape(blockstate1, direction, blockstate2, world, blockpos1, blockpos2);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(NORTH, EAST, WEST, SOUTH, UP, DOWN);
    }

    private void generate_all_VoxelShapes(VoxelShape[] avoxelshape,
                                          VoxelShape voxelShape_N, VoxelShape voxelShape_W, VoxelShape voxelShape_S,
                                          VoxelShape voxelShape_E, VoxelShape voxelShape_D, VoxelShape voxelShape_U)
    {
        int[] v=new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
        int n=8;
        List<int[]> sol_list = new ArrayList<>();

        findSolution(v, n,0, sol_list);

        Map<Integer, VoxelShape> map = new HashMap<Integer, VoxelShape>(2);


        //E D
        //N S
        //W U
        //S N
        //D W
        //U E

        map.put( 0,voxelShape_D);
        map.put( 1,voxelShape_U);
        map.put( 2,voxelShape_N);
        map.put( 3,voxelShape_S);
        map.put( 4,voxelShape_W);
        map.put( 5,voxelShape_E);

        for(int i = 0; i < sol_list.size(); i++)
        {
            VoxelShape s = VoxelShapes.empty();
            for(int j = 2; j < n; j++)
            {
                if (sol_list.get(i)[j] == 1)
                    s = VoxelShapes.or(map.get(j-2));
            }
            avoxelshape[i]=s;
        }

//        for(int i=0;i<sol_list.size();i++)
//        {
//            StringBuffer s = new StringBuffer("");
//            for(int j=0;j<n;j++)
//                s.append(sol_list.get(i)[j]);
//            LOGGER.info("Perm: " + s);
//        }
    }

    private void findSolution(int[] v, int n, int i, List<int[]> sol_list)
    {
        if (i==n)
        {
            if(v[0] != 1 && v[1] != 1)
                sol_list.add(v.clone());
            return;
        }

        for (int k=0;k<2;k++)
        {
            v[i]=k;
            findSolution(v,n,i+1, sol_list);
        }
    }
}

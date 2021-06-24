package com.isoran.bearmode.block;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTree extends Tree {

    public static final int BASE_HEIGHT = 4;
    public static final int FIRST_RANDOM_HEIGHT = 3;
    public static final int SECOND_RANDOM_HEIGHT = 5;

    public static final int LEAVE_RADIUS = 3;
    private static final int LEAVE_OFFSET= 3;
    private static final int LEAVE_HEIGHT = 4;

    public static final BaseTreeFeatureConfig REDWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.REDWOOD_LOG.get().defaultBlockState()),
            new SimpleBlockStateProvider(ModBlocks.REDWOOD_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(
                            FeatureSpread.fixed(LEAVE_RADIUS),
                            FeatureSpread.fixed(LEAVE_OFFSET),
                            LEAVE_HEIGHT),
            new StraightTrunkPlacer(BASE_HEIGHT, FIRST_RANDOM_HEIGHT, SECOND_RANDOM_HEIGHT),
            new TwoLayerFeature(1,0,1)
    )).build();

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_225546_1_, boolean p_225546_2_) {
        return Feature.TREE.configured(REDWOOD_TREE_CONFIG);
    }
}

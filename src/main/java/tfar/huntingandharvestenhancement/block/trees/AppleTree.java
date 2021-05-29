package tfar.huntingandharvestenhancement.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import tfar.huntingandharvestenhancement.world.generation.ModConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class AppleTree extends Tree {

    public AppleTree(){}

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return ModConfiguredFeatures.APPLE;
    }
}

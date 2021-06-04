package tfar.huntingandharvestenhancement.world.generation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import tfar.huntingandharvestenhancement.init.ModBlocks;

public class ModFeatureConfigs {

    public static final BlockClusterFeatureConfig BLACKBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.BLACKBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig BLUEBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.BLUEBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig RASPBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.RASPBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig CRANBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.CRANBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig STRAWBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.STRAWBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig SEABERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.SEABERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();
    public static final BlockClusterFeatureConfig HONEYBERRY_BUSH_PATCH_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.HONEYBERRY_BUSH), SimpleBlockPlacer.PLACER).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).preventProjection().build();

    static class States {
        public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
        public static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        public static final BlockState BLACKBERRY_BUSH = ModBlocks.BLACKBERRY_BUSH.getDefaultState();
        public static final BlockState BLUEBERRY_BUSH = ModBlocks.BLUEBERRY_BUSH.getDefaultState();
        public static final BlockState RASPBERRY_BUSH = ModBlocks.RASPBERRY_BUSH.getDefaultState();
        public static final BlockState CRANBERRY_BUSH = ModBlocks.CRANBERRY_BUSH.getDefaultState();
        public static final BlockState STRAWBERRY_BUSH = ModBlocks.STRAWBERRY_BUSH.getDefaultState();
        public static final BlockState SEABERRY_BUSH = ModBlocks.SEABERRY_BUSH.getDefaultState();
        public static final BlockState HONEYBERRY_BUSH = ModBlocks.HONEYBERRY_BUSH.getDefaultState();
    }
}

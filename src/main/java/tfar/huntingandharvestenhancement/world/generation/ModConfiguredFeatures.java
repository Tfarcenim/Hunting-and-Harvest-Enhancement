package tfar.huntingandharvestenhancement.world.generation;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.init.ModBlocks;

import java.lang.reflect.Field;
import java.util.Locale;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> PATCH_BLACKBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.BLACKBERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.BLUEBERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_RASPBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.RASPBERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.CRANBERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_STRAWBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.STRAWBERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_SEABERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.SEABERRY_BUSH_PATCH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_HONEYBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ModFeatureConfigs.HONEYBERRY_BUSH_PATCH_CONFIG);

    public static final ConfiguredFeature<?, ?> PATCH_BLACKBERRY_DECORATED = PATCH_BLACKBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_DECORATED = PATCH_BLUEBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    public static final ConfiguredFeature<?, ?> PATCH_RASPBERRY_DECORATED = PATCH_RASPBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    //public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_DECORATED = PATCH_CRANBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    public static final ConfiguredFeature<?, ?> PATCH_STRAWBERRY_DECORATED = PATCH_STRAWBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    //public static final ConfiguredFeature<?, ?> PATCH_SEABERRY_DECORATED = PATCH_SEABERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    //public static final ConfiguredFeature<?, ?> PATCH_HONEYBERRY_DECORATED = PATCH_HONEYBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> APPLE = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.APPLE_LOG.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.APPLE_LEAVES.getDefaultState())
            , new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ORANGE = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.APPLE_LOG.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.APPLE_LEAVES.getDefaultState())
            , new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BANANA = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.APPLE_LOG.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.APPLE_LEAVES.getDefaultState())
            , new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> COCONUT = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.APPLE_LOG.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.APPLE_LEAVES.getDefaultState())
            , new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());

    public static void register() {
        for (Field field : ModConfiguredFeatures.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof ConfiguredFeature) {
                    register((field.getName().toLowerCase(Locale.ROOT)), ((ConfiguredFeature<?,?>) o));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HuntingAndHarvestEnhancement.MODID,key), configuredFeature);
    }
}
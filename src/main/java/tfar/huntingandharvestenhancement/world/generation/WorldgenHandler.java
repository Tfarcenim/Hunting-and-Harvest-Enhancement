package tfar.huntingandharvestenhancement.world.generation;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class WorldgenHandler {

    public static void addToBiomes(BiomeLoadingEvent e) {
        BiomeGenerationSettingsBuilder builder = e.getGeneration();
        float temp = e.getClimate().temperature;
        Biome.Category category = e.getCategory();
        if (temp < .5) {
            builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,ModConfiguredFeatures.PATCH_BLACKBERRY_DECORATED);
            builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,ModConfiguredFeatures.PATCH_BLUEBERRY_DECORATED);
            builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,ModConfiguredFeatures.PATCH_RASPBERRY_DECORATED);
        }

        if (category == Biome.Category.FOREST || category == Biome.Category.PLAINS) {
            builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,ModConfiguredFeatures.PATCH_STRAWBERRY_DECORATED);
        }

        if (category == Biome.Category.BEACH) {
            //builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,ModConfiguredFeatures.PATCH_SEABERRY_BUSH);
        }
    }
}

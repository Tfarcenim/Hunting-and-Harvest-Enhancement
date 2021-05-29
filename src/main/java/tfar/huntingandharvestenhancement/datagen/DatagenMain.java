package tfar.huntingandharvestenhancement.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import tfar.huntingandharvestenhancement.datagen.assets.ModBlockstateProvider;
import tfar.huntingandharvestenhancement.datagen.assets.ModItemModelProvider;
import tfar.huntingandharvestenhancement.datagen.data.ModBlockTagsProvider;
import tfar.huntingandharvestenhancement.datagen.data.ModLootTableProvider;

public class DatagenMain {

    public static void start(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();
        if (e.includeServer()) {
            generator.addProvider(new ModLootTableProvider(generator));
            generator.addProvider(new ModBlockTagsProvider(generator, helper));
        }
        if (e.includeClient()) {
            generator.addProvider(new ModBlockstateProvider(generator,helper));
            generator.addProvider(new ModItemModelProvider(generator,helper));
        }
    }
}

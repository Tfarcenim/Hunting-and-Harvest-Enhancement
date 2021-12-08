package tfar.huntingandharvestenhancement.datagen.assets;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.resources.ResourcePackType;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, HuntingAndHarvestEnhancement.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        makeOneLayerItem(ModItems.BLUEBERRIES);
        makeOneLayerItem(ModItems.BLACKBERRIES);
        makeOneLayerItem(ModItems.CRANBERRIES);
        makeOneLayerItem(ModItems.HONEYBERRIES);
        makeOneLayerItem(ModItems.RASPBERRIES);
        makeOneLayerItem(ModItems.SEABERRIES);
        makeOneLayerItem(ModItems.STRAWBERRIES);
        makeOneLayerItem(ModItems.LILY_BLOSSOM);
        makeOneLayerItem(ModItems.ORANGE);
        makeOneLayerItem(ModItems.COCONUT);
        makeOneLayerItem(ModItems.BANANA);

        makeOneLayerBlockItem(ModItems.APPLE_SAPLING);
        makeOneLayerBlockItem(ModItems.ORANGE_SAPLING);

        makeSimpleBlockItem(ModItems.FLOWER_CARPET);
        makeSimpleBlockItem(ModItems.ORANGE_PLANKS);
        makeSimpleBlockItem(ModItems.ORANGE_LOG);
        makeSimpleBlockItem(ModItems.ORANGE_LEAVES);
    }


    protected void makeSimpleBlockItem(Item item) {
        getBuilder(item.getRegistryName().toString())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + item.getRegistryName().getPath())));
    }

    protected void makeOneLayerBlockItem(Item item) {
        String path = item.getRegistryName().getPath();
        if (existingFileHelper.exists(modLoc("block/" + path), ResourcePackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                    .texture("layer0", modLoc("block/" + path));
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void makeOneLayerItem(Item item) {
        String path = item.getRegistryName().getPath();
        if (existingFileHelper.exists(modLoc("item/" + path), ResourcePackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                    .texture("layer0", modLoc("item/" + path));
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void makeSpawnEgg(Item spawnEgg) {
        getBuilder(spawnEgg.getRegistryName().toString()).parent(getExistingFile(mcLoc("item/template_spawn_egg")));
    }
}

package tfar.huntingandharvestenhancement.datagen.assets;

import net.minecraft.block.Block;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.block.CranberryBushBlock;
import tfar.huntingandharvestenhancement.init.ModBlocks;

public class ModBlockstateProvider extends BlockStateProvider {

    public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HuntingAndHarvestEnhancement.MODID, exFileHelper);
    }

    protected static final ResourceLocation SWEET0 = new ResourceLocation("block/sweet_berry_bush_stage0");
    protected static final ResourceLocation SWEET1 = new ResourceLocation("block/sweet_berry_bush_stage1");


    @Override
    protected void registerStatesAndModels() {
        createBushModels(ModBlocks.BLACKBERRY_BUSH);
        createBushModels(ModBlocks.STRAWBERRY_BUSH);
        createBushModels(ModBlocks.SEABERRY_BUSH);
        createBushModels(ModBlocks.RASPBERRY_BUSH);
        createBushModels(ModBlocks.HONEYBERRY_BUSH);
        createBushModels(ModBlocks.BLUEBERRY_BUSH);
        createCranberryModels();
        createCarpetModel(ModBlocks.FLOWER_CARPET);
        logBlock(ModBlocks.ORANGE_LOG);

       // logBlock(ModBlocks.APPLE_LOG);
    }

    protected void createCarpetModel(Block block) {
        String name = block.getRegistryName().getPath();
        getVariantBuilder(block).forAllStates(state -> {
            ModelFile modelFile = models().withExistingParent(name,"block/carpet").texture("wool",new ResourceLocation(HuntingAndHarvestEnhancement.MODID,"block/flower_carpet"));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    protected void createCranberryModels() {
        Block bush = ModBlocks.CRANBERRY_BUSH;
        String name = bush.getRegistryName().getPath();
        getVariantBuilder(bush).forAllStates(state -> {
            int age = state.get(CranberryBushBlock.AGE_0_4);
            ModelFile modelFile;
            if (age == 0) {
                modelFile = models().withExistingParent(name + "_stage_" + age, HuntingAndHarvestEnhancement.MODID+":block/blossom_lilypad");
            } else if (age < 3) {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation("block/sweet_berry_bush_stage" + age));
            } else {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name + "_stage_" + age));
            }
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    protected void createBushModels(Block bush) {
        String name = bush.getRegistryName().getPath();
        getVariantBuilder(bush).forAllStates(state -> {
            int age = state.get(SweetBerryBushBlock.AGE);
            ModelFile modelFile;
            if (age < 2) {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation("block/sweet_berry_bush_stage" + age));
            } else {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name + "_stage_" + age));
            }
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }
}

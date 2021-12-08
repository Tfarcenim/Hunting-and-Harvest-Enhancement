package tfar.huntingandharvestenhancement.datagen.assets;

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.block.CranberryBushBlock;
import tfar.huntingandharvestenhancement.block.FruitBlock;
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
        simpleBlock(ModBlocks.ORANGE_PLANKS);

        createSapling(ModBlocks.APPLE_SAPLING);
        createSapling(ModBlocks.ORANGE_SAPLING);

        ModelFile modelFile = models().withExistingParent("orange_leaves", "minecraft:block/leaves")
                    .texture("all", new ResourceLocation("minecraft:block/oak_leaves"));

        createFruit(ModBlocks.APPLE);
        createFruit(ModBlocks.ORANGE);
        simpleBlock(ModBlocks.ORANGE_LEAVES,modelFile);

        blockstateFromExistingModel(ModBlocks.COCONUT);
        blockstateFromExistingModel(ModBlocks.BANANA);

    }

    protected void blockstateFromExistingModel(Block block) {
        ModelFile modelFile = models().getExistingFile(new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + block.getRegistryName().getPath()));
        getVariantBuilder(block).forAllStates(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder().modelFile(modelFile);
            if (state.hasProperty(HorizontalBlock.HORIZONTAL_FACING)) {
                switch(state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                    case NORTH:
                        builder.rotationY(0);
                        break;
                    case EAST:
                        builder.rotationY(90);
                        break;
                    case SOUTH:
                        builder.rotationY(180);
                        break;
                    case WEST:
                        builder.rotationY(270);
                        break;
                    default:
                        builder.rotationY(0);
                        break;
                }
            }
            return builder.build();
        });
    }

    protected void createSapling(Block plant) {
        String name = plant.getRegistryName().getPath();
        getVariantBuilder(plant).forAllStates(state -> {
            ModelFile modelFile;
            modelFile = models().withExistingParent(name, "block/cross")
                    .texture("cross", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    protected void createFruit(Block plant) {
        String name = plant.getRegistryName().getPath();
        getVariantBuilder(plant).forAllStates(state -> {
            int age = state.get(FruitBlock.AGE);
            ModelFile modelFile;
            if (age < 2) {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name + "_stage_" + age));
            } else {
                modelFile = models().withExistingParent(name + "_stage_" + age, "block/cross")
                        .texture("cross", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name + "_stage_" + age));
            }
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
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
                //lily blossom
                modelFile = models().withExistingParent(name + "_stage_" + age, HuntingAndHarvestEnhancement.MODID+":block/lilypad_base");
            } else if (age < 3) {
                //cranberry, uses vanilla sweetberry stages
                modelFile = models().withExistingParent(name + "_stage_" + age, HuntingAndHarvestEnhancement.MODID+":block/lilypad_base")
                        .texture("top", new ResourceLocation("block/sweet_berry_bush_stage" + age));
            } else {
                //fully grown
                modelFile = models().withExistingParent(name + "_stage_" + age, HuntingAndHarvestEnhancement.MODID+":block/lilypad_base")
                        .texture("top", new ResourceLocation(HuntingAndHarvestEnhancement.MODID, "block/" + name + "_stage_" + age));
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

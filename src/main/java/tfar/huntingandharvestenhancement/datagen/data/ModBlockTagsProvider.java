package tfar.huntingandharvestenhancement.datagen.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.init.ModBlocks;

import javax.annotation.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, HuntingAndHarvestEnhancement.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(BlockTags.LEAVES).add(ModBlocks.ORANGE_LEAVES,ModBlocks.COCONUT_LEAVES,ModBlocks.BANANA_LEAVES);
        getOrCreateBuilder(BlockTags.LOGS_THAT_BURN).add(ModBlocks.ORANGE_LOG,ModBlocks.COCONUT_LOG,ModBlocks.BANANA_LOG);
    }
}

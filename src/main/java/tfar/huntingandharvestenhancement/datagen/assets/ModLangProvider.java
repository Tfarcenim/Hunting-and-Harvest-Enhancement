package tfar.huntingandharvestenhancement.datagen.assets;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.init.ModItems;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen) {
        super(gen, HuntingAndHarvestEnhancement.MODID,"en_us");
    }

    @Override
    protected void addTranslations() {
        for (Item item : ModItems.getAllItems()) {
            add(item.getTranslationKey(), getNameFromItem(item));
        }
        addGroup(ModItems.group,"Hunting and Harvest Enhancement");
    }

    protected void addGroup(ItemGroup group, String name) {
        add(group.getGroupName().getString(),name);
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getTranslationKey().split("\\.")[2].replace("_", " "));
    }
}

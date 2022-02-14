package tfar.huntingandharvestenhancement.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import tfar.huntingandharvestenhancement.HuntingAndHarvestEnhancement;
import tfar.huntingandharvestenhancement.item.CranberryItem;
import tfar.huntingandharvestenhancement.item.LilyBlossomItem;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModItems {

    public static final ItemGroup group = new ItemGroup(HuntingAndHarvestEnhancement.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BLACKBERRIES);
        }
    };

    public static final Item BLACKBERRIES = new BlockNamedItem(ModBlocks.BLACKBERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));
    public static final Item BLUEBERRIES = new BlockNamedItem(ModBlocks.BLUEBERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));
    public static final Item RASPBERRIES = new BlockNamedItem(ModBlocks.RASPBERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));
    public static final Item CRANBERRIES = new CranberryItem((new Item.Properties()).group(group)/*.food(Foods.SWEET_BERRIES)*/);
    public static final Item STRAWBERRIES = new BlockNamedItem(ModBlocks.STRAWBERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));
    public static final Item SEABERRIES = new BlockNamedItem(ModBlocks.SEABERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));
    public static final Item HONEYBERRIES = new BlockNamedItem(ModBlocks.HONEYBERRY_BUSH, (new Item.Properties()).group(group).food(Foods.SWEET_BERRIES));

    public static final Item BANANA = new Item(new Item.Properties().group(group).food(Foods.APPLE));
    public static final Item COCONUT = new Item(new Item.Properties().group(group).food(Foods.APPLE));
    public static final Item ORANGE = new Item(new Item.Properties().group(group).food(Foods.APPLE));

    public static final Item LILY_BLOSSOM = new LilyBlossomItem(new Item.Properties().group(group));

    public static final Item ORANGE_LOG = new BlockItem(ModBlocks.ORANGE_LOG, new Item.Properties().group(group));
    public static final Item PALM_LOG = new BlockItem(ModBlocks.PALM_LOG, new Item.Properties().group(group));
    public static final Item BANANA_LOG = new BlockItem(ModBlocks.BANANA_LOG, new Item.Properties().group(group));

    public static final Item ORANGE_LEAVES = new BlockItem(ModBlocks.ORANGE_LEAVES, new Item.Properties().group(group));
    public static final Item PALM_LEAVES = new BlockItem(ModBlocks.PALM_LEAVES, new Item.Properties().group(group));
    public static final Item BANANA_LEAVES = new BlockItem(ModBlocks.BANANA_LEAVES, new Item.Properties().group(group));

    public static final Item APPLE_SAPLING = new BlockItem(ModBlocks.APPLE_SAPLING, new Item.Properties().group(group));
    public static final Item ORANGE_SAPLING = new BlockItem(ModBlocks.ORANGE_SAPLING, new Item.Properties().group(group));
    public static final Item PALM_SAPLING = new BlockItem(ModBlocks.PALM_SAPLING, new Item.Properties().group(group));
    public static final Item BANANA_SAPLING = new BlockItem(ModBlocks.BANANA_SAPLING, new Item.Properties().group(group));

    public static final Item ORANGE_PLANKS = new BlockItem(ModBlocks.ORANGE_PLANKS, new Item.Properties().group(group));
    public static final Item PALM_PLANKS = new BlockItem(ModBlocks.PALM_PLANKS, new Item.Properties().group(group));
    public static final Item BANANA_PLANKS = new BlockItem(ModBlocks.BANANA_PLANKS, new Item.Properties().group(group));
    public static final Item RED_SORGHUM = new BlockNamedItem(ModBlocks.RED_SORGHUM, new Item.Properties().group(group));
    public static final Item CORN = new BlockNamedItem(ModBlocks.CORN, new Item.Properties().group(group));

    public static final Item JAR = new Item(new Item.Properties().group(group));
    public static final Item MINING_HELMET = new Item(new Item.Properties().group(group));
    public static final Item WOODEN_SHEARS = new ShearsItem(new Item.Properties().maxDamage(119).group(group));

    public static final Item BANANA_FIBER = new Item(new Item.Properties().group(group));
    public static final Item BANANA_STRING = new Item(new Item.Properties().group(group));
    public static final Item BANANA_WOOL = new Item(new Item.Properties().group(group));
    public static final Item HAMMOCK = new BlockItem(ModBlocks.HAMMOCK,new Item.Properties().group(group));


    public static final Item FLOWER_CARPET = new BlockItem(ModBlocks.FLOWER_CARPET,new Item.Properties().group(group));

    private static List<Item> MOD_ITEMS;

    public static void register(RegistryEvent.Register<Item> e) {
        for (Field field : ModItems.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof Item) {
                    e.getRegistry().register(((Item) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    public static List<Item> getAllItems() {
        if (MOD_ITEMS == null) {
            MOD_ITEMS = Arrays.stream(ModItems.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Item.class::isInstance).map(Item.class::cast).collect(Collectors.toList());
        }
        return MOD_ITEMS;
    }
}

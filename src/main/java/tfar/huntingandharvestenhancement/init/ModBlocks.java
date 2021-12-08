package tfar.huntingandharvestenhancement.init;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;
import tfar.huntingandharvestenhancement.block.*;
import tfar.huntingandharvestenhancement.block.trees.AppleTree;
import tfar.huntingandharvestenhancement.block.trees.BananaPalm;
import tfar.huntingandharvestenhancement.block.trees.CoconutPalm;
import tfar.huntingandharvestenhancement.block.trees.OrangeTree;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModBlocks {

    public static final Block BLACKBERRY_BUSH = new BlackberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block BLUEBERRY_BUSH = new StrawberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block RASPBERRY_BUSH = new RaspberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block CRANBERRY_BUSH = new CranberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block STRAWBERRY_BUSH = new StrawberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block SEABERRY_BUSH = new SweetBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));
    public static final Block HONEYBERRY_BUSH = new SweetBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH));

    public static final RotatedPillarBlock ORANGE_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN);
    public static final RotatedPillarBlock COCONUT_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN);
    public static final RotatedPillarBlock BANANA_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN);

    public static final Block ORANGE_LEAVES = createLeavesBlock();
    public static final Block COCONUT_LEAVES = createLeavesBlock();
    public static final Block BANANA_LEAVES = createLeavesBlock();

    public static final Block ORANGE_PLANKS = new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block COCONUT_PLANKS = new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block BANANA_PLANKS = new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final Block APPLE_SAPLING = new SaplingBlock(new AppleTree(),AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT));
    public static final Block ORANGE_SAPLING = new SaplingBlock(new OrangeTree(),AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT));
    public static final Block COCONUT_SAPLING = new SaplingBlock(new CoconutPalm(),AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT));
    public static final Block BANANA_SAPLING = new SaplingBlock(new BananaPalm(),AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT));

    public static final Block FLOWER_CARPET = new FlowerCarpetBlock(AbstractBlock.Properties.create(Material.PLANTS).notSolid());

    public static final Block APPLE = new FruitBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().noDrops().doesNotBlockMovement(), () -> Items.APPLE);
    public static final Block ORANGE = new FruitBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().noDrops().doesNotBlockMovement(), () -> ModItems.ORANGE);
    public static final Block COCONUT = new FruitBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().noDrops().doesNotBlockMovement(), () -> ModItems.ORANGE);
    public static final Block BANANA = new FruitBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().noDrops().doesNotBlockMovement(), () -> ModItems.ORANGE);

    private static List<Block> MOD_BLOCKS;

    public static void register(RegistryEvent.Register<Block> e) {
        for (Field field : ModBlocks.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof Block) {
                    e.getRegistry().register(((Block) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }

    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid()
                .setAllowsSpawn(ModBlocks::allowsSpawnOnLeaves)
                .setSuffocates((a,b,c) -> false).setBlocksVision((a,b,c)-> false));
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    public static List<Block> getAllBlocks() {
        if (MOD_BLOCKS == null) {
            MOD_BLOCKS = Arrays.stream(ModBlocks.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Block.class::isInstance).map(Block.class::cast).collect(Collectors.toList());
        }
        return MOD_BLOCKS;
    }
}

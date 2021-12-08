package tfar.huntingandharvestenhancement;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tfar.huntingandharvestenhancement.datagen.DatagenMain;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModFoliagePlacerTypes;
import tfar.huntingandharvestenhancement.init.ModItems;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;
import tfar.huntingandharvestenhancement.world.generation.ModConfiguredFeatures;
import tfar.huntingandharvestenhancement.world.generation.WorldgenHandler;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HuntingAndHarvestEnhancement.MODID)
public class HuntingAndHarvestEnhancement {

    public static final String MODID = "huntingandharvestenhancement";

    public HuntingAndHarvestEnhancement() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Block.class, ModBlocks::register);
        bus.addGenericListener(Item.class, ModItems::register);
        bus.addGenericListener(TreeDecoratorType.class, ModTreeDecoratorTypes::register);
        bus.addGenericListener(FoliagePlacerType.class, ModFoliagePlacerTypes::register);
        bus.addListener(DatagenMain::start);
        bus.addListener(this::setup);

        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(this::client);
            bus.addListener(this::color);
        }

        MinecraftForge.EVENT_BUS.addListener(WorldgenHandler::addToBiomes);
        MinecraftForge.EVENT_BUS.addListener(this::temp);
    }

    private void temp(PlayerInteractEvent.RightClickBlock e) {
        PlayerEntity player = e.getPlayer();
        if (!player.world.isRemote && player.getHeldItemMainhand().getItem() == Items.STICK) {
            if (player.world.getBlockState(e.getPos()).getBlock().isIn(BlockTags.LOGS)) {
                BlockPos.Mutable mutable = new BlockPos.Mutable();
                mutable.setPos(e.getPos());
                while ((player.world.getBlockState(mutable).getBlock()).isIn(BlockTags.LOGS)) {
                    mutable.move(Direction.UP);
                }
                BlockPos start = mutable.toImmutable();
                List<BlockPos> leaves = new ArrayList<>();
                for (int x = -7; x < 7; x++) {
                    for (int y = -2; y < 2; y++) {
                        for (int z = -7; z < 7; z++) {
                            mutable.setAndOffset(start,x,y,z);
                            if (player.world.getBlockState(mutable).isIn(BlockTags.LEAVES)) {
                                leaves.add(new BlockPos(x,y,z));
                            }
                        }
                    }
                }
                for (BlockPos pos : leaves) {
                    System.out.println("new Vector3i("+pos.getX()+","+pos.getY()+","+pos.getZ()+"),");
                }
                System.out.println(leaves.size());
            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModConfiguredFeatures.register();
    }

    private void client(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.BLACKBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.STRAWBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLUEBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.SEABERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.HONEYBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.CRANBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.RASPBERRY_BUSH, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.FLOWER_CARPET,RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.APPLE,RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE,RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.BANANA,RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.APPLE_SAPLING,RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE_SAPLING,RenderType.getCutoutMipped());
    }


    private void color(ColorHandlerEvent.Block e) {
        BlockColors colors = e.getBlockColors();
        colors.register((state, reader, pos, color) -> reader != null && pos != null ? 0x208030 : 0x71c35c, ModBlocks.CRANBERRY_BUSH);
        colors.register((state, reader, pos, color) -> reader != null && pos != null ?
                BiomeColors.getFoliageColor(reader, pos) | 0x00ff00 : FoliageColors.getDefault(), ModBlocks.ORANGE_LEAVES);
    }
}

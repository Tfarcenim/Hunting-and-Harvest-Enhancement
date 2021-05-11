package tfar.huntingandharvestenhancement;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tfar.huntingandharvestenhancement.datagen.DatagenMain;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.world.generation.ModConfiguredFeatures;
import tfar.huntingandharvestenhancement.init.ModItems;
import tfar.huntingandharvestenhancement.world.generation.WorldgenHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HuntingAndHarvestEnhancement.MODID)
public class HuntingAndHarvestEnhancement {

    public static final String MODID = "huntingandharvestenhancement";

    public HuntingAndHarvestEnhancement() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addGenericListener(Block.class, ModBlocks::register);
        bus.addGenericListener(Item.class, ModItems::register);
        bus.addListener(DatagenMain::start);
        bus.addListener(this::setup);

        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(this::client);
        }

        MinecraftForge.EVENT_BUS.addListener(WorldgenHandler::addToBiomes);
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

        BlockColors blockColors = Minecraft.getInstance().getBlockColors();

        blockColors.register((state, reader, pos, color) -> reader != null && pos != null ? 2129968 : 7455580, ModBlocks.CRANBERRY_BUSH);

    }
}

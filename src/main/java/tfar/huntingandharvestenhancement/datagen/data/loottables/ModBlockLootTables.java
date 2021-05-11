package tfar.huntingandharvestenhancement.datagen.data.loottables;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModItems;

public class ModBlockLootTables extends BlockLootTables {

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    @Override
    protected void addTables() {
        registerDropSelfLootTable(ModBlocks.APPLE_LOG);
        registerDropSelfLootTable(ModBlocks.ORANGE_LOG);
        registerDropSelfLootTable(ModBlocks.COCONUT_LOG);
        registerDropSelfLootTable(ModBlocks.BANANA_LOG);

        //this.registerLootTable(ModBlocks.APPLE_LEAVES, leaves -> droppingWithChancesAndSticks(leaves, ModBlocks.APPLE_SAPLING, DEFAULT_SAPLING_DROP_RATES));
        this.registerLootTable(ModBlocks.ORANGE_LEAVES, leaves -> droppingWithChancesAndSticks(leaves, ModBlocks.ORANGE_SAPLING, DEFAULT_SAPLING_DROP_RATES));
        this.registerLootTable(ModBlocks.BANANA_LEAVES, leaves -> droppingWithChancesAndSticks(leaves, ModBlocks.BANANA_SAPLING, DEFAULT_SAPLING_DROP_RATES));
        this.registerLootTable(ModBlocks.COCONUT_LEAVES, leaves -> droppingWithChancesAndSticks(leaves, ModBlocks.COCONUT_SAPLING, DEFAULT_SAPLING_DROP_RATES));

        registerBushLootTable(ModBlocks.BLACKBERRY_BUSH,ModItems.BLACKBERRIES);
        registerBushLootTable(ModBlocks.BLUEBERRY_BUSH,ModItems.BLUEBERRIES);
        registerBushLootTable(ModBlocks.CRANBERRY_BUSH,ModItems.CRANBERRIES);
        registerBushLootTable(ModBlocks.HONEYBERRY_BUSH,ModItems.HONEYBERRIES);
        registerBushLootTable(ModBlocks.RASPBERRY_BUSH,ModItems.RASPBERRIES);
        registerBushLootTable(ModBlocks.SEABERRY_BUSH,ModItems.SEABERRIES);
        registerBushLootTable(ModBlocks.STRAWBERRY_BUSH,ModItems.STRAWBERRIES);

        registerDropSelfLootTable(ModBlocks.APPLE_SAPLING);
        registerDropSelfLootTable(ModBlocks.ORANGE_SAPLING);
        registerDropSelfLootTable(ModBlocks.COCONUT_SAPLING);
        registerDropSelfLootTable(ModBlocks.BANANA_SAPLING);
        registerDropSelfLootTable(ModBlocks.FLOWER_CARPET);

    }

    protected void registerBushLootTable(Block bush, Item berry) {
        this.registerLootTable(bush, (sweetberry) -> withExplosionDecay(sweetberry, LootTable.builder()
                .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(bush)
                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(SweetBerryBushBlock.AGE, 3)))
                        .addEntry(ItemLootEntry.builder(berry)).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F)))
                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))).addLootPool(LootPool.builder()
                        .acceptCondition(BlockStateProperty.builder(bush)
                                .fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(SweetBerryBushBlock.AGE, 2)))
                        .addEntry(ItemLootEntry.builder(berry)).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))
                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.getAllBlocks();
    }
}

package tfar.huntingandharvestenhancement.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tfar.huntingandharvestenhancement.block.CranberryBushBlock;
import tfar.huntingandharvestenhancement.init.ModBlocks;

public class LilyBlossomItem extends Item {
    public LilyBlossomItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        //   if (!context.canPlace()) {
        //        return ActionResultType.FAIL;
        //   }
        //BlockState blockstate = this.getStateForPlacement(context);
        BlockPos blockpos = context.getPos();
        World world = context.getWorld();
        PlayerEntity playerentity = context.getPlayer();
        ItemStack itemstack = context.getItem();
        BlockState blockState = world.getBlockState(blockpos);
        Block block = blockState.getBlock();
        if (block == Blocks.LILY_PAD) {
            world.setBlockState(blockpos,ModBlocks.CRANBERRY_BUSH.getDefaultState().with(CranberryBushBlock.AGE_0_4,0));
            block.onBlockPlacedBy(world, blockpos, blockState, playerentity, itemstack);
            if (playerentity instanceof ServerPlayerEntity) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos, itemstack);
            }
        }

        SoundType soundtype = blockState.getSoundType(world, blockpos, context.getPlayer());
        //world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        if (playerentity == null || !playerentity.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }
}

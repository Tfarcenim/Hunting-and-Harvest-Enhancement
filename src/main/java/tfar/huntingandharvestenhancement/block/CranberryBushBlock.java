package tfar.huntingandharvestenhancement.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tfar.huntingandharvestenhancement.init.ModItems;

import java.util.Random;

public class CranberryBushBlock extends BushBlock implements IGrowable {

    public static final IntegerProperty AGE_0_4 = IntegerProperty.create("age", 0, 4);

    public CranberryBushBlock(Properties properties) {
        super(properties);
    }

    public ActionResultType onBlockActivated(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int age = state.get(AGE_0_4);
        boolean fullyGrown = age == 4;
        if (!fullyGrown && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (age > 1) {
            int j = 1 + level.rand.nextInt(2);
            spawnAsEntity(level, pos, new ItemStack(ModItems.CRANBERRIES, j + (fullyGrown ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + level.rand.nextFloat() * 0.4F);
            level.setBlockState(pos, state.with(AGE_0_4, 1), 2);
            return ActionResultType.func_233537_a_(level.isRemote);
        } else {
            return super.onBlockActivated(state, level, pos, player, handIn, hit);
        }
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    public boolean ticksRandomly(BlockState state) {
        int age = state.get(AGE_0_4);
        return age > 0 && age < 4;
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.get(AGE_0_4);
        if (i < 4 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state,random.nextInt(5) == 0)) {
            worldIn.setBlockState(pos, state.with(AGE_0_4, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        FluidState fluidstate = worldIn.getFluidState(pos);
        FluidState fluidstate1 = worldIn.getFluidState(pos.up());
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(AGE_0_4);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE_0_4) < 4;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.get(AGE_0_4) + 1);
        worldIn.setBlockState(pos, state.with(AGE_0_4, i), 2);
    }
}

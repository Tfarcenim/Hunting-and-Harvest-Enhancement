package tfar.huntingandharvestenhancement.decorators;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import tfar.huntingandharvestenhancement.block.FruitBlock;

import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class PalmFruitDecorator extends TreeDecorator {

    protected final float prob;

    public PalmFruitDecorator(float probability) {
        this.prob = probability;
    }


    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> logPos, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
        if (!(rand.nextFloat() >= this.prob)) {
            BlockPos top = logPos.get(logPos.size() - 2);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                Direction direction1 = direction.getOpposite();
                BlockPos blockpos = top.add(direction1.getXOffset(), 0, direction1.getZOffset());
                if (Feature.isAirAt(world, blockpos)) {
                    BlockState blockstate = getBlock().getDefaultState().with(FruitBlock.AGE, rand.nextInt(3));//.with(CocoaBlock.HORIZONTAL_FACING, direction);
                    this.func_227423_a_(world, blockpos, blockstate, p_225576_5_, p_225576_6_);
                }
            }
        }
    }
    public abstract Block getBlock();
}
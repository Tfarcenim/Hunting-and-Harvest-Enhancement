package tfar.huntingandharvestenhancement.decorators;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class FruitDecorator extends TreeDecorator {

    protected final float probability;

    public FruitDecorator(float probability) {
        this.probability = probability;
    }

    //mojang: place
    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> logPos, List<BlockPos> foliagePos, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
        if (!(rand.nextFloat() >= this.probability)) {
            foliagePos.stream().filter(pos -> Feature.isAirAt(world, pos.down())).forEach(pos -> {
                BlockPos blockpos = pos.down();
                //place block
                this.func_227423_a_(world, blockpos,getBlockstate(rand), p_225576_5_, p_225576_6_);
            });
        }
    }

    protected abstract BlockState getBlockstate(Random rand);

}

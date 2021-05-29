package tfar.huntingandharvestenhancement.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.block.AppleBlock;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class AppleDecorator extends TreeDecorator {
    public static final Codec<AppleDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(AppleDecorator::new, (p_236868_0_) -> p_236868_0_.probability)
            .codec();

    private final float probability;

    public AppleDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> getDecoratorType() {
        return ModTreeDecoratorTypes.APPLE;
    }

    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> posList1, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
        if (!(rand.nextFloat() >= this.probability)) {
            int y = posList1.get(0).getY();
            posList1.stream().filter(pos -> pos.getY() - y <= 2).forEach(pos -> {
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    if (true) {
                        Direction direction1 = direction.getOpposite();
                        BlockPos blockpos = pos.add(direction1.getXOffset(), 0, direction1.getZOffset());
                        if (Feature.isAirAt(world, blockpos)) {
                            BlockState blockstate = ModBlocks.APPLE.getDefaultState().with(AppleBlock.AGE, rand.nextInt(3));
                            this.func_227423_a_(world, blockpos, blockstate, p_225576_5_, p_225576_6_);
                        }
                    }
                }
            });
        }
    }
}

package tfar.huntingandharvestenhancement.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.block.FruitBlock;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

import java.util.Random;

public class AppleDecorator extends FruitDecorator {
    public static final Codec<AppleDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("fruit")
            .xmap(AppleDecorator::new, decorator -> decorator.density)
            .codec();

    public AppleDecorator(float probability) {
        super(probability);
    }

    @Override
    protected TreeDecoratorType<?> getDecoratorType() {
        return ModTreeDecoratorTypes.APPLE;
    }

    @Override
    protected BlockState getBlockstate(Random rand) {
        return  ModBlocks.APPLE.getDefaultState().with(FruitBlock.AGE, rand.nextInt(3));
    }
}

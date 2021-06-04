package tfar.huntingandharvestenhancement.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.block.FruitBlock;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

import java.util.Random;

public class OrangeDecorator extends FruitDecorator {
    public static final Codec<OrangeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("fruit")
            .xmap(OrangeDecorator::new, decorator -> decorator.probability)
            .codec();

    public OrangeDecorator(float probability) {
        super(probability);
    }

    @Override
    protected BlockState getBlockstate(Random rand) {
        return ModBlocks.ORANGE.getDefaultState().with(FruitBlock.AGE, rand.nextInt(3));
    }

    @Override
    protected TreeDecoratorType<?> getDecoratorType() {
        return ModTreeDecoratorTypes.ORANGE;
    }
}

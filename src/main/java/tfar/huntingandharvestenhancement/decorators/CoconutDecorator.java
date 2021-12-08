package tfar.huntingandharvestenhancement.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

public class CoconutDecorator extends PalmFruitDecorator {
    public static final Codec<CoconutDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability")
            .xmap(CoconutDecorator::new, (coconutDecorator) -> coconutDecorator.prob).codec();

    public CoconutDecorator(float probability) {
        super(probability);
    }


    protected TreeDecoratorType<?> getDecoratorType() {
        return ModTreeDecoratorTypes.COCONUT;
    }

    @Override
    public Block getBlock() {
        return ModBlocks.COCONUT;
    }
}
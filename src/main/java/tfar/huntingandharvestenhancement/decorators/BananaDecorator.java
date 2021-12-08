package tfar.huntingandharvestenhancement.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import tfar.huntingandharvestenhancement.init.ModBlocks;
import tfar.huntingandharvestenhancement.init.ModTreeDecoratorTypes;

public class BananaDecorator extends PalmFruitDecorator {
    public static final Codec<BananaDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability")
            .xmap(BananaDecorator::new, (coconutDecorator) -> coconutDecorator.prob).codec();

    public BananaDecorator(float probability) {
        super(probability);
    }


    protected TreeDecoratorType<?> getDecoratorType() {
        return ModTreeDecoratorTypes.BANANA;
    }

    @Override
    public Block getBlock() {
        return ModBlocks.BANANA;
    }
}
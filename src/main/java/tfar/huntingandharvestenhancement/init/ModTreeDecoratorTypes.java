package tfar.huntingandharvestenhancement.init;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.event.RegistryEvent;
import tfar.huntingandharvestenhancement.decorators.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModTreeDecoratorTypes {

    public static final TreeDecoratorType<AppleDecorator> APPLE = new TreeDecoratorType<>(AppleDecorator.CODEC);
    public static final TreeDecoratorType<OrangeDecorator> ORANGE = new TreeDecoratorType<>(OrangeDecorator.CODEC);
    public static final TreeDecoratorType<CoconutDecorator> COCONUT = new TreeDecoratorType<>(CoconutDecorator.CODEC);
    public static final TreeDecoratorType<BananaDecorator> BANANA = new TreeDecoratorType<>(BananaDecorator.CODEC);

    private static List<TreeDecoratorType<?>> MOD_TREE_DECORATOR_TYPES;

    public static void register(RegistryEvent.Register<TreeDecoratorType<?>> e) {
        for (Field field : ModTreeDecoratorTypes.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof TreeDecoratorType) {
                    e.getRegistry().register(((TreeDecoratorType<?>) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    public static List<TreeDecoratorType<?>> getAllTreeDecoratorTypes() {
        if (MOD_TREE_DECORATOR_TYPES == null) {
            MOD_TREE_DECORATOR_TYPES = Arrays.stream(ModTreeDecoratorTypes.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).filter(TreeDecoratorType.class::isInstance).map(obj -> (TreeDecoratorType<?>) obj).collect(Collectors.toList());
        }
        return MOD_TREE_DECORATOR_TYPES;
    }

}

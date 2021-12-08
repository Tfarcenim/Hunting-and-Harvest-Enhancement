package tfar.huntingandharvestenhancement.init;

import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import tfar.huntingandharvestenhancement.world.generation.PalmFoliagePlacer;

import java.lang.reflect.Field;
import java.util.Locale;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM = new FoliagePlacerType<>(PalmFoliagePlacer.CODEC);

    public static void register(RegistryEvent.Register<FoliagePlacerType<?>> e) {
        for (Field field : ModFoliagePlacerTypes.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof FoliagePlacerType) {
                    e.getRegistry().register(((FoliagePlacerType<?>) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}

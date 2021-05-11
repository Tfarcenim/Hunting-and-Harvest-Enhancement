package tfar.huntingandharvestenhancement.datagen.data.loottables;

import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;

public class ModEntityLootTables extends EntityLootTables {

    @Override
    protected void addTables() {

    }

    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        return new ArrayList<>();
    }
}

package net.hadences.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MimirModEntities {

    public static final EntityType<TestEntity> TEST_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            "mimir:test_entity",
            EntityType.Builder.create(TestEntity::new, SpawnGroup.MISC).build());

}

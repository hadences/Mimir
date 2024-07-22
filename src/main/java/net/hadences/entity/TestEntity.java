package net.hadences.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class TestEntity extends IKEntity {
    protected TestEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
}

package net.hadences.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class IKEntity extends LivingEntity {
    private static final TrackedData<Vector3f> TARGET_POSITION = DataTracker.registerData(IKEntity.class, TrackedDataHandlerRegistry.VECTOR3F);
    private final DefaultedList<ItemStack> armorItems = DefaultedList.ofSize(0, ItemStack.EMPTY);

    protected IKEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TARGET_POSITION, new Vector3f());
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return armorItems;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    public static DefaultAttributeContainer.Builder createIKAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D);
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    public Vec3d getTargetPosition() {
        return new Vec3d(this.dataTracker.get(TARGET_POSITION));
    }

    public void setTargetPosition(Vec3d targetPosition) {
        this.dataTracker.set(TARGET_POSITION, new Vector3f((float) targetPosition.x, (float) targetPosition.y, (float) targetPosition.z));
    }

}


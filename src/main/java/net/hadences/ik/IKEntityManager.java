package net.hadences.ik;

import java.util.ArrayList;
import java.util.List;

public class IKEntityManager {
    private List<IKEntity> entities = new ArrayList<>();

    public void registerEntity(IKEntity entity) {
        entities.add(entity);
    }

    public void unregisterEntity(IKEntity entity) {
        entities.remove(entity);
    }

    public void tickAll() {
        for (IKEntity entity : entities) {
            entity.tick();
        }
    }
}

package net.hadences.entity;

import net.hadences.Mimir;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MimirModEntityModelLayers {
    public static final EntityModelLayer TEST_ENTITY_LAYER =
            new EntityModelLayer(new Identifier(Mimir.MOD_ID, "test_entity"), "main");

}

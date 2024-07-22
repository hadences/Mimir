package net.hadences;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hadences.entity.client.TestEntityModel;
import net.hadences.entity.client.TestEntityRenderer;

import static net.hadences.entity.MimirModEntities.TEST_ENTITY;
import static net.hadences.entity.MimirModEntityModelLayers.TEST_ENTITY_LAYER;

public class MimirClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(TEST_ENTITY, TestEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(TEST_ENTITY_LAYER, TestEntityModel::getTexturedModelData);



	}
}

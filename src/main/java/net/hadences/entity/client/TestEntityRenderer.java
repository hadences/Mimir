package net.hadences.entity.client;

import net.hadences.Mimir;
import net.hadences.entity.IKEntity;
import net.hadences.entity.MimirModEntityModelLayers;
import net.hadences.entity.TestEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends LivingEntityRenderer<TestEntity, TestEntityModel<TestEntity>>{
    private static Identifier TEXTURE = new Identifier(Mimir.MOD_ID, "textures/entity/test.png");
    public TestEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TestEntityModel<>(context.getPart(MimirModEntityModelLayers.TEST_ENTITY_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(TestEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(TestEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}

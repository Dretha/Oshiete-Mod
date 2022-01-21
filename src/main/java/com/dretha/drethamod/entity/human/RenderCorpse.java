package com.dretha.drethamod.entity.human;

import layers.LayerClothes;
import layers.LayerKakugan;
import layers.LayerShard;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderCorpse extends RenderLiving<EntityCorpse> {

    public RenderCorpse(RenderManager manager) {
        super(manager, new ModelHuman(0.0F, true), 0.0F);
        this.addLayer(new LayerShard(this));
        this.addLayer(new LayerKakugan(this));
        this.addLayer(new LayerClothes(this));
        //this.addLayer(new EntityLayerMask(this));
    }

    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityCorpse corpse) {
        return corpse.getSkinTexture();
    }

    @Override
    protected void applyRotations(EntityCorpse corpse, float huiznaetchto, float rotationYaw, float partialTicks) {
        super.applyRotations(corpse, huiznaetchto, rotationYaw, partialTicks);

        GlStateManager.rotate(corpse.rotationAngle, 0.0F, 1.0F, 0.0F); //
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0, -1, 0);
        GlStateManager.translate(0, 0, -0.13);
    }

    public static class Factory implements IRenderFactory<EntityCorpse> {
        @Override
        public Render<? super EntityCorpse> createRenderFor(RenderManager manager) {
            return new RenderCorpse(manager);
        }
    }
}

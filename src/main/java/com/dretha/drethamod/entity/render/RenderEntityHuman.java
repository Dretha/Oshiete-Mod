package com.dretha.drethamod.entity.render;

import javax.annotation.Nonnull;

import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.main.Main;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;



public class RenderEntityHuman extends RenderLiving<EntityHuman>{
    private ResourceLocation mobTexture = new ResourceLocation("dm:textures/entity/skin_human.png");

    public RenderEntityHuman(RenderManager manager) {
        super(manager, new ModelBiped(), 0.5F);
    }
    public static Factory FACTORY = new Factory();
    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityHuman entity) {
        return mobTexture;
    }
    public static class Factory implements IRenderFactory<EntityHuman> {
        @Override
        public Render<? super EntityHuman> createRenderFor(RenderManager manager) {
            return new RenderEntityHuman(manager);
        }
    }
}
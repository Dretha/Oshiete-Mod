package com.dretha.drethamod.entity.render;

import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.human.ModelHuman;
import layers.LayerClothes;
import layers.entity.EntityLayerFlame;
import layers.entity.EntityLayerKagune;
import layers.entity.EntityLayerKakugan;
import layers.entity.EntityLayerShard;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;


public class RenderHuman extends RenderLiving<EntityHuman>{

    public RenderHuman(RenderManager manager) {
        //super(manager, new ModelBiped(), 0.5F);
        super(manager, new ModelHuman(0.0F, true), 0.5F);
        this.addLayer(new EntityLayerShard(this));
        this.addLayer(new EntityLayerKakugan(this));
        this.addLayer(new EntityLayerKagune(this));
        this.addLayer(new EntityLayerFlame(this));
        this.addLayer(new LayerClothes(this));
        //this.addLayer(new EntityLayerMask(this));
    }
    
    public static Factory FACTORY = new Factory();
    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityHuman entity) {
        return entity.getSkinTexture();
    }
    
    public static class Factory implements IRenderFactory<EntityHuman> {
        @Override
        public Render<? super EntityHuman> createRenderFor(RenderManager manager) {
            return new RenderHuman(manager);
        }
    }
}
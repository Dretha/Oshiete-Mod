package com.dretha.drethamod.entity.render;

import com.dretha.drethamod.entity.projectile.EntityEnderArrow;
import com.dretha.drethamod.reference.Reference;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEnderArrow extends RenderArrow<EntityEnderArrow>{

    public RenderEnderArrow(RenderManager manager) {
        super(manager);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityEnderArrow entity) {
        return new ResourceLocation(Reference.MODID + ":textures/entity/projectile/shard.png");
    }
}

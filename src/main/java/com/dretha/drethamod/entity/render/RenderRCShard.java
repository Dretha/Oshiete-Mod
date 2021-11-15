package com.dretha.drethamod.entity.render;

import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderRCShard extends RenderArrow<EntityRCShard>{

    public RenderRCShard(RenderManager manager) {
        super(manager);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityRCShard entity) {
    	return new ResourceLocation(Reference.MODID + ":textures/entity/projectile/shard.png");
    }
    
    public static class Factory implements IRenderFactory<EntityRCShard> {
        @Override
        public Render<? super EntityRCShard> createRenderFor(RenderManager manager) {
            return new RenderRCShard(manager);
        }
    }
}
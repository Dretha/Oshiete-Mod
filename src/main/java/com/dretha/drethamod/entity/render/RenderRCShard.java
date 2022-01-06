package com.dretha.drethamod.entity.render;

import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRCShard extends RenderArrow<EntityRCShard> {

	public RenderRCShard(RenderManager manager) {
		super(manager);
	}
		
	@Override
	protected ResourceLocation getEntityTexture(EntityRCShard entity) {
		return new ResourceLocation(Reference.MODID + ":textures/entity/projectile/shard.png");
	}
}
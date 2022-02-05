package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.client.geckolib.kagunes.entity.Entity3011;
import com.dretha.drethamod.client.geckolib.kagunes.entity.Entity3012;
import com.dretha.drethamod.client.geckolib.kagunes.entity.Entity401;
import com.dretha.drethamod.client.geckolib.kagunes.entity.Entity201;
import com.dretha.drethamod.client.geckolib.kagunes.entity.Entity101;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public enum KaguneHolder {
	kagune101(Entity101.class) {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity101(master);}
	},
	kagune201(Entity201.class) {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity201(master);}
	},
	kagune3011(Entity3011.class) {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity3011(master);}
	},
	kagune3012(Entity3012.class) {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity3012(master);}
	},
	kagune401(Entity401.class) {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity401(master);}
	};

	public abstract EntityKagune getEntity(EntityLivingBase master);

	public GeoEntityRenderer<EntityKagune> getRender(RenderManager manager, String texture) {
		return new RenderKagune(manager, new ModelKagune(this.getModelID(), texture, this.getAnimationID()));
	}

	public final Class<? extends EntityKagune> entityClass;

	KaguneHolder(Class<? extends EntityKagune> entityClass) {
		this.entityClass = entityClass;
	}

	public static void registerEntities() {
		for (KaguneHolder kagune : KaguneHolder.values()) {
			RenderingRegistry.registerEntityRenderingHandler(kagune.entityClass, kagune.getRender(Minecraft.getMinecraft().getRenderManager(), null));
		}
	}

	String getAnimationID() {
		return "animations/kagune/" + this + ".animation.json";
	}
	String getModelID() {
		return "geo/kagune/" + this + ".geo.json";
	}
}

package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401.Entity401;
import com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401.Model401;
import com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401.Render401;
import com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201.Entity201;
import com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201.Model201;
import com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201.Render201;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Entity301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Model301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Render301;
import com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101.Entity101;
import com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101.Model101;
import com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101.Render101;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public enum EnumKagune {
	KAGUNE101 {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity101(master);}
		public AnimatedGeoModel getModel(String texture) {return new Model101(texture);}
		public GeoEntityRenderer getRender(RenderManager manager) {return new Render101(manager);}
	},
	KAGUNE201 {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity201(master);}
		public AnimatedGeoModel getModel(String texture) {return new Model201(texture);}
		public GeoEntityRenderer getRender(RenderManager manager) {return new Render201(manager);}
	},
	KAGUNE301 {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity301(master);}
		public AnimatedGeoModel getModel(String texture) {return new Model301(texture);}
		public GeoEntityRenderer getRender(RenderManager manager) {return new Render301(manager);}
	},
	KAGUNE401 {
		public EntityKagune getEntity(EntityLivingBase master) {return new Entity401(master);}
		public AnimatedGeoModel getModel(String texture) {return new Model401(texture);}
		public GeoEntityRenderer getRender(RenderManager manager) {return new Render401(manager);}
	};
	
	public abstract EntityKagune getEntity(EntityLivingBase master);
	public abstract AnimatedGeoModel getModel(String texture);
	public abstract GeoEntityRenderer getRender(RenderManager manager);
	
	public static void registerEntities() {
		RenderingRegistry.registerEntityRenderingHandler((Class)Entity101.class, manager -> new Render101(manager));
        RenderingRegistry.registerEntityRenderingHandler((Class)Entity201.class, manager -> new Render201(manager));
        RenderingRegistry.registerEntityRenderingHandler((Class)Entity301.class, manager -> new Render301(manager));
        RenderingRegistry.registerEntityRenderingHandler((Class)Entity401.class, manager -> new Render401(manager));
	}
}

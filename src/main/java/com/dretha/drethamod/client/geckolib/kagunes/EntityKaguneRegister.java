package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401.Entity401;
import com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401.Render401;
import com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201.Entity201;
import com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201.Render201;
import com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101.Entity101;
import com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101.Render101;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityKaguneRegister {
	public static void register() {
		RenderingRegistry.registerEntityRenderingHandler((Class)Entity101.class, manager -> new Render101(manager));
        RenderingRegistry.registerEntityRenderingHandler((Class)Entity201.class, manager -> new Render201(manager));
        
        RenderingRegistry.registerEntityRenderingHandler((Class)Entity401.class, manager -> new Render401(manager));
	}
}

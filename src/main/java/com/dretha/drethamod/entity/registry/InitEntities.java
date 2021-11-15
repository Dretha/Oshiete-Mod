package com.dretha.drethamod.entity.registry;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneGeoRenderer;
import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.entity.projectile.EntityEnderArrow;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.entity.render.RenderEnderArrow;
import com.dretha.drethamod.entity.render.RenderEntityHuman;
import com.dretha.drethamod.entity.render.RenderRCShard;
import com.dretha.drethamod.main.Main;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = "dm")
public class InitEntities {
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityHuman.class, (IRenderFactory) new RenderEntityHuman.Factory());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRCShard.class, (IRenderFactory) new RenderRCShard.Factory());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityKagune.class, manager -> new KaguneGeoRenderer(manager, null, null, null));
	}
	
	public static void init() {
		EntityRegistry.addSpawn(EntityHuman.class, 3, 1, 3, EnumCreatureType.CREATURE, Biomes.PLAINS, Biomes.FOREST, Biomes.JUNGLE, Biomes.DESERT, Biomes.EXTREME_HILLS, Biomes.SWAMPLAND, Biomes.TAIGA);
	}

    private static int ID = 0;

    public static EntityEntry HUMAN = EntityEntryBuilder
            .create()
            .entity(EntityHuman.class)
            .name("Human")
            .id("entity_human", ID++)
            .egg(0xff4040, 0xd891ef)
            .tracker(160, 2, false)
            .build();
    
    public static EntityEntry RCSHARD = EntityEntryBuilder
            .create()
            .entity(EntityRCShard.class)
            .name("RCShard")
            .id("rc_shard", ID++)
            .tracker(160, 2, false)
            .build();

    public static EntityEntry KAGUNE = EntityEntryBuilder
            .create()
            .entity(EntityKagune.class)
            .name("KAGUNE")
            .id("kagune_bb1", ID++)
            .egg(0xff4040, 0xd891ef)
            .tracker(160, 2, false)
            .build();
    
    @SubscribeEvent
    public static void registryEntity(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll(
                HUMAN,
                RCSHARD,
                KAGUNE
        );
    }
}

package com.dretha.drethamod.init;

import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.entity.human.RenderCorpse;
import com.dretha.drethamod.entity.projectile.EntityBullet;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.entity.render.RenderBullet;
import com.dretha.drethamod.entity.render.RenderHuman;
import com.dretha.drethamod.entity.render.RenderRCShard;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = "dm")
public class InitEntities {
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        EnumKagune.registerEntities();

        registerNormalEntity("human", EntityHuman.class, 0xffffff, 0x000000);
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, RenderHuman::new);

        registerBugArrow("rcshard", EntityRCShard.class);
        RenderingRegistry.registerEntityRenderingHandler(EntityRCShard.class, RenderRCShard::new);

        registerTechEntity("corpse", EntityCorpse.class);
        RenderingRegistry.registerEntityRenderingHandler(EntityCorpse.class, RenderCorpse::new);

        registerNormalThrow("bullet", EntityBullet.class);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet::new);
	}
	
	public static void init() {
		EntityRegistry.addSpawn(EntityHuman.class, 3, 1, 3, EnumCreatureType.CREATURE, Biomes.PLAINS, Biomes.FOREST, Biomes.JUNGLE, Biomes.DESERT, Biomes.EXTREME_HILLS, Biomes.SWAMPLAND, Biomes.TAIGA);
        //LootTableList.register(EntityCorpse.LOOT);
    }

    private static int ID = 0;

    private static void registerTechEntity(String name, Class<? extends Entity> entityClass) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entityClass, name, ID++, Oshiete.instance, 160, 2, false);
    }
    private static void registerNormalEntity(String name, Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entityClass, name, ID++, Oshiete.instance, 160, 2, false, eggPrimary, eggSecondary);
    }
    private static void registerNormalThrow(String name, Class<? extends Entity> entity)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, ID++, Oshiete.instance, 64, 20, true);
    }
    private static void registerBugArrow(String name, Class<? extends Entity> entity)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, ID++, Oshiete.instance, 64, 1, true);
    }
}

package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Entity301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Model301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Render301;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import java.util.ArrayList;

public class KaguneUnit<E extends EntityKagune, M extends ModelKaguneBase, R extends GeoEntityRenderer<EntityKagune>>
{
    private static ArrayList<KaguneUnit> KAGUNE_UNITS = new ArrayList<>();

    public static void init() {
        KAGUNE_UNITS.clear();
        KAGUNE_UNITS.add(new KaguneUnit<Entity301, Model301, Render301>("KAGUNE301"));
    }

    public String getName() {
        return name;
    }

    private final String name;

    public KaguneUnit(String name) {
        this.name = name;
    }

    public EntityKagune getEntity(EntityLivingBase master) {
        return (E)new EntityKagune(master);
    }

    public AnimatedGeoModel<EntityKagune> getModel(String texture) {
        return (M)new ModelKaguneBase(texture);
    }

    public GeoEntityRenderer<EntityKagune> getRender(RenderManager manager) {
        return new Render301(manager);
    }
}

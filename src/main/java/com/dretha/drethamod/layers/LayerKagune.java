package com.dretha.drethamod.layers;

import java.util.Collections;
import javax.annotation.Nonnull;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneHolder;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.DrethaMath;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LayerKagune implements LayerRenderer<EntityLivingBase> {
	
	private final RenderManager renderManager;


    public LayerKagune()
    {
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }
    public LayerKagune(RenderLiving renderLiving)
    {
        this.renderManager = renderLiving.getRenderManager();
    }

    public void doRenderLayer(@Nonnull EntityLivingBase base, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        PersonStats stats = PersonStats.getStats(base);
        if (stats!=null && stats.isKaguneActive() && stats.getKagune()!=null) {
        	if (stats.ukaku() && !UkakuState.haveLimb(stats)) return;

            renderKagune(base, stats, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, renderManager, false);
        
        	GlStateManager.disableRescaleNormal();
        }
    }

    public static void renderKagune(EntityLivingBase base, PersonStats stats, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale1, RenderManager renderManager, boolean isFirstView)
    {
        KaguneHolder kaguneParams = KaguneHolder.valueOf(stats.getKaguneHolderName());
    	//AnimatedGeoModel kaguneModelProvider = kaguneParams.getModel(stats.getTextureLocation());
    	GeoEntityRenderer<EntityKagune> kaguneRenderer = kaguneParams.getRender(renderManager, stats.getTextureLocation());
        AnimatedGeoModel kaguneModelProvider = (AnimatedGeoModel) kaguneRenderer.getGeoModelProvider();

        EntityKagune kagune;
        do {
        	kagune = stats.getKagune();
        } while (stats.getKagune()==null);
        kagune.setListF(limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale1);

        ResourceLocation kaguneTexture = new ResourceLocation(Reference.MODID, stats.getTextureLocation());
        GeoModel geomodelkagune = kaguneModelProvider.getModel(kaguneModelProvider.getModelLocation(kagune));
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);

        if (base.isSneaking())
            GlStateManager.translate(0.0F, 0.2F, 0.2F);
        GlStateManager.rotate(180, 0, 0, 1);

        toScaleKagune(stats.getGhoulType(), stats.getRCpoints());

        if (isFirstView)
            GlStateManager.rotate(180, 0, 0, 1);
        
        EntityModelData entityModelData = new EntityModelData();
		entityModelData.isSitting = false;
		entityModelData.isChild = false;
		entityModelData.headPitch = -headPitch;
		entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent<EntityKagune> predicate = new AnimationEvent<>(kagune, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        kaguneModelProvider.setLivingAnimations(kagune, kaguneRenderer.getUniqueID(kagune), predicate);

        float red = 255F;
        float green = 255F;
        float blue = 255F;

        kaguneRenderer.render(geomodelkagune, kagune, partialTicks, red/255F, green/255F, blue/255F, 1F);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    private static void toScaleKagune(GhoulType type, int RCpoints)
    {
        DrethaMath.IntervalFinder finder = new DrethaMath.IntervalFinder(RCpoints, 2000, 7000);
        float scale = (float) finder.find(1, 2);
        //switch (type) {
            //case RINKAKU: {
                GlStateManager.scale(scale, scale, scale);
                float translateY = (float) finder.find(0, 0.2);
                float translateZ = (float) finder.find(0, 0.025);
                GlStateManager.translate(0.0F, translateY, translateZ);
                //break;
            //}
        //}
    }

    private static void toColorKagune() {

    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}

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
import org.lwjgl.opengl.GL11;
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
        KaguneHolder kaguneHolder = KaguneHolder.valueOf(stats.getKaguneHolderName());
        String firstTexture = stats.getFirstTextureLocation();
    	GeoEntityRenderer<EntityKagune> kaguneRenderer = kaguneHolder.getRender(renderManager, firstTexture);
        AnimatedGeoModel kaguneModelProvider = (AnimatedGeoModel) kaguneRenderer.getGeoModelProvider();

        EntityKagune entityKagune;
        do {
        	entityKagune = stats.getKagune();
        } while (stats.getKagune()==null);
        entityKagune.setRenderData(limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale1);

        ResourceLocation kaguneTexture = new ResourceLocation(Reference.MODID, firstTexture);
        GeoModel geomodelkagune = kaguneModelProvider.getModel(kaguneModelProvider.getModelLocation(entityKagune));

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        kaguneRenderer.bindTexture(kaguneTexture);

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
        AnimationEvent<EntityKagune> predicate = new AnimationEvent<>(entityKagune, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        kaguneModelProvider.setLivingAnimations(entityKagune, kaguneRenderer.getUniqueID(entityKagune), predicate);

        float red = stats.getRed();
        float green = stats.getGreen();
        float blue = stats.getBlue();

        kaguneRenderer.render(geomodelkagune, entityKagune, partialTicks, red/255F, green/255F, blue/255F, 1F);

        if (kaguneHolder.needSecondLayer) {
            kaguneRenderer.bindTexture(new ResourceLocation(Reference.MODID, stats.getSecondTextureLocation()));
            kaguneRenderer.render(geomodelkagune, entityKagune, partialTicks, 1, 1, 1, 1F);
        }

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        GL11.glPopAttrib();
    }

    private static void toScaleKagune(GhoulType type, int RCpoints)
    {
        DrethaMath.IntervalFinder finder = new DrethaMath.IntervalFinder(RCpoints, 2000, 7000);
        float scale = (float) finder.find(1, 1.9);
        GlStateManager.scale(scale, scale, scale);
        float translateY = 0;
        float translateZ = 0;
        switch (type) {
            case UKAKU: {
                translateY = 0;
                translateZ = -(float) finder.find(0, 0.05);
                break;
            }
            case KOUKAKU: {
                //translateY = (float) finder.find(0, 0.2);
                //translateZ = (float) finder.find(0, 0.025);
                break;
            }
            case RINKAKU: {
                translateY = (float) finder.find(0, 0.2);
                translateZ = (float) finder.find(0, 0.025);
                break;
            }
            case BIKAKU: {
                translateY = (float) finder.find(0, 0.31);
                translateZ = (float) finder.find(0, 0.05);
                break;
            }
        }
        GlStateManager.translate(0.0F, translateY, translateZ);
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}

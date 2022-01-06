package layers.entity;

import java.util.Collections;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nonnull;

public class EntityLayerKagune implements LayerRenderer<EntityHuman> {

    private final RenderLivingBase<?> renderer;
	
	public EntityLayerKagune(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
    }

    public void doRenderLayer(@Nonnull EntityHuman ghoulSP, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	EntityHuman ghoul = null;
    	try {
    	ghoul = EventsHandler.getHumanMP(ghoulSP);
    	} catch (IndexOutOfBoundsException e) {return;}
    	
        if (ghoul!=null && ghoul.isKaguneActive() && ghoul.getKagune()!=null) {
        	if (ghoul.ukaku() && !UkakuState.haveLimb(ghoul)) return;
        	
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
			this.renderKagune(ghoul, ghoul.getGhoulType().id(), limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
			
        	GlStateManager.disableRescaleNormal();
        }
    }

    private void renderKagune(@Nonnull EntityHuman ghoul, int ghoulType, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, boolean b)
    {
    	AnimatedGeoModel kaguneModel = EnumKagune.valueOf(ghoul.getEnumId()).getModel(ghoul.getTextureLocation());
    	GeoEntityRenderer kaguneRenderer = EnumKagune.valueOf(ghoul.getEnumId()).getRender(renderer.getRenderManager());

        EntityKagune kagune;
        do {
        	kagune = ghoul.getKagune();
        } while (ghoul.getKagune()==null);

        ResourceLocation kaguneTexture = kaguneModel.getTextureLocation(kagune);
        GeoModel geomodelkagune = kaguneModel.getModel(kaguneModel.getModelLocation(kagune));    
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        
        
        GlStateManager.rotate(180, 0, 0, 1);
        
        EntityModelData entityModelData = new EntityModelData();
		entityModelData.isSitting = false;
		entityModelData.isChild = false;
		entityModelData.headPitch = -headPitch;
		entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent<EntityKagune> predicate = new AnimationEvent<>(kagune, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        kaguneModel.setLivingAnimations(kagune, kaguneRenderer.getUniqueID(kagune), predicate);
        kaguneRenderer.render(geomodelkagune, kagune, partialTicks, 1F, 1F, 1F, 1F);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}


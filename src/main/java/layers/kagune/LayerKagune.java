package layers.kagune;

import java.util.Collections;
import javax.annotation.Nonnull;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        	
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            this.renderKagune(base, stats, stats.getGhoulType().index()+1, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
        
        	GlStateManager.disableRescaleNormal();
        }
    }

    private void renderKagune(EntityLivingBase base, PersonStats stats, int ghoulType, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, boolean b)
    {
        Item mask = stats.getInventory().getStackInSlot(0).getItem();

        if (mask != Items.AIR) {

            RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)base).getSkinType());
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            renderPlayer.getMainModel().bipedHead.postRender(0.0625F);

            GlStateManager.translate(/*не трогать*/0, (base.isSneaking() ? 0.2F : 0.0F) + /*вниз*/0.1875F, /*вперед*/-0.045F);
            GlStateManager.rotate(180, 0, 0, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(mask), base, ItemCameraTransforms.TransformType.HEAD, false);


            GlStateManager.popMatrix();
        }

        EnumKagune kaguneParams = EnumKagune.valueOf(stats.getEnumId());
    	AnimatedGeoModel kaguneModel = kaguneParams.getModel(stats.getTextureLocation());
    	GeoEntityRenderer<EntityKagune> kaguneRenderer = kaguneParams.getRender(renderManager);

        EntityKagune kagune;
        do {
        	kagune = stats.getKagune();
        } while (stats.getKagune()==null);
        kagune.setListF(limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        
        // TODO масштабировать кагуне
        ResourceLocation kaguneTexture = kaguneModel.getTextureLocation(kagune);
        GeoModel geomodelkagune = kaguneModel.getModel(kaguneModel.getModelLocation(kagune));    
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        
        
        float heightPoint = (base.isSneaking() ? 0.2F : 0.0F);
        GlStateManager.translate(0.0F, heightPoint, 0.0F);
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
        return true;
    }
}

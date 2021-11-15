package layers.kagune;

import java.util.Collections;
import java.util.UUID;

import javax.annotation.Nullable;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneGeoRenderer;
import com.dretha.drethamod.client.geckolib.kagunes.ModelKagune;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderParrot;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerEntityOnShoulder;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class LayerKoukaku implements LayerRenderer<EntityPlayer> {
	
	private final RenderManager renderManager;
	private GeoEntityRenderer <EntityKagune> kaguneRenderer;
    private ModelKagune kaguneModel;
    private ResourceLocation kaguneTexture;
    private EntityKagune kagune;
    

    public LayerKoukaku()
    {
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }

    public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (EventsHandler.getCapaMP(player).getGhoulType()==2 && EventsHandler.getCapaMP(player).isKaguneActive() && EventsHandler.getCapaMP(player).getKagune()!=null) {
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
        	this.renderKagune(player, EventsHandler.getCapaMP(player), EventsHandler.getCapaMP(player).getGhoulType(), limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
        
        	GlStateManager.disableRescaleNormal();
        }
    }

    private void renderKagune(EntityPlayer player, ICapaHandler capa, int ghoulType, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, boolean b)
    {
        kaguneRenderer = (GeoEntityRenderer) new KaguneGeoRenderer(this.renderManager, capa.getModelLocation(), capa.getTextureLocation(), capa.getAnimationLocation());
        kaguneModel = new ModelKagune(capa.getModelLocation(), capa.getTextureLocation(), capa.getAnimationLocation());
        
        
        do {
        	kagune = EventsHandler.getCapaMP(player).getKagune();
        } while (EventsHandler.getCapaMP(player).getKagune()==null);
        kagune.setListF(limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        
        
        kaguneTexture = kaguneModel.getTextureLocation(kagune);
        GeoModel geomodelkagune = kaguneModel.getModel(kaguneModel.getModelLocation(kagune));    
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        
        
        float heightPoint = (player.isSneaking() ? 0.2F : 0.0F);
        GlStateManager.translate(0.0F, heightPoint, 0.0F);
        GlStateManager.rotate(180, 0, 0, 1);
        
        
        EntityModelData entityModelData = new EntityModelData();
		entityModelData.isSitting = false;
		entityModelData.isChild = false;
		entityModelData.headPitch = -headPitch;
		entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent predicate = new AnimationEvent(kagune, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        kaguneModel.setLivingAnimations(kagune, kaguneRenderer.getUniqueID(kagune), predicate);
        kaguneRenderer.render(geomodelkagune, kagune, partialTicks, 1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}

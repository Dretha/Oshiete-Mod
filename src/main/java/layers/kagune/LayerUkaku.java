package layers.kagune;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.CapaStorage;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.google.common.base.MoreObjects;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LayerUkaku implements LayerRenderer<EntityPlayer> {
	
	private final Item UKAKU = InitItems.KAGUNE_UKAKU;
	private static final ResourceLocation KAKUGAN2 = new ResourceLocation(Reference.MODID + ":textures/items/kakugan2.png");
	
   @Override
   public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
	   
	   
	   OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
	   
	   
	   
	   //ukaku
	   if (EventsHandler.getCapaMP(player).getGhoulType()==1 && EventsHandler.getCapaMP(player).isKaguneActive()) {
		   GlStateManager.pushMatrix();
		   
		   GlStateManager.disableLighting();
		   
		   GlStateManager.rotate(205, 0, 1, 0);
		   GlStateManager.rotate(10, 0, 0, 1);
		   GlStateManager.scale(2, 2, 0.5);
		   
		   GlStateManager.rotate(180, 0, 0, 1);
		   
	       GlStateManager.translate(-0.6, 0.4, -0.17);
	       
	       
	       if(player.isSneaking())
	       {
	           GlStateManager.translate(0, 0.2F, 0.0F);
	       }
	       Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(UKAKU), player, ItemCameraTransforms.TransformType.FIXED, false);
	       
           GlStateManager.enableLighting();
           
	       
	       GlStateManager.popMatrix();
	   //-------------------------------------------------
	       GlStateManager.pushMatrix();
	       
	       GlStateManager.disableLighting();
	       
		   GlStateManager.rotate(155, 0, 1, 0);
		   GlStateManager.rotate(350, 0, 0, 1);
		   GlStateManager.scale(2, 2, 0.5);
		   
		   GlStateManager.rotate(180, 1, 0, 0);
		   
	       GlStateManager.translate(-0.6, 0.4, 0.17);
	       
	       
	       if(player.isSneaking())
	       {
	    	   GlStateManager.translate(0, 0.2F, 0.0F);
	       }
	       Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(UKAKU), player, ItemCameraTransforms.TransformType.FIXED, false);
	       
           GlStateManager.enableLighting();
           
	       
	       GlStateManager.popMatrix();
	   }    
   }
	   

   @Override
   public boolean shouldCombineTextures() {
       return true;
   }
}

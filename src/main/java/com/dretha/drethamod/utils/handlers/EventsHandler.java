package com.dretha.drethamod.utils.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.CapaStorage;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneGeoRenderer;
import com.dretha.drethamod.client.geckolib.kagunes.ModelKagune;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.interfaces.IGhoul;
import com.dretha.drethamod.utils.interfaces.IGhoulFood;
import com.dretha.drethamod.utils.interfaces.IKuinke;
import com.jcraft.jogg.Packet;

import ibxm.Player;
import io.netty.handler.codec.http.multipart.Attribute;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.swing.event.Key;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class EventsHandler {
	
	
	
	//for capa
	public static ICapaHandler getHandler(Entity entity) {
        if (entity.hasCapability(CapaProvider.PLAYER_CAP, null)) {
            return entity.getCapability(CapaProvider.PLAYER_CAP, null); }
        return null;
    }

	
	
	private static void returnKagune(ItemStack item, EntityPlayer player) {
		player.inventory.addItemStackToInventory(item);   		   
		player.inventoryContainer.detectAndSendChanges();
	}
	
    public static final ResourceLocation PLAYER_CAP = new ResourceLocation(Reference.MODID, "capa");
    
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        try {
            event.addCapability(PLAYER_CAP, new CapaProvider());
        } catch (Exception e) {
	    }/*
        try {
        ((EntityPlayer) event.getObject()).getCapability(CapaProvider.PLAYER_CAP, null).attachMaster((EntityPlayer) event.getObject());
        } catch (Exception ee) {
        	
        }*/
        }
    
    /*@SubscribeEvent
    public static void playerLoggedIn(PlayerLoggedInEvent e) {
    	EntityPlayer player = (EntityPlayer) e.player;
        ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
        capa.setIsGhoul(true, 1);
        capa.setRClevel(capa.getRCpoints()/10);
    }*/
	
    @SubscribeEvent
    public static void cloneCapa(PlayerEvent.Clone e) {
        final ICapaHandler original = getHandler(e.getOriginal());
        final ICapaHandler clone = getHandler(e.getEntity());
        clone.setRCpoints(original.getRCpoints());
        clone.setIsGhoul(original.isGhoul());
        clone.setGhoulType(original.getGhoulType());
    }
    
    
    
    //playerlist
    private static LinkedList<EntityPlayer> player_list = new LinkedList<EntityPlayer>();
    @SubscribeEvent
    public void PlayerLoggedInList(PlayerLoggedInEvent e) {
    	if (!player_list.contains(e.player)) {
    		player_list.add(e.player);
    		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
    		capa.attachMaster(e.player);
    		if (capa.isKaguneActive()) 
    			capa.releaseKagune();
    	}
    }
    @SubscribeEvent
    public void PlayerLoggedOutList(PlayerLoggedOutEvent e) {
    	if (player_list.contains(e.player)) {
    		player_list.remove(e.player);
    	}
    }
    @SubscribeEvent
    public void PlayerCloneEventList(PlayerEvent.Clone e) {
    	if (player_list.contains(e.getOriginal())) {
    		player_list.remove(e.getOriginal());
    	}
    	if (!player_list.contains(e.getEntityPlayer())) {
    		player_list.add(e.getEntityPlayer());
    	}
    }
    public static EntityPlayer getPlayerMP(EntityPlayer player) {
    	return player_list.get(player_list.indexOf(player));
    }
    public static ICapaHandler getCapaMP(EntityPlayer player) {
    	return player_list.get(player_list.indexOf(player)).getCapability(CapaProvider.PLAYER_CAP, null);
    }
    
    public static final ItemStack ghoulFood = new ItemStack(InitItems.HUMAN_MEAT);
    public static final Item bottle = Item.getItemById(374);
    public static final ItemStack rottenflesh = new ItemStack(Items.ROTTEN_FLESH);
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void fillBottleBlood(AttackEntityEvent e) {
    	if (e.getEntityPlayer().getHeldItemMainhand().getItem().equals(bottle) && e.getTarget() instanceof EntityHuman) {
    		System.out.println(e.getResult().toString());
    		e.getEntityPlayer().inventory.clearMatchingItems(Items.GLASS_BOTTLE, 0, 1, null);
    		e.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(InitItems.HUMAN_BLOOD_BOTTLE));
    	    e.getEntityPlayer().inventoryContainer.detectAndSendChanges(); 
    	    e.getEntity().world.playSound(e.getEntityPlayer(), e.getEntityPlayer().getPosition(), SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.PLAYERS, 1F, 1F);
    	}
    }
    
    @SubscribeEvent
    public static void villagerDeath(LivingDeathEvent e) {
    	if (e.getEntityLiving() instanceof EntityVillager) {
    		e.getEntityLiving().world.spawnEntity(new EntityItem(e.getEntityLiving().world, e.getEntityLiving().posX, e.getEntityLiving().posY, e.getEntityLiving().posZ, ghoulFood));
    	}
    }
    
    
    //worked
    @SubscribeEvent
    public static void kaguneFirstView(RenderSpecificHandEvent e) { 
    	ICapaHandler capa = EventsHandler.getCapaMP(Minecraft.getMinecraft().player);
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if (capa.getGhoulType()==2 && EventsHandler.getCapaMP(player).isKaguneActive() && EventsHandler.getCapaMP(player).getKagune()!=null) {
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
        	
        	EntityKagune kagune = null;
        	float limbSwing=0;
        	float limbSwingAmount=0;
			float partialTicks=0;
			float ageInTicks=0;
			float netHeadYaw=0;
			float headPitch=0;
			float scale=0;
			
			do {
	        	kagune = EventsHandler.getCapaMP(player).getKagune();
	        } while (EventsHandler.getCapaMP(player).getKagune()==null);
			
			limbSwing = kagune.getListF().get(0);
			limbSwingAmount = kagune.getListF().get(1);
			partialTicks = kagune.getListF().get(2);
			ageInTicks = kagune.getListF().get(3);
			netHeadYaw = kagune.getListF().get(4);
			headPitch = kagune.getListF().get(5);
			scale = kagune.getListF().get(6);
			
        	renderKagune(player, EventsHandler.getCapaMP(player).getGhoulType(), limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
        
        	GlStateManager.disableRescaleNormal();
        }
    }
    private static void renderKagune(EntityPlayer player, int ghoulType, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, boolean b)
    {
    	ICapaHandler capa = EventsHandler.getCapaMP(player);
    	GeoEntityRenderer<EntityKagune> kaguneRenderer = (GeoEntityRenderer) new KaguneGeoRenderer(Minecraft.getMinecraft().getRenderManager(), capa.getModelLocation(), capa.getTextureLocation(), capa.getAnimationLocation());
    	ModelKagune kaguneModel = new ModelKagune(capa.getModelLocation(), capa.getTextureLocation(), capa.getAnimationLocation());
    	EntityKagune kagune = null;
        
        
        do {
        	kagune = EventsHandler.getCapaMP(player).getKagune();
        } while (EventsHandler.getCapaMP(player).getKagune()==null);
        
        
        ResourceLocation kaguneTexture = kaguneModel.getTextureLocation(kagune);
        GeoModel geomodelkagune = kaguneModel.getModel(kaguneModel.getModelLocation(kagune));    
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        
        
        float heightPoint = (player.isSneaking() ? 0.2F : 0.0F);
        GlStateManager.translate(0.0F, heightPoint, 0.0F);
        GlStateManager.scale(1, 1, 1);
        
        
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
    
    
    
    
    
    
    /*
    public static final ResourceLocation PUMPKIN_BLUR_TEX_PATH = new ResourceLocation("textures/misc/pumpkinblur.png");
    public static final ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
    //guikakugan
    @SubscribeEvent
    public static void renderGuiKakugan(RenderGameOverlayEvent.Post e) { 
    	
    	GlStateManager.pushMatrix();
    	GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(PUMPKIN_BLUR_TEX_PATH);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0.0D, (double)scaledresolution.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)scaledresolution.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
        
        System.out.println("renderGuiKakugan");
    }*/
    
    
}

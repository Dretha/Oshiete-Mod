package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.main.Main;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.*;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class KeyEventsHandler {
	
	//key listener activate kagune
    @SubscribeEvent
    public void activateKagune(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_ACTIVATE_KAGUNE.isPressed()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		if (capa.isGhoul() && capa.canKaguneActivated(player.ticksExisted) && !(capa.isKaguneActive())) {
    			
    			capa.releaseKagune();
				capa.setKaguneActivatedTicksPre(player.ticksExisted);
    		}
    		if (capa.isGhoul() && capa.canKaguneActivated(player.ticksExisted) && capa.isKaguneActive() && capa.getKagune()!=null && !capa.getKagune().transform()) {
    			
    			capa.admitKagune();
				capa.setKaguneActivatedTicksPre(player.ticksExisted);
    		}
    	}
    }
    
   
   AttributeModifier speedmode = new AttributeModifier("speedmode", 1.6, 2);
   //key listener activate ghoul speed mode
   @SubscribeEvent
   public void activateSpeedMode(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_ACTIVATE_GHOUL_SPEED_MODE.isPressed()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		if (capa.isGhoul() && capa.canSpeedModeActivated(player.ticksExisted) && !(capa.isSpeedModeActive())) {
    			
    			capa.setActivatedSpeedMode(true);
    			capa.setActivatedKakugan(true);
				capa.applyAtrSpeedMode();
				capa.setSpeedModeTicksPre(player.ticksExisted);
    		}
    		if (capa.isGhoul() && capa.canSpeedModeActivated(player.ticksExisted) && capa.isSpeedModeActive()) {
    			
    			capa.setActivatedSpeedMode(false);
    			if (!capa.isKaguneActive())
    			capa.setActivatedKakugan(false);
				capa.removeAtrSpeedMode();
    			capa.setSpeedModeTicksPre(player.ticksExisted);
    		}
    	}
   }
	
   
   //key listener impact
   @SubscribeEvent
   public void impactcontroller(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		EntityKagune kagune = capa.getKagune();
    		
    		if (kagune!=null && capa.isKaguneActive() && kagune.canCloseHit() && !kagune.isHit() && !kagune.transform()) {
    			if (capa.ukaku() && !UkakuState.haveLimb(capa)) return;
    			if (capa.getImpactType() == ImpactType.THRUST) {
    				if (capa.isBlock())
    					Main.NETWORK.sendToServer(new TakeAThrustMessage("foobar"));
    				else
    					Main.NETWORK.sendToServer(new TakeAThrustMessage("foobar"));
    			} else if (!capa.isBlock()){
        			Main.NETWORK.sendToServer(new TakeASlashMessage("fo1111obar"));
    			} else return;
    			kagune.setHit(true);
    			kagune.setHitTicksPre(player.ticksExisted);
    		}
    	}
   }
   
   
   //key listener impact
   @SubscribeEvent
   public void changeImpactMode(KeyInputEvent e) {
	   if (KeybindsRegister.KEY_HIT_MODE.isKeyDown()) {
		   EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
   		   ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
   		
   		   if (capa.getImpactModeTicksPre()+3<=player.ticksExisted) {
   			   capa.changeImpactType();
   		   }
		   if (player.getHeldItemMainhand().getItem() instanceof ItemFirearm) {
			   ItemFirearm firearm = (ItemFirearm) player.getHeldItemMainhand().getItem();
			   firearm.reload(player.getHeldItemMainhand(), player.world, player);
		   }

		   capa.setImpactModeTicksPre(player.ticksExisted);
	   }
   }
   
   //key listener shoot and block
   @SubscribeEvent
   public void keyBlock(PlayerTickEvent e) {
	   ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
	   if (capa!= null && KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && !e.player.world.isRemote && capa.ukaku() && UkakuState.haveFlame(capa) && capa.isKaguneActive() && capa.getKagune()!=null && !capa.getKagune().transform()) {
		   EntityPlayerMP player = (EntityPlayerMP) e.player;
   		   if (player.ticksExisted%3==0 && capa.getShootTicksPre()+2<=player.ticksExisted && (capa.getRClevel()>15 || player.isCreative())) {
   			   capa.setShootTicksPre(player.ticksExisted);
   			   EntityRCShard entityrcshard = new EntityRCShard(player.world, player);
   			   entityrcshard.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1F);
   			   entityrcshard.setIsCritical(false);
   			   entityrcshard.setDamage(5);
   			   entityrcshard.setKnockbackStrength(0);
   			   entityrcshard.setFire(0);     
                       
   			   e.player.world.spawnEntity(entityrcshard);
            
   			   e.player.world.playSound(null, player.getPosition(), InitSounds.ukaku_shooting, SoundCategory.PLAYERS, 0.5F, 1.0F);
   			   if (!player.isCreative()) {
   				   capa.removeRClevel(15);
   			   }
   		   }
	   } else if (KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && !e.player.world.isRemote && capa.isKaguneActive() && !capa.ukaku()) {
	       capa.setBlock(true);
	   } else if (!KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && capa.isGhoul()) {
		   capa.setBlock(false);
	   }
   }
   @SubscribeEvent
   public static void block(LivingAttackEvent e) {
	   
	   if (e.getEntityLiving() instanceof EntityPlayer && !e.getEntityLiving().world.isRemote) {
		   EntityPlayerMP player = (EntityPlayerMP)e.getEntityLiving();
		   ICapaHandler capa = EventsHandler.getCapaMP(player);
		   Entity immediate = e.getSource().getImmediateSource();
		   EntityLivingBase entity = null;
		   if (e.getSource().getTrueSource() instanceof EntityLivingBase)
			   entity = (EntityLivingBase) e.getSource().getTrueSource();
		   
		   DamageSource source;
		   if (entity==null) return;
		   if (entity instanceof EntityPlayer) {
			   source = DamageSource.causePlayerDamage((EntityPlayer) entity);
		   } else {
			   source = DamageSource.causeMobDamage(entity);
		   }
		   if (immediate instanceof EntityArrow) {
			   source = DamageSource.causeArrowDamage((EntityArrow) immediate, entity);
		   }
		   
		   if (source!=null && capa.isBlock())
		   {
			   if (capa.getKagune()!=null && !capa.getKagune().isBlockAnim()) {
				   capa.getKagune().setBlockAnim(true);
				   capa.getKagune().setBlockAnimPre(player.ticksExisted);
			   }

			   e.setCanceled(true);

			   if (immediate instanceof EntityLivingBase) {
				   EntityLivingBase base = (EntityLivingBase) e.getSource().getTrueSource();
				   base.knockBack(player, 0.5F, player.posX - base.posX, player.posZ - base.posZ);
			   }

			   if (capa.isKaguneActive())
			   {
				   player.world.playSound(null, player.getPosition(), InitSounds.hit_ground_kagune_2, SoundCategory.PLAYERS, 1.0F, 1.0F);

				   int damage = (int) Math.round((e.getAmount() * capa.getGhoulType().blockDamageModif(capa.getResponseValue())));
				   if (damage != 0)
					   player.setHealth(player.getHealth() - damage);
			   }
			   else if (player.getActiveItemStack().getItem() instanceof IKuinke)
			   {
				   player.world.playSound(null, player.getPosition(), InitSounds.kuinke_block, SoundCategory.PLAYERS, 1.0F, 1.0F);

				   ItemStack kuinkeStack = player.getActiveItemStack();
				   IKuinke kuinke = (IKuinke) player.getActiveItemStack().getItem();

				   if (kuinke.getBlockValue() < (int)e.getAmount())
				   {
					   kuinkeStack.damageItem((int)e.getAmount()-kuinke.getBlockValue(), (EntityLivingBase) e.getSource().getTrueSource());

					   if (kuinke.getBlockValue()*2 < (int)e.getAmount())
					   {
						   player.setHealth(player.getHealth() - (kuinke.getBlockValue()*2 - (int)e.getAmount()));
						   //setHurt(player, entity, immediate, source.getDamageType(),  (int)e.getAmount() - kuinke.getBlockValue()*2);
					   }
				   }
			   }

		   }
	   }
   }

   public static void setHurt(EntityPlayer target, EntityLivingBase attacker, Entity immediate, String source, int damage) {
	   if (target.getHealth()<=damage)
	   {
		   NBTTagCompound compound = new NBTTagCompound();
		   compound.setString("source", source);
		   compound.setUniqueId("entity", attacker.getUniqueID());
		   compound.setUniqueId("immediate", immediate.getUniqueID());
		   Main.NETWORK.sendToServer(new KillPlayerMessage(compound, damage));
	   }
	   else
	   {
		   target.setHealth(target.getHealth() - damage);
	   }
   }
   
   //key listener smell
   @SubscribeEvent
   public void sniff(KeyInputEvent e) {
	   if (KeybindsRegister.KEY_SMELL.isKeyDown()) {
		   EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
   		   ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);

   		   if (capa.isGhoul() && capa.getSmellTicksPre()+1000<=player.ticksExisted) {
			   System.out.println("smell");
   			   capa.setSmellTicksPre(player.ticksExisted);
   			   Main.NETWORK.sendToServer(new SniffMessage(capa.getSmellRadius(), capa.getSmellDuration()));
   		   }
	   }
   }
   
   //open clothes inventory
   @SubscribeEvent
   public void onKey(KeyInputEvent event) {
       if (KeybindsRegister.KEY_OPEN_CLOTHES_INVENTORY.isPressed()) {
    	   Main.NETWORK.sendToServer(new OpenClothesInventoryMessage());
       }
   }
}

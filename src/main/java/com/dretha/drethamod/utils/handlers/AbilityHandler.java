package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.GhoulEatMessage;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class AbilityHandler {

//for support      
    
    public static final ItemStack ghoulFood = new ItemStack(InitItems.HUMAN_MEAT);
    public static final Item bottle = Item.getItemById(374);
    public static final ItemStack rottenflesh = new ItemStack(Items.ROTTEN_FLESH);
    
    /*@SubscribeEvent
    public static void dropKagune(ItemTossEvent e) {
    	System.out.println(e.getEntity().getName());  
    	if ((e.getPlayer()!=null) && (e.getEntityItem().getItem().getItem() instanceof IKagune)) {
    		e.getEntityItem().setDead();
    	    e.getPlayer().inventory.addItemStackToInventory(new ItemStack(e.getEntityItem().getItem().getItem()));   		   
    	    e.getPlayer().inventoryContainer.detectAndSendChanges(); 
    	}
    }*/
    
    
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e) {    	
        if (e.getEntity() instanceof EntityPlayer) {
        	
        	if(e.getEntity() instanceof EntityPlayer) {
        		EntityPlayer player = (EntityPlayer) e.getEntity();
        		
        		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
                player.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
                player.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
            }
            
        }
    }
    
    
    
    
    /*AttributeModifier speedghoul = new AttributeModifier("speedghoul", 2.1, 2);
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void kaguneActivated1(LivingEquipmentChangeEvent e) {
    	if (e.getEntity() instanceof EntityPlayer && ((EntityPlayer) e.getEntity()).getCapability(CapaProvider.PLAYER_CAP, null).isGhoul()) {
    		if ((((EntityPlayer) e.getEntity()).ticksExisted%3)==0) {
    		System.out.println(e.isCancelable());
    		if (!(e.getFrom().getItem() instanceof KaguneBase) && e.getTo().getItem() instanceof KaguneBase) {
    			
    			EntityPlayer player = (EntityPlayer) e.getEntity();
    			player.getCapability(CapaProvider.PLAYER_CAP, null).setActivatedKagune(true);
    			ticksPre=player.ticksExisted;
    			spawnPatr=true;
    			player.world.playSound(null, player.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
    			
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedghoul);
				
    		}
    		if (e.getFrom().getItem() instanceof IKagune && !(e.getTo().getItem() instanceof IKagune)) {
    			
    			EntityPlayer player = (EntityPlayer) e.getEntity();
    			player.getCapability(CapaProvider.PLAYER_CAP, null).setActivatedKagune(false);				
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedghoul);
				
    		}
    		}
    	}
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    //ghoul eat
    @SubscribeEvent
    public static void ghoulEat(LivingEntityUseItemEvent.Tick e) { 
		if (e.getEntity() instanceof EntityPlayer && e.getItem().getItem() instanceof ItemFood) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			capa.setLastFoodAmount(player.getFoodStats().getFoodLevel());
		}
    }
    @SubscribeEvent
    public static void ghoulEat(LivingEntityUseItemEvent.Finish e) { 
    	if (e.getEntity() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) e.getEntity();
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		if (capa.isGhoul() && !(e.getItem().getItem() instanceof ItemGhoulFood) && e.getItem().getItem() instanceof ItemFood && !(ItemStack.areItemsEqual(e.getItem(), rottenflesh))) {
    			Oshiete.NETWORK.sendToServer(new GhoulEatMessage(e.getItem()));
    		}
    	}
    }
    
    @SubscribeEvent
    public static void ghoulEatSoup(LivingEntityUseItemEvent.Finish e) {
    	if (ItemStack.areItemsEqual(e.getResultStack(), new ItemStack(Items.BOWL))) {
    		((EntityLivingBase) e.getEntity()).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 240, 1)); 
    		EntityPlayer player = (EntityPlayer) e.getEntity();
    		player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-10);
    		player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel()-12);
    	}
    }
    
    @SubscribeEvent
    public static void ghoulEatCake(PlayerInteractEvent.RightClickBlock e) {
    	if (Block.isEqualTo(e.getWorld().getBlockState(e.getPos()).getBlock(), Blocks.CAKE) && e.getEntityPlayer().getFoodStats().getFoodLevel()<=18) {
    		((EntityLivingBase) e.getEntity()).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 240, 1)); 
    		e.setUseBlock(Event.Result.DENY);
    		e.getEntityPlayer().getFoodStats().setFoodLevel(e.getEntityPlayer().getFoodStats().getFoodLevel()-2);
    		e.getEntityPlayer().getFoodStats().setFoodSaturationLevel(e.getEntityPlayer().getFoodStats().getSaturationLevel()-0.4F);
    	}
    }
    
    
    
    //sounds kagune
    
    /*@SubscribeEvent
    public static void playSoundKaguneBlock(LivingEntityUseItemEvent.Start e) {
    	if (e.getEntity() instanceof EntityPlayer && ItemStack.areItemStacksEqual(e.getItem(), kaguneRinkaku)) {
    		EntityPlayer player = (EntityPlayer) e.getEntity();
    		player.world.spawnParticle(EnumParticleTypes.REDSTONE, player.posX, player.posY, player.posZ, player.motionX, player.motionY, player.motionZ);
    		e.getEntityLiving().world.playSound(player, player.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		player.sendMessage(new TextComponentString(capa.getRCpoints()+" RC"));
    	}
    	
    }*/
    /*
    @SubscribeEvent
    public static void playSoundKaguneHitAir(PlayerInteractEvent.LeftClickEmpty e) {
    	if (e.getItemStack().getItem() instanceof KaguneMelee) {
    		EntityPlayer player = (EntityPlayer) e.getEntity();
    		e.getEntityLiving().world.playSound(player, player.getPosition(), InitSounds.hit_air_kagune, SoundCategory.PLAYERS, 0.5F, 1.0F);
    	}
    }
    */
    /*static int i=0;
    @SubscribeEvent
    public static void playSoundKagune(RenderSpecificHandEvent e) {
    	System.out.println("playSoundKagune");
    	if (ItemStack.areItemStacksEqual(e.getItemStack(), kagune)&&i==0) {
    		System.out.println("playSoundKagune true");
    		world.playSound(playerS, playerS.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1.0F, 1.0F);
    		i++;
    	}
    }*/
    
    /*
    @SubscribeEvent
    public static void playSoundLetOfKagune(RenderSpecificHandEvent e) {
    	ItemStack kagune = new ItemStack(InitItems.KAGUNE_RINKAKU);
    	System.out.println("playSoundLetOfKagune");
    	if (ItemStack.areItemStacksEqual(e.getItemStack(), kagune)&&false) {
    		System.out.println("playSoundLetOfKagune true");
    		player.world.playSound(player, player.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1.0F, 1.0F);    		
    	}
    }*/
    
    
    
    
  //Regenerate RC level and remove food level
    /*
  	@SubscribeEvent
      public void regenerateRClevel(PlayerTickEvent e) {
      	//e.player.world.spawnParticle(EnumParticleTypes.REDSTONE, e.player.posX, e.player.posY, e.player.posZ, e.player.motionX, e.player.motionY, e.player.motionZ);
      	if ((EventsHandler.getPlayerMP(e.player).ticksExisted%20)==0 && EventsHandler.getPlayerMP(e.player).getFoodStats().getFoodLevel()>0) {
      		
      		ICapaHandler capa = EventsHandler.getPlayerMP(e.player).getCapability(CapaProvider.PLAYER_CAP, null);
      		EntityPlayer player = EventsHandler.getPlayerMP(e.player);
      		
      		if (capa.isGhoul() && player.getFoodStats().getFoodLevel()>0 && capa.getRClevel()<capa.getRCpoints()/10) {
      			
      			if (capa.isGhoul()) capa.updateRClevel();
      			if (capa.isGhoul() && capa.getRClevel()<capa.getRCpoints()/10) {
      				capa.addRClevel((capa.getRCpoints()/10)/40);
      				player.sendMessage(new TextComponentString(capa.getRClevel()+" RC Level"));
      			}
      			if ((player.ticksExisted%200)==0 && !(player.isCreative())) {
      				if (player.getFoodStats().getSaturationLevel()>0) {
      					player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel()-1);
      				} else {
      					EventsHandler.getPlayerMP(player).getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-1);
      				}
      			}
      			
      		}
      	
      	}
      	
      }
  	*/
  	
  	@SubscribeEvent
    public static void onPlayerUpdate(LivingUpdateEvent event) {
  		if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ICapaHandler capa = EventsHandler.getCapaMP(player);
			PersonStats stats = capa.personStats();
            int foodlevel = player.getFoodStats().getFoodLevel();
            
            
            
            int upicks = 20; //update ticks
            int tofull = 40; //40 secs to full level
            
            int i = (upicks * tofull)/20; //40
            
            if (player.ticksExisted%upicks==0 && capa.isGhoul()) {
            	stats.updateRClevel();
            	
            	if (foodlevel>6 && !stats.RClevelFull()) {
            		stats.addRClevel((stats.getRCpoints()/10)/i);
            		player.getFoodStats().addExhaustion((float)48/i); //1.2
            		player.sendMessage(new TextComponentString(stats.getRClevel()+" RC Level"));
            	}
            	
            }
  		}
  	}
  	
  	
  	
  	
  	//protect ghoul from melee weapon
  	/*worked
  	@SubscribeEvent
      public static void setGhoulProtect(LivingAttackEvent e) {
      	if (e.getEntityLiving() instanceof EntityPlayer) {
      		EntityPlayer player = (EntityPlayer) e.getEntityLiving();
      		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
      		if (capa.isGhoul()) {
      			if (e.getSource().getTrueSource() instanceof EntityPlayer) {
      				EntityPlayer playerAt = (EntityPlayer) e.getEntityLiving();
      				if (!(playerAt.getHeldItemMainhand().getItem() instanceof IKuinke) && !(playerAt.getHeldItemMainhand().getItem() instanceof IKagune)) {
      					e.setCanceled(true);
      					if (!e.getSource().isProjectile()) {
      						playerAt.getHeldItemMainhand().damageItem(100000, playerAt);
      					}
      				}
      			}
      			if (e.getSource().getTrueSource() != null && !(e.getSource().getTrueSource() instanceof EntityPlayer)) {
      				EntityLivingBase entity = (EntityLivingBase) e.getSource().getTrueSource();
      				if (!(entity.getHeldItemMainhand().getItem().equals(Items.AIR)) && !(entity.getHeldItemMainhand().getItem() instanceof IKuinke) && !(entity.getHeldItemMainhand().getItem() instanceof IKagune)) {
      					e.setCanceled(true);
      					if (!e.getSource().isProjectile()) {
      					    entity.getHeldItemMainhand().damageItem(100000, entity);
      					}
      				}
      			}
      		}
      	}
      	if (!(e.getEntityLiving() instanceof EntityPlayer) && e.getEntityLiving() instanceof IGhoul) {
      		if (e.getSource().getTrueSource() instanceof EntityPlayer) {
      			EntityPlayer playerAt = (EntityPlayer) e.getSource().getTrueSource();
  				if (!(playerAt.getHeldItemMainhand().getItem() instanceof IKuinke) && !(playerAt.getHeldItemMainhand().getItem() instanceof IKagune)) {
  					e.setCanceled(true);
  					if (!e.getSource().isProjectile()) {
  						playerAt.getHeldItemMainhand().damageItem(100000, playerAt);
  					}
  				}
      		}
      		if (e.getSource().getTrueSource() != null && !(e.getSource().getTrueSource() instanceof EntityPlayer)) {
      			EntityLivingBase entity = (EntityLivingBase) e.getSource().getTrueSource();
  				if (!(entity.getHeldItemMainhand().getItem().equals(Items.AIR)) && !(entity.getHeldItemMainhand().getItem() instanceof IKuinke) && !(entity.getHeldItemMainhand().getItem() instanceof IKagune)) {
  					e.setCanceled(true);
  					if (!e.getSource().isProjectile()) {
  					    entity.getHeldItemMainhand().damageItem(100000, entity);
  					}
  				}
      		}
      	}
      }
  	*/


	@SubscribeEvent
	public void spawnKagunePatricles(PlayerTickEvent e) {
		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
		if (capa!=null && capa.getSpawnKagunePatriclesFlag()) {
			this.spawnPatricleSpine(e.player);
			if (capa.getSpawnKagunePatriclesTicksPre()+30<=e.player.ticksExisted)
				capa.setSpawnKagunePatriclesFlag(false);
		}
	}
	public void spawnPatricleSpine(EntityLivingBase entity) {
		Random random = new Random();
		double d0 = entity.posX + ((random.nextGaussian()-0.5D)*0.25D);
		double d1 = entity.posY + 1D + ((random.nextGaussian()-0.5D)*0.5D);
		double d2 = entity.posZ + ((random.nextGaussian()-0.5D)*0.25D);
		float f = 10F / 15.0F;
		float f1 = f * 0.6F + 0.4F;
		float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
		float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);
		entity.world.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, (double)f1, (double)f2, (double)f3);
	}

	@SubscribeEvent
	public static void jump(LivingEvent.LivingJumpEvent e)
	{
		PersonStats stats = PersonStats.getStats(e.getEntityLiving());
		if (stats!=null && (!stats.isGhoul() || stats.isSpeedModeActive()))
		{
			double d = stats.isGhoul()?10D:20D;
			double height = stats.materialRank()/d;
			height = stats.isGhoul() ? Math.max(height, 0.1) : height;
			e.getEntityLiving().motionY+=height;
		}
	}
}

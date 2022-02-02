package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.GhoulEatMessage;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.interfaces.IAntiGhoulArmor;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
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

    //ghoul eat
    @SubscribeEvent
    public static void ghoulEat(LivingEntityUseItemEvent.Tick e) { 
		if (e.getEntity() instanceof EntityPlayer && e.getItem().getItem() instanceof ItemFood) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			capa.setLastFoodAmount(player.getFoodStats().getFoodLevel());
		}
    }// TODO не работает питание почини
    @SubscribeEvent
    public static void ghoulEat(LivingEntityUseItemEvent.Finish e) { 
    	if (e.getEntity() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) e.getEntity();
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			Item item = e.getItem().getItem();
    		if (capa.isGhoul() && !(item instanceof ItemGhoulFood) && item instanceof ItemFood && item!=Items.ROTTEN_FLESH) {
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
            		stats.addRClevel((stats.MaxRClevel())/i);
            		player.getFoodStats().addExhaustion((float)48/i); //1.2
            		player.sendMessage(new TextComponentString(stats.getRClevel()+" RC Level"));
            	}
            	
            }
  		}
  	}

	/**
	 * защита гуля от урона не от RC оружия или кагуне, предмет или броня столкнувшиеся с таким оружием или кожей гуля ломаются
	 */
  	@SubscribeEvent
	public static void setGhoulProtectOnMeleeAndRangeAttacks(LivingAttackEvent e) {
        if (OshieteDamageSource.isRCorBulletDamage(e.getSource())) return;

		EntityLivingBase target = e.getEntityLiving();
		PersonStats stats = PersonStats.getStats(target);

		if (stats!=PersonStats.EMPTY && !stats.isVulnerable() && e.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
			ItemStack weapon = attacker.getHeldItemMainhand();

			if (!(weapon.getItem() instanceof IKuinke) && (!weapon.isEmpty() || attacker instanceof EntityPlayer || attacker instanceof EntityHuman))
			{
				if (!e.getSource().isProjectile())
					weapon.damageItem(weapon.getMaxDamage()+1, attacker);
				if (!target.world.isRemote)
					target.world.playSound(null, target.getPosition(), InitSounds.rebound, SoundCategory.AMBIENT, 1.0F, 1.0F);
				if (!e.getSource().isProjectile() || e.getSource().getImmediateSource() instanceof EntityArrow)
					e.setCanceled(true);
			}
		}
		else if (stats!=PersonStats.EMPTY && !stats.isVulnerable())
		{
			if (e.getSource().getImmediateSource() instanceof EntityArrow) {
				e.setCanceled(true);
				if (!target.world.isRemote)
					target.world.playSound(null, target.getPosition(), InitSounds.rebound, SoundCategory.AMBIENT, 1.0F, 1.0F);
			}
		}
	}
	@SubscribeEvent
	public static void ghoulBrokeArmorAndShield(LivingAttackEvent e) {
		if (!OshieteDamageSource.isRCMelleDamage(e.getSource())) return;

		EntityLivingBase target = e.getEntityLiving();
		for (ItemStack armor : target.getArmorInventoryList()) {
			if (armor.getItem() instanceof ItemArmor && !(armor.getItem() instanceof IAntiGhoulArmor)) {
				armor.damageItem(armor.getMaxDamage()+1, target);
				break;
			}
		}

		if (target.getActiveItemStack().getItem() instanceof ItemShield) {
			target.getActiveItemStack().damageItem(target.getActiveItemStack().getMaxDamage()+1, target);
		}
	}


	//TODO частицы кагуне не работают
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
		entity.world.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, f1, f2, f3);
	}

	@SubscribeEvent
	public static void jump(LivingEvent.LivingJumpEvent e)
	{
		PersonStats stats = PersonStats.getStats(e.getEntityLiving());
		if (stats!=PersonStats.EMPTY && (!stats.isGhoul() || stats.isSpeedModeActive()))
		{
			e.getEntityLiving().motionY += stats.getJumpHeight();
		}
	}
	@SubscribeEvent
	public static void fall(LivingFallEvent e)
	{
		PersonStats stats = PersonStats.getStats(e.getEntityLiving());
		if (stats!=PersonStats.EMPTY)
		{
			if (e.getDistance() <= stats.getJumpHeight() * 30)
				e.setDistance(0);
		}
	}

	@SubscribeEvent
	public void removeExhaustion(PlayerTickEvent e) {
		if (e.player.ticksExisted%5==0) {
			PersonStats stats = e.player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
			if (stats.isGhoul() && (!stats.isSpeedModeActive() && !stats.isKaguneActive() || stats.ukaku()))
				e.player.getFoodStats().addExhaustion(-0.125F);
		}
	}
}

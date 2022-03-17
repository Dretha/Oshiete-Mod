package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.ServerGhoulPoisonedMessage;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.interfaces.IAntiGhoulArmor;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class AbilityHandler {

    //ghoul eat
    @SubscribeEvent
    public static void setLastFoodAmount(LivingEntityUseItemEvent.Tick e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			if (e.getItem().getItem() instanceof ItemFood) {
				capa.setLastFoodAmount(player.getFoodStats().getFoodLevel());
			}
			capa.setLastUseItem(e.getItem().getItem());
		}
    }
    @SubscribeEvent
    public static void ghoulEat(LivingEntityUseItemEvent.Finish e) { 
    	if (e.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) e.getEntityLiving();
    		ICapaHandler capa = EventsHandler.getCapaMP(player);
			Item item = player.getActiveItemStack().getItem();
    		if (capa.isGhoul() && !(item instanceof ItemGhoulFood) && (item instanceof ItemFood || capa.getLastUseItem() instanceof ItemFood) && item!=Items.ROTTEN_FLESH) {
    			Oshiete.NETWORK.sendToServer(new ServerGhoulPoisonedMessage());
    		}
    	}
    }
    @SubscribeEvent
    public static void ghoulEatCake(PlayerInteractEvent.RightClickBlock e) {
		EntityPlayer player = e.getEntityPlayer();
		ICapaHandler capa = EventsHandler.getCapaMP(player);
    	if (capa.isGhoul() && e.getWorld().getBlockState(e.getPos()).getBlock() instanceof BlockCake) {
			Oshiete.NETWORK.sendToServer(new ServerGhoulPoisonedMessage());
		}
    }
  	// TODO изменить регенерацию рс
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
            	}
            	
            }
  		}
  	}

	/**
	 * защита гуля от урона не от RC оружия или кагуне, предмет или броня столкнувшиеся с таким оружием или кожей гуля ломаются
	 */
  	@SubscribeEvent
	public static void setGhoulProtectOnMeleeAndRangeAttacks(LivingAttackEvent e) {
		  // TODO нельзя атаковать гуля всем мобам
        if (OshieteDamageSource.isRCorBulletDamage(e.getSource())) return;

		EntityLivingBase target = e.getEntityLiving();
		PersonStats targetStats = PersonStats.getStats(target);

		if (targetStats!=PersonStats.EMPTY && !targetStats.isVulnerable() && e.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
			ItemStack weapon = attacker.getHeldItemMainhand();
			boolean isGhoulFistHit = PersonStats.getStats(attacker).isGhoul() && weapon.isEmpty();

			if (!(weapon.getItem() instanceof IKuinke) && (!weapon.isEmpty() || !isGhoulFistHit))
			{
				if (!e.getSource().isProjectile())
					weapon.damageItem(weapon.getMaxDamage()+1, attacker);
				if (!target.world.isRemote)
					target.world.playSound(null, target.getPosition(), InitSounds.rebound, SoundCategory.AMBIENT, 1.0F, 1.0F);
				if (!e.getSource().isProjectile() || e.getSource().getImmediateSource() instanceof EntityArrow)
					e.setCanceled(true);
			}
		}
		else if (targetStats!=PersonStats.EMPTY && !targetStats.isVulnerable())
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
	public void hungerControl(TickEvent.PlayerTickEvent e)
	{
		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
		PersonStats stats = capa.personStats();

		if (stats.isGhoul() && !stats.isKaguneActive() && !stats.isSpeedModeActive()) {
			if (capa.getLastExhaustion()<e.player.getFoodStats().foodExhaustionLevel)
			{
				e.player.getFoodStats().foodExhaustionLevel = (e.player.getFoodStats().foodExhaustionLevel - capa.getLastExhaustion())/3 + capa.getLastExhaustion();
			}
		}

		capa.setLastExhaustion(e.player.getFoodStats().foodExhaustionLevel);
	}
}

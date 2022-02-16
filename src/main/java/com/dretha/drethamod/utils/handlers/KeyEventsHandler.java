package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.*;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.controllers.SmellController;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
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
			ActionController controller = capa.getKaguneActivateController();
			PersonStats stats = capa.personStats();
    		if (controller.endAct(player.ticksExisted) && !stats.isKaguneActive() && stats.isGhoul())
			{
				controller.setTicksPre(player.ticksExisted);
    			stats.releaseKagune(player);
    		}
    		else if (controller.endAct(player.ticksExisted) && stats.isKaguneActive() && ((stats.getKagune()!=null && !stats.getKagune().transform()) || (UkakuState.haveJustFlame(stats))))
			{
				controller.setTicksPre(player.ticksExisted);
    			stats.admitKagune(player);
    		}
    	}
    }

   //key listener activate ghoul speed mode
   @SubscribeEvent
   public void activateSpeedMode(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_ACTIVATE_GHOUL_SPEED_MODE.isPressed()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			ActionController controller = capa.getSpeedModeController();
			PersonStats stats = capa.personStats();
			// TODO понерфить скорость в скоростном режиме
    		if (controller.endAct(player.ticksExisted, stats.isGhoul() && !stats.isSpeedModeActive()))
			{
    			stats.setSpeedModeActive(true);
    			stats.setKaguneActive(true);
				stats.applyAtrSpeedMode(player);
    		}
    		else if (controller.endAct(player.ticksExisted, stats.isGhoul() && stats.isSpeedModeActive()))
			{
    			stats.setSpeedModeActive(false);
    			if (!stats.isKaguneActive())
    				stats.setKakuganActive(false);
				stats.removeAtrSpeedMode(player);
    		}
    	}
   }
	
   
   //key listener impact
   @SubscribeEvent
   public void impactcontroller(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown() && !KeybindsRegister.KEY_ACTIVITY.isKeyDown()) {
			EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
			PersonStats stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
			if (stats.ukaku() && !UkakuState.haveLimb(stats)) return;
			EntityKagune kagune = stats.getKagune();

			if (kagune!=null && stats.isKaguneActive() && kagune.getImpactController().endAct(player.ticksExisted, stats.getImpactType().speed) && !kagune.transform() && !stats.isBlock()) {

				Oshiete.NETWORK.sendToServer(new KaguneImpactMessage(stats.getDamage(), stats.getImpactType() == ImpactType.THRUST));

				kagune.getImpactController().setTicksPre(player.ticksExisted);

    		}
    	}
   }
   
   
    //key listener impact
    @SubscribeEvent
    public void changeImpactMode(KeyInputEvent e) {
	    if (KeybindsRegister.KEY_HIT_MODE.isKeyDown()) {
		    EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
   		    ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			ActionController controller = capa.getImpactModeController();
            PersonStats stats = capa.personStats();
   		
   		    if (controller.endAct(player.ticksExisted, true)) {
   		  	    stats.changeImpactType();
   		    }
		    if (player.getHeldItemMainhand().getItem() instanceof ItemFirearm) {
		 	    ItemFirearm firearm = (ItemFirearm) player.getHeldItemMainhand().getItem();
			    firearm.reload(player.getHeldItemMainhand(), player.world, player);
		    }
	    }
    }

	//key listener shoot and block
	@SubscribeEvent
	public void keyBlock(PlayerTickEvent e) {
		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
		PersonStats stats = capa.personStats();
		ActionController controller = capa.getShootController();
		if (KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && !e.player.world.isRemote && stats.ukaku() && UkakuState.haveFlame(stats) && stats.isKaguneActive() && stats.getKagune()!=null && !stats.getKagune().transform()) {
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if (controller.endAct(player.ticksExisted, stats.getRClevel()>15 || player.isCreative())) {
				controller.setTicksPre(player.ticksExisted);
				EntityRCShard entityrcshard = new EntityRCShard(player.world, player);
				e.player.world.spawnEntity(entityrcshard);
				SoundPlayer.play(e.player, InitSounds.ukaku_shooting, 0.5F);
				if (!player.isCreative()) {
					stats.removeRClevel(15);
				}
			}
		} else if (KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && stats.isKaguneActive() && !stats.ukaku()) {
			stats.setBlock(true);
		} else if (!KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && capa.isGhoul()) {
			stats.setBlock(false);
		}
	}
	@SubscribeEvent
	public static void block(LivingAttackEvent e) {
		if (!e.getEntityLiving().world.isRemote && (e.getEntityLiving() instanceof EntityPlayer || e.getEntityLiving() instanceof EntityHuman)) {

			if (OshieteDamageSource.isBlockDamage(e.getSource())) return;

			EntityLivingBase blocker = e.getEntityLiving();
			PersonStats stats = PersonStats.getStats(blocker);
			Entity immediate = e.getSource().getImmediateSource();

			if (!stats.isBlock()) return;

			e.setCanceled(true);

			if (immediate instanceof EntityLivingBase) {
				EntityLivingBase target = (EntityLivingBase) immediate;
				PersonStats targetStats = PersonStats.getStats(target);

				float strength = (stats.exactRank() + 0.6F - targetStats.exactRank())/2;
				if (strength>0)
					knockback(blocker, target, strength);
				else if (strength<0)
					knockback(target, blocker, -strength);
			}

			int protection = 0;

			if (stats.isKaguneActive())
			{
				SoundPlayer.play(blocker.world, InitSounds.hit_ground_kagune_2, blocker.getPosition());
				protection = stats.getProtection();

				if (protection < (int)e.getAmount()) {
					stats.removeRClevel((int)e.getAmount() - protection);
					// TODO увеличить цену за фэйл блока и добавить за успех блока
				}

				if (stats.getKagune()!=null) {
					stats.getKagune().getBlockAnimController().endAct(blocker.ticksExisted, true);
				}
			}
			else if (blocker.getActiveItemStack().getItem() instanceof IKuinkeMelee)
			{
				SoundPlayer.play(blocker, InitSounds.kuinke_block);
				ItemStack kuinkeStack = blocker.getActiveItemStack();
				IKuinkeMelee kuinke = (IKuinkeMelee) blocker.getActiveItemStack().getItem();
				protection = kuinke.getBlockValue(blocker.getActiveItemStack());

				if (protection < (int)e.getAmount()) {
					kuinkeStack.damageItem((int)e.getAmount()-protection, (EntityLivingBase) e.getSource().getTrueSource());
				}
			}

			if (protection * 2 < (int)e.getAmount())
			{
				int hurt = (int)e.getAmount() - protection * 2;
				blocker.attackEntityFrom(OshieteDamageSource.causeBreakBlockAttack(), hurt);
			}
		}
	}

	private static void knockback(EntityLivingBase attacker, EntityLivingBase target, float strength) {
		target.knockBack(attacker, strength, attacker.posX - target.posX, attacker.posZ - target.posZ);
	}

	//key listener smell
	@SubscribeEvent
	public void sniff(KeyInputEvent e) {
		if (KeybindsRegister.KEY_SMELL.isKeyDown()) {
			EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
			ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
			SmellController controller = capa.getSmellController();

			if (controller.endAct(player.ticksExisted, capa.isGhoul())) {
				Oshiete.NETWORK.sendToServer(new SniffMessage(controller.getRadius(), controller.getDuration()));
			}
		}
	}

	//open clothes inventory
	@SubscribeEvent
	public void openClothesInventory(KeyInputEvent event) {
		if (KeybindsRegister.KEY_OPEN_CLOTHES_INVENTORY.isPressed()) {
			Oshiete.NETWORK.sendToServer(new OpenClothesInventoryMessage());
			// TODO решить вопрос с шифтом в инвентаре
		}
	}

	@SubscribeEvent
	public static void forceSpeedKeyEvent(InputEvent.KeyInputEvent e) {
		EntityPlayer player = EventsHandler.getPlayerMP(player());
		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
		PersonStats stats = capa.personStats();
		ActionController controller = capa.getForceSpeedController();
		if (controller.endAct(player.ticksExisted, KeybindsRegister.KEY_ACTIVITY.isKeyDown() && WASD()))
		{
			float angle = 0F;

			if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown())
				angle = 270F;
			else if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown())
				angle = 90F;
			else if (Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown())
				angle = 180F;

			stats.forceSpeed(player(), angle);

			if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown() && stats.isGhoul() && stats.getRClevel()>12)
				capa.getForceThrustController().setTicksPre(player.ticksExisted);

			SoundPlayer.play(player, InitSounds.sweep_heavy);
		}
	}
	private static EntityPlayer player() {
		return Minecraft.getMinecraft().player;
	}

	public static boolean WASD() {
		return Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown();
	}

	@SubscribeEvent
	public void waitingForceThrust(PlayerTickEvent e) {
		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
		if (capa.isGhoul() && capa.getForceThrustController().isAct(e.player.ticksExisted)) {
			Oshiete.NETWORK.sendToServer(new ForceThrustMessage((int) (capa.personStats().getDamage() * (capa.personStats().ukaku() ? 2 : 1.4))));
			// TODO сделать атаку в рывке только для укаку
		}
	}
}

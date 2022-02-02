package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneUnit;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Entity301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Model301;
import com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301.Render301;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.*;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

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
    	if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown() && !(KeybindsRegister.KEY_ACTIVITY.isKeyDown() && WASD())) {
			EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
			PersonStats stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
			if (stats.ukaku() && !UkakuState.haveLimb(stats)) return;
			EntityKagune kagune = stats.getKagune();

			if (kagune!=null && stats.isKaguneActive() && kagune.getImpactController().endAct(player.ticksExisted) && !kagune.transform() && !stats.isBlock()) {

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
            PersonStats stats = capa.personStats();
   		
   		    if (capa.getImpactModeTicksPre()+3<=player.ticksExisted) {
   		  	    stats.changeImpactType();
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
		PersonStats stats = capa.personStats();
		if (capa!= null && KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && !e.player.world.isRemote && stats.ukaku() && UkakuState.haveFlame(stats) && stats.isKaguneActive() && stats.getKagune()!=null && !stats.getKagune().transform()) {
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if (player.ticksExisted%3==0 && capa.getShootTicksPre()+2<=player.ticksExisted && (stats.getRClevel()>15 || player.isCreative())) {
				capa.setShootTicksPre(player.ticksExisted);
				EntityRCShard entityrcshard = new EntityRCShard(player.world, player);
				entityrcshard.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1F);
				entityrcshard.setIsCritical(false);
				entityrcshard.setDamage(5);
				entityrcshard.setKnockbackStrength(0);
				entityrcshard.setFire(0);
// TODO это добавить в шард
				e.player.world.spawnEntity(entityrcshard);

				e.player.world.playSound(null, player.getPosition(), InitSounds.ukaku_shooting, SoundCategory.PLAYERS, 0.5F, 1.0F);
				if (!player.isCreative()) {
					stats.removeRClevel(15);
				}
			}
		} else if (KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && !e.player.world.isRemote && stats.isKaguneActive() && !stats.ukaku()) {
			stats.setBlock(true);
		} else if (!KeybindsRegister.KEY_BLOCK_KAGUNE.isKeyDown() && capa.isGhoul()) {
			stats.setBlock(false);
		}
	}
	@SubscribeEvent
	public static void block(LivingAttackEvent e) {
// TODO это упростить
		if (e.getEntityLiving() instanceof EntityPlayer && !e.getEntityLiving().world.isRemote) {
			EntityPlayerMP player = (EntityPlayerMP)e.getEntityLiving();
			ICapaHandler capa = EventsHandler.getCapaMP(player);
			PersonStats stats = capa.personStats();
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
// TODO эти сорсы убрать
			if (source!=null && stats.isBlock())
			{

				e.setCanceled(true);

				if (immediate instanceof EntityLivingBase) {
					EntityLivingBase target = (EntityLivingBase) immediate;
					PersonStats targetStats = PersonStats.getStats(target);

					float strength = (stats.exactRank() + 0.3F - targetStats.exactRank())/2;
					if (strength>0)
						knockback(player, target, strength);
					else if (strength<0)
						knockback(target, player, -strength);
				}

				int protection = 0;

				if (stats.isKaguneActive())
				{
					SoundPlayer.play(player.world, InitSounds.hit_ground_kagune_2, player.getPosition());
					protection = stats.getProtection();

					if (protection < (int)e.getAmount()) {
						stats.removeRClevel((int)e.getAmount() - protection);
					}

					if (stats.getKagune()!=null) {
						stats.getKagune().getBlockAnimController().endAct(player.ticksExisted, true);
					}
				}
				else if (player.getActiveItemStack().getItem() instanceof IKuinkeMelee)
				{
					player.world.playSound(null, player.getPosition(), InitSounds.kuinke_block, SoundCategory.PLAYERS, 1.0F, 1.0F);
					ItemStack kuinkeStack = player.getActiveItemStack();
					IKuinkeMelee kuinke = (IKuinkeMelee) player.getActiveItemStack().getItem();
					protection = kuinke.getBlockValue(player.getActiveItemStack());

					if (protection < (int)e.getAmount()) {
						kuinkeStack.damageItem((int)e.getAmount()-protection, (EntityLivingBase) e.getSource().getTrueSource());
					}
				}

				if (protection * 2 < (int)e.getAmount())
				{
					int hurt = protection * 2 - (int)e.getAmount();
					player.setHealth(player.getHealth() + hurt);
					//setHurt(player, entity, immediate, source.getDamageType(),  (int)e.getAmount() - kuinke.getBlockValue()*2);
				}
			}
		}
	}

	private static void knockback(EntityLivingBase attacker, EntityLivingBase target, float strength) {
		target.knockBack(attacker, strength, attacker.posX - target.posX, attacker.posZ - target.posZ);
	}

	public static void setHurt(EntityPlayer target, EntityLivingBase attacker, Entity immediate, String source, int damage) {
		if (target.getHealth()<=damage)
		{
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("source", source);
			compound.setUniqueId("entity", attacker.getUniqueID());
			compound.setUniqueId("immediate", immediate.getUniqueID());
			Oshiete.NETWORK.sendToServer(new KillPlayerMessage(compound, damage));
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
				capa.setSmellTicksPre(player.ticksExisted);
				Oshiete.NETWORK.sendToServer(new SniffMessage(capa.getSmellRadius(), capa.getSmellDuration()));
			}
		}
	}

	//open clothes inventory
	@SubscribeEvent
	public void openClothesInventory(KeyInputEvent event) {
		if (KeybindsRegister.KEY_OPEN_CLOTHES_INVENTORY.isPressed()) {
			Oshiete.NETWORK.sendToServer(new OpenClothesInventoryMessage());
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

			if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown())
				angle = 225;
			else if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown())
				angle = 315;
			else if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown())
				angle = 135;
			else if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown())
				angle = 45;
			else if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown())
				angle = 270F;
			else if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown())
				angle = 90F;
			else if (Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown())
				angle = 180F;

			stats.forceSpeed(player(), angle);

			if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown() && stats.isGhoul())
				capa.getForceThrustController().setTicksPre(player.ticksExisted);
		}
	}
	private static EntityPlayer player() {
		return Minecraft.getMinecraft().player;
	}

	public static boolean WASD() {
		return Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() || Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown();
	}
/*
	@SubscribeEvent
	public void waitingForceThrust(PlayerTickEvent e) {
		ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
		if (capa.isGhoul() && capa.getForceThrustController().isAct(e.player.ticksExisted)) {
			Oshiete.NETWORK.sendToServer(new ForceThrustMessage((int) (capa.personStats().getDamage() * (capa.personStats().ukaku() ? 2 : 1.4))));
		}
	}
	// TODO доделать удар в рывке
 */
}

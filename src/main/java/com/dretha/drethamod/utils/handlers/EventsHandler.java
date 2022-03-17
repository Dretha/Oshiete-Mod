package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.capability.firearm.CapaFirearmProvider;
import com.dretha.drethamod.capability.world.WorldCapaProvider;
import com.dretha.drethamod.client.gui.StartGui;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.Kakuho;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.firearm.ItemMagazine;
import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.items.kuinkes.QColdSteel;
import com.dretha.drethamod.items.kuinkes.Weapons;
import com.dretha.drethamod.layers.LayerKagune;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.stats.PersonStats;
import com.dretha.drethamod.worldevents.HeadquartersCCG;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

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


    public static final ResourceLocation PLAYER_CAP = new ResourceLocation(Reference.MODID, "capa");
    public static final ResourceLocation FIREARM_CAP = new ResourceLocation(Reference.MODID, "firearmcapa");
    public static final ResourceLocation WORLD_CAP = new ResourceLocation(Reference.MODID, "worldcapa");
    
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        try {
            event.addCapability(PLAYER_CAP, new CapaProvider());
        } catch (Exception e) {
	    }
    }
    @SubscribeEvent
    public void attachFirearmCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (!(event.getObject().getItem() instanceof ItemFirearm)) return;
        try {
            event.addCapability(FIREARM_CAP, new CapaFirearmProvider());
        } catch (Exception e) {
        }
    }
    @SubscribeEvent
    public void attachMagazineCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (!(event.getObject().getItem() instanceof ItemMagazine)) return;
        try {
            event.addCapability(FIREARM_CAP, new CapaFirearmProvider());
        } catch (Exception e) {
        }
    }
    @SubscribeEvent
    public void attachWorldCapability(AttachCapabilitiesEvent<World> event) {
        try {
            event.addCapability(WORLD_CAP, new WorldCapaProvider());
        } catch (Exception e) {
        }
    }
	
    @SubscribeEvent
    public static void cloneCapa(PlayerEvent.Clone e) {
        final ICapaHandler original = getHandler(e.getOriginal());
        final ICapaHandler clone = getHandler(e.getEntity());
        PersonStats cloneStats = clone.personStats();

        NBTTagCompound compound = new NBTTagCompound();
        original.personStats().writeToNBT(compound);
        cloneStats.readFromNBT(compound);

        if (e.isWasDeath()) {
            e.getEntityPlayer().setHealth(e.getEntityPlayer().getMaxHealth());
            cloneStats.setRClevel(cloneStats.MaxRClevel());
            cloneStats.setKaguneActive(false);
            cloneStats.setKakuganActive(false);
            cloneStats.setSpeedModeActive(false);
            cloneStats.updateCharacteristics((EntityLivingBase) e.getEntity());
            cloneStats.setShardCountInEntity(0);
        }

        clone.getSmellController().setRadiusAndDuration(original.getSmellController().getRadius(), original.getSmellController().getDuration());
    }
    
    
    
    //playerlist
    private static final LinkedList<EntityPlayer> player_list = new LinkedList<EntityPlayer>();
    public static void addPlayerToList(EntityPlayer player) {
        if (!player_list.contains(player)) {
            player_list.add(player);
        }
    }
    @SubscribeEvent
    public void PlayerLoggedInList(PlayerLoggedInEvent e) {
    	if (!player_list.contains(e.player)) {
    		player_list.add(e.player);
    		PersonStats stats = e.player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
    		if (stats.isKaguneActive())
    			stats.updateEntityKagune(e.player);
    		stats.updateSpeedAttribute(e.player);
            /*
            ICapaHandler capa = getCapaMP(e.player);
            if (capa!=null && capa.isFirstJoin() && !e.player.world.isRemote) {
                capa.setJoin();
                Oshiete.NETWORK.sendTo(new SendPlayerListMessage(), (EntityPlayerMP) e.player);
                Oshiete.NETWORK.sendTo(new OpenGuiMessage(), (EntityPlayerMP) e.player);
            }
            */
            e.player.sendMessage(new TextComponentString("Oshiete Mod is loaded."));
    	}
    }
    @SubscribeEvent
    public void openStartGui(ClientChatReceivedEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        ICapaHandler capa = getCapaMP(player);
        if (capa!=null && capa.isFirstJoin() && player.world.isRemote) {
            capa.setJoin();
            Minecraft.getMinecraft().displayGuiScreen(new StartGui());
        }
    }
    @SubscribeEvent
    public void PlayerLoggedOutList(PlayerLoggedOutEvent e) {
        player_list.remove(e.player);
    }
    @SubscribeEvent
    public void PlayerCloneEventList(PlayerEvent.Clone e) {
        player_list.remove(e.getOriginal());
    	if (!player_list.contains(e.getEntityPlayer())) {
    		player_list.add(e.getEntityPlayer());
    	}
    }
    @SubscribeEvent
    public void playerJoinWorld(EntityJoinWorldEvent e) {
    	if (e.getEntity() instanceof EntityPlayer) {
    	}
    }
    
    public static EntityPlayer getPlayerMP(EntityPlayer player) {
    	return player_list.get(player_list.indexOf(player));
    }
    public static ICapaHandler getCapaMP(EntityPlayer player) {
        try {
            return player_list.get(player_list.indexOf(player)).getCapability(CapaProvider.PLAYER_CAP, null);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    
    
    
    //humanslist
    private static final LinkedList<EntityHuman> human_list = new LinkedList<EntityHuman>();
    @SubscribeEvent
    public void humanLoggedInList(EntityJoinWorldEvent e) {
    	if (e.getEntity() instanceof EntityHuman && !human_list.contains(e.getEntity())) {
            EntityHuman human = (EntityHuman) e.getEntity();
            PersonStats stats = ((EntityHuman) e.getEntity()).personStats();
    		human_list.add((EntityHuman) e.getEntity());
    		if (stats.isKaguneActive())
                stats.updateEntityKagune(human);
            stats.updateSpeedAttribute(human);
    	}
    }
    @SubscribeEvent
    public void humanLoggedOutList(LivingDeathEvent e) {
    	if (human_list.contains(e.getEntity()) && e.getEntity() instanceof EntityHuman) {
    		human_list.remove(e.getEntity());
    	}
    }
    
    public static EntityHuman getHumanMP(EntityHuman human) {
        if (human_list.contains(human))
    	    return human_list.get(human_list.indexOf(human));
        else return null;
    }
    public static boolean hasHumanMP(EntityHuman human) {
        return human_list.contains(human);
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
    	if (e.getEntityLiving() instanceof EntityVillager && !e.getEntity().world.isRemote) {
            e.getEntityLiving().entityDropItem(new ItemStack(InitItems.HUMAN_MEAT, Oshiete.random.nextInt(2) + 1), 0);
        }
    }

    /**on a destroy kuinke*/
    @SubscribeEvent
    public static void destroyKuinke(PlayerDestroyItemEvent e) {
        if (e.getOriginal().getItem() instanceof IKuinke) {
            IKuinke kuinke = (IKuinke) e.getOriginal().getItem();
            PersonStats stats = PersonStats.getStats(e.getEntityLiving());
            if (stats!=null) stats.setBlock(false);
            int rand = Oshiete.random.nextInt(5)-2;
            if (kuinke.getCountQSteelShards() > 0)
                e.getEntityLiving().entityDropItem(new ItemStack(InitItems.KUINKE_STEEL_SHARD, kuinke.getCountQSteelShards()+rand), 0);
            if (kuinke.getCountKaguneShards() > 0)
                e.getEntityLiving().entityDropItem(new ItemStack(InitItems.KAGUNE_SHARD, kuinke.getCountKaguneShards()+rand), 0);
        }
    }

    @SubscribeEvent
    public static void kaguneFirstView(RenderSpecificHandEvent e) {
        ICapaHandler capa = EventsHandler.getCapaMP(Minecraft.getMinecraft().player);
        if (capa==null) return;
        PersonStats stats = capa.personStats();
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if (stats.isKaguneActive() && stats.getKagune()!=null)
        {
        	EntityKagune kagune = stats.getKagune();

            float limbSwing = kagune.getRenderData().get(0);
            float limbSwingAmount = kagune.getRenderData().get(1);
            float partialTicks = kagune.getRenderData().get(2);
            float ageInTicks = kagune.getRenderData().get(3);
            float netHeadYaw = kagune.getRenderData().get(4);
            float headPitch = kagune.getRenderData().get(5);
            float scale = kagune.getRenderData().get(6);
			
            LayerKagune.renderKagune(player, stats, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, Minecraft.getMinecraft().getRenderManager(), true);
        }
    }
    
    @SubscribeEvent
    public void onCameraUpdate(EntityViewRenderEvent.CameraSetup e) {
        if (Minecraft.getMinecraft().gameSettings.thirdPersonView==1)
            GL11.glTranslatef(getCapaMP(Minecraft.getMinecraft().player).getCameraOffset(), 0, 0);
    }
    
    //clothes inventory
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) 
    {
        if (event.isWasDeath()) return;
        PersonStats novel = event.getEntityPlayer().getCapability(CapaProvider.PLAYER_CAP, null).personStats();
        PersonStats old = EventsHandler.getCapaMP(event.getOriginal()).personStats();
        novel.copyInventory(old);
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntity();
            PersonStats stats = EventsHandler.getCapaMP(player).personStats();
            ClothesInventory inv = stats.getInventory();
            dropAllItems(player, inv);
            inv.clear();
        }
    }
    private static void dropAllItems(EntityPlayer player, ClothesInventory inventory){
        NonNullList<ItemStack> aitemstack = inventory.getStacks();
        for (int i = 0; i < aitemstack.size(); ++i) {
            if (!aitemstack.get(i).isEmpty()) {
                player.dropItem(aitemstack.get(i), true, false);
            }
        }
    }

    @SubscribeEvent
    public static void impactKuinkeAir(PlayerInteractEvent.LeftClickEmpty e)
    {
        EntityPlayer player = getPlayerMP(e.getEntityPlayer());
        if (player.getHeldItemMainhand().getItem() instanceof IKuinkeMelee) {
            if (!player.world.isRemote && player.getHeldItemMainhand().getItem() instanceof QColdSteel) {
                QColdSteel weapon = (QColdSteel) player.getHeldItemMainhand().getItem();
                player.world.playSound(null, player.getPosition(), weapon.getWeapon().soundAir, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
            IKuinkeMelee coldSteel = (IKuinkeMelee) player.getHeldItemMainhand().getItem();
            coldSteel.playImpact(player.getHeldItemMainhand(), player, player.world);
        }
    }
    @SubscribeEvent
    public static void impactKuinkeEntity(LivingAttackEvent e)
    {
        if (e.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase base = (EntityLivingBase) e.getSource().getTrueSource();
            if (base.getHeldItemMainhand().getItem() instanceof IKuinkeMelee) {
                if (!base.world.isRemote && base.getHeldItemMainhand().getItem() instanceof QColdSteel) {
                    QColdSteel weapon = (QColdSteel) base.getHeldItemMainhand().getItem();
                    base.world.playSound(null, base.getPosition(), weapon.getWeapon().soundAttack, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
                IKuinkeMelee coldSteel = (IKuinkeMelee) base.getHeldItemMainhand().getItem();
                coldSteel.playImpact(base.getHeldItemMainhand(), base, base.world);
            }
        }
    }

    @SubscribeEvent
    public static void craftingQColdSteel(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent e)
    {
        if (e.crafting.getItem() instanceof QColdSteel) {
            for (int i=0; i<e.craftMatrix.getSizeInventory(); i++) {
                if (e.craftMatrix.getStackInSlot(i).getItem() instanceof Kakuho)
                {
                    ItemStack kakuho = e.craftMatrix.getStackInSlot(i);
                    Kakuho kakuhoItem = (Kakuho)kakuho.getItem();
                    int modif = kakuho.getTagCompound().getInteger("RCpoints");

                    GhoulType ghoulType = kakuhoItem.ghoulType;

                    Weapons weapons = ((QColdSteel)e.crafting.getItem()).getWeapon();
                    QColdSteel.buildColdSteel(e.crafting, modif, weapons, ghoulType);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void notCorpseAttack(LivingHurtEvent e) {
        if (e.getEntityLiving() instanceof EntityCorpse) {
            if (!e.getSource().isFireDamage())
                e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void monsterSetAttackHumans(EntityJoinWorldEvent e)
    {
        if (!e.getWorld().isRemote && e.getEntity().isCreatureType(EnumCreatureType.MONSTER, false) && !(e.getEntity() instanceof EntityHuman) && !(e.getEntity() instanceof EntityPlayer) && e.getEntity() instanceof EntityCreature && !(e.getEntity() instanceof EntityEnderman)) {
            if (e.getEntity() instanceof EntitySpider && e.getWorld().isDaytime()) return;
            ((EntityLiving) e.getEntity()).targetTasks.addTask(3, new EntityAINearestAttackableTarget((EntityCreature) e.getEntity(), EntityHuman.class, true));
        }
    }

    @SubscribeEvent
    public void onCriminalDeath(LivingDeathEvent e) {
        HeadquartersCCG headquartersCCG = e.getEntityLiving().world.getCapability(WorldCapaProvider.WORLD_CAP, null).getHeadquartersCCG();
        if (!(e.getEntityLiving() instanceof EntityPlayer) && headquartersCCG.isWanted(e.getEntityLiving())) {
            headquartersCCG.removeWanted(e.getEntityLiving());
        }
    }

    @SubscribeEvent
    public void soundPlay(PlaySoundEvent e) {
        if (Minecraft.getMinecraft().currentScreen instanceof StartGui && e.getSound()!=((StartGui) Minecraft.getMinecraft().currentScreen).opening && !SoundPlayer.soundEquals(e.getSound(), InitSounds.let_out_kagune)) {
            e.setResultSound(null);
        }
    }
}

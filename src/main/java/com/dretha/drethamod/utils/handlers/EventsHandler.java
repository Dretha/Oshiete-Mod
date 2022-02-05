package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.capability.firearm.CapaFirearmProvider;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.items.Kakuho;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.items.kuinkes.QColdSteel;
import com.dretha.drethamod.items.kuinkes.Weapons;
import com.dretha.drethamod.layers.LayerKagune;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
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
        }

        clone.getSmellController().setRadiusAndDuration(original.getSmellController().getRadius(), original.getSmellController().getDuration());
    }
    
    
    
    //playerlist
    private static final LinkedList<EntityPlayer> player_list = new LinkedList<EntityPlayer>();
    @SubscribeEvent
    public void PlayerLoggedInList(PlayerLoggedInEvent e) {
    	if (!player_list.contains(e.player)) {
    		player_list.add(e.player);
    		PersonStats stats = e.player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
    		if (stats.isKaguneActive())
    			stats.updateEntityKagune(e.player);
    		stats.updateSpeedAttribute(e.player);
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
    	return player_list.get(player_list.indexOf(player)).getCapability(CapaProvider.PLAYER_CAP, null);
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
    	return human_list.get(human_list.indexOf(human));
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
    public static void fqwgwgw(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent e) {
    }
    
    //worked
    @SubscribeEvent
    public static void kaguneFirstView(RenderSpecificHandEvent e) {
        PersonStats stats = EventsHandler.getCapaMP(Minecraft.getMinecraft().player).personStats();
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if (stats.isKaguneActive() && stats.getKagune()!=null) {
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            // TODO переписать listF попроще
        	
        	EntityKagune kagune = null;
        	float limbSwing=0;
        	float limbSwingAmount=0;
			float partialTicks=0;
			float ageInTicks=0;
			float netHeadYaw=0;
			float headPitch=0;
			float scale=0;
			
			do {
	        	kagune = stats.getKagune();
	        } while (stats.getKagune()==null);
			
			limbSwing = kagune.getListF().get(0);
			limbSwingAmount = kagune.getListF().get(1);
			partialTicks = kagune.getListF().get(2);
			ageInTicks = kagune.getListF().get(3);
			netHeadYaw = kagune.getListF().get(4);
			headPitch = kagune.getListF().get(5);
			scale = kagune.getListF().get(6);
			
            LayerKagune.renderKagune(player, stats, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, Minecraft.getMinecraft().getRenderManager(), true);

        	GlStateManager.disableRescaleNormal();
        }
    }
    
    @SubscribeEvent
    public void onCameraUpdate(EntityViewRenderEvent.CameraSetup e) {
        if (Minecraft.getMinecraft().gameSettings.thirdPersonView==1)
            GL11.glTranslatef(-0.5F, 0, 0);
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
    public static void renderFirearmPose(RenderPlayerEvent.Specials.Pre e)
    {
        EntityPlayer player = getPlayerMP(e.getEntityPlayer());
        if (player.getHeldItemMainhand().getItem() instanceof ItemFirearm)
        {
            ModelPlayer modelplayer = e.getRenderer().getMainModel();

            ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
            ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.BOW_AND_ARROW;

            if (e.getEntityPlayer().getPrimaryHand() == EnumHandSide.RIGHT)
            {
                modelplayer.rightArmPose = modelbiped$armpose;
                modelplayer.leftArmPose = modelbiped$armpose1;
            }
            else
            {
                modelplayer.rightArmPose = modelbiped$armpose1;
                modelplayer.leftArmPose = modelbiped$armpose;
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
                    QColdSteel.modificateWeapon(e.crafting, modif, weapons, ghoulType);
                    return;
                }
            }
        }
    }
    public static int getKakuhoRank(Kakuho kakuho) {

        return 0;
    }

    @SubscribeEvent
    public static void corpseAttack(LivingHurtEvent e) {
        if (e.getEntityLiving() instanceof EntityCorpse) {
            if (!e.getSource().isFireDamage())
                e.setCanceled(true);
        }
    }
}

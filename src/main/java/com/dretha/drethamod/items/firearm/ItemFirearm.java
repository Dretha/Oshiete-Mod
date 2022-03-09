package com.dretha.drethamod.items.firearm;

import com.dretha.drethamod.capability.firearm.CapaFirearmProvider;
import com.dretha.drethamod.capability.firearm.ICapaFirearmHandler;
import com.dretha.drethamod.entity.projectile.EntityBullet;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.EnumKeeper;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.server.ReloadMessage;
import com.dretha.drethamod.utils.SoundPlayer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFirearm extends Item implements IAnimatable
{
    public AnimationFactory factory = new AnimationFactory(this);
    private final String controllerName = "popupController";
    private final String firearmDescription;

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        //Not setting an animation here as that's handled in onItemRightClick
        return PlayState.CONTINUE;
    }

    public ItemFirearm(String name, String firearmDescription)
    {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        setMaxStackSize(1);
        this.firearmDescription = firearmDescription;

        InitItems.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, desc, flagIn);
        desc.add(firearmDescription);

        ICapaFirearmHandler capa = stack.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        //if (capa==null) return;
        desc.add(capa.getAmmo() + "/" + ItemMagazine.STANDART_MAGAZINE_CAPACITY + I18n.format("info.ammo"));
        Bullets bullet = capa.getBullets();
        desc.add(bullet.getDescription());
    }

    public void reload(ItemStack firearm, World world, EntityPlayer player) {
        if (findAmmo(firearm, world, player)) {
            if (!world.isRemote)
                world.playSound(null, player.getPosition(), InitSounds.hk33reload, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    public boolean findAmmo(ItemStack firearm, World world, EntityPlayer player) {
        for (ItemStack magazine : player.inventory.offHandInventory) {
            if (magazine.getItem()==InitItems.MAGAZINE)
            {
                ICapaFirearmHandler magazineCapa = magazine.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
                int magazineCount = magazineCapa.getAmmo();
                Bullets magazineBullets = magazineCapa.getBullets();
                ICapaFirearmHandler capa = firearm.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
                int firearmCount = capa.getAmmo();
                Bullets firearmBullets = capa.getBullets();

                if (magazineCount > firearmCount || magazineBullets != firearmBullets)
                {
                    player.inventory.offHandInventory.clear();
                    capa.setAmmo(magazineCount, magazineBullets);
                    Oshiete.NETWORK.sendToServer(new ReloadMessage(firearm, magazineBullets.toString(), magazineCount));

                    player.setHeldItem(EnumHand.OFF_HAND, magazineFactory(firearmBullets.toString(), firearmCount));
                    return true;
                }
            }
        }
        return false;
    }

    private ItemStack magazineFactory(String bullets, int count) {
        ItemStack magazine = new ItemStack(InitItems.MAGAZINE);
        ICapaFirearmHandler magazineCapa = magazine.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        magazineCapa.setAmmo(count, Bullets.valueOf(bullets));
        return magazine;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        AnimationController controller = new AnimationController(this, controllerName, 20, this::predicate);

        // Registering a sound listener just makes it so when any sound keyframe is hit the method will be called.
        // To register a particle listener or custom event listener you do the exact same thing, just with registerParticleListener and registerCustomInstructionListener, respectively.
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);

        ICapaFirearmHandler capa1 = itemstack.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        if (capa1.getAmmo()==0)
        reload(itemstack, worldIn, playerIn);

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public void onUsingTick(ItemStack firearm, EntityLivingBase base, int count1) {
        ICapaFirearmHandler capa1 = firearm.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        System.out.println(capa1.getAmmo() + " " + capa1.getBullets());
        if (base.ticksExisted%3==0)
        {
            ICapaFirearmHandler capa = firearm.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
            Bullets bullets = capa.getBullets();
            int count = capa.getAmmo();

            boolean isCreative = false;
            if (base instanceof EntityPlayer)
                isCreative = ((EntityPlayer) base).isCreative();

            if (count > 0 && bullets != Bullets.NONE)
            {
                if (!isCreative)
                    capa.spendAmmo();
                shoot(base, bullets);
            }
        }
    }

    protected void shoot(EntityLivingBase base, Bullets bullets) {
        if (!base.world.isRemote) {
            EntityBullet bullet = new EntityBullet(base.world, base, bullets);
            base.world.spawnEntity(bullet);
            SoundPlayer.play(base, InitSounds.hk33shoot);
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumKeeper.FIREARM;
    }
}


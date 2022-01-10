package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.EnumKeeper;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class KuinkeMeleeBase extends Item implements IKuinke, IKuinkeMelee, IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);
    protected String controllerName = "popupController";
    protected final int QSteelShards;
    protected final int kaguneShards;
    protected EnumRarity rarity = EnumKeeper.Q_STEEL_RARITY;
    protected final float attackSpeed;

    public KuinkeMeleeBase(String name, int hardness, int QSteelShards, int kaguneShards, float attackSpeed) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        this.maxStackSize = 1;
        this.setMaxDamage(hardness);
        this.QSteelShards = QSteelShards;
        this.kaguneShards = kaguneShards;
        this.attackSpeed = attackSpeed;

        InitItems.ITEMS.add(this);
    }
    public KuinkeMeleeBase(String name, int hardness, int QSteelShards, int kaguneShards, float attackSpeed, EnumRarity rarity) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        this.maxStackSize = 1;
        this.setMaxDamage(hardness);
        this.QSteelShards = QSteelShards;
        this.kaguneShards = kaguneShards;
        this.rarity = rarity;
        this.attackSpeed = attackSpeed;

        if (rarity!=EnumKeeper.Q_STEEL_RARITY)
            InitItems.UNIQUE_KUINKIES.add(this);

        InitItems.ITEMS.add(this);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        //Not setting an animation here as that's handled in onItemRightClick
        return PlayState.CONTINUE;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            desc.add(getDamageValue(stack) + I18n.format("desk.kuinke0"));
            desc.add(getBlockValue(stack) + I18n.format("desk.kuinke1"));
            desc.add(this.getMaxDamage(stack) + I18n.format("desk.kuinke2"));
        }
    }

    @Override
    public void playImpact(ItemStack stack, EntityLivingBase base, World world) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
        if (controller.getAnimationState() == AnimationState.Stopped)
        {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("impact" + EventsHandler.random.nextInt(3)+1, false));
        }
    }

    @Override
    public int getBlockValue(ItemStack stack) {
        int block = stack.getTagCompound().getInteger("block");
        return block;
    }

    @Override
    public int getDamageValue(ItemStack stack) {
        int damage = stack.getTagCompound().getInteger("damage");
        return damage;
    }

    @Override
    public float getSpeedAttack() {
        return attackSpeed;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        AnimationController controller = new AnimationController(this, controllerName, 2, this::predicate);

        // Registering a sound listener just makes it so when any sound keyframe is hit the method will be called.
        // To register a particle listener or custom event listener you do the exact same thing, just with registerParticleListener and registerCustomInstructionListener, respectively.
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public int getCountQSteelShards() {
        return QSteelShards;
    }

    @Override
    public int getCountKaguneShards() {
        return kaguneShards;
    }

    @Override
    public int pointsToGet() {
        return 0;
    }

    @Override
    public int getItemEnchantability() {
        return 14;
    }



    @Override
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);

        EntityPlayer player = EventsHandler.getPlayerMP(playerIn);
        ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
        if (!capa.isKaguneActive())
            capa.setBlock(true);

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }


    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            //multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", damage_value, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", attackSpeed, 0));
        }

        return multimap;
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer) {
            ICapaHandler capa = entityLiving.getCapability(CapaProvider.PLAYER_CAP, null);
            capa.setBlock(false);
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer) {
            ICapaHandler capa = entityLiving.getCapability(CapaProvider.PLAYER_CAP, null);
            if (!capa.isKaguneActive())
                capa.setBlock(false);
        }
        return stack;
    }
}

package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.EnumKeeper;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.items.firearm.Bullets;
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
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class KuinkeMeleeBase extends Item implements IKuinke, IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);
    protected String controllerName = "popupController";
    protected int block_value;
    protected int damage_value;
    protected int shards;
    protected EnumRarity rarity = EnumKeeper.Q_STEEL_RARITY;

    public KuinkeMeleeBase(String name, int damage_value, int block_value, int hardness, int shards) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        this.maxStackSize = 1;
        this.setMaxDamage(hardness);
        this.damage_value = damage_value;
        this.block_value = block_value;
        this.shards = shards;

        InitItems.ITEMS.add(this);
    }
    public KuinkeMeleeBase(String name, int damage_value, int block_value, int hardness, int shards, EnumRarity rarity) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        this.maxStackSize = 1;
        this.setMaxDamage(hardness);
        this.damage_value = damage_value;
        this.block_value = block_value;
        this.shards = shards;
        this.rarity = rarity;

        InitItems.ITEMS.add(this);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        //Not setting an animation here as that's handled in onItemRightClick
        return PlayState.CONTINUE;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!stack.hasTagCompound()) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setUniqueId("uuid", UUID.randomUUID());
            stack.setTagCompound(compound);
        }
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setUniqueId("uuid", UUID.randomUUID());
            stack.setTagCompound(compound);
        }
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

    public int getCountShards() {
        return shards;
    }

    @Override
    public int getItemEnchantability() {
        return 14;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        //stack.damageItem(1, attacker);
        return true;
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

        ICapaHandler capa = playerIn.getCapability(CapaProvider.PLAYER_CAP, null);
        if (!capa.isKaguneActive())
            capa.setBlock(true);

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        desc.add(block_value + I18n.format("desk.kuinke1"));
        desc.add(this.getMaxDamage(stack) + I18n.format("desk.kuinke2"));
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
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", damage_value, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer) {
            ICapaHandler capa = entityLiving.getCapability(CapaProvider.PLAYER_CAP, null);
            if (!capa.isKaguneActive())
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

    @Override
    public int getBlockValue() {
        return block_value;
    }
}

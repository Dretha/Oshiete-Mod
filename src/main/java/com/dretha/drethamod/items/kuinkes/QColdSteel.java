package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;
import java.util.UUID;

public class QColdSteel extends KuinkeMeleeBase{

    protected Random random = new Random();
    protected final Weapons weapons;

    public QColdSteel(String name, Weapons weapons) {
        super(name, weapons.hardness, weapons.shards, 0, weapons.attackSpeed);
        this.weapons = weapons;
    }

    public QColdSteel(String name, Weapons weapons, EnumRarity rarity) {
        super(name, weapons.hardness, weapons.shards, 0, weapons.attackSpeed, rarity);
        this.weapons = weapons;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!stack.hasTagCompound()) {
            modificateWeapon(stack, 10, weapons);
        }
    }

    public static ItemStack modificateWeapon(ItemStack stack, int modif, Weapons weapons) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setUniqueId("uuid", UUID.randomUUID());

        compound.setInteger("damage", (int) (modif * weapons.damageMultiplier));
        compound.setInteger("block", (int) (modif * weapons.blockMultiplier));

        stack.setTagCompound(compound);
        return stack;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        DamageSource source;
        int damage = stack.getTagCompound().getInteger("damage");

        if (attacker instanceof EntityPlayer) {
            ICapaHandler capa = attacker.getCapability(CapaProvider.PLAYER_CAP, null);
            if (capa.getAttackKuinkeTicksPre() + ((QColdSteel)stack.getItem()).getWeapon().attackTick >= attacker.ticksExisted)
                return true;
        }

        if (attacker instanceof EntityPlayer) {
            source = DamageSource.causePlayerDamage((EntityPlayer) attacker);
        } else {
            source = DamageSource.causeMobDamage(attacker);
        }
        target.attackEntityFrom(source, damage);
        if (attacker instanceof EntityPlayer) {
            ICapaHandler capa = attacker.getCapability(CapaProvider.PLAYER_CAP, null);
            capa.setAttackKuinkeTicksPre(attacker.ticksExisted);
        }

        return true;
    }

    @Override
    public void playImpact(ItemStack stack, EntityLivingBase base, World world) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
        if (controller.getAnimationState() == AnimationState.Stopped)
        {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("impact" + (random.nextInt(3)+1), false));
        }
    }

    public Weapons getWeapon() {
        return weapons;
    }
}

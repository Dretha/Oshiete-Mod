package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.controllers.NoParamActionController;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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

import javax.annotation.Nullable;
import java.util.List;
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
            buildColdSteel(stack, 1500, weapons, GhoulType.BIKAKU);
        }
    }

    public static ItemStack buildColdSteel(ItemStack stack, int RCpool, Weapons weapons, GhoulType ghoulType) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setUniqueId("uuid", UUID.randomUUID());
        int combatPoint = (int) (PersonStats.getCombatPoint(RCpool)/0.8F);
        compound.setInteger("damage", (int) (combatPoint * weapons.damageMultiplier * ghoulType.kuinkeDamageMultiplier));
        compound.setInteger("block", (int) (combatPoint * weapons.blockMultiplier * ghoulType.blockMultiplier));
        compound.setString("type", ghoulType.toString());
        compound.setInteger("speed", (int) (weapons.attackTick * ghoulType.kuinkeSpeedMultiplier));

        stack.setTagCompound(compound);
        return stack;
    }

    public static ItemStack buildRandomColdSteel(int RCpool) {
        ItemStack coldSteel = new ItemStack(Weapons.randomItem());
        Weapons weapons = ((QColdSteel)coldSteel.getItem()).getWeapon();
        GhoulType ghoulType = GhoulType.random();
        return buildColdSteel(coldSteel, RCpool, weapons, ghoulType);
    }
    public static ItemStack buildRandomColdSteel(int RCpool, Weapons weapons) {
        ItemStack coldSteel = new ItemStack(Weapons.getItem(weapons));
        GhoulType ghoulType = GhoulType.random();
        return buildColdSteel(coldSteel, RCpool, weapons, ghoulType);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            desc.add(getDamageValue(stack) + I18n.format("desk.kuinke0"));
            desc.add(getBlockValue(stack) + I18n.format("desk.kuinke1"));
            desc.add(getSpeedValue(stack)/20F + I18n.format("desk.kuinke2"));
            desc.add(I18n.format("desk." + getType(stack).toString().toLowerCase()) + I18n.format("desk.kuinke3"));
            desc.add(this.getMaxDamage(stack) + I18n.format("desk.kuinke4"));
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        DamageSource source = OshieteDamageSource.causeKuinkeAttack(attacker);
        NBTTagCompound compound = stack.getTagCompound();
        if (!stack.hasTagCompound()) return false;

        int damage = compound.getInteger("damage");
        GhoulType ghoulType = GhoulType.valueOf(compound.getString("type"));

        if (attacker instanceof EntityPlayer) {
            ICapaHandler capa = attacker.getCapability(CapaProvider.PLAYER_CAP, null);
            NoParamActionController controller = capa.getKuinkeSpeedController();
            if (!controller.endAct(attacker.ticksExisted, getSpeedValue(stack)))
                return false;
            else
                controller.setTicksPre(attacker.ticksExisted);
        }

        GhoulType targetGhoulType = GhoulType.NONE;
        PersonStats stats = PersonStats.getStats(target);

        if (stats != null) {
            targetGhoulType = stats.getGhoulType();
        }

        float coefficient = GhoulType.getWeakType(ghoulType) == targetGhoulType ? GhoulType.damageCoefficient : 1F;

        int savedResistantTime = target.hurtResistantTime;
        target.hurtResistantTime = 0;
        target.attackEntityFrom(source, damage * coefficient);
        target.hurtResistantTime = savedResistantTime;

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

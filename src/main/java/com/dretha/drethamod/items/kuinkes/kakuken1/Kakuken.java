package com.dretha.drethamod.items.kuinkes.kakuken1;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.items.kuinkes.IColdSteel;
import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Kakuken extends KuinkeMeleeBase implements IColdSteel {

    public Kakuken(String name, int damage_value, int block_value, int hardness, int shards) {
        super(name, damage_value, block_value, hardness, shards);
        controllerName = "kakukenController";
    }
    public Kakuken(String name, int damage_value, int block_value, int hardness, int shards, EnumRarity rarity) {
        super(name, damage_value, block_value, hardness, shards, rarity);
        controllerName = "kakukenController";
    }
/*
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
        if (controller.getAnimationState() == AnimationState.Stopped)
        {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("block", true));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
*/
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase base, int count1)
    {
        ICapaHandler capa = base.getCapability(CapaProvider.PLAYER_CAP, null);
        if (capa!=null && capa.isBlock()) {
            AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
            controller.setAnimation(new AnimationBuilder().addAnimation("block", false));
        }
    }
/*
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
        controller.markNeedsReload();
        controller.clearAnimationCache();
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
*/

    @Override
    public void playImpact(ItemStack stack, EntityLivingBase base, World world) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, controllerName);
        ICapaHandler capa = base.getCapability(CapaProvider.PLAYER_CAP, null);
        if (controller.getAnimationState() == AnimationState.Stopped && capa != null)
        {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation(capa.getImpactType().toString().toLowerCase(), false));
        }
    }

}

package com.dretha.drethamod.client.geckolib.clothes.blue_blouse;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.inventory.EntityEquipmentSlot;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class BlueBlouseArmor extends GeoArmorItem implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public BlueBlouseArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot slot, String name) {
        super(materialIn, renderIndexIn, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        InitItems.ITEMS.add(this);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        //event.getController().setAnimation(new AnimationBuilder().addAnimation("new", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }
}

package com.dretha.drethamod.client.geckolib.clothes.test_hat;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ModCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class ClothesArmor extends GeoArmorItem implements IAnimatable{

	private AnimationFactory factory = new AnimationFactory(this);

	public ClothesArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot slot, String name) {
		super(materialIn, renderIndexIn, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(ModCreativeTabs.CLOTHES);
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

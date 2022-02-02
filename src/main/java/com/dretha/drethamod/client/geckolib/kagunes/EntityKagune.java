package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.Enumerator;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.Random;

public class EntityKagune extends EntityLiving implements IAnimatable {

	protected EntityLivingBase master = null;
	protected PersonStats stats = PersonStats.EMPTY;
	
	protected ActionController impactController = new ActionController(10);
	protected ActionController blockAnimController = new ActionController(7);
	protected ActionController releaseController = new ActionController(23);
	protected ActionController admitController = new ActionController(23);
	protected Enumerator impactEnumerator = new Enumerator(3);

	protected AnimationBuilder BLOCK = new AnimationBuilder().addAnimation("block", true);
	protected AnimationBuilder BLOCK_ANIM = new AnimationBuilder().addAnimation("block_anim", true);
	protected AnimationBuilder MOVE = new AnimationBuilder().addAnimation("move", true);
	protected AnimationBuilder NORMAL = new AnimationBuilder().addAnimation("normal", true);
	
	
	protected ArrayList<Float> listF = new ArrayList<>(Arrays.asList(new Float[] {0F, 0F, 0F, 0F, 0F, 0F, 0F}));
	
	public EntityKagune(EntityLivingBase master) {
		super(master.world);		
		this.master = master;
		stats = PersonStats.getStats(master);
	}
	
	
	protected AnimationFactory factory = new AnimationFactory(this);
	protected AnimationController controller = null;

	
    public EntityKagune(World worldIn)
    {
        super(worldIn);
        this.ignoreFrustumCheck = true;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		if (!releaseController.endAct(master.ticksExisted)) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("release", true));
			return PlayState.CONTINUE;
		}
		else if (!admitController.endAct(master.ticksExisted))
		{
			if (admitController.endAct(master.ticksExisted+1))
			{
				stats.setKaguneActive(false);
				if (!stats.isSpeedModeActive())
					stats.setKakuganActive(false);

				stats.nullKagune();
				this.setDead();
			}
			event.getController().setAnimation(new AnimationBuilder().addAnimation("admit", true));
			return PlayState.CONTINUE;
		}
		else if (!impactController.endAct(master.ticksExisted))
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation(stats.getImpactType().toString().toLowerCase() + (stats.getImpactType() == ImpactType.THRUST ? impactEnumerator.number() : ""), true));
			if (impactController.endAct(master.ticksExisted+1)) impactEnumerator.recite(master.ticksExisted);
			return PlayState.CONTINUE;
		}
		else if (!blockAnimController.endAct(master.ticksExisted))
		{
			event.getController().setAnimation(BLOCK_ANIM);
			return PlayState.CONTINUE;
		}
		else if (stats.isBlock())
		{
			event.getController().setAnimation(BLOCK);
			return PlayState.CONTINUE;
		}
		else if (master.isSprinting() && stats.isSpeedModeActive())
		{
			event.getController().setAnimation(MOVE);
			return PlayState.CONTINUE;
		}

    	event.getController().setAnimation(NORMAL);
        return PlayState.CONTINUE;
    }
    
    public boolean transform() {
    	return !releaseController.endAct(master.ticksExisted) || !admitController.endAct(master.ticksExisted);
    }
    
    @Override
    public void registerControllers(AnimationData data)
    {
    	this.controller = new AnimationController(this, "controller", 0, this::predicate);
        data.addAnimationController(this.controller);
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }
    
    
    public void setListF(float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	listF.clear();
    	this.listF.add(limbSwing);
    	this.listF.add(limbSwingAmount);
    	this.listF.add(partialTicks);
    	this.listF.add(ageInTicks);
    	this.listF.add(netHeadYaw);
    	this.listF.add(headPitch);
    	this.listF.add(scale);
    }
    
    public ArrayList<Float> getListF() {
    	return this.listF;
    }

	public ActionController getImpactController() {
		return impactController;
	}

	public ActionController getBlockAnimController() {
		return blockAnimController;
	}

	public ActionController getReleaseController() {
		return releaseController;
	}

	public ActionController getAdmitController() {
		return admitController;
	}

	public void spawnPatricleSpine(EntityLivingBase entity) {
		double x = entity.posX + ((Oshiete.random.nextGaussian()-0.5D)*0.25D);
		double y = entity.posY + 1D + ((Oshiete.random.nextGaussian()-0.5D)*0.5D);
		double z = entity.posZ + ((Oshiete.random.nextGaussian()-0.5D)*0.25D);
		float f = 10F / 15.0F;
		float r = f * 0.6F + 0.4F;
		float g = Math.max(0.0F, f * f * 0.7F - 0.5F);
		float b = Math.max(0.0F, f * f * 0.6F - 0.7F);
		entity.world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, r, g, b);
	}
}

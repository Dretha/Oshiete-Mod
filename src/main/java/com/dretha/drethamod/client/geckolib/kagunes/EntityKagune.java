package com.dretha.drethamod.client.geckolib.kagunes;

import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class EntityKagune extends EntityLiving implements IAnimatable {

	Runnable livingupdate = new Runnable() {
		public void run() {
			synchronized (this) {
				System.out.println("ia potok");
				try {
					Thread.currentThread().sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("potok interrupt");
					Thread.currentThread().interrupt();
				}
			}
		}
	};
	Thread threadlivingupdate = new Thread(livingupdate);
	
	
	private EntityLivingBase master = null;
	private ICapaHandler capa = null;
	
	private boolean isHit = false;
	
	
	
	
	private ArrayList<Float> listF = new ArrayList<>(Arrays.asList(new Float[] {0F, 0F, 0F, 0F, 0F, 0F, 0F}));
	
	public EntityKagune(EntityLivingBase player) {
		super(player.world);		
		master = player;
		capa = EventsHandler.getCapaMP((EntityPlayer) master);
	}
	
	
	private AnimationFactory factory = new AnimationFactory(this);
	private AnimationController controller = null;

	
    public EntityKagune(World worldIn)
    {
        super(worldIn);
        this.ignoreFrustumCheck = true;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
    	if (this.isHit && capa.getGhoulType()!=1) {
    		event.getController().setAnimation(new AnimationBuilder().addAnimation(capa.getImpactType().toString().toLowerCase() , true));
            return PlayState.CONTINUE;
    	}
    	event.getController().setAnimation(new AnimationBuilder().addAnimation("common" , true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
    	this.controller = new AnimationController(this, "controller", 0, this::predicate);
        data.addAnimationController(this.controller);
    }
    
    public AnimationController getController() {
    	return this.controller;
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
    
    public Thread getThreadLivingUpdate() {
    	return threadlivingupdate;
    }
    
    
    public boolean isHit() {
    	return this.isHit;
    }
    public void setHit(boolean b) {
    	this.isHit=b;
    }
}

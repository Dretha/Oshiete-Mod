package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

public abstract class EntityKagune extends EntityLiving implements IAnimatable {
	
	Random random = new Random();
	
	protected EntityLivingBase master = null;
	protected ICapaHandler capa = null;
	
	protected boolean isHit = false;
	protected int hitTicksPre = 0;
	
	protected boolean isBlockAnim = false;
	protected int blockAnimPre = 0;
	protected int blockAnimCount = 0;
	
	protected boolean release = true;
	protected int releaseTicks = this.ticksExisted;
	protected boolean admit = false;
	protected int admitTicks = this.ticksExisted;
	
	
	AnimationBuilder BLOCK = new AnimationBuilder().addAnimation("block", true);
	AnimationBuilder BLOCK_ANIM = new AnimationBuilder().addAnimation("block_anim", true);
	AnimationBuilder MOVE = new AnimationBuilder().addAnimation("move", true);
	AnimationBuilder NORMAL = new AnimationBuilder().addAnimation("normal", true);
	
	
	protected ArrayList<Float> listF = new ArrayList<>(Arrays.asList(new Float[] {0F, 0F, 0F, 0F, 0F, 0F, 0F}));
	
	public EntityKagune(EntityLivingBase master) {
		super(master.world);		
		this.master = master;
		if (master instanceof EntityPlayer)
			capa = EventsHandler.getCapaMP((EntityPlayer) master);
	}
	
	
	protected AnimationFactory factory = new AnimationFactory(this);
	protected AnimationController controller = null;

	
    public EntityKagune(World worldIn)
    {
        super(worldIn);
        this.ignoreFrustumCheck = true;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
    	
    	if (isBlockAnim && canToggleBlockAnim())
			isBlockAnim=false;
    	
    	if (capa!=null) {
    	
    		if (release) {
    			if (releaseTicks+23<=master.ticksExisted) release = false;
    			event.getController().setAnimation(new AnimationBuilder().addAnimation("release", true));
    			return PlayState.CONTINUE;
    		} else if (admit) {
    			if (admitTicks+23<=master.ticksExisted) {
    				admit = false;
    				
    				capa.setIsKaguneActive(false);
    				if (!capa.isSpeedModeActive())
    				capa.setActivatedKakugan(false);
    				
    				capa.nullKagune();
    				this.setDead();
    			}
    			event.getController().setAnimation(new AnimationBuilder().addAnimation("admit", true));
    			return PlayState.CONTINUE;
    		} else if (this.isHit) {
    			if (canCloseHit()) setHit(false);
    			if (capa.isBlock())
    				event.getController().setAnimation(new AnimationBuilder().addAnimation("block_thrust", true));
    			else
    				event.getController().setAnimation(new AnimationBuilder().addAnimation(capa.getImpactType().toString().toLowerCase() , true));
    			return PlayState.CONTINUE;
    		} else if (this.isBlockAnim) {
    			event.getController().setAnimation(BLOCK_ANIM);
    			return PlayState.CONTINUE;
    		} else if (capa.isBlock()) {
    			event.getController().setAnimation(BLOCK);
          	 return PlayState.CONTINUE;
    		} else if (master.isSprinting() && capa.isSpeedModeActive()) {
    			event.getController().setAnimation(MOVE);
    			return PlayState.CONTINUE;
    		}
    	
    	} else {
		
    	
    		if (release) {
    			if (releaseTicks+23<=master.ticksExisted) release = false;
    			event.getController().setAnimation(new AnimationBuilder().addAnimation("release", true));
    			return PlayState.CONTINUE;
    		} else if (admit) {
    			if (admitTicks+23<=master.ticksExisted) {
    				admit = false;
    				
    				((EntityHuman)master).setIsKaguneActive(false);
    				((EntityHuman)master).setActivatedKakugan(false);
    				
    				
    				((EntityHuman)master).nullKagune();
    				this.setDead();
    			}
    		} else if (this.isHit) {
    			if (canCloseHit()) setHit(false);
    			event.getController().setAnimation(new AnimationBuilder().addAnimation(((EntityHuman)master).getImpactType().toString().toLowerCase() , true));
    			return PlayState.CONTINUE;
    		} else if (this.isBlockAnim) {
    			event.getController().setAnimation(BLOCK_ANIM);
    			return PlayState.CONTINUE;
    		/*} else if (capa.isBlock()) {
    			event.getController().setAnimation(BLOCK);
          	 return PlayState.CONTINUE;*/
    		}
    	}
    	event.getController().setAnimation(NORMAL);
        return PlayState.CONTINUE;
    }
    
    public boolean transform() {
    	return release || admit;
    }
    
    public void setAdmit(boolean b) {
    	admit = b;
    }
    public void setAdmitTicks(int ticks) {
    	admitTicks = ticks;
    }

    public void setReleaseTicks(int ticks) {
    	releaseTicks = ticks;
    }
    
    public boolean canCloseHit() {
    	if (capa!=null) {
    		return hitTicksPre+capa.getImpactType().speed(capa)<=master.ticksExisted-1;
    	} else {
    		return hitTicksPre+15<=master.ticksExisted-1;
    	}
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
    
    
    
    public boolean isHit() {
    	return this.isHit;
    }
    public void setHit(boolean b) {
    	this.isHit=b;
    }
    public void setHitTicksPre(int ticks) {
    	this.hitTicksPre = ticks;
    }
    
    
    
    public boolean isBlockAnim() {
    	return this.isBlockAnim;
    }
    
    public void setBlockAnim(boolean b) {
    	this.isBlockAnim = b;
    }
    
    public boolean canToggleBlockAnim() {
    	return this.blockAnimPre + 5 < master.ticksExisted;
    }
    
    public void setBlockAnimPre(int ticks) {
    	this.blockAnimPre = ticks;
    }
    
    private int getBACount() {
    	this.blockAnimCount++;
    	if (this.blockAnimCount==4) {
    		this.blockAnimCount=1;
    	}
    	return this.blockAnimCount;
    }
}

package com.dretha.drethamod.server;

import java.util.List;

import javax.annotation.Nullable;

import com.dretha.drethamod.init.InitSounds;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TakeASlashMessage implements IMessage{
	
	private String text;
	static DamageSource damagesource = null;

    public TakeASlashMessage() { }

    public TakeASlashMessage(String text) {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<TakeASlashMessage, IMessage> {
        
        @Override
        public IMessage onMessage(TakeASlashMessage message, MessageContext ctx) {
            /*IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world; // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().player.getDisplayName()));
                }
            });kakoi to krutoi potok*/
        	
        	EntityPlayerMP player = (EntityPlayerMP) ctx.getServerHandler().player;
        	WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
        	System.out.println("server!!!!!!! Slash");
        	
        	damagesource = DamageSource.causePlayerDamage(player);
        	boolean thereIsEntity = false;
        	
        	for (int i = -3; i<=3; i++) {
        		if (!thereIsEntity)
        			thereIsEntity = takeAThrust(getMouseOver(world, player, i, 3, false));
        		if (thereIsEntity)
        			takeAThrust(getMouseOver(world, player, i, 3, false));
        	}
        	
        	if (thereIsEntity) {
        		world.playSound(null, player.getPosition(), InitSounds.hit_of_kagune, SoundCategory.PLAYERS, 1F, 1F);
        	} else {
        		world.playSound(null, player.getPosition(), InitSounds.hit_air_kagune, SoundCategory.PLAYERS, 1F, 1F);
        	}
        	
            return null;
        }
    }
    
    private static Entity getMouseOver(WorldServer world, EntityPlayerMP playerMP, float vecn, float dist, boolean canBeCollided)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        Entity pointedEntity = null;
        
        

        if (entity != null)
        {
            if (mc.world != null)
            {
                mc.pointedEntity = null;
                double d0 = dist;
                //Vec3d vec3d = entity.getPositionEyes(partialTicks);
                Vec3d vec3d = new Vec3d(entity.posX + vecn, entity.posY + (double)entity.getEyeHeight(), entity.posZ);
                boolean flag = false;
                double d1 = d0;

                if (mc.playerController.extendedReach())
                {
                    d1 = dist;
                    d0 = d1;
                }
                else
                {
                    if (d0 > dist)
                    {
                        flag = true;
                    }
                }

                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().grow(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).expand(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && (!canBeCollided || p_apply_1_.canBeCollidedWith());
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = (Entity)list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > dist)
                {
                    return null;
                }

                if (pointedEntity != null && d2 < d1)
                {
                    RayTraceResult ray = mc.player.rayTrace(dist, 1F);
                    if (ray == null || ray.hitVec.distanceTo(mc.player.getPositionVector())+0.5f >= pointedEntity.getPositionVector().distanceTo(mc.player.getPositionVector()))
                        
                    	
                    	
                    	return world.getEntityFromUuid(pointedEntity.getUniqueID());
                    //else if (ray.hitVec.distanceTo(mc.player.getPositionVector())+0.5f >= pointedEntity.getPositionVector().distanceTo(mc.player.getPositionVector()))
                        //return world.getEntityFromUuid(pointedEntity.getUniqueID());
                }
            }
        }
        return null;
    }
    
    private static boolean takeAThrust(Entity entity) {
    	if (entity!=null) {
    	int savedResistantTime = entity.hurtResistantTime;
    	entity.hurtResistantTime = 0;
    	entity.attackEntityFrom(damagesource, 10);
    	entity.hurtResistantTime = savedResistantTime;
    	}
    	return entity!=null;
    }
}



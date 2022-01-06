package com.dretha.drethamod.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class SniffMessage implements IMessage{
	
	private int radius;
	private int duration;
	
	public SniffMessage() {}
	public SniffMessage(int radius, int duration) {
        this.radius = radius;
        this.duration = duration;
    }
	
	@Override
    public void fromBytes(ByteBuf buf) {
		radius = ByteBufUtils.readVarInt(buf, 5);
		duration = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, radius, 5);
        ByteBufUtils.writeVarInt(buf, duration, 5);
    }

    public static class Handler implements IMessageHandler<SniffMessage, IMessage> {
        @Override
        public IMessage onMessage(SniffMessage m, MessageContext ctx) {
        	
        	EntityPlayerMP player = (EntityPlayerMP) ctx.getServerHandler().player;
        	WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
        	
        	PotionEffect potioneffect = new PotionEffect(MobEffects.GLOWING, m.duration, 0);
        	sniff(world, player, player.posX, player.posY, player.posZ, (float)m.radius, potioneffect);
        	
			return null;
        }
    }   
    
    public static void sniff(World world, EntityPlayerMP playerMP, double x, double y, double z, float radius, PotionEffect potioneffect) {
        List<EntityLivingBase> entityLivingBases = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x - radius, y - radius + 1, z - radius, x + radius, y + radius - 1, z + radius));
        for (EntityLivingBase base : entityLivingBases) {
            if(base == playerMP)continue;
            
            base.addPotionEffect(potioneffect);
        }
    }
}

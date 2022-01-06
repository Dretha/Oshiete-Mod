package com.dretha.drethamod.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class KillPlayerMessage implements IMessage{
	
	private NBTTagCompound compound = null;
	private int damage = 0;
	
	public KillPlayerMessage() {}
	public KillPlayerMessage(NBTTagCompound compound, int damage) {
        this.compound = compound;
        this.damage = damage;
    }
	
	@Override
    public void fromBytes(ByteBuf buf) {
		compound = ByteBufUtils.readTag(buf);
		damage = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
        ByteBufUtils.writeVarInt(buf, damage, 5);
    }

    public static class Handler implements IMessageHandler<KillPlayerMessage, IMessage> {
        @Override
        public IMessage onMessage(KillPlayerMessage m, MessageContext ctx) {
			System.out.println("server!!!!!");
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
        	
        	
        	String s = m.compound.getString("source");
        	Entity entity = world.getEntityFromUuid(m.compound.getUniqueId("entity"));
        	Entity immediate = world.getEntityFromUuid(m.compound.getUniqueId("immediate"));
        	
        	DamageSource source = null;
        	if (s.equals("player")) {
 			   	source = DamageSource.causePlayerDamage((EntityPlayer) entity);
 		   	} else if (s.equals("mob")) {
 		   		source = DamageSource.causeMobDamage((EntityLivingBase) entity);
 		   	} else if (s.equals("arrow")) {
 		   		source = DamageSource.causeArrowDamage((EntityArrow) immediate, entity);
 		   	}
        	if (source!=null) {
				player.attackEntityFrom(source, m.damage);
			}
        	
			return null;
        }
    }    
}

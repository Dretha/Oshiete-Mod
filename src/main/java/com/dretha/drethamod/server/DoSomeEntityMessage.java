package com.dretha.drethamod.server;

import com.dretha.drethamod.entity.EntityHuman;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DoSomeEntityMessage implements IMessage {

    private NBTTagCompound compound;

    public DoSomeEntityMessage() {}
    public DoSomeEntityMessage(NBTTagCompound compound) {
        this.compound = compound;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class Handler implements IMessageHandler<DoSomeEntityMessage, IMessage> {
        @Override
        public IMessage onMessage(DoSomeEntityMessage m, MessageContext ctx) {

            WorldServer world = (WorldServer) ctx.getServerHandler().player.world;

            try {
                ((EntityHuman) world.getEntityFromUuid(m.compound.getUniqueId("uuid"))).setSkin(m.compound.getString("skin"));
            } catch (Exception e) {

            }
            return null;
        }
    }
}

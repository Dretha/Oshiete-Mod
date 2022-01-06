package com.dretha.drethamod.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBase implements IMessage{
	
	private static ItemStack stack;
	
	public PacketBase() {}
	public PacketBase(ItemStack stack) {
        this.stack = stack;
    }
	
	@Override
    public void fromBytes(ByteBuf buf) {
		stack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
    }

    public static class Handler implements IMessageHandler<PacketBase, IMessage> {
        @Override
        public IMessage onMessage(PacketBase message, MessageContext ctx) {
        	
			return null;
        }
    }    
}

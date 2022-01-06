package com.dretha.drethamod.server;

import com.dretha.drethamod.client.gui.GuiHandler;
import com.dretha.drethamod.main.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenClothesInventoryMessage implements IMessage{
	
	public OpenClothesInventoryMessage() {}
	
	@Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<OpenClothesInventoryMessage, IMessage> {
        @Override
        public IMessage onMessage(OpenClothesInventoryMessage message, MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
            player.openGui(Main.instance, GuiHandler.INVENTORY_GUI_ID, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
			return null;
        }
    }    
}

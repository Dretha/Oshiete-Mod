package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientGhoulPoisonedMessage implements IMessage {

    public ClientGhoulPoisonedMessage() {}

    @Override
    public void fromBytes(ByteBuf buf) {
    }
    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<ClientGhoulPoisonedMessage, IMessage> {
        @Override
        public IMessage onMessage(ClientGhoulPoisonedMessage message, MessageContext ctx)
        {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            ICapaHandler capa = EventsHandler.getCapaMP(player);
            player.getFoodStats().setFoodLevel(capa.getLastFoodAmount());
            player.getFoodStats().setFoodSaturationLevel(0);
            return null;
        }
    }
}

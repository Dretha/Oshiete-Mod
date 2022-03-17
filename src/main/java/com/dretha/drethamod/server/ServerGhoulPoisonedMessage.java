package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerGhoulPoisonedMessage implements IMessage{
	
	public ServerGhoulPoisonedMessage() {}
	
	@Override
    public void fromBytes(ByteBuf buf) {
    }
    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<ServerGhoulPoisonedMessage, IMessage> {
        @Override
        public IMessage onMessage(ServerGhoulPoisonedMessage message, MessageContext ctx)
        {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 240, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 120, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
			player.getFoodStats().setFoodLevel(capa.getLastFoodAmount());
			player.getFoodStats().setFoodSaturationLevel(0);
    			
			return new ClientGhoulPoisonedMessage();
        }
    }    
}

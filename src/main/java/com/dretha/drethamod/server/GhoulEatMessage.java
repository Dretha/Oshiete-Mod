package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GhoulEatMessage implements IMessage{
	
	private static ItemStack stack;
	
	public GhoulEatMessage() {}
	public GhoulEatMessage(ItemStack stack) {
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

    public static class Handler implements IMessageHandler<GhoulEatMessage, IMessage> {
        @Override
        public IMessage onMessage(GhoulEatMessage message, MessageContext ctx) {
        	EntityPlayerMP player = (EntityPlayerMP) ctx.getServerHandler().player;
        	ItemFood food = (ItemFood) stack.getItem();
        	ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
        	ItemStack rottenflesh = new ItemStack(Items.ROTTEN_FLESH);
        	System.out.println("server!!!!!! ghouleat");
        	
    		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 240, 1));
    		
    		int lastamount = (capa.getLastFoodAmount() + food.getHealAmount(stack)) - 20;
    		if (lastamount<0) lastamount = 0;
    		
    	    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-food.getHealAmount(stack) + lastamount);
    	    
    		player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel()-food.getSaturationModifier(stack));
    			
			return null;
        }
    }    
}

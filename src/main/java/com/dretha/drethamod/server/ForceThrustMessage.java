package com.dretha.drethamod.server;

import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.stats.PersonStats;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ForceThrustMessage implements IMessage {

    private int damage;

    public ForceThrustMessage() {}
    public ForceThrustMessage(int damage) {
        this.damage = damage;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        damage = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeVarInt(buf, damage, 5);
    }

    public static class Handler implements IMessageHandler<ForceThrustMessage, IMessage> {
        @Override
        public IMessage onMessage(ForceThrustMessage m, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
                    .addScheduledTask(() -> this.run(m, ctx));
            return null;
        }

        public void run(ForceThrustMessage m, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().player;
            PersonStats stats = PersonStats.getStats(player);
            WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
            DamageSource damagesource = OshieteDamageSource.causeKaguneDamage(player);

            if (KaguneImpactMessage.splech(world, player, damagesource, m.damage, 1.25F) && !player.isCreative())
                stats.removeRClevel(12);

        }
    }
}

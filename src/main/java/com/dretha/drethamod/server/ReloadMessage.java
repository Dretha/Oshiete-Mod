package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.firearm.CapaFirearmProvider;
import com.dretha.drethamod.capability.firearm.ICapaFirearmHandler;
import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.items.firearm.ItemFirearm;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ReloadMessage implements IMessage {

    private ItemStack firearm;
    private String bullets;
    private int count;

    public ReloadMessage() {}
    public ReloadMessage(ItemStack firearm, String bullets, int count) {
        this.firearm = firearm;
        this.bullets = bullets;
        this.count = count;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        firearm = ByteBufUtils.readItemStack(buf);
        bullets = ByteBufUtils.readUTF8String(buf);
        count = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, firearm);
        ByteBufUtils.writeUTF8String(buf, bullets);
        ByteBufUtils.writeVarInt(buf, count, 5);
    }

    public static class Handler implements IMessageHandler<ReloadMessage, IMessage> {
        @Override
        public IMessage onMessage(ReloadMessage m, MessageContext ctx)
        {
            if (!(m.firearm.getItem() instanceof ItemFirearm)) return null;

            //ICapaFirearmHandler capa = m.firearm.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
            //capa.setAmmo(m.count, Bullets.valueOf(m.bullets));

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
                    .addScheduledTask(() -> this.reload(m));
            return m;
        }

        private void reload(ReloadMessage m) {
            ICapaFirearmHandler capa = m.firearm.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
            capa.setAmmo(m.count, Bullets.valueOf(m.bullets));
        }
    }
}

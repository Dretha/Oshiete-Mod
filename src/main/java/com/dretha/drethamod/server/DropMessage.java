package com.dretha.drethamod.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DropMessage implements IMessage {

    private ItemStack stack;
    private NBTTagCompound compound;

    public DropMessage() {}
    public DropMessage(ItemStack stack, NBTTagCompound compound) {
        this.stack = stack;
        this.compound = compound;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        stack = ByteBufUtils.readItemStack(buf);
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class Handler implements IMessageHandler<DropMessage, IMessage> {
        @Override
        public IMessage onMessage(DropMessage message, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
                    .addScheduledTask(() -> this.drop(message, ctx));

            return null;
        }

        public void drop(DropMessage m, MessageContext ctx) {
            WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
            EntityLivingBase base = (EntityLivingBase) world.getEntityFromUuid(m.compound.getUniqueId("uuid"));
            if (base!=null && m.stack!=null)
                base.entityDropItem(m.stack, 0);
            System.out.println(m.stack.getItem().getUnlocalizedName());
            System.out.println(m.stack.getCount());
        }
    }
}
package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.init.InitSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class TakeASlashMessage implements IMessage{
	
	private String text;
	static DamageSource damagesource = null;

    public TakeASlashMessage() { }

    public TakeASlashMessage(String text) {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<TakeASlashMessage, IMessage> {
        
        @Override
        public IMessage onMessage(TakeASlashMessage message, MessageContext ctx) {
            /*IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world; // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().player.getDisplayName()));
                }
            });kakoi to krutoi potok*/
        	
        	EntityPlayerMP player = (EntityPlayerMP) ctx.getServerHandler().player;
        	WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
        	System.out.println("server!!!!!!! Slash");
        	
        	damagesource = DamageSource.causePlayerDamage(player);
        	boolean thereIsEntity = false;
        	
        	splech(world, player, player.posX, player.posY, player.posZ, 3, 10);
        	
        	if (thereIsEntity) {
        		world.playSound(null, player.getPosition(), InitSounds.hit_of_kagune, SoundCategory.PLAYERS, 1F, 1F);
        	} else {
        		world.playSound(null, player.getPosition(), InitSounds.hit_air_kagune, SoundCategory.PLAYERS, 1F, 1F);
        	}
        	
            return null;
        }
    }
    
    public static void splech(World world, EntityPlayerMP playerMP, double x, double y, double z, float radius, float damage) {
        List<EntityLivingBase> entityLivingBases = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));
        for (EntityLivingBase base : entityLivingBases) {
            if(base == playerMP)continue;
            
            int savedResistantTime = base.hurtResistantTime;
            base.hurtResistantTime = 0;
            base.attackEntityFrom(damagesource, damage);
            base.hurtResistantTime = savedResistantTime;
            
            base.knockBack(playerMP, 0.9F, playerMP.posX - base.posX, playerMP.posZ - base.posZ);

            PotionEffect potioneffect = null;

            if (base instanceof EntityPlayer)
            {
                ICapaHandler capaBase = base.getCapability(CapaProvider.PLAYER_CAP, null);
                ICapaHandler capa = playerMP.getCapability(CapaProvider.PLAYER_CAP, null);

                int rankmeter = capa.rank()-(capaBase.rank() + (capaBase.isBlock() ? 1 : capaBase.isGhoul() ? -1 : -2));
                if (rankmeter>0)
                    potioneffect = new PotionEffect(MobEffects.SLOWNESS, 200, rankmeter);
                System.out.println(rankmeter);
            }
            if (base instanceof EntityHuman)
            {
                ICapaHandler capa = playerMP.getCapability(CapaProvider.PLAYER_CAP, null);
                EntityHuman human = (EntityHuman) base;

                int rankmeter = capa.rank() - (human.rank() + (human.isBlock() ? 1 : human.isGhoul() ? -1 : -2));
                if (rankmeter>0)
                    potioneffect = new PotionEffect(MobEffects.SLOWNESS, 200, rankmeter);
                System.out.println(rankmeter);
            }

            if (potioneffect!=null && !base.isDead)
            base.addPotionEffect(potioneffect);
        }
    }
}



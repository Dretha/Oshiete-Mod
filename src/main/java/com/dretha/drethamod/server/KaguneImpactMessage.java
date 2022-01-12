package com.dretha.drethamod.server;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;
import java.util.List;

public class KaguneImpactMessage implements IMessage{

    private int damage;
    private boolean isThrust;
	private DamageSource damagesource = null;

    public KaguneImpactMessage() { }

    public KaguneImpactMessage(int damage, boolean isThrust)
    {
        this.damage = damage;
        this.isThrust = isThrust;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        damage = ByteBufUtils.readVarInt(buf, 5);
        isThrust = ByteBufUtils.readVarInt(buf, 5)==1;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeVarInt(buf, damage, 5);
        ByteBufUtils.writeVarInt(buf, isThrust ? 1 : 0, 5);
    }

    public static class Handler implements IMessageHandler<KaguneImpactMessage, IMessage> {
        
        @Override
        public IMessage onMessage(KaguneImpactMessage m, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
                    .addScheduledTask(() -> this.run(m, ctx));

            return null;
        }

        public void run(KaguneImpactMessage m, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().player;
            WorldServer world = (WorldServer) ctx.getServerHandler().player.world;
            m.damagesource = DamageSource.causePlayerDamage(player);

            if (m.isThrust)
            {
                System.out.println("server!!!!!!! Thrust");

                Entity entity = getMouseOver(world, player, 0, 3, true);

                if (entity instanceof EntityLivingBase)
                {
                    impact(player, (EntityLivingBase) entity, m.damagesource, m.damage);
                    world.playSound(null, player.getPosition(), InitSounds.hit_of_kagune, SoundCategory.PLAYERS, 0.7F, 1F);
                }
                else
                {
                    world.playSound(null, player.getPosition(), InitSounds.hit_air_kagune, SoundCategory.PLAYERS, 0.4F, 1F);
                }
            }
            else
            {
                System.out.println("server!!!!!!! Slash");

                boolean thereIsEntity = splech(world, player, m.damagesource, (int) (m.damage * GhoulType.slashDebuf), player.posX, player.posY, player.posZ, 3);

                if (thereIsEntity) {
                    world.playSound(null, player.getPosition(), InitSounds.hit_of_kagune, SoundCategory.PLAYERS, 1F, 1F);
                } else {
                    world.playSound(null, player.getPosition(), InitSounds.hit_air_kagune, SoundCategory.PLAYERS, 1F, 1F);
                }
            }
        }
    }
    
    private static Entity getMouseOver(WorldServer world, EntityPlayerMP playerMP, float vecn, float dist, boolean canBeCollided)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        Entity pointedEntity = null;
        
        

        if (entity != null)
        {
            if (mc.world != null)
            {
                mc.pointedEntity = null;
                double d0 = dist;
                //Vec3d vec3d = entity.getPositionEyes(partialTicks);
                Vec3d vec3d = new Vec3d(entity.posX + vecn, entity.posY + (double)entity.getEyeHeight(), entity.posZ);
                boolean flag = false;
                double d1 = d0;

                if (mc.playerController.extendedReach())
                {
                    d1 = dist;
                    d0 = d1;
                }
                else
                {
                    if (d0 > dist)
                    {
                        flag = true;
                    }
                }

                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().grow(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).expand(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && (!canBeCollided || p_apply_1_.canBeCollidedWith());
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = (Entity)list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > dist)
                {
                    return null;
                }

                if (pointedEntity != null && d2 < d1)
                {
                    RayTraceResult ray = mc.player.rayTrace(dist, 1F);
                    if (ray == null || ray.hitVec.distanceTo(mc.player.getPositionVector())+0.5f >= pointedEntity.getPositionVector().distanceTo(mc.player.getPositionVector()))
                        
                    	
                    	
                    	return world.getEntityFromUuid(pointedEntity.getUniqueID());
                    //else if (ray.hitVec.distanceTo(mc.player.getPositionVector())+0.5f >= pointedEntity.getPositionVector().distanceTo(mc.player.getPositionVector()))
                        //return world.getEntityFromUuid(pointedEntity.getUniqueID());
                }
            }
        }
        return null;
    }

    public static boolean splech(World world, EntityPlayerMP playerMP, DamageSource source, int damage, double x, double y, double z, float radius)
    {
        List<EntityLivingBase> entityLivingBases = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

        boolean thereIsEntity = false;

        for (EntityLivingBase base : entityLivingBases) {
            if(base == playerMP)continue;

            impact(playerMP, base, source, damage);

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

            thereIsEntity = true;
        }
        return thereIsEntity;
    }
    
    private static void impact(EntityPlayerMP attacker, EntityLivingBase target, DamageSource source, int damage) {
    	if (target!=null)
        {
            ICapaHandler capa = attacker.getCapability(CapaProvider.PLAYER_CAP, null);
            GhoulType weakType = GhoulType.getWeakType(capa.getGhoulType());

            boolean isWeak = false;
            if (target instanceof EntityPlayer) {
                ICapaHandler capa1 = attacker.getCapability(CapaProvider.PLAYER_CAP, null);
                if (capa1.isGhoul()) {
                    isWeak = weakType==capa1.getGhoulType();
                }
            } else if (target instanceof EntityHuman) {
                if (((EntityHuman) target).isGhoul()) {
                    isWeak = weakType==((EntityHuman) target).getGhoulType();
                }
            }

            float coefficient = isWeak ? GhoulType.damageCoefficient : 1F;

            int savedResistantTime = target.hurtResistantTime;
    	    target.hurtResistantTime = 0;
    	    target.attackEntityFrom(source, damage*coefficient);
    	    target.hurtResistantTime = savedResistantTime;
    	}
    }
}

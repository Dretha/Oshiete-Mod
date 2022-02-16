package com.dretha.drethamod.entity.ai;

import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class HumanAIAvoidScary extends EntityAIBase
{
    /** The entity we are attached to */
    protected EntityHuman human;
    private final double farSpeed;
    private final double nearSpeed;
    protected EntityLivingBase closestLivingEntity;
    /** The PathEntity of our entity */
    private Path path;
    /** The PathNavigate of our entity */
    private final PathNavigate navigation;
    /** Class of entity this behavior seeks to avoid */

    public HumanAIAvoidScary(EntityHuman human, double speed)
    {
        this.human = human;
        this.farSpeed = speed;
        this.nearSpeed = speed;
        this.navigation = human.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (human.personStats().isCombatReady(human))
            return false;
        if (human.getAttackTarget()!=null)
            return false;

        EntityLivingBase scary = human.heMeScared();
        if (scary!=null)
            closestLivingEntity = scary;
        else if (human.getRevengeTarget()!=null && human.getRevengeTimer()>60)
            closestLivingEntity = human.getRevengeTarget();

        if (closestLivingEntity!=null)
        {
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.human, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3d == null)
            {
                return false;
            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.human))
            {
                return false;
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.navigation.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.navigation.setPath(this.path, this.farSpeed);
        if (human.getRevengeTarget()!=null && human.getRevengeTimer()>10)
            alertOthers();
    }

    protected void alertOthers()
    {
        double d0 = this.getTargetDistance();

        for (EntityCreature teammate : this.human.world.getEntitiesWithinAABB(this.human.getClass(), (new AxisAlignedBB(this.human.posX, this.human.posY, this.human.posZ, this.human.posX + 1.0D, this.human.posY + 1.0D, this.human.posZ + 1.0D)).grow(d0, 10.0D, d0)))
        {
            if (human.personStats()!=null && teammate!=null && this.human.getRevengeTarget()!=null && this.human != teammate && teammate.getAttackTarget() == null && teammate instanceof EntityHuman && human.personStats().isMyTeammate(teammate))
            {
                teammate.setAttackTarget(human.getRevengeTarget());
            }
        }
    }

    protected double getTargetDistance()
    {
        IAttributeInstance iattributeinstance = this.human.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        return iattributeinstance.getAttributeValue();
    }


    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        if (this.human.getDistanceSq(this.closestLivingEntity) < 49.0D)
        {
            this.human.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.human.getNavigator().setSpeed(this.farSpeed);
        }
    }
}
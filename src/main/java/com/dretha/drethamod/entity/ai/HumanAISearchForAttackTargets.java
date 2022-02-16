package com.dretha.drethamod.entity.ai;

import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.worldevents.HeadquartersCCG;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;

public class HumanAISearchForAttackTargets extends EntityAITarget
{
    protected final EntityAINearestAttackableTarget.Sorter sorter;
    protected EntityLivingBase targetEntity;
    protected EntityHuman human;

    public HumanAISearchForAttackTargets(EntityHuman creature)
    {
        super(creature, true, false);
        this.setMutexBits(1);
        this.sorter = new EntityAINearestAttackableTarget.Sorter(creature);
        human = creature;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!human.personStats().isCombatReady(human))
            return false;

        List<EntityLivingBase> list = this.taskOwner.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getTargetableArea(this.getTargetDistance()));

        list.removeIf(base -> !human.isMyEnemy(base) || base instanceof EntityPlayer && ((EntityPlayer) base).isCreative());

        if (list.isEmpty())
        {
            return false;
        }
        else
        {
            list.sort(this.sorter);
            this.targetEntity = list.get(0);
            return true;
        }
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance)
    {
        return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}

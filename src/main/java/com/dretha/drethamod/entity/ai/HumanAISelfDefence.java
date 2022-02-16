package com.dretha.drethamod.entity.ai;

import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.utils.stats.PersonStats;
import com.dretha.drethamod.worldevents.HeadquartersCCG;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.math.AxisAlignedBB;

public class HumanAISelfDefence extends EntityAIHurtByTarget {
    protected PersonStats stats;
    public HumanAISelfDefence(EntityHuman creatureIn) {
        super(creatureIn, true, new Class[0]);
        stats = creatureIn.personStats();
    }

    @Override
    public boolean shouldExecute()
    {
        stats = PersonStats.getStats(taskOwner);
        if (stats!=null && !stats.isCombatReady(taskOwner))
            return false;
        if (taskOwner.getAttackTarget()!=null)
            return false;
        return super.shouldExecute();
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public boolean shouldContinueExecuting() {
        stats = PersonStats.getStats(taskOwner);
        if (stats!=null && !stats.isCombatReady(taskOwner))
            return false;
        return super.shouldContinueExecuting();
    }

    @Override
    protected void alertOthers()
    {
        stats = PersonStats.getStats(taskOwner);
        double d0 = this.getTargetDistance();

        for (EntityCreature teammate : this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).grow(d0, 10.0D, d0)))
        {
            if (stats!=null && teammate!=null && this.taskOwner.getRevengeTarget()!=null && this.taskOwner != teammate && teammate.getAttackTarget() == null && teammate instanceof EntityHuman && stats.isMyTeammate(teammate))
            {
                this.setEntityAttackTarget(teammate, this.taskOwner.getRevengeTarget());
            }
        }
    }
}

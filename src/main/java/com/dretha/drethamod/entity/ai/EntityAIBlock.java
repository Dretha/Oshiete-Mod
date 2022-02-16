package com.dretha.drethamod.entity.ai;

import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.utils.EntityUtils;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumHand;

public class EntityAIBlock extends EntityAIBase {

    protected EntityCreature blocker;
    protected PersonStats stats;

    public EntityAIBlock(EntityCreature creature)
    {
        this.blocker = creature;
        this.stats = PersonStats.getStats(creature);
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        stats = PersonStats.getStats(blocker);

        if (!stats.isNotCivillian() && !melleInMyHand())
            return false;

        if (blocker.getRevengeTarget()!=null && blocker.getRevengeTimer()<30)
            return true;

        return false;
    }

    @Override
    public void startExecuting() {
        //stats.setBlock(true);
        if (stats.isGhoul())
        {
            stats.setBlock(true);
        }
        else
        {
            if (melleeInMyMainhand())
                blocker.setActiveHand(EnumHand.MAIN_HAND);
            else if (melleeInMyOffhand())
                blocker.setActiveHand(EnumHand.OFF_HAND);
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return blocker.getRevengeTimer()<1200 && blocker.getRevengeTarget()!=null && EntityUtils.getDistanceBetweenEntities(blocker, blocker.getRevengeTarget())<4;
    }

    @Override
    public void resetTask() {
        stats.setBlock(false);
        blocker.resetActiveHand();
    }

    protected boolean melleeInMyMainhand() {
        return blocker.getHeldItemMainhand().getItem() instanceof IKuinkeMelee;
    }
    protected boolean melleeInMyOffhand() {
        return blocker.getHeldItemOffhand().getItem() instanceof IKuinkeMelee;
    }
    protected boolean melleInMyHand() {
        return blocker.getHeldItemMainhand().getItem() instanceof IKuinkeMelee || blocker.getHeldItemOffhand().getItem() instanceof IKuinkeMelee;
    }
}

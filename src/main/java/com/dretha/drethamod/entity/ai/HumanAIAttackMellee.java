package com.dretha.drethamod.entity.ai;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.capability.world.IWorldCapaHandler;
import com.dretha.drethamod.capability.world.WorldCapaProvider;
import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.server.KaguneImpactMessage;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.stats.PersonStats;
import com.dretha.drethamod.worldevents.HeadquartersCCG;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;

public class HumanAIAttackMellee extends EntityAIAttackMelee {

    protected PersonStats stats;
    protected EntityHuman human;

    public HumanAIAttackMellee(EntityHuman human) {
        super(human, 2, true);
        stats = human.personStats();
        this.human = human;
    }

    @Override
    public boolean shouldExecute() {
        human = (EntityHuman) attacker;
        stats = PersonStats.getStats(attacker);
        return super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (!stats.isCombatReady(attacker))
            return false;
        return super.shouldContinueExecuting();
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        stats = PersonStats.getStats(attacker);
        if (!stats.isKaguneActive() && stats.isGhoul())
            stats.releaseKagune(attacker);
        if (stats.isDove()) {
            HeadquartersCCG headquartersCCG = attacker.world.getCapability(WorldCapaProvider.WORLD_CAP, null).getHeadquartersCCG();
            headquartersCCG.addWanted(attacker.getAttackTarget());
        }
    }

    @Override
    public void resetTask() {
        stats.setBlock(false);
        super.resetTask();
    }

    @Override
    public void updateTask()
    {
        if (attacker.getLastAttackedEntityTime()+60>attacker.ticksExisted && !(attacker.getAttackTarget() instanceof EntityHuman && !((EntityHuman) attacker.getAttackTarget()).personStats().isNotCivillian())) {
            human.setBlocking();
        } else
            human.resetBlocking();
        super.updateTask();
        // TODO сделать блок для хумана
    }

    @Override
    protected void checkAndPerformAttack(EntityLivingBase base, double speedTowardsTarget) {
        double d0 = this.getAttackReachSqr(base);
        if (speedTowardsTarget <= d0 && this.attackTick <= 0 && !stats.isBlock())
        {
            this.attackTick = attackInterval;
            if (stats.getKagune()!=null && !stats.getKagune().transform() && stats.isKaguneActive())
            {
                KaguneImpactMessage.impact(attacker, base, stats.getDamage());
                stats.getKagune().getImpactController().setTicksPre(attacker.ticksExisted);
                attacker.setLastAttackedEntity(base);
            }
            else
            {
                this.attacker.swingArm(EnumHand.MAIN_HAND);
                this.attacker.attackEntityAsMob(base);
                attacker.getHeldItemMainhand().getItem().hitEntity(attacker.getHeldItemMainhand(), base, attacker);
            }
        }
    }
}

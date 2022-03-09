package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.utils.Enumerator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public abstract class EntityFlame extends EntityKagune
{
    protected Enumerator textureFrameEnumerator = new Enumerator(1, 5);

    public EntityFlame(EntityLivingBase master) {
        super(master);
    }

    public EntityFlame(World worldIn) {
        super(worldIn);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (master.ticksExisted%4==0)
            textureFrameEnumerator.forwardOnce(master.ticksExisted);

        if (!releaseController.endAct(master.ticksExisted)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("release", true));
            return PlayState.CONTINUE;
        }
        else if (!admitController.endAct(master.ticksExisted))
        {
            if (admitController.endAct(master.ticksExisted+1))
            {
                stats.setKaguneActive(false);
                if (!stats.isSpeedModeActive())
                    stats.setKakuganActive(false);

                stats.nullKagune();
                this.setDead();
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("admit", true));
            return PlayState.CONTINUE;
        }
        else if (master.isSprinting() && stats.isSpeedModeActive())
        {
            event.getController().setAnimation(MOVE);
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(NORMAL);
        return PlayState.CONTINUE;
    }

    public int getTextureFrame() {
        return textureFrameEnumerator.number();
    }
}

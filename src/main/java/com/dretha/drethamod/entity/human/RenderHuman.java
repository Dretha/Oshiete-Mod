package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.layers.LayerClothes;
import com.dretha.drethamod.layers.LayerKakugan;
import com.dretha.drethamod.layers.LayerShard;
import com.dretha.drethamod.layers.LayerFlame;
import com.dretha.drethamod.layers.LayerKagune;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import javax.annotation.Nonnull;
import java.util.Objects;


public class RenderHuman extends RenderLiving<EntityHuman>{

    public RenderHuman(RenderManager manager) {
        super(manager, new ModelHuman(0.0F, true), 0.5F);
        this.addLayer(new LayerShard(this));
        this.addLayer(new LayerKakugan(this));
        this.addLayer(new LayerKagune(this));
        this.addLayer(new LayerFlame());
        this.addLayer(new LayerClothes(this));
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityHuman entity) {
        return entity.getSkinTexture();
    }

    @Override
    public void doRender(EntityHuman human, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(human, x, y, z, entityYaw, partialTicks);
        PersonStats stats = PersonStats.EMPTY;
        if (EventsHandler.getHumanMP(human)!=null)
            stats = Objects.requireNonNull(EventsHandler.getHumanMP(human)).personStats();

        if (human.melleInMyHand() && stats.isBlock())
        {
            ((ModelHuman)mainModel).setArmPose(ModelBiped.ArmPose.BLOCK, ModelBiped.ArmPose.BLOCK);
        }
        else
            ((ModelHuman)mainModel).setArmPose(ModelBiped.ArmPose.EMPTY, ModelBiped.ArmPose.EMPTY);
    }

    public void setActionPoses(EntityHuman human)
    {
        System.out.println(human.personStats().isBlock());
        ModelBiped.ArmPose mainArmPose = ModelBiped.ArmPose.EMPTY;
        ModelBiped.ArmPose offArmPose = ModelBiped.ArmPose.EMPTY;

        if (human.melleeInMyMainhand())
        {
            if (human.personStats().isBlock())
                mainArmPose = ModelBiped.ArmPose.BLOCK;
        }

        if (human.melleeInMyOffhand())
        {
            if (human.personStats().isBlock())
                offArmPose = ModelBiped.ArmPose.BLOCK;
        }
        ((ModelHuman)mainModel).setArmPose(mainArmPose, offArmPose);
    }
}
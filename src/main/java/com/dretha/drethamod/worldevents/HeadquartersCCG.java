package com.dretha.drethamod.worldevents;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import java.util.ArrayList;
import java.util.UUID;

public class HeadquartersCCG
{
    private static final ArrayList<UUID> wantedCriminalsBase = new ArrayList<>();

    public static void addWanted(EntityLivingBase base) {
        if (base==null) return;
        if (!base.isDead && !wantedCriminalsBase.contains(base.getUniqueID()) && PersonStats.getStats(base).isMaskOff()) {
            wantedCriminalsBase.add(base.getUniqueID());
            if (base instanceof EntityPlayer)
                base.sendMessage(new TextComponentString(I18n.format("message.addedcriminalbase")));
        }
    }

    public static void removeWanted(EntityLivingBase base) {
        wantedCriminalsBase.remove(base.getUniqueID());
    }

    public static boolean isWanted(EntityLivingBase base) {
        if (wantedCriminalsBase.isEmpty()) return false;
        return wantedCriminalsBase.contains(base.getUniqueID());
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("wantedCriminalsBaseIsEmpty", wantedCriminalsBase.isEmpty());
        if (wantedCriminalsBase.isEmpty()) return;
        compound.setInteger("wantedQuantity", wantedCriminalsBase.size());
        for (int i = 0; i<wantedCriminalsBase.size(); i++) {
            compound.setUniqueId("criminal"+i, wantedCriminalsBase.get(i));
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.getBoolean("wantedCriminalsBaseIsEmpty")) return;
        int count = compound.getInteger("wantedQuantity");
        for (int i = 0; i<count; i++) {
            wantedCriminalsBase.add(compound.getUniqueId("criminal"+i));
        }
    }
}

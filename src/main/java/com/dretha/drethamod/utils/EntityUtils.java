package com.dretha.drethamod.utils;

import net.minecraft.entity.EntityLivingBase;

public class EntityUtils
{
    public static double getDistanceBetweenEntities(EntityLivingBase base1, EntityLivingBase base2) {
        return Math.sqrt(Math.pow(base2.posX-base1.posX, 2) + Math.pow(base2.posY-base1.posY, 2) + Math.pow(base2.posZ-base1.posZ, 2));
    }
}

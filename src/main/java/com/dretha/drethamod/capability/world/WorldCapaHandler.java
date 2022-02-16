package com.dretha.drethamod.capability.world;

import com.dretha.drethamod.worldevents.HeadquartersCCG;

public class WorldCapaHandler implements IWorldCapaHandler{

    private static final HeadquartersCCG headquartersCCG = new HeadquartersCCG();

    @Override
    public HeadquartersCCG getHeadquartersCCG() {
        return headquartersCCG;
    }
}

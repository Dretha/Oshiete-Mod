package com.dretha.drethamod.utils.enums;

import java.util.Arrays;

public enum GrowthStages {
    NONE(0),
    FIRST(1000),
    SECOND(2300),
    THIRD(3600),
    SEMIKAKUJA_FIRST(5000),
    SEMIKAKUJA_SECOND(6000),
    SEMIKAKUJA_THIRD(7000),
    KAKUJA(8000),
    SUPERKAKUJA(12000);

    public static final int stagesAvailable = 3;

    public static GrowthStages getStage(int RCpoints)
    {
        GrowthStages result = GrowthStages.FIRST;
        for (GrowthStages stage : GrowthStages.values())
        {
            if (RCpoints>=stage.threshold)
                result = stage;
            else break;
        }

        if (result.id()>stagesAvailable) result=GrowthStages.values()[stagesAvailable];

        return result;
    }

    public final int threshold;

    GrowthStages(int threshold) {
        this.threshold = threshold;
    }

    public int id() {
        return Arrays.asList(GrowthStages.values()).indexOf(this);
    }
}

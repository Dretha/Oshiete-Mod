package com.dretha.drethamod.utils.enums;

import java.util.Arrays;

public enum GrowthStages {
    NONE,
    FIRST,
    SECOND,
    THIRD,
    SEMIKAKUJA_FIRST, //5000 rc
    SEMIKAKUJA_SECOND,
    SEMIKAKUJA_THIRD,
    KAKUJA,
    SUPERKAKUJA;

    public static final int stagesAvailable = 3;

    public static GrowthStages getStage(int RCpoints) {
        int index = Math.round((RCpoints-500F)/1000F);
        if (index > stagesAvailable) {
            index = stagesAvailable;
        }
        return GrowthStages.values()[index];
    }

    public int id() {
        return Arrays.asList(GrowthStages.values()).indexOf(this);
    }
}

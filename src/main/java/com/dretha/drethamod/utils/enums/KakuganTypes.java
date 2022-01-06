package com.dretha.drethamod.utils.enums;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.item.Item;

public enum KakuganTypes {
    TYPE31 {
        public String id() {return "31";}
        public Item get() {return InitItems.KAKUGAN31;}
    },
    TYPE12 {
        public String id() {return "12";}
        public Item get() {return InitItems.KAKUGAN12;}
    },
    TYPE11 {
        public String id() {return "11";}
        public Item get() {return InitItems.KAKUGAN11;}
    };

    public abstract String id();
    public abstract Item get();
}

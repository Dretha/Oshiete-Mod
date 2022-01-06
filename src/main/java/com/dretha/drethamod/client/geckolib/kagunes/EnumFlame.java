package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.item.Item;

public enum EnumFlame {
	TRUE01{
		public Item getFlame() {return InitItems.UKAKU_FLAME_BIG_01;}
	},
	FALSE01{
		public Item getFlame() {return InitItems.UKAKU_FLAME_BIG_01;}
	};
	
	public abstract Item getFlame();
}

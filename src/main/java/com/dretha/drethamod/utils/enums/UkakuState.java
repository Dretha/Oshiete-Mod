package com.dretha.drethamod.utils.enums;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.utils.stats.PersonStats;
import scala.actors.threadpool.Arrays;

import java.util.Random;

public enum UkakuState {
	NONE {
		public UkakuState next() {return NONE;}
	},
	FLAME {
		public UkakuState next() {return FLAMELIMB;}
	},
	LIMB {
		public UkakuState next() {return FLAME;}
	},
	FLAMELIMB {
		public UkakuState next() {return LIMB;}
	};
	
	public abstract UkakuState next();
	
	public static UkakuState generateState() {
		Random random = new Random();
		int r = random.nextInt(100);
		if (r<=15)
			return UkakuState.FLAMELIMB;
		if (r>15 && r<=70)
			return UkakuState.FLAME;
		if (r>70 && r<=100)
			return UkakuState.LIMB;
		return UkakuState.FLAME;
	}
	
	
	public static int indexOf(UkakuState state) {
		return Arrays.asList(UkakuState.values()).indexOf(state);
	}
	public static UkakuState typeOf(int index) {
		return (UkakuState) Arrays.asList(UkakuState.values()).get(index);
	}
	
	
	public static boolean haveLimb(PersonStats stats) {
		return stats.getUkakuState()==UkakuState.FLAMELIMB || stats.getUkakuState()==UkakuState.LIMB;
	}
	public static boolean haveFlame(PersonStats stats) {
		return stats.getUkakuState()==UkakuState.FLAMELIMB || stats.getUkakuState()==UkakuState.FLAME;
	}
	public static boolean haveJustFlame(PersonStats stats) {
		return stats.getUkakuState()==UkakuState.FLAME;
	}
}

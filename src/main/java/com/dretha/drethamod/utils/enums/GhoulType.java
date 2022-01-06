package com.dretha.drethamod.utils.enums;


import java.util.Arrays;
import java.util.Random;

public enum GhoulType {
	UKAKU {
		public float speed() {return 5;}
		public int index() {return 0;}
	    public int id() {return 1;}
	    
		@Override
		public float blockDamageModif(float response) {
			if (response>1.0F) return 0;
			return 1.025F-response*2;
		}
	},
	KOUKAKU {
		public float speed() {return 10;}
		public int index() {return 1;}
	    public int id() {return 2;}
	    
	    @Override
		public float blockDamageModif(float response) {
	    	if (response>=1.0F) return 0;
			return 1.025F-response/2;
		}
	},
	RINKAKU {
		public float speed() {return 15;}
		public int index() {return 2;}
	    public int id() {return 3;}
	    
	    @Override
		public float blockDamageModif(float response) {
	    	if (response>1.3F) return 0;
	    	else return 1.0F-(response/1.3F);
		}
	},
	BIKAKU {
		public float speed() {return 7.5F;}
		public int index() {return 3;}
	    public int id() {return 4;}
	    
	    @Override
		public float blockDamageModif(float response) {
	    	if (response>1.0F) return 0;
			return 1.025F-response;
		}
	},
	NONE {
		public float speed() {return 0;}
		public int index() {return 4;}
	    public int id() {return 0;}
	    
	    @Override
		public float blockDamageModif(float response) {
			return 0;
		}
	};
	public abstract float speed();
	public abstract int index();
	public abstract int id();
	public abstract float blockDamageModif(float response);
	
	public static int indexOf(GhoulType type) {
		return Arrays.asList(GhoulType.values()).indexOf(type);
	}
	
	public static GhoulType typeOf(int index) {
		return (GhoulType) Arrays.asList(GhoulType.values()).get(index);
	}
	
	private static Random random = new Random();
	public static GhoulType random() {
		return (GhoulType) Arrays.asList(GhoulType.values()).get(random.nextInt(4));
	}
}

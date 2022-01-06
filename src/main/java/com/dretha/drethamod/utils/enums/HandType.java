package com.dretha.drethamod.utils.enums;

public enum HandType {
	RIGHT {
		public HandType opposite() {return LEFT;}
	},
	LEFT {
		public HandType opposite() {return RIGHT;}
	};
	
	public abstract HandType opposite();
}

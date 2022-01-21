package com.dretha.drethamod.utils.enums;

public enum ImpactType {
	THRUST {
		public float speed() {
			return 15;
		}
		public ImpactType change() {
			return SLASH;
		}
	},
	SLASH {
		public float speed() {
			return (float) 25;
		}
		public ImpactType change() {
			return THRUST;
		}
	};
	public abstract float speed();
	public abstract ImpactType change();
}

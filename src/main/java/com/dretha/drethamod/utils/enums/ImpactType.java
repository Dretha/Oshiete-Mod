package com.dretha.drethamod.utils.enums;

import com.dretha.drethamod.capability.ICapaHandler;

public enum ImpactType {
	THRUST {
		public float speed(ICapaHandler capa) {
			return capa.isBlock() ? 10 : 15;
		}
		public ImpactType change() {
			return SLASH;
		}
	},
	SLASH {
		public float speed(ICapaHandler capa) {
			return (float) 25;
		}
		public ImpactType change() {
			return THRUST;
		}
	};
	public abstract float speed(ICapaHandler capa);
	public abstract ImpactType change();
}

package com.dretha.drethamod.utils.enums;

import com.dretha.drethamod.capability.ICapaHandler;

public enum ImpactType {
	THRUST {
		public float speed(ICapaHandler capa) {
			return capa.getImpactSpeed();
		}
		public ImpactType change() {
			return SLASH;
		}
	},
	SLASH {
		public float speed(ICapaHandler capa) {
			return (float) (capa.getImpactSpeed()*1.5);
		}
		public ImpactType change() {
			return THRUST;
		}
	};
	public abstract float speed(ICapaHandler capa);
	public abstract ImpactType change();
}

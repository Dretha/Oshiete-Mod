package com.dretha.drethamod.utils.enums;

import java.util.Arrays;
import java.util.List;

public enum ImpactType {
	THRUST(10) {
		public ImpactType change() {
			return SLASH;
		}
	},
	SLASH(25) {
		public ImpactType change() {
			return THRUST;
		}
	};

	public final int speed;

	ImpactType(int speed) {
		this.speed = speed;
	}

	public ImpactType change() {
		ImpactType result;
		try {
			result = list().get(this.index() + 1);
		} catch (IndexOutOfBoundsException e) {
			result = list().get(0);
		}
		return result;
	}

	public int index() {
		return Arrays.asList(ImpactType.values()).indexOf(this);
	}

	public static List<ImpactType> list() {
		return Arrays.asList(ImpactType.values());
	}
}

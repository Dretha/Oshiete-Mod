package com.dretha.drethamod.utils.enums;


import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.Random;

public enum GhoulType {
	UKAKU(InitItems.RAW_KAKUHO_UKAKU, 5, 0.5F),
	KOUKAKU(InitItems.RAW_KAKUHO_KOUKAKU, 10, 1.5F),
	RINKAKU(InitItems.RAW_KAKUHO_RINKAKU, 15, 0.7F),
	BIKAKU(InitItems.RAW_KAKUHO_BIKAKU, 7, 1F),
	NONE (null, 0, 0);

	public int index() {
		return GhoulType.indexOf(this);
	}
	public int id() {
		return GhoulType.indexOf(this) + 1;
	}

	public final Item kakuho;
	public final int speed;
	public final float blockMultiplier;

	GhoulType(Item kakuho, int speed, float blockMultiplier) {
		this.kakuho = kakuho;
		this.speed = speed;
		this.blockMultiplier = blockMultiplier;
	}

	public Item getKakuho() {
		return kakuho;
	}

	public int getProtection(ICapaHandler capa) {
		return (int) (capa.exactRank() * 10 * this.blockMultiplier);
	}
	
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

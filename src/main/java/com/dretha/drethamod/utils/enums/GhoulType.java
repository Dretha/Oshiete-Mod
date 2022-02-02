package com.dretha.drethamod.utils.enums;


import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.Random;

public enum GhoulType {
	UKAKU(InitItems.RAW_KAKUHO_UKAKU, 5, 0.5F, 0.77F, 0.5F),
	KOUKAKU(InitItems.RAW_KAKUHO_KOUKAKU, 10, 1.5F, 1F, 1.5F),
	RINKAKU(InitItems.RAW_KAKUHO_RINKAKU, 15, 0.7F, 1.5F, 1F),
	BIKAKU(InitItems.RAW_KAKUHO_BIKAKU, 7, 1F, 1F, 1F),
	NONE (null, 0, 0, 0, 0);

	public int index() {
		return GhoulType.indexOf(this);
	}
	public int id() {
		return GhoulType.indexOf(this) + 1;
	}

	public final Item kakuho;
	public final int speed;
	public final float blockMultiplier;
	public final float kuinkeDamageMultiplier;
	public final float kuinkeSpeedMultiplier;
	public static final float damageCoefficient = 1.25F;
	public static final float slashDebuf = 0.5F;

	GhoulType(Item kakuho, int speed, float blockMultiplier, float kuinkeDamageMultiplier, float kuinkeSpeedMultiplier) {
		this.kakuho = kakuho;
		this.speed = speed;
		this.blockMultiplier = blockMultiplier;
		this.kuinkeDamageMultiplier = kuinkeDamageMultiplier;
		this.kuinkeSpeedMultiplier = kuinkeSpeedMultiplier;
	}

	public Item getKakuho() {
		return kakuho;
	}

	public static Item getKakuho(GhoulType type) {
		switch (type) {
			case UKAKU:
				return InitItems.RAW_KAKUHO_UKAKU;
			case BIKAKU:
				return InitItems.RAW_KAKUHO_BIKAKU;
			case KOUKAKU:
				return InitItems.RAW_KAKUHO_KOUKAKU;
			case RINKAKU:
				return InitItems.RAW_KAKUHO_RINKAKU;
		}
		return null;
	}
	
	public static int indexOf(GhoulType type) {
		return Arrays.asList(GhoulType.values()).indexOf(type);
	}
	
	public static GhoulType typeOf(int index) {
		return Arrays.asList(GhoulType.values()).get(index);
	}
	
	private static final Random random = new Random();
	public static GhoulType random() {
		return Arrays.asList(GhoulType.values()).get(random.nextInt(4));
	}

	public static GhoulType getWeakType(GhoulType strongType) {
		switch (strongType) {
			case UKAKU:
				return BIKAKU;
			case KOUKAKU:
				return UKAKU;
			case RINKAKU:
				return KOUKAKU;
			case BIKAKU:
				return RINKAKU;
		}
		return NONE;
	}
}

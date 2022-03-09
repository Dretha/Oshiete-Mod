package com.dretha.drethamod.utils.enums;


import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.main.Oshiete;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

import java.util.Arrays;

public enum GhoulType {
	NONE (I18n.format("desk.none"), null, 1, 1, 1, 1),
	UKAKU(I18n.format("desk.ukaku"), InitItems.RAW_KAKUHO_UKAKU, 5, 0.5F, 0.77F, 0.5F),
	KOUKAKU(I18n.format("desk.koukaku"), InitItems.RAW_KAKUHO_KOUKAKU, 10, 1.25F, 1F, 1.5F),
	RINKAKU(I18n.format("desk.rinkaku"), InitItems.RAW_KAKUHO_RINKAKU, 15, 0.75F, 1.5F, 1F),
	BIKAKU(I18n.format("desk.bikaku"), InitItems.RAW_KAKUHO_BIKAKU, 7, 1F, 1F, 1F);

	public int id() {
		return Arrays.asList(GhoulType.values()).indexOf(this);
	}

	public final String description;
	public final Item kakuho;
	public final int speed;
	public final float blockMultiplier;
	public final float kuinkeDamageMultiplier;
	public final float kuinkeSpeedMultiplier;
	public static final float damageCoefficient = 1.5F;
	public static final float slashDebuf = 0.4F;

	GhoulType(String description, Item kakuho, int speed, float blockMultiplier, float kuinkeDamageMultiplier, float kuinkeSpeedMultiplier) {
		this.description = description;
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

	public GhoulType change() {
		GhoulType result;
		try {
			result = GhoulType.values()[this.id() + 1];
		} catch (IndexOutOfBoundsException e) {
			result = GhoulType.values()[0];
		}
		return result;
	}
	
	public static GhoulType typeOf(int index) {
		return Arrays.asList(GhoulType.values()).get(index);
	}

	public static GhoulType random() {
		return Arrays.asList(GhoulType.values()).get(Oshiete.random.nextInt(4)+1);
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

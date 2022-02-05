package com.dretha.drethamod.init;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.clothes.blue_blouse.BlueBlouseArmor;
import com.dretha.drethamod.client.geckolib.clothes.grandpa_hat.GrandpaHatArmor;
import com.dretha.drethamod.client.geckolib.clothes.kureo_cape.KureoArmor;
import com.dretha.drethamod.client.geckolib.clothes.test_hat.ClothesArmor;
import com.dretha.drethamod.items.*;
import com.dretha.drethamod.items.clothes.ItemCloth;
import com.dretha.drethamod.items.clothes.ItemMask;
import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.items.firearm.ItemBullet;
import com.dretha.drethamod.items.firearm.ItemMagazine;
import com.dretha.drethamod.items.firearm.assault_rifle_hk33.HK33Item;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import com.dretha.drethamod.items.kuinkes.QColdSteel;
import com.dretha.drethamod.items.kuinkes.Weapons;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class InitItems {
	
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<IKuinke> UNIQUE_KUINKIES = new ArrayList<>();
	
	//tablets
	public static final Item TABLET_HUMAN = new ItemTabletCreative("tablet_human", I18n.format("info.tablethuman"), GhoulType.NONE);
	public static final Item TABLET_UKAKU = new ItemTabletCreative("tablet_ukaku", I18n.format("info.none"), GhoulType.UKAKU);
	public static final Item TABLET_KOUKAKU = new ItemTabletCreative("tablet_koukaku", I18n.format("info.none"), GhoulType.KOUKAKU);
	public static final Item TABLET_RINKAKU = new ItemTabletCreative("tablet_rinkaku", I18n.format("info.none"), GhoulType.RINKAKU);
	public static final Item TABLET_BIKAKU = new ItemTabletCreative("tablet_bikaku", I18n.format("info.none"), GhoulType.BIKAKU);

	//food
	public static final Item HUMAN_MEAT = new ItemGhoulFood("human_meat", I18n.format("info.human_meat"), 8, 10, 11, true, EnumKeeper.LOW_SATIATION);
	public static final Item HUMAN_EYE = new ItemGhoulFood("human_eye", I18n.format("info.human_eye"), 2, 12, 3, true, EnumKeeper.LOW_SATIATION);
	public static final Item HUMAN_BLOOD_BOTTLE = new ItemGhoulFood("human_blood_bottle", I18n.format("info.blood_bottle"), 3, 8, 6, true, EnumKeeper.LOW_SATIATION, EnumAction.DRINK);

	public static final Item RAW_KAKUHO_UKAKU = new Kakuho("kakuho_ukaku", I18n.format("info.kakuho"), GhoulType.UKAKU, 4, 4);
	public static final Item RAW_KAKUHO_KOUKAKU = new Kakuho("kakuho_koukaku", I18n.format("info.kakuho"), GhoulType.KOUKAKU, 4, 4);
	public static final Item RAW_KAKUHO_RINKAKU = new Kakuho("kakuho_rinkaku", I18n.format("info.kakuho"), GhoulType.RINKAKU, 4, 4);
	public static final Item RAW_KAKUHO_BIKAKU = new Kakuho("kakuho_bikaku", I18n.format("info.kakuho"), GhoulType.BIKAKU, 4, 4);

	//public static final Item PREPARED_KAKUHO = new Kakuho("prepared_kakuho", I18n.format("info.kakuho"), 4, 4);
	//public static final Item INDUCED_KAKUHO = new Kakuho("induced_kakuho", I18n.format("info.kakuho"), 4, 4);

	public static final Item GHOUL_MEAT = new ItemGhoulFood("ghoul_meat", I18n.format("info.kakuho"), 8, 10, 16, true, EnumKeeper.LOW_SATIATION);
	public static final Item KAGUNE_SHARD = new ItemGhoulFood("kagune_shard", I18n.format("info.kakuho"), 2, 2, 24, true, EnumKeeper.HIGH_SATIATION);

	//technical items
	public static final Item UKAKU_FLAME_BIG_01 = new RenderingItem("ukaku_flame_big_01");
	public static final Item KAKUGAN31 = new RenderingItem("kakugan31");
	public static final Item KAKUGAN12 = new RenderingItem("kakugan12");
	public static final Item KAKUGAN11 = new RenderingItem("kakugan11");

	//clothes
	public static final Item CLOTHESITEM1 = new ClothesArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.HEAD, "hat");
	public static final Item TEST_HAT = new ItemCloth("testhat", 2, new ItemStack(CLOTHESITEM1));

	public static final Item BLUE_BLOUSE_HEAD = new BlueBlouseArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.HEAD, "blue_blouse_head");
	public static final Item BLUE_BLOUSE_BODY = new BlueBlouseArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.CHEST, "blue_blouse_body");
	public static final Item BLUE_BLOUSE = new ItemCloth("blue_blouse", 1, new ItemStack(BLUE_BLOUSE_HEAD), new ItemStack(BLUE_BLOUSE_BODY));

	public static final Item GRANDPA_HAT_HEAD = new GrandpaHatArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.HEAD, "grandpa_hat_head");
	public static final Item GRANDPA_HAT = new ItemCloth("grandpa_hat", 2, new ItemStack(GRANDPA_HAT_HEAD));

	public static final Item KUREO_BODY = new KureoArmor(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST, "kureo_body");
	public static final Item KUREO_LEGS = new KureoArmor(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.LEGS, "kureo_legs");
	public static final Item KUREO_CAPE = new ItemCloth("kureo_cape", 3, new ItemStack(KUREO_BODY), new ItemStack(KUREO_LEGS));

	public static final Item BLACK_TUX_BODY = new KureoArmor(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST, "black_tux_body");
	public static final Item BLACK_TUX_LEGS = new KureoArmor(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.LEGS, "black_tux_legs");
	public static final Item BLACK_TUX = new ItemCloth("black_tux", 1, new ItemStack(BLACK_TUX_BODY), new ItemStack(BLACK_TUX_LEGS));

	public static final Item GOURMET_TUX_BODY = new KureoArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.CHEST, "gourmet_tux_body");
	public static final Item GOURMET_TUX_LEGS = new KureoArmor(ArmorMaterial.DIAMOND, 2, EntityEquipmentSlot.LEGS, "gourmet_tux_legs");
	public static final Item GOURMET_TUX = new ItemCloth("gourmet_tux", 1, new ItemStack(GOURMET_TUX_BODY), new ItemStack(GOURMET_TUX_LEGS));

	public static final Item BLACK_BLOUSE_HEAD = new BlueBlouseArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.CHEST, "black_blouse_head");
	public static final Item BLACK_BLOUSE_BODY = new BlueBlouseArmor(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.LEGS, "black_blouse_body");
	public static final Item BLACK_BLOUSE = new ItemCloth("black_blouse", 1, new ItemStack(BLACK_BLOUSE_HEAD), new ItemStack(BLACK_BLOUSE_BODY));

	//tuners
	public static final Item UKAKU_STATE_TUNER = new Tuner("ukaku_state_tuner", player -> {
		ICapaHandler capa = EventsHandler.getCapaMP(player);
		PersonStats stats = capa.personStats();
		if (!stats.ukaku()) return;
		stats.setUkakuState(stats.getUkakuState().next());
	});
	
	//masks
	public static final Item UTA_MASK = new ItemMask("mask_uta");
	public static final Item NORO_MASK = new ItemMask("mask_noro");
	public static final Item AOGIRI_MASK = new ItemMask("mask_aogiri");

	//kuinkies
	public static final Item KNIFE = new QColdSteel("knife", Weapons.KNIFE);
	public static final Item KATANA = new QColdSteel("katana", Weapons.KATANA);
	public static final Item SCYTHE = new QColdSteel("scythe", Weapons.SCYTHE);
	public static final Item CLEAVER = new QColdSteel("cleaver", Weapons.CLEAVER);
	public static final Item CUDGEL = new QColdSteel("cudgel", Weapons.CUDGEL);

	//unique kuinkies
	public static final Item KATANA_SASAKI = new KuinkeMeleeBase("katana_sasaki", 128, 6, 0, 3, EnumKeeper.LEGENDARY);

	//materials
	public static final Item KUINKE_STEEL_SHARD = new ItemMaterial("qsteel_shard", EnumKeeper.Q_STEEL_RARITY);
	public static final Item KUINKE_STEEL = new ItemMaterial("kuinke_steel", EnumKeeper.Q_STEEL_RARITY);
	public static final Item STEEL = new ItemMaterial("steel_ingot");
	public static final Item FIRECLAY_BAR = new ItemMaterial("fireclay_bar");
	public static final Item CHAMOTTE = new ItemMaterial("chamotte");
	public static final Item FIRE_CLAY = new ItemMaterial("fire_clay");
	public static final Item IRON_PLATE = new ItemMaterial("iron_plate");
	public static final Item STEEL_PLATE = new ItemMaterial("steel_plate");
	public static final Item SHOCK_RESISTANT_HANDLE = new ItemMaterial("shock_resistant_handle", EnumKeeper.Q_STEEL_RARITY);
	public static final Item KUINKE_STEEL_BAR = new ItemMaterial("q_steel_bar", EnumKeeper.Q_STEEL_RARITY);
	public static final Item BALSAM = new ItemMaterial("balsam");

	//instruments
	public static final Item STEEL_HAMMER = new ItemTool("steel_hammer", EnumKeeper.STEEL, Tools.HAMMER);

	//weapon
	public static final Item MAGAZINE = new ItemMagazine("magazine");

	public static final Item IRON_BULLET = new ItemBullet("iron_bullet", Bullets.IRON);
	public static final Item STEEL_BULLET = new ItemBullet("steel_bullet", Bullets.STEEL);
	public static final Item Q_BULLET = new ItemBullet("q_bullet", Bullets.Q_STEEL, EnumKeeper.Q_STEEL_RARITY);

	public static final Item HK33 = new HK33Item("assault_rifle_hk33");

	//spawn eggs
	public static final Item SPAWN_EGG_GHOUL = new SpawnEgg("spawn_egg_ghoul");
	public static final Item SPAWN_EGG_DOVE = new SpawnEgg("spawn_egg_dove");
}

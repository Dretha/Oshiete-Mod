package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Random;

public class HumanTexHandler {

    public static String getRandomTexture()
    {
        Random random = new Random();
        String texture = String.format("skin%04d", random.nextInt(KAKUGAN_PARAMS.size()) + 1);
        return texture;
    }

    private static HashMap<String, String> KAKUGAN_PARAMS = new HashMap<>();
    private int skinID = 0;

    public static void init() {
        KAKUGAN_PARAMS.clear();
        KAKUGAN_PARAMS.put("skin0001", "31");
        KAKUGAN_PARAMS.put("skin0002", "12");
        KAKUGAN_PARAMS.put("skin0003", "11");
        KAKUGAN_PARAMS.put("skin0004", "11");
        KAKUGAN_PARAMS.put("skin0005", "12");
        KAKUGAN_PARAMS.put("skin0006", "11");
        KAKUGAN_PARAMS.put("skin0007", "11");
        KAKUGAN_PARAMS.put("skin0008", "31");
        KAKUGAN_PARAMS.put("skin0009", "11");
        KAKUGAN_PARAMS.put("skin0010", "11");
        KAKUGAN_PARAMS.put("skin0011", "11");
        KAKUGAN_PARAMS.put("skin0012", "11");
        KAKUGAN_PARAMS.put("skin0013", "12");
    }
    private String name() {
        return String.format("skin%04d", skinID++);
    }

    public static Item getKakugan(String texture) {

        String params = KAKUGAN_PARAMS.get(texture);

        if (params.equals("31")) {
            return InitItems.KAKUGAN31;
        }
        if (params.equals("12")) {
            return InitItems.KAKUGAN12;
        }
        if (params.equals("11")) {
            return InitItems.KAKUGAN11;
        }

        return InitItems.KAKUGAN31;
    }
}

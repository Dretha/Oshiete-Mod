package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Random;

public class SkinHandler {

    public static final ResourceLocation kakugan31 = new ResourceLocation(Reference.MODID, "textures/kakugans/kakugan31.png");
    public static final ResourceLocation kakugan11 = new ResourceLocation(Reference.MODID, "textures/kakugans/kakugan11.png");
    public static final ResourceLocation kakugan12 = new ResourceLocation(Reference.MODID, "textures/kakugans/kakugan12.png");

    public static String getRandomTexture()
    {
        Random random = new Random();
        String texture = String.format("skin%04d", random.nextInt(KAKUGAN_PARAMS.size()) + 1);
        return texture;
    }

    private static final HashMap<String, ResourceLocation> KAKUGAN_PARAMS = new HashMap<>();
    private int skinID = 0;

    public static void init() {
        KAKUGAN_PARAMS.clear();
        KAKUGAN_PARAMS.put("skin0001", kakugan31);
        KAKUGAN_PARAMS.put("skin0002", kakugan12);
        KAKUGAN_PARAMS.put("skin0003", kakugan11);
        KAKUGAN_PARAMS.put("skin0004", kakugan11);
        KAKUGAN_PARAMS.put("skin0005", kakugan12);
        KAKUGAN_PARAMS.put("skin0006", kakugan11);
        KAKUGAN_PARAMS.put("skin0007", kakugan11);
        KAKUGAN_PARAMS.put("skin0008", kakugan31);
        KAKUGAN_PARAMS.put("skin0009", kakugan11);
        KAKUGAN_PARAMS.put("skin0010", kakugan11);
        KAKUGAN_PARAMS.put("skin0011", kakugan11);
        KAKUGAN_PARAMS.put("skin0012", kakugan11);
        KAKUGAN_PARAMS.put("skin0013", kakugan12);
    }
    private String name() {
        return String.format("skin%04d", skinID++);
    }

    public static ResourceLocation getKakuganResource(String textureID) {
        return KAKUGAN_PARAMS.get(textureID);
    }

    public static ResourceLocation changeKakugan(ResourceLocation kakugan) {
        if (kakugan==kakugan31)
            return kakugan11;
        if (kakugan==kakugan11)
            return kakugan12;
        return kakugan31;
    }
}


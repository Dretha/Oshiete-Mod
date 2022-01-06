package com.dretha.drethamod.client.geckolib.clothes;

import com.dretha.drethamod.client.geckolib.clothes.black_blouse.BlackBlouseArmor;
import com.dretha.drethamod.client.geckolib.clothes.black_blouse.BlackBlouseRender;
import com.dretha.drethamod.client.geckolib.clothes.black_tux.BlackTuxArmor;
import com.dretha.drethamod.client.geckolib.clothes.black_tux.BlackTuxRender;
import com.dretha.drethamod.client.geckolib.clothes.blue_blouse.BlueBlouseArmor;
import com.dretha.drethamod.client.geckolib.clothes.blue_blouse.BlueBlouseRender;
import com.dretha.drethamod.client.geckolib.clothes.gourmet_tux.GourmetTuxArmor;
import com.dretha.drethamod.client.geckolib.clothes.gourmet_tux.GourmetTuxRender;
import com.dretha.drethamod.client.geckolib.clothes.grandpa_hat.GrandpaHatArmor;
import com.dretha.drethamod.client.geckolib.clothes.grandpa_hat.GrandpaHatRender;
import com.dretha.drethamod.client.geckolib.clothes.kureo_cape.KureoArmor;
import com.dretha.drethamod.client.geckolib.clothes.kureo_cape.KureoRender;
import com.dretha.drethamod.client.geckolib.clothes.test_hat.ClothesArmor;
import com.dretha.drethamod.client.geckolib.clothes.test_hat.KanekiBlueBlouseRender;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class InitClothes {
    public static void init() {
        GeoArmorRenderer.registerArmorRenderer(ClothesArmor.class, new KanekiBlueBlouseRender());
        GeoArmorRenderer.registerArmorRenderer(BlueBlouseArmor.class, new BlueBlouseRender());
        GeoArmorRenderer.registerArmorRenderer(GrandpaHatArmor.class, new GrandpaHatRender());
        GeoArmorRenderer.registerArmorRenderer(KureoArmor.class, new KureoRender());
        GeoArmorRenderer.registerArmorRenderer(BlackTuxArmor.class, new BlackTuxRender());
        GeoArmorRenderer.registerArmorRenderer(GourmetTuxArmor.class, new GourmetTuxRender());
        GeoArmorRenderer.registerArmorRenderer(BlackBlouseArmor.class, new BlackBlouseRender());
    }
}

package layers.entity;

import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.render.RenderHuman;
import com.dretha.drethamod.init.InitItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;

public class EntityLayerMask  implements LayerRenderer<EntityHuman> {

    private final RenderLivingBase<?> renderer;

    public EntityLayerMask(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityHuman ghoul, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        RenderHuman renderHuman = (RenderHuman) renderer;
        GlStateManager.pushMatrix();

        GlStateManager.disableLighting();

        ((ModelBiped)renderHuman.getMainModel()).bipedHead.postRender(0.0625F);

        GlStateManager.translate(/*не трогать*/0, /*вниз*/0.1875F, /*вперед*/-0.045F);
        GlStateManager.rotate(180, 0, 0, 0);
        GlStateManager.scale(0.5F, 0.5F, 0.5F);

        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(InitItems.AOGIRI_MASK), ghoul, ItemCameraTransforms.TransformType.HEAD, false);

        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}

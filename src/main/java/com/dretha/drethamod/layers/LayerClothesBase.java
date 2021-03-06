package com.dretha.drethamod.layers;

import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.utils.stats.PersonStats;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

@SideOnly(Side.CLIENT)
public abstract class LayerClothesBase<T extends ModelBase> implements LayerRenderer<EntityLivingBase>
{
    ItemStack air = new ItemStack(Items.AIR);
    protected static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    protected T modelLeggings;
    protected T modelArmor;
    private final float alpha = 1.0F;
    private final float colorR = 1.0F;
    private final float colorG = 1.0F;
    private final float colorB = 1.0F;
    private final RenderLivingBase<?> renderer;
    private boolean skipRenderGlint;
    private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();

    public LayerClothesBase(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
        this.initArmor();
    }

    public void doRenderLayer(EntityLivingBase base, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        PersonStats stats = PersonStats.getStats(base);

        ItemStack mask = stats.getInventory().getStackInSlot(0);
        if (!mask.isEmpty()) {

            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            if (base instanceof EntityPlayer) {
                RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer) base).getSkinType());
                renderPlayer.getMainModel().bipedHead.postRender(0.0625F);
            } else {
                ((ModelBiped)renderer.getMainModel()).bipedHead.postRender(0.0625F);
            }

            GlStateManager.translate(/*?? ???????*/0, (base.isSneaking() ? 0.2F : 0.0F) + /*????*/0.1875F, /*??????*/-0.045F);
            GlStateManager.rotate(180, 0, 0, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getMinecraft().getRenderItem().renderItem(mask, base, ItemCameraTransforms.TransformType.HEAD, false);

            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
        }

        for (int index = 1; index<4; index++) {
            if (base.isDead) return;

            ItemStack cloth = stats.getInventory().getStackInSlot(index);

            if (cloth.getItem() instanceof IDressable) {
                IDressable dressable = (IDressable) cloth.getItem();

                for (ItemStack stack : dressable.getArmors()) {
                    this.renderArmorLayer(base, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, ((ItemArmor) stack.getItem()).getEquipmentSlot(), stack);
                }
            }
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }

    private void renderArmorLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, ItemStack itemstack)
    {
        if (itemstack.getItem() instanceof ItemArmor)
        {
            ItemArmor itemarmor = (ItemArmor)itemstack.getItem();

            if (itemarmor.getEquipmentSlot() == slotIn)
            {
                T model = this.getModelFromSlot(slotIn);
                model = getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, model);
                model.setModelAttributes(this.renderer.getMainModel());
                model.setLivingAnimations(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTicks);
                this.setModelSlotVisible(model, slotIn);
                boolean flag = this.isLegSlot(slotIn);
                this.renderer.bindTexture(this.getArmorResource(entityLivingBaseIn, itemstack, slotIn, null));

                {
                    if (itemarmor.hasOverlay(itemstack)) // Allow this for anything, not only cloth
                    {
                        int i = itemarmor.getColor(itemstack);
                        float f = (float)(i >> 16 & 255) / 255.0F;
                        float f1 = (float)(i >> 8 & 255) / 255.0F;
                        float f2 = (float)(i & 255) / 255.0F;
                        GlStateManager.color(this.colorR * f, this.colorG * f1, this.colorB * f2, this.alpha);
                        GlStateManager.disableLighting();
                        model.render(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                        GlStateManager.disableLighting();
                        this.renderer.bindTexture(this.getArmorResource(entityLivingBaseIn, itemstack, slotIn, "overlay"));
                    }
                    { // Non-colored
                        GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
                        model.render(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                    } // Default
                    if (!this.skipRenderGlint && itemstack.hasEffect())
                    {
                        renderEnchantedGlint(this.renderer, entityLivingBaseIn, model, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                    }
                }
            }
        }
    }

    public T getModelFromSlot(EntityEquipmentSlot slotIn)
    {
        return this.isLegSlot(slotIn) ? this.modelLeggings : this.modelArmor;
    }

    private boolean isLegSlot(EntityEquipmentSlot slotIn)
    {
        return slotIn == EntityEquipmentSlot.LEGS;
    }

    public static void renderEnchantedGlint(RenderLivingBase<?> p_188364_0_, EntityLivingBase p_188364_1_, ModelBase model, float p_188364_3_, float p_188364_4_, float p_188364_5_, float p_188364_6_, float p_188364_7_, float p_188364_8_, float p_188364_9_)
    {
        float f = (float)p_188364_1_.ticksExisted + p_188364_5_;
        p_188364_0_.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        float f1 = 0.5F;
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);

        for (int i = 0; i < 2; ++i)
        {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
            float f2 = 0.76F;
            GlStateManager.color(0.38F, 0.19F, 0.608F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f3 = 0.33333334F;
            GlStateManager.scale(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.rotate(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            model.render(p_188364_1_, p_188364_3_, p_188364_4_, p_188364_6_, p_188364_7_, p_188364_8_, p_188364_9_);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    }

    @Deprecated //Use the more sensitive version getArmorResource below
    private ResourceLocation getArmorResource(ItemArmor armor, boolean p_177181_2_)
    {
        return this.getArmorResource(armor, p_177181_2_, null);
    }

    @Deprecated //Use the more sensitive version getArmorResource below
    private ResourceLocation getArmorResource(ItemArmor armor, boolean p_177178_2_, String p_177178_3_)
    {
        String s = String.format("textures/models/armor/%s_layer_%d%s.png", armor.getArmorMaterial().getName(), p_177178_2_ ? 2 : 1, p_177178_3_ == null ? "" : String.format("_%s", p_177178_3_));
        ResourceLocation resourcelocation = ARMOR_TEXTURE_RES_MAP.get(s);

        if (resourcelocation == null)
        {
            resourcelocation = new ResourceLocation(s);
            ARMOR_TEXTURE_RES_MAP.put(s, resourcelocation);
        }

        return resourcelocation;
    }

    protected abstract void initArmor();

    protected abstract void setModelSlotVisible(T p_188359_1_, EntityEquipmentSlot slotIn);

    /*=================================== FORGE START =========================================*/

    /**
     * Hook to allow item-sensitive armor model. for LayerBipedArmor.
     */
    protected T getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, T model)
    {
        return model;
    }

    /**
     * More generic ForgeHook version of the above function, it allows for Items to have more control over what texture they provide.
     *
     * @param entity Entity wearing the armor
     * @param stack ItemStack for the armor
     * @param slot Slot ID that the item is in
     * @param type Subtype, can be null or "overlay"
     * @return ResourceLocation pointing at the armor's texture
     */
    public ResourceLocation getArmorResource(net.minecraft.entity.Entity entity, ItemStack stack, EntityEquipmentSlot slot, String type)
    {
        ItemArmor item = (ItemArmor)stack.getItem();
        String texture = item.getArmorMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(':');
        if (idx != -1)
        {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }
        String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, (isLegSlot(slot) ? 2 : 1), type == null ? "" : String.format("_%s", type));

        s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
        ResourceLocation resourcelocation = ARMOR_TEXTURE_RES_MAP.get(s1);

        if (resourcelocation == null)
        {
            resourcelocation = new ResourceLocation(s1);
            ARMOR_TEXTURE_RES_MAP.put(s1, resourcelocation);
        }

        return resourcelocation;
    }
    /*=================================== FORGE END ===========================================*/
}

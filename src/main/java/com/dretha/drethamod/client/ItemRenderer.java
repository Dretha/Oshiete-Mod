package com.dretha.drethamod.client;

import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ItemRenderer extends GeoItemRenderer<KuinkeMeleeBase>
{
    public ItemRenderer()
    {
        super(new ItemModel());
    }

    public ItemRenderer(String model, String texture, String animation)
    {
        super(new ItemModel(model, texture, animation));
    }
    public ItemRenderer(String name)
    {
        super(new ItemModel(name, name, name));
    }
}


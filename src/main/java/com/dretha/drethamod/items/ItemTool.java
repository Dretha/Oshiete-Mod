package com.dretha.drethamod.items;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.model.ModelLoader;

public class ItemTool extends Item implements IHasModel {

    private final float speed;
    protected Item.ToolMaterial toolMaterial;
    private final int attackDamage;
    private Tool tool;

    public ItemTool(String name, ToolMaterial material, Tool tool)
    {
            setRegistryName(name);
            setUnlocalizedName(name);
            setCreativeTab(ModCreativeTabs.TOOLS);
            this.toolMaterial = material;
            this.maxStackSize = 1;
            this.setMaxDamage(tool.getHardness(material.getMaxUses()));
            this.speed = material.getAttackDamage() + 1.0F;
            this.attackDamage = tool.getDamage((int) material.getAttackDamage());
            this.tool = tool;
            setContainerItem(this);

            InitItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }

    public String getMaterialName()
    {
        return this.toolMaterial.toString();
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 1.5, 0));
        }

        return multimap;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        stack.setItemDamage(stack.getItemDamage() + 1);

        return super.getContainerItem(stack);
    }

    public float getAttackDamage()
    {
        return this.toolMaterial.getAttackDamage();
    }

    public Tool getTool() {
        return tool;
    }
}

package com.dretha.drethamod.items;

import javax.annotation.Nullable;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.projectile.EntityEnderArrow;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.dretha.drethamod.utils.interfaces.IKagune;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKaguneUkaku extends KaguneBase implements IKagune, IHasModel{

	public static float arrowVelocity = 1F;
	private int shootSpeed = 3;
	
	public ItemKaguneUkaku(String name) {
		this.maxStackSize = 1;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        
        setCreativeTab(ModCreativeTab.CTAB);
        InitItems.ITEMS.add(this);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() != InitItems.KAGUNE_UKAKU ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }
	
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = true;

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.capabilities.isCreativeMode && !flag)
        {
            return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
    }
	
	
	//shooting
	
	public static EntityRCShard createShard(World worldIn, EntityLivingBase shooter)
    {
        EntityRCShard entitytippedrcshard = new EntityRCShard(worldIn, shooter);
        return entitytippedrcshard;
    }
	public static EntityEnderArrow createShard1(World worldIn, EntityLivingBase shooter)
    {
		EntityEnderArrow entitytippedrcshard = new EntityEnderArrow(worldIn, shooter);
        return entitytippedrcshard;
    }
	
	/*private int countShoot = 0;
	private final int maxCountShoot = 5;
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		
	    	ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
	        if (!player.world.isRemote && countShoot==maxCountShoot && capa.getRClevel()>19) {
	        	countShoot=0;
	    	     
	            EntityRCShard entityrcshard = createShard(player.world, player);
	            entityrcshard.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, ItemKaguneUkaku.arrowVelocity * 3.0F, 1.0F);
	            entityrcshard.setIsCritical(false);
	            entityrcshard.setDamage(0);
	            entityrcshard.setKnockbackStrength(0);
	            entityrcshard.setFire(0);     
	                       
	            player.world.spawnEntity(entityrcshard);
	            player.world.playSound(null, player.getPosition(), InitSounds.ukaku_shooting, SoundCategory.PLAYERS, 0.5F, 1.0F);
	            capa.removeRClevel(20);
	        }
	        if (countShoot<maxCountShoot) {
	        	countShoot++;  
	        }
    }*/
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		
	    	ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
	        if (!player.world.isRemote && (player.ticksExisted%shootSpeed)==0 && capa.getRClevel()>19) {
	    	     
	            EntityRCShard entityrcshard = createShard(player.world, player);
	            entityrcshard.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, ItemKaguneUkaku.arrowVelocity * 3F, 1.0F);
	            entityrcshard.setIsCritical(false);
	            entityrcshard.setDamage(0);
	            entityrcshard.setKnockbackStrength(0);
	            entityrcshard.setFire(0);     
	                       
	            player.world.spawnEntity(entityrcshard);
	            
	            player.world.playSound(null, player.getPosition(), InitSounds.ukaku_shooting, SoundCategory.PLAYERS, 0.5F, 1.0F);
	            if (!((EntityPlayer) player).isCreative()) {
	                capa.removeRClevel(20);
	            }
	        }
    }
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
}

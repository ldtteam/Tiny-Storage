package com.timthebrick.tinystorage.item;

import java.util.List;

import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemChestFilter extends Item {

	public static final String[] nameSuffix = new String[] { "Small", "Medium", "Large" };
	private IIcon[] icons;

	public ItemChestFilter() {
		super();
		this.setUnlocalizedName("chestFilter");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		int metaData = itemStack.getItemDamage();
		if (metaData == 0) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:chestFilterPrefix.small"));
		} else if (metaData == 1) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:chestFilterPrefix.medium"));
		} else if (metaData == 2) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:chestFilterPrefix.large"));
		}
	}

	@Override
	public IIcon getIconFromDamage(int metaData) {
		int j = MathHelper.clamp_int(metaData, 0, 2);
		return this.icons[j];
	}

	@Override
	public void registerIcons(IIconRegister register) {
		this.icons = new IIcon[nameSuffix.length];
		for (int i = 0; i < icons.length; i++) {
			this.icons[i] = register.registerIcon((References.MOD_ID.toLowerCase() + ":chestFilter" + nameSuffix[i]));
		}
	}

}

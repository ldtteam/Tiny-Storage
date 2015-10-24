package com.smithsmodding.tinystorage.common.item;

import java.util.List;

import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.reference.Messages;
import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.common.reference.References;

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

public class ItemChestFilter extends Item {

	private static final String[] nameSuffix = new String[] { "Small", "Medium", "Large" };
	private IIcon[] icons;

	public ItemChestFilter() {
		super();
		this.setUnlocalizedName(Names.Items.CHEST_FILTER);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			//noinspection unchecked
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		int metaData = itemStack.getItemDamage();
		if (metaData == 0) {
			//noinspection unchecked
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_1));
		} else if (metaData == 1) {
			//noinspection unchecked
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_2));
		} else if (metaData == 2) {
			//noinspection unchecked
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_3));
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

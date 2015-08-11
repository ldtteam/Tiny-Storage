package com.timthebrick.tinystorage.common.item;

import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemChestLock extends Item {

	private IIcon icon;

	public ItemChestLock() {
		super();
		this.setUnlocalizedName(Names.Items.CHEST_LOCK);
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":chestLock");
	}

}

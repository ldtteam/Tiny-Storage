package com.timthebrick.tinystorage.item;

import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.References;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemChestLock extends Item {

	private IIcon icon;

	public ItemChestLock() {
		super();
		this.setUnlocalizedName("chestLock");
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":chestLock");
	}

}

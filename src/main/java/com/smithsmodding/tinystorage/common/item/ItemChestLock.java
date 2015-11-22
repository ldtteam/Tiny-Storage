package com.smithsmodding.tinystorage.common.item;

import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;

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

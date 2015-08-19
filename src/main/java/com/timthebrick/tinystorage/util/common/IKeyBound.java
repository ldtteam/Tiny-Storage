package com.timthebrick.tinystorage.util.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.common.reference.Key;

public interface IKeyBound {
	void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key);
}

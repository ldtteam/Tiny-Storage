package com.smithsmodding.tinystorage.util.common;

import com.smithsmodding.tinystorage.common.reference.Key;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {
	void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key);
}

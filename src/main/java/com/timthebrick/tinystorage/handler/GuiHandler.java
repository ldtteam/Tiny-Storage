package com.timthebrick.tinystorage.handler;

import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyChest;
import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.reference.GUIs;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()){
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest)world.getTileEntity(x, y, z);
			return new ContainerTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()){
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest)world.getTileEntity(x, y, z);
			return new GuiTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		}
		return null;
	}

}

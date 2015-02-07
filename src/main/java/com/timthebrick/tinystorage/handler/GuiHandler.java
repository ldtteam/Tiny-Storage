package com.timthebrick.tinystorage.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.client.gui.inventory.GuiFilterChest;
import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyChest;
import com.timthebrick.tinystorage.inventory.ContainerFilterChest;
import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.reference.GUIs;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()){
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest)world.getTileEntity(x, y, z);
			return new ContainerTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		}else if (id == GUIs.FILTER_CHEST.ordinal()){
			TileEntityFilterChest tileEntityFilterChest = (TileEntityFilterChest)world.getTileEntity(x, y, z);
			return new ContainerFilterChest(entityPlayer.inventory, tileEntityFilterChest);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()){
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest)world.getTileEntity(x, y, z);
			return new GuiTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		}else if (id == GUIs.FILTER_CHEST.ordinal()){
			TileEntityFilterChest tileEntityFilterChest = (TileEntityFilterChest)world.getTileEntity(x, y, z);
			return new GuiFilterChest(entityPlayer.inventory, tileEntityFilterChest);
		}
		return null;
	}

}

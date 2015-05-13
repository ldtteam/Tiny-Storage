package com.timthebrick.tinystorage.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiDraw;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiFilterChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiMicroChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiPeacefulChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiTinyChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiTrashChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiVacuumChest;
import com.timthebrick.tinystorage.client.gui.inventory.implementations.GuiWoolChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerDraw;
import com.timthebrick.tinystorage.inventory.implementations.ContainerFilterChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerMicroChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerPeacefulChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerTinyChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerTrashChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerVacuumChest;
import com.timthebrick.tinystorage.inventory.implementations.ContainerWoolChest;
import com.timthebrick.tinystorage.reference.GUIs;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityDraw;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityMicroChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityPeacefulChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityTinyChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityTrashChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityVacuumChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityWoolChest;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()) {
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest) world.getTileEntity(x, y, z);
			return new ContainerTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		} else if (id == GUIs.FILTER_CHEST.ordinal()) {
			TileEntityFilterChest tileEntityFilterChest = (TileEntityFilterChest) world.getTileEntity(x, y, z);
			return new ContainerFilterChest(entityPlayer.inventory, tileEntityFilterChest);
		} else if (id == GUIs.DRAW.ordinal()) {
			TileEntityDraw tileEntityDraw = (TileEntityDraw) world.getTileEntity(x, y, z);
			return new ContainerDraw(entityPlayer.inventory, tileEntityDraw);
		} else if (id == GUIs.TRASH_CHEST.ordinal()) {
			TileEntityTrashChest tileEntityTrashChest = (TileEntityTrashChest) world.getTileEntity(x, y, z);
			return new ContainerTrashChest(entityPlayer.inventory, tileEntityTrashChest);
		} else if (id == GUIs.MICRO_CHEST.ordinal()) {
			TileEntityMicroChest tileEntityMicroChest = (TileEntityMicroChest) world.getTileEntity(x, y, z);
			return new ContainerMicroChest(entityPlayer.inventory, tileEntityMicroChest);
		} else if (id == GUIs.WOOL_CHEST.ordinal()) {
			TileEntityWoolChest tileEntityWoolChest = (TileEntityWoolChest) world.getTileEntity(x, y, z);
			return new ContainerWoolChest(entityPlayer.inventory, tileEntityWoolChest);
		} else if (id == GUIs.PEACEFUL_CHEST.ordinal()) {
			TileEntityPeacefulChest tileEntityPeacefulChest = (TileEntityPeacefulChest) world.getTileEntity(x, y, z);
			return new ContainerPeacefulChest(entityPlayer.inventory, tileEntityPeacefulChest);
		} else if (id == GUIs.VACUUM_CHEST.ordinal()) {
			TileEntityVacuumChest tileEntityVacuumChest = (TileEntityVacuumChest) world.getTileEntity(x, y, z);
			return new ContainerVacuumChest(entityPlayer.inventory, tileEntityVacuumChest);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		if (id == GUIs.TINY_CHEST.ordinal()) {
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest) world.getTileEntity(x, y, z);
			return new GuiTinyChest(entityPlayer.inventory, tileEntityTinyChest);
		} else if (id == GUIs.FILTER_CHEST.ordinal()) {
			TileEntityFilterChest tileEntityFilterChest = (TileEntityFilterChest) world.getTileEntity(x, y, z);
			return new GuiFilterChest(entityPlayer.inventory, tileEntityFilterChest);
		} else if (id == GUIs.DRAW.ordinal()) {
			TileEntityDraw tileEntityDraw = (TileEntityDraw) world.getTileEntity(x, y, z);
			return new GuiDraw(entityPlayer.inventory, tileEntityDraw);
		} else if (id == GUIs.TRASH_CHEST.ordinal()) {
			TileEntityTrashChest tileEntityTrashChest = (TileEntityTrashChest) world.getTileEntity(x, y, z);
			return new GuiTrashChest(entityPlayer.inventory, tileEntityTrashChest);
		} else if (id == GUIs.MICRO_CHEST.ordinal()) {
			TileEntityMicroChest tileEntityMicroChest = (TileEntityMicroChest) world.getTileEntity(x, y, z);
			return new GuiMicroChest(entityPlayer.inventory, tileEntityMicroChest);
		} else if (id == GUIs.WOOL_CHEST.ordinal()) {
			TileEntityWoolChest tileEntityWoolChest = (TileEntityWoolChest) world.getTileEntity(x, y, z);
			return new GuiWoolChest(entityPlayer.inventory, tileEntityWoolChest);
		} else if (id == GUIs.PEACEFUL_CHEST.ordinal()) {
			TileEntityPeacefulChest tileEntityPeacefulChest = (TileEntityPeacefulChest) world.getTileEntity(x, y, z);
			return new GuiPeacefulChest(entityPlayer.inventory, tileEntityPeacefulChest);
		} else if (id == GUIs.VACUUM_CHEST.ordinal()) {
			TileEntityVacuumChest tileEntityVauumChest = (TileEntityVacuumChest) world.getTileEntity(x, y, z);
			return new GuiVacuumChest(entityPlayer.inventory, tileEntityVauumChest);
		}
		return null;
	}

}

package com.timthebrick.tinystorage.client.gui.inventory;

import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.inventory.ContainerTrashChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTrashChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GuiTrashChest extends GuiContainer {

	public GuiTrashChest(InventoryPlayer inventoryPlayer, TileEntityTrashChest tileEntity) {
		super(new ContainerTrashChest(inventoryPlayer, tileEntity));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

	}

}

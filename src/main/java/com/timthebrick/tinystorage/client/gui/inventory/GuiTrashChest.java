package com.timthebrick.tinystorage.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.inventory.ContainerTrashChest;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTrashChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiTrashChest extends GuiTinyStorage {
	
	private TileEntityTrashChest tileEntity;

	public GuiTrashChest(InventoryPlayer inventoryPlayer, TileEntityTrashChest tileEntity) {
		super(new ContainerTrashChest(inventoryPlayer, tileEntity), tileEntity);
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 3, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, 4210752);
		drawFG();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiTrashChest.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawBG();
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawFG() {
		super.drawFG();
	}
	
	@Override
	public void drawBG() {
		super.drawBG();
	}

}

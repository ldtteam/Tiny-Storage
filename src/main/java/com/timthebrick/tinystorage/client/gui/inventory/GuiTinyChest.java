package com.timthebrick.tinystorage.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

public class GuiTinyChest extends GuiContainer {

	private TileEntityTinyChest tileEntity;

	public GuiTinyChest(InventoryPlayer inventoryPlayer, TileEntityTinyChest tileEntity) {
		super(new ContainerTinyChest(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiTinyChest.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}

}

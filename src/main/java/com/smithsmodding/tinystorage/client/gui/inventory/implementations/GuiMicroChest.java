package com.smithsmodding.tinystorage.client.gui.inventory.implementations;

import com.smithsmodding.tinystorage.util.client.Colours;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.smithsmodding.tinystorage.client.gui.GuiTinyStorage;
import com.smithsmodding.tinystorage.common.inventory.implementations.ContainerMicroChest;
import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityMicroChest;

public class GuiMicroChest extends GuiTinyStorage {
	
	private TileEntityMicroChest tileEntity;

	public GuiMicroChest(InventoryPlayer inventoryPlayer, TileEntityMicroChest tileEntity) {
		super(new ContainerMicroChest(inventoryPlayer, tileEntity), tileEntity);
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		if (tileEntity.hasOwner()) {
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + tileEntity.getOwner(), 8, 6, Colours.INV_GRAY);
		} else {
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 6, Colours.INV_GRAY);
		}
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
		drawFG(0, 0, x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiTrashChest.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawBG(0, 0, x, y);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawFG(int ox, int oy, int x, int y) {
		super.drawFG(ox, oy, x, y);
	}
	
	@Override
	public void drawBG(int ox, int oy, int x, int y) {
		super.drawBG(ox, oy, x, y);
	}

}
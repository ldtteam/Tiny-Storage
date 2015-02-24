package com.timthebrick.tinystorage.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.inventory.ContainerMicroChest;
import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.inventory.ContainerTrashChest;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.TileEntityMicroChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTrashChest;

public class GuiMicroChest extends GuiContainer {
	
	private TileEntityMicroChest tileEntity;

	public GuiMicroChest(InventoryPlayer inventoryPlayer, TileEntityMicroChest tileEntity) {
		super(new ContainerMicroChest(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		if (tileEntity.hasOwner()) {
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + tileEntity.getOwner(), 8, 6, 4210752);
		} else {
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 6, 4210752);
		}
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiTrashChest.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

}

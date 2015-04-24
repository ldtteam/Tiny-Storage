package com.timthebrick.tinystorage.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.inventory.ContainerPeacefulChest;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.TileEntityPeacefulChest;

public class GuiPeacefulChest extends GuiContainer {

	private TileEntityPeacefulChest tileEntity;
	
	public GuiPeacefulChest(InventoryPlayer inventoryPlayer, TileEntityPeacefulChest tileEntity){
		super(new ContainerPeacefulChest(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
		if (this.tileEntity.getState() == 0) {
			xSize = 176;
			ySize = 133;
		} else if (this.tileEntity.getState() == 1) {
			xSize = 176;
			ySize = 151;
		} else if (this.tileEntity.getState() == 2) {
			xSize = 176;
			ySize = 169;
		}
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
		if (tileEntity.getState() == 0) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPeacefulChestSmall.png"));
		} else if (tileEntity.getState() == 1) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPeacefulChestMedium.png"));
		} else if (tileEntity.getState() == 2) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPeacefulChestLarge.png"));
		}
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	@Override
	public void initGui() {
		super.initGui();
	}

}

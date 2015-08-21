package com.timthebrick.tinystorage.client.gui.inventory.implementations;

import com.timthebrick.tinystorage.util.client.Colours;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyStorage;
import com.timthebrick.tinystorage.common.inventory.implementations.ContainerFilterChest;
import com.timthebrick.tinystorage.common.inventory.slot.SlotRestrictedInput;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityFilterChest;

public class GuiFilterChest extends GuiTinyStorage {

	private TileEntityFilterChest tileEntity;

	public GuiFilterChest(InventoryPlayer inventoryPlayer, TileEntityFilterChest tileEntity) {
		super(new ContainerFilterChest(inventoryPlayer, tileEntity), tileEntity);
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
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + tileEntity.getOwner(), 8, 6, Colours.INV_GRAY);
		} else {
			fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 6, Colours.INV_GRAY);
		}
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
		if (tileEntity.getState() == 0) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestSmall.png"));
		} else if (tileEntity.getState() == 1) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestMedium.png"));
		} else if (tileEntity.getState() == 2) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestLarge.png"));
		}
		for(int i = 0; i < inventorySlots.inventorySlots.size(); i++){
			Slot slot = i < 0 ? null : (Slot) this.inventorySlots.inventorySlots.get(i);
			if(slot instanceof SlotRestrictedInput){
				if(((SlotRestrictedInput)slot).containsInvalidStack()){
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glColor4d(184, 84, 84, 255);
					drawTexturedModalRect(slot.xDisplayPosition, slot.yDisplayPosition, 176, 0, 16, 16);
				}
			}
		}
		drawFG(0, 0, x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (tileEntity.getState() == 0) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestSmall.png"));
		} else if (tileEntity.getState() == 1) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestMedium.png"));
		} else if (tileEntity.getState() == 2) {
			this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFilterChestLarge.png"));
		}
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

	@Override
	public void initGui() {
		super.initGui();
	}

}

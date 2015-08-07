package com.timthebrick.tinystorage.client.gui.inventory.implementations;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.GuiSlot;
import com.timthebrick.tinystorage.client.gui.widgets.IGuiWidget;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.reference.Colours;
import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.common.inventory.implementations.ContainerDraw;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityDraw;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiDraw extends GuiTinyStorage {

    //61, 11
    //61, 33
    //61, 55

    private TileEntityDraw tileEntity;

    public GuiDraw(InventoryPlayer inventoryPlayer, TileEntityDraw tileEntity) {
        super(new ContainerDraw(inventoryPlayer, tileEntity), tileEntity);
        this.tileEntity = tileEntity;
    }

    @Override
    public void addWidgets() {
        TinyStorageLog.info(tileEntity.rowOpened);
        for (int i = 0; i < 3; i++) {
            TinyStorageLog.info(i);
            if (tileEntity.rowOpened == 0) {
                GuiSlot slot = new GuiSlot(this, 61 + i * 18, 11);
                this.addWidget(slot);
            } else if (tileEntity.rowOpened == 1) {
                GuiSlot slot = new GuiSlot(this, 61 + i * 18, 33);
                this.addWidget(slot);
            } else if (tileEntity.rowOpened == 2) {
                GuiSlot slot = new GuiSlot(this, 61 + i * 18, 55);
                this.addWidget(slot);
            }
        }
        super.addWidgets();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 3, Colours.INV_GRAY);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
        drawFG(0, 0, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiDraw.png"));
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
        handleWidgetVisibility();
        super.drawBG(ox, oy, x, y);
    }

    @Override
    public void handleWidgetVisibility() {
        for (IGuiWidget widget : this.widgets) {
            if (widget instanceof GuiSlot) {
                widget.setVisibility(true);
            }
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addWidgets();
    }
}

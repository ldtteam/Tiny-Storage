package com.timthebrick.tinystorage.client.gui.inventory.implementations;

import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.IGuiWidgetAdvanced;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.reference.Colours;
import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.common.inventory.implementations.ContainerDraw;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityDraw;

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
        super.addWidgets();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        if (tileEntity.hasOwner()) {
            fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + tileEntity.getOwner(), 8, 3, Colours.INV_GRAY);
        } else {
            fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 3, Colours.INV_GRAY);
        }
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
        drawFG(0, 0, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.tileEntity.rowOpened == 0) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiDraw_1.png"));
        } else if (this.tileEntity.rowOpened == 1) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiDraw_2.png"));
        } else if (this.tileEntity.rowOpened == 2) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiDraw_3.png"));
        } else {
            TinyStorageLog.error("There was no row opened in the Tile Entity - this is probably a bug!");
            this.mc.thePlayer.closeScreen();
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
        handleWidgetVisibility();
        super.drawBG(ox, oy, x, y);
    }

    @Override
    public void handleWidgetVisibility() {
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addWidgets();
    }
}

package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.References;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiScrollBar extends Gui implements IGuiWidget{

    /**
     * The height of the scroll bar (max distance scrolled)
     */
    public int scrollHeight;
    /**
     * True if this control is enabled, false to disable.
     */
    public boolean enabled;
    /**
     * Hides the control completely if false.
     */
    public boolean visible;
    /**
     * The current scroll position of the bar
     */
    public int currScroll;
    /**
     * The X Position of the widget
     */
    public int xPosition;

    /**
     * The Y Position of the widget
     */
    public int yPosition;

    /**
     * @param x The X Position of  the scroll bar
     * @param y The Y Position of the scroll bar
     * @param scrollHeight The max scrollable distance
     */
    public GuiScrollBar(int x, int y, int scrollHeight) {
        this.scrollHeight = scrollHeight;
    }

    public void setVisibility(boolean vis) {
        this.visible = vis;
        this.enabled = vis;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        int xStart = (guiScreen.width - xScreenSize) / 2;
        int yStart = (guiScreen.height - yScreenSize) / 2;
        //TinyStorageLog.info(xPosition);
        this.drawTexturedModalRect(xPosition, 0, 0, 0, getWidth(), getHeight());
    }

    @Override
    public int xPos() {
        return this.xPosition;
    }

    @Override
    public int yPos() {
        return this.yPosition;
    }

    @Override
    public int getWidth() {
        return 12;
    }

    @Override
    public int getHeight() {
        return 15;
    }

    public int getScrollProgress() {
        return (currScroll / scrollHeight) * 100;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}

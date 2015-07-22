package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.util.IGuiScreen;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiScrollBar extends Gui implements IGuiWidget {

    /**
     * The current height the bar is scrolled to
     */
    protected int scrollPos;
    /**
     * The height of the scroll bar (max distance scrolled)
     */
    protected int scrollMax;
    /**
     * True if this control is enabled, false to disable.
     */
    protected boolean enabled;
    /**
     * Hides the control completely if false.
     */
    protected boolean visible;
    /**
     * The X Position of the widget
     */
    public int xPosition;
    /**
     * The Y Position of the widget
     */
    public int yPosition;
    /**
     * The X Origin of the widget
     */
    protected int xOrigin;
    /**
     * The Y Origin of the widget
     */
    protected int yOrigin;

    protected Rectangle wholeArea;
    protected IGuiScreen gui;
    protected boolean pressedUp;
    protected boolean pressedDown;
    protected boolean pressedThumb;
    protected int scrollDir;

    /**
     * @param x            The X Position of  the scroll bar
     * @param y            The Y Position of the scroll bar
     * @param scrollHeight The max scrollable distance
     */
    public GuiScrollBar(IGuiScreen gui, int x, int y, int scrollHeight) {
        this.scrollMax = scrollHeight;
        this.gui = gui;
        this.xOrigin = x;
        this.yOrigin = y;
        setScrollMax(scrollHeight);
    }

    public void adjustPosition() {
        xPosition = xOrigin + gui.getGuiLeft();
        yPosition = yOrigin + gui.getGuiTop();
        TinyStorageLog.info(gui.getGuiLeft() + ", " + gui.getGuiTop());
        wholeArea = new Rectangle(xOrigin + gui.getXSize(), yPosition, getWidth(), getScrollMax());
    }

    public int getScrollPos() {
        return scrollPos;
    }

    public void setScrollPos(int scrollPos) {
        this.scrollPos = limitPos(scrollPos);
    }

    public void scrollBy(int amount) {
        setScrollPos(scrollPos + amount);
    }

    public int getScrollMax() {
        return scrollMax;
    }

    public void setScrollMax(int scrollMax) {
        this.scrollMax = scrollMax;
        setScrollPos(scrollPos);
    }

    public void setVisibility(boolean vis) {
        this.visible = vis;
        this.enabled = vis;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        this.drawTexturedModalRect(xPosition, yPosition + getScrollPos(), 0, 0, getWidth(), getHeight());
    }

    public boolean mouseClicked(int x, int y, int button) {
        return isDragActive();
    }

    public boolean mouseClickMove(int x, int y, int button, long time) {
        return false;
    }

    public void mouseMovedOrUp(int x, int y, int button) {
        pressedUp = false;
        pressedDown = false;
        pressedThumb = false;
        scrollDir = 0;
    }

    public void mouseWheel(int x, int y, int delta) {
        if (!isDragActive() && wholeArea.contains(x, y)) {
            scrollBy(-Integer.signum(delta));
        }
    }

    public boolean isDragActive() {
        return false;
    }

    protected int limitPos(int pos) {
        return Math.max(0, Math.min(pos, scrollMax));
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

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}

package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.util.IGuiScreen;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
     * The position to scroll to when clicked
     */
    protected int scrollToPos;
    /**
     * Whether or not the scroll bar should scroll
     */
    protected boolean shouldScroll;
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
    /**
     * The area within which the scroll bar can be used
     */
    protected Rectangle wholeArea;
    /**
     * The GuiScreen class that the widget is in
     */
    protected IGuiScreen gui;
    protected boolean pressedUp;
    protected boolean pressedDown;
    protected boolean pressedThumb;
    /**
     * The direction to scroll the bar
     */
    protected int scrollDir;

    /**
     * @param x            The X Position of  the scroll bar (relative to GUI)
     * @param y            The Y Position of the scroll bar (relative to GUI)
     * @param scrollHeight The max scrollable distance (height of background - this gets adjusted)
     */
    public GuiScrollBar(IGuiScreen gui, int x, int y, int scrollHeight) {
        this.gui = gui;
        this.xOrigin = x;
        this.yOrigin = y;
        setEnabled(true);
        setScrollMax(scrollHeight - getHeight());
    }

    @Override
    public void adjustPosition() {
        TinyStorageLog.info("Adjust position");
        xPosition = xOrigin + gui.getGuiLeft();
        yPosition = yOrigin + gui.getGuiTop();
        wholeArea = new Rectangle(xPosition, yPosition, getWidth(), getScrollMax());
    }

    public void setScrollPos(int scrollPos) {
        this.scrollPos = limitPos(scrollPos);
    }

    public void scrollBy(int amount) {
        setScrollPos(scrollPos + amount);
    }

    public void setScrollMax(int scrollMax) {
        this.scrollMax = scrollMax;
    }

    public void scrollTo(int scrollPos) {
        scrollToPos = Math.max(0, Math.min(scrollPos, scrollMax));
    }

    protected int limitPos(int pos) {
        return Math.max(0, Math.min(pos, scrollMax));
    }

    public int getScrollMax() {
        return scrollMax;
    }

    public int getScrollPos() {
        return scrollPos;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setVisibility(boolean vis) {
        this.visible = vis;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        if (scrollToPos > 0 && this.isEnabled() && shouldScroll) {
            if (scrollToPos != getScrollPos()) {
                if (getScrollPos() > scrollToPos) {
                    scrollBy(-1);
                } else {
                    scrollBy(1);
                }
            } else {
                shouldScroll = false;
                scrollToPos = 0;
            }
        }
        if (this.isEnabled()) {
            this.drawTexturedModalRect(xOrigin, yOrigin + getScrollPos(), 0, 0, getWidth(), getHeight());
        } else {
            this.drawTexturedModalRect(xOrigin, yOrigin + getScrollPos(), getWidth(), 0, getWidth(), getHeight());
        }
    }

    @Override
    public boolean mouseClicked(int x, int y, int button) {
        if (this.isEnabled() && wholeArea.contains(x, y)) {
            shouldScroll = true;
            scrollTo(y - gui.getGuiTop() - yOrigin);
        }
        return false;
    }

    @Override
    public boolean mouseClickMove(int x, int y, int button, long time) {
        return false;
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
        pressedUp = false;
        pressedDown = false;
        pressedThumb = false;
        scrollDir = 0;
    }

    @Override
    public void mouseWheel(int x, int y, int delta) {
        if (this.isEnabled() && wholeArea.contains(x, y)) {
            scrollBy(-Integer.signum(delta));
        }
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
    public int getXOrigin() {
        return xOrigin;
    }

    @Override
    public int getYOrigin() {
        return yOrigin;
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

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

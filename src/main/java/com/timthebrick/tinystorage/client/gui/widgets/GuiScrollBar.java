package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.util.math.MathHelper;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiScrollBar extends Gui implements IGuiWidgetAdvanced {

    /**
     * The current height the bar is scrolled to
     */
    private int scrollPos;
    /**
     * The height of the scroll bar (max distance scrolled)
     */
    private int scrollMax;
    /**
     * The position to scroll to when clicked
     */
    private int scrollToPos;
    /**
     * Whether or not the scroll bar should scroll
     */
    private boolean shouldScroll;
    /**
     * True if this control is enabled, false to disable.
     */
    private boolean enabled;
    /**
     * Hides the control completely if false.
     */
    private boolean visible;
    /**
     * The X Position of the widget
     */
    private int xPosition;
    /**
     * The Y Position of the widget
     */
    private int yPosition;
    /**
     * The X Origin of the widget
     */
    private int xOrigin;
    /**
     * The Y Origin of the widget
     */
    private int yOrigin;
    /**
     * The area within which the scroll bar can be used
     */
    private Rectangle scrollArea;
    /**
     * The area that the entire inventory is within
     */
    private Rectangle containerArea;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    private IWidgetProvider widgetProvider;
    /**
     * The direction to scroll the bar
     */
    protected int scrollDir;
    /**
     * The amount of pixels to move the bar on each update
     */
    private final int SCROLL_AMOUNT = 1;

    /**
     * @param widgetProvider The provider that adds this object to it
     * @param x              The X Position of  the scroll bar (relative to GUI)
     * @param y              The Y Position of the scroll bar (relative to GUI)
     * @param scrollHeight   The max scrollable distance (height of background - this gets adjusted)
     */
    public GuiScrollBar(IWidgetProvider widgetProvider, int x, int y, int scrollHeight) {
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        setEnabled(true);
        setScrollMax(scrollHeight - getHeight());
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
        scrollArea = new Rectangle(xPosition, yPosition, getWidth(), getScrollMax() + getHeight());
        containerArea = new Rectangle(widgetProvider.getInvLeft() + widgetProvider.getGuiLeft(), widgetProvider.getInvTop() + widgetProvider.getGuiTop(), widgetProvider.getInvWidth(), widgetProvider.getInvHeight());
    }

    public void scrollBy(int amount) {
        setScrollPos(scrollPos + amount);
    }

    private void scrollByNoUpdate(int amount) {
        setScrollPosNoUpdate(scrollPos + amount);
    }

    private void setScrollMax(int scrollMax) {
        this.scrollMax = scrollMax;
    }

    private void scrollTo(int scrollPos) {
        scrollToPos = Math.max(0, Math.min(scrollPos, scrollMax));
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setVisibility(boolean vis) {
        this.visible = vis;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        if (this.isEnabled()) {
            this.drawTexturedModalRect(xOrigin, yOrigin + getScrollPos(), 0, 0, getWidth(), getHeight());
        } else {
            this.drawTexturedModalRect(xOrigin, yOrigin + getScrollPos(), getWidth(), 0, getWidth(), getHeight());
        }
    }

    @Override
    public void updateWidget() {
        if (scrollToPos >= 0 && this.isEnabled() && shouldScroll) {
            if (scrollToPos != getScrollPos()) {
                if (getScrollPos() > scrollToPos) {
                    scrollByNoUpdate(-SCROLL_AMOUNT);
                } else {
                    scrollByNoUpdate(SCROLL_AMOUNT);
                }
                if (getScrollPos() <= scrollToPos + SCROLL_AMOUNT && getScrollPos() >= scrollToPos - SCROLL_AMOUNT) {
                    setScrollPos(scrollToPos);
                }
            } else {
                shouldScroll = false;
                scrollToPos = 0;
            }
        }
    }

    @Override
    public boolean onMouseClick(int x, int y, int button) {
        if (this.isEnabled() && this.shouldScroll && containerArea.contains(x, y)) {
            notifyOfChange();
            scrollTo(MathHelper.roundToNearestInterval(getScrollPos(), 4));
            notifyOfChange();
        } else if (this.isEnabled() && scrollArea.contains(x, y)) {
            notifyOfChange();
            shouldScroll = true;
            scrollTo(MathHelper.roundToNearestInterval(y - widgetProvider.getGuiTop() - yOrigin, 4));
        }
        return false;
    }

    @Override
    public boolean mouseClickMove(int x, int y, int button, long time) {
        return false;
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
        scrollDir = 0;
    }

    @Override
    public void mouseWheel(int x, int y, int delta) {
        if (this.isEnabled() && scrollArea.contains(x, y) && !shouldScroll) {
            notifyOfChange();
            scrollTo(getScrollPos() + ((-Integer.signum(delta) * 4)));
            shouldScroll = true;
        }
    }

    @Override
    public void keyTyped(char c, int key) {
    }

    @Override
    public Rectangle getWidgetAreaAbsolute() {
        return new Rectangle(xPos(), yPos(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getWidgetAreaRelative() {
        return new Rectangle(getXOrigin(), getYOrigin(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getWidgetVisibleAreaAbsolute() {
        return new Rectangle(xPos(), yPos(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getWidgetVisibleAreaRelative() {
        return new Rectangle(getXOrigin(), getYOrigin(), getWidth(), getHeight());
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
        return 14;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private int getScrollMax() {
        return scrollMax;
    }

    public int getScrollPos() {
        return scrollPos;
    }

    private void setScrollPosNoUpdate(int scrollPos) {
        this.scrollPos = Math.max(0, Math.min(scrollPos, scrollMax));
    }

    private void setScrollPos(int scrollPos) {
        this.scrollPos = Math.max(0, Math.min(scrollPos, scrollMax));
        this.widgetProvider.handleWidgetFunctionality(this);
    }

    private void notifyOfChange() {
        this.widgetProvider.handleWidgetFunctionality(this);
    }

    @Override
    public String toString() {
        return "GuiScrollBar | xOrigin: " + xOrigin + " | yOrigin: " + yOrigin + " | xPosition: " + xPosition + " | yPosition: " + yPosition + " | scrollMax: " + getScrollMax() + " | scrollPos: " + getScrollPos() + " | Visible: " + isVisible() + " | Enabled: " + isEnabled();
    }
}

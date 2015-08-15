package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiTabbedPane extends Gui implements IGuiWidgetAdvanced, IWidgetTooltip {
    /**
     * True if this control is enabled, false to disable.
     */
    private boolean enabled;
    /**
     * Hides the control completely if false.
     */
    private boolean visible;
    /**
     * Whether the area has been expanded or not
     */
    private boolean expanded;
    /**
     * Whether or not the pane should be moving
     */
    private boolean shouldAnimate;
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
     * The width of the pane
     */
    protected int widthPane;
    /**
     * The height of the pane
     */
    protected int heightPane;
    /**
     * The width of the 'button' to show
     */
    protected int widthButton;
    /**
     * The height of the 'button' to show
     */
    protected int heightButton;
    /**
     * The area to click on to expand/retract the pane
     */
    private Rectangle expandButton;
    /**
     * The X start position for the background texture
     */
    protected int backgroundTextureX;
    /**
     * The Y start position for the background texture
     */
    protected int backgroundTextureY;
    /**
     * The X start position for the foreground texture
     */
    protected int buttonTextureX;
    /**
     * The Y start position for the foreground texture
     */
    protected int buttonTextureY;
    /**
     * Used to determine how much of the tab is shown, and how much is left to go
     */
    private float progressX, progressY;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    private IWidgetProvider widgetProvider;

    /**
     * @param widgetProvider    The provider that adds this object to it
     * @param x                 The X Position of  the scroll bar (relative to GUI)
     * @param y                 The Y Position of the scroll bar (relative to GUI)
     * @param width             The width of the entire tab
     * @param height            The height of the entire tab
     * @param buttonWidth       The width of the display button
     * @param buttonHeight      The height of the display button
     * @param backgroundY       The Y Position of the background texture for the tab
     * @param buttonBackgroundX The X Position of the background texture for the button
     * @param buttonBackgroundY The Y Position of the background texture for the button
     * @@param backgroundX     The X Position of the background texture for the tab
     */
    public GuiTabbedPane(IWidgetProvider widgetProvider, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int backgroundX, int backgroundY, int buttonBackgroundX, int buttonBackgroundY) {
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        this.widthPane = width;
        this.heightPane = height;
        this.widthButton = buttonWidth;
        this.heightButton = buttonHeight;
        this.backgroundTextureX = backgroundX;
        this.backgroundTextureY = backgroundY;
        this.buttonTextureX = buttonBackgroundX;
        this.buttonTextureY = buttonBackgroundY;
        setEnabled(true);
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
        expandButton = new Rectangle(xPosition, yPosition, getButtonWidth(), getButtonHeight());
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        if (this.isEnabled()) {
            this.drawTexturedModalRect(xOrigin, yOrigin, buttonTextureX, buttonTextureY, getButtonWidth(), getButtonHeight());
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPanes.png"));
            if (shouldAnimate) {
                if (expanded) {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) (getWidth() - progressX), (int) (getHeight() - progressX));
                } else {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) progressX, (int) progressY);
                }
            } else if (expanded) {
                this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, getWidth(), getHeight());
            }
        } else {
            this.drawTexturedModalRect(xOrigin, yOrigin, buttonTextureX + getButtonWidth(), buttonTextureY, getButtonWidth(), getButtonHeight());
        }
    }

    @Override
    public void updateWidget() {
        if (this.shouldAnimate && this.isEnabled()) {
            int xAdj, yAdj;
            if (getWidth() < getHeight()) {
                TinyStorageLog.info((float) getHeight() / getWidth());
                xAdj = getHeight() / getWidth();
                yAdj = 1;
            } else {
                TinyStorageLog.info((float) getWidth() / getHeight());
                xAdj = 1;
                yAdj = getWidth() / getHeight();
            }

            if (progressX < getWidth()) {
                progressX += xAdj;
            }
            if (progressY < getHeight()) {
                progressY += yAdj;
            }
            if (progressX >= getWidth() && progressY >= getHeight()) {
                progressX = 0;
                progressY = 0;
                expanded = !expanded;
                shouldAnimate = false;
            }
        }
    }

    @Override
    public boolean mouseClicked(int xPos, int yPos, int btn) {
        if (this.isEnabled() && expandButton.contains(xPos, yPos) && shouldAnimate == false) {
            shouldAnimate = true;
        }
        return false;
    }

    @Override
    public boolean mouseClickMove(int x, int y, int button, long time) {
        return false;
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
    }

    @Override
    public void mouseWheel(int x, int y, int delta) {
    }

    @Override
    public int xPos() {
        return xPosition;
    }

    @Override
    public int yPos() {
        return yPosition;
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
        return widthPane;
    }

    @Override
    public int getHeight() {
        return heightPane;
    }

    public int getButtonWidth() {
        return widthButton;
    }

    public int getButtonHeight() {
        return heightButton;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setVisibility(boolean vis) {
        this.visible = vis;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getMessage() {
        return StatCollector.translateToLocal(Messages.WidgetTooltips.FRIENDS_LIST);
    }

    @Override
    public int xTriggerPos() {
        return xPos();
    }

    @Override
    public int yTriggerPos() {
        return yPos();
    }

    @Override
    public int getTooltipWidth() {
        return getButtonWidth();
    }

    @Override
    public int getTooltipHeight() {
        return getButtonHeight();
    }

    @Override
    public boolean isTooltipVisible() {
        return true;
    }
}
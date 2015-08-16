package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

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
     * A list of widgets contained by this panel
     */
    private ArrayList<IGuiWidgetAdvanced> containedWidgets = new ArrayList<IGuiWidgetAdvanced>();

    /**
     * @param widgetProvider    The provider that adds this object to it
     * @param x                 The X Position of  the scroll bar (relative to GUI)
     * @param y                 The Y Position of the scroll bar (relative to GUI)
     * @param width             The width of the entire tab
     * @param height            The height of the entire tab
     * @param buttonWidth       The width of the display button
     * @param buttonHeight      The height of the display button
     * @param backgroundX       The X Position of the background texture for the tab
     * @param backgroundY       The Y Position of the background texture for the tab
     * @param buttonBackgroundX The X Position of the background texture for the button
     * @param buttonBackgroundY The Y Position of the background texture for the button
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

    public void addContainedWidget(IGuiWidgetAdvanced widget) {
        containedWidgets.add(widget);
        widget.adjustPosition();
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
        expandButton = new Rectangle(xPosition, yPosition, getButtonWidth(), getButtonHeight());
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.adjustPosition();
        }
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.isEnabled()) {
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPanes.png"));
            this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, getButtonWidth(), getButtonHeight());
            if (shouldAnimate) {
                for (IGuiWidgetAdvanced widget : containedWidgets) {
                    widget.setVisibility(false);
                }
                if (expanded) {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
                } else {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) Math.ceil(progressX), (int) Math.ceil(progressY));
                }
            } else if (expanded) {
                for (IGuiWidgetAdvanced widget : containedWidgets) {
                    widget.setVisibility(true);
                }
                this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, getWidth(), getHeight());
            }
        } else {
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
            this.drawTexturedModalRect(xOrigin, yOrigin, buttonTextureX + getButtonWidth(), buttonTextureY, getButtonWidth(), getButtonHeight());
        }
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.drawWidget(guiScreen, xScreenSize, yScreenSize);
        }
    }

    @Override
    public void updateWidget() {
        if (this.shouldAnimate && this.isEnabled()) {
            float xAdj, yAdj;
            float multiplier = 1.5f;

            if (getWidth() < getHeight()) {
                xAdj = 1;
                yAdj = (float) getHeight() / getWidth();
            } else {
                xAdj = (float) (getWidth() / getHeight());
                yAdj = 1;
            }

            if (progressX < getWidth()) {
                progressX += (xAdj * multiplier);
            }
            if (progressY < getHeight()) {
                progressY += (yAdj * multiplier);
            }
            if (progressX >= getWidth() && progressY >= getHeight()) {
                progressX = 0;
                progressY = 0;
                expanded = !expanded;
                shouldAnimate = false;
            }

            for (IGuiWidgetAdvanced widget : containedWidgets) {
                widget.updateWidget();
            }
        }
    }

    @Override
    public boolean onMouseClick(int xPos, int yPos, int btn) {
        if (this.isEnabled() && expandButton.contains(xPos, yPos) && !shouldAnimate) {
            shouldAnimate = true;
        }
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.onMouseClick(xPos, yPos, btn);
        }
        return false;
    }

    @Override
    public boolean mouseClickMove(int x, int y, int button, long time) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseClickMove(x, y, button, time);
        }
        return false;
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseMovedOrUp(x, y, button);
        }
    }

    @Override
    public void mouseWheel(int x, int y, int delta) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseWheel(x, y, delta);
        }
    }

    @Override
    public void keyTyped(char c, int key) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.keyTyped(c, key);
        }
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
        if (shouldAnimate) {
            if (expanded) {
                return new Rectangle(xPos(), yPos(), (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
            } else {
                return new Rectangle(xPos(), yPos(), (int) Math.ceil(progressX), (int) Math.ceil(progressY));
            }
        } else if (expanded) {
            return new Rectangle(xPos(), yPos(), getWidth(), getHeight());
        } else {
            return new Rectangle(xPos(), yPos(), getButtonWidth(), getButtonHeight());
        }
    }

    @Override
    public Rectangle getWidgetVisibleAreaRelative() {
        if (shouldAnimate) {
            if (expanded) {
                return new Rectangle(getXOrigin(), getYOrigin(), (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
            } else {
                return new Rectangle(getXOrigin(), getYOrigin(), (int) Math.ceil(progressX), (int) Math.ceil(progressY));
            }
        } else if (expanded) {
            return new Rectangle(getXOrigin(), getYOrigin(), getWidth(), getHeight());
        } else {
            return new Rectangle(getXOrigin(), getYOrigin(), getButtonWidth(), getButtonHeight());
        }
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

    /*
    Tool tip stuff
     */

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
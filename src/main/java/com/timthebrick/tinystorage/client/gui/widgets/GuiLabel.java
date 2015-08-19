package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.util.client.colour.Colour;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

import java.awt.Rectangle;

public class GuiLabel extends Gui implements IGuiLabel {
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
    protected int xPosition;
    /**
     * The Y Position of the widget
     */
    protected int yPosition;
    /**
     * The X Origin of the widget
     */
    private int xOrigin;
    /**
     * The Y Origin of the widget
     */
    private int yOrigin;
    /**
     * The width of the object
     */
    private int width;
    /**
     * The height of the object
     */
    private int height;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    protected IWidgetProvider widgetProvider;
    private FontRenderer renderer;
    private String message;
    private Colour colour;

    public GuiLabel(IWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, String text, Colour colour) {
        this.widgetProvider = widgetProvider;
        this.renderer = fontRenderer;
        this.xOrigin = x;
        this.yOrigin = y;
        this.width = width;
        this.height = height;
        this.message = text;
        this.colour = colour;
        setEnabled(true);
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        if(this.isEnabled() && this.isVisible()){
            String localised = StatCollector.translateToLocal(message);
            String displayString = renderer.trimStringToWidth(localised, getWidth());
            renderer.drawString(displayString, xPosition, yPosition, colour.getColour());
        }
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public boolean onMouseClick(int xPos, int yPos, int btn) {
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
    public String getMessage() {
        return message;
    }

    @Override
    public void updateWidget() {

    }

    public static class GuiLabelTabbed extends GuiLabel implements IGuiWidgetTabbed {
        /**
         * The tab that contains this text input field
         */
        private GuiTabbedPane container;

        public GuiLabelTabbed(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, String text, Colour colour) {
            super(widgetProvider, fontRenderer, x, y, width, height, text, colour);
            this.container = tab;
        }

        @Override
        public void adjustPosition() {
            xPosition = getXOrigin() + container.getXOrigin();
            yPosition = getYOrigin() + container.getYOrigin();
        }

        @Override
        public boolean onMouseClick(int xPos, int yPos, int btn) {
            super.onMouseClick(xPos - widgetProvider.getGuiLeft(), yPos - widgetProvider.getGuiTop(), btn);
            return false;
        }

        @Override
        public GuiTabbedPane getContainerTab() {
            return container;
        }
    }
}

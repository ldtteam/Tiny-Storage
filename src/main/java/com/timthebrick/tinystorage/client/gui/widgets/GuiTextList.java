package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.util.colour.Colour;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GuiTextList extends Gui implements IGuiWidgetAdvanced {

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
    private List<String> textList;
    private GuiTextInput filter;
    private int indexSelected;

    public GuiTextList(IWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text) {
        this(widgetProvider, fontRenderer, x, y, width, height, text, null);
    }

    public GuiTextList(IWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text, GuiTextInput filter) {
        this.widgetProvider = widgetProvider;
        this.renderer = fontRenderer;
        this.xOrigin = x;
        this.yOrigin = y;
        this.width = width;
        this.height = height;
        this.textList = text;
        this.filter = filter;
        Collections.sort(textList);
        setEnabled(true);
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        if (isVisible()) {
            this.drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            if (indexSelected > 0) {
                this.drawRect(xPosition, (this.yPosition) + ((indexSelected - 1) * renderer.FONT_HEIGHT), xPosition + this.width, (this.yPosition + 1) + ((indexSelected * renderer.FONT_HEIGHT)), new Colour(90, 255, 255).getColour());
            }
            int i = 0;
            for (String name : textList) {
                String dispName = renderer.trimStringToWidth(name, getWidth());
                if (filter != null) {
                    if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                        if (filter.getText().isEmpty()) {
                            if (indexSelected > 0 && indexSelected - 1 == i) {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                            } else {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                            }
                            i++;
                        } else {
                            if (name.toLowerCase().contains(filter.getText().toLowerCase())) {
                                if (indexSelected > 0 && indexSelected - 1 == i) {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                } else {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                }
                                i++;
                            }
                        }
                    }
                } else {
                    if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                        if (indexSelected > 0 && indexSelected - 1 == i) {
                            renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                        } else {
                            renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                        }
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public boolean onMouseClick(int xPos, int yPos, int btn) {
        if (textList.size() > 0 && this.isVisible() && this.isEnabled()) {
            if (getWidgetAreaAbsolute().contains(xPos, yPos)) {
                indexSelected = (int) Math.floor((yPos - yPosition) / (renderer.FONT_HEIGHT)) + 1;
            } else {
                indexSelected = 0;
            }
        }
        return false;
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
    public void updateWidget() {
    }

    public static class GuiTextListTabbed extends GuiTextList implements IGuiWidgetTabbed {
        /**
         * The tab that contains this text input field
         */
        private GuiTabbedPane container;

        public GuiTextListTabbed(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text) {
            this(widgetProvider, tab, fontRenderer, x, y, width, height, text, null);
        }

        public GuiTextListTabbed(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text, GuiTextInput filter) {
            super(widgetProvider, fontRenderer, x, y, width, height, text, filter);
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

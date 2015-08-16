package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.client.gui.widgets.settings.ICharFilter;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.lang.reflect.Field;

public class GuiTextInput extends GuiTextField implements IGuiWidgetAdvanced {

    /**
     * True if this control is enabled, false to disable.
     */
    private boolean enabled;
    /**
     * Hides the control completely if false.
     */
    private boolean visible;
    /**
     * The X Origin of the widget
     */
    private int xOrigin;
    /**
     * The Y Origin of the widget
     */
    private int yOrigin;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    private IWidgetProvider widgetProvider;
    /**
     * The filter for the specified field
     */
    private ICharFilter charFilter;
    /**
     * Whether or not the field can lose focus
     */
    private static Field canLoseFocus;

    static {
        try {
            canLoseFocus = ReflectionHelper.findField(GuiTextField.class, "canLoseFocus", "field_146212_n", "n");
            canLoseFocus.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GuiTextInput(IWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height) {
        this(widgetProvider, fontRenderer, x, y, width, height, null);
    }

    public GuiTextInput(IWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, ICharFilter filter) {
        super(fontRenderer, x, y, width, height);
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        this.charFilter = filter;
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
    }

    public GuiTextInput setCharFilter(ICharFilter filter) {
        this.charFilter = filter;
        return this;
    }

    @Override
    public boolean textboxKeyTyped(char c, int key) {
        if (charFilter == null || charFilter.passesFilter(this, c) || isSpecialChar(c, key)) {
            return super.textboxKeyTyped(c, key);
        }
        return false;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        this.drawTextBox();
    }

    @Override
    public void updateWidget() {
        this.updateCursorCounter();
    }

    @Override
    public void mouseClicked(int xPos, int yPos, int btn) {
        super.mouseClicked(xPos, yPos, btn);
    }

    @Override
    public boolean onMouseClick(int xPos, int yPos, int btn) {
        this.mouseClicked(xPos, yPos, btn);
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

    public static boolean isSpecialChar(char c, int key) {
        return c == 1 || c == 3 || c == 22 || c == 24 || key == 14 || key == 199 || key == 203 || key == 205 || key == 207 || key == 211;
    }

    public boolean getCanLoseFocus() {
        try {
            return canLoseFocus.getBoolean(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(int x, int y) {
        return x >= this.xPosition && x < this.xPosition + this.width && y >= this.yPosition && y < this.yPosition + this.height;
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
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean getVisible() {
        return isVisible();
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

    public static class GuiTextInputTabbed extends GuiTextInput implements IGuiWidgetTabbed {
        /**
         * The tab that contains this text input field
         */
        private GuiTabbedPane container;

        public GuiTextInputTabbed(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height) {
            this(widgetProvider, tab, fontRenderer, x, y, width, height, null);
        }

        public GuiTextInputTabbed(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, ICharFilter filter) {
            super(widgetProvider, fontRenderer, x, y, width, height, filter);
            this.container = tab;
        }

        @Override
        public void adjustPosition() {
            xPosition = getXOrigin() + container.getXOrigin();
            yPosition = getYOrigin() + container.getYOrigin();
        }

        @Override
        public GuiTabbedPane getContainerTab() {
            return container;
        }
    }
}

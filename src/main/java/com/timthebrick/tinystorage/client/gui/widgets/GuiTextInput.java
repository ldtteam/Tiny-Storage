package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.client.gui.widgets.settings.ICharFilter;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.lang.reflect.Field;

public class GuiTextInput extends Gui implements IGuiWidgetAdvanced {

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
     * The width of the text box
     */
    private int height;
    /**
     * The height of the text box
     */
    private int width;
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
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        this.width = width;
        this.height = height;
        this.charFilter = filter;
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
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setVisibility(boolean vis) {
        this.visible = vis;
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
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
                drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            }
            int i = this.isEnabled ? this.enabledColor : this.disabledColor;
            int j = this.cursorPosition - this.lineScrollOffset;
            int k = this.selectionEnd - this.lineScrollOffset;
            String s = this.field_146211_a.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            boolean flag = j >= 0 && j <= s.length();
            boolean flag1 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
            int l = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
            int i1 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
            int j1 = l;
            if (k > s.length()) {
                k = s.length();
            }
            if (s.length() > 0) {
                String s1 = flag ? s.substring(0, j) : s;
                j1 = this.field_146211_a.drawStringWithShadow(s1, l, i1, i);
            }
            boolean flag2 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int k1 = j1;
            if (!flag) {
                k1 = j > 0 ? l + this.width : l;
            } else if (flag2) {
                k1 = j1 - 1;
                --j1;
            }
            if (s.length() > 0 && flag && j < s.length()) {
                this.field_146211_a.drawStringWithShadow(s.substring(j), j1, i1, i);
            }
            if (flag1) {
                if (flag2) {
                    Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + this.field_146211_a.FONT_HEIGHT, -3092272);
                } else {
                    this.field_146211_a.drawStringWithShadow("_", k1, i1, i);
                }
            }
            if (k != j) {
                int l1 = l + this.field_146211_a.getStringWidth(s.substring(0, k));
                this.drawCursorVertical(k1, i1 - 1, l1 - 1, i1 + 1 + this.field_146211_a.FONT_HEIGHT);
            }
        }
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
    }

    @Override
    public void updateWidget() {
        this.updateCursorCounter();
    }
}

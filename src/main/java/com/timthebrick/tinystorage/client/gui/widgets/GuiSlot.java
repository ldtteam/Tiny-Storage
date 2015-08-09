package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSlot extends Gui implements IGuiWidgetBackground {

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
    protected int xPosition;
    /**
     * The Y Position of the widget
     */
    protected int yPosition;
    /**
     * The X Origin of the widget
     */
    protected int xOrigin;
    /**
     * The Y Origin of the widget
     */
    protected int yOrigin;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    protected IWidgetProvider widgetProvider;

    public GuiSlot(IWidgetProvider widgetProvider, int x, int y){
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        setEnabled(true);
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        this.drawTexturedModalRect(xPosition, yPosition, 0, 14, getWidth(), getHeight());
    }

    @Override
    public void updateWidget() {
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
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
        return 18;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean mouseClicked(int xPos, int yPos, int btn) {
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
}

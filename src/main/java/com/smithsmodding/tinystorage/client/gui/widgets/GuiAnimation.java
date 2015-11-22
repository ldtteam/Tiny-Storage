package com.smithsmodding.tinystorage.client.gui.widgets;

import com.smithsmodding.tinystorage.client.gui.widgets.settings.AnimationDirection;
import com.smithsmodding.tinystorage.util.client.colour.Colour;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public abstract class GuiAnimation extends Gui implements IGuiAnimation {

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
    protected int xOrigin;
    /**
     * The Y Origin of the widget
     */
    protected int yOrigin;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    protected IScreenWidgetProvider widgetProvider;
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
    protected int foregroundTextureX;
    /**
     * The Y start position for the foreground texture
     */
    protected int foregroundTextureY;
    /**
     * The width of the animation
     */
    protected int width;
    /**
     * The height of the animation
     */
    protected int height;
    /**
     * The progress made for the animation
     */
    protected int progress;
    /**
     * How often (in ticks) this animation should update
     */
    protected int updateFrequency;
    /**
     * How many ticks this object has existed for (gets reset after max progress reached)
     */
    protected int lifeCycle;
    /**
     * The direction to animate in
     */
    protected AnimationDirection direction;
    /**
     * Whether the animation has started or not
     */
    protected boolean shouldAnimate;
    /**
     * The desired colour for the animation
     */
    protected Colour colour;

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void updateWidget() {
    }

    @Override
    public void updateGraphics() {
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
        shouldAnimate = false;
    }

    @Override
    public boolean isRunning() {
        return shouldAnimate;
    }

    @Override
    public void startAnimation() {
        shouldAnimate = true;
    }

    @Override
    public void setVisibility(boolean vis) {
        this.visible = vis;
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public int backgroundTextureX() {
        return backgroundTextureX;
    }

    @Override
    public int backgroundTextureY() {
        return backgroundTextureY;
    }

    @Override
    public int foregroundTextureX() {
        return foregroundTextureX;
    }

    @Override
    public int foregroundTextureY() {
        return foregroundTextureY;
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

}

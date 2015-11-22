package com.smithsmodding.tinystorage.client.gui.widgets;

import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public interface IGuiWidgetAdvanced extends IGuiWidgetSimple{

    /**
     * x Location for the object.
     *
     * @return xPosition
     */
    int xPos();

    /**
     * y Location for the object.
     *
     * @return yPosition
     */
    int yPos();

    /**
     * Gets the gui relative x position of the widget
     *
     * @return xOrigin
     */
    int getXOrigin();

    /**
     * Gets the gui relative y position of the widget
     *
     * @return yOrigin
     */
    int getYOrigin();

    /**
     * Width of the object.
     *
     * @return width
     */
    int getWidth();

    /**
     * Height for the object.
     *
     * @return height
     */
    int getHeight();

    /**
     * @return true if widget being drawn
     */
    boolean isVisible();

    /**
     * @return true if the user can interact with the widget
     */
    boolean isEnabled();

    /**
     * Sets the visibility of the widget
     *
     * @param vis
     */
    void setVisibility(boolean vis);

    /**
     * Sets whether the control is enabled or not
     *
     * @param enabled
     */
    void setEnabled(boolean enabled);

    boolean onMouseClick(int xPos, int yPos, int btn);

    boolean mouseClickMove(int x, int y, int button, long time);

    void mouseMovedOrUp(int x, int y, int button);

    void mouseWheel(int x, int y, int delta);

    void keyTyped(char c, int key);

    void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize);

    void updateGraphics();

    void adjustPosition();

    Rectangle getWidgetAreaAbsolute();

    Rectangle getWidgetAreaRelative();

    Rectangle getWidgetVisibleAreaAbsolute();

    Rectangle getWidgetVisibleAreaRelative();

}
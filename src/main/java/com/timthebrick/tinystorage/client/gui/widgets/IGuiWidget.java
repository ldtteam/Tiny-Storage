package com.timthebrick.tinystorage.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public interface IGuiWidget {

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

    void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize);

}

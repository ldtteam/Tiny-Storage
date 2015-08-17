package com.timthebrick.tinystorage.client.gui.inventory;

import net.minecraft.client.gui.GuiScreen;

public interface IGuiScreen {

    /**
     * @return The left position of the GUI
     */
    int getGuiLeft();

    /**
     * @return The top position of the GUI
     */
    int getGuiTop();

    /**
     * @return The X size of the GUI
     */
    int getXSize();

    /**
     * @return The Y size of the GUI
     */
    int getYSize();

    GuiScreen getGuiScreen();
}

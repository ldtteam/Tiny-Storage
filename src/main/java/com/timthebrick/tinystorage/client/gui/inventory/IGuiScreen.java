package com.timthebrick.tinystorage.client.gui.inventory;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;

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

    FontRenderer getFontRenderer();

    RenderItem getItemRenderer();

    GuiScreen getGuiScreen();
}

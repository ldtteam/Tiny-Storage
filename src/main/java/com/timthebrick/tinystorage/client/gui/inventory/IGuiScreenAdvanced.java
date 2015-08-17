package com.timthebrick.tinystorage.client.gui.inventory;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;

public interface IGuiScreenAdvanced extends IGuiScreen {

    /**
     * @return The left position of the first inventory row
     */
    int getInvLeft();

    /**
     * @return The top position of the first inventory row
     */
    int getInvTop();

    /**
     * @return The width of the inventory (pixels)
     */
    int getInvWidth();

    /**
     * @return The height of the inventory (pixels)
     */
    int getInvHeight();

    FontRenderer getFontRenderer();

    RenderItem getItemRenderer();

}

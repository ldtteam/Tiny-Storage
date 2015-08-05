package com.timthebrick.tinystorage.client.gui.widgets;

import net.minecraft.client.gui.GuiScreen;

public interface IGuiAnimation extends IGuiWidget {

    int backgroundTextureX();

    int backgroundTextureY();

    int foregroundTextureX();

    int foregroundTextureY();

    void drawAnimationForeground(GuiScreen guiScreen, int xScreenSize, int yScreenSize);

    void drawAnimationBackground(GuiScreen guiScreen, int xScreenSize, int yScreenSize);

    boolean isRunning();

    void startAnimation();
}

package com.timthebrick.tinystorage.client.gui.widgets.settings;

import com.timthebrick.tinystorage.client.gui.widgets.GuiImageButton;
import com.timthebrick.tinystorage.client.gui.widgets.GuiTabbedPane;
import com.timthebrick.tinystorage.client.gui.widgets.IGuiTabHandler;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import net.minecraft.client.gui.GuiButton;

public class TabHandlerFriendsList implements IGuiTabHandler {

    public TabHandlerFriendsList() {
    }

    @Override
    public void actionPerformed(GuiTabbedPane gui, GuiButton button) {
        if (button instanceof GuiImageButton) {
            GuiImageButton imgButton = (GuiImageButton) button;
            if (imgButton.getSetting() == ButtonSettings.ADD) {
                TinyStorageLog.info("Hi there");
            }
        }
    }
}

package com.timthebrick.tinystorage.client.gui.widgets.settings;

import com.timthebrick.tinystorage.client.gui.widgets.*;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import net.minecraft.client.gui.GuiButton;

public class TabHandlerFriendsList implements IGuiTabHandler {

    public TabHandlerFriendsList() {
    }

    @Override
    public void actionPerformed(GuiTabbedPane gui, GuiButton button) {
        if (button instanceof GuiImageButton) {
            GuiImageButton imgButton = (GuiImageButton) button;
            if (imgButton.getSetting() == ButtonSettings.DOWN) {
                if (gui.getButtonList().indexOf(imgButton) == 1) {
                    for (IGuiWidgetAdvanced widget : gui.getContainedWidgets()) {
                        if (widget instanceof GuiFriendsList) {
                            int maxDisplayRow = widget.getHeight() / (((GuiFriendsList) widget).renderer.FONT_HEIGHT + 1);
                            int listSize = ((GuiFriendsList) widget).textList.size();
                            int currentDisplayIndex = ((GuiFriendsList) widget).displayIndex;
                            if (currentDisplayIndex + maxDisplayRow + 2 <= listSize) {
                                ((GuiFriendsList) widget).displayIndex++;
                            }
                        }
                    }
                }
            } else if (imgButton.getSetting() == ButtonSettings.UP) {
                if (gui.getButtonList().indexOf(imgButton) == 0) {
                    for (IGuiWidgetAdvanced widget : gui.getContainedWidgets()) {
                        if (widget instanceof GuiFriendsList) {
                            if (((GuiFriendsList) widget).displayIndex - 1 >= 0) {
                                ((GuiFriendsList) widget).displayIndex--;
                            }
                        }
                    }
                }
            }
        }
    }
}

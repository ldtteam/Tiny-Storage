package com.timthebrick.tinystorage.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.List;

public class GuiSetterFriendsList extends GuiTextList {

    public GuiSetterFriendsList(IScreenWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text) {
        this(widgetProvider, fontRenderer, x, y, width, height, text, null);
    }

    public GuiSetterFriendsList(IScreenWidgetProvider widgetProvider, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text, GuiTextInput filter) {
        super(widgetProvider, fontRenderer, x, y, width, height, text, filter);
        text.remove(Minecraft.getMinecraft().thePlayer.getDisplayName());
    }
}

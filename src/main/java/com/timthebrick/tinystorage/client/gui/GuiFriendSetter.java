package com.timthebrick.tinystorage.client.gui;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.*;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.timthebrick.tinystorage.client.gui.widgets.settings.CharFilters;
import com.timthebrick.tinystorage.client.gui.widgets.settings.EnableMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.TabHandlerFriendsList;
import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.util.client.Colours;
import com.timthebrick.tinystorage.util.client.colour.Colour;
import com.timthebrick.tinystorage.util.common.UUIDHelper;
import net.minecraft.client.gui.GuiScreen;

public class GuiFriendSetter extends GuiScreen implements IWidgetProvider {

    private GuiTabbedPane friendsPanel;
    private GuiLabel searchLabel;
    private GuiTextInput searchBar;
    private GuiFriendsList friendsList;
    private GuiImageButton next;
    private GuiImageButton prev;

    @Override
    public void drawScreen(int mouseX, int mouseY, float btn) {

    }

    @Override
    public void updateScreen() {

    }

    @Override
    public void initGui() {
        if (this.friendsPanel == null) {
            this.friendsPanel = new GuiTabbedPane(this, new TabHandlerFriendsList(), getXSize() + 2, 8, 112, 170, 12, 12, 0, 0, 24, 0);
            this.addWidget(friendsPanel);
            GuiLabel searchLabel = new GuiLabel.GuiLabelTabbed(this, friendsPanel, fontRendererObj, 2, friendsPanel.getButtonHeight() + 1, friendsPanel.getWidth() - 3, fontRendererObj.FONT_HEIGHT + 1, Messages.GuiLabels.FRIENDS_LIST, new Colour(Colours.INV_GRAY));
            friendsPanel.addContainedWidget(searchLabel);
            GuiTextInput search = new GuiTextInput.GuiTextInputTabbed(this, friendsPanel, this.fontRendererObj, 3, friendsPanel.getButtonHeight() + searchLabel.getHeight() + 1, friendsPanel.getWidth() - 6, 10, CharFilters.FILTER_ALPHANUMERIC);
            friendsPanel.addContainedWidget(search);
            GuiTextList playerList = new GuiFriendsList(this, friendsPanel, this.fontRendererObj, search.getXOrigin(), search.getYOrigin() + search.getHeight() + 4, friendsPanel.getWidth() - 15, 3 + (fontRendererObj.FONT_HEIGHT * 14), UUIDHelper.getStringFromMap(TinyStorage.instance.playerUUIDMap), search);
            friendsPanel.addContainedWidget(playerList);
            GuiImageButton prev = new GuiImageButton(playerList.xPos() + playerList.getWidth() + 2, playerList.yPos() - 1, ButtonSettings.UP, EnableMode.ENABLED, true);
            prev.visible = false;
            friendsPanel.addContainedButton(prev);
            GuiImageButton next = new GuiImageButton(playerList.xPos() + playerList.getWidth() + 2, playerList.yPos() + playerList.getHeight() - 7, ButtonSettings.DOWN, EnableMode.ENABLED, true);
            next.visible = false;
            friendsPanel.addContainedButton(next);
        } else {
            this.friendsPanel.adjustPosition();
        }
    }

}

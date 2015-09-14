package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.MessageAddFriend;
import com.timthebrick.tinystorage.network.message.MessageRemoveFriend;
import com.timthebrick.tinystorage.util.client.Colours;
import com.timthebrick.tinystorage.util.client.SessionVars;
import com.timthebrick.tinystorage.util.client.colour.Colour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.UUID;

public class GuiFriendsList extends GuiTextList.GuiTextListTabbed {

    private IContainerWidgetProvider widgetProvider;

    public GuiFriendsList(IContainerWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text) {
        this(widgetProvider, tab, fontRenderer, x, y, width, height, text, null);
    }

    public GuiFriendsList(IContainerWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text, GuiTextInput filter) {
        super(widgetProvider, tab, fontRenderer, x, y, width, height, text, filter);
        text.remove(Minecraft.getMinecraft().thePlayer.getDisplayName());
        this.widgetProvider = widgetProvider;
    }

    @Override
    public boolean onMouseClick(int xPos, int yPos, int btn) {
        if (textList.size() > 0 && this.isVisible() && this.isEnabled()) {
            if (getWidgetAreaAbsolute().contains(xPos - widgetProvider.getGuiLeft(), yPos - widgetProvider.getGuiTop())) {
                if (xPos - widgetProvider.getGuiLeft() > xPosition + getWidth() - 10) {
                    int rowSelect = (int) Math.floor((yPos - widgetProvider.getGuiTop() - (yPosition + 1)) / (renderer.FONT_HEIGHT));
                    if (rowSelect < displayedText.size()) {
                        if (widgetProvider.getTileEntity() instanceof TileEntityTinyStorage) {
                            for (UUID id : TinyStorage.instance.playerUUIDMap.keySet()) {
                                if (TinyStorage.instance.playerUUIDMap.get(id).equals(displayedText.get(rowSelect))) {
                                    if (!((TileEntityTinyStorage) widgetProvider.getTileEntity()).friendsList.contains(id.toString() + displayedText.get(rowSelect))) {
                                        ((TileEntityTinyStorage) widgetProvider.getTileEntity()).addFriend(id, TinyStorage.instance.playerUUIDMap.get(id));
                                        PacketHandler.INSTANCE.sendToServer(new MessageAddFriend(id, TinyStorage.instance.playerUUIDMap.get(id), widgetProvider.getTileEntity().xCoord, widgetProvider.getTileEntity().yCoord, widgetProvider.getTileEntity().zCoord));
                                        SessionVars.addRecentPlayer(id);
                                    } else {
                                        ((TileEntityTinyStorage) widgetProvider.getTileEntity()).removeFriend(id, TinyStorage.instance.playerUUIDMap.get(id));
                                        PacketHandler.INSTANCE.sendToServer(new MessageRemoveFriend(id, TinyStorage.instance.playerUUIDMap.get(id), widgetProvider.getTileEntity().xCoord, widgetProvider.getTileEntity().yCoord, widgetProvider.getTileEntity().zCoord));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        if (isVisible()) {
            //Draw the background
            guiScreen.drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            guiScreen.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            Colour.resetGLColour();

            //Draw the highlight, if any
            if (this.indexSelected > 0) {
                guiScreen.drawRect(xPosition, (this.yPosition) + ((indexSelected - 1) * renderer.FONT_HEIGHT), xPosition + this.width, (this.yPosition + 1) + ((indexSelected * renderer.FONT_HEIGHT)), new Colour(90, 255, 255).getColour());
            }
            Colour.resetGLColour();

            //Draw the list of strings
            int i = 0;
            int j = 0;
            displayedText.clear();
            for (String name : textList) {
                String dispName = renderer.trimStringToWidth(name, getWidth() - 11);
                if (j >= displayIndex) {
                    if (filter != null) {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            if (filter.getText().isEmpty()) {
                                if (indexSelected > 0 && indexSelected - 1 == i) {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                    displayedText.add(name);
                                } else {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                    displayedText.add(name);
                                }
                                i++;
                            } else {
                                if (name.toLowerCase().contains(filter.getText().toLowerCase())) {
                                    if (indexSelected > 0 && indexSelected - 1 == i) {
                                        renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                        displayedText.add(name);
                                    } else {
                                        renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                        displayedText.add(name);
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            if (indexSelected > 0 && indexSelected - 1 == i) {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                displayedText.add(name);
                            } else {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                displayedText.add(name);
                            }
                            i++;
                        }
                    }
                }
                j++;
                Colour.resetGLColour();
            }
            Colour.resetGLColour();

            //Draw more things
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
            i = 0;
            j = 0;
            for (String name : textList) {
                if (j >= displayIndex) {
                    String dispName = renderer.trimStringToWidth(name, getWidth() - 11);
                    if (filter != null) {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            if (filter.getText().isEmpty()) {
                                this.drawTexturedModalRect(xPosition + getWidth() - 11, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT), 48, 0, 9, 9);
                                if (widgetProvider.getTileEntity() instanceof TileEntityTinyStorage) {
                                    TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) widgetProvider.getTileEntity();
                                    for (UUID id : TinyStorage.instance.playerUUIDMap.keySet()) {
                                        if (tileEntity.friendsList.contains(id.toString() + TinyStorage.instance.playerUUIDMap.get(id)) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                            Colours.General.RED.performGLColour3f();
                                            this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                            Colour.resetGLColour();
                                        } else if (SessionVars.getRecentFriends().contains(id) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                            Colours.General.YELLOW.performGLColour3f();
                                            this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                            Colour.resetGLColour();
                                        }
                                    }
                                }
                                i++;
                            } else {
                                if (name.toLowerCase().contains(filter.getText().toLowerCase())) {
                                    this.drawTexturedModalRect(xPosition + getWidth() - 11, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT), 48, 0, 9, 9);
                                    if (widgetProvider.getTileEntity() instanceof TileEntityTinyStorage) {
                                        TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) widgetProvider.getTileEntity();
                                        for (UUID id : TinyStorage.instance.playerUUIDMap.keySet()) {
                                            if (tileEntity.friendsList.contains(id.toString() + TinyStorage.instance.playerUUIDMap.get(id)) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                                Colours.General.RED.performGLColour3f();
                                                this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                                Colour.resetGLColour();
                                            } else if (SessionVars.getRecentFriends().contains(id) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                                Colours.General.YELLOW.performGLColour3f();
                                                this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                                Colour.resetGLColour();
                                            }
                                        }
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            this.drawTexturedModalRect(xPosition + getWidth() - 11, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT), 48, 0, 9, 9);
                            if (widgetProvider.getTileEntity() instanceof TileEntityTinyStorage) {
                                TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) widgetProvider.getTileEntity();
                                for (UUID id : TinyStorage.instance.playerUUIDMap.keySet()) {
                                    if (tileEntity.friendsList.contains(id.toString() + TinyStorage.instance.playerUUIDMap.get(id)) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                        Colours.General.RED.performGLColour3f();
                                        this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                        Colour.resetGLColour();
                                    } else if (SessionVars.getRecentFriends().contains(id) && name.equals(TinyStorage.instance.playerUUIDMap.get(id))) {
                                        Colours.General.YELLOW.performGLColour3f();
                                        this.drawTexturedModalRect(xPosition + getWidth() - 10, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 57, 1, 7, 7);
                                        Colour.resetGLColour();
                                    }
                                }
                            }
                            i++;
                        }
                    }
                }
                j++;
                Colour.resetGLColour();
            }
            Colour.resetGLColour();
        }
    }
}

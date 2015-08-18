package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.util.colour.Colour;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class GuiFriendsList extends GuiTextList.GuiTextListTabbed {

    public GuiFriendsList(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text) {
        this(widgetProvider, tab, fontRenderer, x, y, width, height, text, null);
    }

    public GuiFriendsList(IWidgetProvider widgetProvider, GuiTabbedPane tab, FontRenderer fontRenderer, int x, int y, int width, int height, List<String> text, GuiTextInput filter) {
        super(widgetProvider, tab, fontRenderer, x, y, width, height, text, filter);
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        Colour.resetGLColour();
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
        if (isVisible()) {
            guiScreen.drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            guiScreen.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
            if (this.indexSelected > 0) {
                guiScreen.drawRect(xPosition, (this.yPosition) + ((indexSelected - 1) * renderer.FONT_HEIGHT), xPosition + this.width, (this.yPosition + 1) + ((indexSelected * renderer.FONT_HEIGHT)), new Colour(90, 255, 255).getColour());
            }
            guiScreen.drawTexturedModalRect(-10, -10, 48, 0, 9, 9);
            int i = 0;
            int j = 0;
            for (String name : textList) {
                if (j >= displayIndex) {
                    String dispName = renderer.trimStringToWidth(name, getWidth() - 11);
                    if (filter != null) {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            if (filter.getText().isEmpty()) {
                                if (indexSelected > 0 && indexSelected - 1 == i) {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                } else {
                                    renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                }
                                i++;
                            } else {
                                if (name.toLowerCase().contains(filter.getText().toLowerCase())) {
                                    if (indexSelected > 0 && indexSelected - 1 == i) {
                                        renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                                    } else {
                                        renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        if ((this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1 + renderer.FONT_HEIGHT < this.height + this.yPosition) {
                            if (indexSelected > 0 && indexSelected - 1 == i) {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, new Colour(000, 000, 000).getColour());
                            } else {
                                renderer.drawString(dispName, xPosition + 1, (this.yPosition + 1) + (i * renderer.FONT_HEIGHT) + 1, 14737632);
                            }
                            i++;
                        }
                    }
                }
                j++;
            }
        }
    }
}

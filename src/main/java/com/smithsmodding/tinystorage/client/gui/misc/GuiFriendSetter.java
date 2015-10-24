package com.smithsmodding.tinystorage.client.gui.misc;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.client.gui.GuiTinyStorageSimple;
import com.smithsmodding.tinystorage.client.gui.widgets.*;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.CharFilters;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.EnableMode;
import com.smithsmodding.tinystorage.common.reference.Messages;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.util.client.Colours;
import com.smithsmodding.tinystorage.util.common.UUIDHelper;
import com.smithsmodding.tinystorage.util.client.colour.Colour;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFriendSetter extends GuiTinyStorageSimple {

    GuiLabel searchLabel;
    GuiTextInput search;
    GuiTextList playerList;
    GuiImageButton prev;
    GuiImageButton next;

    public GuiFriendSetter(ItemStack stack) {
        super();
        this.xSize = 116;
        this.ySize = 174;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addWidgets();
        this.addButtons();
    }

    @Override
    protected void drawGuiBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiFriendSetter.png"));
        drawBG(0, 0, mouseX, mouseY);
        drawTexturedModalRect(getGuiLeft(), getGuiTop(), 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiForegroundLayer(int mouseX, int mouseY) {
        drawFG(0, 0, mouseX, mouseY);
    }


    @Override
    protected void drawFG(int guiLeft, int guiTop, int mouseX, int mouseY) {
        super.drawFG(guiLeft, guiTop, mouseX, mouseY);
    }


    @Override
    protected void drawBG(int guiLeft, int guiTop, int mouseX, int mouseY) {
        handleButtonVisibility();
        handleWidgetVisibility();
        super.drawBG(guiLeft, guiTop, mouseX, mouseY);
    }

    @Override
    public void handleWidgetVisibility() {
        if (searchLabel != null) {
            searchLabel.setVisibility(true);
        }
        if (search != null) {
            search.setVisibility(true);
        }
        if (playerList != null) {
            playerList.setVisibility(true);
        }
        super.handleWidgetVisibility();
    }

    @Override
    public void handleButtonVisibility() {
        if (prev != null) {
            prev.setVisibility(true);
        }
        if (next != null) {
            next.setVisibility(true);
        }
        super.handleButtonVisibility();
    }

    @Override
    public void addWidgets() {
        if (searchLabel == null) {
            searchLabel = new GuiLabel(this, fontRendererObj, 4, 4, xSize - 4, fontRendererObj.FONT_HEIGHT + 1, Messages.GuiLabels.FRIENDS_LIST, new Colour(Colours.INV_GRAY));
            this.addWidget(searchLabel);
        } else {
            searchLabel.adjustPosition();
        }
        if (search == null) {
            search = new GuiTextInput(this, this.fontRendererObj, 4, 4 + searchLabel.getHeight() + 1, xSize - 9, 10, CharFilters.FILTER_ALPHANUMERIC);
            this.addWidget(search);
        } else {
            search.adjustPosition();
        }
        if (playerList == null) {
            playerList = new GuiSetterFriendsList(this, this.fontRendererObj, search.getXOrigin(), search.getYOrigin() + search.getHeight() + 4, xSize - 18, 3 + (fontRendererObj.FONT_HEIGHT * 15), UUIDHelper.getStringFromMap(TinyStorage.instance.playerUUIDMap), search);
            this.addWidget(playerList);
        } else {
            playerList.adjustPosition();
        }
    }

    @Override
    protected void addButtons() {
        super.addButtons();
        prev = new GuiImageButton(playerList.xPos() + playerList.getWidth() + 2, playerList.yPos() - 1, ButtonSettings.UP, EnableMode.ENABLED, true);
        prev.visible = true;
        this.buttonList.add(prev);
        next = new GuiImageButton(playerList.xPos() + playerList.getWidth() + 2, playerList.yPos() + playerList.getHeight() - 7, ButtonSettings.DOWN, EnableMode.ENABLED, true);
        next.visible = true;
        this.buttonList.add(next);
    }
}

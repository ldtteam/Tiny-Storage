package com.smithsmodding.tinystorage.client.gui.inventory.implementations;

import com.smithsmodding.tinystorage.client.gui.widgets.settings.BooleanMode;
import com.smithsmodding.tinystorage.common.inventory.implementations.ContainerTrashChest;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityTrashChest;
import com.smithsmodding.tinystorage.network.message.MessageConfigButton;
import com.smithsmodding.tinystorage.util.client.Colours;
import com.smithsmodding.tinystorage.client.gui.widgets.GuiImageButton;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.smithsmodding.tinystorage.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.smithsmodding.tinystorage.client.gui.GuiTinyStorage;
import com.smithsmodding.tinystorage.common.reference.Names;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiTrashChest extends GuiTinyStorage {

    private TileEntityTrashChest tileEntity;
    private GuiImageButton deleteLastStack;

    public GuiTrashChest (InventoryPlayer inventoryPlayer, TileEntityTrashChest tileEntity) {
        super(new ContainerTrashChest(inventoryPlayer, tileEntity), tileEntity);
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer (int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 3, Colours.INV_GRAY);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
        drawFG(0, 0, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer (float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiTrashChest.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawBG(0, 0, x, y);
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    public void addButtons () {
        super.addButtons();
        deleteLastStack = new GuiImageButton(this.guiLeft - 18, this.guiTop + 8, ButtonSettings.DELETE_LAST_STACK, BooleanMode.FALSE);
        this.buttonList.add(deleteLastStack);
    }

    @Override
    public void drawFG (int ox, int oy, int x, int y) {
        super.drawFG(ox, oy, x, y);
        if (this.deleteLastStack != null) {
            this.deleteLastStack.set(this.tileEntity.deleteStack);
        }
    }

    @Override
    public void drawBG (int ox, int oy, int x, int y) {
        super.drawBG(ox, oy, x, y);
        if (this.deleteLastStack != null) {
            this.deleteLastStack.setVisibility(true);
        }
    }
    @Override
    protected void mouseClicked(int xCoord, int yCoord, int btn) {
        super.mouseClicked(xCoord, yCoord, btn);
    }

    @Override
    protected void actionPerformed (GuiButton btn) {
        super.actionPerformed(btn);
        boolean backwards = Mouse.isButtonDown(1);
        if (btn == this.deleteLastStack) {
            PacketHandler.INSTANCE.sendToServer(new MessageConfigButton(Minecraft.getMinecraft().thePlayer, this.deleteLastStack.getSetting(), backwards, this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord));
        }
    }

}

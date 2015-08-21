package com.timthebrick.tinystorage.client.gui.inventory.implementations;

import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.*;
import com.timthebrick.tinystorage.client.gui.widgets.settings.AnimationDirection;
import com.timthebrick.tinystorage.common.inventory.implementations.ContainerImpossibleChest;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.MessageScrollBar;
import com.timthebrick.tinystorage.util.client.Colours;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityImpossibleChest;
import com.timthebrick.tinystorage.util.client.colour.Colour;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiImpossibleChest extends GuiTinyStorage {

    private TileEntityImpossibleChest tileEntity;
    private GuiScrollBar scrollBar;
    private int ticksExisted;

    public GuiImpossibleChest(InventoryPlayer inventoryPlayer, TileEntityImpossibleChest tileEntity) {
        super(new ContainerImpossibleChest(inventoryPlayer, tileEntity), tileEntity);
        this.tileEntity = tileEntity;
        xSize = 194;
        ySize = 222;
    }

    @Override
    public void addWidgets() {
        if (scrollBar == null) {
            this.scrollBar = new GuiScrollBar(this, 174, 18, 106);
            this.addWidget(scrollBar);
        } else {
            scrollBar.adjustPosition();
        }
        for (int i = 0; i < ContainerImpossibleChest.INVENTORY_COLUMNS; i++) {
            if (i % 2 == 0) {
                GuiAnimationVertical guiAnimationVertical = new GuiAnimationVertical(this, 7 + (i * 18), 17, 0, 0, 18, 108, 2, AnimationDirection.BOTTOM_UP, new Colour(47, 80, 123));
                this.addWidget(guiAnimationVertical);
            } else {
                GuiAnimationVertical guiAnimationVertical = new GuiAnimationVertical(this, 7 + (i * 18), 17, 35, 0, 18, 108, 2, AnimationDirection.TOP_DOWN, new Colour(47, 80, 123));
                this.addWidget(guiAnimationVertical);
            }
        }
        super.addWidgets();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        if (tileEntity.hasOwner()) {
            fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + tileEntity.getOwner(), 8, 6, Colours.INV_GRAY);
        } else {
            fontRendererObj.drawString(StatCollector.translateToLocal(tileEntity.getInventoryName()), 8, 6, Colours.INV_GRAY);
        }
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
        drawFG(0, 0, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiImpossibleChest.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawBG(0, 0, x, y);
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void drawFG(int ox, int oy, int x, int y) {
        super.drawFG(ox, oy, x, y);
    }

    @Override
    public void drawBG(int ox, int oy, int x, int y) {
        handleWidgetVisibility();
        super.drawBG(ox, oy, x, y);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        boolean foundStoppedAnimation = false;
        for (IGuiAnimation animation : animations) {
            if (!animation.isRunning()) {
                foundStoppedAnimation = true;
                ticksExisted++;
                if (ticksExisted % 10 == 0) {
                    animation.startAnimation();
                }
            }
        }
        if (!foundStoppedAnimation) {
            ticksExisted = 0;
        }
    }

    @Override
    protected void mouseClicked(int xCoord, int yCoord, int btn) {
        if (scrollBar != null) {
            scrollBar.onMouseClick(xCoord, yCoord, btn);
        }
        super.mouseClicked(xCoord, yCoord, btn);
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        if (scrollBar != null) {
            scrollBar.mouseClickMove(x, y, button, time);
            return;
        }
        super.mouseClickMove(x, y, button, time);
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int button) {
        if (scrollBar != null) {
            scrollBar.mouseMovedOrUp(x, y, button);
        }
        super.mouseMovedOrUp(x, y, button);
    }

    @Override
    public void handleWidgetFunctionality(IGuiWidgetAdvanced widget) {
        if (this.container instanceof IWidgetReceptor) {
            if (widget instanceof GuiScrollBar) {
                PacketHandler.INSTANCE.sendToServer(new MessageScrollBar(Minecraft.getMinecraft().thePlayer, ((GuiScrollBar) widget).getScrollPos(), this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord));
            }
            ((IWidgetReceptor) container).handleWidgetInteraction(widget);
        }
        super.handleWidgetFunctionality(widget);
    }

    @Override
    public void handleWidgetVisibility() {
        if (this.scrollBar != null) {
            this.scrollBar.setVisibility(true);
        }
        for (IGuiAnimation animation : this.animations) {
            if (animation != null) {
                animation.setVisibility(true);
            }
        }
        super.handleWidgetVisibility();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addWidgets();
    }

    @Override
    public int getInvLeft() {
        return 7;
    }

    @Override
    public int getInvTop() {
        return 17;
    }

    @Override
    public int getInvWidth() {
        return 162;
    }

    @Override
    public int getInvHeight() {
        return 108;
    }
}

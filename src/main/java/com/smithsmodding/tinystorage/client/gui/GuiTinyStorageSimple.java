package com.smithsmodding.tinystorage.client.gui;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import com.smithsmodding.tinystorage.client.gui.widgets.*;
import com.smithsmodding.tinystorage.util.client.colour.Colour;
import cpw.mods.fml.common.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Optional.InterfaceList({@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")})
public class GuiTinyStorageSimple extends GuiScreen implements IScreenWidgetProvider, INEIGuiHandler {

    protected List<IGuiWidgetAdvanced> widgets = new ArrayList<IGuiWidgetAdvanced>();
    protected List<IGuiAnimation> animations = new ArrayList<IGuiAnimation>();
    private GuiTabbedPane currentOpenPane;
    protected int xSize = 176;
    protected int ySize = 166;
    private int guiLeft;
    private int guiTop;

    public GuiTinyStorageSimple() {
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiBackgroundLayer(partialTicks, mouseX, mouseY);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        super.drawScreen(mouseX, mouseY, partialTicks);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) k, (float) l, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiForegroundLayer(mouseX, mouseY);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
        Colour.resetGLColour();
        boolean hasClicked = Mouse.isButtonDown(0);
        for (IGuiWidgetAdvanced widget : widgets) {
            widget.updateGraphics();
            if (widget instanceof IGuiWidgetBackground) {
                widget.drawWidget(this, xSize, ySize);
            }
            if (widget instanceof GuiTabbedPane) {
                ((GuiTabbedPane) widget).drawScreen(mouseX, mouseY, partialTicks);
            }
        }
        Colour.resetGLColour();
        for (Object c : this.buttonList) {
            if (c instanceof IButtonTooltip) {
                IButtonTooltip tooltip = (IButtonTooltip) c;
                int x = tooltip.xPos(); // ((GuiImgButton) c).xPosition;
                int y = tooltip.yPos(); // ((GuiImgButton) c).yPosition;
                if (x < mouseX && x + tooltip.getWidth() > mouseX && tooltip.isVisible()) {
                    if (y < mouseY && y + tooltip.getHeight() > mouseY) {
                        if (y < 15) {
                            y = 15;
                        }
                        String msg = tooltip.getMessage();
                        if (msg != null) {
                            this.drawTooltip(x + 11, y + 4, 0, msg);
                            Colour.resetGLColour();
                        }
                    }
                }
            }
        }
        Colour.resetGLColour();
        for (Object c : this.widgets) {
            if (c instanceof IWidgetTooltip) {
                IWidgetTooltip tooltip = (IWidgetTooltip) c;
                int x = tooltip.xTriggerPos(); // ((GuiImgButton) c).xPosition;
                int y = tooltip.yTriggerPos(); // ((GuiImgButton) c).yPosition;
                if (x < mouseX && x + tooltip.getTooltipWidth() > mouseX && tooltip.isTooltipVisible()) {
                    if (y < mouseY && y + tooltip.getTooltipHeight() > mouseY) {
                        if (y < 15) {
                            y = 15;
                        }
                        String msg = tooltip.getMessage();
                        if (msg != null) {
                            this.drawTooltip(x + 11, y + 4, 0, msg);
                            Colour.resetGLColour();
                        }
                    }
                }
            }
        }
        Colour.resetGLColour();
    }

    private void drawTooltip(int x, int y, int forceWidth, String Msg) {
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        String[] var4 = Msg.split("\n");
        if (var4.length > 0) {
            int var5 = 0;
            int var6;
            int var7;
            for (var6 = 0; var6 < var4.length; ++var6) {
                var7 = this.fontRendererObj.getStringWidth(var4[var6]);
                if (var7 > var5) {
                    var5 = var7;
                }
            }
            var6 = x + 5;
            var7 = y - 5;
            int var9 = 8;
            if (var4.length > 1) {
                var9 += 2 + (var4.length - 1) * 10;
            }
            if (this.guiTop + var7 + var9 + 6 > this.height) {
                var7 = this.height - var9 - this.guiTop - 6;
            }
            if (forceWidth > 0) {
                var5 = forceWidth;
            }
            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int var10 = -267386864;
            this.drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10);
            this.drawGradientRect(var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
            this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
            this.drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10);
            this.drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
            int var11 = 1347420415;
            int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
            this.drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11);
            this.drawGradientRect(var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < var4.length; ++var13) {
                String var14 = var4[var13];
                if (var13 == 0) {
                    var14 = '\u00a7' + Integer.toHexString(15) + var14;
                } else {
                    var14 = "\u00a77" + var14;
                }
                this.fontRendererObj.drawStringWithShadow(var14, var6, var7, -1);
                if (var13 == 0) {
                    var7 += 2;
                }
                var7 += 10;
            }
            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
        }
        GL11.glPopAttrib();
        RenderHelper.enableStandardItemLighting();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Colour.resetGLColour();
    }

    protected void drawGuiForegroundLayer(int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawFG(guiLeft, guiTop, mouseX, mouseY);
    }

    protected void drawGuiBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawBG(guiLeft, guiTop, mouseX, mouseY);
    }

    protected void drawFG(int guiLeft, int guiTop, int mouseX, int mouseY) {
        for (IGuiWidgetAdvanced widget : this.widgets) {
            if (widget instanceof IGuiWidgetBackground) {
                continue;
            }
            if (widget.isVisible()) {
                widget.drawWidget(this, xSize, ySize);
            }
        }
        for (IGuiAnimation animation : this.animations) {
            if (animation.isVisible()) {
                animation.drawAnimationBackground(this, xSize, ySize);
            }
        }
        for (IGuiAnimation animation : this.animations) {
            if (animation.isVisible()) {
                animation.drawAnimationForeground(this, xSize, ySize);
            }
        }
    }

    protected void drawBG(int guiLeft, int guiTop, int mouseX, int mouseY) {
        this.handleButtonVisibility();
        this.handleWidgetVisibility();
    }

    @Override
    public void addWidgets() {
    }

    protected void addButtons() {
    }

    @Override
    public void handleWidgetVisibility() {
    }

    public void handleButtonVisibility() {
    }

    @Override
    public void updateScreen() {
        for (IGuiWidgetAdvanced widget : widgets) {
            widget.updateWidget();
        }
        super.updateScreen();
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        int b = Mouse.getEventButton();
        int delta = Mouse.getEventDWheel();
        if (delta != 0) {
            mouseWheelEvent(x, y, delta);
        }
    }

    @Override
    protected void mouseClicked(int xCoord, int yCoord, int btn) {
        if (!widgets.isEmpty()) {
            for (IGuiWidgetAdvanced widget : widgets) {
                if (widget instanceof GuiTabbedPane && ((GuiTabbedPane) widget).expandButtonContains(xCoord, yCoord)) {
                    if (currentOpenPane == null) {
                        currentOpenPane = (GuiTabbedPane) widget;
                        widget.onMouseClick(xCoord, yCoord, btn);
                    } else if (currentOpenPane == widget && currentOpenPane.isExpanded()) {
                        currentOpenPane = null;
                        widget.onMouseClick(xCoord, yCoord, btn);
                    } else if (currentOpenPane != null && currentOpenPane.isExpanded()) {
                        currentOpenPane.toggleAnimation();
                        currentOpenPane = (GuiTabbedPane) widget;
                        widget.onMouseClick(xCoord, yCoord, btn);
                    }
                } else {
                    widget.onMouseClick(xCoord, yCoord, btn);
                }
            }
        }
        if (btn == 1) {
            for (Object o : this.buttonList) {
                GuiButton guibutton = (GuiButton) o;
                if (guibutton.mousePressed(this.mc, xCoord, yCoord)) {
                    super.mouseClicked(xCoord, yCoord, 0);
                    return;
                }
            }
        }
        super.mouseClicked(xCoord, yCoord, btn);
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int button) {
        super.mouseMovedOrUp(x, y, button);
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        super.mouseClickMove(x, y, button, time);
    }

    protected void mouseWheelEvent(int x, int y, int delta) {
        if (!widgets.isEmpty()) {
            for (IGuiWidgetAdvanced widget : widgets) {
                widget.mouseWheel(x, y, delta);
            }
        }
    }

    @Override
    protected void keyTyped(char c, int key) {
        boolean keyCaptured = false;
        GuiTextInput guiTextInput = null;
        for (IGuiWidgetAdvanced widget : widgets) {
            if (widget instanceof IGuiWidgetContainer) {
                if (((IGuiWidgetContainer) widget).isActive()) {
                    widget.keyTyped(c, key);
                    keyCaptured = ((IGuiWidgetContainer) widget).getKeyCaptured();
                    if (keyCaptured) {
                        return;
                    }
                }
            } else {
                widget.keyTyped(c, key);
            }
        }
        for (IGuiWidgetAdvanced widget : widgets) {
            if (widget instanceof GuiTextInput) {
                if (((GuiTextInput) widget).isFocused()) {
                    guiTextInput = (GuiTextInput) widget;
                }
            }
        }
        if (key == 1) {
            if (guiTextInput != null && key == 1) {
                guiTextInput.setFocused(false);
                return;
            }
        }
        if (c == '\t') {
            if (guiTextInput != null && c == '\t') {
                guiTextInput.setFocused(false);
                return;
            }
        }
        if (guiTextInput != null) {
            String old = guiTextInput.getText();
            if (guiTextInput.textboxKeyTyped(c, key)) {
                onTextFieldChanged(guiTextInput, old);
                return;
            }
        }
        if (c == 'f') {
            for (IGuiWidgetAdvanced widget : widgets) {
                if (widget instanceof GuiTextInput) {
                    if (!((GuiTextInput) widget).isFocused()) {
                        guiTextInput = (GuiTextInput) widget;
                        guiTextInput.setFocused(true);
                        keyCaptured = true;
                        break;
                    }
                }
            }
        }
        if (!keyCaptured) {
            super.keyTyped(c, key);
        }
    }

    private void onTextFieldChanged(GuiTextInput guiTextInput, String old) {
    }

    @Override
    protected void actionPerformed(GuiButton btn) {
        super.actionPerformed(btn);
    }

    @Override
    public void addWidget(IGuiWidgetSimple widget) {
        if (widget instanceof IGuiAnimation) {
            animations.add((IGuiAnimation) widget);
            widgets.add((IGuiWidgetAdvanced) widget);
            ((IGuiWidgetAdvanced) widget).adjustPosition();
        } else {
            widgets.add((IGuiWidgetAdvanced) widget);
            ((IGuiWidgetAdvanced) widget).adjustPosition();
        }
    }

    @Override
    public void removeWidget(IGuiWidgetSimple widget) {
        if (widget instanceof IGuiAnimation) {
            animations.remove(widget);
        }
        widgets.remove(widget);
    }

    @Override
    public void handleWidgetFunctionality(IGuiWidgetAdvanced widget) {
    }

    @Override
    public Minecraft getMinecraft() {
        return this.mc;
    }

    @Override
    public FontRenderer getFontRenderer() {
        return this.fontRendererObj;
    }

    @Override
    public RenderItem getItemRenderer() {
        return this.itemRender;
    }

    @Override
    public int getGuiLeft() {
        return (this.width - this.xSize) / 2;
    }

    @Override
    public int getGuiTop() {
        return (this.height - this.ySize) / 2;
    }

    @Override
    public int getXSize() {
        return xSize;
    }

    @Override
    public int getYSize() {
        return ySize;
    }

    @Override
    public GuiScreen getGuiScreen() {
        return this;
    }

    @Override
    @Optional.Method(modid = "NotEnoughItems")
    public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility) {
        return null;
    }

    @Override
    @Optional.Method(modid = "NotEnoughItems")
    public Iterable<Integer> getItemSpawnSlots(GuiContainer gui, ItemStack item) {
        return null;
    }

    @Override
    @Optional.Method(modid = "NotEnoughItems")
    public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui) {
        return null;
    }

    @Override
    @Optional.Method(modid = "NotEnoughItems")
    public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button) {
        return false;
    }

    @Override
    @Optional.Method(modid = "NotEnoughItems")
    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h) {
        Rectangle area = new Rectangle(x, y, w, h);
        for (IGuiWidgetAdvanced widget : widgets) {
            if ((area.intersects(widget.getWidgetVisibleAreaAbsolute()) && widget.isVisible())) {
                return true;
            }
        }
        return false;
    }
}

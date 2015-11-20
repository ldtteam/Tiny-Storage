package com.smithsmodding.tinystorage.client.gui.widgets;

import com.smithsmodding.tinystorage.common.reference.Messages;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.util.client.colour.Colour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GuiTabbedPane extends Gui implements IGuiWidgetAdvanced, IWidgetTooltip, IGuiWidgetContainer {
    /**
     * Whether or not this panel should be forced open
     */
    private boolean forceOpen;
    /**
     * True if this control is enabled, false to disable.
     */
    private boolean enabled;
    /**
     * Hides the control completely if false.
     */
    private boolean visible;
    /**
     * Whether the area has been expanded or not
     */
    private boolean expanded;
    /**
     * Whether or not the pane should be moving
     */
    private boolean shouldAnimate;
    /**
     * The X Position of the widget
     */
    private int xPosition;
    /**
     * The Y Position of the widget
     */
    private int yPosition;
    /**
     * The X Origin of the widget
     */
    private int xOrigin;
    /**
     * The Y Origin of the widget
     */
    private int yOrigin;
    /**
     * The width of the pane
     */
    protected int widthPane;
    /**
     * The height of the pane
     */
    protected int heightPane;
    /**
     * The width of the 'button' to show
     */
    protected int widthButton;
    /**
     * The height of the 'button' to show
     */
    protected int heightButton;
    /**
     * The area to click on to expand/retract the pane
     */
    private Rectangle expandButton;
    /**
     * The X start position for the background texture
     */
    protected int backgroundTextureX;
    /**
     * The Y start position for the background texture
     */
    protected int backgroundTextureY;
    /**
     * The X start position for the foreground texture
     */
    protected int buttonTextureX;
    /**
     * The Y start position for the foreground texture
     */
    protected int buttonTextureY;
    /**
     * Used to determine how much of the tab is shown, and how much is left to go
     */
    private float progressX, progressY;
    /**
     * The widget provider for this IGuiWidgetAdvanced
     */
    private IScreenWidgetProvider widgetProvider;
    /**
     * The handler class for the pane
     */
    private IGuiTabHandler tabHandler;
    /**
     * A list of widgets contained by this panel
     */
    private ArrayList<IGuiWidgetAdvanced> containedWidgets = new ArrayList<IGuiWidgetAdvanced>();
    private List<GuiButton> containedButtons = new ArrayList<GuiButton>();

    private boolean keyCaptured = false;
    private boolean mouseCaptured = false;
    private GuiButton selectedButton;

    /**
     * @param widgetProvider    The provider that adds this object to it
     * @param handler           The specific widget handler for the tab object
     * @param x                 The X Position of  the tab (relative to GUI)
     * @param y                 The Y Position of the tab (relative to GUI)
     * @param width             The width of the entire tab
     * @param height            The height of the entire tab
     * @param buttonWidth       The width of the display button
     * @param buttonHeight      The height of the display button
     * @param backgroundX       The X Position of the background texture for the tab
     * @param backgroundY       The Y Position of the background texture for the tab
     * @param buttonBackgroundX The X Position of the background texture for the button
     * @param buttonBackgroundY The Y Position of the background texture for the button
     */
    public GuiTabbedPane(IScreenWidgetProvider widgetProvider, IGuiTabHandler handler, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int backgroundX, int backgroundY, int buttonBackgroundX, int buttonBackgroundY) {
        this(widgetProvider, handler, false, x, y, width, height, buttonWidth, buttonHeight, backgroundX, backgroundY, buttonBackgroundX, buttonBackgroundY);
    }

    /**
     * @param widgetProvider    The provider that adds this object to it
     * @param handler           The specific widget handler for the tab object
     * @param forceOpen         Whether to force the panel open or not
     * @param x                 The X Position of  the tab (relative to GUI)
     * @param y                 The Y Position of the tab (relative to GUI)
     * @param width             The width of the entire tab
     * @param height            The height of the entire tab
     * @param buttonWidth       The width of the display button
     * @param buttonHeight      The height of the display button
     * @param backgroundX       The X Position of the background texture for the tab
     * @param backgroundY       The Y Position of the background texture for the tab
     * @param buttonBackgroundX The X Position of the background texture for the button
     * @param buttonBackgroundY The Y Position of the background texture for the button
     */
    public GuiTabbedPane(IScreenWidgetProvider widgetProvider, IGuiTabHandler handler, boolean forceOpen, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int backgroundX, int backgroundY, int buttonBackgroundX, int buttonBackgroundY) {
        this.widgetProvider = widgetProvider;
        this.tabHandler = handler;
        this.forceOpen = forceOpen;
        this.xOrigin = x;
        this.yOrigin = y;
        this.widthPane = width;
        this.heightPane = height;
        this.widthButton = buttonWidth;
        this.heightButton = buttonHeight;
        this.backgroundTextureX = backgroundX;
        this.backgroundTextureY = backgroundY;
        this.buttonTextureX = buttonBackgroundX;
        this.buttonTextureY = buttonBackgroundY;
        if (!forceOpen) {
            progressX = getButtonWidth();
            progressY = getButtonHeight();
        } else {
            progressX = width;
            progressY = height;
        }
        setEnabled(true);
    }

    public void addContainedWidget(IGuiWidgetAdvanced widget) {
        containedWidgets.add(widget);
        widget.adjustPosition();
    }

    public void addContainedButton(GuiButton button) {
        containedButtons.add(button);
    }

    @Override
    public void adjustPosition() {
        xPosition = xOrigin + widgetProvider.getGuiLeft();
        yPosition = yOrigin + widgetProvider.getGuiTop();
        expandButton = new Rectangle(xPosition, yPosition, getButtonWidth(), getButtonHeight());
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.adjustPosition();
        }
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.isEnabled()) {
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiPanes.png"));
            if (expanded && !shouldAnimate) {
                for (IGuiWidgetAdvanced widget : containedWidgets) {
                    widget.setVisibility(true);
                }
                for (GuiButton button : containedButtons) {
                    button.visible = true;
                }
                this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, getWidth(), getHeight());
            } else if (shouldAnimate) {
                for (IGuiWidgetAdvanced widget : containedWidgets) {
                    widget.setVisibility(false);
                }
                for (GuiButton button : containedButtons) {
                    button.visible = false;
                }
                if (expanded) {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
                } else {
                    this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, (int) Math.ceil(progressX), (int) Math.ceil(progressY));
                }
            } else {
                for (IGuiWidgetAdvanced widget : containedWidgets) {
                    widget.setVisibility(false);
                }
                for (GuiButton button : containedButtons) {
                    button.visible = false;
                }
            }
            for (IGuiWidgetAdvanced widget : containedWidgets) {
                widget.drawWidget(guiScreen, xScreenSize, yScreenSize);
            }
            for (GuiButton button : containedButtons) {
                button.drawButton(guiScreen.mc, xScreenSize, yScreenSize);
            }
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
            this.drawTexturedModalRect(xOrigin, yOrigin, buttonTextureX, buttonTextureY, getButtonWidth(), getButtonHeight());
        } else {
            guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiWidgets.png"));
            this.drawTexturedModalRect(xOrigin, yOrigin, buttonTextureX + getButtonWidth(), buttonTextureY, getButtonWidth(), getButtonHeight());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float btn) {
        for (Object c : this.containedButtons) {
            if (c instanceof IButtonTooltip) {
                IButtonTooltip tooltip = (IButtonTooltip) c;
                int x = tooltip.xPos() + widgetProvider.getGuiLeft(); // ((GuiImgButton) c).xPosition;
                int y = tooltip.yPos() + widgetProvider.getGuiTop(); // ((GuiImgButton) c).yPosition;
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
        for (Object c : this.containedWidgets) {
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
                var7 = this.widgetProvider.getFontRenderer().getStringWidth(var4[var6]);
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
            if (this.widgetProvider.getGuiTop() + var7 + var9 + 6 > this.widgetProvider.getGuiScreen().height) {
                var7 = this.widgetProvider.getGuiScreen().height - var9 - this.widgetProvider.getGuiTop() - 6;
            }
            if (forceWidth > 0) {
                var5 = forceWidth;
            }
            this.zLevel = 300.0F;
            this.widgetProvider.getItemRenderer().zLevel = 300.0F;
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
                this.widgetProvider.getFontRenderer().drawStringWithShadow(StatCollector.translateToLocal(var14), var6, var7, -1);
                if (var13 == 0) {
                    var7 += 2;
                }
                var7 += 10;
            }
            this.zLevel = 0.0F;
            this.widgetProvider.getItemRenderer().zLevel = 0.0F;
        }
        GL11.glPopAttrib();
        RenderHelper.enableStandardItemLighting();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Colour.resetGLColour();
    }

    @Override
    public void updateGraphics() {
        if (this.shouldAnimate && this.isEnabled()) {
            float xAdj, yAdj;
            float multiplier = 5f;

            if (getWidth() < getHeight()) {
                xAdj = 1;
                yAdj = (float) getHeight() / getWidth();
            } else {
                xAdj = (float) (getWidth() / getHeight());
                yAdj = 1;
            }

            if (progressX < getWidth()) {
                progressX += (xAdj * multiplier);
            }
            if (progressY < getHeight()) {
                progressY += (yAdj * multiplier);
            }
            if (progressX >= getWidth() && progressY >= getHeight()) {
                progressX = getButtonWidth();
                progressY = getButtonHeight();
                expanded = !expanded;
                shouldAnimate = false;
            }
        }
    }

    @Override
    public void updateWidget() {
        if (this.isEnabled()) {
            for (IGuiWidgetAdvanced widget : containedWidgets) {
                widget.updateWidget();
            }
        }
    }

    @Override
    public boolean onMouseClick(int xPos, int yPos, int btn) {
        if (this.isEnabled() && this.getWidgetVisibleAreaAbsolute().contains(xPos, yPos)) {
            mouseCaptured = true;
        }
        if (this.isEnabled() && !forceOpen && expandButton.contains(xPos, yPos) && !shouldAnimate) {
            shouldAnimate = true;
        }
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.onMouseClick(xPos, yPos, btn);
        }
        if (btn == 0) {
            for (int l = 0; l < this.containedButtons.size(); ++l) {
                GuiButton guibutton = (GuiButton) this.containedButtons.get(l);
                if (guibutton.mousePressed(this.widgetProvider.getMinecraft(), xPos - widgetProvider.getGuiLeft(), yPos - widgetProvider.getGuiTop())) {
                    GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this.widgetProvider.getGuiScreen(), guibutton, this.containedButtons);
                    if (MinecraftForge.EVENT_BUS.post(event)) {
                        break;
                    }
                    this.selectedButton = event.button;
                    event.button.func_146113_a(this.widgetProvider.getMinecraft().getSoundHandler());
                    this.actionPerformed(event.button);
                    if (this.equals(this.widgetProvider.getMinecraft().currentScreen)) {
                        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.ActionPerformedEvent.Post(this.widgetProvider.getGuiScreen(), event.button, this.containedButtons));
                    }
                }
            }
        }
        return false;
    }

    private void actionPerformed(GuiButton btn) {
        tabHandler.actionPerformed(this, btn);
    }

    @Override
    public boolean mouseClickMove(int x, int y, int button, long time) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseClickMove(x, y, button, time);
        }
        return false;
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseMovedOrUp(x, y, button);
        }
        if (this.selectedButton != null && button == 0) {
            this.selectedButton.mouseReleased(x, y);
            this.selectedButton = null;
        }
    }

    @Override
    public void mouseWheel(int x, int y, int delta) {
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.mouseWheel(x, y, delta);
        }
    }

    @Override
    public void keyTyped(char c, int key) {
        keyCaptured = false;
        GuiTextInput guiTextInput = null;
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            if (widget instanceof GuiTextInput) {
                if (((GuiTextInput) widget).isFocused()) {
                    guiTextInput = (GuiTextInput) widget;
                }
            }
        }
        if (key == 1) {
            if (guiTextInput != null && key == 1) {
                guiTextInput.setFocused(false);
                keyCaptured = true;
                return;
            }
        }
        if (c == '\t') {
            if (guiTextInput != null && c == '\t') {
                guiTextInput.setFocused(false);
                keyCaptured = true;
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
            for (IGuiWidgetAdvanced widget : containedWidgets) {
                if (widget instanceof GuiTextInput) {
                    if (((GuiTextInput) widget).isFocused()) {
                        guiTextInput = (GuiTextInput) widget;
                        guiTextInput.setFocused(true);
                        keyCaptured = true;
                    }
                }
            }
        }
        for (IGuiWidgetAdvanced widget : containedWidgets) {
            widget.keyTyped(c, key);
        }
        if (key == Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode()) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
            return;
        }
    }

    protected void onTextFieldChanged(GuiTextInput textInput, String old) {
        keyCaptured = true;
    }

    @Override
    public Rectangle getWidgetAreaAbsolute() {
        return new Rectangle(xPos(), yPos(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getWidgetAreaRelative() {
        return new Rectangle(getXOrigin(), getYOrigin(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getWidgetVisibleAreaAbsolute() {
        if (shouldAnimate) {
            if (expanded) {
                return new Rectangle(xPos(), yPos(), (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
            } else {
                return new Rectangle(xPos(), yPos(), (int) Math.ceil(progressX), (int) Math.ceil(progressY));
            }
        } else if (expanded) {
            return new Rectangle(xPos(), yPos(), getWidth(), getHeight());
        } else {
            return new Rectangle(xPos(), yPos(), getButtonWidth(), getButtonHeight());
        }
    }

    @Override
    public Rectangle getWidgetVisibleAreaRelative() {
        if (shouldAnimate) {
            if (expanded) {
                return new Rectangle(getXOrigin(), getYOrigin(), (int) Math.ceil(getWidth() - progressX), (int) Math.ceil(getHeight() - progressY));
            } else {
                return new Rectangle(getXOrigin(), getYOrigin(), (int) Math.ceil(progressX), (int) Math.ceil(progressY));
            }
        } else if (expanded) {
            return new Rectangle(getXOrigin(), getYOrigin(), getWidth(), getHeight());
        } else {
            return new Rectangle(getXOrigin(), getYOrigin(), getButtonWidth(), getButtonHeight());
        }
    }

    public boolean expandButtonContains(int xPos, int yPos){
        return expandButton.contains(xPos, yPos);
    }

    @Override
    public int xPos() {
        return xPosition;
    }

    @Override
    public int yPos() {
        return yPosition;
    }

    @Override
    public int getXOrigin() {
        return xOrigin;
    }

    @Override
    public int getYOrigin() {
        return yOrigin;
    }

    @Override
    public int getWidth() {
        return widthPane;
    }

    @Override
    public int getHeight() {
        return heightPane;
    }

    public int getButtonWidth() {
        return widthButton;
    }

    public int getButtonHeight() {
        return heightButton;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setVisibility(boolean vis) {
        this.visible = vis;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public ArrayList<IGuiWidgetAdvanced> getContainedWidgets() {
        return this.containedWidgets;
    }

    public List<GuiButton> getButtonList() {
        return this.containedButtons;
    }

    @Override
    public boolean getKeyCaptured() {
        return keyCaptured;
    }

    public boolean getMouseCaptured() {
        return mouseCaptured;
    }

    public void toggleAnimation() {
        shouldAnimate = !shouldAnimate;
    }

    public boolean isExpanded() {
        return expanded;
    }

    /*
    Tool tip stuff
     */

    @Override
    public String getMessage() {
        return StatCollector.translateToLocal(Messages.WidgetTooltips.FRIENDS_LIST);
    }

    @Override
    public int xTriggerPos() {
        return xPos();
    }

    @Override
    public int yTriggerPos() {
        return yPos();
    }

    @Override
    public int getTooltipWidth() {
        return getButtonWidth();
    }

    @Override
    public int getTooltipHeight() {
        return getButtonHeight();
    }

    @Override
    public boolean isTooltipVisible() {
        return true;
    }
}

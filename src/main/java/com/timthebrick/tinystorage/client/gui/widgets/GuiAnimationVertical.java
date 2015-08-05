package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AnimationDirection;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.util.colour.Colour;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiAnimationVertical extends GuiAnimation {

    /**
     * @param widgetProvider  The provider that adds this object to it
     * @param x               The X Position of  the scroll bar (relative to GUI)
     * @param y               The Y Position of the scroll bar (relative to GUI)
     * @param backgroundX     The X Position of the background texture
     * @param backgroundY     The Y Position of the background texture
     * @param width           The width of the animation
     * @param height          The height of the animation
     * @param updateFrequency The frequency with which to update the animation
     * @param direction       The direction to animate in
     * @param colour          The colour to change the grayscale to
     */
    public GuiAnimationVertical(IWidgetProvider widgetProvider, int x, int y, int backgroundX, int backgroundY, int width, int height, int updateFrequency, AnimationDirection direction, Colour colour) {
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        this.backgroundTextureX = backgroundX;
        this.backgroundTextureY = backgroundY;
        this.foregroundTextureX = backgroundX + width;
        this.foregroundTextureY = backgroundY;
        this.width = width;
        this.height = height;
        this.updateFrequency = updateFrequency;
        this.direction = direction;
        this.colour = colour;
        setEnabled(true);
    }

    /**
     * @param widgetProvider  The provider that adds this object to it
     * @param x               The X Position of  the scroll bar (relative to GUI)
     * @param y               The Y Position of the scroll bar (relative to GUI)
     * @param backgroundX     The X Position of the background texture
     * @param backgroundY     The Y Position of the background texture
     * @param width           The width of the animation
     * @param height          The height of the animation
     * @param updateFrequency The frequency with which to update the animation
     * @param direction       The direction to animate in
     */
    public GuiAnimationVertical(IWidgetProvider widgetProvider, int x, int y, int backgroundX, int backgroundY, int width, int height, int updateFrequency, AnimationDirection direction) {
        this.widgetProvider = widgetProvider;
        this.xOrigin = x;
        this.yOrigin = y;
        this.backgroundTextureX = backgroundX;
        this.backgroundTextureY = backgroundY;
        this.foregroundTextureX = backgroundX + width;
        this.foregroundTextureY = backgroundY;
        this.width = width;
        this.height = height;
        this.updateFrequency = updateFrequency;
        this.direction = direction;
        this.colour = new Colour(1.0F, 1.0F, 1.0F, 1.0F);
        setEnabled(true);
    }

    @Override
    public void updateWidget() {
        if (this.shouldAnimate) {
            lifeCycle++;
            if (lifeCycle % updateFrequency == 0) {
                progress++;
            }
            if (progress == height * 2) {
                progress = 0;
                lifeCycle = 0;
            }
        }
    }

    @Override
    public void drawWidget(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
    }

    @Override
    public void drawAnimationBackground(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiAnimations.png"));
        this.drawTexturedModalRect(xOrigin, yOrigin, backgroundTextureX, backgroundTextureY, width, height);
    }

    @Override
    public void drawAnimationForeground(GuiScreen guiScreen, int xScreenSize, int yScreenSize) {
        this.colour.performGLColour4f();
        guiScreen.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiAnimations.png"));
        if (shouldAnimate) {
            if (direction == AnimationDirection.BOTTOM_UP) {
                if (progress > height) {
                    this.drawTexturedModalRect(xOrigin, yOrigin + (progress - height), foregroundTextureX, foregroundTextureY + (progress - height), width, height - (progress - height));
                } else {
                    this.drawTexturedModalRect(xOrigin, yOrigin + (height - progress), foregroundTextureX, foregroundTextureY + (height - progress), width, progress);
                }
            } else if (direction == AnimationDirection.TOP_DOWN) {
                if (progress > height) {
                    this.drawTexturedModalRect(xOrigin, yOrigin, foregroundTextureX, foregroundTextureY, width, height + (height - progress));
                } else {
                    this.drawTexturedModalRect(xOrigin, yOrigin, foregroundTextureX, foregroundTextureY, width, progress);
                }
            }
        }
        Colour.resetGLColour();
    }
}

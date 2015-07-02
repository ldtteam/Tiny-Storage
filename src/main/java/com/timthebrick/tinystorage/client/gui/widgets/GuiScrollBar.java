package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.client.gui.inventory.GuiTinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.settings.IScrollSource;
import org.lwjgl.opengl.GL11;

public class GuiScrollBar implements IScrollSource {

    private int displayX = 0;
    private int displayY = 0;
    private int width = 12;
    private int height = 16;
    private int pageSize = 1;

    private int maxScroll = 0;
    private int minScroll = 0;
    private int currentScroll = 0;

    public void draw(GuiTinyStorage gui) {
        gui.bindTexture("minecraft", "gui/container/creative_inventory/tabs.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.getRange() == 0) {
            gui.drawTexturedModalRect(this.displayX, this.displayY, 232 + this.width, 0, this.width, 15);
        } else {
            int offset = (this.currentScroll - this.minScroll) * (this.height - 15) / this.getRange();
            gui.drawTexturedModalRect(this.displayX, offset + this.displayY, 232, 0, this.width, 15);
        }
    }

    public int getRange() {
        return this.maxScroll - this.minScroll;
    }

    public int getLeft() {
        return this.displayX;
    }

    public GuiScrollBar setLeft(int v) {
        this.displayX = v;
        return this;
    }

    public int getTop() {
        return this.displayY;
    }

    public GuiScrollBar setTop(int v) {
        this.displayY = v;
        return this;
    }

    public int getWidth() {
        return this.width;
    }

    public GuiScrollBar setWidth(int v) {
        this.width = v;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public GuiScrollBar setHeight(int v) {
        this.height = v;
        return this;
    }

    public void setRange(int min, int max, int pageSize) {
        this.minScroll = min;
        this.maxScroll = max;
        this.pageSize = pageSize;

        if (this.minScroll > this.maxScroll) {
            this.maxScroll = this.minScroll;
        }

        this.applyRange();
    }

    private void applyRange() {
        this.currentScroll = Math.max(Math.min(this.currentScroll, this.maxScroll), this.minScroll);
    }

    @Override
    public int getCurrentScroll() {
        return this.currentScroll;
    }

    public void click(GuiTinyStorage gui, int x, int y) {
        if (this.getRange() == 0) {
            return;
        }

        if (x > this.displayX && x <= this.displayX + this.width) {
            if (y > this.displayY && y <= this.displayY + this.height) {
                this.currentScroll = (y - this.displayY);
                this.currentScroll = this.minScroll + ((this.currentScroll * 2 * this.getRange() / this.height));
                this.currentScroll = (this.currentScroll + 1) >> 1;
                this.applyRange();
            }
        }
    }

    public void wheel(int delta) {
        delta = Math.max(Math.min(-delta, 1), -1);
        this.currentScroll += delta * this.pageSize;
        this.applyRange();
    }
}

package com.timthebrick.tinystorage.client.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.event.*;

public class GuiScrollBar extends Gui implements IGuiWidget, MouseListener, MouseWheelListener {

    /**
     * The height of the scroll bar (max distance scrolled)
     */
    public int scrollHeight;
    /**
     * True if this control is enabled, false to disable.
     */
    public boolean enabled;
    /**
     * Hides the control completely if false.
     */
    public boolean visible;
    /**
     * The current scroll position of the bar
     */
    public int currScroll;
    /**
     * The X Position of the widget
     */
    public int xPosition;

    /**
     * The Y Position of the widget
     */
    public int yPosition;

    /**
     * @param x The X Postition of  the scroll bar
     * @param y The Y Position of the scroll bar
     * @param scrollHeight The max scrollable distance
     */
    public GuiScrollBar(int x, int y, int scrollHeight) {
        this.scrollHeight = scrollHeight;
    }

    public void setVisibility(boolean vis) {
        this.visible = vis;
        this.enabled = vis;
    }

    @Override
    public void drawWidget(Minecraft minecraft) {

    }

    @Override
    public int xPos() {
        return this.xPosition;
    }

    @Override
    public int yPos() {
        return this.yPosition;
    }

    @Override
    public int getWidth() {
        return 12;
    }

    @Override
    public int getHeight() {
        return 15;
    }

    public int getScrollProgress() {
        return (currScroll / scrollHeight) * 100;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}

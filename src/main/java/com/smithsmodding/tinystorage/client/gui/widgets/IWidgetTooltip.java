package com.smithsmodding.tinystorage.client.gui.widgets;

public interface IWidgetTooltip {

    /**
     * returns the tooltip message.
     * @return tooltip message
     */
    String getMessage();

    /**
     * x Location for the object that triggers the tooltip.
     *
     * @return xPosition
     */
    int xTriggerPos();

    /**
     * y Location for the object that triggers the tooltip.
     *
     * @return yPosition
     */
    int yTriggerPos();

    /**
     * Width of the object that triggers the tooltip.
     *
     * @return width
     */
    int getTooltipWidth();

    /**
     * Height for the object that triggers the tooltip.
     *
     * @return height
     */
    int getTooltipHeight();

    /**
     * @return true if button being drawn
     */
    boolean isTooltipVisible();
}

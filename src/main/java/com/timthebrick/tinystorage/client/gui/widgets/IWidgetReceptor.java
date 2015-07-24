package com.timthebrick.tinystorage.client.gui.widgets;

public interface IWidgetReceptor {

    /**
     * The method called to handle the interaction of a widget
     * @param widget The widget being used
     */
    void handleWidgetInteraction(IGuiWidget widget);
}

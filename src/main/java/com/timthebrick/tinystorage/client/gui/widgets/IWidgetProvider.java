package com.timthebrick.tinystorage.client.gui.widgets;

public interface IWidgetProvider {

    /**
     * Add the widgets to the gui
     */
    void addWidgets();

    /**
     * Handle the visibility of the widgets
     */
    void handleWidgetVisibility();

    /**
     * Add a widget to the GUI
     * @param widget The widget to add
     */
    void addWidget(IGuiWidget widget);

    /**
     * Remove a widget from the GUI
     * @param widget The widget to remove
     */
    void removeWidget(IGuiWidget widget);
}

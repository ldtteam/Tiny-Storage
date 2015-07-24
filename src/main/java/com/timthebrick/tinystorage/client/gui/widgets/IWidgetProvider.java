package com.timthebrick.tinystorage.client.gui.widgets;

import com.timthebrick.tinystorage.util.IGuiScreen;

public interface IWidgetProvider extends IGuiScreen {

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
     *
     * @param widget The widget to add
     */
    void addWidget(IGuiWidget widget);

    /**
     * Remove a widget from the GUI
     *
     * @param widget The widget to remove
     */
    void removeWidget(IGuiWidget widget);

    /**
     * Called whenever a widget does something
     */
    void handleWidgetFunctionality(IGuiWidget widget);

}

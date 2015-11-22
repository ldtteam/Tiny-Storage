package com.smithsmodding.tinystorage.client.gui.widgets.settings;

import com.smithsmodding.tinystorage.client.gui.widgets.GuiTextInput;

public interface ICharFilter {

    boolean passesFilter(GuiTextInput textInput, char c);
}

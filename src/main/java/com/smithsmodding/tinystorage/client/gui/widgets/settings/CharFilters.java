package com.smithsmodding.tinystorage.client.gui.widgets.settings;

import com.google.common.base.Strings;
import com.smithsmodding.tinystorage.client.gui.widgets.GuiTextInput;

public class CharFilters {

    public static final ICharFilter FILTER_NUMERIC = new ICharFilter() {
        @Override
        public boolean passesFilter(GuiTextInput tf, char c) {
            return Character.isDigit(c) || (c == '-' && Strings.isNullOrEmpty(tf.getText()));
        }
    };

    public static ICharFilter FILTER_ALPHABETICAL = new ICharFilter() {
        @Override
        public boolean passesFilter(GuiTextInput tf, char c) {
            return Character.isLetter(c);
        }
    };

    public static ICharFilter FILTER_ALPHANUMERIC = new ICharFilter() {
        @Override
        public boolean passesFilter(GuiTextInput tf, char c) {
            return FILTER_NUMERIC.passesFilter(tf, c) || FILTER_ALPHABETICAL.passesFilter(tf, c);
        }
    };
}

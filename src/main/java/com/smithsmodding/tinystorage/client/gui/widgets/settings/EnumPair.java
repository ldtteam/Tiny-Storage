package com.smithsmodding.tinystorage.client.gui.widgets.settings;

public class EnumPair {
    final Enum setting;
    final Enum value;

    public EnumPair(Enum a, Enum b) {
        this.setting = a;
        this.value = b;
    }

    @Override
    public int hashCode() {
        return this.setting.hashCode() ^ this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        EnumPair other = (EnumPair) obj;
        return other.setting == this.setting && other.value == this.value;
    }
}

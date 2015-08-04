package com.timthebrick.tinystorage.common.util;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable {

    void readFromNBT(NBTTagCompound nbtTagCompound);

    void writeToNBT(NBTTagCompound nbtTagCompound);

    String getTagLabel();
}

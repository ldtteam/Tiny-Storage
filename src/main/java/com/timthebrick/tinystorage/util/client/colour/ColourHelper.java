package com.timthebrick.tinystorage.util.client.colour;

import com.timthebrick.tinystorage.util.client.Colours;
import com.timthebrick.tinystorage.common.reference.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ColourHelper {

    public static boolean hasColour (ItemStack itemStack) {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(Names.NBT.DISPLAY) && itemStack.getTagCompound().getCompoundTag(Names.NBT.DISPLAY).hasKey(Names.NBT.COLOR);
    }

    public static int getColour (ItemStack itemStack) {
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
        if (nbtTagCompound == null) {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        } else {
            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Names.NBT.DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(Colours.PURE_WHITE, 16) : displayTagCompound.hasKey(Names.NBT.COLOR) ? displayTagCompound.getInteger(Names.NBT.COLOR) : Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    public static void setColour (ItemStack itemStack, int color) {
        if (itemStack != null) {
            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
            if (nbtTagCompound == null) {
                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }
            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Names.NBT.DISPLAY);
            if (!nbtTagCompound.hasKey(Names.NBT.DISPLAY)) {
                nbtTagCompound.setTag(Names.NBT.DISPLAY, colourTagCompound);
            }
            colourTagCompound.setInteger(Names.NBT.COLOR, color);
        }
    }
}

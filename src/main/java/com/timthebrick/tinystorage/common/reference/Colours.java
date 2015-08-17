package com.timthebrick.tinystorage.common.reference;


import com.timthebrick.tinystorage.util.colour.Colour;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Colours {
    public static final String PURE_WHITE = "ffffff";
    public static final int INV_GRAY = 4210752;
    public static final int TEXT_WHITE = 14737632;
    public static final Colour SELECT_GRAY = new Colour(150, 150, 150);
    public static HashMap<Item, Colour> itemColourMap;
}

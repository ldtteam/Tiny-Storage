package com.smithsmodding.tinystorage.util.client;


import com.smithsmodding.tinystorage.util.client.colour.Colour;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Colours {
    public static final String PURE_WHITE = "ffffff";
    public static final int INV_GRAY = 4210752;
    public static final int TEXT_WHITE = 14737632;
    public static final Colour SELECT_GRAY = new Colour(150, 150, 150);
    public static HashMap<Item, Colour> itemColourMap;

    public static class General {
        public static Colour RED = new Colour(255, 0, 0);
        public static Colour GREEN = new Colour(0, 255, 0);
        public static Colour BLUE = new Colour(0, 0, 255);
        public static Colour YELLOW = new Colour(255, 255, 0);
        public static Colour ORANGE = new Colour(255, 150, 35);
        public static Colour LIGHTBLUE = new Colour(120, 158, 255);
        public static Colour LICHTGREEN = new Colour(102, 255, 122);
        public static Colour ELECTRICBLUE = new Colour(45, 206, 250);
    }
}

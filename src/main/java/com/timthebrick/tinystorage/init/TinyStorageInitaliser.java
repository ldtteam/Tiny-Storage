package com.timthebrick.tinystorage.init;

import com.google.common.base.Stopwatch;
import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.Colours;
import com.timthebrick.tinystorage.util.colour.Colour;
import com.timthebrick.tinystorage.util.colour.ColourSampler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.item.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TinyStorageInitaliser {

    public static void postInit(FMLPostInitializationEvent event) {
        doColourMap();
    }

    private static void doColourMap() {
        Stopwatch watch = Stopwatch.createStarted();
        Iterator<Item> iterator = Item.itemRegistry.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item instanceof ItemBook || item instanceof ItemEditableBook || item instanceof ItemEnchantedBook || item instanceof ItemWritableBook) {
                if (item != null) {
                    if (Colours.itemColourMap == null) {
                        Colours.itemColourMap = new HashMap<Item, Colour>();
                    }
                    Colour colour = ColourSampler.getColourSampleFromItemStack(new ItemStack(item));
                    Colours.itemColourMap.put(item, colour);
                    TinyStorageLog.info("Registering colour mapping of: " + item.getUnlocalizedName() + " to colour: " + colour.toString());
                }
            }
        }
        TinyStorageLog.info("Colour mapping done after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }
}

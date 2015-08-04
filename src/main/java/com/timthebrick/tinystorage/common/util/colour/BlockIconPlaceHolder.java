package com.timthebrick.tinystorage.common.util.colour;

import net.minecraft.util.ResourceLocation;

public class BlockIconPlaceHolder extends IconPlaceHolder {

    public BlockIconPlaceHolder(ResourceLocation tLocation) {
        super(tLocation);
        iIconLocation = new ResourceLocation(tLocation.getResourceDomain(), "textures/blocks/" + tLocation.getResourcePath() + ".png");
    }
}

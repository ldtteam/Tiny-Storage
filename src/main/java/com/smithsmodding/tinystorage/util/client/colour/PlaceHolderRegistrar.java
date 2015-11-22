package com.smithsmodding.tinystorage.util.client.colour;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class PlaceHolderRegistrar implements IIconRegister {

    @Override
    public IIcon registerIcon(String pAddress) {
        return new IconPlaceHolder(new ResourceLocation(pAddress));
    }
}

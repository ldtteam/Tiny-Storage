package com.timthebrick.tinystorage.util.colour;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class BlockPlaceHolderRegistrar implements IIconRegister {

    @Override
    public IIcon registerIcon(String pTextureLocation) {
        return new BlockIconPlaceHolder(new ResourceLocation( pTextureLocation));
    }
}

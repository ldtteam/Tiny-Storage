package com.smithsmodding.tinystorage.common.proxy;

import com.smithsmodding.tinystorage.TinyStorage;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Tim on 19/06/2016.
 */
public abstract class CommonProxy implements IProxy{

    @Override
    public void preInit() {
        TinyStorage.side = Side.SERVER;
    }

    @Override
    public void init() {
        TinyStorage.side = Side.SERVER;
    }

    @Override
    public void postInit() {
        TinyStorage.side = Side.SERVER;
    }

    @Override
    public void registerEventHandlers() {

    }

    @Override
    public String getMinecraftVersion() {
        return Loader.instance().getMinecraftModContainer().getVersion();
    }

    @Override
    public CommonProxy getCommonProxy() {
        return this;
    }

}

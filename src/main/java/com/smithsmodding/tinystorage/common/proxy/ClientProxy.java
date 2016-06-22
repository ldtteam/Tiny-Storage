package com.smithsmodding.tinystorage.common.proxy;

import com.smithsmodding.tinystorage.TinyStorage;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Tim on 19/06/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderingAndTextures() {
    }

    @Override
    public void preInit() {
        TinyStorage.side = Side.CLIENT;
    }

    @Override
    public void init() {
        TinyStorage.side = Side.CLIENT;
    }

    @Override
    public void postInit() {
        TinyStorage.side = Side.CLIENT;
    }

    @Override
    public void registerEventHandlers() {
    }

    @Override
    public void registerKeyBindings() {
    }

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }

}

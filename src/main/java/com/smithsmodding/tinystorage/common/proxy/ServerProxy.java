package com.smithsmodding.tinystorage.common.proxy;

/**
 * Created by Tim on 19/06/2016.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return null;
    }

    @Override
    public void initRenderingAndTextures() {
    }

    @Override
    public void registerKeyBindings() {
    }

}

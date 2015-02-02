package com.timthebrick.tinystorage.proxy;

public interface IProxy
{
    public abstract ClientProxy getClientProxy();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();

    public abstract void registerKeybindings();

}

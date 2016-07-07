package com.smithsmodding.tinystorage.common.proxy;

import com.google.common.collect.ImmutableMap;
import com.smithsmodding.tinystorage.client.proxy.ClientProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

/**
 * Created by Tim on 19/06/2016.
 */
public interface IProxy {

    ClientProxy getClientProxy ();

    CommonProxy getCommonProxy();

    void initItemRendering();

    void initIileRendering();

    void registerEventHandlers ();

    void registerKeyBindings ();

    String getMinecraftVersion ();

    void preInit();

    void init();

    void postInit();

    void onLoadComplete();

    void registerIMCs();

    IAnimationStateMachine loadAnimationStateMachine(ResourceLocation animationLocation, ImmutableMap<String, ITimeValue> parameters);
}

package com.smithsmodding.tinystorage.common.proxy;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.handler.CraftingEventHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

import com.smithsmodding.tinystorage.common.handler.ConfigurationHandler;
import com.smithsmodding.tinystorage.common.handler.PlayerEventHandler;
import com.smithsmodding.tinystorage.common.handler.TickHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

import java.util.HashMap;
import java.util.Map;

public abstract class CommonProxy implements IProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

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

    public void registerEventHandlers() {
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();
        CraftingEventHandler craftingEventHandler = new CraftingEventHandler();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        MinecraftForge.EVENT_BUS.register(craftingEventHandler);
        FMLCommonHandler.instance().bus().register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(craftingEventHandler);
    }

    public String getMinecraftVersion() {
        return Loader.instance().getMinecraftModContainer().getVersion();
    }

    public static void storeEntityData(String name, NBTTagCompound compound) {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name) {
        return extendedEntityData.get(name);
    }

    @Override
    public CommonProxy getCommonProxy() {
        return this;
    }
}

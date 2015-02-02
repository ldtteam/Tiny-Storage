package com.timthebrick.tinystorage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.proxy.CommonProxy;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS, dependencies = "after:ForgeMultipart")
public class TinyStorage {
	
	@Instance(References.MOD_ID)
	public static TinyStorage instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs creativeTab;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerEventHandlers();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		creativeTab = new CreativeTabs("TinyStorage") {
			public Item getTabIconItem() {
				return (new ItemStack(Blocks.chest).getItem());
			}
		};

		//ModBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//CraftingRecipes.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}

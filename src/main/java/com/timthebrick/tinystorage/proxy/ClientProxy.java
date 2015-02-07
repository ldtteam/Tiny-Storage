package com.timthebrick.tinystorage.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.timthebrick.tinystorage.client.renderer.item.ItemRendererTinyChest;
import com.timthebrick.tinystorage.client.renderer.tileentity.TileEntityRendererTinyChest;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.reference.RenderIDs;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void initRenderingAndTextures() {
		RenderIDs.tinyChest = RenderingRegistry.getNextAvailableRenderId();

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestStone), new ItemRendererTinyChest("Stone"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestOakLog), new ItemRendererTinyChest("OakLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestAcaciaLog), new ItemRendererTinyChest("AcaciaLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestBirchLog), new ItemRendererTinyChest("BirchLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestJungleLog), new ItemRendererTinyChest("JungleLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestSpruceLog), new ItemRendererTinyChest("SpruceLog"));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTinyChest.class, new TileEntityRendererTinyChest());
	}

	@Override
	public ClientProxy getClientProxy() {
		return this;
	}

	@Override
	public void registerKeybindings() {
		
	}

}

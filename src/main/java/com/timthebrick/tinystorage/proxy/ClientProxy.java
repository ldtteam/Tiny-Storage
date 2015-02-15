package com.timthebrick.tinystorage.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.timthebrick.tinystorage.client.renderer.item.ItemRendererDraw;
import com.timthebrick.tinystorage.client.renderer.item.ItemRendererFilterChest;
import com.timthebrick.tinystorage.client.renderer.item.ItemRendererTinyChest;
import com.timthebrick.tinystorage.client.renderer.tileentity.TileEntityRendererDraw;
import com.timthebrick.tinystorage.client.renderer.tileentity.TileEntityRendererFilterChest;
import com.timthebrick.tinystorage.client.renderer.tileentity.TileEntityRendererTinyChest;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.reference.RenderIDs;
import com.timthebrick.tinystorage.tileentity.TileEntityDraw;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;
import com.timthebrick.tinystorage.util.PlayerHelper;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void initRenderingAndTextures() {
		RenderIDs.tinyChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.filterChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.draw = RenderingRegistry.getNextAvailableRenderId();

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestStone), new ItemRendererTinyChest("Stone", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestOakLog), new ItemRendererTinyChest("OakLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestAcaciaLog), new ItemRendererTinyChest("AcaciaLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestBirchLog), new ItemRendererTinyChest("BirchLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestJungleLog), new ItemRendererTinyChest("JungleLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestSpruceLog), new ItemRendererTinyChest("SpruceLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestOakPlank), new ItemRendererTinyChest("OakPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestAcaciaPlank), new ItemRendererTinyChest("AcaciaPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestBirchPlank), new ItemRendererTinyChest("BirchPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestJunglePlank), new ItemRendererTinyChest("JunglePlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestSprucePlank), new ItemRendererTinyChest("SprucePlank", false));
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestStoneLocked), new ItemRendererTinyChest("Stone", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestOakLogLocked), new ItemRendererTinyChest("OakLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestAcaciaLogLocked), new ItemRendererTinyChest("AcaciaLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestBirchLogLocked), new ItemRendererTinyChest("BirchLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestJungleLogLocked), new ItemRendererTinyChest("JungleLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestSpruceLogLocked), new ItemRendererTinyChest("SpruceLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestOakPlankLocked), new ItemRendererTinyChest("OakPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestAcaciaPlankLocked), new ItemRendererTinyChest("AcaciaPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestBirchPlankLocked), new ItemRendererTinyChest("BirchPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestJunglePlankLocked), new ItemRendererTinyChest("JunglePlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTinyChestSprucePlankLocked), new ItemRendererTinyChest("SprucePlank", true));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestStone), new ItemRendererFilterChest("Stone"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakLog), new ItemRendererFilterChest("OakLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaLog), new ItemRendererFilterChest("AcaciaLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchLog), new ItemRendererFilterChest("BirchLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJungleLog), new ItemRendererFilterChest("JungleLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSpruceLog), new ItemRendererFilterChest("SpruceLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakPlank), new ItemRendererFilterChest("OakPlank"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaPlank), new ItemRendererFilterChest("AcaciaPlank"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchPlank), new ItemRendererFilterChest("BirchPlank"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJunglePlank), new ItemRendererFilterChest("JunglePlank"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSprucePlank), new ItemRendererFilterChest("SprucePlank"));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockDrawOakLog), new ItemRendererDraw("OakLog"));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTinyChest.class, new TileEntityRendererTinyChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFilterChest.class, new TileEntityRendererFilterChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDraw.class, new TileEntityRendererDraw());
	}

	@Override
	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
		PlayerHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
	}

	@Override
	public void registerKeybindings() {

	}

	@Override
	public ClientProxy getClientProxy() {
		return this;
	}

}

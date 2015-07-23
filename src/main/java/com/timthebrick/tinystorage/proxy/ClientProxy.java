package com.timthebrick.tinystorage.proxy;

import com.timthebrick.tinystorage.client.renderer.item.*;
import com.timthebrick.tinystorage.client.renderer.tileentity.*;
import com.timthebrick.tinystorage.tileentity.implementations.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

import com.timthebrick.tinystorage.client.handler.KeyInputEventHandler;
import com.timthebrick.tinystorage.client.helper.ClientSoundHelper;
import com.timthebrick.tinystorage.client.settings.KeyBindings;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.reference.RenderIDs;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	public void initRenderingAndTextures() {
		RenderIDs.tinyChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.filterChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.draw = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.trashChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.microChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.woolChestSmall = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.woolChestMedium = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.woolChestLarge = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.piggyBank = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.peacefulChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.vacuumChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.clayChestSmall = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.clayChestMedium = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.clayChestLarge = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.quarryChest = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.bookCase = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.impossibleChest = RenderingRegistry.getNextAvailableRenderId();

		// Tiny Chests
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

		// Filter Chests
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestStone), new ItemRendererFilterChest("Stone", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakLog), new ItemRendererFilterChest("OakLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaLog), new ItemRendererFilterChest("AcaciaLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchLog), new ItemRendererFilterChest("BirchLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJungleLog), new ItemRendererFilterChest("JungleLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSpruceLog), new ItemRendererFilterChest("SpruceLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakPlank), new ItemRendererFilterChest("OakPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaPlank), new ItemRendererFilterChest("AcaciaPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchPlank), new ItemRendererFilterChest("BirchPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJunglePlank), new ItemRendererFilterChest("JunglePlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSprucePlank), new ItemRendererFilterChest("SprucePlank", false));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestStoneLocked), new ItemRendererFilterChest("Stone", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakLogLocked), new ItemRendererFilterChest("OakLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaLogLocked), new ItemRendererFilterChest("AcaciaLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchLogLocked), new ItemRendererFilterChest("BirchLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJungleLogLocked), new ItemRendererFilterChest("JungleLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSpruceLogLocked), new ItemRendererFilterChest("SpruceLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestOakPlankLocked), new ItemRendererFilterChest("OakPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestAcaciaPlankLocked), new ItemRendererFilterChest("AcaciaPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestBirchPlankLocked), new ItemRendererFilterChest("BirchPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestJunglePlankLocked), new ItemRendererFilterChest("JunglePlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockFilterChestSprucePlankLocked), new ItemRendererFilterChest("SprucePlank", true));

		// Micro Chests
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestStone), new ItemRendererMicroChest("Stone", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestOakLog), new ItemRendererMicroChest("OakLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestAcaciaLog), new ItemRendererMicroChest("AcaciaLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestBirchLog), new ItemRendererMicroChest("BirchLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestJungleLog), new ItemRendererMicroChest("JungleLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestSpruceLog), new ItemRendererMicroChest("SpruceLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestOakPlank), new ItemRendererMicroChest("OakPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestAcaciaPlank), new ItemRendererMicroChest("AcaciaPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestBirchPlank), new ItemRendererMicroChest("BirchPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestJunglePlank), new ItemRendererMicroChest("JunglePlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestSprucePlank), new ItemRendererMicroChest("SprucePlank", false));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestStoneLocked), new ItemRendererMicroChest("Stone", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestOakLogLocked), new ItemRendererMicroChest("OakLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestAcaciaLogLocked), new ItemRendererMicroChest("AcaciaLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestBirchLogLocked), new ItemRendererMicroChest("BirchLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestJungleLogLocked), new ItemRendererMicroChest("JungleLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestSpruceLogLocked), new ItemRendererMicroChest("SpruceLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestOakPlankLocked), new ItemRendererMicroChest("OakPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestAcaciaPlankLocked), new ItemRendererMicroChest("AcaciaPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestBirchPlankLocked), new ItemRendererMicroChest("BirchPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestJunglePlankLocked), new ItemRendererMicroChest("JunglePlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockMicroChestSprucePlankLocked), new ItemRendererMicroChest("SprucePlank", true));

		//Wool Chests
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestSmall), new ItemRendererWoolChest(0, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestSmallLocked), new ItemRendererWoolChest(0, true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestMedium), new ItemRendererWoolChest(1, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestMediumLocked), new ItemRendererWoolChest(1, true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestLarge), new ItemRendererWoolChest(2, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockWoolChestLargeLocked), new ItemRendererWoolChest(2, true));
		
		//Clay Chests
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestSmall), new ItemRendererClayChest(0, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestSmallLocked), new ItemRendererClayChest(0, true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestMedium), new ItemRendererClayChest(1, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestMediumLocked), new ItemRendererClayChest(1, true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestLarge), new ItemRendererClayChest(2, false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockClayChestLargeLocked), new ItemRendererClayChest(2, true));
		
		//Vacuum Chests
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestStone), new ItemRendererVacuumChest("Stone", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestOakLog), new ItemRendererVacuumChest("OakLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestAcaciaLog), new ItemRendererVacuumChest("AcaciaLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestBirchLog), new ItemRendererVacuumChest("BirchLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestJungleLog), new ItemRendererVacuumChest("JungleLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestSpruceLog), new ItemRendererVacuumChest("SpruceLog", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestOakPlank), new ItemRendererVacuumChest("OakPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestAcaciaPlank), new ItemRendererVacuumChest("AcaciaPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestBirchPlank), new ItemRendererVacuumChest("BirchPlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestJunglePlank), new ItemRendererVacuumChest("JunglePlank", false));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestSprucePlank), new ItemRendererVacuumChest("SprucePlank", false));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestStoneLocked), new ItemRendererVacuumChest("Stone", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestOakLogLocked), new ItemRendererVacuumChest("OakLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestAcaciaLogLocked), new ItemRendererVacuumChest("AcaciaLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestBirchLogLocked), new ItemRendererVacuumChest("BirchLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestJungleLogLocked), new ItemRendererVacuumChest("JungleLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestSpruceLogLocked), new ItemRendererVacuumChest("SpruceLog", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestOakPlankLocked), new ItemRendererVacuumChest("OakPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestAcaciaPlankLocked), new ItemRendererVacuumChest("AcaciaPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestBirchPlankLocked), new ItemRendererVacuumChest("BirchPlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestJunglePlankLocked), new ItemRendererVacuumChest("JunglePlank", true));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockVacuumChestSprucePlankLocked), new ItemRendererVacuumChest("SprucePlank", true));

		//Book Cases
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseOakLog), new ItemRendererBookCase("OakLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseAcaciaLog), new ItemRendererBookCase("AcaciaLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseBirchLog), new ItemRendererBookCase("BirchLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseJungleLog), new ItemRendererBookCase("JungleLog"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseSpruceLog), new ItemRendererBookCase("SpruceLog"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseOakPlank), new ItemRendererBookCase("OakPlank"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseAcaciaPlank), new ItemRendererBookCase("AcaciaPlank"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseBirchPlank), new ItemRendererBookCase("BirchPlank"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseJunglePlank), new ItemRendererBookCase("JunglePlank"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockBookCaseSprucePlank), new ItemRendererBookCase("SprucePlank"));
		
		// Misc
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockTrashChest), new ItemRendererTrashChest());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockPiggyBank), new ItemRendererPiggyBank());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockPeacefulChest), new ItemRendererPeacefulChest());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockQuarryChest), new ItemRendererQuarryChest());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockImpossibleChest), new ItemRendererImpossibleChest(false));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockImpossibleChestLocked), new ItemRendererImpossibleChest(true));

		// TileEntity binding
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTinyChest.class, new TileEntityRendererTinyChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFilterChest.class, new TileEntityRendererFilterChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDraw.class, new TileEntityRendererDraw());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashChest.class, new TileEntityRendererTrashChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMicroChest.class, new TileEntityRendererMicroChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoolChest.class, new TileEntityRendererWoolChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiggyBank.class, new TileEntityRendererPiggyBank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPeacefulChest.class, new TileEntityRendererPeacefulChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVacuumChest.class, new TileEntityRendererVacuumChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClayChest.class, new TileEntityRendererClayChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityQuarryChest.class, new TileEntityRendererQuarryChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBookCase.class, new TileEntityRendererBookCase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityImpossibleChest.class, new TileEntityRendererImpossibleChest());

	}
	
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
	}

	@Override
	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
		ClientSoundHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
	}

	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(KeyBindings.changeMode);
	}

	@Override
	public ClientProxy getClientProxy() {
		return this;
	}

}

package com.timthebrick.tinystorage.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.core.TinyStorageLog;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void init() {
		TinyStorageLog.info("Initialising Crafting Recipes");

		// Storage Upgrades
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemStorageUpgrade, 3, 0), "sss", "l l", "sss", 's', new ItemStack(Items.stick, 1), 'l', new ItemStack(Blocks.log, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

		// Chest Filters
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemChestFilter, 3, 0), "sss", "l l", "sss", 's', new ItemStack(Items.stick, 1), 'l', new ItemStack(Items.string, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));

		// Chest Lock
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemChestLock, 2), " h ", "sds", "sss", 's', new ItemStack(Blocks.stone, 1), 'd', new ItemStack(Items.diamond, 1), 'h', new ItemStack(Blocks.stone_slab));

		// Tiny Chests - Logs
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log2, 1, 0));

		// Tiny Chests - Planks
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 5));

		// Tiny Chests Locked - Logs
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));

		// Tiny Chests Locked - Planks
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 5));

		// Filter Chests - Logs
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log2, 1, 0));

		// Filter Chests - Planks
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 4));

		// Filter Chests Locked - Logs
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));

		// Filter Chests Locked - Planks
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
	}

}

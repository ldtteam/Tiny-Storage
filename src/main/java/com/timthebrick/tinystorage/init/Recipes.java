package com.timthebrick.tinystorage.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.timthebrick.tinystorage.core.TinyStorageLog;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	protected static final String[] dyes = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };

	public static void init() {
		TinyStorageLog.info("Initialising Crafting Recipes");

		// Storage Upgrades
		registerShapedRecipe(new ItemStack(ModItems.itemStorageUpgrade, 3, 0), "sss", "l l", "sss", 's', new ItemStack(Items.stick, 1), 'l', new ItemStack(Blocks.log, 1));
		registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
		registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
		registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

		// Chest Filters
		registerShapedRecipe(new ItemStack(ModItems.itemChestFilter, 3, 0), "sss", "l l", "sss", 's', new ItemStack(Items.stick, 1), 'l', new ItemStack(Items.string, 1));
		registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));
		registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0));
		registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));

		// Chest Lock
		registerShapedRecipe(new ItemStack(ModItems.itemChestLock, 2), " h ", "sds", "sss", 's', new ItemStack(Blocks.stone, 1), 'd', new ItemStack(Items.diamond, 1), 'h', new ItemStack(Blocks.stone_slab));
		
		//Chest Wrench
		registerShapedRecipe(new ItemStack(ModItems.itemDebugTool, 1), "I I", " C ", " I ", 'I', new ItemStack(Items.iron_ingot, 1), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

		// Tiny Chests - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log2, 1, 0));

		// Tiny Chests - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 5));

		// Tiny Chests - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStone, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.stone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStone, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.stone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStone, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.stone, 1, 0));

		// Tiny Chests Locked - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 2));

		// Tiny Chests Locked - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 5));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 2));

		// Tiny Chests Locked - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestStone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestStone, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockTinyChestStone, 1, 2));

		// Filter Chests - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.log2, 1, 0));

		// Filter Chests - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.planks, 1, 4));

		// Filter Chests - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStone, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.stone, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStone, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.stone, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStone, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.stone, 1));

		// Filter Chests Locked - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0));

		// Filter Chests Locked - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4));

		// Filter Chests Locked - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStoneLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStoneLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestStoneLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1));

		// Wool Chests
		{
			// Wool block recipes
			for (int i = 0; i < 16; i++) {
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestSmall, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.wool, 1, 15 - i));
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestMedium, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.wool, 1, 15 - i));
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestLarge, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.wool, 1, 15 - i));
			}

			// Dye recipes
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					if (i != j) {
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmall, 1, j), new ItemStack(ModBlocks.blockWoolChestSmall, 1, i), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMedium, 1, j), new ItemStack(ModBlocks.blockWoolChestMedium, 1, i), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLarge, 1, j), new ItemStack(ModBlocks.blockWoolChestLarge, 1, i), "dye" + dyes[j]));
					}
				}
			}
		}

		// Wool Chests Locked
		{
			// Wool block recipes
			for (int i = 0; i < 16; i++) {
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.wool, i, 15 - i));
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.wool, i, 15 - i));
				registerShapelessRecipe(new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.wool, i, 15 - i));
			}

			// Dye recipes
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					if (i != j) {
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, i), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, i), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, i), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestSmall, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestMedium, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + dyes[j]));
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestLarge, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + dyes[j]));
					}
				}
			}
		}

		// Micro Chests - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestOakLog, 9), new ItemStack(ModBlocks.blockTinyChestOakLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestSpruceLog, 9), new ItemStack(ModBlocks.blockTinyChestSpruceLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestBirchLog, 9), new ItemStack(ModBlocks.blockTinyChestBirchLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestJungleLog, 9), new ItemStack(ModBlocks.blockTinyChestJungleLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestAcaciaLog, 9), new ItemStack(ModBlocks.blockTinyChestAcaciaLog, 1, 0));

		// Micro Chests - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestOakPlank, 9), new ItemStack(ModBlocks.blockTinyChestOakPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestSprucePlank, 9), new ItemStack(ModBlocks.blockTinyChestSprucePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestBirchPlank, 9), new ItemStack(ModBlocks.blockTinyChestBirchPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestJunglePlank, 9), new ItemStack(ModBlocks.blockTinyChestJunglePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestAcaciaPlank, 9), new ItemStack(ModBlocks.blockTinyChestAcaciaPlank, 1, 0));

		// Micro Chests - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestStone, 9), new ItemStack(ModBlocks.blockTinyChestStone, 1, 0));

		// Micro Chests Locked - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestOakLogLocked, 9), new ItemStack(ModBlocks.blockTinyChestOakLogLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestSpruceLogLocked, 9), new ItemStack(ModBlocks.blockTinyChestSpruceLogLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestBirchLogLocked, 9), new ItemStack(ModBlocks.blockTinyChestBirchLogLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestJungleLogLocked, 9), new ItemStack(ModBlocks.blockTinyChestJungleLogLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestAcaciaLogLocked, 9), new ItemStack(ModBlocks.blockTinyChestAcaciaLogLocked, 1, 0));

		// Micro Chests Locked - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestOakPlankLocked, 9), new ItemStack(ModBlocks.blockTinyChestOakPlankLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestSprucePlankLocked, 9), new ItemStack(ModBlocks.blockTinyChestSprucePlankLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestBirchPlank, 9), new ItemStack(ModBlocks.blockTinyChestBirchPlankLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestJunglePlankLocked, 9), new ItemStack(ModBlocks.blockTinyChestJunglePlankLocked, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestAcaciaPlankLocked, 9), new ItemStack(ModBlocks.blockTinyChestAcaciaPlankLocked, 1, 0));

		// Micro Chests Locked - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestStoneLocked, 9), new ItemStack(ModBlocks.blockTinyChestStoneLocked, 1, 0));

		// Trash Chest
		registerShapedRecipe(new ItemStack(ModBlocks.blockTrashChest, 1), "OOO", "OCO", "OOO", 'O', new ItemStack(Blocks.obsidian), 'C', new ItemStack(ModBlocks.blockTinyChestStone, 1, 0));

		// Piggy Banks
		registerShapedRecipe(new ItemStack(ModBlocks.blockPiggyBank, 1, 0), "PPP", "PSP", "PPP", 'P', new ItemStack(Items.porkchop), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
		registerShapedRecipe(new ItemStack(ModBlocks.blockPiggyBank, 1, 1), "PPP", "PSP", "PPP", 'P', new ItemStack(Items.porkchop), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
		registerShapedRecipe(new ItemStack(ModBlocks.blockPiggyBank, 1, 2), "PPP", "PSP", "PPP", 'P', new ItemStack(Items.porkchop), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 2));

		// Peaceful Chest
		registerShapedRecipe(new ItemStack(ModBlocks.blockPeacefulChest, 1, 0), "OSO", "OCO", "OOO", 'O', new ItemStack(Blocks.obsidian), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 0), 'S', new ItemStack(Items.wooden_sword));
		registerShapedRecipe(new ItemStack(ModBlocks.blockPeacefulChest, 1, 1), "OSO", "OCO", "OOO", 'O', new ItemStack(Blocks.obsidian), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1), 'S', new ItemStack(Items.wooden_sword));
		registerShapedRecipe(new ItemStack(ModBlocks.blockPeacefulChest, 1, 2), "OSO", "OCO", "OOO", 'O', new ItemStack(Blocks.obsidian), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 2), 'S', new ItemStack(Items.wooden_sword));

		// Vacuum Chests - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));

		// Vacuum Chests - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.planks, 1, 4), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.planks, 1, 4), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.planks, 1, 5), new ItemStack(Items.ender_pearl, 1));

		// Vacuum Chests - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStone, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStone, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStone, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));

		// Vacuum Chests Locked - Logs
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSpruceLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSpruceLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJungleLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJungleLog, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaLogLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaLog, 1, 2));

		// Vacuum Chests Locked - Planks
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestOakPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestOakPlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestSprucePlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestSprucePlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestBirchPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestBirchPlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestJunglePlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestJunglePlank, 1, 2));

		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 4), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.planks, 1, 5), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestAcaciaPlankLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestAcaciaPlank, 1, 2));

		// Vacuum Chests Locked - Others
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stone, 1, 0), new ItemStack(Items.ender_pearl, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestStone, 1, 0));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestStone, 1, 1));
		registerShapelessRecipe(new ItemStack(ModBlocks.blockVacuumChestStoneLocked, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(ModBlocks.blockVacuumChestStone, 1, 2));

	}

	private static void registerShapelessRecipe(ItemStack output, Object... params) {
		TinyStorageLog.info("Creating shapeless recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
		try {
			GameRegistry.addShapelessRecipe(output, params);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

	private static void registerShapedRecipe(ItemStack output, Object... params) {
		TinyStorageLog.info("Creating shaped recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
		try {
			GameRegistry.addShapedRecipe(output, params);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

}

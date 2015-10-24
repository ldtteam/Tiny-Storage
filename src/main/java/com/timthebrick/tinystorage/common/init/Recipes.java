package com.timthebrick.tinystorage.common.init;

import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init() {
        TinyStorageLog.info("Initialising Crafting Recipes");

        // Storage Upgrades
        registerShapedOreRecipe(new ItemStack(ModItems.itemStorageUpgrade, 3, 0), "sss", "l l", "sss", 's', "stickWood", 'l', "logWood");
        registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
        registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
        registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
        registerShapedRecipe(new ItemStack(ModItems.itemStorageUpgrade, 1, 3), "DSD", "SPS", "DSD", 'D', new ItemStack(Items.diamond), 'P', new ItemStack(Items.ender_pearl), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 2));
        registerShapelessRecipe(new ItemStack(ModItems.itemStorageUpgrade, 3, 4), new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

        // Chest Filters
        registerShapedOreRecipe(new ItemStack(ModItems.itemChestFilter, 3, 0), "sss", "l l", "sss", 's', "stickWood", 'l', new ItemStack(Items.string, 1));
        registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));
        registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 0));
        registerShapelessRecipe(new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0));

        // Chest Lock
        registerShapedRecipe(new ItemStack(ModItems.itemChestLock, 2), " h ", "sds", "sss", 's', new ItemStack(Blocks.stone, 1), 'd', new ItemStack(Items.diamond, 1), 'h', new ItemStack(Blocks.stone_slab));

        // Chest Wrench
        registerShapedOreRecipe(new ItemStack(ModItems.itemDebugTool, 1), "I I", " C ", " I ", 'I', "ingotIron", 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

        //Storage Bags
        registerShapedRecipe(new ItemStack(ModItems.itemStorageBag, 1, 0), " W ", "WCW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 0));
        registerShapedRecipe(new ItemStack(ModItems.itemStorageBag, 1, 1), " W ", "WCW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModItems.itemStorageBag, 1, 2), " W ", "WCW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 2));

        //Friend Setter
        registerShapedOreRecipe(new ItemStack(ModItems.itemFriendSetter, 4, 0), "GPG", "PSP", "GPG", 'G', "nuggetGold", 'P', new ItemStack(Items.paper), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

        // Tiny Chests
        registerLockableChestRecipes(ModBlocks.blockTinyChestOakLog, ModBlocks.blockTinyChestOakLogLocked, Blocks.log, 0);
        registerLockableChestRecipes(ModBlocks.blockTinyChestSpruceLog, ModBlocks.blockTinyChestSpruceLogLocked, Blocks.log, 1);
        registerLockableChestRecipes(ModBlocks.blockTinyChestBirchLog, ModBlocks.blockTinyChestBirchLogLocked, Blocks.log, 2);
        registerLockableChestRecipes(ModBlocks.blockTinyChestJungleLog, ModBlocks.blockTinyChestJungleLogLocked, Blocks.log, 3);
        registerLockableChestRecipes(ModBlocks.blockTinyChestAcaciaLog, ModBlocks.blockTinyChestAcaciaLogLocked, Blocks.log2, 0);
        registerLockableChestRecipes(ModBlocks.blockTinyChestOakPlank, ModBlocks.blockTinyChestOakPlankLocked, Blocks.planks, 0);
        registerLockableChestRecipes(ModBlocks.blockTinyChestSprucePlank, ModBlocks.blockTinyChestSprucePlankLocked, Blocks.planks, 1);
        registerLockableChestRecipes(ModBlocks.blockTinyChestBirchPlank, ModBlocks.blockTinyChestBirchPlankLocked, Blocks.planks, 2);
        registerLockableChestRecipes(ModBlocks.blockTinyChestJunglePlank, ModBlocks.blockTinyChestJunglePlankLocked, Blocks.planks, 3);
        registerLockableChestRecipes(ModBlocks.blockTinyChestAcaciaPlank, ModBlocks.blockTinyChestAcaciaPlankLocked, Blocks.planks, 4);
        registerLockableChestRecipes(ModBlocks.blockTinyChestStone, ModBlocks.blockTinyChestStoneLocked, Blocks.stone);
        registerLockableChestRecipes(ModBlocks.blockTinyChestEndStone, ModBlocks.blockTinyChestEndStoneLocked, Blocks.end_stone);
        registerLockableChestRecipes(ModBlocks.blockTinyChestNetherRack, ModBlocks.blockTinyChestNetherRackLocked, Blocks.netherrack);

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
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStone, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStone, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStone, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRack, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(Blocks.netherrack, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRack, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(Blocks.netherrack, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRack, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(Blocks.netherrack, 1));

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
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStoneLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStoneLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestEndStoneLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.end_stone, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRackLocked, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestFilter, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.netherrack, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRackLocked, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestFilter, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.netherrack, 1));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockFilterChestNetherRackLocked, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestFilter, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.netherrack, 1));

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
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmall, 1, j), new ItemStack(ModBlocks.blockWoolChestSmall, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMedium, 1, j), new ItemStack(ModBlocks.blockWoolChestMedium, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLarge, 1, j), new ItemStack(ModBlocks.blockWoolChestLarge, 1, i), "dye" + References.dyes[j]);
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
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestSmall, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestMedium, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockWoolChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockWoolChestLarge, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
                    }
                }
            }
        }

        // Clay Chests
        {
            // Clay block recipes
            for (int i = 0; i < 16; i++) {
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestSmall, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i));
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestMedium, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i));
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestLarge, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i));
            }

            // Dye recipes
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (i != j) {
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestSmall, 1, j), new ItemStack(ModBlocks.blockClayChestSmall, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestMedium, 1, j), new ItemStack(ModBlocks.blockClayChestMedium, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestLarge, 1, j), new ItemStack(ModBlocks.blockClayChestLarge, 1, i), "dye" + References.dyes[j]);
                    }
                }
            }
        }

        // Clay Chests Locked
        {
            // Clay block recipes
            for (int i = 0; i < 16; i++) {
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestSmallLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stained_hardened_clay, i, 15 - i));
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestMediumLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stained_hardened_clay, i, 15 - i));
                registerShapelessRecipe(new ItemStack(ModBlocks.blockClayChestLargeLocked, 1, i), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(Blocks.stained_hardened_clay, i, 15 - i));
            }

            // Dye recipes
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (i != j) {
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockClayChestSmallLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockClayChestMediumLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockClayChestLargeLocked, 1, i), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestSmallLocked, 1, j), new ItemStack(ModBlocks.blockClayChestSmall, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestMediumLocked, 1, j), new ItemStack(ModBlocks.blockClayChestMedium, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
                        registerShapelessOreRecipe(new ItemStack(ModBlocks.blockClayChestLargeLocked, 1, j), new ItemStack(ModBlocks.blockClayChestLarge, 1, i), new ItemStack(ModItems.itemChestLock, 1), "dye" + References.dyes[j]);
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
        registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestEndStone, 9), new ItemStack(ModBlocks.blockTinyChestEndStone, 1, 0));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestNetherRack, 9), new ItemStack(ModBlocks.blockTinyChestNetherRack, 1, 0));

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
        registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestEndStoneLocked, 9), new ItemStack(ModBlocks.blockTinyChestEndStoneLocked, 1, 0));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockMicroChestNetherRackLocked, 9), new ItemStack(ModBlocks.blockTinyChestNetherRackLocked, 1, 0));

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

        // Vacuum Chests
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestOakLog, ModBlocks.blockVacuumChestOakLogLocked, Blocks.log, 0);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestSpruceLog, ModBlocks.blockVacuumChestSpruceLogLocked, Blocks.log, 1);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestBirchLog, ModBlocks.blockVacuumChestBirchLogLocked, Blocks.log, 1);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestJungleLog, ModBlocks.blockVacuumChestJungleLogLocked, Blocks.log, 2);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestAcaciaLog, ModBlocks.blockVacuumChestAcaciaLogLocked, Blocks.log2, 0);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestOakPlank, ModBlocks.blockVacuumChestOakPlankLocked, Blocks.planks, 0);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestSprucePlank, ModBlocks.blockVacuumChestSprucePlankLocked, Blocks.planks, 1);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestBirchPlank, ModBlocks.blockVacuumChestBirchPlankLocked, Blocks.planks, 2);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestJunglePlank, ModBlocks.blockVacuumChestJunglePlankLocked, Blocks.planks, 3);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestAcaciaPlank, ModBlocks.blockVacuumChestAcaciaPlankLocked, Blocks.planks, 4);
        registerLockableVacuumChestRecipes(ModBlocks.blockVacuumChestStone, ModBlocks.blockVacuumChestStoneLocked, Blocks.stone);

        //Quarry Chests
        registerShapedRecipe(new ItemStack(ModBlocks.blockQuarryChest, 1, 0), "DED", "DSD", "DDD", 'D', new ItemStack(ModBlocks.blockDenseBlock, 1, 0), 'E', new ItemStack(Items.ender_pearl), 'S', new ItemStack(ModBlocks.blockVacuumChestStone, 1, 0));
        registerShapedRecipe(new ItemStack(ModBlocks.blockQuarryChest, 1, 1), "DED", "DSD", "DDD", 'D', new ItemStack(ModBlocks.blockDenseBlock, 1, 1), 'E', new ItemStack(Items.ender_pearl), 'S', new ItemStack(ModBlocks.blockVacuumChestStone, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockQuarryChest, 1, 2), "DED", "DSD", "DDD", 'D', new ItemStack(ModBlocks.blockDenseBlock, 1, 2), 'E', new ItemStack(Items.ender_pearl), 'S', new ItemStack(ModBlocks.blockVacuumChestStone, 1, 2));

        //Dense Blocks
        registerShapedRecipe(new ItemStack(ModBlocks.blockDenseBlock, 1, 0), "OSO", "OEO", "OSO", 'O', new ItemStack(Blocks.obsidian), 'S', new ItemStack(Blocks.cobblestone), 'E', new ItemStack(Items.ender_pearl));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDenseBlock, 1, 1), "DDD", "DDD", "DDD", 'D', new ItemStack(ModBlocks.blockDenseBlock, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDenseBlock, 1, 2), "DDD", "DDD", "DDD", 'D', new ItemStack(ModBlocks.blockDenseBlock, 1, 2));

        //Book Cases
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseOakLog), " S ", " C ", " S ", 'S', new ItemStack(Blocks.log, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseSpruceLog), " S ", " C ", " S ", 'S', new ItemStack(Blocks.log, 1, 1), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseBirchLog), " S ", " C ", " S ", 'S', new ItemStack(Blocks.log, 1, 2), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseJungleLog), " S ", " C ", " S ", 'S', new ItemStack(Blocks.log, 1, 3), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseAcaciaLog), " S ", " C ", " S ", 'S', new ItemStack(Blocks.log2, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseOakPlank), " S ", "SCS", " S ", 'S', new ItemStack(Blocks.wooden_slab, 1, 0), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseSprucePlank), " S ", "SCS", " S ", 'S', new ItemStack(Blocks.wooden_slab, 1, 1), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseBirchPlank), " S ", "SCS", " S ", 'S', new ItemStack(Blocks.wooden_slab, 1, 2), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseJunglePlank), " S ", "SCS", " S ", 'S', new ItemStack(Blocks.wooden_slab, 1, 3), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));
        registerShapedRecipe(new ItemStack(ModBlocks.blockBookCaseAcaciaPlank), " S ", "SCS", " S ", 'S', new ItemStack(Blocks.wooden_slab, 1, 4), 'C', new ItemStack(ModItems.itemStorageUpgrade, 1, 1));

        //Draws
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawOakLog), " S ", "LSL", " S ", 'L', new ItemStack(Blocks.log, 1, 0), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawSpruceLog), " S ", "LSL", " S ", 'L', new ItemStack(Blocks.log, 1, 1), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawBirchLog), " S ", "LSL", " S ", 'L', new ItemStack(Blocks.log, 1, 2), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawJungleLog), " S ", "LSL", " S ", 'L', new ItemStack(Blocks.log, 1, 3), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawAcaciaLog), " S ", "LSL", " S ", 'L', new ItemStack(Blocks.log2, 1, 0), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawOakPlank), "LSL", " S ", "LSL", 'L', new ItemStack(Blocks.wooden_slab, 1, 0), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawSprucePlank), "LSL", " S ", "LSL", 'L', new ItemStack(Blocks.wooden_slab, 1, 1), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawBirchPlank), "LSL", " S ", "LSL", 'L', new ItemStack(Blocks.wooden_slab, 1, 2), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawJunglePlank), "LSL", " S ", "LSL", 'L', new ItemStack(Blocks.wooden_slab, 1, 3), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));
        registerShapedRecipe(new ItemStack(ModBlocks.blockDrawAcaciaPlank), "LSL", " S ", "LSL", 'L', new ItemStack(Blocks.wooden_slab, 1, 4), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 4));

        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawOakLogLocked), new ItemStack(ModBlocks.blockDrawOakLog), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawSpruceLogLocked), new ItemStack(ModBlocks.blockDrawSpruceLog), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawBirchLogLocked), new ItemStack(ModBlocks.blockDrawBirchLog), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawJungleLogLocked), new ItemStack(ModBlocks.blockDrawJungleLog), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawAcaciaLogLocked), new ItemStack(ModBlocks.blockDrawAcaciaLog), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawOakPlankLocked), new ItemStack(ModBlocks.blockDrawOakPlank), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawSprucePlankLocked), new ItemStack(ModBlocks.blockDrawSprucePlank), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawBirchPlankLocked), new ItemStack(ModBlocks.blockDrawBirchPlank), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawJunglePlankLocked), new ItemStack(ModBlocks.blockDrawJunglePlank), new ItemStack(ModItems.itemChestLock));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockDrawAcaciaPlankLocked), new ItemStack(ModBlocks.blockDrawAcaciaPlank), new ItemStack(ModItems.itemChestLock));

        //Blue Log
        registerShapedOreRecipe(new ItemStack(ModBlocks.blockBlueLog, 8, 0), "LLL", "LDL", "LLL", 'L', "logWood", 'D', "dyeBlue");

        //Impossible Chest
        registerShapedRecipe(new ItemStack(ModBlocks.blockImpossibleChest), "LLL", "LSL", "LLL", 'L', new ItemStack(ModBlocks.blockBlueLog), 'S', new ItemStack(ModItems.itemStorageUpgrade, 1, 3));
        registerShapelessRecipe(new ItemStack(ModBlocks.blockImpossibleChestLocked), new ItemStack(ModBlocks.blockImpossibleChest), new ItemStack(ModItems.itemChestLock));
    }

    private static void registerShapelessOreRecipe(ItemStack output, Object... recipe) {
        //TinyStorageLog.info("Creating shapeless ore recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
        try {
            GameRegistry.addRecipe(new ShapelessOreRecipe(output, recipe));
        } catch (Exception e) {
            TinyStorageLog.error(e);
        }
    }

    private static void registerShapedOreRecipe(ItemStack output, Object... recipe) {
        //TinyStorageLog.info("Creating shaped ore recipe recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
        try {
            GameRegistry.addRecipe(new ShapedOreRecipe(output, recipe));
        } catch (Exception e) {
            TinyStorageLog.error(e);
        }
    }

    private static void registerLockableChestRecipes(Block normalOutput, Block lockedOutput, Block material) {
        registerLockableChestRecipes(normalOutput, lockedOutput, material, 0);
    }

    private static void registerLockableChestRecipes(Block normalOutput, Block lockedOutput, Block material, int matMeta) {
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(material, 1, matMeta));
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(material, 1, matMeta));
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(material, 1, matMeta));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 0));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 1));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 2));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta));
    }

    private static void registerLockableVacuumChestRecipes(Block normalOutput, Block lockedOutput, Block material) {
        registerLockableChestRecipes(normalOutput, lockedOutput, material, 0);
    }

    private static void registerLockableVacuumChestRecipes(Block normalOutput, Block lockedOutput, Block material, int matMeta) {
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
        registerShapelessRecipe(new ItemStack(normalOutput, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 0));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 1));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(normalOutput, 1, 2));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 0), new ItemStack(ModItems.itemStorageUpgrade, 1, 0), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 1), new ItemStack(ModItems.itemStorageUpgrade, 1, 1), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
        registerShapelessRecipe(new ItemStack(lockedOutput, 1, 2), new ItemStack(ModItems.itemStorageUpgrade, 1, 2), new ItemStack(ModItems.itemChestLock, 1), new ItemStack(material, 1, matMeta), new ItemStack(Items.ender_pearl, 1));
    }

    private static void registerShapelessRecipe(ItemStack output, Object... params) {
        //TinyStorageLog.info("Creating shapeless recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
        try {
            GameRegistry.addShapelessRecipe(output, params);
        } catch (Exception e) {
            TinyStorageLog.error(e);
        }
    }

    private static void registerShapedRecipe(ItemStack output, Object... params) {
        //TinyStorageLog.info("Creating shaped recipe for: " + output.getDisplayName() + " with MetaData: " + output.getItemDamage());
        try {
            GameRegistry.addShapedRecipe(output, params);
        } catch (Exception e) {
            TinyStorageLog.error(e);
        }
    }

}
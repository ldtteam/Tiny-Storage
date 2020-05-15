package com.timthebrick.tinystorage.common.init;

import com.timthebrick.tinystorage.common.block.storage.chests.BlockTinyChest;
import com.timthebrick.tinystorage.common.core.ModSetup;
import com.timthebrick.tinystorage.common.reference.ChestSize;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTinyChest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    protected static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, References.MOD_ID);
    protected static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, References.MOD_ID);
    protected static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, References.MOD_ID);
    protected static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, References.MOD_ID);
    protected static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, References.MOD_ID);
    protected static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, References.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Blocks
    public static RegistryObject<BlockTinyChest> TINY_CHEST_STONE_SMALL = BLOCKS.register(generateChestName(Names.Blocks.TINY_CHEST, "stone", ChestSize.SMALL), () -> new BlockTinyChest(Block.Properties.create(Material.ROCK), ChestSize.SMALL));

    // Item Blocks
    public static final RegistryObject<Item> TINY_CHEST_STONE_SMALL_ITEM = ITEMS.register(generateChestName(Names.Blocks.TINY_CHEST, "stone", ChestSize.SMALL), () -> new BlockItem(TINY_CHEST_STONE_SMALL.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    // Items

    // Tile Entities
    public static RegistryObject<TileEntityType<?>> TINY_CHEST_STONE_TE = TILES.register(Names.TileEntities.TINY_CHEST + "." + "stone", () -> TileEntityType.Builder.create(TileEntityTinyChest::new, TINY_CHEST_STONE_SMALL.get()).build(null));

    // Containers

    // Helper Methods
    private static String generateChestName(String baseName, String subName, ChestSize size) {
        return (baseName + "." + "." + subName + "." + size).toLowerCase();
    }
}

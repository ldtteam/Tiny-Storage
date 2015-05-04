package com.timthebrick.tinystorage.handler;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.block.BlockPiggyBank;
import com.timthebrick.tinystorage.block.BlockWoolChest;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityPiggyBank;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityWoolChest;
import com.timthebrick.tinystorage.util.PlayerHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerEventHandler {

	@SubscribeEvent
	public void playerInteract(PlayerInteractEvent event) {
		if (!event.entityPlayer.worldObj.isRemote) {
			World world = event.entityPlayer.worldObj;
			int x = event.x;
			int y = event.y;
			int z = event.z;
			EntityPlayer player = event.entityPlayer;
			if (!PlayerHelper.isPlayerFake(player)) {
				if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
					if(TinyStorage.instance.developmentEnvironment){
						TinyStorageLog.info("Trying to handle a PlayerInteractEvent - Right Click Block");
					}
					Block block = world.getBlock(x, y, z);
					TileEntity te = world.getTileEntity(x, y, z);
					if (te instanceof TileEntityTinyStorage) {
						TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) te;

						if (block instanceof BlockWoolChest) {
							if (!tileEntity.hasUniqueOwner() || (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName()))) {
								if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemDye) {
									if (player.getHeldItem().getItemDamage() != world.getBlockMetadata(x, y, z)) {
										world.setBlockMetadataWithNotify(x, y, z, player.getHeldItem().getItemDamage(), 3);
										player.getHeldItem().stackSize--;
									}
								}
							}
						}
						// Any more interactions go here
					}
				}
				if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {

				}
			}
		}
	}

}

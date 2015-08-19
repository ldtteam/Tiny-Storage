package com.timthebrick.tinystorage.common.handler;

import com.timthebrick.tinystorage.common.reference.Messages;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.block.storage.chests.BlockClayChest;
import com.timthebrick.tinystorage.common.block.storage.chests.BlockWoolChest;
import com.timthebrick.tinystorage.common.core.VersionChecker;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.common.PlayerHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerEventHandler {

	private boolean nagged;

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
					if (TinyStorage.instance.developmentEnvironment) {
						//TinyStorageLog.info("Trying to handle a PlayerInteractEvent - Right Click Block");
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
						} else if (block instanceof BlockClayChest) {
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
				} else if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
					if (TinyStorage.instance.developmentEnvironment) {
						//TinyStorageLog.info("Trying to handle a PlayerInteractEvent - Left Click Block");
					}
				} else if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
					if (TinyStorage.instance.developmentEnvironment) {
						//TinyStorageLog.info("Trying to handle a PlayerInteractEvent - Right Click Air");
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		if (nagged) {
			return;
		}
		if (VersionChecker.needsUpdateNoticeAndMarkAsSeen()) {
			PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_UPDATE));
			PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_VERSION, VersionChecker.getRecommendedVersion(), TinyStorage.proxy.getMinecraftVersion()));
			PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_DOWNLOAD));
			PlayerHelper.sendChatMessage(player, " ");
		}
		nagged = true;
	}

}

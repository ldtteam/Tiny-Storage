package com.smithsmodding.tinystorage.common.handler;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.block.storage.chests.BlockClayChest;
import com.smithsmodding.tinystorage.common.block.storage.chests.BlockWoolChest;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.core.VersionChecker;
import com.smithsmodding.tinystorage.common.entity.ExtendedPropertyGlobalFriends;
import com.smithsmodding.tinystorage.common.proxy.CommonProxy;
import com.smithsmodding.tinystorage.common.reference.Messages;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.smithsmodding.tinystorage.util.common.PlayerHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

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
                    Block block = world.getBlock(x, y, z);
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof TileEntityTinyStorage) {
                        TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) te;
                        if (block instanceof BlockWoolChest) {
                            if (!tileEntity.hasUniqueOwner() || (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName()))) {
                                if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemDye) {
                                    if (player.getHeldItem().getItemDamage() != world.getBlockMetadata(x, y, z)) {
                                        world.setBlockMetadataWithNotify(x, y, z, player.getHeldItem().getItemDamage(), 3);
                                        player.getHeldItem().stackSize--;
                                    }
                                }
                            }
                        } else if (block instanceof BlockClayChest) {
                            if (!tileEntity.hasUniqueOwner() || (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName()))) {
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
                } else if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
                }
            }
        }
    }

    @SubscribeEvent
    public void playerLoggedIn(PlayerLoggedInEvent event) {
        TinyStorageLog.info("Updating player UUID list");
        EntityPlayer player = event.player;

        if (!TinyStorage.instance.playerUUIDMap.containsKey(player.getGameProfile().getId())) {
            TinyStorage.instance.playerUUIDMap.put(player.getGameProfile().getId(), UsernameCache.getLastKnownUsername(player.getGameProfile().getId()));
            TinyStorage.instance.playerUUIDList.add(player.getGameProfile().getId().toString());
        }

        if (nagged) {
            return;
        }
        if (VersionChecker.needsUpdateNoticeAndMarkAsSeen()) {
            PlayerHelper.sendChatMessage(player, " ");
            PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_UPDATE));
            PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_VERSION, VersionChecker.getRecommendedVersion(), TinyStorage.proxy.getMinecraftVersion()));
            PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.MOD_DOWNLOAD));
            PlayerHelper.sendChatMessage(player, " ");
        }
        nagged = true;
    }

    @SubscribeEvent
    public void playerLoggedOut(PlayerLoggedOutEvent event) {
    }

    @SubscribeEvent
    public void clientLeaveServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        TinyStorageLog.info("Clearing player UUID list");
        TinyStorage.instance.playerUUIDList.clear();
        TinyStorage.instance.playerUUIDMap.clear();
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && ExtendedPropertyGlobalFriends.get((EntityPlayer) event.entity) == null) {
            ExtendedPropertyGlobalFriends.register((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
            ExtendedPropertyGlobalFriends.saveProxyData((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
            ExtendedPropertyGlobalFriends.loadProxyData((EntityPlayer) event.entity);
        }
    }
}

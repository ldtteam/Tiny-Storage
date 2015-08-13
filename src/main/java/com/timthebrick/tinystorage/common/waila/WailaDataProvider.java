package com.timthebrick.tinystorage.common.waila;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTinyChest;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof TileEntityTinyStorage) {
            TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) accessor.getTileEntity();
            if (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(accessor.getPlayer().getUniqueID().toString() + accessor.getPlayer().getDisplayName())) {
                if (tileEntity.getAccessMode() == AccessMode.DISABLED) {
                    currenttip.add(0, StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_BLOCKED));
                } else if (tileEntity.getAccessMode() == AccessMode.INPUT_ONLY) {
                    currenttip.add(0, StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_INPUT_ONLY));
                } else if (tileEntity.getAccessMode() == AccessMode.OUTPUT_ONLY) {
                    currenttip.add(0, StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_OUTPUT_ONLY));
                } else if (tileEntity.getAccessMode() == AccessMode.INPUT_OUTPUT) {
                    currenttip.add(0, StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.ButtonTooltips.ACCESS_MODE_BOTH));
                }
            }
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return null;
    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        TinyStorageLog.info("Registering Waila Data Providers");
        registrar.registerBodyProvider(new WailaDataProvider(), TileEntityTinyStorage.class);
    }

}

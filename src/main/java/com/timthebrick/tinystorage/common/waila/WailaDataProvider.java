package com.timthebrick.tinystorage.common.waila;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityBookCase;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityDraw;
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
    public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof TileEntityTinyStorage) {
            TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) accessor.getTileEntity();
            if (tileEntity instanceof TileEntityDraw || tileEntity instanceof TileEntityBookCase) {
                currentTip.add(StatCollector.translateToLocal(tileEntity.getInventoryName()));
            } else {
                switch (tileEntity.getState()) {
                    case 0:
                        currentTip.add(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + StatCollector.translateToLocal(Messages.WailaTooltips.BLOCK_SMALL));
                    case 1:
                        currentTip.add(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + StatCollector.translateToLocal(Messages.WailaTooltips.BLOCK_MEDIUM));
                    case 2:
                        currentTip.add(StatCollector.translateToLocal(tileEntity.getInventoryName()) + " - " + StatCollector.translateToLocal(Messages.WailaTooltips.BLOCK_LARGE));
                }
            }
        }
        return currentTip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof TileEntityTinyStorage) {
            TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) accessor.getTileEntity();
            if (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(accessor.getPlayer().getUniqueID().toString() + accessor.getPlayer().getDisplayName())) {
                if (tileEntity.getAccessMode() == AccessMode.DISABLED) {
                    currentTip.add(StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_BLOCKED));
                } else if (tileEntity.getAccessMode() == AccessMode.INPUT_ONLY) {
                    currentTip.add(StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_INPUT_ONLY));
                } else if (tileEntity.getAccessMode() == AccessMode.OUTPUT_ONLY) {
                    currentTip.add(StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_OUTPUT_ONLY));
                } else if (tileEntity.getAccessMode() == AccessMode.INPUT_OUTPUT) {
                    currentTip.add(StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_TITLE) + ": " + StatCollector.translateToLocal(Messages.WailaTooltips.ACCESS_MODE_BOTH));
                }
            } else {
                currentTip.add(StatCollector.translateToLocal(Messages.WailaTooltips.CHEST_NOT_OWNED) + ": " + tileEntity.getOwner());
            }
        }
        return currentTip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currentTip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return null;
    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        TinyStorageLog.info("Registering Waila Data Providers");
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityTinyStorage.class);
        registrar.registerBodyProvider(new WailaDataProvider(), TileEntityTinyStorage.class);
        registrar.registerTailProvider(new WailaDataProvider(), TileEntityTinyStorage.class);
    }

}

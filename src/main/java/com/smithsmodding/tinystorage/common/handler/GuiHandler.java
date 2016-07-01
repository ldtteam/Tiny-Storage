package com.smithsmodding.tinystorage.common.handler;

import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.client.gui.GuiTinyStorage;
import com.smithsmodding.tinystorage.common.inventory.ContainerTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Tim on 22/06/2016.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == References.GUIs.TINY_CHEST.ordinal()) {
            TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerTinyStorage(tileEntity, player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == References.GUIs.TINY_CHEST.ordinal()) {
            TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) world.getTileEntity(new BlockPos(x, y, z));
            return new GuiTinyStorage(new ContainerTinyStorage(tileEntity, player));
        }
        return null;
    }
}

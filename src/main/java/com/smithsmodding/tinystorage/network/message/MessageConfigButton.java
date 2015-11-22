package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.BooleanMode;
import com.smithsmodding.tinystorage.common.inventory.ContainerTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityTrashChest;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

import com.smithsmodding.tinystorage.client.gui.widgets.settings.AccessMode;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.smithsmodding.tinystorage.util.common.EnumHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigButton implements IMessage, IMessageHandler<MessageConfigButton, IMessage> {

    private ButtonSettings option;
    private float xCoord, yCoord, zCoord;
    private boolean rotationDirection;

    public MessageConfigButton() {
    }

    public MessageConfigButton(EntityPlayer player, ButtonSettings option, boolean rotationDirection, float xCoord, float yCoord, float zCoord) {
        this.option = option;
        this.rotationDirection = rotationDirection;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.option = ButtonSettings.values()[byteBuf.readInt()];
        this.rotationDirection = byteBuf.readBoolean();
        this.xCoord = byteBuf.readFloat();
        this.yCoord = byteBuf.readFloat();
        this.zCoord = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(option.ordinal());
        byteBuf.writeBoolean(rotationDirection);
        byteBuf.writeFloat(xCoord);
        byteBuf.writeFloat(yCoord);
        byteBuf.writeFloat(zCoord);
    }

    @Override
    public IMessage onMessage(MessageConfigButton event, MessageContext ctx) {
        Container container = ctx.getServerHandler().playerEntity.openContainer;
        if (container instanceof ContainerTinyStorage) {
            TileEntity entity = ((ContainerTinyStorage) container).tileEntityTinyStorage;
            if (entity != null && entity instanceof TileEntityTinyStorage) {
                TileEntityTinyStorage te = (TileEntityTinyStorage) entity;
                if (event.option.ordinal() == ButtonSettings.AUTOMATED_SIDE_ACCESS.ordinal()) {
                    Enum<?> newState = EnumHelper.rotateEnum(te.accessMode, event.rotationDirection, event.option.getPossibleValues());
                    te.accessMode = AccessMode.values()[newState.ordinal()];
                    te.markDirty();
                    te.getWorldObj().markBlockForUpdate((int) event.xCoord, (int) event.yCoord, (int) event.zCoord);
                } else if (event.option.ordinal() == ButtonSettings.DELETE_LAST_STACK.ordinal() && te instanceof TileEntityTrashChest) {
                    Enum<?> newState = EnumHelper.rotateEnum(((TileEntityTrashChest) te).deleteStack, event.rotationDirection, event.option.getPossibleValues());
                    ((TileEntityTrashChest) te).deleteStack = BooleanMode.values()[newState.ordinal()];
                    te.markDirty();
                    te.getWorldObj().markBlockForUpdate((int) event.xCoord, (int) event.yCoord, (int) event.zCoord);
                }

            }
        }
        return null;
    }

}

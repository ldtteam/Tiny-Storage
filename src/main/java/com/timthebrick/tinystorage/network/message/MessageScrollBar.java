package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityImpossibleChest;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageScrollBar implements IMessage, IMessageHandler<MessageScrollBar, IMessage> {

    private EntityPlayer player;
    private float xCoord, yCoord, zCoord;
    private int scrollPos;

    public MessageScrollBar() {
    }

    public MessageScrollBar(EntityPlayer player, int scrollPos, float xCoord, float yCoord, float zCoord) {
        this.player = player;
        this.scrollPos = scrollPos;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.scrollPos = byteBuf.readInt();
        this.xCoord = byteBuf.readFloat();
        this.yCoord = byteBuf.readFloat();
        this.zCoord = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(scrollPos);
        byteBuf.writeFloat(xCoord);
        byteBuf.writeFloat(yCoord);
        byteBuf.writeFloat(zCoord);
    }

    @Override
    public IMessage onMessage(MessageScrollBar event, MessageContext context) {
        if (!FMLClientHandler.instance().getServer().getEntityWorld().isRemote) {
            World world = FMLClientHandler.instance().getServer().getEntityWorld();
            TileEntity entity = world.getTileEntity((int) event.xCoord, (int) event.yCoord, (int) event.zCoord);
            if (entity != null && entity instanceof TileEntityImpossibleChest) {
                ((TileEntityImpossibleChest) entity).handleWidgetInteraction(event.scrollPos);
            }
        }
        return null;
    }
}

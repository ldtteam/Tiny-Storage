package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityQuarryChest;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageSpawnParticle implements IMessage, IMessageHandler<MessageSpawnParticle, IMessage> {

    private EntityPlayer player;
    private float xCoord, yCoord, zCoord;
    private float dX, dY, dZ;

    public MessageSpawnParticle() {
    }

    public MessageSpawnParticle(EntityPlayer player,float xCoord, float yCoord, float zCoord, float dX, float dY, float dZ) {
        this.player = player;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.xCoord = byteBuf.readFloat();
        this.yCoord = byteBuf.readFloat();
        this.zCoord = byteBuf.readFloat();
        this.dX = byteBuf.readFloat();
        this.dY = byteBuf.readFloat();
        this.dZ = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeFloat(xCoord);
        byteBuf.writeFloat(yCoord);
        byteBuf.writeFloat(zCoord);
        byteBuf.writeFloat(dX);
        byteBuf.writeFloat(dY);
        byteBuf.writeFloat(dZ);
    }

    @Override
    public IMessage onMessage(MessageSpawnParticle event, MessageContext context) {
        //if(FMLClientHandler.instance().getServer().getEntityWorld().isRemote) {
            //TinyStorageLog.info("Client side");
            World world = FMLClientHandler.instance().getServer().getEntityWorld();
            TileEntity entity = world.getTileEntity((int)event.xCoord,(int) event.yCoord, (int)event.zCoord);
            if(entity != null && entity instanceof TileEntityQuarryChest){
                ((TileEntityQuarryChest)entity).spawnDigParticle(event.xCoord, event.yCoord, event.zCoord, event.dX, event.dY, event.dZ);
            }
        //}
        return null;
    }
}

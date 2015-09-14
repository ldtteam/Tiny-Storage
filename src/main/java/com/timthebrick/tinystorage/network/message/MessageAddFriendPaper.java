package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.common.item.ItemFriendSetter;
import com.timthebrick.tinystorage.util.common.NBTHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class MessageAddFriendPaper implements IMessage, IMessageHandler<MessageAddFriendPaper, IMessage> {

    private float xCoord, yCoord, zCoord;
    private UUID playerUUID;
    private String playerName;

    public MessageAddFriendPaper(){
    }

    public MessageAddFriendPaper(UUID id, String playerName, float xCoord, float yCoord, float zCoord) {
        this.playerUUID = id;
        this.playerName = playerName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        playerName = ByteBufUtils.readUTF8String(buf);
        this.xCoord = buf.readFloat();
        this.yCoord = buf.readFloat();
        this.zCoord = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
        ByteBufUtils.writeUTF8String(buf, playerName);
        buf.writeFloat(xCoord);
        buf.writeFloat(yCoord);
        buf.writeFloat(zCoord);
    }

    @Override
    public IMessage onMessage(MessageAddFriendPaper message, MessageContext ctx) {
        ItemStack stack = ctx.getServerHandler().playerEntity.getHeldItem();
        if(stack.getItem() instanceof ItemFriendSetter){
            NBTTagList friendsList = NBTHelper.getTagList(stack, "friendsList", 10);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("friend", message.playerUUID.toString() + message.playerName);
            friendsList.appendTag(tag);
            NBTHelper.setTagList(stack, "friendsList", friendsList);
        }
        return null;
    }
}

package com.smithsmodding.tinystorage.common.entity;

import com.smithsmodding.tinystorage.common.proxy.CommonProxy;
import com.smithsmodding.tinystorage.network.PacketHandler;
import com.smithsmodding.tinystorage.network.message.MessageAddFriendGlobal;
import com.smithsmodding.tinystorage.network.message.MessageRemoveFriendGlobal;
import com.smithsmodding.tinystorage.network.message.MessageSyncPlayerProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExtendedPropertyGlobalFriends implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "PlayerGlobalFriends";
    private final EntityPlayer player;
    private List<String> friendsList;

    public ExtendedPropertyGlobalFriends(EntityPlayer player) {
        this.player = player;
        friendsList = new ArrayList<String>();
    }

    public static final void register(EntityPlayer player) {
        player.registerExtendedProperties(ExtendedPropertyGlobalFriends.EXT_PROP_NAME, new ExtendedPropertyGlobalFriends(player));
    }

    public static final ExtendedPropertyGlobalFriends get(EntityPlayer player) {
        return (ExtendedPropertyGlobalFriends) player.getExtendedProperties(EXT_PROP_NAME);
    }

    public Entity getEntity() {
        return player;
    }

    public static String getSaveKey(EntityPlayer player) {
        return player.getUniqueID().toString() + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player) {
        ExtendedPropertyGlobalFriends playerData = ExtendedPropertyGlobalFriends.get(player);
        NBTTagCompound savedData = new NBTTagCompound();
        playerData.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player) {
        ExtendedPropertyGlobalFriends playerData = ExtendedPropertyGlobalFriends.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
        if (savedData != null) {
            playerData.loadNBTData(savedData);
        }
        PacketHandler.INSTANCE.sendTo(new MessageSyncPlayerProperties(player), (EntityPlayerMP) player);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        if (friendsList.size() > 0) {
            NBTTagList friendsListNBT = new NBTTagList();
            for (String string : friendsList) {
                NBTTagCompound tagC = new NBTTagCompound();
                tagC.setString("friend", string);
                friendsListNBT.appendTag(tagC);
            }
            compound.setTag(EXT_PROP_NAME, friendsListNBT);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        friendsList = new ArrayList<String>();
        if (compound.hasKey("friendsList")) {
            NBTTagList tagList = compound.getTagList("friendsList", 10);
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tagC = tagList.getCompoundTagAt(i);
                friendsList.add(tagC.getString(EXT_PROP_NAME));
            }
        }
    }

    @Override
    public void init(Entity entity, World world) {
    }

    public void addFriend(UUID uuid, String playerName) {
        friendsList.add(uuid.toString() + playerName);
        PacketHandler.INSTANCE.sendToServer(new MessageAddFriendGlobal(player.getUniqueID().toString() + player.getDisplayName(), uuid, playerName));
    }

    public void removeFriend(UUID uuid, String playerName) {
        friendsList.remove(uuid.toString() + playerName);
        PacketHandler.INSTANCE.sendToServer(new MessageRemoveFriendGlobal(player.getUniqueID().toString() + player.getDisplayName(), uuid, playerName));
    }

    public boolean isFriend(EntityPlayer player) {
        return friendsList.contains(player.getGameProfile().getId().toString() + player.getDisplayName());
    }

    public boolean isFriend(String uuid, String playerName) {
        return friendsList.contains(uuid + playerName);
    }
}

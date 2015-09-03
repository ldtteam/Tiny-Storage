package com.timthebrick.tinystorage.common.item;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.settings.KeyBindings;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.reference.*;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.common.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.*;

public class ItemFriendSetter extends Item implements IKeyBound {

    public enum OperationMode {
        OPERATION_MODE(EnumSet.allOf(OperationModeSettings.class));

        private final EnumSet<? extends Enum<?>> values;

        OperationMode(@Nonnull EnumSet<? extends Enum<?>> possibleOptions) {
            if (possibleOptions.isEmpty()) {
                throw new IllegalArgumentException("Tried to instantiate an empty setting.");
            }

            this.values = possibleOptions;
        }

        public EnumSet<? extends Enum<?>> getPossibleValues() {
            return this.values;
        }
    }

    public enum OperationModeSettings {
        READ_ONLY, OVERWRITE, WRITE_MERGE
    }

    public ItemFriendSetter() {
        super();
        this.setUnlocalizedName(Names.Items.FRIEND_SETTER);
        this.setMaxDamage(0);
        this.setCreativeTab(TabTinyStorage.creativeTab);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(stack, "operationMode")];
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE) + ": " + StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_CASE + operationMode.ordinal()));
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE_TIP_1) + " " + Keyboard.getKeyName(KeyBindings.changeMode.getKeyCode()) + " " + StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE_TIP_2));
        list.add("");
        if (owner.equals(player.getGameProfile().getId().toString() + player.getDisplayName())) {
            List<String> friendsList = new ArrayList<String>();
            if (NBTHelper.hasTag(stack, "friendsList")) {
                NBTTagList tagList = NBTHelper.getTagList(stack, "friendsList", 10);
                for (int k = 0; k < tagList.tagCount(); k++) {
                    NBTTagCompound tagC = tagList.getCompoundTagAt(k);
                    friendsList.add(tagC.getString("friend"));
                }
            }
            if (friendsList.size() > 0) {
                list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_CONTAINS));
                for (String string : friendsList) {
                    if (friendsList.indexOf(string) < 2) {
                        if (list.indexOf(string) == 1) {
                            list.add(UUIDHelper.getNameFromUUIDCompound(string) + "...");
                        } else {
                            list.add(UUIDHelper.getNameFromUUIDCompound(string));
                        }
                    }
                }
            } else {
                list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_EMPTY));
            }
        }else{
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_NOT_YOURS));
        }

    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":friendSetter");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote && !player.isSneaking()) {
            player.openGui(TinyStorage.instance, GUIs.FRIEND_SETTER.ordinal(), player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
        }
        return stack;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(stack, "operationMode")];
        String owner = NBTHelper.getString(stack, "owner");
        if (owner.equals(player.getGameProfile().getId().toString() + player.getDisplayName())) {
            if (player.isSneaking() && !world.isRemote) {
                if (world.getTileEntity(x, y, z) instanceof TileEntityTinyStorage) {
                    TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) world.getTileEntity(x, y, z);
                    if (tileEntity.hasUniqueOwner() && tileEntity.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName())) {
                        if (operationMode == OperationModeSettings.READ_ONLY) {
                            NBTHelper.removeTag(stack, "friendsList");
                            if (tileEntity.friendsList.size() > 0) {
                                NBTTagList friendsListNBT = new NBTTagList();
                                for (String string : tileEntity.friendsList) {
                                    NBTTagCompound tag = new NBTTagCompound();
                                    tag.setString("friend", string);
                                    friendsListNBT.appendTag(tag);
                                }
                                NBTHelper.setTagList(stack, "friendsList", friendsListNBT);
                            }
                        } else if (operationMode == OperationModeSettings.OVERWRITE) {
                            tileEntity.setFriendsList(NBTHelper.getTagList(stack, "friendsList", 10));
                            tileEntity.markDirty();
                            world.markBlockForUpdate(x, y, z);
                            stack.stackSize--;
                        } else if (operationMode == OperationModeSettings.WRITE_MERGE) {
                            NBTTagList tagList = NBTHelper.getTagList(stack, "friendsList", 10);
                            for (int i = 0; i < tagList.tagCount(); i++) {
                                if (!tileEntity.friendsList.contains(tagList.getCompoundTagAt(i).getString("friend"))) {
                                    tileEntity.friendsList.add(tagList.getCompoundTagAt(i).getString("friend"));
                                    tileEntity.markDirty();
                                    world.markBlockForUpdate(x, y, z);
                                    stack.stackSize--;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack itemStack, Key key) {
        if (key == Key.MODE) {
            if (itemStack.getItem() instanceof ItemFriendSetter) {
                OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(itemStack, "operationMode")];
                Enum<?> newState = EnumHelper.rotateEnum(operationMode, player.isSneaking(), OperationMode.OPERATION_MODE.getPossibleValues());
                operationMode = OperationModeSettings.values()[newState.ordinal()];
                NBTHelper.setInteger(itemStack, "operationMode", operationMode.ordinal());
                PlayerHelper.sendChatMessage(player, StatCollector.translateToLocal(Messages.Chat.FRIEND_SETTER_MODE) + ": " + StatCollector.translateToLocal(Messages.Chat.FRIEND_SETTER_CASE + operationMode.ordinal()));
            }
        }
    }
}

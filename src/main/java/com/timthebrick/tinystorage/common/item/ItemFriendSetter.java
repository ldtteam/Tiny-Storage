package com.timthebrick.tinystorage.common.item;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.settings.KeyBindings;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.reference.*;
import com.timthebrick.tinystorage.util.common.EnumHelper;
import com.timthebrick.tinystorage.util.common.IKeyBound;
import com.timthebrick.tinystorage.util.common.NBTHelper;
import com.timthebrick.tinystorage.util.common.PlayerHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;

public class ItemFriendSetter extends Item implements IKeyBound {

    private IIcon icon;

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
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE_TIP_1) + " " + Keyboard.getKeyName(KeyBindings.changeMode.getKeyCode()) + " " + StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE_TIP_2));
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_MODE) + ": " + StatCollector.translateToLocal(Messages.ItemTooltips.FRIEND_SETTER_CASE + operationMode.ordinal()));
    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":friendSetter");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        TinyStorageLog.info("On Right Click");
        player.openGui(TinyStorage.instance, GUIs.FRIEND_SETTER.ordinal(), player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
        return stack;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TinyStorageLog.info("On Use First");
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

package com.timthebrick.tinystorage.item;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.*;
import com.timthebrick.tinystorage.util.ColourHelper;
import com.timthebrick.tinystorage.util.ItemHelper;
import com.timthebrick.tinystorage.util.NBTHelper;
import com.timthebrick.tinystorage.util.PlayerHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemStorageBag extends Item {

    private static final String[] STORAGE_BAG_ICONS = {"Open", "Closed", "Small", "Medium", "Large"};

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemStorageBag () {
        super();
        this.setUnlocalizedName("storageBag");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(TabTinyStorage.creativeTab);

    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean flag) {
        int metaData = stack.getItemDamage();
        if (metaData == 0) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_1));
        } else if (metaData == 1) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_2));
        } else if (metaData == 2) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_3));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses () {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister) {
        icons = new IIcon[STORAGE_BAG_ICONS.length];
        for (int i = 0; i < STORAGE_BAG_ICONS.length; i++) {
            icons[i] = iconRegister.registerIcon((References.MOD_ID.toLowerCase() + ":storageBag" + STORAGE_BAG_ICONS[i]));
        }
    }

    @Override
    public IIcon getIcon (ItemStack itemStack, int renderPass) {
        if (renderPass == 0) {
            if (NBTHelper.hasTag(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN)) {
                return icons[0];
            } else {
                return icons[1];
            }
        } else {
            return icons[2 + MathHelper.clamp_int(itemStack.getItemDamage(), 0, 3)];
        }
    }


    @Override
    public void getSubItems (Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 3; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean getShareTag () {
        return true;
    }

    @Override
    public ItemStack onItemRightClick (ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            if (!ItemHelper.hasOwnerUUID(itemStack)) {
                ItemHelper.setOwner(itemStack, entityPlayer);
                PlayerHelper.sendChatMessage(entityPlayer, StatCollector.translateToLocal(Messages.Chat.OWNER_SET_TO_SELF));
            }
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN, true);
            entityPlayer.openGui(TinyStorage.instance, GUIs.STORAGE_BAG.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack (ItemStack itemStack, int renderPass) {
        int bagColor = this.getColor(itemStack);
        if (bagColor < 0) {
            bagColor = Integer.parseInt(Colours.PURE_WHITE, 16);
        }
        return bagColor;
    }

    public boolean hasColor (ItemStack itemStack) {
        return ColourHelper.hasColor(itemStack);
    }

    public int getColor (ItemStack itemStack) {
        return ColourHelper.getColor(itemStack);
    }

    public void setColor (ItemStack itemStack, int color) {
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemStorageBag) {
                ColourHelper.setColor(itemStack, color);
            }
        }
    }

    public void removeColor (ItemStack itemStack) {
        if (itemStack != null) {
            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
            if (nbtTagCompound != null) {
                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Names.NBT.DISPLAY);
                if (displayTagCompound.hasKey(Names.NBT.COLOR)) {
                    displayTagCompound.removeTag(Names.NBT.COLOR);
                }
            }
        }
    }
}

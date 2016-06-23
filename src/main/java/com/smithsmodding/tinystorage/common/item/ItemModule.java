package com.smithsmodding.tinystorage.common.item;

import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.modules.IModuleProvider;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.registry.GeneralRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * Created by Tim on 23/06/2016.
 */
public class ItemModule extends Item implements IModuleProvider {

    public ItemModule() {
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setUnlocalizedName(References.Items.ItemModule);
        this.setRegistryName(References.MOD_ID.toLowerCase(), References.Items.ItemModule);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (IModule module : GeneralRegistry.instance().getModuleRegistry().getAllBuildableModules()) {
            ItemStack stack = new ItemStack(itemIn);
            NBTTagCompound data = new NBTTagCompound();
            data.setString("moduleID", module.getUniqueID());
            stack.setTagCompound(data);
            subItems.add(stack);
        }
    }

    @Override
    public IModule getModule(ItemStack stack) {
        return GeneralRegistry.instance().getModuleRegistry().getModule(getModuleID(stack));
    }

    @Override
    public String getModuleID(ItemStack stack) {
        if (stack.getItem() instanceof ItemModule) {
            if (stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound();
                if (tag.hasKey("moduleID")) {
                    return tag.getString("moduleID");
                }
            }
        }
        return "";
    }
}

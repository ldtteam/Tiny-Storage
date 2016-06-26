package com.smithsmodding.tinystorage.api.common.factory;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.exception.ModuleStackConstructionException;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.reference.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Author Orion (Created on: 22.06.2016)
 */
public interface IModuleFactory {

    /**
     * Method to get all ID's of the Modules that this Factory can build.
     *
     * @return A list containing all the ID's of the Modules that this Factory can build.
     */
    ImmutableList<String> getBuildableModules();

    /**
     * Method used to construct a new Instance of a Module with the given ID.
     *
     * @param uniqueId The ID of the Module that this Factory should construct.
     * @return A IModule instance for the given ID.
     * @throws ModuleConstructionException Thrown when the given ID is not registered to this Factory.
     */
    IModule buildModule(String uniqueId) throws ModuleConstructionException;

    /**
     * Method to get the ItemStack representation of a given module
     *
     * @param module The module to create an ItemStack for
     * @return The ItemStack representation of the module
     * @throws ModuleStackConstructionException Thrown when the given module is not registered to this Factory
     */
    default ItemStack buildItemStack(IModule module) throws ModuleStackConstructionException {
        if (!getBuildableModules().contains(module.getUniqueID())) {
            throw new ModuleStackConstructionException("This Module if unknown to Factory: " + this.getClass().getName());
        }
        try {
            ItemStack stack = new ItemStack(ModItems.itemModule);
            NBTTagCompound data = new NBTTagCompound();
            data.setString("moduleID", module.getUniqueID());
            stack.setTagCompound(data);

            return stack;
        } catch (Exception ex) {
            throw new ModuleStackConstructionException("Failed to create a Stack for module: " + module.getUniqueID() + " in factory: " + this.getClass().getName());
        }
    }
}

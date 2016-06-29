package com.smithsmodding.tinystorage.common.modules;

import com.smithsmodding.tinystorage.api.client.modules.IModelProvidingModule;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.client.renderer.tileentity.TileEntityRendererTinyStorage;
import com.smithsmodding.tinystorage.common.registry.ModuleRegistry;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Author Orion (Created on: 26.06.2016)
 */
public final class ModuleTinyStorageCore implements IModelProvidingModule, IStorageModule {

    //chest.getInstalledModules().get(new ArrayList<>(chest.getInstalledModules().keySet()).get(index + 1))

    //TEMPORARY:
    public static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("textures/entity/chest/normal.png");

    private IModularChest chest;

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRender(ModelChest chest, RenderEntityItem itemRenderer, Random random) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void doRender(ModelChest chest, RenderEntityItem itemRenderer, Random random, TileEntityRendererTinyStorage renderer) {
        renderer.bindTexture(TEXTURE_NORMAL);
        chest.renderAll();
    }

    @Override
    public String getUniqueID() {
        return References.Modules.ModuleNames.CORE;
    }

    @Override
    public String getDisplayText() {
        return "Module Chest System.";
    }

    @Override
    public void onTileEntityUpdate(IModularChest tileEntityModularChest) {
        //NOOP
    }

    @Override
    public void onInstalled(IModularChest tileEntityModularChest) {
        chest = tileEntityModularChest;
    }

    @Override
    public boolean canInstall(IModularChest tileEntityModularChest) {
        return !tileEntityModularChest.getInstalledModules().containsKey(getUniqueID());
    }

    @Override
    public NBTTagCompound writeToNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void loadFromNBT(NBTTagCompound tag) {
        //NOOP
    }

    @Override
    public int getSizeInventory() {
        return chest.getModuleLimit();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index >= chest.getInstalledModules().size() - 1)
            return null;

        return ModuleRegistry.getInstance().getStackForModule(chest.getModuleOnPosition(index));
    }

    @Override
    public void clearInventory() {
        //NOOP Dynamic genererated inventory from installed Modules. Gets synced automatically.
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index >= chest.getInstalledModules().size() - 1)
            return;

        if (stack != null) {
            IModule module = ModuleRegistry.getInstance().getModuleFromStack(stack);
            if (module == null)
                return;

            if (module.getUniqueID().equals(chest.getModuleOnPosition(index).getUniqueID()))
                return;

            chest.installModule(module);
        } else {
            chest.removeModule(chest.getModuleOnPosition(index));
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public void markDirty() {
        chest.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return ModuleRegistry.getInstance().getModuleFromStack(stack) != null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }
}

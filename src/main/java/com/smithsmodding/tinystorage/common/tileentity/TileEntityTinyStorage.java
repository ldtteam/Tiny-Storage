package com.smithsmodding.tinystorage.common.tileentity;

import com.google.common.collect.ImmutableMap;
import com.smithsmodding.smithscore.common.tileentity.TileEntitySmithsCore;
import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.ICustomFilterModule;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.modules.ModuleTinyStorageCore;
import com.smithsmodding.tinystorage.common.tileentity.guimanager.GuiManagerTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.state.TileEntityTinyStorageState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class TileEntityTinyStorage extends TileEntitySmithsCore<TileEntityTinyStorageState, GuiManagerTinyStorage> implements IModularChest, ITickable {

    private final IAnimationStateMachine asm;
    private final TimeValues.VariableValue cycleLength = new TimeValues.VariableValue(4);
    private final TimeValues.VariableValue clickTime = new TimeValues.VariableValue(Float.NEGATIVE_INFINITY);

    public TileEntityTinyStorage() {
        getState().setModuleLimit(References.Modules.Limits.SMALL_SIZE + 1);
        installModule(new ModuleTinyStorageCore());

        asm = TinyStorage.instance.proxy.loadAnimationStateMachine(new ResourceLocation(References.MOD_ID.toLowerCase(), "animations/blocks/tinystorage.blocks.base.json"), ImmutableMap.of(
                "cycle_length", cycleLength,
                "click_time", clickTime
        ));
    }

    public void handleEvents(float time, Iterable<Event> pastEvents)
    {
        for(Event event : pastEvents)
        {
            System.out.println("Event: " + event.event() + " " + event.offset() + " " + getPos() + " " + time);
        }
    }

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }

    @Override
    protected GuiManagerTinyStorage getInitialGuiManager() {
        return new GuiManagerTinyStorage();
    }

    @Override
    protected TileEntityTinyStorageState getInitialState() {
        return new TileEntityTinyStorageState();
    }

    @Override
    public LinkedHashMap<String, IModule> getInstalledModules() {
        return getState().getInstalledModules();
    }

    @Override
    public void installModule(IModule module) {
        if (!getState().getInstalledModules().containsKey(module.getUniqueID())
                && getModuleCount() + 1 <= getModuleLimit() && module.canInstall(this)) {
            getState().getInstalledModules().put(module.getUniqueID(), module);
            module.onInstalled(this);
        }
    }

    @Override
    public void removeModule(IModule module) {
        if (!getInstalledModules().containsKey(module.getUniqueID())) {
            return;
        }
        getInstalledModules().remove(module.getUniqueID());
    }

    @Override
    public int getModuleLimit() {
        return getState().getModuleLimit();
    }

    @Override
    public int getModuleCount() {
        return getState().getInstalledModules().size() - 1;
    }

    @Override
    public IModule getModuleAtPosition(int index) {
        return this.getInstalledModules().get(new ArrayList<>(this.getInstalledModules().keySet()).get(index + 1));
    }

    public ModuleTinyStorageCore getCoreModule() {
        return (ModuleTinyStorageCore) this.getInstalledModules().get(new ArrayList<>(this.getInstalledModules().keySet()).get(0));
    }

    @Override
    public boolean containsModule(String uniqueID) {
        return getInstalledModules().containsKey(uniqueID);
    }

    @Override
    public boolean containsModuleType(Class<? extends IModule> module) {
        for (IModule installedModule : getState().getInstalledModules().values()) {
            if (installedModule.getClass().isAssignableFrom(module)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        int invSize = 0;
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                invSize += ((IStorageModule) module).getSizeInventory();
            }
        }
        return invSize;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.getStackInSlot(index);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.decrStackSize(index, count);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return null;
    }

    @Override
    public void clearInventory() {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                storageModule.clearInventory();
            }
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    storageModule.setInventorySlotContents(index, stack);
                }
                index -= storageModule.getSizeInventory();
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {
        int sum = 0;
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                sum += storageModule.getInventoryStackLimit();
            }
        }
        return sum;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (this.containsModuleType(ICustomFilterModule.class)) {
            boolean matchesFilter = false;
            for (IModule module : getState().getInstalledModules().values()) {
                if (module instanceof ICustomFilterModule) {
                    ICustomFilterModule filterModule = (ICustomFilterModule) module;
                    if (filterModule.allowItemStack(-1, stack)) {
                        matchesFilter = true;
                    }
                }
            }
            if (!matchesFilter) {
                return false;
            }
        }
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.isItemValidForSlot(index, stack);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return false;
    }

    @Override
    public void update() {
        for (IModule module : getState().getInstalledModules().values()) {
            module.onTileEntityUpdate(this);
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing side)
    {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY)
        {
            return true;
        }
        return super.hasCapability(capability, side);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side)
    {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY)
        {
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        }
        return super.getCapability(capability, side);
    }


    public void click(boolean sneaking)
    {
        if(asm != null)
        {
            if(sneaking)
            {
                cycleLength.setValue(6 - cycleLength.apply(0));
            }
            else if(asm.currentState().equals("closed"))
            {
                clickTime.setValue(Animation.getWorldTime(getWorld()));
                asm.transition("opening");
            }
            else if(asm.currentState().equals("open"))
            {
                clickTime.setValue(Animation.getWorldTime(getWorld()));
                asm.transition("closing");
            }
        }
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

    @Override
    public String getContainerID() {
        return References.TE + "-" + getLocation().toString();
    }
}

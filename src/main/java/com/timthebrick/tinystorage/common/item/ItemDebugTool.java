package com.timthebrick.tinystorage.common.item;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nonnull;

import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.util.EnumHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.input.Keyboard;

import com.timthebrick.tinystorage.client.settings.KeyBindings;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.init.ModBlocks;
import com.timthebrick.tinystorage.common.reference.Key;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityClayChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityPeacefulChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityPiggyBank;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTinyChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityVacuumChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityWoolChest;
import com.timthebrick.tinystorage.util.IKeyBound;
import com.timthebrick.tinystorage.util.NBTHelper;
import com.timthebrick.tinystorage.util.PlayerHelper;

public class ItemDebugTool extends Item implements IKeyBound {

    private IIcon icon;

    public enum OperationMode {
        OPERATION_MODE(EnumSet.allOf(OperationModeSettings.class));

        private final EnumSet<? extends Enum<?>> values;

        OperationMode (@Nonnull EnumSet<? extends Enum<?>> possibleOptions) {
            if (possibleOptions.isEmpty()) {
                throw new IllegalArgumentException("Tried to instantiate an empty setting.");
            }

            this.values = possibleOptions;
        }

        public EnumSet<? extends Enum<?>> getPossibleValues () {
            return this.values;
        }
    }

    public enum OperationModeSettings {
        RENDER_AREA, ROTATE_CHEST, DOWNGRADE_CHEST
    }

    public ItemDebugTool () {
        super();
        this.setUnlocalizedName(Names.Items.DEBUG_TOOL);
        this.setMaxDamage(0);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean flag) {
        OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(stack, "operationMode")];
        //noinspection unchecked
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.DEBUG_TOOL_MODE_TIP_1) + " " + Keyboard.getKeyName(KeyBindings.changeMode.getKeyCode()) + " " + StatCollector.translateToLocal(Messages.ItemTooltips.DEBUG_TOOL_MODE_TIP_2));
        //noinspection unchecked
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.DEBUG_TOOL_MODE) + ": " + StatCollector.translateToLocal(Messages.ItemTooltips.DEBUG_TOOL_CASE + operationMode.ordinal()));
    }

    @Override
    public void registerIcons (IIconRegister register) {
        this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":debugTool");
    }

    @Override
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public boolean onItemUseFirst (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && player.isSneaking()) {
            if (world.getTileEntity(x, y, z) instanceof TileEntityTinyStorage) {
                TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) world.getTileEntity(x, y, z);
                OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(stack, "operationMode")];
                if (operationMode == OperationModeSettings.ROTATE_CHEST) {
                    ForgeDirection dir = tileEntity.getOrientation();
                    tileEntity.setOrientation(dir.getRotation(ForgeDirection.DOWN));
                    tileEntity.markDirty();
                    world.markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
                } else if (operationMode == OperationModeSettings.DOWNGRADE_CHEST) {
                    if (tileEntity != null && tileEntity instanceof TileEntityTinyChest) {
                        TileEntityTinyChest newChest;
                        TileEntityTinyChest tinyChest = (TileEntityTinyChest) tileEntity;
                        if (tinyChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = tinyChest.applyDowngradeClick(world, this, tinyChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        world.setTileEntity(x, y, z, newChest);
                        world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
                        world.markBlockForUpdate(x, y, z);
                        newChest.markDirty();
                        return true;
                    } else if (tileEntity != null && tileEntity instanceof TileEntityPeacefulChest) {
                        TinyStorageLog.info("Peaceful Chest");
                        TileEntityPeacefulChest newChest;
                        TileEntityPeacefulChest tinyChest = (TileEntityPeacefulChest) tileEntity;
                        if (tinyChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = tinyChest.applyDowngradeClick(world, this, tinyChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        world.setTileEntity(x, y, z, newChest);
                        world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
                        world.markBlockForUpdate(x, y, z);
                        newChest.markDirty();
                        return true;
                    } else if (tileEntity != null && tileEntity instanceof TileEntityPiggyBank) {
                        TileEntityPiggyBank newChest;
                        TileEntityPiggyBank tinyChest = (TileEntityPiggyBank) tileEntity;
                        if (tinyChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = tinyChest.applyDowngradeClick(world, this, tinyChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        world.setTileEntity(x, y, z, newChest);
                        world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
                        world.markBlockForUpdate(x, y, z);
                        newChest.markDirty();
                        return true;
                    } else if (tileEntity != null && tileEntity instanceof TileEntityWoolChest) {
                        TileEntityWoolChest newChest;
                        TileEntityWoolChest tinyChest = (TileEntityWoolChest) tileEntity;
                        int metaData = world.getBlockMetadata(x, y, z);
                        if (tinyChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = tinyChest.applyDowngradeClick(world, this, tinyChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        if (newChest.getUniqueOwner() == "") {
                            if (newChest.getState() == 0) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestSmall, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 1) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestMedium, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 2) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestLarge, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else {
                                return true;
                            }
                        } else {
                            if (newChest.getState() == 0) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestSmallLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 1) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestMediumLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 2) {
                                world.setBlock(x, y, z, ModBlocks.blockWoolChestLargeLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else {
                                return true;
                            }
                        }
                    } else if (tileEntity != null && tileEntity instanceof TileEntityClayChest) {
                        TileEntityClayChest newChest;
                        TileEntityClayChest clayChest = (TileEntityClayChest) tileEntity;
                        int metaData = world.getBlockMetadata(x, y, z);
                        if (clayChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = clayChest.applyDowngradeClick(world, this, clayChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        if (newChest.getUniqueOwner() == "") {
                            if (newChest.getState() == 0) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestSmall, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 1) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestMedium, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 2) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestLarge, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else {
                                return true;
                            }
                        } else {
                            if (newChest.getState() == 0) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestSmallLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 1) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestMediumLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else if (newChest.getState() == 2) {
                                world.setBlock(x, y, z, ModBlocks.blockClayChestLargeLocked, metaData, 3);
                                world.setTileEntity(x, y, z, newChest);
                                return true;
                            } else {
                                return true;
                            }
                        }
                    } else if (tileEntity != null && tileEntity instanceof TileEntityVacuumChest) {
                        TileEntityVacuumChest newChest;
                        TileEntityVacuumChest tinyChest = (TileEntityVacuumChest) tileEntity;
                        if (tinyChest.getState() - 1 < 0) {
                            return true;
                        }
                        newChest = tinyChest.applyDowngradeClick(world, this, tinyChest.getState() - 1, player);
                        if (newChest == null) {
                            return true;
                        }
                        world.setTileEntity(x, y, z, newChest);
                        world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
                        world.markBlockForUpdate(x, y, z);
                        newChest.markDirty();
                        return true;
                    }
                } else {
                    // Other operation modes here
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void doKeyBindingAction (EntityPlayer player, ItemStack itemStack, Key key) {
        if (key == Key.MODE) {
            if (itemStack.getItem() instanceof ItemDebugTool) {
                OperationModeSettings operationMode = OperationModeSettings.values()[NBTHelper.getInteger(itemStack, "operationMode")];
                Enum<?> newState = EnumHelper.rotateEnum(operationMode, player.isSneaking(), OperationMode.OPERATION_MODE.getPossibleValues());
                operationMode = OperationModeSettings.values()[newState.ordinal()];
                NBTHelper.setInteger(itemStack, "operationMode", operationMode.ordinal());
                PlayerHelper.sendChatMessage(player, StatCollector.translateToLocal(Messages.Chat.DEBUG_TOOL_MODE) + ": " + StatCollector.translateToLocal(Messages.Chat.DEBUG_TOOL_CASE + operationMode.ordinal()));
            }
        }
    }
}

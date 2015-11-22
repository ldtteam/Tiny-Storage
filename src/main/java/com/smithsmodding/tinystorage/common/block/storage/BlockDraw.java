package com.smithsmodding.tinystorage.common.block.storage;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.block.BlockContainerTinyStorage;
import com.smithsmodding.tinystorage.common.reference.*;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.smithsmodding.tinystorage.util.common.PlayerHelper;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.AccessMode;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityDraw;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockDraw extends BlockContainerTinyStorage {

    protected String textureName;
    private boolean isLockable;

    public BlockDraw(Material mat, String textureName, boolean isLockable) {
        super(mat);
        this.setHardness(2.5f);
        this.textureName = textureName;
        this.isLockable = isLockable;
        if (!this.isLockable) {
            this.setBlockName(Names.UnlocalisedBlocks.DRAW + this.textureName);
        } else {
            this.setBlockName(Names.UnlocalisedBlocks.DRAW_LOCKED + this.textureName);
        }
        this.setCreativeTab(TabTinyStorage.creativeTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {
        return new TileEntityDraw();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderIDs.draw;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if ((player.isSneaking() && player.getCurrentEquippedItem() != null) || world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) {
            return true;
        } else {
            if (world.getTileEntity(x, y, z) instanceof TileEntityDraw) {
                TileEntityDraw tileEntityDraw = (TileEntityDraw) world.getTileEntity(x, y, z);
                if (tileEntityDraw.hasUniqueOwner()) {
                    if (!tileEntityDraw.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName())) {
                        PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.CHEST_NOT_OWNED));
                        return true;
                    }
                }
                if ((tileEntityDraw.getOrientation() == ForgeDirection.NORTH && subZ == (1F / 16)) || (tileEntityDraw.getOrientation() == ForgeDirection.SOUTH && subZ == (1F / 16) * 14)) {
                    if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 10 && subY < (1F / 16) * 13) {
                        tileEntityDraw.rowOpened = 0;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 6 && subY < (1F / 16) * 9) {
                        tileEntityDraw.rowOpened = 1;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 2 && subY < (1F / 16) * 5) {
                        tileEntityDraw.rowOpened = 2;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    }
                } else if ((tileEntityDraw.getOrientation() == ForgeDirection.EAST && subX == (1F / 16) * 14) || (tileEntityDraw.getOrientation() == ForgeDirection.WEST) && subX == (1F / 16)) {
                    if (subZ >= (1F / 16) * 2 && subZ <= (1F / 16) * 14 && subY >= (1F / 16) * 10 && subY < (1F / 16) * 13) {
                        tileEntityDraw.rowOpened = 0;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subZ >= (1F / 16) * 2 && subZ <= (1F / 16) * 14 && subY >= (1F / 16) * 6 && subY < (1F / 16) * 9) {
                        tileEntityDraw.rowOpened = 1;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subZ >= (1F / 16) * 2 && subZ <= (1F / 16) * 14 && subY >= (1F / 16) * 2 && subY < (1F / 16) * 5) {
                        tileEntityDraw.rowOpened = 2;
                        if (!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    }
                }
            }
            return true;
        }
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData) {
        super.onBlockEventReceived(world, x, y, z, eventId, eventData);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(eventId, eventData);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityTinyStorage) {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if (facing == 0) {
                direction = ForgeDirection.NORTH.ordinal();
            } else if (facing == 1) {
                direction = ForgeDirection.EAST.ordinal();
            } else if (facing == 2) {
                direction = ForgeDirection.SOUTH.ordinal();
            } else if (facing == 3) {
                direction = ForgeDirection.WEST.ordinal();
            }
            if (itemStack.hasDisplayName()) {
                ((TileEntityTinyStorage) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
            }
            ((TileEntityTinyStorage) world.getTileEntity(x, y, z)).setOrientation(direction);

            if (this.isLockable) {
                if (entityLiving instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entityLiving;
                    if (!PlayerHelper.isPlayerFake(player)) {
                        ((TileEntityTinyStorage) world.getTileEntity(x, y, z)).setUniqueOwner(entityLiving.getUniqueID().toString() + player.getDisplayName());
                        ((TileEntityTinyStorage) world.getTileEntity(x, y, z)).setOwner(player.getDisplayName());
                        ((TileEntityTinyStorage) world.getTileEntity(x, y, z)).setAccessMode(AccessMode.DISABLED);
                    } else {
                        TinyStorageLog.error("Something (not a player) just tried to place a locked chest!" + " | " + entityLiving.toString());
                        world.removeTileEntity(x, y, z);
                        world.setBlockToAir(x, y, z);
                        if (itemStack != null && itemStack.stackSize > 0) {
                            Random rand = new Random();

                            float dX = rand.nextFloat() * 0.8F + 0.1F;
                            float dY = rand.nextFloat() * 0.8F + 0.1F;
                            float dZ = rand.nextFloat() * 0.8F + 0.1F;

                            EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                            if (itemStack.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                            }

                            float factor = 0.05F;
                            entityItem.motionX = rand.nextGaussian() * factor;
                            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                            entityItem.motionZ = rand.nextGaussian() * factor;
                            world.spawnEntityInWorld(entityItem);
                            itemStack.stackSize = 0;
                        }
                    }
                }
            }
        }
    }

    private void dropInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        updateBlockBounds(world, x, y, z);
    }

    private void updateBlockBounds(IBlockAccess world, int x, int y, int z) {
        setBlockBounds(1F / 16, 1F / 16, 1F / 16, (1F / 16) * 14, (1F / 16) * 14, (1F / 16) * 14);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityDraw) {
            TileEntityDraw tileEntity = (TileEntityDraw) world.getTileEntity(x, y, z);
            if (tileEntity.hasUniqueOwner()) {
                if (tileEntity.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName())) {
                    return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
                } else {
                    return -1F;
                }
            } else {
                return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
            }
        } else {
            return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":" + textureName);
    }

    public boolean getIsLockable(){
        return isLockable;
    }

    @Override
    public String getTextureName() {
        return textureName;
    }

}

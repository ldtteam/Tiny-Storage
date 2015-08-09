package com.timthebrick.tinystorage.common.block.storage;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.reference.GUIs;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.reference.RenderIDs;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityDraw;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockDraw extends BlockContainer {

    protected String textureName;

    public BlockDraw(Material mat, String textureName) {
        super(mat);
        this.setHardness(2.5f);
        this.setBlockName(Names.UnlocalisedBlocks.DRAW + textureName);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.textureName = textureName;
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

    /*
    [14:42:04] [Client thread/INFO] [Tiny Storage]: 0.49209595, 0.7013855, 0.0
    [14:42:04] [Client thread/INFO] [Tiny Storage]: NORTH
    [14:42:05] [Client thread/INFO] [Tiny Storage]: 1.0, 0.7095413, 0.4985962
    [14:42:05] [Client thread/INFO] [Tiny Storage]: EAST
    [14:42:06] [Client thread/INFO] [Tiny Storage]: 0.47946167, 0.70111465, 1.0
    [14:42:06] [Client thread/INFO] [Tiny Storage]: SOUTH
    [14:42:07] [Client thread/INFO] [Tiny Storage]: 0.0, 0.7023468, 0.5088806
    [14:42:07] [Client thread/INFO] [Tiny Storage]: WEST
     */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        /*
        if (world.isRemote) {
            TinyStorageLog.info(subX + ", " + subY + ", " + subZ);
            if (world.getTileEntity(x, y, z) instanceof TileEntityDraw) {
                TileEntityDraw tileEntity = (TileEntityDraw) world.getTileEntity(x, y, z);
                TinyStorageLog.info(tileEntity.getOrientation().toString());
            }
        }
        */
        if ((player.isSneaking() && player.getCurrentEquippedItem() != null) || world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) {
            return true;
        } else {
            if (world.getTileEntity(x, y, z) instanceof TileEntityDraw) {
                TileEntityDraw tileEntityDraw = (TileEntityDraw) world.getTileEntity(x, y, z);
                if (tileEntityDraw.getOrientation() == ForgeDirection.NORTH) {
                    if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 10 && subY <= (1F / 16) * 12) {
                        tileEntityDraw.rowOpened = 0;
                        if(!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 6 && subY <= (1F / 16) * 8) {
                        tileEntityDraw.rowOpened = 1;
                        if(!world.isRemote) {
                            player.openGui(TinyStorage.instance, GUIs.DRAW.ordinal(), world, x, y, z);
                        }
                    } else if (subX >= (1F / 16) * 2 && subX <= (1F / 16) * 14 && subY >= (1F / 16) * 2 && subY <= (1F / 16) * 4) {
                        tileEntityDraw.rowOpened = 2;
                        if(!world.isRemote) {
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
        }
    }

    protected void dropInventory(World world, int x, int y, int z) {
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

    public void updateBlockBounds(IBlockAccess world, int x, int y, int z) {
        setBlockBounds(1F / 16, 1F / 16, 1F / 16, (1F / 16) * 14, (1F / 16) * 14, (1F / 16) * 14);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":" + textureName);
    }

    @Override
    public String getTextureName() {
        return textureName;
    }

}

package com.timthebrick.tinystorage.block;

import java.util.Random;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.GUIs;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.reference.RenderIDs;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityMicroChest;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityTinyChest;
import com.timthebrick.tinystorage.util.PlayerHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
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

public class BlockMicroChest extends BlockContainer implements ITileEntityProvider {

	protected String textureName;
	private boolean isLockable;

	public BlockMicroChest(Material mat, String textureName, boolean isLockable) {
		super(mat);
		this.setHardness(2.5f);
		this.isLockable = isLockable;
		this.textureName = textureName;
		if (!this.isLockable) {
			this.setBlockName("blockMicroChest" + this.textureName);
		} else {
			this.setBlockName("blockMicroChestLocked" + this.textureName);
		}
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		return new TileEntityMicroChest();
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
		updateChestBounds(world.getBlockMetadata(x, y, z));
	}

	public void updateChestBounds(int meta) {
		setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.51f, 0.8f);
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
		return RenderIDs.microChest;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		} else {
			if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityMicroChest) {
				TileEntityMicroChest tileEntity = (TileEntityMicroChest) world.getTileEntity(x, y, z);
				if (tileEntity.hasUniqueOwner()) {
					if (tileEntity.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName())) {
						player.openGui(TinyStorage.instance, GUIs.MICRO_CHEST.ordinal(), world, x, y, z);
					} else {
						PlayerHelper.sendChatMessage(player, "This chest does not belong to you! Back off!");
					}
				} else {
					player.openGui(TinyStorage.instance, GUIs.MICRO_CHEST.ordinal(), world, x, y, z);
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
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) instanceof TileEntityMicroChest) {
			TileEntityMicroChest tileEntity = (TileEntityMicroChest) world.getTileEntity(x, y, z);
			if (tileEntity.hasUniqueOwner()) {
				if (tileEntity.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName())) {
					return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
				} else {
					return -1F;
				}
			} else {
				return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
			}
		}
		return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
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
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":blockMicroChest");
	}

	@Override
	public String getTextureName() {
		return textureName;
	}

	public boolean getIsLockable() {
		return isLockable;
	}

}

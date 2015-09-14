package com.timthebrick.tinystorage.common.block.storage.chests;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.reference.*;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityQuarryChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestSmall;
import com.timthebrick.tinystorage.util.common.PlayerHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockQuarryChest extends BlockContainer implements ITileEntityProvider {

	public BlockQuarryChest() {
		super(Material.rock);
		this.setHardness(2.5f);
		this.setBlockName(Names.UnlocalisedBlocks.QUARRY_CHEST);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		if (metaData == 0) {
			return new TileEntityQuarryChestSmall();
		} else if (metaData == 1) {
			return new TileEntityQuarryChestMedium();
		} else if (metaData == 2) {
			return new TileEntityQuarryChestLarge();
		}
		return null;
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

	private void updateChestBounds(int meta) {
		float f = 0.125F;
		if (meta == 0) {
			setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.60f, 0.8f);
		}
		if (meta == 1) {
			setBlockBounds(0.125f, 0.0f, 0.125f, 1F - 0.125f, 0.72f, 1F - 0.125f);
		}
		if (meta == 2) {
			setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
		}
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
		return RenderIDs.quarryChest;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
		if ((player.isSneaking() && player.getCurrentEquippedItem() != null) || world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) {
			return true;
		}
		if (world.isRemote) {
			return true;
		} else {
			if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityQuarryChest) {
				TileEntityQuarryChest tileEntity = (TileEntityQuarryChest) world.getTileEntity(x, y, z);
				if (tileEntity.hasUniqueOwner()) {
					if (tileEntity.getUniqueOwner().equals(player.getGameProfile().getId().toString() + player.getDisplayName())|| tileEntity.isFriend(player)) {
						player.openGui(TinyStorage.instance, GUIs.QUARRY_CHEST.ordinal(), world, x, y, z);
					} else {
						PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.CHEST_NOT_OWNED));
					}
				} else {
					player.openGui(TinyStorage.instance, GUIs.QUARRY_CHEST.ordinal(), world, x, y, z);
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
		if (world.getTileEntity(x, y, z) instanceof TileEntityQuarryChest) {
			TileEntityQuarryChest tileEntity = (TileEntityQuarryChest) world.getTileEntity(x, y, z);
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

			if (!world.isRemote && entityLiving instanceof EntityPlayer) {
				if (world.getTileEntity(x, y, z) instanceof TileEntityQuarryChest) {
					((TileEntityQuarryChest) world.getTileEntity(x, y, z)).genFirstLayer((EntityPlayer) entityLiving);
				}
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
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
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < 3; meta++) {
			//noinspection unchecked
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override
	public int damageDropped(int metaData) {
		return metaData;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":Obsidian");
	}

	@Override
	public String getTextureName() {
		return textureName;
	}

}

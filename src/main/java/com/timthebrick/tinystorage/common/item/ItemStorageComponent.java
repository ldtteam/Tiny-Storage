package com.timthebrick.tinystorage.common.item;

import java.util.List;

import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.common.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.common.init.ModBlocks;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityClayChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityPeacefulChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityPiggyBank;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTinyChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTrashChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityVacuumChest;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityWoolChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStorageComponent extends Item {

	public static final String[] nameSuffix = new String[] { "Small", "Medium", "Large" };
	private IIcon[] icons;

	public ItemStorageComponent() {
		super();
		this.setUnlocalizedName(Names.Items.STORAGE_COMPONENT);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking())
			return false;
		if (world.isRemote) {
			return false;
		} else {
			if (stack.getItemDamage() != 0) {
				return false;
			}
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityTinyChest) {
				TileEntityTinyChest newChest;
				TileEntityTinyChest tinyChest = (TileEntityTinyChest) te;
				if (tinyChest.getState() + 1 > 2)
					return false;
				newChest = tinyChest.applyUpgradeItem(this, tinyChest.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				world.setTileEntity(x, y, z, newChest);
				world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
				stack.stackSize--;
				return true;
			} else if (te != null && te instanceof TileEntityPeacefulChest) {
				TileEntityPeacefulChest newChest;
				TileEntityPeacefulChest peacefulChest = (TileEntityPeacefulChest) te;
				if (peacefulChest.getState() + 1 > 2)
					return false;
				newChest = peacefulChest.applyUpgradeItem(this, peacefulChest.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				world.setTileEntity(x, y, z, newChest);
				world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
				stack.stackSize--;
				return true;
			} else if (te != null && te instanceof TileEntityTrashChest) {
				TileEntityTrashChest newChest;
				TileEntityTrashChest trashChest = (TileEntityTrashChest) te;
				int state;
				if (trashChest.getState() + 1 > 2) {
					state = 0;
				} else {
					state = trashChest.getState() + 1;
				}
				newChest = trashChest.applyUpgradeItem(this, state, player);
				if (newChest == null) {
					return false;
				}
				world.setTileEntity(x, y, z, newChest);
				world.setBlockMetadataWithNotify(x, y, z, state, 3);
				return true;
			} else if (te != null && te instanceof TileEntityPiggyBank) {
				TileEntityPiggyBank newChest;
				TileEntityPiggyBank piggyBank = (TileEntityPiggyBank) te;
				int state;
				if (piggyBank.getState() + 1 > 2) {
					return false;
				}
				newChest = piggyBank.applyUpgradeItem(this, piggyBank.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				world.setTileEntity(x, y, z, newChest);
				world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
				stack.stackSize--;
				return true;
			} else if (te != null && te instanceof TileEntityWoolChest) {
				TileEntityWoolChest newChest;
				TileEntityWoolChest woolChest = (TileEntityWoolChest) te;
				int metaData = world.getBlockMetadata(x, y, z);
				if (woolChest.getState() + 1 > 2)
					return false;
				newChest = woolChest.applyUpgradeItem(this, woolChest.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				if (newChest.getUniqueOwner() == "") {
					if (newChest.getState() == 0) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestSmall, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 1) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestMedium, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 2) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestLarge, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else {
						return false;
					}
				} else {
					if (newChest.getState() == 0) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestSmallLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 1) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestMediumLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 2) {
						world.setBlock(x, y, z, ModBlocks.blockWoolChestLargeLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else {
						return false;
					}
				}
			} else if (te != null && te instanceof TileEntityClayChest) {
				TileEntityClayChest newChest;
				TileEntityClayChest clayChest = (TileEntityClayChest) te;
				int metaData = world.getBlockMetadata(x, y, z);
				if (clayChest.getState() + 1 > 2)
					return false;
				newChest = clayChest.applyUpgradeItem(this, clayChest.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				if (newChest.getUniqueOwner() == "") {
					if (newChest.getState() == 0) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestSmall, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 1) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestMedium, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 2) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestLarge, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else {
						return false;
					}
				} else {
					if (newChest.getState() == 0) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestSmallLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 1) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestMediumLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else if (newChest.getState() == 2) {
						world.setBlock(x, y, z, ModBlocks.blockClayChestLargeLocked, metaData, 3);
						world.setTileEntity(x, y, z, newChest);
						stack.stackSize--;
						return true;
					} else {
						return false;
					}
				}
			} else if (te != null && te instanceof TileEntityVacuumChest) {
				TileEntityVacuumChest newChest;
				TileEntityVacuumChest vacuumChest = (TileEntityVacuumChest) te;
				int state;
				if (vacuumChest.getState() + 1 > 2) {
					return false;
				}
				newChest = vacuumChest.applyUpgradeItem(this, vacuumChest.getState() + 1, player);
				if (newChest == null) {
					return false;
				}
				world.setTileEntity(x, y, z, newChest);
				world.setBlockMetadataWithNotify(x, y, z, newChest.getState(), 3);
				stack.stackSize--;
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		int metaData = itemStack.getItemDamage();
		if (metaData == 0) {
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_1));
		} else if (metaData == 1) {
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_2));
		} else if (metaData == 2) {
			list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_3));
		}
	}

	@Override
	public IIcon getIconFromDamage(int metaData) {
		int j = MathHelper.clamp_int(metaData, 0, 2);
		return this.icons[j];
	}

	@Override
	public void registerIcons(IIconRegister register) {
		this.icons = new IIcon[nameSuffix.length];
		for (int i = 0; i < icons.length; i++) {
			this.icons[i] = register.registerIcon((References.MOD_ID.toLowerCase() + ":storageComponent" + nameSuffix[i]));
		}
	}

}

package com.timthebrick.tinystorage.item;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.NBTHelper;
import com.timthebrick.tinystorage.util.PlayerHelper;
import com.timthebrick.tinystorage.util.Utils;

public class ItemDebugTool extends Item {

	private IIcon icon;

	public enum OperationMode {
		OPERATION_MODE(EnumSet.allOf(OperationModeOptions.class));

		private final EnumSet<? extends Enum<?>> values;

		OperationMode(@Nonnull EnumSet<? extends Enum<?>> possibleOptions) {
			if (possibleOptions.isEmpty()) {
				throw new IllegalArgumentException("Tried to instantiate an empty setting.");
			}

			this.values = possibleOptions;
		}

		public EnumSet<? extends Enum<?>> getPossibleValues() {
			return this.values;
		}
	}

	public enum OperationModeOptions {
		RENDER_AREA, ROTATE_CHEST
	}

	public ItemDebugTool() {
		super();
		this.setUnlocalizedName("debugTool");
		this.setMaxDamage(0);
		this.setCreativeTab(TabTinyStorage.creativeTab);
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		OperationModeOptions operationMode = OperationModeOptions.values()[NBTHelper.getInteger(stack, "operationMode")];
		list.add(StatCollector.translateToLocal("tooltip.tinystorage:debugTool.case" + operationMode.ordinal()));
	}

	@Override
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":debugTool");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				OperationModeOptions operationMode = OperationModeOptions.values()[NBTHelper.getInteger(stack, "operationMode")];
				Enum<?> newState = Utils.rotateEnum(operationMode, false, OperationMode.OPERATION_MODE.getPossibleValues());
				operationMode = OperationModeOptions.values()[newState.ordinal()];
				NBTHelper.setInteger(stack, "operationMode", operationMode.ordinal());
				PlayerHelper.sendChatMessage(player, StatCollector.translateToLocal("tooltip.tinystorage:debugTool.mode") + ": " + StatCollector.translateToLocal("tooltip.tinystorage:debugTool.case" + operationMode.ordinal()));
			}
		}
		return super.onItemRightClick(stack, world, player);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(player.isSneaking()){
			if(world.getTileEntity(x, y, z) instanceof TileEntityTinyStorage){
				TileEntityTinyStorage tileEntity = (TileEntityTinyStorage) world.getTileEntity(x, y, z);
				OperationModeOptions operationMode = OperationModeOptions.values()[NBTHelper.getInteger(stack, "operationMode")];
				if(operationMode == OperationModeOptions.ROTATE_CHEST){
					ForgeDirection dir = tileEntity.getOrientation();
					tileEntity.setOrientation(dir.getRotation(ForgeDirection.DOWN));
					tileEntity.markDirty();
					world.markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
				}
			}
			return true;
		}
		return false;
	}
}

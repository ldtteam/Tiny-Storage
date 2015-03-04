package com.timthebrick.tinystorage.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.PacketTileEntityTinyStorage;
import com.timthebrick.tinystorage.reference.Names;

public class TileEntityTinyStorage extends TileEntity {

	protected ForgeDirection orientation;
	protected byte state;
	protected String customName;
	protected String uniqueOwner;
	protected String owner;
	protected String textureName;
	protected boolean action;

	public TileEntityTinyStorage() {
		orientation = ForgeDirection.SOUTH;
		state = 0;
		customName = "";
		uniqueOwner = "";
		owner = "";
		textureName = "";
		action = false;
	}

	/**
	 * Set the orientation of the TE
	 * 
	 * @param orientation
	 *            the facing direction of the TE
	 */
	public void setOrientation(ForgeDirection orientation) {
		this.orientation = orientation;
	}

	/**
	 * Set the orientation of the TE
	 * 
	 * @param orientation
	 *            the facing direction of the TE
	 */
	public void setOrientation(int orientation) {
		this.orientation = ForgeDirection.getOrientation(orientation);
	}

	/**
	 * Set the metaData of the TE
	 * 
	 * @param state
	 *            The metaData of the TE
	 */
	public void setState(byte state) {
		this.state = state;
	}

	/**
	 * Set the generic owner of the TE - usually the display name of the player
	 * 
	 * @param owner
	 *            The display name of the player
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Set the generic owner of the TE - uses the display name of the player
	 * 
	 * @param player
	 *            The player that is the owner of the TE
	 */
	public void setOwner(EntityPlayer player) {
		this.owner = player.getDisplayName();
	}

	/**
	 * Set the unique owner of a TE - combination of the player's UUID and
	 * Display name should be used
	 * 
	 * @param owner
	 *            The string to set as the unique owner of the TE
	 */
	public void setUniqueOwner(String owner) {
		this.uniqueOwner = owner;
	}

	/**
	 * Set the unique owner of a TE - combination of the player's UUID and
	 * Display name is used
	 * 
	 * @param player
	 *            The player that is the owner of the TE
	 */
	public void setUniqueOwner(EntityPlayer player) {
		this.uniqueOwner = (player.getUniqueID().toString() + player.getDisplayName());
	}

	/**
	 * Sets the custom name of the block
	 * 
	 * @param customName
	 *            The name to set as custom
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/**
	 * Sets the custom texture name for the block
	 * 
	 * @param textureName
	 */
	public void setCustomTextureName(String textureName) {
		this.textureName = textureName;
	}
	
	/**
	 * Sets whether the TE should perfom an action or not
	 * @param action
	 */
	public void setAction(boolean action){
		this.action = action;
	}

	/**
	 * Get the orientation of the TE
	 * 
	 * @return the facing direction of the TE
	 */
	public ForgeDirection getOrientation() {
		return orientation;
	}

	/**
	 * Gets the metaData of the TE
	 * 
	 * @return the state of the TE
	 */
	public short getState() {
		return state;
	}

	/**
	 * @return The display name of the player who placed the TE
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @return A unique reference to the player who placed the TE, consisting of
	 *         the player's UUID and display name
	 */
	public String getUniqueOwner() {
		return uniqueOwner;
	}

	/**
	 * Get the custom name of the block
	 * 
	 * @return The custom name for the block
	 */
	public String getCustomName() {
		return customName;
	}

	/**
	 * Get the texture name of the block
	 * 
	 * @return The texture name of the block
	 */
	public String getTextureName() {
		return textureName;
	}
	
	/**
	 * Get whether the TE should perform an action or not
	 * @return True/False
	 */
	public boolean getAction(){
		return action;
	}

	/**
	 * Whether or not this block has a custom name
	 * 
	 * @return True/False
	 */
	public boolean hasCustomName() {
		return customName != null && customName.length() > 0;
	}

	/**
	 * Whether or not this block has a unique owner ID set
	 * 
	 * @return True/False
	 */
	public boolean hasUniqueOwner() {
		return uniqueOwner != null && uniqueOwner.length() > 0;
	}

	/**
	 * Whether or not this block has an owner set
	 * 
	 * @return True/False
	 */
	public boolean hasOwner() {
		return owner != null && owner.length() > 0;
	}

	/**
	 * Whether or not this block has a custom texture name set
	 * 
	 * @return True/False
	 */
	public boolean hasCustomTextureName() {
		return textureName != null && textureName.length() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);

		if (nbtTagCompound.hasKey(Names.NBT.DIRECTION)) {
			this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.DIRECTION));
		}

		if (nbtTagCompound.hasKey(Names.NBT.STATE)) {
			this.state = nbtTagCompound.getByte(Names.NBT.STATE);
		}

		if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME)) {
			this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
		}

		if (nbtTagCompound.hasKey(Names.NBT.UNIQUE_OWNER)) {
			this.uniqueOwner = nbtTagCompound.getString(Names.NBT.UNIQUE_OWNER);
		}

		if (nbtTagCompound.hasKey(Names.NBT.OWNER)) {
			this.owner = nbtTagCompound.getString(Names.NBT.OWNER);
		}

		if (nbtTagCompound.hasKey(Names.NBT.TEXTURE_NAME)) {
			this.textureName = nbtTagCompound.getString(Names.NBT.TEXTURE_NAME);
		}
		this.action = nbtTagCompound.getBoolean("Action");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte) orientation.ordinal());
		nbtTagCompound.setByte(Names.NBT.STATE, state);

		if (this.hasCustomName()) {
			nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
		}

		if (this.hasUniqueOwner()) {
			nbtTagCompound.setString(Names.NBT.UNIQUE_OWNER, uniqueOwner);
		}
		
		if(this.hasOwner()){
			nbtTagCompound.setString(Names.NBT.OWNER, owner);
		}

		if (this.hasCustomTextureName()) {
			nbtTagCompound.setString(Names.NBT.TEXTURE_NAME, textureName);
		}
		nbtTagCompound.setBoolean("Action", action);
	}

	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.INSTANCE.getPacketFrom(new PacketTileEntityTinyStorage(this));
	}

}

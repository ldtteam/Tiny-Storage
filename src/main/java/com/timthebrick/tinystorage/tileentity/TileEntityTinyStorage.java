package com.timthebrick.tinystorage.tileentity;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.timthebrick.tinystorage.reference.Names;

public class TileEntityTinyStorage extends TileEntity {
	
	private final Map<ButtonSettings, Enum<?>> settings = new EnumMap<ButtonSettings, Enum<?>>( ButtonSettings.class );

	protected ForgeDirection orientation;
	protected byte state;
	protected String customName;
	protected String uniqueOwner;
	protected String owner;
	protected String textureName;
	public AccessMode accessMode;

	public TileEntityTinyStorage() {
		orientation = ForgeDirection.SOUTH;
		state = 0;
		customName = "";
		uniqueOwner = "";
		owner = "";
		textureName = "";
		accessMode = AccessMode.INPUT_OUTPUT;
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
	 * Set the access mode for a *locking* chest
	 * 
	 * @param mode
	 */
	public void setAccessMode(AccessMode mode){
		this.accessMode =  mode;
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
	 * Get the access mode for the chest
	 * 
	 * @return
	 */
	public AccessMode getAccessMode(){
		return this.accessMode;
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
	

	/**
	 * Returns the description packet used for the object; used for syncing data
	 * between the client and server
	 */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncedNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	/**
	 * Used for syncning data between the client and sever
	 */
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readSyncedNBT(pkt.func_148857_g());
	}

	/**
	 * Standard method to read data from NBT
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		readSyncedNBT(nbtTagCompound);
	}

	/**
	 * Standard method for writing data to NBT
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		writeSyncedNBT(nbtTagCompound);
	}

	/**
	 * Method for writing data to be synced to the client
	 * 
	 * @param tag
	 *            The tag to sync
	 */
	public void writeSyncedNBT(NBTTagCompound tag) {
		tag.setByte(Names.NBT.DIRECTION, (byte) orientation.ordinal());
		tag.setByte(Names.NBT.STATE, state);
		tag.setInteger(Names.NBT.ACCESS_MODE, accessMode.ordinal());

		if (this.hasCustomName()) {
			tag.setString(Names.NBT.CUSTOM_NAME, customName);
		}

		if (this.hasUniqueOwner()) {
			tag.setString(Names.NBT.UNIQUE_OWNER, uniqueOwner);
		}

		if (this.hasOwner()) {
			tag.setString(Names.NBT.OWNER, owner);
		}

		if (this.hasCustomTextureName()) {
			tag.setString(Names.NBT.TEXTURE_NAME, textureName);
		}
	}

	/**
	 * The method for reading synced data on the client
	 * 
	 * @param tag
	 *            The tag to be read
	 */
	public void readSyncedNBT(NBTTagCompound tag) {
		if (tag.hasKey(Names.NBT.DIRECTION)) {
			this.orientation = ForgeDirection.getOrientation(tag.getByte(Names.NBT.DIRECTION));
		}

		if (tag.hasKey(Names.NBT.STATE)) {
			this.state = tag.getByte(Names.NBT.STATE);
		}

		if (tag.hasKey(Names.NBT.ACCESS_MODE)) {
			this.accessMode = AccessMode.values()[tag.getInteger(Names.NBT.ACCESS_MODE)];
		}

		if (tag.hasKey(Names.NBT.CUSTOM_NAME)) {
			this.customName = tag.getString(Names.NBT.CUSTOM_NAME);
		}

		if (tag.hasKey(Names.NBT.UNIQUE_OWNER)) {
			this.uniqueOwner = tag.getString(Names.NBT.UNIQUE_OWNER);
		}
		if (tag.hasKey(Names.NBT.TEXTURE_NAME)) {
			this.textureName = tag.getString(Names.NBT.TEXTURE_NAME);
		}
	}
}

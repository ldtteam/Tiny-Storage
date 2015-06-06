package com.timthebrick.tinystorage.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.block.BlockClayChest;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityClayChest;

public class TileEntityRendererClayChest extends TileEntitySpecialRenderer {

	public static final String[] textureNames = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };
	private final ModelChest modelChest = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		if (tileEntity instanceof TileEntityClayChest) {
			TileEntityClayChest tileEntityClayChest = (TileEntityClayChest) tileEntity;
			ForgeDirection direction = null;
			String textureName = "";

			if (tileEntityClayChest.getWorldObj() != null) {
				direction = tileEntityClayChest.getOrientation();
			}

			World world = tileEntityClayChest.getWorldObj();
			Block block = world.getBlock(tileEntityClayChest.xCoord, tileEntityClayChest.yCoord, tileEntityClayChest.zCoord);

			if (block instanceof BlockClayChest) {
				BlockClayChest blockChest = (BlockClayChest) block;
				int chestMeta = world.getBlockMetadata(tileEntityClayChest.xCoord, tileEntityClayChest.yCoord, tileEntityClayChest.zCoord);
				textureName = textureNames[chestMeta];
				if (!blockChest.getIsLockable()) {
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/chestClay_" + textureName + ".png"));
				} else {
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/chestClay_" + textureName + ".png"));
				}
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			// GL11.glScalef(1.0F, -1.0F, -1.0F);
			if (tileEntityClayChest.getState() == 0) {
				GL11.glScalef(0.65F, -0.65F, -0.65F);
				GL11.glTranslatef(0.26F, 0.53F, 0.26F);
			} else if (tileEntityClayChest.getState() == 1) {
				GL11.glScalef(0.8F, -0.8F, -0.8F);
				GL11.glTranslatef(0.12F, 0.25F, 0.12F);
			} else if (tileEntityClayChest.getState() == 2) {
				GL11.glScalef(1.0F, -1.0F, -1.0F);
			}
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short angle = 0;

			if (direction != null) {
				if (direction == ForgeDirection.NORTH) {
					angle = 180;
				} else if (direction == ForgeDirection.SOUTH) {
					angle = 0;
				} else if (direction == ForgeDirection.WEST) {
					angle = 90;
				} else if (direction == ForgeDirection.EAST) {
					angle = -90;
				}
			}

			GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float adjustedLidAngle = tileEntityClayChest.prevLidAngle + (tileEntityClayChest.lidAngle - tileEntityClayChest.prevLidAngle) * tick;
			adjustedLidAngle = 1.0F - adjustedLidAngle;
			adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
			modelChest.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2.0F);
			modelChest.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

}

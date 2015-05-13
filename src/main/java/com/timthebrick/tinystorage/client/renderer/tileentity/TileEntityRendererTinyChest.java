package com.timthebrick.tinystorage.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.block.BlockTinyChest;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityTinyChest;

public class TileEntityRendererTinyChest extends TileEntitySpecialRenderer {

	private final ModelChest modelChest = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		if (tileEntity instanceof TileEntityTinyChest) {
			TileEntityTinyChest tileEntityTinyChest = (TileEntityTinyChest) tileEntity;
			ForgeDirection direction = null;
			String textureName = "";

			if (tileEntityTinyChest.getWorldObj() != null) {
				direction = tileEntityTinyChest.getOrientation();
			}

			World world = tileEntityTinyChest.getWorldObj();
			Block block = world.getBlock(tileEntityTinyChest.xCoord, tileEntityTinyChest.yCoord, tileEntityTinyChest.zCoord);

			if (block instanceof BlockTinyChest) {
				BlockTinyChest blockChest = (BlockTinyChest) block;
				if (!blockChest.getIsLockable()) {
					textureName = blockChest.getTextureName();
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/chest" + textureName + ".png"));
				} else {
					textureName = blockChest.getTextureName();
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/chest" + textureName + ".png"));
				}
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			// GL11.glScalef(1.0F, -1.0F, -1.0F);
			if (tileEntityTinyChest.getState() == 0) {
				GL11.glScalef(0.65F, -0.65F, -0.65F);
				GL11.glTranslatef(0.26F, 0.53F, 0.26F);
			} else if (tileEntityTinyChest.getState() == 1) {
				GL11.glScalef(0.8F, -0.8F, -0.8F);
				GL11.glTranslatef(0.12F, 0.25F, 0.12F);
			} else if (tileEntityTinyChest.getState() == 2) {
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
			float adjustedLidAngle = tileEntityTinyChest.prevLidAngle + (tileEntityTinyChest.lidAngle - tileEntityTinyChest.prevLidAngle) * tick;
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

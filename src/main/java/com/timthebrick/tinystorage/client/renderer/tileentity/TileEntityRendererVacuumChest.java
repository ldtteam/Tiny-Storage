package com.timthebrick.tinystorage.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.block.BlockVacuumChest;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.ItemDebugTool;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityVacuumChest;

public class TileEntityRendererVacuumChest extends TileEntitySpecialRenderer {

	private final ModelChest modelChest = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		if (tileEntity instanceof TileEntityVacuumChest) {
			TileEntityVacuumChest tileEntityVacuumChest = (TileEntityVacuumChest) tileEntity;
			ForgeDirection direction = null;
			String textureName = "";

			if (tileEntityVacuumChest.getWorldObj() != null) {
				direction = tileEntityVacuumChest.getOrientation();
			}

			World world = tileEntityVacuumChest.getWorldObj();
			Block block = world.getBlock(tileEntityVacuumChest.xCoord, tileEntityVacuumChest.yCoord, tileEntityVacuumChest.zCoord);

			if (block instanceof BlockVacuumChest) {
				BlockVacuumChest blockChest = (BlockVacuumChest) block;
				if (!blockChest.getIsLockable()) {
					textureName = blockChest.getTextureName();
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/vacuumChest" + textureName + ".png"));
				} else {
					textureName = blockChest.getTextureName();
					this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/vacuumChest" + textureName + ".png"));
				}
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);

			// GL11.glScalef(1.0F, -1.0F, -1.0F);
			if (tileEntityVacuumChest.getState() == 0) {
				GL11.glScalef(0.65F, -0.65F, -0.65F);
				GL11.glTranslatef(0.26F, 0.53F, 0.26F);
			} else if (tileEntityVacuumChest.getState() == 1) {
				GL11.glScalef(0.8F, -0.8F, -0.8F);
				GL11.glTranslatef(0.12F, 0.25F, 0.12F);
			} else if (tileEntityVacuumChest.getState() == 2) {
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
			float adjustedLidAngle = tileEntityVacuumChest.prevLidAngle + (tileEntityVacuumChest.lidAngle - tileEntityVacuumChest.prevLidAngle) * tick;
			adjustedLidAngle = 1.0F - adjustedLidAngle;
			adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
			modelChest.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2.0F);
			modelChest.renderAll();

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null) {
				if (Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() != null && Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() instanceof ItemDebugTool) {
					GL11.glPushMatrix();
					GL11.glTranslatef((float) x, (float) y, (float) z);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glTranslatef(0.5F, 0.5F, 0.5F);
					for (int yArea = (int) tileEntityVacuumChest.minY; yArea <= tileEntityVacuumChest.maxY; yArea++) {
						for (int xArea = (int) tileEntityVacuumChest.minX; xArea <= tileEntityVacuumChest.maxX; xArea++) {
							for (int zArea = (int) tileEntityVacuumChest.minZ; zArea <= tileEntityVacuumChest.maxZ; zArea++) {

								Tessellator t = Tessellator.instance;
								t.startDrawingQuads();
								this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/debugBlock.png"));
								t.setColorRGBA(255, 0, 255, 200);

								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 1);

								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 0, 1);

								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 0, 1);

								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 1);

								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 1 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 1);

								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 1);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 1 + zArea + (zArea * 1F), 1, 0);
								t.addVertexWithUV(0 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 0);
								t.addVertexWithUV(1 + xArea + (xArea * 1F), 0 + yArea + (yArea * 1F), 0 + zArea + (zArea * 1F), 0, 1);

								t.draw();
							}
						}
					}
					GL11.glPopMatrix();
				}
			}

		}
	}
}

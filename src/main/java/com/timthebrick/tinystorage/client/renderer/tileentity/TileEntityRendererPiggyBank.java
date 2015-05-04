package com.timthebrick.tinystorage.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityPiggyBank;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityTinyChest;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRendererPiggyBank extends TileEntitySpecialRenderer {

	private final ModelPig modelPig = new ModelPig();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		if (tileEntity instanceof TileEntityPiggyBank) {
			TileEntityPiggyBank tileEntityPiggyBank = (TileEntityPiggyBank) tileEntity;
			ForgeDirection direction = null;
			String textureName = "";

			if (tileEntityPiggyBank.getWorldObj() != null) {
				direction = tileEntityPiggyBank.getOrientation();
			}

			World world = tileEntityPiggyBank.getWorldObj();
			Block block = world.getBlock(tileEntityPiggyBank.xCoord, tileEntityPiggyBank.yCoord, tileEntityPiggyBank.zCoord);

			this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/piggyBank.png"));

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			
			float translateX = 0F;
			float translateY = 0F;
			float translateZ = 0F;
			
			if (tileEntityPiggyBank.getState() == 0) {
				GL11.glScalef(0.65F, -0.65F, -0.65F);
				GL11.glTranslatef(0.26F, 0.53F, 0.26F);
				translateZ = -0.1F;
			} else if (tileEntityPiggyBank.getState() == 1) {
				GL11.glScalef(0.8F, -0.8F, -0.8F);
				GL11.glTranslatef(0.12F, 0.25F, 0.12F);
				translateZ = 0.05F;
			} else if (tileEntityPiggyBank.getState() == 2) {
				GL11.glScalef(1.0F, -1.0F, -1.0F);
				translateZ = 0.187F;
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
			GL11.glTranslatef(0F + translateX, -1F + translateY, 0F + translateZ);
			float adjustedLidAngle = tileEntityPiggyBank.getHeadAngle();
			modelPig.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, adjustedLidAngle, 0.0625F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}

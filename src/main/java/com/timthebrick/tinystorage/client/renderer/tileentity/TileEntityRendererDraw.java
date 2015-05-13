package com.timthebrick.tinystorage.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.timthebrick.tinystorage.block.BlockDraw;
import com.timthebrick.tinystorage.block.BlockFilterChest;
import com.timthebrick.tinystorage.client.renderer.model.ModelDraw;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityDraw;
import com.timthebrick.tinystorage.tileentity.implementations.TileEntityFilterChest;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRendererDraw extends TileEntitySpecialRenderer {

	private final ModelDraw modelDraw = new ModelDraw();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		if (tileEntity instanceof TileEntityDraw) {
			TileEntityDraw tileEntityDraw = (TileEntityDraw) tileEntity;
			ForgeDirection direction = null;
			String textureName = "";

			if (tileEntityDraw.getWorldObj() != null) {
				direction = tileEntityDraw.getOrientation();
			}

			World world = tileEntityDraw.getWorldObj();
			Block block = world.getBlock(tileEntityDraw.xCoord, tileEntityDraw.yCoord, tileEntityDraw.zCoord);

			if (block instanceof BlockDraw) {
				textureName = ((BlockDraw) block).getTextureName();
			}

			this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/draws/draw" + textureName + ".png"));

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			GL11.glTranslatef((float) x+0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			
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
			modelDraw.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

}

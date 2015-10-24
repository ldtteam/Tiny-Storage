package com.smithsmodding.tinystorage.client.renderer.tileentity;

import com.smithsmodding.tinystorage.common.block.storage.chests.BlockImpossibleChest;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityImpossibleChest;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityRendererImpossibleChest extends TileEntitySpecialRenderer {

    private final ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        if (tileEntity instanceof TileEntityImpossibleChest) {
            TileEntityImpossibleChest tileEntityImpossibleChest = (TileEntityImpossibleChest) tileEntity;
            ForgeDirection direction = null;

            if (tileEntityImpossibleChest.getWorldObj() != null) {
                direction = tileEntityImpossibleChest.getOrientation();
            }

            World world = tileEntityImpossibleChest.getWorldObj();
            Block block = world.getBlock(tileEntityImpossibleChest.xCoord, tileEntityImpossibleChest.yCoord, tileEntityImpossibleChest.zCoord);

            if (block instanceof BlockImpossibleChest) {
                BlockImpossibleChest blockImpossibleChest = (BlockImpossibleChest) block;
                if (!blockImpossibleChest.getIsLockable()) {
                    this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/chestImpossible.png"));
                } else {
                    this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/chestImpossible.png"));
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            // GL11.glScalef(1.0F, -1.0F, -1.0F);
            /*
            if (tileEntityImpossibleChest.getState() == 0) {
                GL11.glScalef(0.65F, -0.65F, -0.65F);
                GL11.glTranslatef(0.26F, 0.53F, 0.26F);
            } else if (tileEntityImpossibleChest.getState() == 1) {
                GL11.glScalef(0.8F, -0.8F, -0.8F);
                GL11.glTranslatef(0.12F, 0.25F, 0.12F);
            } else if (tileEntityImpossibleChest.getState() == 2) {*/
                GL11.glScalef(1.0F, -1.0F, -1.0F);
            //}
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
            float adjustedLidAngle = tileEntityImpossibleChest.prevLidAngle + (tileEntityImpossibleChest.lidAngle - tileEntityImpossibleChest.prevLidAngle) * tick;
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

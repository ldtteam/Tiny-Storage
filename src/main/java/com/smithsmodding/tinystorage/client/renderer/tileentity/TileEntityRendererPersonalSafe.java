package com.smithsmodding.tinystorage.client.renderer.tileentity;

import com.smithsmodding.tinystorage.client.renderer.model.ModelPersonalSafe;
import com.smithsmodding.tinystorage.common.block.storage.BlockPersonalSafe;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityPersonalSafe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityRendererPersonalSafe extends TileEntitySpecialRenderer {

    private final ModelPersonalSafe modelPersonalSafe = new ModelPersonalSafe();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        if (tileEntity instanceof TileEntityPersonalSafe) {
            TileEntityPersonalSafe tileEntityPersonalSafe = (TileEntityPersonalSafe) tileEntity;
            ForgeDirection direction = null;
            String textureName = "";

            if (tileEntityPersonalSafe.getWorldObj() != null) {
                direction = tileEntityPersonalSafe.getOrientation();
            }

            World world = tileEntityPersonalSafe.getWorldObj();
            Block block = world.getBlock(tileEntityPersonalSafe.xCoord, tileEntityPersonalSafe.yCoord, tileEntityPersonalSafe.zCoord);

            BlockPersonalSafe blockPersonalSafe = (BlockPersonalSafe) block;

            this.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/personalSafe.png"));

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
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
            modelPersonalSafe.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}

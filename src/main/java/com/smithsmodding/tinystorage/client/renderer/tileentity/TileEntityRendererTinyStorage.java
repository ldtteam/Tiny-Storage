package com.smithsmodding.tinystorage.client.renderer.tileentity;

import com.smithsmodding.tinystorage.api.client.modules.IInWorldRenderingModule;
import com.smithsmodding.tinystorage.api.reference.ModBlocks;
import com.smithsmodding.tinystorage.common.block.BlockChestBase;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

import java.util.Random;

/**
 * Created by Tim on 26/06/2016.
 */
public class TileEntityRendererTinyStorage extends TileEntitySpecialRenderer<TileEntityTinyStorage> {


    private static float[][] shifts = {{0.3F, 0.45F, 0.3F}, {0.7F, 0.45F, 0.3F}, {0.3F, 0.45F, 0.7F}, {0.7F, 0.45F, 0.7F}, {0.3F, 0.1F, 0.3F},
            {0.7F, 0.1F, 0.3F}, {0.3F, 0.1F, 0.7F}, {0.7F, 0.1F, 0.7F}, {0.5F, 0.32F, 0.5F},};
    private Random random;
    private RenderEntityItem itemRenderer;
    private ModelChest model;

    public TileEntityRendererTinyStorage() {
        model = new ModelChest();
        random = new Random();
        itemRenderer = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()) {
            @Override
            public boolean shouldBob() {
                return false;
            }

            @Override
            public boolean shouldSpreadItems() {
                return false;
            }
        };
    }

    public void render(TileEntityTinyStorage tile, double x, double y, double z, float partialTick, int breakStage) {
        EnumFacing facing = EnumFacing.NORTH;

        if (tile != null && tile.hasWorldObj() && tile.getWorld().getBlockState(tile.getPos()).getBlock() == ModBlocks.blockChest) {
            //TODO: PULL DIRECTION FROM BLOCKSTATE

            IBlockState state = tile.getWorld().getBlockState(tile.getPos());
            facing = state.getValue(BlockChestBase.FACING);
        }

        if (breakStage >= 0) {
            bindTexture(DESTROY_STAGES[breakStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }

        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GlStateManager.scale(1.0F, -1F, -1F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        int k = 0;
        if (facing == EnumFacing.SOUTH) {
            k = 180;
        }
        if (facing == EnumFacing.WEST) {
            k = 90;
        }
        if (facing == EnumFacing.EAST) {
            k = -90;
        }
        GlStateManager.rotate(k, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float lidangle = tile.getState().getPrevLidAngle() + (tile.getState().getLidAngle() - tile.getState().getPrevLidAngle()) * partialTick;
        lidangle = 1.0F - lidangle;
        lidangle = 1.0F - lidangle * lidangle * lidangle;
        model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);

        tile.getInstalledModules().values().stream().filter(module -> module instanceof IInWorldRenderingModule && ((IInWorldRenderingModule) module).shouldRender(model, itemRenderer, random)).forEach(module -> ((IInWorldRenderingModule) module).doRender(model, itemRenderer, random));

        if (breakStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntityTinyStorage tileentity, double x, double y, double z, float partialTick, int breakStage) {
        render(tileentity, x, y, z, partialTick, breakStage);
    }

}

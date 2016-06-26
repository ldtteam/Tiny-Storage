package com.smithsmodding.tinystorage.client.renderer.tileentity;

import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

/**
 * Created by Tim on 26/06/2016.
 */
public class TileEntityRendererTinyStorage extends TileEntitySpecialRenderer<TileEntityTinyStorage> {

    private final ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntityTinyStorage te, double x, double y, double z, float partialTicks, int destroyStage) {

    }

}

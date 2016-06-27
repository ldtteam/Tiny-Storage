package com.smithsmodding.tinystorage.api.client.modules;

import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.client.renderer.tileentity.TileEntityRendererTinyStorage;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Author Orion (Created on: 26.06.2016)
 */
public interface IInWorldRenderingModule extends IModule {

    @SideOnly(Side.CLIENT)
    boolean shouldRender(ModelChest chest, RenderEntityItem itemRenderer, Random random);

    @SideOnly(Side.CLIENT)
    void doRender(ModelChest chest, RenderEntityItem itemRenderer, Random random, TileEntityRendererTinyStorage renderer);
}

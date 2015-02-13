package com.timthebrick.tinystorage.client.renderer.item;

import com.timthebrick.tinystorage.client.renderer.model.ModelDraw;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererDraw implements IItemRenderer{
	
	private final ModelDraw modelDraw;
	private String textureName;

	public ItemRendererDraw(String textureName) {
		modelDraw = new ModelDraw();
		this.textureName = textureName;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
	}

}

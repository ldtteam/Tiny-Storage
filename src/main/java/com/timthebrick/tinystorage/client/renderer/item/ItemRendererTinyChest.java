package com.timthebrick.tinystorage.client.renderer.item;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRendererTinyChest implements IItemRenderer {

	private final ModelChest modelChest;

	public ItemRendererTinyChest() {
		modelChest = new ModelChest();
	}

	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data) {
		switch (itemRenderType) {
		case ENTITY: {
			renderTinyChest(0.5F, 0.5F, 0.5F, itemStack.getItemDamage());
			break;
		}
		case EQUIPPED: {
			renderTinyChest(1.0F, 1.0F, 1.0F, itemStack.getItemDamage());
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			renderTinyChest(1.0F, 1.0F, 1.0F, itemStack.getItemDamage());
			break;
		}
		case INVENTORY: {
			renderTinyChest(0.0F, 0.075F, 0.0F, itemStack.getItemDamage());
			break;
		}
		default:
			break;
		}
	}

	private void renderTinyChest(float x, float y, float z, int metaData) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/tinyChest.png"));

		GL11.glPushMatrix(); // start
		GL11.glTranslatef(x, y, z); // size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		modelChest.renderAll();
		GL11.glPopMatrix(); // end
	}

}

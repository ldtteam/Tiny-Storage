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

public class ItemRendererTrashChest implements IItemRenderer {

	private final ModelChest modelChest;

	public ItemRendererTrashChest() {
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
		// Scaling
		float scale = 1F;
		// Translating
		float transX = 0F, transY = 0F, transZ = 0F;

		// Bind texture, scale
		if (itemStack.getItemDamage() == 0) {
			scale = 0.5F;
		} else if (itemStack.getItemDamage() == 1) {
			scale = 0.75F;
		} else if (itemStack.getItemDamage() == 2) {
			scale = 1F;
		}

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/trashChest.png"));

		// Translate and render
		switch (itemRenderType) {
		case ENTITY: {
			renderTinyChest(0.5F + transX, 0.5F + transY, 0.5F + transZ, scale);
			break;
		}
		case EQUIPPED: {
			transX = 0.3F;
			transY = -0.6F;
			transZ = -0.9F;
			renderTinyChest(0.9F + transX, -1.0F + transY, -1.2F + transZ, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			transX = -0.15F;
			transY = -3F;
			transZ = -2.5F;
			renderTinyChest(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
			break;
		}
		case INVENTORY: {
			transY = 1F;
			renderTinyChest(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
			break;
		}
		default:
			break;
		}
	}

	private void renderTinyChest(float x, float y, float z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale, -scale, -scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-90, 0, 1, 0);
		modelChest.renderAll();
		GL11.glPopMatrix(); // end
	}

}

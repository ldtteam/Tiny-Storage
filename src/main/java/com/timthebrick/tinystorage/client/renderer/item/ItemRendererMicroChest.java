package com.timthebrick.tinystorage.client.renderer.item;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemRendererMicroChest implements IItemRenderer {

	private final ModelChest modelChest;
	private String textureName;
	private boolean isLocked;

	public ItemRendererMicroChest(String textureName, boolean isLocked) {
		modelChest = new ModelChest();
		this.textureName = textureName;
		this.isLocked = isLocked;
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
		scale = 0.5F;

		if (!isLocked) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/chest" + textureName + ".png"));
		} else {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/chest" + textureName + ".png"));
		}

		// Translate and render
		switch (itemRenderType) {
		case ENTITY: {
			renderMicroChest(0.5F + transX, 0.5F + transY, 0.5F + transZ, scale);
			break;
		}
		case EQUIPPED: {
			transX = 0.3F;
			transY = -0.6F;
			transZ = -0.9F;
			renderMicroChest(0.9F + transX, -1.0F + transY, -1.2F + transZ, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			transX = -0.15F;
			transY = -3F;
			transZ = -2.5F;
			renderMicroChest(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
			break;
		}
		case INVENTORY: {
			transY = 1F;
			renderMicroChest(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
			break;
		}
		default:
			break;
		}
	}

	private void renderMicroChest(float x, float y, float z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale, -scale, -scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-90, 0, 1, 0);
		modelChest.renderAll();
		GL11.glPopMatrix(); // end
	}

}

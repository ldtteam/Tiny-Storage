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
	private String textureName;

	public ItemRendererTinyChest(String textureName) {
		modelChest = new ModelChest();
		this.textureName = textureName;
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

		// Bind texture, scale and translate
		if (itemStack.getItemDamage() == 0) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/tinyChest" + textureName + "_SMALL.png"));
			scale = 0.5F;
		} else if (itemStack.getItemDamage() == 1) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/tinyChest" + textureName + "_MEDIUM.png"));
			scale = 0.75F;
		} else if (itemStack.getItemDamage() == 2) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/tinyChest" + textureName + "_LARGE.png"));
			scale = 1F;
		}

		switch (itemRenderType) {
		case ENTITY: {
			renderTinyChest(0.5F, 0.5F, 0.5F, scale);
			break;
		}
		case EQUIPPED: {

			renderTinyChest(0.9F, -1.0F, -1.2F, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			renderTinyChest(1.0F, 1.0F, 1.0F, scale);
			break;
		}
		case INVENTORY: {
			renderTinyChest(0.0F, -0.075F, 0.0F, scale);
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

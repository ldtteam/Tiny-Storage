package com.timthebrick.tinystorage.client.renderer.item;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.client.renderer.model.ModelDraw;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelChest;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererDraw implements IItemRenderer {

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
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
		// Scaling
		float scale = 1F;
		// Translating
		float transX = 0F, transY = 0F, transZ = 0F;

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/draws/draw" + textureName + ".png"));

		switch (type) {
		case ENTITY: {
			renderDraw(0.5F + transX, 0.5F + transY, 0.5F + transZ, scale);
			break;
		}
		case EQUIPPED: {
			renderDraw(0.9F + transX, -1.0F + transY, -1.2F + transZ, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			transX = -0.15F;
			transY = -2.2F;
			transZ = -2F;
			renderDraw(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
			break;
		}
		case INVENTORY: {
			renderDraw(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
			break;
		}
		default:
			break;
		}
	}

	private void renderDraw(float x, float y, float z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale, -scale, -scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-90, 0, 1, 0);
		modelDraw.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix(); // end
	}

}

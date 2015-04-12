package com.timthebrick.tinystorage.client.renderer.item;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.client.renderer.model.ModelDraw;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemRendererPiggyBank implements IItemRenderer {

	private final ModelPig modelPig;

	public ItemRendererPiggyBank() {
		modelPig = new ModelPig();
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

		// Bind texture, scale
		if (itemStack.getItemDamage() == 0) {
			scale = 0.5F;
		} else if (itemStack.getItemDamage() == 1) {
			scale = 0.75F;
		} else if (itemStack.getItemDamage() == 2) {
			scale = 1F;
		}

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/piggyBank.png"));

		switch (type) {
		case ENTITY: {
			renderPiggyBank(0.5F + transX, -0.75F, 0.5F + transZ, scale);
			break;
		}
		case EQUIPPED: {
			if (itemStack.getItemDamage() == 0) {
				transX = -0.25F;
				transY = -2.2F;
				transZ = -1.5F;
			} else if (itemStack.getItemDamage() == 1) {
				transX = -0.15F;
				transY = -1.5F;
				transZ = -1F;
			} else if (itemStack.getItemDamage() == 2) {
				transX = -0.2F;
				transY = -1.4F;
				transZ = -1F;
			}
			renderPiggyBank(0 + transX, 0 + transY, 0 + transZ, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			if (itemStack.getItemDamage() == 0) {
				transX = -0.15F;
				transY = -3F;
				transZ = -2.5F;
			} else if (itemStack.getItemDamage() == 1) {
				transX = -0.15F;
				transY = -2.4F;
				transZ = -2F;
			} else if (itemStack.getItemDamage() == 2) {
				transX = -0.15F;
				transY = -2.2F;
				transZ = -2F;
			}
			renderPiggyBank(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
			break;
		}
		case INVENTORY: {
			if (itemStack.getItemDamage() == 0) {

			} else if (itemStack.getItemDamage() == 1) {
				transY = -0.5F;
			}else if (itemStack.getItemDamage() == 2){
				transY = -0.75F;
			}
			renderPiggyBank(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
			break;
		}
		default:
			break;
		}
	}

	private void renderPiggyBank(float x, float y, float z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale, -scale, -scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-90, 0, 1, 0);
		modelPig.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix(); // end
	}

}

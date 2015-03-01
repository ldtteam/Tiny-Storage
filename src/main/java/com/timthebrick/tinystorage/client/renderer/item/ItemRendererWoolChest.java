package com.timthebrick.tinystorage.client.renderer.item;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRendererWoolChest implements IItemRenderer {

	public static final String[] textureNames = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };
	private final ModelChest modelChest;
	private boolean isLocked;
	private String textureName;
	private int size;

	public ItemRendererWoolChest(int size, boolean isLocked) {
		modelChest = new ModelChest();
		this.isLocked = isLocked;
		this.size = size;
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
		if (size == 0) {
			scale = 0.5F;
		} else if (size == 1) {
			scale = 0.75F;
		} else if (size == 2) {
			scale = 1F;
		}

		int chestMeta = itemStack.getItemDamage();
		textureName = textureNames[chestMeta];

		if (!isLocked) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/unlocked/chestWool_" + textureName + ".png"));
		} else {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/chests/locked/chestWool_" + textureName + ".png"));
		}

		// Translate and render
		switch (itemRenderType) {
		case ENTITY: {
			renderWoolChest(0.5F + transX, 0.5F + transY, 0.5F + transZ, scale);
			break;
		}
		case EQUIPPED: {
			if (size == 0) {
				transX = 0.3F;
				transY = -0.6F;
				transZ = -0.9F;
			}
			renderWoolChest(0.9F + transX, -1.0F + transY, -1.2F + transZ, scale);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			if (size == 0) {
				transX = -0.15F;
				transY = -3F;
				transZ = -2.5F;
			} else if (size == 1) {
				transX = -0.15F;
				transY = -2.4F;
				transZ = -2F;
			} else if (size == 2) {
				transX = -0.15F;
				transY = -2.2F;
				transZ = -2F;
			}
			renderWoolChest(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
			break;
		}
		case INVENTORY: {
			if (size == 0) {
				transY = 1F;
			} else if (size == 1) {
				transY = 0.35F;
			}
			renderWoolChest(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
			break;
		}
		default:
			break;
		}
	}

	private void renderWoolChest(float x, float y, float z, float scale) {
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale, -scale, -scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-90, 0, 1, 0);
		modelChest.renderAll();
		GL11.glPopMatrix(); // end
	}

}

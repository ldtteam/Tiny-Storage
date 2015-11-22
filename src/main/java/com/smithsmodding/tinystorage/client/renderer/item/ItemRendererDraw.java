package com.smithsmodding.tinystorage.client.renderer.item;

import com.smithsmodding.tinystorage.common.reference.References;
import org.lwjgl.opengl.GL11;

import com.smithsmodding.tinystorage.client.renderer.model.ModelDraw;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererDraw implements IItemRenderer {

	private final ModelDraw modelDraw;
	private String textureName;
    private boolean isLocked;

	public ItemRendererDraw(String textureName, boolean isLocked) {
		modelDraw = new ModelDraw();
		this.textureName = textureName;
        this.isLocked = isLocked;
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

        if(!isLocked) {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/draws/unlocked/draw" + textureName + ".png"));
        }else{
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/draws/locked/draw" + textureName + ".png"));
        }

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
			renderDraw(0.0F + transX, -1.025F + transY, 0.0F + transZ, scale);
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

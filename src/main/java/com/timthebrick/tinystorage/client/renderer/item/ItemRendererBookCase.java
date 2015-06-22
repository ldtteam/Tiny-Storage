package com.timthebrick.tinystorage.client.renderer.item;

import com.timthebrick.tinystorage.client.renderer.model.ModelBookCase;
import com.timthebrick.tinystorage.reference.References;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererBookCase implements IItemRenderer {

    private final ModelBookCase modelBookCase;
    private String textureName;

    public ItemRendererBookCase (String textureName) {
        modelBookCase = new ModelBookCase();
        this.textureName = textureName;
    }

    @Override
    public boolean handleRenderType (ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper (ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem (ItemRenderType type, ItemStack itemStack, Object... data) {
        // Scaling
        float scale = 1F;
        // Translating
        float transX = 0F, transY = 0F, transZ = 0F;

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/models/bookcases/bookCase" + textureName + ".png"));

        switch (type) {
            case ENTITY: {
                renderBookCase(0.5F + transX, 0.5F + transY, 0.5F + transZ, scale);
                break;
            }
            case EQUIPPED: {
                renderBookCase(0.9F + transX, -1.0F + transY, -1.2F + transZ, scale);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                transX = -0.15F;
                transY = -2.2F;
                transZ = -2F;
                renderBookCase(1.0F + transX, 1.0F + transY, 1.0F + transZ, scale);
                break;
            }
            case INVENTORY: {
                renderBookCase(0.0F + transX, -0.075F + transY, 0.0F + transZ, scale);
                break;
            }
            default:
                break;
        }
    }

    private void renderBookCase (float x, float y, float z, float scale) {
        GL11.glPushMatrix(); // start
        GL11.glScalef(scale, -scale, -scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90, 0, 1, 0);
        modelBookCase.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix(); // end
    }
}

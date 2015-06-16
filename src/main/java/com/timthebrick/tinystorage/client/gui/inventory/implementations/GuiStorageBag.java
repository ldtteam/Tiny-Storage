package com.timthebrick.tinystorage.client.gui.inventory.implementations;

import com.timthebrick.tinystorage.inventory.implementations.ContainerStorageBag;
import com.timthebrick.tinystorage.inventory.implementations.InventoryStorageBag;
import com.timthebrick.tinystorage.reference.Colours;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.util.NBTHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiStorageBag extends GuiContainer {
    private final ItemStack parentItemStack;
    private final InventoryStorageBag inventoryStorageBag;

    public GuiStorageBag (EntityPlayer entityPlayer, InventoryStorageBag inventoryStorageBag) {
        super(new ContainerStorageBag(entityPlayer, inventoryStorageBag));

        this.parentItemStack = inventoryStorageBag.parentItemStack;
        this.inventoryStorageBag = inventoryStorageBag;

        if (this.parentItemStack.getItemDamage() == 0) {
            xSize = 230;
            ySize = 186;
        } else if (this.parentItemStack.getItemDamage() == 1) {
            xSize = 230;
            ySize = 240;
        } else if (this.parentItemStack.getItemDamage() == 2) {
            xSize = 248;
            ySize = 256;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer (int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal(inventoryStorageBag.getInventoryName()), 8, 6, Integer.parseInt(Colours.PURE_WHITE, 16));
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 180, Integer.parseInt(Colours.PURE_WHITE, 16));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer (float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.parentItemStack.getItemDamage() == 0) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiChestSmall.png"));
        } else if (this.parentItemStack.getItemDamage() == 1) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiChestMedium.png"));
        } else if (this.parentItemStack.getItemDamage() == 2) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MOD_ID + ":textures/gui/guiChestLarge.png"));
        }
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed () {
        super.onGuiClosed();
        if (mc.thePlayer != null) {
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }

    @Override
    protected boolean checkHotbarKeys (int key) {
        return false;
    }
}

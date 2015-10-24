package com.smithsmodding.tinystorage.client.gui.inventory.implementations;

import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.util.client.Colours;
import com.smithsmodding.tinystorage.common.inventory.implementations.ContainerStorageBag;
import com.smithsmodding.tinystorage.common.inventory.implementations.InventoryStorageBag;
import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.util.common.NBTHelper;
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
            xSize = 176;
            ySize = 133;
        } else if (this.parentItemStack.getItemDamage() == 1) {
            xSize = 176;
            ySize = 151;
        } else if (this.parentItemStack.getItemDamage() == 2) {
            xSize = 176;
            ySize = 169;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer (int x, int y) {
        fontRendererObj.drawString(StatCollector.translateToLocal(inventoryStorageBag.getInventoryName()), 8, 6, Colours.INV_GRAY);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 95 + 2, Colours.INV_GRAY);
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

package com.smithsmodding.tinystorage.client.gui.modularchest;

import com.smithsmodding.smithscore.client.gui.components.core.ComponentConnectionType;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentBorder;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentPlayerInventory;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentSlot;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.smithsmodding.smithscore.client.gui.state.IGUIComponentState;
import com.smithsmodding.smithscore.client.gui.state.SlotComponentState;
import com.smithsmodding.smithscore.client.gui.tabs.implementations.CoreTab;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import com.smithsmodding.smithscore.util.client.color.Colors;
import com.smithsmodding.smithscore.util.client.color.MinecraftColor;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.inventory.ContainerTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 01/07/2016.
 */
public class TabCoreModule extends CoreTab {

    private GuiTinyStorage guiTinyStorage;
    private TileEntityTinyStorage tileEntity;

    public TabCoreModule(String uniqueID, IGUIBasedTabHost root, IGUIComponentState state, ItemStack displayStack, MinecraftColor tabColor, String toolTipString) {
        super(uniqueID, root, state, displayStack, tabColor, toolTipString);
        guiTinyStorage = (GuiTinyStorage) root;
        tileEntity = ((ContainerTinyStorage) guiTinyStorage.container).getTileEntity();
    }

    @Override
    public void registerComponents(IGUIBasedComponentHost host) {
        host.registerNewComponent(new ComponentBorder(References.GUIs.Components.TinyChest.BACKGROUND, host, new Coordinate2D(0, 0), ComponentPlayerInventory.WIDTH + 50, 150, Colors.DEFAULT, ComponentBorder.CornerTypes.Inwards, ComponentBorder.CornerTypes.Inwards, ComponentBorder.CornerTypes.Inwards, ComponentBorder.CornerTypes.Inwards));
        for (int i = 0; i < tileEntity.getCoreModule().getSizeInventory(); i++) {
            Slot slot = host.getRootGuiObject().inventorySlots.inventorySlots.get(i);
            host.registerNewComponent(new ComponentSlot(References.GUIs.Components.SLOT_CORE + i, new SlotComponentState(null, slot, tileEntity.getCoreModule(), null), host, slot, Colors.DEFAULT));
        }
        host.registerNewComponent(new ComponentPlayerInventory(References.GUIs.Components.PLAYER_INVENTORY, host, new Coordinate2D(25, 147), Colors.DEFAULT, ((ContainerSmithsCore) (guiTinyStorage.inventorySlots)).getPlayerInventory(), ComponentConnectionType.BELOWSMALLER));
    }
}

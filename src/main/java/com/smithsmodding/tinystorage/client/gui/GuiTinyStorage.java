package com.smithsmodding.tinystorage.client.gui;

import com.smithsmodding.smithscore.client.gui.GuiContainerSmithsCore;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentBorder;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import com.smithsmodding.smithscore.util.client.color.Colors;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import com.smithsmodding.tinystorage.api.reference.References;

/**
 * Created by Tim on 30/06/2016.
 */
public class GuiTinyStorage extends GuiContainerSmithsCore {

    public GuiTinyStorage(ContainerSmithsCore container) {
        super(container);
    }

    @Override
    public void registerComponents(IGUIBasedComponentHost host) {
        host.registerNewComponent(new ComponentBorder(References.GUIs.Components.TinyChest.BACKGROUND, host, new Coordinate2D(0, 0), 150, 150, Colors.DEFAULT, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts));
    }

    @Override
    public void registerLedgers(IGUIBasedLedgerHost parent) {
    }
}

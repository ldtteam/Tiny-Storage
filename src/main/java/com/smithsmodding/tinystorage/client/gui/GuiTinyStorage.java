package com.smithsmodding.tinystorage.client.gui;

import com.smithsmodding.smithscore.client.gui.GuiContainerSmithsCore;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;

/**
 * Created by Tim on 30/06/2016.
 */
public class GuiTinyStorage extends GuiContainerSmithsCore {

    public GuiTinyStorage(ContainerSmithsCore container) {
        super(container);
    }

    @Override
    public void registerTabs(IGUIBasedTabHost host) {
        super.registerTabs(host);
    }

    @Override
    public void registerLedgers(IGUIBasedLedgerHost parent) {

    }
}

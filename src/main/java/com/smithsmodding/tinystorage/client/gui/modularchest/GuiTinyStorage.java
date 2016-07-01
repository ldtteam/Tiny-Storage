package com.smithsmodding.tinystorage.client.gui.modularchest;

import com.smithsmodding.smithscore.client.gui.GuiContainerSmithsCore;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentBorder;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.smithsmodding.smithscore.client.gui.state.CoreComponentState;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import com.smithsmodding.smithscore.util.client.color.Colors;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.registry.ModuleRegistry;

/**
 * Created by Tim on 30/06/2016.
 */
public class GuiTinyStorage extends GuiContainerSmithsCore {

    public GuiTinyStorage(ContainerSmithsCore container) {
        super(container);
    }

    @Override
    public void registerTabs(IGUIBasedTabHost host) {
        host.registerNewTab(new TabCoreModule(References.GUIs.Tabs.CORE_MODULE, host, new CoreComponentState(), ModuleRegistry.getInstance().getStackForModule(ModuleRegistry.getInstance().getModule(References.Modules.ModuleNames.LARGE_STORAGE_MODULE)), Colors.DEFAULT, ""));
    }

    @Override
    public void registerComponents(IGUIBasedComponentHost host) {
    }

    @Override
    public void registerLedgers(IGUIBasedLedgerHost parent) {
    }
}

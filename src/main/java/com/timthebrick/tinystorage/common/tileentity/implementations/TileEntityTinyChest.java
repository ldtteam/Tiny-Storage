package com.timthebrick.tinystorage.common.tileentity.implementations;

import com.timthebrick.tinystorage.common.init.Registration;
import com.timthebrick.tinystorage.common.reference.ChestSize;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;

public class TileEntityTinyChest extends TileEntityTinyStorage {

    public TileEntityTinyChest() {
        super(Registration.TINY_CHEST_STONE_TE.get());
    }

    public TileEntityTinyChest(ChestSize size) {
        super(Registration.TINY_CHEST_STONE_TE.get());
    }
}

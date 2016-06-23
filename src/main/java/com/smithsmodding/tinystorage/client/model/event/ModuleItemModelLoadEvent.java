package com.smithsmodding.tinystorage.client.model.event;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.smithscore.client.model.deserializers.MultiComponentModelDeserializer;
import com.smithsmodding.smithscore.client.model.deserializers.definition.MultiComponentModelDefinition;
import com.smithsmodding.smithscore.common.events.SmithsCoreEvent;
import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.client.model.loader.ModuleItemModelLoader;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Author Orion (Created on: 23.06.2016)
 */
public class ModuleItemModelLoadEvent extends SmithsCoreEvent {

    private ArrayList<MultiComponentModelDefinition> additionalDefinitions = new ArrayList<>();

    public void registerNewLocation(ResourceLocation location) {
        try {
            if (!ModuleItemModelLoader.instance.accepts(location))
                throw new Exception("Cannot load the given additional model location: " + location.toString() + " as the MIM model loader does not accept it!");

            additionalDefinitions.add(MultiComponentModelDeserializer.instance.deserialize(location));
        } catch (Exception e) {
            TinyStorage.getLogger().error(new Exception("Failed to load additional models from: " + location.toString(), e));
        }
    }

    public ImmutableList<MultiComponentModelDefinition> getAdditionalDefinitions() {
        return ImmutableList.copyOf(additionalDefinitions);
    }
}

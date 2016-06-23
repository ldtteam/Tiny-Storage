package com.smithsmodding.tinystorage.client.model.loader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.smithsmodding.smithscore.client.model.deserializers.MultiComponentModelDeserializer;
import com.smithsmodding.smithscore.client.model.deserializers.definition.MultiComponentModelDefinition;
import com.smithsmodding.smithscore.client.model.loader.MultiComponentModelLoader;
import com.smithsmodding.smithscore.client.model.unbaked.DummyModel;
import com.smithsmodding.smithscore.client.model.unbaked.ItemLayerModel;
import com.smithsmodding.smithscore.client.model.unbaked.MultiComponentModel;
import com.smithsmodding.smithscore.util.client.ModelHelper;
import com.smithsmodding.tinystorage.client.model.event.ModuleItemModelLoadEvent;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author Orion (Created on: 23.06.2016)
 */
public class ModuleItemModelLoader implements ICustomModelLoader {
    public static final ModuleItemModelLoader instance = new ModuleItemModelLoader();
    public static final String EXTENSION = "MIM-TinyStorage";

    private ArrayList<String> acceptedDomains = new ArrayList<>();

    private ModuleItemModelLoader() {
    }

    public void registerDomain(String domain) {
        acceptedDomains.add(domain.toLowerCase());
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        if (!modelLocation.getResourcePath().endsWith(EXTENSION)) {
            return false;
        }
        for (String domain : acceptedDomains)
            if (modelLocation.getResourceDomain().toLowerCase().equals(domain)) {
                return true;
            }
        return false;
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
            return DummyModel.INSTANCE;
        }
        modelLocation = ModelHelper.getModelLocation(modelLocation);

        MultiComponentModelDefinition definition = MultiComponentModelDeserializer.instance.deserialize(modelLocation);

        ModuleItemModelLoadEvent event = new ModuleItemModelLoadEvent();
        event.PostClient();

        ImmutableMap.Builder<String, ResourceLocation> texureBuilder = new ImmutableMap.Builder();
        ImmutableMap.Builder<ItemCameraTransforms.TransformType, TRSRTransformation> transformationBuilder = new ImmutableMap.Builder<>();

        texureBuilder.putAll(definition.getTextureLocations());
        transformationBuilder.putAll(definition.getTransforms());

        for (MultiComponentModelDefinition additionDefinition : event.getAdditionalDefinitions()) {
            texureBuilder.putAll(additionDefinition.getTextureLocations());
            transformationBuilder.putAll(additionDefinition.getTransforms());
        }

        definition = new MultiComponentModelDefinition(texureBuilder.build(), transformationBuilder.build());

        ImmutableMap.Builder<String, IModel> builder = new ImmutableMap.Builder<>();
        for (Map.Entry<String, ResourceLocation> component : definition.getTextureLocations().entrySet()) {
            builder.put(component.getKey(), new ItemLayerModel(ImmutableList.of(component.getValue())));
        }

        return new MultiComponentModel(builder.build(), definition.getTransforms());
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        ///NOOP
    }
}

package com.smithsmodding.tinystorage.client.model.unbaked;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.client.modules.IModelProvidingModule;
import com.smithsmodding.tinystorage.api.client.registries.IModuleModelRegistry;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.common.registry.ModuleRegistry;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;

import java.util.Collection;

/**
 * Author Orion (Created on: 29.06.2016)
 */
public class ChestBlockModel implements IModel {

    private final IModuleModelRegistry modelRegistry;

    public ChestBlockModel(IModuleModelRegistry modelRegistry) {
        this.modelRegistry = modelRegistry;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        ImmutableList.Builder<ResourceLocation> builder = new ImmutableList.Builder<>();

        for (IModule module : ModuleRegistry.getInstance().getAllBuildableModules()) {
            IModel model = modelRegistry.getModelForModule(module.getUniqueID());

            if (model != null)
                builder.add(((IModelProvidingModule) module).getModelLocation());
        }

        return builder.build();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        ImmutableList.Builder<ResourceLocation> builder = new ImmutableList.Builder<>();

        for (IModule module : ModuleRegistry.getInstance().getAllBuildableModules()) {
            if (!(module instanceof IModelProvidingModule))
                continue;

            IModel subModel = ModelLoaderRegistry.getModelOrMissing(((IModelProvidingModule) module).getModelLocation());
            builder.addAll(subModel.getTextures());
        }

        return builder.build();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return null;
    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }
}

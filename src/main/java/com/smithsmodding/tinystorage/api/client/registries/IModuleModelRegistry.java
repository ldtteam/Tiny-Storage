package com.smithsmodding.tinystorage.api.client.registries;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.smithsmodding.tinystorage.api.client.modules.IModelProvidingModule;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

import java.util.Collection;

/**
 * Author Orion (Created on: 30.06.2016)
 */
public interface IModuleModelRegistry {

    void registerModuleModel(IModelProvidingModule module, IModel model);

    void registerModuleModel(String Id, IModel model);

    IModel getModelForModule(String moduleId);

    IModel getModelForModule(IModelProvidingModule module);

    Collection<ResourceLocation> getTextures();

    ImmutableMap<String, IBakedModel> bakeAll(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter);
}

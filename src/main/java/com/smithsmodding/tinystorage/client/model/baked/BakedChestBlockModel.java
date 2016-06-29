package com.smithsmodding.tinystorage.client.model.baked;

import com.smithsmodding.smithscore.client.model.baked.BakedWrappedModel;
import com.smithsmodding.smithscore.util.client.ModelHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

/**
 * Author Orion (Created on: 29.06.2016)
 */
public class BakedChestBlockModel extends BakedWrappedModel.PerspectiveAware {

    public BakedChestBlockModel(IBakedModel parentModel) {
        super(parentModel, ModelHelper.DEFAULT_ITEM_TRANSFORMS);
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return null;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
    }
}

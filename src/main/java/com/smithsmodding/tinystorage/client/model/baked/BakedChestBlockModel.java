package com.smithsmodding.tinystorage.client.model.baked;

import com.smithsmodding.smithscore.client.model.baked.BakedWrappedModel;
import com.smithsmodding.smithscore.util.client.ModelHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Author Orion (Created on: 29.06.2016)
 */
public class BakedChestBlockModel extends BakedWrappedModel.PerspectiveAware {


    public BakedChestBlockModel(IBakedModel parentModel) {
        super(parentModel, ModelHelper.DEFAULT_BLOCK_TRANSFORMS);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return null;
    }
}

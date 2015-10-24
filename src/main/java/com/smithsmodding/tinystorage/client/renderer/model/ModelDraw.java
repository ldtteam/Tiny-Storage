package com.smithsmodding.tinystorage.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDraw extends ModelBase {

	ModelRenderer FrameFrontTop;
	ModelRenderer FrameFrontBottom;
	ModelRenderer FrameFrontLeft;
	ModelRenderer FrameFrontRight;
	ModelRenderer FrameDividerTop;
	ModelRenderer FrameDividerMiddle;
	ModelRenderer FrameBack;
	ModelRenderer FrameLeft;
	ModelRenderer FrameRight;
	ModelRenderer FrameBottom;
	ModelRenderer FrameTop;
	ModelRenderer DrawTop;
	ModelRenderer DrawMiddle;
	ModelRenderer DrawBottom;
	ModelRenderer DrawHandleTop;
	ModelRenderer DrawHandleMiddle;
	ModelRenderer DrawHandleBottom;

	public ModelDraw() {
		textureWidth = 128;
		textureHeight = 128;

		FrameFrontTop = new ModelRenderer(this, 0, 0);
		FrameFrontTop.addBox(0F, 0F, 0F, 14, 1, 1);
		FrameFrontTop.setRotationPoint(-7F, 10F, -7F);
		FrameFrontTop.setTextureSize(128, 128);
		FrameFrontTop.mirror = true;
		setRotation(FrameFrontTop, 0F, 0F, 0F);
		FrameFrontBottom = new ModelRenderer(this, 0, 14);
		FrameFrontBottom.addBox(0F, 0F, 0F, 14, 2, 1);
		FrameFrontBottom.setRotationPoint(-7F, 22F, -7F);
		FrameFrontBottom.setTextureSize(128, 128);
		FrameFrontBottom.mirror = true;
		setRotation(FrameFrontBottom, 0F, 0F, 0F);
		FrameFrontLeft = new ModelRenderer(this, 0, 2);
		FrameFrontLeft.addBox(0F, 0F, 0F, 1, 11, 1);
		FrameFrontLeft.setRotationPoint(-7F, 11F, -7F);
		FrameFrontLeft.setTextureSize(128, 128);
		FrameFrontLeft.mirror = true;
		setRotation(FrameFrontLeft, 0F, 0F, 0F);
		FrameFrontRight = new ModelRenderer(this, 26, 2);
		FrameFrontRight.addBox(0F, 0F, 0F, 1, 11, 1);
		FrameFrontRight.setRotationPoint(6F, 11F, -7F);
		FrameFrontRight.setTextureSize(128, 128);
		FrameFrontRight.mirror = true;
		setRotation(FrameFrontRight, 0F, 0F, 0F);
		FrameDividerTop = new ModelRenderer(this, 30, 47);
		FrameDividerTop.addBox(0F, 0F, 0F, 12, 1, 1);
		FrameDividerTop.setRotationPoint(-6F, 14F, -7F);
		FrameDividerTop.setTextureSize(128, 128);
		FrameDividerTop.mirror = true;
		setRotation(FrameDividerTop, 0F, 0F, 0F);
		FrameDividerMiddle = new ModelRenderer(this, 30, 49);
		FrameDividerMiddle.addBox(0F, 0F, 0F, 12, 1, 1);
		FrameDividerMiddle.setRotationPoint(-6F, 18F, -7F);
		FrameDividerMiddle.setTextureSize(128, 128);
		FrameDividerMiddle.mirror = true;
		setRotation(FrameDividerMiddle, 0F, 0F, 0F);
		FrameBack = new ModelRenderer(this, 0, 17);
		FrameBack.addBox(0F, 0F, 0F, 14, 14, 1);
		FrameBack.setRotationPoint(-7F, 10F, 6F);
		FrameBack.setTextureSize(128, 128);
		FrameBack.mirror = true;
		setRotation(FrameBack, 0F, 0F, 0F);
		FrameLeft = new ModelRenderer(this, 0, 32);
		FrameLeft.addBox(0F, 0F, 0F, 1, 14, 12);
		FrameLeft.setRotationPoint(-7F, 10F, -6F);
		FrameLeft.setTextureSize(128, 128);
		FrameLeft.mirror = true;
		setRotation(FrameLeft, 0F, 0F, 0F);
		FrameRight = new ModelRenderer(this, 0, 58);
		FrameRight.addBox(0F, 0F, 0F, 1, 14, 12);
		FrameRight.setRotationPoint(6F, 10F, -6F);
		FrameRight.setTextureSize(128, 128);
		FrameRight.mirror = true;
		setRotation(FrameRight, 0F, 0F, 0F);
		FrameBottom = new ModelRenderer(this, 30, 0);
		FrameBottom.addBox(0F, 0F, 0F, 12, 1, 12);
		FrameBottom.setRotationPoint(-6F, 23F, -6F);
		FrameBottom.setTextureSize(128, 128);
		FrameBottom.mirror = true;
		setRotation(FrameBottom, 0F, 0F, 0F);
		FrameTop = new ModelRenderer(this, 30, 13);
		FrameTop.addBox(0F, 0F, 0F, 12, 1, 12);
		FrameTop.setRotationPoint(-6F, 10F, -6F);
		FrameTop.setTextureSize(128, 128);
		FrameTop.mirror = true;
		setRotation(FrameTop, 0F, 0F, 0F);
		DrawTop = new ModelRenderer(this, 30, 52);
		DrawTop.addBox(0F, 0F, 0F, 12, 3, 1);
		DrawTop.setRotationPoint(-6F, 11F, -6F);
		DrawTop.setTextureSize(128, 128);
		DrawTop.mirror = true;
		setRotation(DrawTop, 0F, 0F, 0F);
		DrawMiddle = new ModelRenderer(this, 30, 56);
		DrawMiddle.addBox(0F, 0F, 0F, 12, 3, 1);
		DrawMiddle.setRotationPoint(-6F, 15F, -6F);
		DrawMiddle.setTextureSize(128, 128);
		DrawMiddle.mirror = true;
		setRotation(DrawMiddle, 0F, 0F, 0F);
		DrawBottom = new ModelRenderer(this, 30, 60);
		DrawBottom.addBox(0F, 0F, 0F, 12, 3, 1);
		DrawBottom.setRotationPoint(-6F, 19F, -6F);
		DrawBottom.setTextureSize(128, 128);
		DrawBottom.mirror = true;
		setRotation(DrawBottom, 0F, 0F, 0F);
		DrawHandleTop = new ModelRenderer(this, 5, 3);
		DrawHandleTop.addBox(0F, 0F, 0F, 2, 1, 1);
		DrawHandleTop.setRotationPoint(-1F, 12F, -7F);
		DrawHandleTop.setTextureSize(128, 128);
		DrawHandleTop.mirror = true;
		setRotation(DrawHandleTop, 0F, 0F, 0F);
		DrawHandleMiddle = new ModelRenderer(this, 5, 6);
		DrawHandleMiddle.addBox(0F, 0F, 0F, 2, 1, 1);
		DrawHandleMiddle.setRotationPoint(-1F, 16F, -7F);
		DrawHandleMiddle.setTextureSize(128, 128);
		DrawHandleMiddle.mirror = true;
		setRotation(DrawHandleMiddle, 0F, 0F, 0F);
		DrawHandleBottom = new ModelRenderer(this, 5, 9);
		DrawHandleBottom.addBox(0F, 0F, 0F, 2, 1, 1);
		DrawHandleBottom.setRotationPoint(-1F, 20F, -7F);
		DrawHandleBottom.setTextureSize(128, 128);
		DrawHandleBottom.mirror = true;
		setRotation(DrawHandleBottom, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		FrameFrontTop.render(f5);
		FrameFrontBottom.render(f5);
		FrameFrontLeft.render(f5);
		FrameFrontRight.render(f5);
		FrameDividerTop.render(f5);
		FrameDividerMiddle.render(f5);
		FrameBack.render(f5);
		FrameLeft.render(f5);
		FrameRight.render(f5);
		FrameBottom.render(f5);
		FrameTop.render(f5);
		DrawTop.render(f5);
		DrawMiddle.render(f5);
		DrawBottom.render(f5);
		DrawHandleTop.render(f5);
		DrawHandleMiddle.render(f5);
		DrawHandleBottom.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}

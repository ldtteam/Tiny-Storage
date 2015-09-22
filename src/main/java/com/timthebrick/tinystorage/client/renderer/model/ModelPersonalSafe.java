package com.timthebrick.tinystorage.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPersonalSafe extends ModelBase {

    ModelRenderer Back;
    ModelRenderer Left;
    ModelRenderer Right;
    ModelRenderer Base;
    ModelRenderer Top;
    ModelRenderer Control_Panel;
    ModelRenderer Divider_Panel;
    ModelRenderer FrontTop;
    ModelRenderer FrontBottom;
    ModelRenderer TopLeft;
    ModelRenderer Door;

    public ModelPersonalSafe() {
        textureWidth = 128;
        textureHeight = 128;

        Back = new ModelRenderer(this, 0, 0);
        Back.addBox(0F, 0F, 0F, 14, 14, 1);
        Back.setRotationPoint(-7F, 10F, 6F);
        Back.setTextureSize(128, 128);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 0, 22);
        Left.addBox(0F, 0F, 0F, 1, 14, 12);
        Left.setRotationPoint(-7F, 10F, -6F);
        Left.setTextureSize(128, 128);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 27, 22);
        Right.addBox(0F, 0F, 0F, 1, 14, 12);
        Right.setRotationPoint(6F, 10F, -6F);
        Right.setTextureSize(128, 128);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Base = new ModelRenderer(this, 0, 49);
        Base.addBox(0F, 0F, 0F, 12, 1, 12);
        Base.setRotationPoint(-6F, 23F, -6F);
        Base.setTextureSize(128, 128);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 49, 49);
        Top.addBox(0F, 0F, 0F, 12, 1, 12);
        Top.setRotationPoint(-6F, 10F, -6F);
        Top.setTextureSize(128, 128);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Control_Panel = new ModelRenderer(this, 31, 0);
        Control_Panel.addBox(0F, 0F, 0F, 5, 14, 1);
        Control_Panel.setRotationPoint(2F, 10F, -7F);
        Control_Panel.setTextureSize(128, 128);
        Control_Panel.mirror = true;
        setRotation(Control_Panel, 0F, 0F, 0F);
        Divider_Panel = new ModelRenderer(this, 0, 63);
        Divider_Panel.addBox(0F, 0F, 0F, 1, 12, 12);
        Divider_Panel.setRotationPoint(2F, 11F, -6F);
        Divider_Panel.setTextureSize(128, 128);
        Divider_Panel.mirror = true;
        setRotation(Divider_Panel, 0F, 0F, 0F);
        FrontTop = new ModelRenderer(this, 0, 16);
        FrontTop.addBox(0F, 0F, 0F, 9, 1, 1);
        FrontTop.setRotationPoint(-7F, 10F, -7F);
        FrontTop.setTextureSize(128, 128);
        FrontTop.mirror = true;
        setRotation(FrontTop, 0F, 0F, 0F);
        FrontBottom = new ModelRenderer(this, 0, 19);
        FrontBottom.addBox(0F, 0F, 0F, 9, 1, 1);
        FrontBottom.setRotationPoint(-7F, 23F, -7F);
        FrontBottom.setTextureSize(128, 128);
        FrontBottom.mirror = true;
        setRotation(FrontBottom, 0F, 0F, 0F);
        TopLeft = new ModelRenderer(this, 44, 0);
        TopLeft.addBox(0F, 0F, 0F, 1, 12, 1);
        TopLeft.setRotationPoint(-7F, 11F, -7F);
        TopLeft.setTextureSize(128, 128);
        TopLeft.mirror = true;
        setRotation(TopLeft, 0F, 0F, 0F);
        Door = new ModelRenderer(this, 49, 0);
        Door.addBox(0F, 0F, 0F, 8, 12, 1);
        Door.setRotationPoint(-6F, 11F, -7F);
        Door.setTextureSize(128, 128);
        Door.mirror = true;
        setRotation(Door, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Back.render(f5);
        Left.render(f5);
        Right.render(f5);
        Base.render(f5);
        Top.render(f5);
        Control_Panel.render(f5);
        Divider_Panel.render(f5);
        FrontTop.render(f5);
        FrontBottom.render(f5);
        TopLeft.render(f5);
        Door.render(f5);
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

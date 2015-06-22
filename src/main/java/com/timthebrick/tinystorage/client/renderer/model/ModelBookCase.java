package com.timthebrick.tinystorage.client.renderer.model;

import com.timthebrick.tinystorage.tileentity.implementations.TileEntityBookCase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBookCase extends ModelBase {

    ModelRenderer Back;
    ModelRenderer Right;
    ModelRenderer Left;
    ModelRenderer Top;
    ModelRenderer Bottom;
    ModelRenderer Shelf;
    ModelRenderer Book1;
    ModelRenderer Book1Top;
    ModelRenderer Book2;
    ModelRenderer Book2Top;
    ModelRenderer Book3;
    ModelRenderer Book3Top;
    ModelRenderer Book4;
    ModelRenderer Book4Top;
    ModelRenderer Book5;
    ModelRenderer Book5Top;
    ModelRenderer Book6;
    ModelRenderer Book6Top;
    ModelRenderer Book7;
    ModelRenderer Book7Top;
    ModelRenderer Book8;
    ModelRenderer Book8Top;
    ModelRenderer Book9;
    ModelRenderer Book9Top;
    ModelRenderer Book10;
    ModelRenderer Book10Top;
    ModelRenderer Book11;
    ModelRenderer Book11Top;
    ModelRenderer Book12;
    ModelRenderer Book12Top;
    ModelRenderer Book13;
    ModelRenderer Book13Top;
    ModelRenderer Book14;
    ModelRenderer Book14Top;
    ModelRenderer Book15;
    ModelRenderer Book15Top;
    ModelRenderer Book16;
    ModelRenderer Book16Top;
    ModelRenderer Book17;
    ModelRenderer Book17Top;
    ModelRenderer Book18;
    ModelRenderer Book18Top;

    public ModelBookCase () {
        textureWidth = 128;
        textureHeight = 128;

        Back = new ModelRenderer(this, 0, 0);
        Back.addBox(0F, 0F, 0F, 16, 16, 1);
        Back.setRotationPoint(-8F, 8F, 7F);
        Back.setTextureSize(128, 128);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 17, 18);
        Right.addBox(0F, 0F, 0F, 1, 16, 5);
        Right.setRotationPoint(7F, 8F, 2F);
        Right.setTextureSize(128, 128);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 0, 18);
        Left.addBox(0F, 0F, 0F, 1, 16, 5);
        Left.setRotationPoint(-8F, 8F, 2F);
        Left.setTextureSize(128, 128);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 0, 42);
        Top.addBox(0F, 0F, 0F, 14, 1, 5);
        Top.setRotationPoint(-7F, 8F, 2F);
        Top.setTextureSize(128, 128);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Bottom = new ModelRenderer(this, 0, 61);
        Bottom.addBox(0F, 0F, 2F, 14, 1, 5);
        Bottom.setRotationPoint(-7F, 23F, 0F);
        Bottom.setTextureSize(128, 128);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Shelf = new ModelRenderer(this, 0, 51);
        Shelf.addBox(0F, 0F, 0F, 14, 2, 5);
        Shelf.setRotationPoint(-7F, 15F, 2F);
        Shelf.setTextureSize(128, 128);
        Shelf.mirror = true;
        setRotation(Shelf, 0F, 0F, 0F);
        Book1 = new ModelRenderer(this, 35, 0);
        Book1.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book1.setRotationPoint(-6F, 12.5F, 4.5F);
        Book1.setTextureSize(128, 128);
        Book1.mirror = true;
        setRotation(Book1, 0F, 0F, -0.1919862F);
        Book1Top = new ModelRenderer(this, 46, 0);
        Book1Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book1Top.setRotationPoint(-6F, 12.5F, 4.5F);
        Book1Top.setTextureSize(128, 128);
        Book1Top.mirror = true;
        setRotation(Book1Top, 0F, 0F, -0.1919862F);
        Book2 = new ModelRenderer(this, 35, 0);
        Book2.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book2.setRotationPoint(-4.5F, 12.5F, 4.5F);
        Book2.setTextureSize(128, 128);
        Book2.mirror = true;
        setRotation(Book2, 0F, 0F, 0F);
        Book2Top = new ModelRenderer(this, 46, 0);
        Book2Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book2Top.setRotationPoint(-4.5F, 12.5F, 4.5F);
        Book2Top.setTextureSize(128, 128);
        Book2Top.mirror = true;
        setRotation(Book2Top, 0F, 0F, 0F);
        Book3 = new ModelRenderer(this, 35, 0);
        Book3.addBox(0F, 0F, 0F, 1, 5, 4);
        Book3.setRotationPoint(-3.5F, 10F, 2.5F);
        Book3.setTextureSize(128, 128);
        Book3.mirror = true;
        setRotation(Book3, 0F, 0F, 0F);
        Book3Top = new ModelRenderer(this, 46, 0);
        Book3Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book3Top.setRotationPoint(-3.5F, 10F, 2.5F);
        Book3Top.setTextureSize(128, 128);
        Book3Top.mirror = true;
        setRotation(Book3Top, 0F, 0F, 0F);
        Book4 = new ModelRenderer(this, 35, 0);
        Book4.addBox(0F, 0F, 0F, 1, 5, 4);
        Book4.setRotationPoint(-2F, 10F, 2.5F);
        Book4.setTextureSize(128, 128);
        Book4.mirror = true;
        setRotation(Book4, 0F, 0F, 0F);
        Book4Top = new ModelRenderer(this, 46, 0);
        Book4Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book4Top.setRotationPoint(-2F, 10F, 2.5F);
        Book4Top.setTextureSize(128, 128);
        Book4Top.mirror = true;
        setRotation(Book4Top, 0F, 0F, 0F);
        Book5 = new ModelRenderer(this, 35, 0);
        Book5.addBox(0F, 0F, 0F, 1, 5, 4);
        Book5.setRotationPoint(-0.5F, 10F, 2.5F);
        Book5.setTextureSize(128, 128);
        Book5.mirror = true;
        setRotation(Book5, 0F, 0F, 0F);
        Book5Top = new ModelRenderer(this, 46, 0);
        Book5Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book5Top.setRotationPoint(-0.5F, 10F, 2.5F);
        Book5Top.setTextureSize(128, 128);
        Book5Top.mirror = true;
        setRotation(Book5Top, 0F, 0F, 0F);
        Book6 = new ModelRenderer(this, 35, 0);
        Book6.addBox(0F, 0F, 0F, 1, 5, 4);
        Book6.setRotationPoint(1F, 10F, 2.5F);
        Book6.setTextureSize(128, 128);
        Book6.mirror = true;
        setRotation(Book6, 0F, 0F, 0F);
        Book6Top = new ModelRenderer(this, 46, 0);
        Book6Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book6Top.setRotationPoint(1F, 10F, 2.5F);
        Book6Top.setTextureSize(128, 128);
        Book6Top.mirror = true;
        setRotation(Book6Top, 0F, 0F, 0F);
        Book7 = new ModelRenderer(this, 35, 0);
        Book7.addBox(0F, 0F, 0F, 1, 5, 4);
        Book7.setRotationPoint(2.5F, 10F, 2.5F);
        Book7.setTextureSize(128, 128);
        Book7.mirror = true;
        setRotation(Book7, 0F, 0F, 0F);
        Book7Top = new ModelRenderer(this, 46, 0);
        Book7Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book7Top.setRotationPoint(2.5F, 10F, 2.5F);
        Book7Top.setTextureSize(128, 128);
        Book7Top.mirror = true;
        setRotation(Book7Top, 0F, 0F, 0F);
        Book8 = new ModelRenderer(this, 35, 0);
        Book8.addBox(0F, 0F, 0F, 1, 5, 4);
        Book8.setRotationPoint(4F, 10F, 2.5F);
        Book8.setTextureSize(128, 128);
        Book8.mirror = true;
        setRotation(Book8, 0F, 0F, 0F);
        Book8Top = new ModelRenderer(this, 46, 0);
        Book8Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book8Top.setRotationPoint(4F, 10F, 2.5F);
        Book8Top.setTextureSize(128, 128);
        Book8Top.mirror = true;
        setRotation(Book8Top, 0F, 0F, 0F);
        Book9 = new ModelRenderer(this, 35, 0);
        Book9.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book9.setRotationPoint(6F, 12.5F, 4.5F);
        Book9.setTextureSize(128, 128);
        Book9.mirror = true;
        setRotation(Book9, 0F, 0F, 0.1919862F);
        Book9Top = new ModelRenderer(this, 46, 0);
        Book9Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book9Top.setRotationPoint(6F, 12.5F, 4.5F);
        Book9Top.setTextureSize(128, 128);
        Book9Top.mirror = true;
        setRotation(Book9Top, 0F, 0F, 0.1919862F);
        Book10 = new ModelRenderer(this, 35, 0);
        Book10.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book10.setRotationPoint(-5.8F, 20.5F, 4.5F);
        Book10.setTextureSize(128, 128);
        Book10.mirror = true;
        setRotation(Book10, 0F, 0F, -0.2617994F);
        Book10Top = new ModelRenderer(this, 46, 0);
        Book10Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book10Top.setRotationPoint(-5.8F, 20.5F, 4.5F);
        Book10Top.setTextureSize(128, 128);
        Book10Top.mirror = true;
        setRotation(Book10Top, 0F, 0F, -0.2617994F);
        Book11 = new ModelRenderer(this, 35, 0);
        Book11.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book11.setRotationPoint(-4.5F, 20.5F, 4.5F);
        Book11.setTextureSize(128, 128);
        Book11.mirror = true;
        setRotation(Book11, 0F, 0F, -0.3316126F);
        Book11Top = new ModelRenderer(this, 46, 0);
        Book11Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book11Top.setRotationPoint(-4.5F, 20.5F, 4.5F);
        Book11Top.setTextureSize(128, 128);
        Book11Top.mirror = true;
        setRotation(Book11Top, 0F, 0F, -0.3316126F);
        Book12 = new ModelRenderer(this, 35, 0);
        Book12.addBox(-0.5F, -2.5F, -2F, 1, 5, 4);
        Book12.setRotationPoint(-2.6F, 20.5F, 4.5F);
        Book12.setTextureSize(128, 128);
        Book12.mirror = true;
        setRotation(Book12, 0F, 0F, 0.0523599F);
        Book12Top = new ModelRenderer(this, 46, 0);
        Book12Top.addBox(-0.5F, -2.5F, -2F, 1, 0, 4);
        Book12Top.setRotationPoint(-2.6F, 20.5F, 4.5F);
        Book12Top.setTextureSize(128, 128);
        Book12Top.mirror = true;
        setRotation(Book12Top, 0F, 0F, 0.0523599F);
        Book13 = new ModelRenderer(this, 35, 0);
        Book13.addBox(0F, 0F, 0F, 1, 5, 4);
        Book13.setRotationPoint(-2F, 18F, 2.5F);
        Book13.setTextureSize(128, 128);
        Book13.mirror = true;
        setRotation(Book13, 0F, 0F, 0F);
        Book13Top = new ModelRenderer(this, 46, 0);
        Book13Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book13Top.setRotationPoint(-2F, 18F, 2.5F);
        Book13Top.setTextureSize(128, 128);
        Book13Top.mirror = true;
        setRotation(Book13Top, 0F, 0F, 0F);
        Book14 = new ModelRenderer(this, 35, 0);
        Book14.addBox(0F, 0F, 0F, 1, 5, 4);
        Book14.setRotationPoint(-0.5F, 18F, 2.5F);
        Book14.setTextureSize(128, 128);
        Book14.mirror = true;
        setRotation(Book14, 0F, 0F, 0F);
        Book14Top = new ModelRenderer(this, 46, 0);
        Book14Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book14Top.setRotationPoint(-0.5F, 18F, 2.5F);
        Book14Top.setTextureSize(128, 128);
        Book14Top.mirror = true;
        setRotation(Book14Top, 0F, 0F, 0F);
        Book15 = new ModelRenderer(this, 35, 0);
        Book15.addBox(0F, 0F, 0F, 1, 5, 4);
        Book15.setRotationPoint(1F, 18F, 2.5F);
        Book15.setTextureSize(128, 128);
        Book15.mirror = true;
        setRotation(Book15, 0F, 0F, 0F);
        Book15Top = new ModelRenderer(this, 35, 0);
        Book15Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book15Top.setRotationPoint(1F, 18F, 2.5F);
        Book15Top.setTextureSize(128, 128);
        Book15Top.mirror = true;
        setRotation(Book15Top, 0F, 0F, 0F);
        Book16 = new ModelRenderer(this, 35, 0);
        Book16.addBox(0F, 0F, 0F, 1, 5, 4);
        Book16.setRotationPoint(2.5F, 18F, 2.5F);
        Book16.setTextureSize(128, 128);
        Book16.mirror = true;
        setRotation(Book16, 0F, 0F, 0F);
        Book16Top = new ModelRenderer(this, 46, 0);
        Book16Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book16Top.setRotationPoint(2.5F, 18F, 2.5F);
        Book16Top.setTextureSize(128, 128);
        Book16Top.mirror = true;
        setRotation(Book16Top, 0F, 0F, 0F);
        Book17 = new ModelRenderer(this, 35, 0);
        Book17.addBox(0F, 0F, 0F, 1, 5, 4);
        Book17.setRotationPoint(4F, 18F, 2.5F);
        Book17.setTextureSize(128, 128);
        Book17.mirror = true;
        setRotation(Book17, 0F, 0F, 0F);
        Book17Top = new ModelRenderer(this, 46, 0);
        Book17Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book17Top.setRotationPoint(4F, 18F, 2.5F);
        Book17Top.setTextureSize(128, 128);
        Book17Top.mirror = true;
        setRotation(Book17Top, 0F, 0F, 0F);
        Book18 = new ModelRenderer(this, 35, 0);
        Book18.addBox(0F, 0F, 0F, 1, 5, 4);
        Book18.setRotationPoint(5.5F, 18F, 2.5F);
        Book18.setTextureSize(128, 128);
        Book18.mirror = true;
        setRotation(Book18, 0F, 0F, 0F);
        Book18Top = new ModelRenderer(this, 46, 0);
        Book18Top.addBox(0F, 0F, 0F, 1, 0, 4);
        Book18Top.setRotationPoint(5.5F, 18F, 2.5F);
        Book18Top.setTextureSize(128, 128);
        Book18Top.mirror = true;
        setRotation(Book18Top, 0F, 0F, 0F);
    }

    public void render (Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Back.render(f5);
        Right.render(f5);
        Left.render(f5);
        Top.render(f5);
        Bottom.render(f5);
        Shelf.render(f5);
    }

    public void renderBooks (float f1) {
        Book1.render(f1);
        Book2.render(f1);
        Book3.render(f1);
        Book4.render(f1);
        Book5.render(f1);
        Book6.render(f1);
        Book7.render(f1);
        Book8.render(f1);
        Book9.render(f1);
        Book10.render(f1);
        Book11.render(f1);
        Book12.render(f1);
        Book13.render(f1);
        Book14.render(f1);
        Book15.render(f1);
        Book16.render(f1);
        Book17.render(f1);
        Book18.render(f1);
    }


    public void renderBooks (TileEntityBookCase tileEntity, float f1) {
        if (tileEntity.getStackInSlot(0) != null) {
            GL11.glColor3f(1.0F, 0.0F, 0.0F);
            Book1.render(f1);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Book1Top.render(f1);
        }
        if (tileEntity.getStackInSlot(1) != null) {
            GL11.glColor3f(0.0F, 1.0F, 0.0F);
            Book2.render(f1);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Book2Top.render(f1);
        }
        if (tileEntity.getStackInSlot(2) != null) {
            Book3.render(f1);
            Book3Top.render(f1);
        }
        if (tileEntity.getStackInSlot(3) != null) {
            Book4.render(f1);
            Book4Top.render(f1);
        }
        if (tileEntity.getStackInSlot(4) != null) {
            Book5.render(f1);
            Book5Top.render(f1);
        }
        if (tileEntity.getStackInSlot(5) != null) {
            Book6.render(f1);
            Book6Top.render(f1);
        }
        if (tileEntity.getStackInSlot(6) != null) {
            Book7.render(f1);
            Book7Top.render(f1);
        }
        if (tileEntity.getStackInSlot(7) != null) {
            Book8.render(f1);
            Book8Top.render(f1);
        }
        if (tileEntity.getStackInSlot(8) != null) {
            Book9.render(f1);
            Book9Top.render(f1);
        }
        if (tileEntity.getStackInSlot(9) != null) {
            Book10.render(f1);
            Book10Top.render(f1);
        }
        if (tileEntity.getStackInSlot(10) != null) {
            Book11.render(f1);
            Book11Top.render(f1);
        }
        if (tileEntity.getStackInSlot(11) != null) {
            Book12.render(f1);
            Book12Top.render(f1);
        }
        if (tileEntity.getStackInSlot(12) != null) {
            Book13.render(f1);
            Book13Top.render(f1);
        }
        if (tileEntity.getStackInSlot(13) != null) {
            Book14.render(f1);
            Book14Top.render(f1);
        }
        if (tileEntity.getStackInSlot(14) != null) {
            Book15.render(f1);
            Book15Top.render(f1);
        }
        if (tileEntity.getStackInSlot(15) != null) {
            Book16.render(f1);
            Book16Top.render(f1);
        }
        if (tileEntity.getStackInSlot(16) != null) {
            Book17.render(f1);
            Book17Top.render(f1);
        }
        if (tileEntity.getStackInSlot(17) != null) {
            Book18.render(f1);
            Book18Top.render(f1);
        }
    }


    private void setRotation (ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles (float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

package com.timthebrick.tinystorage.util.colour;

import org.lwjgl.opengl.GL11;

public class Colour {
    private int iColourRed = 255;
    private int iColourGreen = 255;
    private int iColourBlue = 255;
    private int iAlpha = 255;

    public Colour (int pRed, int pGreen, int pBlue) {
        this(pRed, pGreen, pBlue, 255);
    }

    public Colour (float pRed, float pGreen, float pBlue) {
        this(pRed, pGreen, pBlue, 1F);
    }

    public Colour (int pRed, int pGreen, int pBlue, int pAlpha) {
        if (pRed > 255) {
            pRed = 255;
        }

        if (pGreen > 255) {
            pGreen = 255;
        }

        if (pBlue > 255) {
            pBlue = 255;
        }

        if (pAlpha > 255) {
            pAlpha = 255;
        }

        this.iColourRed = pRed;
        this.iColourGreen = pGreen;
        this.iColourBlue = pBlue;
        this.iAlpha = pAlpha;
    }

    public Colour (float pRed, float pGreen, float pBlue, float pAlpha) {
        this((int) (pRed * 255), (int) (pGreen * 255), (int) (pBlue * 255), (int) (pAlpha * 255));
    }

    public Colour (int pColour) {
        iAlpha = pColour >> 24 & 255;
        iColourRed = pColour >> 16 & 255;
        iColourGreen = pColour >> 8 & 255;
        iColourBlue = pColour & 255;
    }

    public int getColourRedInt () {
        return this.iColourRed;
    }

    public float getColourRedFloat () {
        return (this.iColourRed / 255F);
    }

    public int getColourGreenInt () {
        return this.iColourGreen;
    }

    public float getColourGreenFloat () {
        return (this.iColourGreen / 255F);
    }

    public int getColourBlueInt () {
        return this.iColourBlue;
    }

    public float getColourBlueFloat () {
        return (this.iColourBlue / 255F);
    }

    public int getAlphaInt () {
        return this.iAlpha;
    }

    public float getAlphaFloat () {
        return (this.iAlpha / 255F);
    }

    public int getColour () {
        if (iColourRed > 255) {
            iColourRed = 255;
        }

        if (iColourGreen > 255) {
            iColourGreen = 255;
        }

        if (iColourBlue > 255) {
            iColourBlue = 255;
        }

        if (iAlpha > 255) {
            iAlpha = 255;
        }


        int tReturnValue = iAlpha;

        tReturnValue = (tReturnValue << 8) + iColourRed;
        tReturnValue = (tReturnValue << 8) + iColourGreen;
        tReturnValue = (tReturnValue << 8) + iColourBlue;

        return tReturnValue;
    }

    public void setColourRed (int pRed) {
        if (pRed > 255) {
            pRed = 255;
        }
        this.iColourRed = pRed;
    }

    public void setColourRed (float pRed) {
        this.setColourRed((int) (pRed * 255F));
    }

    public void setColourBlue (int pBlue) {
        if (pBlue > 255) {
            pBlue = 255;
        }
        this.iColourBlue = pBlue;
    }

    public void setColourBlue (float pBlue) {
        this.setColourBlue((int) (pBlue * 255F));
    }

    public void setColourGreen (int pGreen) {
        if (pGreen > 255) {
            pGreen = 255;
        }

        this.iColourGreen = pGreen;
    }

    public void setColourGreen (float pGreen) {
        this.setColourGreen((int) (pGreen * 255F));
    }

    public void setAlpha (int pAlpha) {
        if (pAlpha > 255) {
            pAlpha = 255;
        }
        this.iAlpha = pAlpha;
    }

    public void setAlpha (float pAlpha) {
        this.setAlpha((int) (pAlpha * 255F));
    }

    public Colour Combine (Colour pMixed, float pMixingScale) {
        return Combine(this, pMixed, pMixingScale);
    }

    public double getAngleInDegrees () {
        ColourVector tRedVec = new ColourVector(iColourRed * Math.cos(Math.toRadians(0)), iColourRed * Math.sin(Math.toRadians(0)));
        ColourVector tGreenVec = new ColourVector(iColourGreen * Math.cos(Math.toRadians(120)), iColourGreen * Math.sin(Math.toRadians(120)));
        ColourVector tBlueVec = new ColourVector(iColourBlue * Math.cos(Math.toRadians(240)), iColourBlue * Math.sin(Math.toRadians(240)));

        ColourVector tColourVec = new ColourVector(tRedVec.iColourX + tBlueVec.iColourX + tGreenVec.iColourX, tRedVec.iColourY + tBlueVec.iColourY + tGreenVec.iColourY);

        if (tColourVec.iColourY == 0) {
            if (tColourVec.iColourX < -10) {
                return 90;
            } else if (tColourVec.iColourX > 10) {
                return 270;
            } else {
                return 0;
            }
        }

        if (tColourVec.iColourX == 0) {
            if (tColourVec.iColourY < -10) {
                return 180;
            } else if (tColourVec.iColourY > 10) {
                return 0;
            } else {
                return 0;
            }
        }

        return 360 - (Math.atan((((float) tColourVec.iColourX) / ((float) tColourVec.iColourY))) * (180 / Math.PI));
    }

    public void performGLColour4f () {
        GL11.glColor4f(getColourRedFloat(), getColourGreenFloat(), getColourBlueFloat(), getAlphaFloat());
    }

    public void performGLColour3f () {
        GL11.glColor3f(getColourRedFloat(), getColourGreenFloat(), getColourBlueFloat());
    }

    public static void resetGLColour () {
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    public static Colour Combine (Colour pOriginal, Colour pMixed, float pMixingScale) {
        int tAlpha = (int) ((1 - pMixingScale) * pOriginal.iAlpha + pMixingScale * pMixed.iAlpha);
        int tRedColour = (int) ((1 - pMixingScale) * pOriginal.iColourRed + pMixingScale * pMixed.iColourRed);
        int tGreenColour = (int) ((1 - pMixingScale) * pOriginal.iColourGreen + pMixingScale * pMixed.iColourGreen);
        int tBlueColour = (int) ((1 - pMixingScale) * pOriginal.iColourBlue + pMixingScale * pMixed.iColourBlue);

        return new Colour(tRedColour, tGreenColour, tBlueColour, tAlpha);
    }

    @Override
    public String toString () {
        return "R: " + iColourRed + ", G: " + iColourGreen + ", B: " + iColourBlue;
    }
}

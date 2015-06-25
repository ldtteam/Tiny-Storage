package com.timthebrick.tinystorage.util.colour;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ColourSampler {

    private static HashMap<Colour, EnumChatFormatting> iMappedColors;

    private static void initializeEnumChatFormattingColours () {
        iMappedColors = new HashMap<Colour, EnumChatFormatting>();
        for (int tIndex = 0; tIndex < 16; tIndex++) {
            if (EnumChatFormatting.values()[tIndex].name().equals("BLACK")) {
                continue;
            }
            Colour tMappedColor = new Colour(Minecraft.getMinecraft().fontRenderer.colorCode[tIndex]);
            TinyStorageLog.info("Generated Color Code : " + tMappedColor.getColourRedInt() + "-" + tMappedColor.getColourGreenInt() + "-" + tMappedColor.getColourBlueInt() + " for the following EnumChatFormatting: " + EnumChatFormatting.values()[tIndex].name() + ".");
            iMappedColors.put(tMappedColor, EnumChatFormatting.values()[tIndex]);
        }
    }

    public static Colour getColourSampleFromItemStack (ItemStack pStack) {
        if (pStack.getItem().getColorFromItemStack(pStack, 0) != 16777215) {
            return new Colour(pStack.getItem().getColorFromItemStack(pStack, 0));
        } else {
            pStack.getItem().registerIcons(new PlaceHolderRegistrar());
            if (pStack.getItem() instanceof ItemBlock) {
                ((ItemBlock) pStack.getItem()).field_150939_a.registerBlockIcons(new BlockPlaceHolderRegistrar());
            }
            IconPlaceHolder pIcon = (IconPlaceHolder) pStack.getItem().getIcon(pStack, 0);
            try {
                Colour tSample = calculateAverageColour(ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(pIcon.iIconLocation).getInputStream()));

                return tSample;
            } catch (IOException e) {
                return new Colour(16777215);
            }
        }
    }

    public static Colour calculateAverageColour (BufferedImage pBuffer) {
        long tSumR = 0, tSumG = 0, tSumB = 0;
        int tCountedPixels = 0;
        for (int tXPos = 0; tXPos < pBuffer.getWidth(); tXPos++) {
            for (int tYPos = 0; tYPos < pBuffer.getHeight(); tYPos++) {
                int tRGB = pBuffer.getRGB(tXPos, tYPos);
                Colour tPixel = new Colour(tRGB);
                if (tPixel.getAlphaInt() > 0) {
                    tSumR += tPixel.getColourRedInt();
                    tSumG += tPixel.getColourGreenInt();
                    tSumB += tPixel.getColourBlueInt();
                    tCountedPixels++;
                }
            }
        }
        if (tCountedPixels == 0) {
            TinyStorageLog.info("No pixels counted!");
            return new Colour(255, 255, 255);
        }
        return new Colour((int) (tSumR / tCountedPixels), (int) (tSumG / tCountedPixels), (int) (tSumB / tCountedPixels));
    }

    public static EnumChatFormatting getChatColourSample (Colour pSource) {
        if (iMappedColors == null) {
            initializeEnumChatFormattingColours();
        }
        double tCurrentDistance = -1D;
        EnumChatFormatting tCurrentFormatting = null;
        for (Colour tColor : iMappedColors.keySet()) {
            if (colourDistance(pSource, tColor) < tCurrentDistance) {
                tCurrentDistance = colourDistance(pSource, tColor);
                tCurrentFormatting = iMappedColors.get(tColor);
            } else if (tCurrentDistance < 0) {
                tCurrentDistance = colourDistance(pSource, tColor);
                tCurrentFormatting = iMappedColors.get(tColor);
            }
        }
        return tCurrentFormatting;
    }

    private static double colourDistance (Colour pColour1, Colour pColour2) {
        if ((pColour1.getColourRedInt() > pColour1.getColourBlueInt() * 2) && (pColour1.getColourRedInt() > pColour1.getColourGreenInt() * 2)) {
            if ((pColour1.getColourRedInt() > pColour2.getColourRedInt())) {
                return pColour1.getColourRedInt() - pColour2.getColourRedInt();
            }
            return pColour2.getColourRedInt() - pColour1.getColourRedInt();
        }
        if ((pColour1.getColourBlueInt() > pColour1.getColourRedInt() * 2) && (pColour1.getColourBlueInt() > pColour1.getColourGreenInt() * 2)) {
            if ((pColour1.getColourBlueInt() > pColour2.getColourBlueInt())) {
                return pColour1.getColourBlueInt() - pColour2.getColourBlueInt();
            }
            return pColour2.getColourBlueInt() - pColour1.getColourBlueInt();
        }
        if ((pColour1.getColourGreenInt() > pColour1.getColourBlueInt() * 2) && (pColour1.getColourGreenInt() > pColour1.getColourRedInt() * 2)) {
            if ((pColour1.getColourGreenInt() > pColour2.getColourGreenInt())) {
                return pColour1.getColourGreenInt() - pColour2.getColourGreenInt();
            }
            return pColour2.getColourGreenInt() - pColour1.getColourGreenInt();
        }
        return Math.abs(pColour1.getAngleInDegrees() - pColour2.getAngleInDegrees());
    }
}

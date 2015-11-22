package com.smithsmodding.tinystorage.util.client.colour;

public class ColourVector {

    public int iColourX;
    public int iColourY;

    public ColourVector (int pColourX, int pColourY)
    {
        iColourX = pColourX;
        iColourY = pColourY;
    }

    public ColourVector (double pColourX, double pColourY) {
        this((int) pColourX, (int) pColourY);
    }
}

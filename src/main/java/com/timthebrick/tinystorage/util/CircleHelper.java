package com.timthebrick.tinystorage.util;

import net.minecraft.world.World;

import com.timthebrick.tinystorage.core.TinyStorageLog;

/**
 * Created by Orion
 * Created on 31.05.2015
 * 13:32
 * <p/>
 * Copyrighted according to Project specific license
 */
public class CircleHelper {

	public static void FillCircle(int pOriginX, int pOriginY, int pOriginZ, World pWorldObj, int pRadius) {
		pWorldObj.setBlockToAir(pOriginX, pOriginY, pOriginZ);

		for (int tStep = 1; tStep <= pRadius; tStep++) {
			SetCircleIncNeigborCheck(pOriginX, pOriginY, pOriginZ, pWorldObj, tStep);
		}
	}

	public static void SetCircle(int pOriginX, int pOriginY, int pOriginZ, World pWorldObj, int pRadius) {
		int tCurrentX = pRadius;
		int tCurrentZ = 0;
		int decisionOver2 = 1 - tCurrentX;

		while (tCurrentX >= tCurrentZ) {
			pWorldObj.setBlockToAir(tCurrentX + pOriginX, pOriginY, tCurrentZ + pOriginZ);
			pWorldObj.setBlockToAir(tCurrentZ + pOriginX, pOriginY, tCurrentX + pOriginZ);
			pWorldObj.setBlockToAir(-tCurrentX + pOriginX, pOriginY, tCurrentZ + pOriginZ);
			pWorldObj.setBlockToAir(-tCurrentZ + pOriginX, pOriginY, tCurrentX + pOriginZ);
			pWorldObj.setBlockToAir(-tCurrentX + pOriginX, pOriginY, -tCurrentZ + pOriginZ);
			pWorldObj.setBlockToAir(-tCurrentZ + pOriginX, pOriginY, -tCurrentX + pOriginZ);
			pWorldObj.setBlockToAir(tCurrentX + pOriginX, pOriginY, -tCurrentZ + pOriginZ);
			pWorldObj.setBlockToAir(tCurrentZ + pOriginX, pOriginY, -tCurrentX + pOriginZ);
			tCurrentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * tCurrentZ + 1;

			} else {
				tCurrentX--;
				decisionOver2 += 2 * (tCurrentZ - tCurrentX) + 1;
			}
		}
	}

	public static void SetCircleIncNeigborCheck(int pOriginX, int pOriginY, int pOriginZ, World pWorldObj, int pRadius) {
		int tCurrentX = pRadius;
		int tCurrentZ = 0;
		int decisionOver2 = 1 - tCurrentX; // Decision criterion divided by 2
											// evaluated at x=r, y=0

		while (tCurrentX >= tCurrentZ) {
			SetBlockIncNeighborCheck(tCurrentX + pOriginX, pOriginY, tCurrentZ + pOriginZ, tCurrentX, 0, tCurrentZ, pWorldObj);
			SetBlockIncNeighborCheck(tCurrentZ + pOriginX, pOriginY, tCurrentX + pOriginZ, tCurrentZ, 0, tCurrentX, pWorldObj);
			SetBlockIncNeighborCheck(-tCurrentX + pOriginX, pOriginY, tCurrentZ + pOriginZ, -tCurrentX, 0, tCurrentZ, pWorldObj);
			SetBlockIncNeighborCheck(-tCurrentZ + pOriginX, pOriginY, tCurrentX + pOriginZ, -tCurrentZ, 0, tCurrentX, pWorldObj);
			SetBlockIncNeighborCheck(-tCurrentX + pOriginX, pOriginY, -tCurrentZ + pOriginZ, -tCurrentX, 0, -tCurrentZ, pWorldObj);
			SetBlockIncNeighborCheck(-tCurrentZ + pOriginX, pOriginY, -tCurrentX + pOriginZ, -tCurrentZ, 0, -tCurrentX, pWorldObj);
			SetBlockIncNeighborCheck(tCurrentX + pOriginX, pOriginY, -tCurrentZ + pOriginZ, tCurrentX, 0, -tCurrentZ, pWorldObj);
			SetBlockIncNeighborCheck(tCurrentZ + pOriginX, pOriginY, -tCurrentX + pOriginZ, tCurrentZ, 0, -tCurrentX, pWorldObj);
			tCurrentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * tCurrentZ + 1; // Change in decision
													// criterion for y -> y+1
			} else {
				tCurrentX--;
				decisionOver2 += 2 * (tCurrentZ - tCurrentX) + 1; // Change for
																	// y -> y+1,
																	// x -> x-1
			}
		}
	}

	private static void SetBlockIncNeighborCheck(int pOriginX, int pOriginY, int pOriginZ, int pRelativeX, int pRelativeY, int pRelativeZ, World pWorldObj) {
		pWorldObj.setBlockToAir(pOriginX, pOriginY, pOriginZ);

		int tRelativeX = 0;
		int tRelativeZ = 0;

		if (pRelativeX > 0) {
			tRelativeX = -1;
		}

		if (pRelativeX < 0) {
			tRelativeX = 1;
		}

		if (pRelativeZ > 0) {
			tRelativeZ = -1;
		}

		if (pRelativeZ < 0) {
			tRelativeZ = 1;
		}

		TinyStorageLog.info(pRelativeX + "-" + pRelativeY + "-" + pRelativeZ + "/" + tRelativeX + "-" + tRelativeZ);
		pWorldObj.setBlockToAir(pOriginX + tRelativeX, pOriginY, pOriginZ + tRelativeZ);
		pWorldObj.setBlockToAir(pOriginX + tRelativeX, pOriginY, pOriginZ);
		pWorldObj.setBlockToAir(pOriginX, pOriginY, pOriginZ + tRelativeZ);
	}

}

package com.timthebrick.tinystorage.util;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.core.TinyStorageLog;

/**
 * Created by Orion Created on 31.05.2015 13:32
 * <p/>
 * Copyrighted according to Project specific license
 */
public class CircleHelper {

	public static void fillCircle(int originX, int originY, int originZ, World world, int radius) {
		world.setBlockToAir(originX, originY, originZ);
		for (int tStep = 1; tStep <= radius; tStep++) {
			setCircleIncNeigborCheck(originX, originY, originZ, world, tStep);
		}
	}
	
	public static ArrayList genCircle(int originX, int originY, int originZ, World world, int radius){
		ArrayList blocks = new ArrayList<Block>();
		blocks.add(world.getBlock(originX, originY, originZ));
		for (int tStep = 1; tStep <= radius; tStep++) {
			getCircleIncNeigborCheck(originX, originY, originZ, world, tStep, blocks);
		}
		return blocks;
	}

	public static void setCircle(int originX, int originY, int originZ, World world, int pRadius) {
		int currentX = pRadius;
		int currentZ = 0;
		int decisionOver2 = 1 - currentX;

		while (currentX >= currentZ) {
			world.setBlockToAir(currentX + originX, originY, currentZ + originZ);
			world.setBlockToAir(currentZ + originX, originY, currentX + originZ);
			world.setBlockToAir(-currentX + originX, originY, currentZ + originZ);
			world.setBlockToAir(-currentZ + originX, originY, currentX + originZ);
			world.setBlockToAir(-currentX + originX, originY, -currentZ + originZ);
			world.setBlockToAir(-currentZ + originX, originY, -currentX + originZ);
			world.setBlockToAir(currentX + originX, originY, -currentZ + originZ);
			world.setBlockToAir(currentZ + originX, originY, -currentX + originZ);
			currentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * currentZ + 1;

			} else {
				currentX--;
				decisionOver2 += 2 * (currentZ - currentX) + 1;
			}
		}
	}
	
	private static void getCircleIncNeigborCheck(int originX, int originY, int originZ, World world, int pRadius, ArrayList<Block> blocks) {
		int currentX = pRadius;
		int currentZ = 0;
		int decisionOver2 = 1 - currentX;

		while (currentX >= currentZ) {
			getBlockIncNeighborCheck(currentX + originX, originY, currentZ + originZ, currentX, 0, currentZ, world, blocks);
			getBlockIncNeighborCheck(currentZ + originX, originY, currentX + originZ, currentZ, 0, currentX, world, blocks);
			getBlockIncNeighborCheck(-currentX + originX, originY, currentZ + originZ, -currentX, 0, currentZ, world, blocks);
			getBlockIncNeighborCheck(-currentZ + originX, originY, currentX + originZ, -currentZ, 0, currentX, world, blocks);
			getBlockIncNeighborCheck(-currentX + originX, originY, -currentZ + originZ, -currentX, 0, -currentZ, world, blocks);
			getBlockIncNeighborCheck(-currentZ + originX, originY, -currentX + originZ, -currentZ, 0, -currentX, world, blocks);
			getBlockIncNeighborCheck(currentX + originX, originY, -currentZ + originZ, currentX, 0, -currentZ, world, blocks);
			getBlockIncNeighborCheck(currentZ + originX, originY, -currentX + originZ, currentZ, 0, -currentX, world, blocks);
			currentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * currentZ + 1;
			} else {
				currentX--;
				decisionOver2 += 2 * (currentZ - currentX) + 1;
			}
		}
	}
	
	private static void getBlockIncNeighborCheck(int originX, int originY, int originZ, int relativeX, int relativeY, int relativeZ, World world, ArrayList<Block> blocks) {
		world.setBlockToAir(originX, originY, originZ);

		int tRelativeX = 0;
		int tRelativeZ = 0;

		if (relativeX > 0) {
			tRelativeX = -1;
		}

		if (relativeX < 0) {
			tRelativeX = 1;
		}

		if (relativeZ > 0) {
			tRelativeZ = -1;
		}

		if (relativeZ < 0) {
			tRelativeZ = 1;
		}

		TinyStorageLog.info(relativeX + "-" + relativeY + "-" + relativeZ + "/" + tRelativeX + "-" + tRelativeZ);
		blocks.add(world.getBlock(originX + tRelativeX, originY, originZ + tRelativeZ));
		blocks.add(world.getBlock(originX + tRelativeX, originY, originZ));
		blocks.add(world.getBlock(originX, originY, originZ + tRelativeZ));
	}

	public static void setCircleIncNeigborCheck(int originX, int originY, int originZ, World world, int pRadius) {
		int currentX = pRadius;
		int currentZ = 0;
		int decisionOver2 = 1 - currentX;

		while (currentX >= currentZ) {
			setBlockIncNeighborCheck(currentX + originX, originY, currentZ + originZ, currentX, 0, currentZ, world);
			setBlockIncNeighborCheck(currentZ + originX, originY, currentX + originZ, currentZ, 0, currentX, world);
			setBlockIncNeighborCheck(-currentX + originX, originY, currentZ + originZ, -currentX, 0, currentZ, world);
			setBlockIncNeighborCheck(-currentZ + originX, originY, currentX + originZ, -currentZ, 0, currentX, world);
			setBlockIncNeighborCheck(-currentX + originX, originY, -currentZ + originZ, -currentX, 0, -currentZ, world);
			setBlockIncNeighborCheck(-currentZ + originX, originY, -currentX + originZ, -currentZ, 0, -currentX, world);
			setBlockIncNeighborCheck(currentX + originX, originY, -currentZ + originZ, currentX, 0, -currentZ, world);
			setBlockIncNeighborCheck(currentZ + originX, originY, -currentX + originZ, currentZ, 0, -currentX, world);
			currentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * currentZ + 1;
			} else {
				currentX--;
				decisionOver2 += 2 * (currentZ - currentX) + 1;
			}
		}
	}

	private static void setBlockIncNeighborCheck(int originX, int originY, int originZ, int relativeX, int relativeY, int relativeZ, World world) {
		world.setBlockToAir(originX, originY, originZ);

		int tRelativeX = 0;
		int tRelativeZ = 0;

		if (relativeX > 0) {
			tRelativeX = -1;
		}

		if (relativeX < 0) {
			tRelativeX = 1;
		}

		if (relativeZ > 0) {
			tRelativeZ = -1;
		}

		if (relativeZ < 0) {
			tRelativeZ = 1;
		}

		TinyStorageLog.info(relativeX + "-" + relativeY + "-" + relativeZ + "/" + tRelativeX + "-" + tRelativeZ);
		world.setBlockToAir(originX + tRelativeX, originY, originZ + tRelativeZ);
		world.setBlockToAir(originX + tRelativeX, originY, originZ);
		world.setBlockToAir(originX, originY, originZ + tRelativeZ);
	}

}

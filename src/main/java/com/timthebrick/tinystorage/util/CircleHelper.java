package com.timthebrick.tinystorage.util;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.addAll(world.getBlock(originX, originY, originZ).getDrops(world, originX, originY, originZ, world.getBlockMetadata(originX, originY, originZ), 0));
		for (int tStep = 1; tStep <= radius; tStep++) {
			drops.addAll(getCircleIncNeigborCheck(originX, originY, originZ, world, tStep));
		}
		return drops;
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
	
	private static ArrayList<ItemStack> getCircleIncNeigborCheck(int originX, int originY, int originZ, World world, int pRadius) {
		int currentX = pRadius;
		int currentZ = 0;
		int decisionOver2 = 1 - currentX;

		HashMap<int[], ArrayList<ItemStack>> drops = new HashMap<int[], ArrayList<ItemStack>>();

		while (currentX >= currentZ) {
			getBlockIncNeighborCheck(currentX + originX, originY, currentZ + originZ, currentX, 0, currentZ, world, drops);
			getBlockIncNeighborCheck(currentZ + originX, originY, currentX + originZ, currentZ, 0, currentX, world, drops);
			getBlockIncNeighborCheck(-currentX + originX, originY, currentZ + originZ, -currentX, 0, currentZ, world, drops);
			getBlockIncNeighborCheck(-currentZ + originX, originY, currentX + originZ, -currentZ, 0, currentX, world, drops);
			getBlockIncNeighborCheck(-currentX + originX, originY, -currentZ + originZ, -currentX, 0, -currentZ, world, drops);
			getBlockIncNeighborCheck(-currentZ + originX, originY, -currentX + originZ, -currentZ, 0, -currentX, world, drops);
			getBlockIncNeighborCheck(currentX + originX, originY, -currentZ + originZ, currentX, 0, -currentZ, world, drops);
			getBlockIncNeighborCheck(currentZ + originX, originY, -currentX + originZ, currentZ, 0, -currentX, world, drops);
			currentZ++;

			if (decisionOver2 <= 0) {
				decisionOver2 += 2 * currentZ + 1;
			} else {
				currentX--;
				decisionOver2 += 2 * (currentZ - currentX) + 1;
			}
		}

		ArrayList<ItemStack> droppedStacks = new ArrayList<ItemStack>();
		for (ArrayList<ItemStack> tStacks : drops.values())
		{
			droppedStacks.addAll(tStacks);
		}

		return droppedStacks;
	}
	
	private static void getBlockIncNeighborCheck(int originX, int originY, int originZ, int relativeX, int relativeY, int relativeZ, World world,HashMap<int[], ArrayList<ItemStack>> drops) {
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

		if (!drops.containsKey(new int[] {originX + tRelativeX, originY, originZ + tRelativeZ}))
		{
			drops.put(new int[] {originX + tRelativeX, originY, originZ + tRelativeZ}, world.getBlock(originX + tRelativeX, originY, originZ + tRelativeZ).getDrops(world, originX + tRelativeX, originY, originZ + tRelativeZ, world.getBlockMetadata(originX + tRelativeX, originY, originZ + tRelativeZ), 0));
		}

		if (!drops.containsKey(new int[] {originX + tRelativeX, originY, originZ}))
		{
			drops.put(new int[] {originX + tRelativeX, originY, originZ}, world.getBlock(originX + tRelativeX, originY, originZ).getDrops(world, originX + tRelativeX, originY, originZ, world.getBlockMetadata(originX + tRelativeX, originY, originZ), 0));
		}

		if (!drops.containsKey(new int[] {originX, originY, originZ + tRelativeZ}))
		{
			drops.put(new int[] {originX, originY, originZ + tRelativeZ}, world.getBlock(originX, originY, originZ + tRelativeZ).getDrops(world, originX, originY, originZ + tRelativeZ, world.getBlockMetadata(originX, originY, originZ + tRelativeZ), 0));
		}
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

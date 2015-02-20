package com.timthebrick.tinystorage.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.init.ModItems;

public class UnlocalizedNameDump {

	public static void dumpBlockNames(File file) {
		TinyStorageLog.info("Attempting to dump unlocalized block names");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			for (Block b : ModBlocks.tinyStorageBlocks) {
				writer.println(b.getUnlocalizedName() + ".name=");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			TinyStorageLog.error(e);
		} catch (UnsupportedEncodingException e) {
			TinyStorageLog.error(e);
		}
	}
	
	public static void dumpItemNames(File file) {
		TinyStorageLog.info("Attempting to dump unlocalized item names");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			for (Item i : ModItems.tinyStorageItems) {
				writer.println(i.getUnlocalizedName() + ".name=");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			TinyStorageLog.error(e);
		} catch (UnsupportedEncodingException e) {
			TinyStorageLog.error(e);
		}
	}

}

package com.timthebrick.tinystorage.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.core.VersionChecker;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TickHandler {

	@SubscribeEvent
	public void checkUpToDate(PlayerTickEvent evt) {
		
	}

}

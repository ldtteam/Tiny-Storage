package com.timthebrick.tinystorage.client.settings;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.timthebrick.tinystorage.common.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {
	public static KeyBinding changeMode = new KeyBinding(Names.Keys.MODE, Keyboard.KEY_M, Names.Keys.CATEGORY);
}

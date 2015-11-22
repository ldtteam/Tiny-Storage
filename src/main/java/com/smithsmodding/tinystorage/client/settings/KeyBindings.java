package com.smithsmodding.tinystorage.client.settings;

import com.smithsmodding.tinystorage.common.reference.Names;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {
	public static KeyBinding changeMode = new KeyBinding(Names.Keys.MODE, Keyboard.KEY_M, Names.Keys.CATEGORY);
}

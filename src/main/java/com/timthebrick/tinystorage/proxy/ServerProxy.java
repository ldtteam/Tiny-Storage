package com.timthebrick.tinystorage.proxy;

public class ServerProxy extends CommonProxy{

	@Override
	public ClientProxy getClientProxy() {
		return null;
	}

	@Override
	public void initRenderingAndTextures() {
	}

	@Override
	public void registerKeybindings() {		
	}

	@Override
	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
	}

}

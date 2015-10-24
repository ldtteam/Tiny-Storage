package com.smithsmodding.tinystorage.common.proxy;

public class ServerProxy extends CommonProxy{

	@Override
	public ClientProxy getClientProxy() {
		return null;
	}

	@Override
	public void initRenderingAndTextures() {
	}

	@Override
	public void registerKeyBindings() {		
	}

	@Override
	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
	}

}

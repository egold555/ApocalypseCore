package org.golde.apocalypsecore.common.utils;

import org.golde.apocalypsecore.client.ACParticleTypesClient;
import org.golde.apocalypsecore.common.ApocalypseCore;

public enum ACParticleTypesServer {

	ERROR,
	SMOKE(3),
	FIREBALL,
	FIREBALL_HUGE;
	
	private final int argCount;
	ACParticleTypesServer() {
		this(0);
	}

	ACParticleTypesServer(int argCount) {
		this.argCount = argCount;
	}

	public final int getArgumentCount() {
		return argCount;
	}

	public final int getParticleID() {
		return this.ordinal(); //seems to work fine
	}
	
	public static ACParticleTypesServer getParticleFromId(int d) {
		if(d > ACParticleTypesServer.values().length - 1) {
			ApocalypseCore.logger.error("Recievdd particle id '" + d + "' which is bigger then the max particle id of '" + (ACParticleTypesClient.values().length - 1) + "'! Returning ERROR particle to prevent crash!");
			return ACParticleTypesServer.ERROR;
		}
		return ACParticleTypesServer.values()[d];
	}
	
	
}

package org.golde.apocalypsecore.client;

public class ClientStats {

	public int level;
	public float saturation;
	public boolean isPoisoned;
	public int movementSpeed;
	public float temperature;

	private static ClientStats instance = new ClientStats();

	public static ClientStats getInstance() {
		return instance;
	}

}

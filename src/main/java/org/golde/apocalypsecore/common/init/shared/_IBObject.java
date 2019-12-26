package org.golde.apocalypsecore.init.shared;

public interface _IBObject {

	public default boolean shouldBeInCreatveTab() {
		return true;
	}
	
	public void initModel();
	
}

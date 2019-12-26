package org.golde.apocalypsecore.common.init.shared;

public interface _IBObject {

	public default boolean shouldBeInCreatveTab() {
		return true;
	}
	
	public void initModel();
	
}

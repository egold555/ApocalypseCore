package org.golde.apocalypsecore.blocks._core;

import org.golde.apocalypsecore.init.shared._IBObject;

public interface _IACBlock extends _IBObject {

	public default boolean shouldRegisterItem() {
		return true;
	}
	
}

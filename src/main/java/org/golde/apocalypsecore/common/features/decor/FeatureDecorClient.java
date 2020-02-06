package org.golde.apocalypsecore.common.features.decor;

import org.golde.apocalypsecore.client.render.item.LayerStaticColor;
import org.golde.apocalypsecore.common.features.FeatureClient;

public class FeatureDecorClient extends FeatureClient {

	@Override
	public void registerItemColorHandlers() {
		registerItemColorHandler(new LayerStaticColor(), FeatureDecor.sprayPaint);
	}
	
}

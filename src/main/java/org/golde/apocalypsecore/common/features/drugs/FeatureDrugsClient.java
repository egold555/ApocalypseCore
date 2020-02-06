package org.golde.apocalypsecore.common.features.drugs;

import org.golde.apocalypsecore.client.render.item.LayerStaticColor;
import org.golde.apocalypsecore.common.features.FeatureClient;

public class FeatureDrugsClient extends FeatureClient {

	@Override
	public void registerItemColorHandlers() {
		registerItemColorHandler(new LayerStaticColor(), FeatureDrugs.syringeFull);
	}
	
}

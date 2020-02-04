package org.golde.apocalypsecore.common.features.drugs;

import org.golde.apocalypsecore.common.features.FeatureClient;
import org.golde.apocalypsecore.common.features.drugs.item.syringe.SyringeLiquidColor;

public class FeatureDrugsClient extends FeatureClient {

	@Override
	public void registerItemColorHandlers() {
		registerItemColorHandler(new SyringeLiquidColor(), FeatureDrugs.syringeFull);
	}
	
}

package org.golde.apocalypsecore.common.features;

import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.common.blocks._IACBlock;
import org.golde.apocalypsecore.common.features.building.FeatureBuilding;
import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.features.drugs.FeatureDrugsClient;
import org.golde.apocalypsecore.common.features.food.FeatureFood;
import org.golde.apocalypsecore.common.features.misc.FeatureMisc;
import org.golde.apocalypsecore.common.features.misc.FeatureMiscClient;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeaponsClient;
import org.golde.apocalypsecore.common.items._IACItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureRegistrationClient {

	private static List<FeatureClient> features = new ArrayList<FeatureClient>();
	
	static {
		features.add(new FeatureMiscClient());
		features.add(new FeatureWeaponsClient());
		features.add(new FeatureDrugsClient());
	}
	
	@SideOnly(Side.CLIENT)
	public static final void initModels() {

		for(_IACBlock block : Feature.getALL_BLOCKS()) {
			if(block.shouldRegisterItem()) {
				block.initModel();
			}
		}
		
		for(_IACItem item : Feature.getALL_ITEMS()) {
			item.initModel();
		}

	}
	
	public static final void registerItemColorHandler(ItemColors colors) {
		for(FeatureClient f : features) {
			f.registerItemColorHandlers();
		}
		
		for(IItemColor c : FeatureClient.getColorMapItem().keySet()) {
			colors.registerItemColorHandler(c, FeatureClient.getColorMapItem().get(c));
		}
		
		for(IItemColor c : FeatureClient.getColorMapBlock().keySet()) {
			colors.registerItemColorHandler(c, FeatureClient.getColorMapBlock().get(c));
		}
	}
	
	public static void bindTESR() {
		for(FeatureClient f : features) {
			f.bindTESR();
		}
	}
	
	public static void regsterEntityRenderers() {
		for(FeatureClient f : features) {
			f.regsterEntityRenderers();
		}
	}
	
	
}

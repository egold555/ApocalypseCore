package org.golde.apocalypsecore.client.render.block;

import org.golde.apocalypsecore.blocks._core._ACTE;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class _ACTESR<TE extends _ACTE> extends TileEntitySpecialRenderer<TE> {

	private boolean isRenderingItem;

	@Override
	/**
	 *  ALWAYS CALL ME FIRST
	 */
	public void render(TE te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		try {
			int dummy = te.getBlockMetadata();
			isRenderingItem = false;
		}
		catch(Exception e) {
			isRenderingItem = true;
		}
		if(!isRenderingItem) {
			super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		}
	}

	public final boolean isRenderingItem() {
		return isRenderingItem;
	}

}

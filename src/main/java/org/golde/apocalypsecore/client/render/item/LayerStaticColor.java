package org.golde.apocalypsecore.client.render.item;

import java.awt.Color;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class LayerStaticColor implements IItemColor {

	/**
	 * Returns the colour for rendering, based on
	 * 1) the itemstack
	 * 2) the "tintindex" (layer in the item model json)
	 * For example:
	 * bottle_drinkable.json contains
	 *   "layer0": "items/potion_overlay",
	 *   "layer1": "items/potion_bottle_drinkable"
	 * layer0 = tintindex 0 = for the bottle outline, whose colour doesn't change
	 * layer1 = tintindex 1 = for the bottle contents, whose colour changes depending on the type of potion
	 * @param stack
	 * @param tintIndex
	 * @return an RGB colour (to be multiplied by the texture colours)
	 */
	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		// when rendering, choose the colour multiplier based on the contents
		// we want layer 0 (the bottle glass) to be unaffected (return white as the multiplier)
		// layer 1 will change colour depending on the contents.


		switch (tintIndex) {
		case 0: return Color.WHITE.getRGB();
		case 1: {

			if(stack.getTagCompound().hasKey("color")) {
				int c = stack.getTagCompound().getInteger("color");
				return c;
			}

			else {
				return Color.BLACK.getRGB();
			}
		}
		default: {
			// oops! should never get here.
			return Color.BLACK.getRGB();
		}
		}

	}
}
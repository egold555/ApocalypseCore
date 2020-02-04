package org.golde.apocalypsecore.common.utils;

import java.lang.reflect.Field;

import net.minecraft.item.EnumDyeColor;

public class ShittyServerFix {

	//i hate this server client bulllshit
	public static int getColor(EnumDyeColor c) {
		try {
			return c.getColorValue();
		}
		catch(Throwable ex1) {
			try {
				Field f = EnumDyeColor.class.getField("w");
				f.setAccessible(true);
				return (int) f.get(c);
			}
			catch(Throwable ex2) {
				
			}
		}
		return 0;
		
	}
	
}

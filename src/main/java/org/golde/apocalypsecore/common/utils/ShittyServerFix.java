package org.golde.apocalypsecore.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.item.EnumDyeColor;

public class ShittyServerFix {

	private static HashMap<EnumDyeColor, Integer> whyMustINeedThis = new HashMap<EnumDyeColor, Integer>();
	
	static {
		whyMustINeedThis.put(EnumDyeColor.WHITE, 16383998);
		whyMustINeedThis.put(EnumDyeColor.ORANGE, 16351261);
		whyMustINeedThis.put(EnumDyeColor.MAGENTA, 13061821);
		whyMustINeedThis.put(EnumDyeColor.LIGHT_BLUE, 3847130);
		whyMustINeedThis.put(EnumDyeColor.YELLOW, 16701501);
		whyMustINeedThis.put(EnumDyeColor.LIME, 8439583);
		whyMustINeedThis.put(EnumDyeColor.PINK, 15961002);
		whyMustINeedThis.put(EnumDyeColor.GRAY, 4673362);
		whyMustINeedThis.put(EnumDyeColor.SILVER, 10329495);
		whyMustINeedThis.put(EnumDyeColor.CYAN, 1481884);
		whyMustINeedThis.put(EnumDyeColor.PURPLE, 8991416);
		whyMustINeedThis.put(EnumDyeColor.BLUE, 3949738);
		whyMustINeedThis.put(EnumDyeColor.BROWN, 8606770);
		whyMustINeedThis.put(EnumDyeColor.GREEN, 6192150);
		whyMustINeedThis.put(EnumDyeColor.RED, 11546150);
		whyMustINeedThis.put(EnumDyeColor.BLACK, 1908001);
		
	}
	
	//i hate this server client bulllshit
	public static int getColor(EnumDyeColor c) {
//		try {
//			System.out.println("getColorValue()");
//			return c.getColorValue();
//		}
//		catch(Throwable ex1) {
//			try {
//				Field f = EnumDyeColor.class.getField("w");
//				f.setAccessible(true);
//				System.out.println("Field");
//				return (int) f.get(c);
//			}
//			catch(Throwable ex2) {
//				
//			}
//		}
//		System.out.println("default");
//		return 0;
		
//		switch(c) {
//		case BLACK:
//			break;
//		case BLUE:
//			break;
//		case BROWN:
//			break;
//		case CYAN:
//			break;
//		case GRAY:
//			break;
//		case GREEN:
//			break;
//		case LIGHT_BLUE:
//			break;
//		case LIME:
//			break;
//		case MAGENTA:
//			break;
//		case ORANGE:
//			break;
//		case PINK:
//			break;
//		case PURPLE:
//			break;
//		case RED:
//			break;
//		case SILVER:
//			break;
//		case WHITE:
//			break;
//		case YELLOW:
//			break;
//		default:
//			break;
//		
//		}
//		
		return whyMustINeedThis.get(c);
		
	}
	
}

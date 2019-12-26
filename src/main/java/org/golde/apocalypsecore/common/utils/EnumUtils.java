package org.golde.apocalypsecore.common.utils;

import java.util.Arrays;
import java.util.Random;

public class EnumUtils {

	private static final Random random = new Random();
	
	public static String[] getNames(Class<? extends Enum<?>> e) {
		return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

	public static <T extends Enum<?>> T randomEnum(T[] arr){
		return arr[random.nextInt(arr.length)];
	}
	
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

}

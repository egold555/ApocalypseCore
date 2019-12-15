public enum EnumDyeColor
{
	WHITE(0, 15, "white", "white", 16383998),
	ORANGE(1, 14, "orange", "orange", 16351261),
	MAGENTA(2, 13, "magenta", "magenta", 13061821),
	LIGHT_BLUE(3, 12, "light_blue", "lightBlue", 3847130),
	YELLOW(4, 11, "yellow", "yellow", 16701501),
	LIME(5, 10, "lime", "lime", 8439583),
	PINK(6, 9, "pink", "pink", 15961002),
	GRAY(7, 8, "gray", "gray", 4673362),
	SILVER(8, 7, "silver", "silver", 10329495),
	CYAN(9, 6, "cyan", "cyan", 1481884),
	PURPLE(10, 5, "purple", "purple", 8991416),
	BLUE(11, 4, "blue", "blue", 3949738),
	BROWN(12, 3, "brown", "brown", 8606770),
	GREEN(13, 2, "green", "green", 6192150),
	RED(14, 1, "red", "red", 11546150),
	BLACK(15, 0, "black", "black", 1908001);

	private static final EnumDyeColor[] META_LOOKUP = new EnumDyeColor[values().length];
	private static final EnumDyeColor[] DYE_DMG_LOOKUP = new EnumDyeColor[values().length];
	private final int meta;
	private final int dyeDamage;
	private final String name;
	private final String unlocalizedName;
	/** An int containing the corresponding RGB color for this dye color. */
	private final int colorValue;
	/**
	 * An array containing 3 floats ranging from 0.0 to 1.0: the red, green, and blue components of the corresponding
	 * color.
	 */
	private final float[] colorComponentValues;;

	private EnumDyeColor(int metaIn, int dyeDamageIn, String nameIn, String unlocalizedNameIn, int colorValueIn)
	{
		this.meta = metaIn;
		this.dyeDamage = dyeDamageIn;
		this.name = nameIn;
		this.unlocalizedName = unlocalizedNameIn;
		this.colorValue = colorValueIn;
		int i = (colorValueIn & 16711680) >> 16;
		int j = (colorValueIn & 65280) >> 8;
		int k = (colorValueIn & 255) >> 0;
		this.colorComponentValues = new float[] {(float)i / 255.0F, (float)j / 255.0F, (float)k / 255.0F};
	}

	public int getMetadata()
	{
		return this.meta;
	}

	public int getDyeDamage()
	{
		return this.dyeDamage;
	}

	public String getDyeColorName()
	{
		return this.name;
	}

	public String getUnlocalizedName()
	{
		return this.unlocalizedName;
	}

	/**
	 * Gets the RGB color corresponding to this dye color.
	 */
	public int getColorValue()
	{
		return this.colorValue;
	}

	/**
	 * Gets an array containing 3 floats ranging from 0.0 to 1.0: the red, green, and blue components of the
	 * corresponding color.
	 */
	public float[] getColorComponentValues()
	{
		return this.colorComponentValues;
	}

	public static EnumDyeColor byDyeDamage(int damage)
	{
		if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
		{
			damage = 0;
		}

		return DYE_DMG_LOOKUP[damage];
	}

	public static EnumDyeColor byMetadata(int meta)
	{
		if (meta < 0 || meta >= META_LOOKUP.length)
		{
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String toString()
	{
		return this.unlocalizedName;
	}

	public String getName()
	{
		return this.name;
	}

	static
	{
		for (EnumDyeColor enumdyecolor : values())
		{
			META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
			DYE_DMG_LOOKUP[enumdyecolor.getDyeDamage()] = enumdyecolor;
		}
	}
}
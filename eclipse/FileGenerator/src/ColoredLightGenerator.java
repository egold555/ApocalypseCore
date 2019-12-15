import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ColoredLightGenerator {

	private static final String STATE_JSON = "{\r\n" + 
			"    \"variants\": {\r\n" + 
			"        \"facing=up\":  { \"model\": \"ac:%color%\", \"x\": 180 },\r\n" + 
			"        \"facing=down\":    { \"model\": \"ac:%color%\", \"x\": 0 },\r\n" + 
			"        \"facing=north\": { \"model\": \"ac:%color%\", \"x\": 90, \"y\": 180},\r\n" + 
			"        \"facing=south\": { \"model\": \"ac:%color%\", \"x\": 90, \"y:\": 0},\r\n" + 
			"        \"facing=west\":  { \"model\": \"ac:%color%\", \"x\": 90, \"y\": 90 },\r\n" + 
			"        \"facing=east\":  { \"model\": \"ac:%color%\", \"x\": 90, \"y\": 270 }\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"";

	private static final String ITEM_JSON = "{\r\n" + 
			"   \"parent\": \"ac:block/%color%\"\r\n" + 
			"}";

	private static final String BLOCK_JSON = "{\r\n" + 
			"   \"parent\": \"ac:block/lamp_caged\",\r\n" + 
			"   \"textures\": {\r\n" + 
			"       \"color\": \"ac:blocks/lamp/on/%dye%\",\r\n" + 
			"	   \"particle\": \"ac:blocks/lamp/on/%dye%\"\r\n" + 
			"   }\r\n" + 
			"}";

	private static final String RECIPE_JSON = "{\r\n" + 
			"  \"type\": \"minecraft:crafting_shaped\",\r\n" + 
			"  \"pattern\": [\r\n" + 
			"    \" S \",\r\n" + 
			"    \"IDI\",\r\n" + 
			"    \" I \"\r\n" + 
			"  ],\r\n" + 
			"  \"key\": {\r\n" + 
			"    \"S\": {\r\n" + 
			"      \"item\": \"minecraft:stone\",\r\n" + 
			"      \"data\": 0\r\n" + 
			"    },\r\n" + 
			"    \"I\": {\r\n" + 
			"      \"item\": \"minecraft:iron_bars\",\r\n" + 
			"      \"data\": 0\r\n" + 
			"    },\r\n" + 
			"    \"D\": {\r\n" + 
			"      \"item\": \"minecraft:dye\",\r\n" + 
			"      \"data\": %dye%\r\n" + 
			"    }\r\n" + 
			"  },\r\n" + 
			"  \"result\": {\r\n" + 
			"    \"item\": \"ac:lamp_%color%\",\r\n" + 
			"	\"count\": 8\r\n" + 
			"  }\r\n" + 
			"}";


	public void run() throws Exception {

		genBlockModelFiles();
		//genItemModelFiles();
		//genStateFiles();
		//generateLanguage();
		//generateReg();
		//generateTexturesOn();
		//generateTexturesOff();
		//generateRecipeFiles();
	}

	private void generateRecipeFiles() throws IOException {
		File folder = new File("output/light/recipe");
		folder.mkdirs();

		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();
			int dye = c.getDyeDamage();

			File jsonFile = new File(folder, "lamp_" + color + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(RECIPE_JSON.replace("%color%", color).replace("%dye%", "" + dye));
			writer.close();

		}
	}

	private void generateTexturesOff() throws IOException {
		for(EnumDyeColor c : EnumDyeColor.values()) {
			BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			float[] rgb = c.getColorComponentValues();
			System.out.println(Arrays.toString(rgb));
			Color co = new Color(rgb[0], rgb[1], rgb[2]);
			co = co.darker();
			co = co.darker();
			co = co.darker();
			
			g.setColor(co);
			g.fillRect ( 0, 0, bi.getWidth(), bi.getHeight());
			ImageIO.write(bi, "png", new File("output/light/textures/off/" + c.getMetadata() + ".png"));
		}
		
	}
	
	private void generateTexturesOn() throws IOException {
		for(EnumDyeColor c : EnumDyeColor.values()) {
			BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			float[] rgb = c.getColorComponentValues();
			System.out.println(Arrays.toString(rgb));
			Color co = new Color(rgb[0], rgb[1], rgb[2]);
			g.setColor(co);
			g.fillRect ( 0, 0, bi.getWidth(), bi.getHeight());
			//File f = new File("output/light/textures/on/" + c.getMetadata() + ".png");
			ImageIO.write(bi, "png", new File("output/light/textures/on/" + c.getMetadata() + ".png"));
		}
		
	}

	private void generateReg() {
		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			String tp = "public static BlockCagedLight4 lamp_" +color +  ";";
			System.out.println(tp);
		}
		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			String tp = "event.getRegistry().register(lamp_" + color + " = new BlockCagedLight4(EnumDyeColor." + color.toUpperCase() + "));";
			System.out.println(tp);
		}
	}

	private void generateLanguage() {
		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			String tp = "item.ac.lamp." + color + ".name=" + capitaliseFirstLetter(color) + " Caged Lamp";
			System.out.println(tp);
		}
	}

	private String capitaliseFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private void genBlockModelFiles() throws IOException {
		File folder = new File("output/light/block");
		folder.mkdirs();

		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			File jsonFile = new File(folder, "lamp_" + color + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(BLOCK_JSON.replace("%dye%", "" + c.getMetadata()));
			writer.close();

		}
	}

	private void genItemModelFiles() throws IOException {
		File folder = new File("output/light/item");
		folder.mkdirs();

		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			File jsonFile = new File(folder, "lamp_" + color + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(ITEM_JSON.replace("%color%", "lamp_" + color));
			writer.close();

		}
	}

	private void genStateFiles() throws IOException {
		File folder = new File("output/light/state");
		folder.mkdirs();

		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			File jsonFile = new File(folder, "lamp_" + color + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(STATE_JSON.replace("%color%", "lamp_" + color));
			writer.close();

		}
	}

}

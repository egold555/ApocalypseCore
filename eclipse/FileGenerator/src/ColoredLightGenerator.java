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
			"    \"parent\": \"block/orientable\",\r\n" + 
			"	\"__comment\": \"Designed by Eric with Cubik Studio - https://cubik.studio\",\r\n" + 
			"	\"textures\": {\r\n" + 
			"		\"particle\": \"ac:blocks/lamp/%color%\",\r\n" + 
			"		\"texture\": \"ac:blocks/lamp/light_cage\",\r\n" + 
			"		\"texture1\": \"ac:blocks/lamp/%color%\",\r\n" + 
			"		\"texture2\": \"ac:blocks/lamp/top\"\r\n" + 
			"	},\r\n" + 
			"	\"elements\": [ 	 \r\n" + 
			"		{\r\n" + 
			"			\"__comment\": \"Holder\",\r\n" + 
			"			\"from\": [ 6, 15, 6 ],\r\n" + 
			"			\"to\": [ 10, 16, 10 ],\r\n" + 
			"			\"faces\": {\r\n" + 
			"				\"down\": { \"uv\": [ 6, 6, 10, 10 ], \"texture\": \"#texture2\" },\r\n" + 
			"				\"up\": { \"uv\": [ 6, 6, 10, 10 ], \"texture\": \"#texture2\" },\r\n" + 
			"				\"north\": { \"uv\": [ 6, 0, 10, 1 ], \"texture\": \"#texture2\" },\r\n" + 
			"				\"south\": { \"uv\": [ 6, 0, 10, 1 ], \"texture\": \"#texture2\" },\r\n" + 
			"				\"west\": { \"uv\": [ 6, 0, 10, 1 ], \"texture\": \"#texture2\" },\r\n" + 
			"				\"east\": { \"uv\": [ 6, 0, 10, 1 ], \"texture\": \"#texture2\" }\r\n" + 
			"			}\r\n" + 
			"		},\r\n" + 
			"		{\r\n" + 
			"			\"__comment\": \"Inner\",\r\n" + 
			"			\"from\": [ 6.5, 12, 6.5 ],\r\n" + 
			"			\"to\": [ 9.5, 15, 9.5 ],\r\n" + 
			"			\"faces\": {\r\n" + 
			"				\"down\": { \"uv\": [ 6.5, 6.5, 9.5, 9.5 ], \"texture\": \"#texture1\" },\r\n" + 
			"				\"up\": { \"uv\": [ 6.5, 6.5, 9.5, 9.5 ], \"texture\": \"#texture1\" },\r\n" + 
			"				\"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture1\" },\r\n" + 
			"				\"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture1\" },\r\n" + 
			"				\"west\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture1\" },\r\n" + 
			"				\"east\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture1\" }\r\n" + 
			"			}\r\n" + 
			"		},\r\n" + 
			"		{\r\n" + 
			"			\"__comment\": \"Outer\",\r\n" + 
			"			\"from\": [ 6, 11.5, 6 ],\r\n" + 
			"			\"to\": [ 10, 15, 10 ],\r\n" + 
			"			\"faces\": {\r\n" + 
			"				\"down\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n" + 
			"				\"up\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n" + 
			"				\"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n" + 
			"				\"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n" + 
			"				\"west\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n" + 
			"				\"east\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" }\r\n" + 
			"			}\r\n" + 
			"		}\r\n" + 
			"	],\r\n" + 
			"	\"display\": {\r\n" + 
			"		\"thirdperson_righthand\": {\r\n" + 
			"			\"rotation\":  [ 75, 45, 0 ],\r\n" + 
			"			\"translation\":  [ 0, 1.1086, -1.391 ],\r\n" + 
			"			\"scale\":  [ 0.375, 0.375, 0.375 ]\r\n" + 
			"		},\r\n" + 
			"		\"thirdperson_lefthand\": {\r\n" + 
			"			\"rotation\":  [ 75, 45, 0 ],\r\n" + 
			"			\"translation\":  [ 0, 1.1086, -1.391 ],\r\n" + 
			"			\"scale\":  [ 0.375, 0.375, 0.375 ]\r\n" + 
			"		},\r\n" + 
			"		\"firstperson_righthand\": {\r\n" + 
			"			\"rotation\":  [ 0, 45, 0 ],\r\n" + 
			"			\"scale\":  [ 0.4, 0.4, 0.4 ]\r\n" + 
			"		},\r\n" + 
			"		\"firstperson_lefthand\": {\r\n" + 
			"			\"rotation\":  [ 0, 45, 0 ],\r\n" + 
			"			\"scale\":  [ 0.4, 0.4, 0.4 ]\r\n" + 
			"		},\r\n" + 
			"		\"gui\": {\r\n" + 
			"			\"rotation\":  [ 30, 225, 0 ],\r\n" + 
			"			\"translation\":  [ 0, -2.782, 0 ],\r\n" + 
			"			\"scale\":  [ 0.625, 0.625, 0.625 ]\r\n" + 
			"		},\r\n" + 
			"		\"ground\": {\r\n" + 
			"			\"translation\":  [ 0, 3, 0 ],\r\n" + 
			"			\"scale\":  [ 0.5, 0.5, 0.5 ]\r\n" + 
			"		},\r\n" + 
			"		\"fixed\": {\r\n" + 
			"			\"scale\":  [ 0.5, 0.5, 0.5 ]\r\n" + 
			"		}\r\n" + 
			"	}\r\n" + 
			"}";



	public void run() throws Exception {

		//genBlockModelFiles();
		//genItemModelFiles();
		//genStateFiles();
		//generateLanguage();
		//generateReg();
		//generateTexturesOn();
		generateTexturesOff();
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
			writer.write(BLOCK_JSON.replace("%color%", "on/" + c.getMetadata()));
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

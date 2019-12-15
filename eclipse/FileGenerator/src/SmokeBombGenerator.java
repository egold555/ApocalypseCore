import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SmokeBombGenerator {

	private static final String DUMMY_JSON = "{\r\n" + 
			"  \"parent\": \"item/generated\",\r\n" + 
			"  \"textures\": {\r\n" + 
			"    \"layer0\": \"ac:items/smokebomb_%color%\"\r\n" + 
			"  }\r\n" + 
			"}";

	

	public void run() throws Exception {

		//generateModelFiles();
		//generateLanguage();
		generateImages();
	}

	private void generateImages() throws IOException {

		BufferedImage underlay = ImageIO.read(new File("input/smoke/underlay.png"));
		BufferedImage overlay = ImageIO.read(new File("input/smoke/overlay.png"));





		for(EnumDyeColor c : EnumDyeColor.values()) {
			String name = c.name().toLowerCase();
			Color color = new Color(c.getColorValue());
			BufferedImage newImg = dye(underlay, color);

			BufferedImage combined = new BufferedImage(overlay.getWidth(), overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(newImg, 0, 0, null);
			g.drawImage(overlay, 0, 0, null);

			ImageIO.write(combined, "png", new File("output/smoke/smokebomb_" + name + ".png"));
		}


	}

	private BufferedImage dye(BufferedImage image, Color color)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(image, 0,0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0,0,w,h);
		g.dispose();
		return dyed;
	}

	private void generateLanguage() {
		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			String tp = "item.ac.smoke_bomb." + color + ".name=" + capitaliseFirstLetter(color) + " Smoke Bomb";
			System.out.println(tp);
		}
	}

	private String capitaliseFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private void generateModelFiles() throws IOException {
		File folder = new File("output/smoke");
		folder.mkdirs();

		for(EnumDyeColor c : EnumDyeColor.values()) {
			String color = c.name().toLowerCase();

			File jsonFile = new File(folder, "smoke_bomb_" + color + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(DUMMY_JSON.replace("%color%", color));
			writer.close();

		}
	}

}

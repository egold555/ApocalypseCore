package org.golde.apocalypsecore.common.utils.griffiti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ThreadCreateGraffiti {

	//color of -1 means no color
	public static void create(String text, int color, int color2, GraffitiCreatedCallback callback) {

		new Thread() {
			@Override
			public void run() {

				Graffiti g = new Graffiti(GraffitiFonts.getTest(new File("C:\\Users\\eric\\eclipse-workspace\\FontRenderer")), text);
				if(color2 != -1) {
					if(color == -1) {
						g.drawText(new Color(color2));
					}
					else {
						g.drawText(new Color(color2), 5, 5);
					}
					
				}
				
				if(color != -1) {
					g.drawText(new Color(color));
				}
				

				BufferedImage image = createRoundedTo128BufferedImage(g.getImg());

				HashMap<int[], int[][]> split = getSplitImage(image);
				g.dispose();
				
				callback.onFinish(split);

				


			}
		}.start();

	}

	private static HashMap<int[], int[][]> getSplitImage(BufferedImage image) {
		HashMap<int[], int[][]> giantAssMap = new HashMap<int[], int[][]>();

		int x1 = 0;
		int y1 = 0;
		for(int x = 0; x < image.getWidth(); x+=128) {
			y1 = 0;
			for(int y = 0; y < image.getHeight(); y+=128) {
				BufferedImage sub = image.getSubimage(x, y, 128, 128);
				int[][] data = decomposeBufferedImageIntoPixels(sub);
				giantAssMap.put(new int[] {x1, y1}, data);
				y1++;
			}
			x1++;
		}

		return giantAssMap;
	}

	private static int[][] decomposeBufferedImageIntoPixels(BufferedImage image){
		int[][] data = new int[image.getWidth()][image.getHeight()];
		for(int x = 0; x < data.length; x++) {
			for(int y = 0; y < data[x].length; y++) {
				data[x][y] = image.getRGB(x, y);
			}
		}
		return data;
	}

	private static BufferedImage createRoundedTo128BufferedImage(BufferedImage bi) {
		int w = bi.getWidth();
		int h = bi.getHeight();
		int nw = round(w, 128);
		int nh = round(h, 128);

		BufferedImage newbi = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_ARGB);
		Graphics newbiG = newbi.getGraphics();
		newbiG.setColor(new Color(0, 0, 0, 0));
		newbiG.fillRect(0, 0, newbi.getWidth(), newbi.getHeight());
		newbiG.drawImage(bi, 0, 0, null);

		return newbi;
	}

	static int round(int in, int mutiple){

		if(in == mutiple) {
			return in;
		}

		if(in < mutiple) {
			return mutiple;
		}

		int change = mutiple;
		while(in > change) {
			change += mutiple;
		}

		return change;
	}

	public static interface GraffitiCreatedCallback {
		public void onFinish(HashMap<int[], int[][]> data);
	}

}

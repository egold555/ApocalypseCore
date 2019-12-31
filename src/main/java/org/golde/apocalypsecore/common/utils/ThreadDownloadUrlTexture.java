package org.golde.apocalypsecore.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

public class ThreadDownloadUrlTexture {

	public static void downloadAndSetTexture(String url, ResourceLocationCallback callback)
	{
		new Thread() {
			@Override
			public void run() {
				
			
				try {
					URL url2 = new URL(url);
					BufferedImage image = createRoundedTo128BufferedImage(ImageIO.read(url2));
					callback.onTextureLoaded(image);
					
					HashMap<int[], int[][]> split = getSplitImage(image);
					callback.onSplit(split);
					
					int[][] data = decomposeBufferedImageIntoPixels(image);
					
					callback.onDataParsed(data, image.getWidth(), image.getHeight());
					
					
					
				} 
				catch (IOException e) {
					
				}
		        

			}
		}.start();
	}
	
	private static HashMap<int[], int[][]> getSplitImage(BufferedImage image) {
		HashMap<int[], int[][]> giantAssMap = new HashMap<int[], int[][]>();
		
		int x1 = 0;
		int y1 = 0;
		for(int x = 0; x < image.getWidth(); x+=128) {
			y1 = 0;
			for(int y = 0; y < image.getWidth(); y+=128) {
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
		
		if(in < mutiple) {
			return mutiple;
		}
		
		int change = mutiple;
		while(in > change) {
			change += 128;
		}
		
		return change;
	}

	public interface ResourceLocationCallback {
		public void onTextureLoaded(BufferedImage bi);
		public void onDataParsed(int[][] data, int imgW, int imgH);
		public void onSplit(HashMap<int[], int[][]> data);
	}

}

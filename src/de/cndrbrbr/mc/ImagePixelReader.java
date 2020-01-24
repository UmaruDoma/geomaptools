// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * @author Sabine
 */
public class ImagePixelReader {

	BufferedImage img = null;
	/**
	 * @param args
	 */
	public ImagePixelReader(String Filename) throws Exception {
		img = ImageIO.read(new File(Filename));
	}
	public int Getxdim()
	{
	     return img.getWidth();
	}
	public int Getydim()
	{
		return img.getHeight();
	}
	public Color GetColor(int x, int y) {
		//Pixelfarbe an stelle x=100, y=50
		int rgb = img.getRGB(x, y);
		Color c = new Color(rgb);
		System.out.println("Red: " + c.getRed());
		System.out.println("Green: " + c.getGreen());
		System.out.println("Blue: " + c.getBlue());
		return c;
	}
}
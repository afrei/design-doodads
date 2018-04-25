/**
 * File: RandomImage.java
 *
 * Description:
 * Create a random color image.
 *
 * @author Yusuf Shakeel
 * Date: 01-04-2014 tue
 */
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Lines{
	public static void main(String args[])throws IOException{
		//image dimension
		int width = 3500;
		int height = 2500;
		//create buffered image object img
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//file object
		File f = null;
		//create random image pixel by pixel
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int a = (int)(255);
				int q = f(x, y, width, height);
				int r = r(q);
				int g = g(q);
				int b = b(q);
				int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel

				img.setRGB(x, y, p);
			}
		}
		//write image
		try{
			f = new File("waves2.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}//main() ends here
	public static int f(int x, int y, int width, int height){
		double a = ((1.0) * x) / width;
		double b = ((1.0) * x) / height;
		double xx = x * a * b * 2;
		double yx = Math.max(y * a + 3 * x * a * b - 3 * x * b * b * a * a, 0.0);
		double c = (Math.sin(xx * 0.03) * Math.cos(yx * 0.03));
		if (c < .4) {
			return 0;
		}
		if (c < .6) {
			return 1;
		}
		if (c < .67) {
			return 2;
		}
		if (c < .75) {
			return 3;
		}
		if (c < .9) {
			return 4;
		}
		return 5;
	}
	public static int r(int q){
		if (q == 0) {
			return 240;
		}
		if (q == 1) {
			return 23;
		}
		if (q == 2) {
			return 240;
		}
		if (q == 3) {
			return 234;
		}
		if (q == 4) {
			return 23;
		}
		if (q == 5) {
			return 240;
		}
		return 0;
	}
	public static int g(int q){
		if (q == 0) {
			return 255;
		}
		if (q == 1) {
			return 50;
		}
		if (q == 2) {
			return 255;
		}
		if (q == 3) {
			return 200;
		}
		if (q == 4) {
			return 50;
		}
		if (q == 5) {
			return 255;
		}
		return 0;
	}
	public static int b(int q){
		if (q == 0) {
			return 240;
		}
		if (q == 1) {
			return 70;
		}
		if (q == 2) {
			return 240;
		}
		if (q == 3) {
			return 200;
		}
		if (q == 4) {
			return 70;
		}
		if (q == 5) {
			return 240;
		}
		return 0;
	}
}//class ends here
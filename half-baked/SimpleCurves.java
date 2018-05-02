import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SimpleCurves{
	public static void main(String args[])throws IOException{
		//image dimension
		int width = 1618;
		int height = 1000;
		//create buffered image object img
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//file object
		File f = null;
		//create random image pixel by pixel
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				double q = f((1.0)*x / width, (1.0) * y / height);
				int p = colorq(q); //(255<<24) | (r<<16) | (g<<8) | b; //pixel
				img.setRGB(x, y, p);
			}
		}
		//write image
		try{
			f = new File("simpleCurves.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}//main() ends here
	public static double f(double x, double y){
		return Math.min(1, Math.max(x + 0.008 * Math.sin(10 * y * x), 0));
	}
	public static int colorq(double q){
		int p, r, g, b;

		if (q < 15.975/24.0) {
			r = 255;
			g = 240;
			b = 245;
		}
		else if (q < 16.0 / 24.0) {
			double z = 4.0 * 240.0*(q - (15.975/24.0));
		        r = (int) (255 * (1 - z) + 100 * z);
			g = (int) (240 * (1 - z) + 149 * z);
			b = (int) (245 * (1 - z) + 237 * z);
		}
		else if (q < 16.775/24.0) {
			r = 100;
			g = 149;
			b = 237;
		}
		else if (q < 16.8/24.0) {
			double z = 4.0 * 240.0*(q - (16.775/24.0));
		        r = (int) (46 * (z) + 100 * (1-z));
			g = (int) (139 * (z) + 149 * (1-z));
			b = (int) (87 * (z) + 237 * (1-z));
		}
		else if (q < 20.2/24.0) {
			r = 46;
			g = 139;
			b = 87;
		}
		else if (q < 20.2250/24.0){
			double z = 4.0 * 240.0*(q - (20.2/24.0));
		        r = (int) (46  * (1-z) + 255 * (z));
			g = (int) (139 * (1-z) + 240 * (z));
			b = (int) (87  * (1-z) + 245 * (z));
		}
		else {
			r = 255;
			g = 240;
			b = 245;
		}
		p = (255<<24) | (r<<16) | (g<<8) | b;
		return p;
	}
}//class ends here
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class RandomTexture{
	public static void main(String args[])throws IOException{
		int width = 1000;
		int height = 1000;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		Random rand = new Random();
		double[] nums0 = new double[width];
		double[] nums1 = new double[width];
		double temp = 0;
		for (int i = 0; i < width; i++) {
			nums0[i] = .50;
			nums1[i] = .50;
		}
		for(int y = 0; y < height; y++){
			if (y % 2 == 0) {
				temp = ((nums1[0]+nums1[1])/2.0) + (0.02 * rand.nextGaussian());
				if (temp < .75 && temp > .25)
					nums0[0] = temp;
				else
					nums0[0] = nums1[0];
				for (int i = 1; i < width - 1; i++) {
					temp = ((nums1[i - 1] + nums1[i] + nums1[i+1]) / 3.0) + (0.02 * rand.nextGaussian());
					if (temp < .75 && temp > .25)
						nums0[i] = temp;
					else
						nums0[0] = nums1[0];
				}
				temp = ((nums1[width - 2]+nums1[width - 1])/2.0) + (0.02 * rand.nextGaussian());
				if (temp < .75 && temp > .25)
					nums0[width-1] = temp;
				else
					nums0[width-1] = nums1[width-1];
				for(int x = 0; x < width; x++) {
					img.setRGB(x, y, color(nums0[x]));
				}
			}
			else {
				temp = ((nums0[0]+nums0[1])/2.0) + (0.02 * rand.nextGaussian());
				if (temp < .75 && temp > .25)
					nums1[0] = temp;
				else
					nums1[0] = nums0[0];
				for (int i = 1; i < width - 1; i++) {
					temp = ((nums0[i - 1] + nums0[i] + nums0[i+1]) / 3.0) + (0.02 * rand.nextGaussian());
					if (temp < .75 && temp > .25)
						nums1[i] = temp;
					else
						nums1[0] = nums0[0];
				}
				temp = ((nums0[width - 2]+nums0[width - 1])/2.0) + (0.02 * rand.nextGaussian());
				if (temp < .75 && temp > .25)
					nums1[width-1] = temp;
				else
					nums1[width-1] = nums0[width-1];
				for(int x = 0; x < width; x++){
					img.setRGB(x, y, color(nums1[x]));
				}
			}
		}

		try{
			f = new File("randomTexture.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}

	public static int color(double z){
		int r = (int) (z * 255);
		int g = (int) (z * 10);
		int b = (int) (z * 20);
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}
}
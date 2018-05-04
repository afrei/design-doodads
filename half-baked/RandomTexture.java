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
		double[][] nums = new double[2][width];
		double temp = 0;

		for (int j = 0; j < 2; j++){
			for (int i = 0; i < width; i++) {
				nums[j][i] = .3 + 0.1*rand.nextGaussian() + i * .4/width;
			}
		}

		for(int y = 0; y < height; y++){
			int j = y % 2;
			int k = 1 - j;
			temp = ((nums[k][0]+nums[k][1])/2.0) + (0.02 * (rand.nextGaussian()));
			nums[j][0] = temp;
			for (int i = 1; i < width - 1; i++) {
				temp = ((nums[k][i - 1] + nums[k][i] + nums[k][i+1]) / 3.0) + (0.02 * rand.nextGaussian());
				nums[j][i] = temp;
			}
			temp = ((nums[k][width - 2] + nums[k][width - 1])/2.0) + (0.02 * rand.nextGaussian());
			nums[j][width-1] = temp;
			for(int x = 0; x < width; x++) {
				img.setRGB(x, y, color(Math.min(Math.max(nums[j][x], .1), .9)));
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
		int r = (int) (z * 255 + (1-z) * 0);
		int g = (int) (z * 25  + (1-z) * 0);
		int b = (int) (z * 25  + (1-z) * 0);
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}
}
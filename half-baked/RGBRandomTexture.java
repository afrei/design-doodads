import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class RGBRandomTexture{
	public static void main(String args[])throws IOException{
		int width = 1000;
		int height = 1000;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		Random rand = new Random();
		double[][][] nums = new double[3][2][width];
		double temp = 0;
		for (int k = 0; k < 3; k++){
			for (int j = 0; j < 2; j++){
				for (int i = 0; i < width; i++) {
					nums[k][j][i] = ((i / 120) % 2) * (.2 * k) + .3;
				}
			}
		}

		for(int y = 0; y < height; y++){
			int j = y % 2;
			int k = 1 - j;
			for (int l = 0; l < 3; l++){
				temp = ((nums[l][k][0]+nums[l][k][1])/2.0) + (0.02 * (rand.nextGaussian()));
				nums[l][j][0] = Math.min(Math.max(temp, .3), .9);
				for (int i = 1; i < width - 1; i++) {
					temp = ((nums[l][k][i - 1] + nums[l][k][i] + nums[l][k][i+1]) / 3.0) +
						(0.02 * rand.nextGaussian());
					nums[l][j][i] = Math.min(Math.max(temp, .3), .9);
				}
				temp = ((nums[l][k][width - 2] + nums[l][k][width - 1])/2.0) +
					(0.02 * rand.nextGaussian());
				nums[l][j][width-1] = Math.min(Math.max(temp, .3), .9);
			}
			for(int x = 0; x < width; x++) {
				double d1 = (1.0) * x / width - .5;
				double d2 = (1.0) * y / height - .5;
				double d3 = d1 * d1 + d2 * d2;
				//if (d3 < .1)
				img.setRGB(x, y, color(nums[0][j][x],
						       nums[1][j][x],
						       nums[2][j][x]));
				//else if (d3 < .11)
				//img.setRGB(x, y, color(0, 0, 0));
				//else
				//img.setRGB(x, y, color(7.0 * x / width,
				//		       5.0 * y / height,
				//		       13.0 * (2*x + 3*y) / (width + height)));
			}
		}

		try{
			f = new File("rgbRandomTexture.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}

	public static int color(double z1, double z2, double z3){
		int r = (int) (z1 * 255);
		int g = (int) (z2 * 255);
		int b = (int) (z3 * 255);
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}
	public static int flatten(double z1, double z2, double z3){
		double z4 = (z1 + z2 + z3)/1.5;
		z1 /= z4;
		z2 /= z4;
		z3 /= z4;
		int r = (int) (z1 * 255);
		int g = (int) (z2 * 255);
		int b = (int) (z3 * 255);
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}
}
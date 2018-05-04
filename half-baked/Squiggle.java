import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Squiggle{

	public static void main(String args[])throws IOException{
		int width = 1000;
		int height = 1000;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		//Random rand = new Random();
		//double[][][] nums = new double[3][2][width];
		//double temp = 0;
		//for (int k = 0; k < 3; k++){
		//	for (int j = 0; j < 2; j++){
		//		for (int i = 0; i < width; i++) {
		//			nums[k][j][i] = ((i / 120) % 2) * (.2 * k) + .3;
		//		}
		//	}
		//}

		for(int y = 0; y < height; y++){
			//int j = y % 2;
			//int k = 1 - j;
			//for (int l = 0; l < 3; l++){
			//	temp = ((nums[l][k][0]+nums[l][k][1])/2.0) + (0.02 * (rand.nextGaussian()));
			//	nums[l][j][0] = Math.min(Math.max(temp, .3), .9);
			//	for (int i = 1; i < width - 1; i++) {
			//		temp = ((nums[l][k][i - 1] + nums[l][k][i] + nums[l][k][i+1]) / 3.0) +
			//			(0.02 * rand.nextGaussian());
			//		nums[l][j][i] = Math.min(Math.max(temp, .3), .9);
			//	}
			//	temp = ((nums[l][k][width - 2] + nums[l][k][width - 1])/2.0) +
			//		(0.02 * rand.nextGaussian());
			//	nums[l][j][width-1] = Math.min(Math.max(temp, .3), .9);
			//}
			for(int x = 0; x < width; x++) {
				img.setRGB(x, y, color(rval(x, y),
						       bval(x, y),
						       gval(x, y)));
			}
		}

		try{
			f = new File("wavyGravy.png");
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
	public static int colorInt(int r, int g, int b){
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

	public static int rval(int x, int y){
		int width = 1000;
		int height = 1000;
		int offset = (int)(Math.sin(((1.0) * y / height) * 10.0 * Math.PI) * (.1) * width);
		int z = x + offset;
		int w = z / 40; // this is the number skinny column we're in
		int a = w % 6;
		switch (a) {
		case 0:
			return 100;
		case 1:
			return 200;
		case 2:
			return 30;
		default:
			return 230;
		}
	}
	public static int bval(int x, int y){
		int width = 1000;
		int height = 1000;
		int offset = (int)(Math.sin(((1.0) * y / height) * 10.0 * Math.PI) * (.1) * width);
		int z = x + offset;
		int w = z / 40; // this is the number skinny column we're in
		int a = w % 6;
		switch (a) {
		case 0:
			return 130;
		case 1:
			return 20;
		case 2:
			return 230;
		default:
			return 200;
		}
	}
	public static int gval(int x, int y){
		int width = 1000;
		int height = 1000;
		int offset = (int)(Math.sin(((1.0) * y / height) * 10.0 * Math.PI) * (.1) * width);
		int z = x + offset;
		int w = z / 40; // this is the number skinny column we're in
		int a = w % 6;
		switch (a) {
		case 0:
			return 175;
		case 1:
			return 75;
		case 2:
			return 62;
		default:
			return 235;
		}
	}
}
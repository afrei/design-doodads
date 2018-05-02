import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class InterText{
	public static void main(String args[])throws IOException{
		int width = 900;
		int height = 600;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		Random rand = new Random();
		double[] nums0 = new double[width];
		double[] nums1 = new double[width];
		double temp = 0;
		int col = 0;
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
				for(int x = 0; x < width; x++){
					col = avgCol(col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height));
					img.setRGB(x, y, scale(nums0[x], col));
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
					col = avgCol(col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
						     col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height));
					img.setRGB(x, y, scale(nums1[x], col));
				}
			}
		}

		try{
			f = new File("inter.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}
	public static int col(double x, double y){
		if ((Math.abs((x+1.0) - ((0.035 * Math.sin((y + 0.05) * 60.0)))) % 0.1) < .01)
			return -537911;
		else if ((Math.abs((x+1.0) - ((0.035 * Math.sin((y + 0.05) * 60.0)))) % 0.1) < .02)
			return -7165743;
		else if ((Math.abs((x+1.05) - ((0.035 * Math.sin(y * 60.0)))) % 0.1) < .01)
			return -537911;
		else
			return -7165743;

	}

	public static int avgCol(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8){
		int r = (((x1 >> 16) & 0xFF) + ((x2 >> 16) & 0xFF) + ((x3 >> 16) & 0xFF) +
			 ((x4 >> 16) & 0xFF) + ((x5 >> 16) & 0xFF) + ((x6 >> 16) & 0xFF) +
			 ((x7 >> 16) & 0xFF) + ((x8 >> 16) & 0xFF))/8;
		int g = (((x1 >> 8) & 0xFF) + ((x2 >> 8) & 0xFF) + ((x3 >> 8) & 0xFF) +
			 ((x4 >> 8) & 0xFF) + ((x5 >> 8) & 0xFF) + ((x6 >> 8) & 0xFF) +
			 ((x7 >> 8) & 0xFF) + ((x8 >> 8) & 0xFF))/8;
		int b = (((x1) & 0xFF) + ((x2) & 0xFF) + ((x3) & 0xFF) +
			 ((x4) & 0xFF) + ((x5) & 0xFF) + ((x6) & 0xFF) +
			 ((x7) & 0xFF) + ((x8) & 0xFF))/8;
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}

	public static int scale(double z, int x){
		int r = (int)(z * ((x >> 16) & 0xFF));
		int g = (int)(z * ((x >> 8) & 0xFF));
		int b = (int)(z * (x & 0xFF));
		return ((255<<24) | (r<<16) | (g<<8) | b);
	}

}
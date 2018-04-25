import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Tiles{
	public static void main(String args[])throws IOException{
		int width = 900;
		int height = 600;
		int[][] a = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


		int black = -537911;//(255<<24) | (247<<16) | (202<<8) | 201; //pixel
		int white = -7165743;//(255<<24) | (146<<16) | (168<<8) | 209; //pixel
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				img.setRGB(x, y, avgCol(col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col(a, (1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height)));
			}
		}

		try{
			f = new File("tileAlias.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}
	public static int col(int[][] arr, double x, double y){
		int a = (int) (30 * x);
		int b = (int) (20 * y);
		double c = (x * 30) % 1.0;
		double d = (y * 20) % 1.0;
		if(arr[b][a] == 0){
			if (((a + b + c) % 2 == 1) ^ (c > d))
				return -537911;//-16777216;
			else
				return -7165743;
		} else {
			if (!((a + b + c) % 2 == 1) ^ (c > d))
				return ((0&0x0ff)<<16)|((0&0x0ff)<<8)|(128&0x0ff);//-16777216;
			else
				return ((34&0x0ff)<<16)|((139&0x0ff)<<8)|(34&0x0ff);
		}
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
}
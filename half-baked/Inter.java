import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Inter{
	public static void main(String args[])throws IOException{
		int width = 900;
		int height = 600;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				img.setRGB(x, y, avgCol(col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height),
							col((1.0) * (x + Math.random())/width, (1.0) * (y + Math.random())/height)));
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
			return -537911;//-16777216;
		else if ((Math.abs((x+1.0) - ((0.035 * Math.sin((y + 0.05) * 60.0)))) % 0.1) < .02)
			return -7165743; //-537911;//-16777216;
		else if ((Math.abs((x+1.05) - ((0.035 * Math.sin(y * 60.0)))) % 0.1) < .01)
			return -537911;//-16777216;
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
}
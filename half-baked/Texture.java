import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Texture{
	public static void main(String args[])throws IOException{
		int width = 900;
		int height = 600;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				img.setRGB(x, y, grey((1.0)*x/width, (1.0)*y/height));
			}
		}

		try{
			f = new File("texture.png");
			ImageIO.write(img, "png", f);
		}catch(IOException e){
			System.out.println("Error: " + e);
		}
	}
	public static int grey(double x, double y){
		double z = 1 - (0.1 * (1 + Math.cos(Math.PI * x * 9)) * (1 + Math.sin(Math.PI * 6.0 * y)));
		int w = (int) (z * 255);
		return 255 << 24 | w << 16 | w << 8 | w;
	}
}
// I ripped this off from someone on the internet more or less
// I hope to properly attribute this in an upcoming commit

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class KMeans{

	private int [][] _data;
	private int [] _labels;

	private int [][] _centroids;
	private int _npoints;
	private int _ndims = 3;
	private int _numClusters = 8;

	public KMeans(String fileName) {
		BufferedImage img = null;
		File f = null;

		try {
			f = new File(fileName);
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		int height = img.getHeight();
		int width = img.getWidth();
		_npoints = width * height;

		int curr = 0;

		_data = new int[_npoints][];
		for (int i = 0 ; i < _npoints; i++)
			_data[i] = new int[_ndims];

		for (int x = 0 ; x < width ; x++){
			for (int y = 0 ; y < height ; y++){
				writePixels(_data[curr], img.getRGB(x, y));
				curr++;
			}
		}
	}

	public void clustering(int niter){
		_centroids = new int[_numClusters][];
		ArrayList<Integer> idx = new ArrayList<Integer>();
		for (int i = 0 ; i < _numClusters ; i++){
			int c;
			do {
				c = (int) (Math.random()*_npoints);
			} while (idx.contains(c));
			idx.add(c);
			_centroids[i] = new int[_ndims];
			for (int j = 0; j <_ndims; j++)
				_centroids[i][j] = _data[c][j];
		}
		int[][] c1 = _centroids;
		double threshold = 0.001;
		int round = 0;

		while (true) {
			_centroids = c1;
			_labels = new int[_npoints];
			for (int i = 0; i <_npoints ; i++)
				_labels[i] = closest(_data[i]);

			c1 = updateCentroids();
			round++;
			if ((niter >0 && round >=niter) || converge(_centroids, c1, threshold))
				break;
		}
	}

	public int retCol(int pixel){
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		int[] a = {red, green, blue};
		int y = closest(a);
		return (255 << 24) | (_centroids[y][0]<<16) | (_centroids[y][1]<<8) | _centroids[y][2];
	}

	private int closest(int[] v) {
		double mindist = dist(v, _centroids[0]);
		int label = 0;
		for (int i = 1; i<_numClusters; i++){
			double t = dist(v, _centroids[i]);
			if (mindist > t){
				mindist = t;
				label = i;
			}
		}
		return label;
	}

	private double dist(int[] v1, int[] v2){
		int total = 0;
		for (int i = 0; i < v1.length; i++)
			total += (v1[i] - v2[i])*(v1[i] - v2[i]);
		return Math.sqrt(total);
	}

	private int [][] updateCentroids(){
		int [][] newc = new int[_numClusters][];
		int [] counts = new int[_numClusters];

		for (int i=0; i<_numClusters; i++){
			counts[i] = 0;
			newc[i] = new int [_ndims];
			for (int j=0; j<_ndims; j++)
				newc[i][j] =0;
		}

		for (int i = 0; i<_npoints; i++){
			int cn = _labels[i];
			for (int j = 0; j < _ndims; j++){
				newc[cn][j] += _data[i][j];
			}
			counts[cn]++;
		}

		for (int i=0; i< _numClusters; i++){
			for (int j=0; j<_ndims; j++){
				int nc = randomDiv(newc[i][j],Math.max(1, counts[i]));
				newc[i][j] = nc;
			}
		}
		return newc;
	}

	public static int randomDiv(int x, int y) {
		int z = (int) Math.random() * y;
		if ((x % y) > z + 1)
			return x / y + 1;
		return x / y;
	}


	private boolean converge(int [][] c1, int [][] c2, double threshold){
		double maxv = 0;
		for (int i=0; i< _numClusters; i++){
			double d = dist(c1[i], c2[i]);
			if (maxv<d)
				maxv = d;
		}

		if (maxv < threshold)
			return true;
		else
			return false;

	}

	public static void writePixels(int data[], int pixel){
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		data[0] = red;
		data[1] = green;
		data[2] = blue;
	}

	public void printClusters(){
		for (int i = 0; i < _numClusters; i++) {
			System.out.println("Cluster " + i + ": is R: " + _centroids[i][0] + " G: " + _centroids[i][1]+ " B: " + _centroids[i][2]);
		}
	}

	public static void main(String args[])throws IOException{
		KMeans KM = new KMeans(args[0]);
		KM.clustering(10);
		//KM.printClusters();
		BufferedImage img1 = null;
		File f = null;

		try {
			f = new File(args[0]);
			img1 = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		int height = img1.getHeight();
		int width = img1.getWidth();

		BufferedImage img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                f = null;

                for(int x = 0; x < width; x++){
                        for(int y = 0; y < height; y++){
                                img2.setRGB(x, y, KM.retCol(img1.getRGB(x, y)));
                        }
                }
                try{
                        f = new File(args[1]);
                        ImageIO.write(img2, "png", f);
                } catch(IOException e){
                        System.out.println("Error: " + e);
                }
	}
}
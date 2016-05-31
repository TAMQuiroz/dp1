/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import static org.opencv.highgui.Highgui.imread;
import static org.opencv.highgui.Highgui.imwrite;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Claudia
 */
public class PreprocessSignature {
    private static final int IMG_WIDTH = 146; //430
    private static final int IMG_HEIGHT = 70; //221
    
    public static void main(String[] args) throws IOException {

      File dll = new File("lib\\opencv_java2412.dll");
      java.lang.System.load(dll.getAbsolutePath());

      Mat frame = new Mat();
      
      try{
          String ext = ".jpg";
          String route = "firmas\\";
          String out = "firmas\\resized\\";
            for(int i=1;i<=58;i++){
                String nombre  = "f0";
                if(i<10)
                    nombre=nombre+"0";
                nombre=nombre+i;
                java.lang.System.out.print(i);
		BufferedImage originalImage = ImageIO.read(new File(route+nombre+ext));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		
                String routeRe=out+nombre+"r";
		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "png", new File(routeRe+ext)); 
                
                //preprosesada
                frame = imread(routeRe+ext,1);
                frame = doCanny(frame);
                imwrite(routeRe+"p"+ext,frame);
            }
	}catch(IOException e){
                java.lang.System.out.println(e.getMessage());
	}
      
   }
    
   private static Mat doCanny(Mat frame)
	{
		// init
		Mat grayImage = new Mat();
		Mat detectedEdges = new Mat();
		
		// convert to grayscale
		Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);
		
		// reduce noise with a 3x3 kernel
		Imgproc.blur(grayImage, detectedEdges, new Size(3, 3));
		
		// canny detector, with ratio of lower:upper threshold of 3:1
		//Imgproc.Canny(detectedEdges, detectedEdges, this.threshold.getValue(), this.threshold.getValue() * 3);
		Imgproc.Canny(detectedEdges, detectedEdges, 6, 6*3);
                
		// using Canny's output as a mask, display the result
		Mat dest = new Mat();
		frame.copyTo(dest, detectedEdges);
		
		return dest;
	}
    
   private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
		
	return resizedImage;
    }
	
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
		
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);

	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	
	return resizedImage;
    }	

  

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Claudia
 */
public class SignatureLib {
    static JTextArea console;
    public static int max;
    private static final int IMG_WIDTH = 640; //430
    private static final int IMG_HEIGHT = 430; //221
    //public static int indice;
    //public static int cambio;
    //firmas(persona existente, registro en padron)
    public static int validarFirmas(String routeRNV, String routeAdherent) {
        
        int res =sift(routeRNV, "../pre/sig.jpg");
       
        java.lang.System.out.println("Firma - matches: "+max+" res:"+res);
        
        return res;
    }  
        
        public static int sift(String routeRNV, String routeAdherent){
  
        String bookObject = routeAdherent;
        String bookScene = routeRNV; 

        //System.out.println("Iniciando SIFT");
        //java.lang.System.out.print("Abriendo imagenes | ");
        Mat objectImage = Highgui.imread(bookObject, Highgui.CV_LOAD_IMAGE_COLOR);  
        Mat sceneImage = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);  

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();  
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);  
        //java.lang.System.out.print("Encontrar keypoints con SIFT | ");  
        featureDetector.detect(objectImage, objectKeyPoints);  
        KeyPoint[] keypoints = objectKeyPoints.toArray();  

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();  
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);  
        //java.lang.System.out.print("Computando descriptores | ");  
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);  

        // Create the matrix for output image.   
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar newKeypointColor = new Scalar(255, 0, 0);  

        //java.lang.System.out.print("Dibujando keypoints en imagen base | ");  
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);  

        // Match object image with the scene image  
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();  
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();  
        //java.lang.System.out.print("Detectando keypoints en imagen base | ");
        featureDetector.detect(sceneImage, sceneKeyPoints);  
        //java.lang.System.out.print("Computando descriptores en imagen base | ");
        descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);  

        Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar matchestColor = new Scalar(0, 255, 0);  

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();  
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);  
        //java.lang.System.out.println(sceneDescriptors);  
        
        if(sceneDescriptors.empty()){
            java.lang.System.out.println("Objeto no encontrado");
            return 0; 
        }
        
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);  

        //java.lang.System.out.println("Calculando buenos matches");
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();  

        float nndrRatio = 0.7f;  

        for (int i = 0; i < matches.size(); i++)  
        {  
            MatOfDMatch matofDMatch = matches.get(i);  
            DMatch[] dmatcharray = matofDMatch.toArray();  
            DMatch m1 = dmatcharray[0];  
            DMatch m2 = dmatcharray[1];  

            if (m1.distance <= m2.distance * nndrRatio)  
            {  
                goodMatchesList.addLast(m1);  

            }  
        }  

        if (goodMatchesList.size() >= 7)  
        {  
            max=goodMatchesList.size();
            
            List<KeyPoint> objKeypointlist = objectKeyPoints.toList();  
            List<KeyPoint> scnKeypointlist = sceneKeyPoints.toList();  

            LinkedList<Point> objectPoints = new LinkedList<>();  
            LinkedList<Point> scenePoints = new LinkedList<>();  

            for (int i = 0; i < goodMatchesList.size(); i++)  
            {  
                objectPoints.addLast(objKeypointlist.get(goodMatchesList.get(i).queryIdx).pt);  
                scenePoints.addLast(scnKeypointlist.get(goodMatchesList.get(i).trainIdx).pt);  
            }  

            MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();  
            objMatOfPoint2f.fromList(objectPoints);  
            MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();  
            scnMatOfPoint2f.fromList(scenePoints);  

            Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);  

            Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);  
            Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);  

            obj_corners.put(0, 0, new double[] { 0, 0 });  
            obj_corners.put(1, 0, new double[] { objectImage.cols(), 0 });  
            obj_corners.put(2, 0, new double[] { objectImage.cols(), objectImage.rows() });  
            obj_corners.put(3, 0, new double[] { 0, objectImage.rows() });  

            //System.out.println("Transforming object corners to scene corners...");  
            Core.perspectiveTransform(obj_corners, scene_corners, homography);  

            Mat img = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);  

            Core.line(img, new Point(scene_corners.get(0, 0)), new Point(scene_corners.get(1, 0)), new Scalar(0, 255, 0), 4);  
            Core.line(img, new Point(scene_corners.get(1, 0)), new Point(scene_corners.get(2, 0)), new Scalar(0, 255, 0), 4);  
            Core.line(img, new Point(scene_corners.get(2, 0)), new Point(scene_corners.get(3, 0)), new Scalar(0, 255, 0), 4);  
            Core.line(img, new Point(scene_corners.get(3, 0)), new Point(scene_corners.get(0, 0)), new Scalar(0, 255, 0), 4);  

            //java.lang.System.out.println("Dibujando imagen de coincidencias");
            MatOfDMatch goodMatches = new MatOfDMatch();  
            goodMatches.fromList(goodMatchesList);  

            Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput, matchestColor, newKeypointColor, new MatOfByte(), 2);  
            
            String n_outputImage = "../pre/outputImage_sift.jpg";
            String n_matchoutput = "../pre/matchoutput_sift.jpg";
            String n_img = "../pre/sift.jpg";
            Highgui.imwrite(n_outputImage, outputImage);
            Highgui.imwrite(n_matchoutput, matchoutput);  
            Highgui.imwrite(n_img, img);  
            java.lang.System.out.println(goodMatches.size().height);
            double result =goodMatches.size().height;//*100/matches.size();
            int score = 0;
            if(result > 27){
                score = 100;
            }else if(result <= 27 && result > 22){
                score = 85;
            }else if(result <= 22 && result > 17){
                score = 50;
            }else if(result <= 17 && result > 11){
                score = 25;
            }else{
                score = 0;
            }
            java.lang.System.out.println("Score: " + score);
            return score;
        }  
        else  
        {  
            java.lang.System.out.println("Objeto no encontrado");
            return 0;
        }
        //System.out.println("Terminando SIFT");  
    }
        
    public static void preprocessSignatures(String routeRNV, String routeAdherent) throws IOException {
        
        BufferedImage originalImage1 = ImageIO.read(new File(routeRNV));
        int type = originalImage1.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage1.getType();
        
        BufferedImage originalImage2 = ImageIO.read(new File(routeAdherent));
        int type2 = originalImage2.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage2.getType();

        int index1 =routeRNV.length()-4;
        int index2 =routeAdherent.length()-4;
        String routeRNV2= routeRNV.substring(0,index1) + routeRNV.substring(index1, index1+4);
        String routeAdherent2= routeAdherent.substring(0,index2) + routeAdherent.substring(index2, index2+4);
        //BufferedImage resizeImagePng = resizeImage(originalImage1, type);
        BufferedImage resizeImagePng2 = resizeImage(originalImage2, type2);
        //ImageIO.write(resizeImagePng, "png", new File(routeRNV2));
        ImageIO.write(resizeImagePng2, "png", new File(routeAdherent2));
        
        ImagePlus imgPlusAdherent = new ImagePlus(routeAdherent2);
        
        IJ.run(imgPlusAdherent,"Make Binary","");
        imgPlusAdherent.setRoi(0,15,imgPlusAdherent.getWidth(),imgPlusAdherent.getHeight());
        IJ.run(imgPlusAdherent, "Crop", "");
        imgPlusAdherent.setRoi(0,0,imgPlusAdherent.getWidth(),imgPlusAdherent.getHeight()-15);
        IJ.run(imgPlusAdherent, "Crop", "");
        FileSaver fs = new FileSaver(imgPlusAdherent);
        fs.saveAsJpeg("../pre/sig.jpg");
   }
    
   private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
		
	return resizedImage;
    }
   
   public static void main(String[] args) throws IOException{
        File dll;
        if(java.lang.System.getProperty("os.name").equals("Linux")){
           dll = new File("lib/libopencv_java2412.so");
        }else{
            dll = new File("lib/opencv_java2412.dll");
        }
        
        java.lang.System.load(dll.getAbsolutePath());
            
        String n_img1= "../rnv/gfi024.jpg";
        String n_img2  = "../cortes/24/part.G.original3.1/firma.jpg";

        SignatureLib.preprocessSignatures(n_img1, n_img2);

        ImagePlus img1 = new ImagePlus(n_img1);
        img1.show();
        ImagePlus img2 = new ImagePlus("../pre/sig.jpg");
        img2.show();

        double resultado = validarFirmas(n_img1, "../pre/sig.jpg");
        java.lang.System.out.println("Resultado: " + resultado);
        
   }
}

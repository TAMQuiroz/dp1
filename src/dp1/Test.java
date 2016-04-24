package dp1;

//PREPROCESAMIENTO
import com.asprise.ocr.Ocr;
import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;

//TEMPLATE MATCHING

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.*;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;


public class Test {
    static int train_samples = 1;  
    static int classes = 10;  
    static int sizex = 20;  
    static int sizey = 30;  
    static int ImageSize = sizex * sizey;  
    static String pathToImages = "test\\ocr\\opencv\\";  

    public static void preprocesamiento(String route, String route_base , String nimg, String extension){
        
        String url = route + nimg + extension;
        System.out.print("Procesando: " + url + " | ");
        ImagePlus imgPlus = new ImagePlus(url);
        
        System.out.print("Afilando imagen | ");
        IJ.run(imgPlus, "Sharpen", "");
        
        System.out.print("Sacando Mediana | ");
        IJ.run(imgPlus, "Median...", "radius=1.5");
        
        System.out.print("Filtrando imagen binaria | ");
        IJ.run(imgPlus,"Make Binary","");
        
        System.out.print("Esqueletonizando | ");
        IJ.run(imgPlus,"Skeletonize","");
        FileSaver fs = new FileSaver(imgPlus);
        
        System.out.println("Guardando imagen");
        String n_out = route_base + "\\pre\\" + nimg + extension;
        fs.saveAsJpeg(n_out);
              
        //System.out.println("Finalizando preprocesamiento");
    }
    
    public static void gabor(String route, String route_base , String nimg, String extension){
        String url1 = route + nimg + extension;
        System.out.print("Abriendo imagen " + url1);
        Mat myImg = Highgui.imread(url1, 0);
        myImg.convertTo(myImg, CvType.CV_32F);

        // prepare the output matrix for filters
        Mat gabor1 = new Mat (myImg.height(), myImg.width(), CvType.CV_32F );
        Mat gabor2 = new Mat (myImg.height(), myImg.width(), CvType.CV_32F );
        Mat gabor3 = new Mat (myImg.height(), myImg.width(), CvType.CV_32F );
        Mat gabor4 = new Mat (myImg.height(), myImg.width(), CvType.CV_32F );
        Mat enhanced = new Mat (myImg.height(), myImg.width(), CvType.CV_32F );
        
        //predefine parameters for Gabor kernel 
        Size kSize = new Size(31,31);

        double theta1 = 0;
        double theta2 = 45;
        double theta3 = 90;
        double theta4 = 135;

        double lambda = 12;
        double sigma = 8;  
        double gamma = 0.25;
        double psi =  0;

        // the filters kernel
        Mat kernel1 = Imgproc.getGaborKernel(kSize, sigma, theta1, lambda, gamma, psi, CvType.CV_32F);
        Mat kernel2 = Imgproc.getGaborKernel(kSize, sigma, theta2, lambda, gamma, psi, CvType.CV_32F);
        Mat kernel3 = Imgproc.getGaborKernel(kSize, sigma, theta3, lambda, gamma, psi, CvType.CV_32F);
        Mat kernel4 = Imgproc.getGaborKernel(kSize, sigma, theta4, lambda, gamma, psi, CvType.CV_32F);

        // apply filters on my image. The result is stored in gabor1...4
        Imgproc.filter2D(myImg, gabor1, -1, kernel1);
        Highgui.imwrite("test\\pre\\gabor1.tif", gabor1);
        Imgproc.filter2D(myImg, gabor2, -1, kernel2);
        Highgui.imwrite("test\\pre\\gabor2.tif", gabor2);
        Imgproc.filter2D(myImg, gabor3, -1, kernel3);
        Highgui.imwrite("test\\pre\\gabor3.tif", gabor3);        
        Imgproc.filter2D(myImg, gabor4, -1, kernel4);
        Highgui.imwrite("test\\pre\\gabor4.tif", gabor4);
        
        Core.addWeighted(enhanced , 0, gabor1, 1, 0, enhanced );
        Core.addWeighted(enhanced , 1, gabor2, 1, 0, enhanced );
        Core.addWeighted(enhanced , 1, gabor3, 1, 0, enhanced );
        Core.addWeighted(enhanced , 1, gabor4, 1, 0, enhanced );
        
        String n_out = route_base + "\\pre\\" + nimg + "_gabor" + extension;
        Highgui.imwrite(n_out, enhanced);
    }
    
    public static Vector<String> cutDigits(String route, String nimg, String extension, ImagePlus imp){
        Vector<String> imgs = new Vector<String>();
        ImageProcessor ip = imp.getProcessor();
        ImageProcessor clone;
        String n_out = new String();
        //int cropX = ip.getWidth()/8;
        int cropX = 125;
        int cropY = ip.getHeight();
        for (int i = 0; i < 8; i++){
            try {
                clone = ip.duplicate();
                clone.setRoi(i * cropX, 0, cropX, cropY);
                clone = clone.crop();
                n_out = route + "pre\\" + nimg + (i+1) + "_out.jpg";
                System.out.println("Saving: " + n_out);
                BufferedImage croppedImage = clone.getBufferedImage();
                ImageIO.write(croppedImage, "jpg", new File(n_out));
            } catch (IOException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgs.add(i, n_out);
        }
        
        return imgs;
    }
    
    public static Vector<String> preprocesamiento_ocr(String route, String nimg, String extension){
        System.out.println("Antes de preprocesamiento");
        String url = route + nimg + extension;
        System.out.println("Reading from: " + url);
        ImagePlus imgPlus = new ImagePlus(url);
        System.out.println("Preprocessing: " + url);
        
        ImageProcessor ip = imgPlus.getProcessor();
        ip.setInterpolationMethod(ImageProcessor.BILINEAR);
        ip = ip.resize(ip.getWidth() * 5, ip.getHeight() * 5);
        BufferedImage img = ip.getBufferedImage();

       
        imgPlus = new ImagePlus("croppedImage", img);
        
        //imgPlus.show();
        //Macro.SetOption("BlackBackground", false);
        IJ.run(imgPlus, "Make Binary", "");
        IJ.run(imgPlus, "Median...", "radius=5");
        System.out.println("Cutting: " + url);
        Vector<String> imgs;
        imgs = cutDigits(route, nimg, extension, imgPlus);
        System.out.println("Despues de preprocesamiento");

        return imgs;
    }
    
    public static void ocr(ITesseract instance, Vector<String> imgs){
        String final_result = new String();
        for (int i = 0; i < imgs.size(); i++){
            String url = imgs.get(i);
            //System.out.println("Reading from: " + url);
            File imageFile = new File(url);
            
            try {
                String result = instance.doOCR(imageFile);
                System.out.print(result.trim() + " | ");
                final_result += result.trim();
                //System.out.print(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(final_result);
    }
    
    public static void ocr_exp(ITesseract instance, String route_ocr_exp){
        String final_result = new String();
        for (int i = 0; i < 10; i++){
            final_result = "";
            System.out.print("Test: "+i + " | ");
            for (int j = 1; j < 6; j++){
                String url = route_ocr_exp + i + "\\" + i + "_" + j + ".tif";
                //System.out.println("Reading from: " + url);
                
                File imageFile = new File(url);

                try {
                    String result = instance.doOCR(imageFile);
                    //System.out.print(result.trim() + " | ");
                    final_result += result.trim();
                    //System.out.print(result);
                } catch (TesseractException e) {
                    System.err.println(e.getMessage());
                } 
            }
            System.out.println(final_result);
        }
    }
    
    public static void ocr_exp_let(ITesseract instance, String route_ocr_exp){
        String final_result = new String();
        for (int i = 0; i < 10; i++){
            final_result = "";
            System.out.print("Test: "+i + " | ");
            
            String url = route_ocr_exp + "Words\\" + i + ".png";
            //System.out.println("Reading from: " + url);

            File imageFile = new File(url);

            try {
                final_result = instance.doOCR(imageFile).trim();
                //System.out.print(result.trim() + " | ");
                
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            } 
            
            System.out.println(final_result);
        }
    }
    
    public static void ocr_asprise(Ocr ocr, Vector<String> imgs){
        String final_result = new String();
        for (int i = 0; i < imgs.size(); i++){
            String url = imgs.get(i);
            //System.out.println("Reading from: " + url);
            File imageFile = new File(url);
            //System.out.print(url);
            String result = ocr.recognize(new File[] {imageFile}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT, "PROP_LIMIT_TO_CHARSET=0123456789");
            System.out.print(result + " | ");
            // ocr more images here ...
            final_result += result.trim();
            //System.out.print(result);

        }
        System.out.println(final_result);
    }
    
    public static void ocr_asprise_exp(Ocr instance, String route_ocr_exp){
        String final_result = new String();
        for (int i = 0; i < 10; i++){
            final_result = "";
            System.out.print("Test: "+i + " | ");
            for (int j = 1; j < 6; j++){
                String url = route_ocr_exp + i + "\\" + i + "_" + j + ".tif";
                //System.out.println("Reading from: " + url);
                
                File imageFile = new File(url);

                String result = instance.recognize(new File[] {imageFile}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT, "PROP_LIMIT_TO_CHARSET=0123456789");
                //System.out.print(result.trim() + " | ");
                final_result += result.trim();
                //System.out.print(result);

            }
            System.out.println(final_result);
        }
    }
    
        public static void ocr_asprise_exp_let(Ocr instance, String route_ocr_exp){
        String final_result = new String();
        for (int i = 0; i < 10; i++){
            final_result = "";
            System.out.print("Test: "+i + " | ");
            
            String url = route_ocr_exp + "Words\\" + i + ".png";
            //System.out.println("Reading from: " + url);

            File imageFile = new File(url);

            final_result  = instance.recognize(new File[] {imageFile}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT, "PROP_LIMIT_TO_CHARSET=abcdefghijklmnopqrstuvwxyz");
            //System.out.print(result.trim() + " | ");

            System.out.println(final_result);
        }
    }
    
    public static void orb(String route, String route_out, String n_img1, String n_img2, String extension){
        //System.out.println("Iniciando ORB");
        String url1 = route + n_img1 + extension;
        String url2 = route + n_img2 + extension;
        
        System.out.print("Abriendo imagenes | ");
        Mat img1 = Highgui.imread(url1, 0); //imagen query
        Mat img2 = Highgui.imread(url2, 0); //imagen base
        
        //iniciar detector ORB
        FeatureDetector orb = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        DescriptorMatcher bf = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();
        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        
        //encontrar keypoints y descriptores con orb
        System.out.print("Encontrar keypoints con ORB | ");
        orb.detect(img1, keypoints1);
        descriptor.compute(img1, keypoints1, descriptors1);
        
        orb.detect(img2, keypoints2);
        descriptor.compute(img2, keypoints2, descriptors2);
        
        //crear un matcher de fuerza bruta
        System.out.print("Encontrar matches brutos | ");
        MatOfDMatch matches = new MatOfDMatch();
        
        //matchear descriptores
        bf.match(descriptors1, descriptors2, matches);
        
        // Linking
        Scalar RED = new Scalar(255,0,0);
        Scalar GREEN = new Scalar(0,255,0);
        List<DMatch> matchesList = matches.toList();
        Double max_dist = 0.0;
        Double min_dist = 45.0;
        
        System.out.print(" Encontrar matches buenos | ");
        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
        for(int i = 0;i < matchesList.size(); i++){
            if (matchesList.get(i).distance <= min_dist)
                good_matches.addLast(matchesList.get(i));
        }

        // Printing
        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(good_matches);
        
        Mat outputImg = new Mat();
        MatOfByte drawnMatches = new MatOfByte();
        Features2d.drawMatches(img1, keypoints1, img2, keypoints2, goodMatches, outputImg, GREEN, RED, drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
        System.out.println("Guardar imagen");
        String n_out = route_out + "\\results\\" + n_img1 + "_orb" + extension;
        Highgui.imwrite(n_out, outputImg);
        
        System.out.println("Resultados: " + matches.size() + " " + goodMatches.size());  
        
        //System.out.println("Final de orb");
    }
    
    public static void sift(String route, String route_out, String n_img1, String n_img2, String extension){
  
        String bookObject = route + n_img1 + extension;
        String bookScene = route + n_img2 + extension;
        //String bookObject = "test//1.png";  
        //String bookScene = "test//4.png";  

        //System.out.println("Iniciando SIFT");
        System.out.print("Abriendo imagenes | ");
        Mat objectImage = Highgui.imread(bookObject, Highgui.CV_LOAD_IMAGE_COLOR);  
        Mat sceneImage = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);  

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();  
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);  
        System.out.print("Encontrar keypoints con SIFT | ");  
        featureDetector.detect(objectImage, objectKeyPoints);  
        KeyPoint[] keypoints = objectKeyPoints.toArray();  

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();  
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);  
        System.out.print("Computando descriptores | ");  
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);  

        // Create the matrix for output image.   
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar newKeypointColor = new Scalar(255, 0, 0);  

        System.out.print("Dibujando keypoints en imagen base | ");  
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);  

        // Match object image with the scene image  
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();  
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();  
        System.out.print("Detectando keypoints en imagen base | ");
        featureDetector.detect(sceneImage, sceneKeyPoints);  
        System.out.print("Computando descriptores en imagen base | ");
        descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);  

        Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar matchestColor = new Scalar(0, 255, 0);  

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();  
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);  
        System.out.print("Encontrando matches entre imagenes | ");  
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);  

        System.out.println("Calculando buenos matches");
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();  

        float nndrRatio = 0.8f;  

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
            System.out.println("Match enontrado!!! Matches: "+goodMatchesList.size());  

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

            System.out.println("Dibujando imagen de coincidencias");
            MatOfDMatch goodMatches = new MatOfDMatch();  
            goodMatches.fromList(goodMatchesList);  

            Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput, matchestColor, newKeypointColor, new MatOfByte(), 2);  

            String n_outputImage = route_out + "\\results\\" + n_img1 + "_outputImage_sift" + extension;
            String n_matchoutput = route_out + "\\results\\" + n_img1 + "_matchoutput_sift" + extension;
            String n_img = route_out + "\\results\\" + n_img1 + "_sift" + extension;
            Highgui.imwrite(n_outputImage, outputImage);
            Highgui.imwrite(n_matchoutput, matchoutput);  
            Highgui.imwrite(n_img, img);  
        }  
        else  
        {  
            System.out.println("Objeto no encontrado");  
        }  

        //System.out.println("Terminando SIFT");  
    }
    
    public static void surf(String route, String route_out, String n_img1, String n_img2, String extension){
  
        String bookObject = route + n_img1 + extension;
        String bookScene = route + n_img2 + extension;
        //String bookObject = "test//1.png";  
        //String bookScene = "test//4.png";  

        //System.out.println("Started....");  
        System.out.print("Abriendo imagenes | ");
        Mat objectImage = Highgui.imread(bookObject, Highgui.CV_LOAD_IMAGE_COLOR);  
        Mat sceneImage = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);  

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();  
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SURF);  
        System.out.print("Encontrar keypoints con SURF | ");
        featureDetector.detect(objectImage, objectKeyPoints);  
        KeyPoint[] keypoints = objectKeyPoints.toArray();  
        //System.out.println(keypoints);  

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();  
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);  
        System.out.print("Computando descriptores | ");
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);  

        // Create the matrix for output image.   
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar newKeypointColor = new Scalar(255, 0, 0);  

        System.out.print("Dibujando keypoints en imagen base | "); 
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);  

        // Match object image with the scene image  
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();  
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();  
        System.out.print("Detectando keypoints en imagen base | ");
        featureDetector.detect(sceneImage, sceneKeyPoints);  
        System.out.print("Computando descriptores en imagen base | ");
        descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);  

        Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar matchestColor = new Scalar(0, 255, 0);  

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();  
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);  
        System.out.print("Encontrando matches entre imagenes | ");  
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);  

        System.out.println("Calculando buenos matches");
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
            System.out.println("Match enontrado!!! Matches: "+goodMatchesList.size());  

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

            System.out.println("Dibujando imagen de coincidencias");
            MatOfDMatch goodMatches = new MatOfDMatch();  
            goodMatches.fromList(goodMatchesList);  

            Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput, matchestColor, newKeypointColor, new MatOfByte(), 2);  

            String n_outputImage = route_out + "\\results\\" + n_img1 + "_outputImage_surf" + extension;
            String n_matchoutput = route_out + "\\results\\" + n_img1 + "_matchoutput_surf" + extension;
            String n_img = route_out + "\\results\\" + n_img1 + "_surf" + extension;
            Highgui.imwrite(n_outputImage, outputImage);
            Highgui.imwrite(n_matchoutput, matchoutput);  
            Highgui.imwrite(n_img, img);  
        }  
        else  
        {  
            System.out.println("Objeto no encontrado");
        }  

        //System.out.println("Ended....");  
    }

    public static void main(String[] args) {
        
        File dll = new File("lib\\opencv_java2412.dll");
        System.load(dll.getAbsolutePath());
        
        String n_img_text = "1_cut";
        String route_base = "test\\";
        String route_huellas = route_base + "huellas\\";
        String route_pre = route_base + "pre\\";
        String route_ocr = route_base + "ocr\\";
        String route_ocr_exp = route_ocr + "exp\\";
        String extension = ".png";
        String n_img1  = "101_1";
        String n_img2  = "105_1";
        
        //OCR TESSERACT
        Vector<String> imgs;
        //imgs = preprocesamiento_ocr(route_ocr, n_img_text, extension);
        ITesseract instance_num = new Tesseract1(); // JNA Direct Mapping
        ITesseract instance_let = new Tesseract1(); // JNA Direct Mapping
        instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        instance_let.setTessVariable("tessedit_char_whitelist", "abcdefghijklmnopqrstuvwxyz");
        //ocr(instance, imgs);
        //ocr_exp(instance_num, route_ocr_exp);
        ocr_exp_let(instance_let, route_ocr_exp);
        
        //OCR ASPRISE
        
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FASTEST);
        //ocr_asprise(ocr,imgs);
        //ocr_asprise_exp(ocr,route_ocr_exp);
        ocr_asprise_exp_let(ocr,route_ocr_exp);
        ocr.stopEngine();
        
        //PREPROCESAMIENTO IMAGEJ + ORB - SIFT - SURF - HARRIS
        /*
        System.out.println("***INICIANDO PREPROCESAMIENTO***");
        gabor(route_huellas, route_base, n_img1, extension);
        preprocesamiento(route_huellas, route_base, n_img1, extension);
        preprocesamiento(route_huellas, route_base, n_img2, extension);
        System.out.println("***FINALIZANDO PREPROCESAMIENTO***");
        System.out.println("***INICIANDO ORB***");
        orb(route_pre, route_base, n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO ORB***");
        System.out.println("***INICIANDO SURF***");
        surf(route_pre, route_base, n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO SURF***");
        */
        //System.out.println("***INICIANDO SIFT***");
        //sift(route_pre, route_base, n_img1, n_img2, extension);
        //System.out.println("***FINALIZANDO SIFT***");
       
    }  
    
}


package dp1;

//PREPROCESAMIENTO
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

    public static void preprocesamiento(String route, String nimg, String extension){
        
        //System.out.println("Iniciando preprocesamiento de " + nimg + extension);
        String url = route + nimg + extension;
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
        String n_out = route + "\\pre\\" + nimg + extension;
        fs.saveAsJpeg(n_out);
              
        //System.out.println("Finalizando preprocesamiento");
    }
    
    public static Vector<String> cutDigits(String route, String nimg, String extension, ImagePlus imp){
        Vector<String> imgs = new Vector<String>();
        ImageProcessor ip = imp.getProcessor();
        ImageProcessor clone;
        String n_out = new String();
        int cropX = 25;
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
        IJ.run(imgPlus, "Subtract Background...", "rolling=50 light");
        //IJ.run(imgPlus, "Make Binary", "");
        //IJ.run(imgPlus, "Smooth", "");
        //IJ.run(imgPlus, "Sharpen", "");
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
                final_result += result.trim();
                //System.out.print(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(final_result);
    }
    
    public static void orb(String route, String n_img1, String n_img2, String extension){
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
        String n_out = route + "\\results\\" + n_img1 + "_orb" + extension;
        Highgui.imwrite(n_out, outputImg);
        
        System.out.println("Resultados: " + matches.size() + " " + goodMatches.size());  
        
        //System.out.println("Final de orb");
    }
    
    public static void sift(String route, String n_img1, String n_img2, String extension){
  
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

            String n_outputImage = route + "\\results\\" + n_img1 + "_outputImage_sift" + extension;
            String n_matchoutput = route + "\\results\\" + n_img1 + "_matchoutput_sift" + extension;
            String n_img = route + "\\results\\" + n_img1 + "_sift" + extension;
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
    
    public static void surf(String route, String n_img1, String n_img2, String extension){
  
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

            String n_outputImage = route + "\\results\\" + n_img1 + "_outputImage_surf" + extension;
            String n_matchoutput = route + "\\results\\" + n_img1 + "_matchoutput_surf" + extension;
            String n_img = route + "\\results\\" + n_img1 + "_surf" + extension;
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
    
    public static void harris(String route, String n_img1, String n_img2, String extension){
        //System.out.println("Inicio de harris");
        String url1 = route + n_img1 + extension;
        String url2 = route + n_img2 + extension;
        
        System.out.print("Abriendo imagenes | ");
        Mat img1 = Highgui.imread(url1, 0); //imagen query
        Mat img2 = Highgui.imread(url2, 0); //imagen base
        
        //Binarizar imagen via tresholding
        Mat input_binary = new Mat();
        Mat input_binary2 = new Mat();
        Imgproc.threshold(img1, input_binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        Imgproc.threshold(img2, input_binary2, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
 
        
        //Detecta minucias usando la deteccion de esquinas Harris
        System.out.print("Hallar esquinas de Harris | ");
        Mat harris_corners;
        Mat harris_normalised = new Mat();
        harris_corners = Mat.zeros(img1.size(),CvType.CV_32FC1);
        Imgproc.cornerHarris(img1, harris_corners, 2, 3, 0.04);
        Core.normalize(harris_corners, harris_normalised, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1);
        
        Mat harris_corners2;
        Mat harris_normalised2 = new Mat();
        harris_corners2 = Mat.zeros(img2.size(),CvType.CV_32FC1);
        Imgproc.cornerHarris(img2, harris_corners2, 2, 3, 0.04);
        Core.normalize(harris_corners2, harris_normalised2, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1);
        
        //Selecciona las esquinas mas fuertes
        System.out.print("Seleccionar esquinas mas coincidentes | ");
        int threshold_harris = 125;
        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        
        //Hacer un clon de color para ver mejor
        Mat rescaled = new Mat();
        Mat rescaled2 = new Mat();
        Core.convertScaleAbs(harris_normalised, rescaled);
        Core.convertScaleAbs(harris_normalised2, rescaled2);
        
        List<Mat> harris_c = new ArrayList<Mat>();
        List<Mat> harris_c2 = new ArrayList<Mat>();
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        
        List<Mat> in = new ArrayList<Mat>();
        List<Mat> in2 = new ArrayList<Mat>();
        
        in.add(rescaled);
        in.add(rescaled);
        in.add(rescaled);
        
        in2.add(rescaled2);
        in2.add(rescaled2);
        in2.add(rescaled2);
        
        MatOfInt from_to = new MatOfInt(0,0, 1,1, 2,2);
        MatOfInt from_to2 = new MatOfInt(0,0, 1,1, 2,2);
        Core.mixChannels(in, harris_c, from_to);
        Core.mixChannels(in2, harris_c2, from_to2);
        
        for (int x = 0; x < harris_normalised.cols(); x++){
            for (int y = 0; y < harris_normalised.rows(); y++){
                if((int)harris_normalised.get(y, x)[0] > threshold_harris){
                    //Dibuja o guarda los keypoints
                    keypoints.fromArray(new KeyPoint(x,y,1));
                }
            }
        }
        
        for (int x = 0; x < harris_normalised2.cols(); x++){
            for (int y = 0; y < harris_normalised2.rows(); y++){
                if((int)harris_normalised2.get(y, x)[0] > threshold_harris){
                    //Dibuja o guarda los keypoints
                    keypoints2.fromArray(new KeyPoint(x,y,1));
                }
            }
        }
        
        //Calcular descriptor ORB basado en keypoints
        System.out.println("Calcular coincidencias");
        DescriptorExtractor descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        Mat descriptors = new Mat();
        Mat descriptors2 = new Mat();
        descriptor.compute(img1, keypoints, descriptors);
        descriptor.compute(img2, keypoints2, descriptors2);
        
        DescriptorMatcher bf = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();
        bf.match(descriptors, descriptors2, matches);
        
        float score = 0;
        List<DMatch> matchesList = matches.toList();
        
        for (int i = 0; i < matchesList.size(); i++){
            DMatch current_match = matchesList.get(i);
            score = score + current_match.distance;
            
        }
        
        System.out.println("Current matching score = " + score);
        
    }
    
    public static void main(String[] args) {
        
        String n_img_text = "1_cut";
        File dll = new File("lib\\opencv_java2412.dll");
        System.load(dll.getAbsolutePath());
        
        String route = "test\\";
        String route_ocr = "test\\ocr\\";
        String extension = ".png";
        String n_img1  = "1";
        String n_img2  = "3";
        
        //OCR
        /*
        Vector<String> imgs;
        imgs = preprocesamiento_ocr(route_ocr, n_img_text, extension);
        ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setTessVariable("tessedit_char_whitelist", "0123456789");
        ocr(instance, imgs);
        */
        
        //PREPROCESAMIENTO IMAGEJ + ORB - SIFT - SURF - HARRIS
        System.out.println("***INICIANDO PREPROCESAMIENTO***");
        //preprocesamiento(route, n_img1, extension);
        //preprocesamiento(route, n_img2, extension);
        System.out.println("***FINALIZANDO PREPROCESAMIENTO***");
        System.out.println("***INICIANDO ORB***");
        orb(route, n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO ORB***");
        System.out.println("***INICIANDO SIFT***");
        sift(route,n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO SIFT***");
        System.out.println("***INICIANDO SURF***");
        surf(route,n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO SURF***");
        /*
        System.out.println("***INICIANDO HARRIS CORNER***");
        harris(route, n_img1, n_img2, extension);
        System.out.println("***FINALIZANDO HARRIS CORNER***");
        */        
    }  
    
}


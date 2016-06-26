package View;

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

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import net.sourceforge.tess4j.*;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

public class FingerprintLib {
    static int train_samples = 1;  
    static int classes = 10;  
    static int sizex = 20;  
    static int sizey = 30;
    static int scalex = 27;
    static int scaley = 16;
    static int ImageSize = sizex * sizey;
    static JTextArea console;
    static JProgressBar status;


    public static String preprocesamiento(String nimg, int type){
        String n_out;
        java.lang.System.out.print("Procesando: " + nimg + " | ");
        ImagePlus imgPlus = new ImagePlus(nimg);
        ImageProcessor img = imgPlus.getProcessor();
        img = img.resize(600);
        imgPlus = new ImagePlus("resized",img);
        java.lang.System.out.print("Afilando imagen | ");
        IJ.run(imgPlus, "Sharpen", "");
        
        java.lang.System.out.print("Sacando Mediana | ");
        IJ.run(imgPlus, "Median...", "radius=1.5");
        
        java.lang.System.out.print("Filtrando imagen binaria | ");
        IJ.run(imgPlus,"Make Binary","");
        
        java.lang.System.out.print("Esqueletonizando\n");
        IJ.run(imgPlus,"Skeletonize","");
        
        FileSaver fs = new FileSaver(imgPlus);
        //java.lang.System.out.println("Guardando imagen");
        if(type == 0){
            n_out = "../pre/source.jpg";
        }else{
            n_out = "../pre/test.jpg";
        }
        fs.saveAsJpeg(n_out);
        
        return n_out;
        //System.out.println("Finalizando preprocesamiento");
    }
         
    public static void orb(String route, String route_out, String n_img1, String n_img2, String extension){
        long ini = java.lang.System.currentTimeMillis();
        //System.out.println("Iniciando ORB");
        String url1 = route + n_img1 + extension;
        String url2 = route + n_img2 + extension;
        
        java.lang.System.out.print("Abriendo imagenes | ");
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
        java.lang.System.out.print("Encontrar keypoints con ORB | ");
        orb.detect(img1, keypoints1);
        descriptor.compute(img1, keypoints1, descriptors1);
        
        orb.detect(img2, keypoints2);
        descriptor.compute(img2, keypoints2, descriptors2);
        
        //crear un matcher de fuerza bruta
        java.lang.System.out.print("Encontrar matches brutos | ");
        MatOfDMatch matches = new MatOfDMatch();
        
        //matchear descriptores
        bf.match(descriptors1, descriptors2, matches);
        
        // Linking
        Scalar RED = new Scalar(255,0,0);
        Scalar GREEN = new Scalar(0,255,0);
        List<DMatch> matchesList = matches.toList();
        Double max_dist = 0.0;
        Double min_dist = 45.0;
        
        java.lang.System.out.print(" Encontrar matches buenos | ");
        LinkedList<DMatch> good_matches = new LinkedList<>();
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
        java.lang.System.out.println("Guardar imagen");
        String n_out = route_out + "\\results\\" + n_img1 + "_orb" + extension;
        Highgui.imwrite(n_out, outputImg);
        
        java.lang.System.out.println("Resultados: " + matches.size() + " " + goodMatches.size());  
        long total = (java.lang.System.currentTimeMillis() - ini);
        java.lang.System.out.println("Tiempo tomado: " + total);
        //System.out.println("Final de orb");
    }
       
    public static double surf(String n_img1, String n_img2){
        String bookObject = n_img1;
        String bookScene = n_img2;
        //String bookObject = "test//1.png";  
        //String bookScene = "test//4.png";  
        

        //System.out.println("Started....");  
        java.lang.System.out.print("Abriendo imagenes | ");
        Mat objectImage = Highgui.imread(bookObject, Highgui.CV_LOAD_IMAGE_COLOR);  
        Mat sceneImage = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);  

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();  
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SURF);  
        java.lang.System.out.print("Encontrar keypoints con SURF | ");
        featureDetector.detect(objectImage, objectKeyPoints);  
        //KeyPoint[] keypoints = objectKeyPoints.toArray();  
        //System.out.println(keypoints);  

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();  
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);  
        java.lang.System.out.print("Computando descriptores | ");
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);  

        // Create the matrix for output image.   
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar newKeypointColor = new Scalar(255, 0, 0);  

        java.lang.System.out.print("Dibujando keypoints en imagen base | "); 
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);  

        // Match object image with the scene image  
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();  
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();  
        java.lang.System.out.print("Detectando keypoints en imagen base | ");
        featureDetector.detect(sceneImage, sceneKeyPoints);  
        java.lang.System.out.print("Computando descriptores en imagen base | ");
        descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);  

        Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);  
        Scalar matchestColor = new Scalar(0, 255, 0);  

        List<MatOfDMatch> matches = new LinkedList<>();  
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);  
        java.lang.System.out.print("Encontrando matches entre imagenes | ");  
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);  

        java.lang.System.out.println("Calculando buenos matches");
        LinkedList<DMatch> goodMatchesList = new LinkedList<>();  

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
            //java.lang.System.out.println("Match enontrado!!! Matches: "+goodMatchesList.size());  

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

            //java.lang.System.out.println("Transforming object corners to scene corners...");  
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

            String n_outputImage = "../pre/outputImage_surf.jpg";
            String n_matchoutput = "../pre/matchoutput_surf.jpg";
            String n_img = "../pre/surf.jpg";
            Highgui.imwrite(n_outputImage, outputImage);
            Highgui.imwrite(n_matchoutput, matchoutput);  
            Highgui.imwrite(n_img, img);  
            
            java.lang.System.out.println("Matches: " + goodMatches.size().height);
            double result = goodMatches.size().height;
            if(result > 120){
                return 100;
            }else if(result <= 120 && result > 100){
                return 85;
            }else if(result <= 100 && result > 80){
                return 50;
            }else if(result <= 80 && result > 50){
                return 25;
            }else{
                return 0;
            }
        }  
        else  
        {  
            java.lang.System.out.println("Objeto no encontrado");
            return 0;
        }  
    }
    
    public static double huellas(String rnvFingerprintSource, String testFingerprintSource){
        double resultado = 0;
        
        String rnvFingerprint = preprocesamiento(rnvFingerprintSource, 0);
        String testFingerprint = preprocesamiento(testFingerprintSource, 1);
        resultado = surf(rnvFingerprint, testFingerprint);
        
        return resultado;
    }

    public static void main(String[] args) throws IOException{
        
        File dll = new File("lib\\opencv_java2412.dll");
        java.lang.System.load(dll.getAbsolutePath());
        
        String n_img1  = "../cortes/";
        String n_img2  = "../rnv/gfi001.jpg";
        
        //PREPROCESAMIENTO IMAGEJ + ORB - SURF
        /*
        java.lang.System.out.println("***INICIANDO ORB***");
        orb(route_pre, route_base, n_img1, n_img2, extension_huellas);
        java.lang.System.out.println("***FINALIZANDO ORB***");
        */
        /*
        double resultado = huellas(n_img1, n_img2);
        java.lang.System.out.println("Resultado: " + resultado);
        */
        
        String padronesPathString = "../padrones";
        String cortesPathString = "../cortes";        
        File padronesFile = new File(padronesPathString);
        File cortesFile = new File(cortesPathString);
        if(padronesFile.exists() && padronesFile.isDirectory()) { 
            java.lang.System.out.println("Ya Existe");
        }else{
            java.lang.System.out.println("Creando");
            padronesFile.mkdir();
        }
        
        if(cortesFile.exists() && cortesFile.isDirectory()) { 
            java.lang.System.out.println("Ya Existe");
        }else{
            java.lang.System.out.println("Creando");
            cortesFile.mkdir();
        }
        
    }  
}


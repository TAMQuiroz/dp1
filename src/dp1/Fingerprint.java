package dp1;

//PREPROCESAMIENTO
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Line;
import ij.io.FileSaver;
import ij.plugin.Duplicator;
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

import java.lang.System.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.opencv.core.Core.MinMaxLocResult;
public class Fingerprint {
    static int train_samples = 1;  
    static int classes = 10;  
    static int sizex = 20;  
    static int sizey = 30;
    static int scalex = 27;
    static int scaley = 16;
    static int ImageSize = sizex * sizey;  
    static String pathToImages = "test\\ocr\\opencv\\";  
    static String routeToCortes = "test\\auxiliar\\cortes\\";
    static String routeToPadrones = "test\\auxiliar\\padrones\\";

    public static void preprocesamiento(String route, String route_base , String nimg, String extension){
        
        String url = route + nimg + extension;
        java.lang.System.out.print("Procesando: " + url + " | ");
        ImagePlus imgPlus = new ImagePlus(url);
        
        java.lang.System.out.print("Afilando imagen | ");
        IJ.run(imgPlus, "Sharpen", "");
        
        java.lang.System.out.print("Sacando Mediana | ");
        IJ.run(imgPlus, "Median...", "radius=1.5");
        
        java.lang.System.out.print("Filtrando imagen binaria | ");
        IJ.run(imgPlus,"Make Binary","");
        
        java.lang.System.out.print("Esqueletonizando | ");
        IJ.run(imgPlus,"Skeletonize","");
        FileSaver fs = new FileSaver(imgPlus);
        
        java.lang.System.out.println("Guardando imagen");
        String n_out = route_base + "\\pre\\" + nimg + extension;
        fs.saveAsJpeg(n_out);
        
        
        //System.out.println("Finalizando preprocesamiento");
    }

    public static Vector<String> preprocesamiento_ocr(String route, String nimg, String extension){
        java.lang.System.out.println("Antes de preprocesamiento");
        String url = route + nimg + extension;
        java.lang.System.out.println("Reading from: " + url);
        ImagePlus imgPlus = new ImagePlus(url);
        java.lang.System.out.println("Preprocessing: " + url);
        
        ImageProcessor ip = imgPlus.getProcessor();
        ip.setInterpolationMethod(ImageProcessor.BILINEAR);
        ip = ip.resize(ip.getWidth() * 5, ip.getHeight() * 5);
        BufferedImage img = ip.getBufferedImage();

       
        imgPlus = new ImagePlus("croppedImage", img);
        
        //imgPlus.show();
        //Macro.SetOption("BlackBackground", false);
        IJ.run(imgPlus, "Make Binary", "");
        IJ.run(imgPlus, "Median...", "radius=5");
        java.lang.System.out.println("Cutting: " + url);
        Vector<String> imgs;
        imgs = null;//cutDigits(route, nimg, extension, imgPlus);
        java.lang.System.out.println("Despues de preprocesamiento");

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
                java.lang.System.out.print(result.trim() + " | ");
                final_result += result.trim();
                //System.out.print(result);
            } catch (TesseractException e) {
                java.lang.System.err.println(e.getMessage());
            }
        }
        java.lang.System.out.println(final_result);
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
        java.lang.System.out.println("Guardar imagen");
        String n_out = route_out + "\\results\\" + n_img1 + "_orb" + extension;
        Highgui.imwrite(n_out, outputImg);
        
        java.lang.System.out.println("Resultados: " + matches.size() + " " + goodMatches.size());  
        long total = (java.lang.System.currentTimeMillis() - ini);
        java.lang.System.out.println("Tiempo tomado: " + total);
        //System.out.println("Final de orb");
    }
       
    public static void surf(String route, String route_out, String n_img1, String n_img2, String extension){
        long ini = java.lang.System.currentTimeMillis();
        String bookObject = route + n_img1 + extension;
        String bookScene = route + n_img2 + extension;
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
        KeyPoint[] keypoints = objectKeyPoints.toArray();  
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

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();  
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);  
        java.lang.System.out.print("Encontrando matches entre imagenes | ");  
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);  

        java.lang.System.out.println("Calculando buenos matches");
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
            java.lang.System.out.println("Match enontrado!!! Matches: "+goodMatchesList.size());  

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

            java.lang.System.out.println("Dibujando imagen de coincidencias");
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
            java.lang.System.out.println("Objeto no encontrado");
        }  
        long total = (java.lang.System.currentTimeMillis() - ini);
        java.lang.System.out.println("Tiempo tomado: " + total);
        //java.lang.System.out.println("Ended....");  
    }

    public static ImagePlus cortarInferior(ImagePlus imgOrigen){
        int x, y;
        double r,g,b;
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        java.lang.System.out.println("Cortando borde inferior");
        //java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        
        x = 10;
        y = imgPlus.getHeight()-1;
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        r = imgPlus.getPixel(x,y)[0];
        g = imgPlus.getPixel(x,y)[1];
        b = imgPlus.getPixel(x,y)[2];
        
        while (r != 0){
            y = y - 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            
            //java.lang.System.out.println("Analizando punto " + x + ", " + y);
        }
        
        while (r == 0){
            y = y - 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            
            //java.lang.System.out.println("Analizando punto " + x + ", " + y);
        }
        
        //java.lang.System.out.println("Linea encontrada en punto " + x + ", " + y);        
        
        //Cortar desde el punto Y hacia el final de la imagen
        
        imgOrigen.setRoi(0,0,imgOrigen.getWidth(),y);
        //java.lang.System.out.println("Cortando");
        IJ.run(imgOrigen, "Crop", "");
        
        return imgOrigen;
    }
    
    public static ImagePlus cortarLaterales(ImagePlus imgOrigen){
        int x, y;
        double r,g,b;
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        java.lang.System.out.println("Cortando borde izquierdo");
        //java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        
        x = 0;
        y = (imgPlus.getHeight()-1)/2;
                
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        r = imgPlus.getPixel(x,y)[0];
        g = imgPlus.getPixel(x,y)[1];
        b = imgPlus.getPixel(x,y)[2];
        //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        
        while (r != 0){
            x = x + 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        while (r == 0){
            x = x + 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        //java.lang.System.out.println("Linea encontrada en punto " + x + ", " + y);        
        
        //Cortar desde el punto Y hacia el final de la imagen
        
        imgOrigen.setRoi(x,0,imgOrigen.getWidth(),imgOrigen.getHeight());
        //java.lang.System.out.println("Cortando");
        IJ.run(imgOrigen, "Crop", "");
        
        return imgOrigen;
    }
    
    public static ImagePlus cortarLateralDer(ImagePlus imgOrigen){
        int x, y;
        double r,g,b;
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        java.lang.System.out.println("Cortando borde derecho");
        //java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        x = 10;
        y = 0;
                
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        r = imgPlus.getPixel(x,y)[0];
        g = imgPlus.getPixel(x,y)[1];
        b = imgPlus.getPixel(x,y)[2];
        //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        
        while (r != 0){
            y = y + 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        while (r == 0){
            y = y + 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        //java.lang.System.out.println("Linea encontrada en punto " + x + ", " + y);        
        
        //Ubicada la altura Y
        y = y + 10;
        x = imgPlus.getWidth()-1;
        
        while (r != 0){
            x = x - 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        while (r == 0){
            x = x - 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        //Cortar desde el punto Y hacia el final de la imagen
        imgOrigen.setRoi(0,0, x, imgOrigen.getHeight());
        //java.lang.System.out.println("Cortando");
        IJ.run(imgOrigen, "Crop", "");
        
        return imgOrigen;
    }
    
    public static void cortarCaracteres(String route, String n_img, int n, ImagePlus imgOrigen, String extension, int[] index){
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 9 y
        //cortar dni
        int aux = index[1] - index[0];
        imgOrigen.setRoi(index[0],0,aux,imgOrigen.getHeight());
        ImagePlus imp = new Duplicator().run(imgOrigen);
        FileSaver fs = new FileSaver(imp);
        String n_out = routeToCortes + n_img + n + "\\dni" + extension;
        fs.saveAsPng(n_out);
        java.lang.System.out.println("Cortando dni en: " + index[0] + " y " + index[1]);
        
        //cortar apellido
        aux = index[3] - index[2];
        imgOrigen.setRoi(index[2],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = routeToCortes + n_img + n + "\\apellido" + extension;
        fs.saveAsPng(n_out);
        java.lang.System.out.println("Cortando apellido en: " + index[2] + " y " + index[3]);
        
        //cortar nombre
        aux = index[4] - index[3];
        imgOrigen.setRoi(index[3],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = routeToCortes + n_img + n + "\\nombre" + extension;
        fs.saveAsPng(n_out);
        java.lang.System.out.println("Cortando nombre en: " + index[3] + " y " + index[4]);
        
        //cortar firma
        aux = index[6] - index[5];
        imgOrigen.setRoi(index[5],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = routeToCortes + n_img + n + "\\firma" + extension;
        fs.saveAsPng(n_out);
        java.lang.System.out.println("Cortando firma en: " + index[5] + " y " + index[6]);
        
        //cortar huella
        aux = index[8] - index[7];
        imgOrigen.setRoi(index[7],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = routeToCortes + n_img + n + "\\huella" + extension;
        fs.saveAsPng(n_out);
        java.lang.System.out.println("Cortando huella en: " + index[7] + " y " + index[8]);
    }
    
    public static ImagePlus cortarFilas (int n, ImagePlus imgOrigen, String route, String n_img, String extension, int[] index){
        int x, y;
        double r,g,b;
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        //java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        x = 10;
        y = imgPlus.getHeight() - 10;
                
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        r = imgPlus.getPixel(x,y)[0];
        g = imgPlus.getPixel(x,y)[1];
        b = imgPlus.getPixel(x,y)[2];
        //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        
        while (r == 0){
            y = y - 1;
            
            r = imgPlus.getPixel(x,y)[0];
            g = imgPlus.getPixel(x,y)[1];
            b = imgPlus.getPixel(x,y)[2];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        
        int rowSize = imgPlus.getHeight() - y;
        //java.lang.System.out.println("Tamaño de fila: " + rowSize);        
        
        //Copiar imagen
        imgOrigen.setRoi(0,0, imgOrigen.getWidth(), y);
        ImagePlus img = new Duplicator().run(imgOrigen);
        
        //Cortar Filas
        //java.lang.System.out.println(x);
        imgOrigen.setRoi(0,y, imgOrigen.getWidth(), rowSize);
        java.lang.System.out.println("Cortando fila " + n);
        IJ.run(imgOrigen, "Crop", "");
        
        
        FileSaver fs = new FileSaver(imgOrigen);
        String n_out = routeToCortes + n_img + n + "\\" + n_img + "_row_" + n + extension;
        java.lang.System.out.println("=======Guardando imagen en " + n_out + "=======");
        fs.saveAsJpeg(n_out);
        
        
        //Cortar Caracteres de fila
        cortarCaracteres(route, n_img, n, imgOrigen, extension, index);
        
        return img;
        
    }
    
    public static int bajar(ImagePlus imgPlus, int x, int y, int color){
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        double r = imgPlus.getPixel(x,y)[0];
        //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        while (r == color){
            y = y + 1;
            r = imgPlus.getPixel(x,y)[0];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        return y;
    }
    
    public static int derecha(ImagePlus imgPlus, int x, int y, int color){
        //java.lang.System.out.println("Buscando color en punto " + x + ", " + y);
        double r = imgPlus.getPixel(x,y)[0];
        //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        while (r == color){
            x = x + 1;
            r = imgPlus.getPixel(x,y)[0];
            //java.lang.System.out.println("Color en punto " + x + ", " + y + ": " + r + " " + g + " " + b);        
        }
        return x;
    }
    
    public static int[] saltarNumero(ImagePlus imgPlus, int[] index){
        int x = 10;
        int y = 0;

        y = bajar(imgPlus, x, y, 255); //Negro
        y = bajar(imgPlus, x, y, 0); //Blanco
        java.lang.System.out.println("y: " + y);
        y = bajar(imgPlus, x, y, 255); //Negro
        java.lang.System.out.println("y: " + y);
        y = y + 2;
        index[9] = y; //Control rotacion
        java.lang.System.out.println("y: " + y);
        //java.lang.System.exit(0);
        x = 0;
        x = derecha(imgPlus, x, y, 0); //Blanco
        x = derecha(imgPlus, x, y, 255); //Negro
        //java.lang.System.out.println("x: " + x);
        x = derecha(imgPlus, x, y, 0); //Blanco
        //java.lang.System.out.println("x: " + x);
        x = derecha(imgPlus, x, y, 255); //Negro
        //java.lang.System.out.println("x: " + x);
        
        index[0] = x;
        return index;
    }
    
    public static int[] contarDni(ImagePlus imgPlus, int[] index){
        int x = index[0];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[1] = x;
        x = derecha(imgPlus, x, y, 255); //Negro
        index[2] = x;
        
        return index;
    }
    
    public static int[] contarApellidos(ImagePlus imgPlus, int[] index){
        int x = index[2];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[4] = x;
        //java.lang.System.out.println("x:" + x + " y: " + y);
        x = derecha(imgPlus, x, y, 255); //Negro
        index[5] = x;
        
        return index;
    }
    
    public static int[] contarNombres(ImagePlus imgPlus, int[] index){
        double porcentajeApellidos = 52.083333;
        int longitud = index[4] - index[2];
        int longitudApellido = (int) (longitud*porcentajeApellidos/100);
        int x = index[2] + longitudApellido;
        index[3] = x;
        //java.lang.System.out.println(longitudApellido);
        //java.lang.System.exit(0);
        return index;
    }
    
    public static int[] contarFirma(ImagePlus imgPlus, int[] index){
        int x = index[5];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[6] = x;
        x = derecha(imgPlus, x, y, 255); //Negro
        index[7] = x;
        
        return index;
    }
    
    public static int[] contarHuella(ImagePlus imgPlus, int[] index){
        int x = index[7];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[8] = x;
        
        return index;
    }
        
    public static int[] contarCabecera(ImagePlus imgOrigen){
        int[] index = new int[10];
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        
        //GIRAR IMAGEN
        imgPlus = girarImagen(imgPlus);
        
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 9 y
        java.lang.System.out.println("Saltando numero");
        index = saltarNumero(imgPlus, index);
        //contar dni
        java.lang.System.out.println("Contando dni");
        index = contarDni(imgPlus, index);
        //contar apellidos
        java.lang.System.out.println("Contando apellidos");
        index = contarApellidos(imgPlus, index);
        //contar nombres
        java.lang.System.out.println("Contando nombres");
        index = contarNombres(imgPlus, index);
        //contar firma
        java.lang.System.out.println("Contando firmas");
        index = contarFirma(imgPlus, index);
        //contar huella
        java.lang.System.out.println("Contando huellas");
        index = contarHuella(imgPlus, index);
        
        return index;
    }
    
    public static ImagePlus girarImagen(ImagePlus imgOrigen){
        int x1 = 10;
        int y1 = 0;
        
        y1 = bajar(imgOrigen, x1, y1, 255); //Blanco
        y1 = bajar(imgOrigen, x1, y1, 0); //Negro
        
        int x2 = imgOrigen.getWidth()-10;
        int y2 = 0;
        y2 = bajar(imgOrigen, x2, y2, 255); //Blanco
        y2 = bajar(imgOrigen, x2, y2, 0); //Negro
        //java.lang.System.out.println(y1 + " " + y2);
        
        double angle = new Line(1,467,18,467).getAngle(x1, y1, x2, y2);
        java.lang.System.out.println("Angulo: " + angle);
        ImageProcessor img = imgOrigen.getProcessor();
        img.setBackgroundValue(255);
        img.rotate(angle);
        ImagePlus img_rotated = new ImagePlus("rotated", img);
        
        
        FileSaver fs = new FileSaver(img_rotated);
        String n_out = routeToCortes + "test.jpg";
        //fs.saveAsPng(n_out);
        
        return img_rotated;
    }
    
    public static void cortarCajas(String route, String n_img, String extension){     
        
        String inFile = route + n_img + extension;
        
        java.lang.System.out.println("=======Abriendo imagen " + n_img + extension + " para binarizar=======");
        
        ImagePlus imgOrigen = new ImagePlus(inFile);
        ImageProcessor img = imgOrigen.getProcessor();
        img = img.resize(3500);
        ImagePlus imgResize = new ImagePlus("resized",img);
        //Cortar bordes
        //java.lang.System.out.println("=======Cortando borde izquierdo=======");
        imgOrigen = cortarLaterales(imgResize);
        //java.lang.System.out.println("=======Cortando borde inferior=======");
        imgOrigen = cortarInferior(imgResize);
        //java.lang.System.out.println("=======Cortando borde derecho=======");
        imgOrigen = cortarLateralDer(imgResize);
        
        
        
        //java.lang.System.exit(0);
        FileSaver fs = new FileSaver(imgResize);
        String n_out = routeToCortes + n_img + "_crop" + extension;
        //fs.saveAsPng(n_out);
        
        
        //Cortar
        java.lang.System.out.println("=======Contando cabecera=======");
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 
        int[] index = contarCabecera(imgResize);
        java.lang.System.out.println("=======Cortando filas de imagen=======");
        for(int n = 1; n < 9 ; n++){
            File directorio = new File(routeToCortes+n_img+n); 
            directorio.mkdir(); 
            imgResize = cortarFilas(n,imgResize, route, n_img, extension, index);
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        File dll = new File("lib\\opencv_java2412.dll");
        java.lang.System.load(dll.getAbsolutePath());
        
        String n_img_text = "1_cut";
        String route_base = "test\\";
        String route_huellas = route_base + "huellas\\";
        String route_pre = route_base + "pre\\";
        String route_ocr = route_base + "ocr\\";
        String route_aux = route_base + "auxiliar\\";
        String route_ocr_exp = route_ocr + "exp\\";
        String extension = ".jpg";
        String extension_huellas = ".tif";
        String n_img1  = "lena";
        String n_img2  = "103_1";
        
        //OCR TESSERACT
        //Vector<String> imgs;
        //imgs = preprocesamiento_ocr(route_ocr, n_img_text, extension);
        //ITesseract instance_num = new Tesseract1(); // JNA Direct Mapping
        //ITesseract instance_let = new Tesseract1(); // JNA Direct Mapping
        //instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        //instance_let.setTessVariable("tessedit_char_whitelist", "abcdefghijklmnopqrstuvwxyz");
        //ocr(instance, imgs);
        
        //java.lang.System.out.println("Test Tesseract:");
        //ocr_exp(instance_num, route_ocr_exp);
        //ocr_exp_let(instance_let, route_ocr_exp);
                
        //PREPROCESAMIENTO IMAGEJ + ORB - SURF
        
        /*
        java.lang.System.out.println("***INICIANDO PREPROCESAMIENTO***");
        preprocesamiento(route_huellas, route_base, n_img1, extension_huellas);
        preprocesamiento(route_huellas, route_base, n_img2, extension_huellas);
        java.lang.System.out.println("***FINALIZANDO PREPROCESAMIENTO***");
        java.lang.System.out.println("***INICIANDO ORB***");
        orb(route_pre, route_base, n_img1, n_img2, extension_huellas);
        java.lang.System.out.println("***FINALIZANDO ORB***");
        */
        //java.lang.System.out.println("***INICIANDO SURF***");
        //surf(route_pre, route_base, n_img1, n_img2, extension_huellas);
        //java.lang.System.out.println("***FINALIZANDO SURF***");       
        
        Files.walk(Paths.get(routeToPadrones)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                java.lang.System.out.println(filePath.getFileName());
                String[] name = filePath.getFileName().toString().split("[.]");
                cortarCajas(routeToPadrones,name[0],"." + name[1]);
            }
        });

    }  
    
}


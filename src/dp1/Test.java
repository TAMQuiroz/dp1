package dp1;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.*;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Test {
    static int train_samples = 1;  
    static int classes = 10;  
    static int sizex = 20;  
    static int sizey = 30;  
    static int ImageSize = sizex * sizey;  
    static String pathToImages = "test\\ocr\\opencv\\";  
    
    public static void template_matching(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
        System.out.println("\nRunning Template Matching");
        
        String inFile = "C:\\Users\\tamqu\\Desktop\\in.png";
        String outFile = "C:\\Users\\tamqu\\Desktop\\out.png";
        String templateFile = "C:\\Users\\tamqu\\Desktop\\template.png";
        int match_method = 1;
        
        Mat img = Imgcodecs.imread(inFile, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat templ = Imgcodecs.imread(templateFile, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // / Localizing the best match with minMaxLoc
        MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        // / Show me what you got
        Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));

        // Save the visualized detection.
        System.out.println("Writing "+ outFile);
        Imgcodecs.imwrite(outFile, img);
    }

    public static void preprocesamiento(String route, String nimg, String extension){
        System.out.println("Antes de preprocesamiento");
        String url = route + nimg + extension;
        ImagePlus imgPlus = new ImagePlus(url);
        IJ.run(imgPlus, "Sharpen", "");
        IJ.run(imgPlus, "Median...", "radius=1.5");
        IJ.run(imgPlus,"Make Binary","");
        IJ.run(imgPlus,"Skeletonize","");
        FileSaver fs = new FileSaver(imgPlus);
        String n_out = route + "\\pre\\" + nimg + "_out" + extension;
        fs.saveAsJpeg(n_out);
              
        System.out.println("Despues de preprocesamiento");
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
    
    public static void orb(String route, String n_img1, String n_img2, String extension){
        System.out.println("Inicio de orb");
        String url1 = route + "\\pre\\" + n_img1 + "_out" + extension;
        String url2 = route + "\\pre\\" + n_img2 + "_out" + extension;
        
        Mat img1 = Imgcodecs.imread(url1, 0); //imagen query
        Mat img2 = Imgcodecs.imread(url2, 0); //imagen base
        
        //iniciar detector SIFT
        FeatureDetector orb = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        DescriptorMatcher bf = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();
        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        
        //encontrar keypoints y descriptores con SIFT
        orb.detect(img1, keypoints1);
        descriptor.compute(img1, keypoints1, descriptors1);
        
        orb.detect(img2, keypoints2);
        descriptor.compute(img2, keypoints2, descriptors2);
        
        //crear un matcher de fuerza bruta
        MatOfDMatch matches = new MatOfDMatch();
        
        //matchear descriptores
        bf.match(descriptors1, descriptors2, matches);
        
        //ordenarlos con menor distancia
        
        
        // Linking
        Scalar RED = new Scalar(255,0,0);
        Scalar GREEN = new Scalar(0,255,0);
        System.out.println(matches);
        List<DMatch> matchesList = matches.toList();
        Double max_dist = 0.0;
        Double min_dist = 45.0;
        
        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
        for(int i = 0;i < matchesList.size(); i++){
            if (matchesList.get(i).distance <= min_dist)
                good_matches.addLast(matchesList.get(i));
        }

        // Printing
        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(good_matches);

        System.out.println("Results: " + matches.size() + " " + goodMatches.size());  
        
        Mat outputImg = new Mat();
        MatOfByte drawnMatches = new MatOfByte();
        Features2d.drawMatches(img1, keypoints1, img2, keypoints2, goodMatches, outputImg, GREEN, RED, drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
        
        String n_out = route + "\\results\\" + n_img1 + "_" + n_img2 + "_match" + extension;
        Imgcodecs.imwrite(n_out, outputImg);
        System.out.println("Fin de orb");
    }
    
    public static Mat thinningIteration(Mat im, int iter){
        Mat marker = Mat.zeros(im.size(), CvType.CV_8UC1);
        for (int i = 1; i < im.rows()-1; i++)
        {
            for (int j = 1; j < im.cols()-1;j++)
            {
                int p2 = checkDawg((int)im.get(i-1, j)[0]);
                int p3 = checkDawg((int)im.get(i-1, j+1)[0]);
                int p4 = checkDawg((int)im.get(i, j+1)[0]);
                int p5 = checkDawg((int)im.get(i+1, j+1)[0]);
                int p6 = checkDawg((int)im.get(i+1, j)[0]);
                int p7 = checkDawg((int)im.get(i+1, j-1)[0]);
                int p8 = checkDawg((int)im.get(i, j-1)[0]);
                int p9 = checkDawg((int)im.get(i-1, j-1)[0]);
                int A = (checkYo(p2,p3)) + (checkYo(p3,p4)) +
                        (checkYo(p4,p5)) + (checkYo(p5,p6)) +
                        (checkYo(p6,p7)) + (checkYo(p7,p8)) +
                        (checkYo(p8,p9)) + (checkYo(p9,p2));
                
                int B = p2+p3+p4+p5+p6+p7+p8+p9;
                
                int m1 = iter==0?(p2*p4*p6):(p2*p4*p8);
                int m2 = iter==0?(p4*p6*p8):(p2*p6*p8);
                
                //System.out.println("A: " + A + " B: " + B + " m1: " + m1 + " m2: " + m2);
                if(A == 1 && (B >=2 && B <= 6) && m1 == 0 && m2 == 0){
                    marker.put(i, j, 255);
                }
            }
        }
        im = marker;
        
        return im;
    }
    
    public static int checkDawg(int a){
        if(a == 255)
            return 1;
        return 0;
    }
    
    public static int checkYo(int a, int b){
        if (a == 0 && b == 1)
            return 1;
        return 0;
    }
    
    public static Mat thinning(Mat im){
        Mat im_result = new Mat();
        im.copyTo(im_result);
        //Core.divide(im, new Scalar(255), im_result);     
        
        Mat prev = Mat.zeros(im_result.size(), CvType.CV_8UC1);
        Mat diff = new Mat();

        int i = 0;
        do{
            im_result = thinningIteration(im_result,0);
            im_result = thinningIteration(im_result,1);
            Core.absdiff(im_result,prev,diff);
            im_result.copyTo(prev);
            
            Imgcodecs.imwrite("test\\results\\1.png", im_result);
            if(i == 0)
                exit(1);
            i++;
        }
        while(Core.countNonZero(diff)>0);

        //Core.multiply(im_result, Scalar.all(255), im);

        return im_result;
    }
    
    public static void harris(String route, String n_img1, String n_img2, String extension){
        System.out.println("Inicio de harris");
        String url1 = route + n_img1 + extension;
        String url2 = route + n_img2 + extension;
        
        Mat input = Imgcodecs.imread(url1, 0); //imagen query
        Mat input2 = Imgcodecs.imread(url2, 0); //imagen base
        
        //Binarizar imagen via tresholding
        Mat input_binary = new Mat();
        Mat input_binary2 = new Mat();
        Imgproc.threshold(input, input_binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        Imgproc.threshold(input2, input_binary2, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        
        //Aplicar algoritmo de thinning
        System.out.println("Entro a thinning");
        Mat input_thinned = new Mat();
        input_thinned = thinning(input_binary);
        System.out.println("Entro a thinning2");
        Mat input_thinned2 = new Mat();
        input_thinned2 = thinning(input_binary2);
        
        //Detecta minucias usando la deteccion de esquinas Harris
        Mat harris_corners;
        Mat harris_normalised = new Mat();
        harris_corners = Mat.zeros(input_thinned.size(),CvType.CV_32FC1);
        Imgproc.cornerHarris(input_thinned, harris_corners, 2, 3, 0.04);
        Core.normalize(harris_corners, harris_normalised, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1);
        
        Mat harris_corners2;
        Mat harris_normalised2 = new Mat();
        harris_corners2 = Mat.zeros(input_thinned2.size(),CvType.CV_32FC1);
        Imgproc.cornerHarris(input_thinned2, harris_corners2, 2, 3, 0.04);
        Core.normalize(harris_corners2, harris_normalised2, 0, 255, Core.NORM_MINMAX, CvType.CV_32FC1);
        
        //Selecciona las esquinas mas fuertes
        int threshold_harris = 125;
        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        
        //Hacer un clon de color para ver mejor
        Mat rescaled = new Mat();
        Core.convertScaleAbs(harris_normalised, rescaled);
        
        List<Mat> harris_c = new ArrayList<Mat>();
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        harris_c.add(new Mat(rescaled.rows(),rescaled.cols(),CvType.CV_8UC3));
        List<Mat> in = new ArrayList<Mat>();
        in.add(rescaled);
        in.add(rescaled);
        in.add(rescaled);
        MatOfInt from_to = new MatOfInt(0,0, 1,1, 2,2);
               
        Core.mixChannels(in, harris_c, from_to);
        
        for (int x = 0; x < harris_normalised.cols(); x++){
            for (int y = 0; y < harris_normalised.rows(); y++){
                if((int)harris_normalised.get(y, x)[0] > threshold_harris){
                    //Dibuja o guarda los keypoints
                    keypoints.fromArray(new KeyPoint(x,y,1));
                }
            }
        }
        
        
        Mat rescaled2 = new Mat();
        Core.convertScaleAbs(harris_normalised2, rescaled2);
        List<Mat> harris_c2 = new ArrayList<Mat>();
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        harris_c2.add(new Mat(rescaled2.rows(),rescaled2.cols(),CvType.CV_8UC3));
        List<Mat> in2 = new ArrayList<Mat>();
        in2.add(rescaled2);
        in2.add(rescaled2);
        in2.add(rescaled2);
        MatOfInt from_to2 = new MatOfInt(0,0, 1,1, 2,2);
        Core.mixChannels(in2, harris_c2, from_to2);
        
        for (int x = 0; x < harris_normalised2.cols(); x++){
            for (int y = 0; y < harris_normalised2.rows(); y++){
                if((int)harris_normalised2.get(y, x)[0] > threshold_harris){
                    //Dibuja o guarda los keypoints
                    keypoints2.fromArray(new KeyPoint(x,y,1));
                }
            }
        }
        
        //Calcular descriptor ORB basado en keypoints
        DescriptorExtractor descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        Mat descriptors = new Mat();
        descriptor.compute(input_thinned, keypoints, descriptors);
        Mat descriptors2 = new Mat();
        descriptor.compute(input_thinned2, keypoints2, descriptors2);
        
        DescriptorMatcher bf = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();
        bf.match(descriptors, descriptors2, matches);
        
        float score = 0;
        List<DMatch> matchesList = matches.toList();
        
        for (int i = 0; i < matchesList.size(); i++){
            DMatch current_match = matchesList.get(i);
            score = score + current_match.distance;
            
        }
        
        System.out.println("\nCurrent matching score = " + score + "\n");
        
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
    
    public static Mat preProcessImage(Mat img){
        Mat outImage = new Mat();
        Mat grayImage = new Mat();
        Mat blurredImage = new Mat();
        Mat thresholdImage = new Mat();
        Mat contourImage = new Mat();
        Mat regionOfInterest;
        
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.cvtColor(img, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(grayImage, blurredImage, new Size(5,5), 2,2);
        Imgproc.adaptiveThreshold(blurredImage, thresholdImage, 255, 1, 1, 11, 2);
        thresholdImage.copyTo(contourImage);
        Imgproc.findContours(contourImage, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        
        int idx=0;
        double area = 0;
        for (int i = 0; i < contours.size(); i++){
            //System.out.println(Imgproc.contourArea(contours.get(i)));
            double contourArea = Imgproc.contourArea(contours.get(i));
            if (area < contourArea){
                idx = i;
                area = contourArea;
            }
        }
        Rect rec = Imgproc.boundingRect(contours.get(idx));
        regionOfInterest = thresholdImage.submat(rec);
        Imgproc.resize(regionOfInterest, outImage, new Size(sizex,sizey));
        
        return outImage;
    }
    
    public static void learnFromImages(Mat trainData, Mat trainClasses){
        Mat img;
        String file;
        for(int i = 0; i < classes; i++){
            file = pathToImages + i + ".png";
            img = imread(file,1);
            if (img.empty()){
                System.out.println("File " + file + " not found\n");
                exit(1);
            }
            Mat outfile = preProcessImage(img);
            //Imgcodecs.imwrite(pathToImages+i+"_out.png",outfile);
            trainData.push_back(outfile);
           // trainClasses.push_back(i);
            
        }
    }
    
    public static void ocrOpenCV(String route_ocr, String n_img_text, String extension){
        Mat trainData = new Mat (10 * 1, 20 * 30, CvType.CV_32FC1);  
        Mat trainClasses = new Mat (10 * 1, 1, CvType.CV_32FC1);  
        
        learnFromImages(trainData, trainClasses);
    }
    
    public static void main(String[] args) {
        String route = "test\\";
        String route_ocr = "test\\ocr\\";
        String extension = ".png";
        String n_img1  = "1";
        String n_img2  = "2";
        
        String n_img_text = "1_cut";
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
        
        //OCR
        Vector<String> imgs;
        imgs = preprocesamiento_ocr(route_ocr, n_img_text, extension);
        ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setTessVariable("tessedit_char_whitelist", "0123456789");
        ocr(instance, imgs);
        //ocrOpenCV(route_ocr, n_img_text, extension);
        //template_matching();        
        
        //PREPROCESAMIENTO IMAGEJ + ORB
        //preprocesamiento(route, n_img1, extension);
        //preprocesamiento(route, n_img2, extension);
        //orb(route, n_img1, n_img2, extension);
        
        //PREPROCESAMIENTO OPENCV (HARRIS CORNER) + ORB
        //harris(route, n_img1, n_img2, extension);
        
    }  
    
    
    
}


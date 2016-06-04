/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Person;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author tamqu
 */
public class ocrLib {
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
        
    public static void tesseract(ITesseract instance, Vector<String> imgs){
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
    
    public static Person ocr(String dni, String name, String lastname){
        Person persona = null;
        return persona;
    }
    
    public static void main(String[] args){
        String route_base = "test\\";
        String route_ocr = route_base + "ocr\\";
        String n_img_text = "1_cut";
        String extension = ".jpg";

        //OCR TESSERACT
        Vector<String> imgs;
        imgs = preprocesamiento_ocr(route_ocr, n_img_text, extension);
        ITesseract instance_num = new Tesseract1();
        ITesseract instance_let = new Tesseract1();
        instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        instance_let.setTessVariable("tessedit_char_whitelist", "abcdefghijklmnopqrstuvwxyz");
        tesseract(instance_num, imgs);
        tesseract(instance_let, imgs);
    }
}

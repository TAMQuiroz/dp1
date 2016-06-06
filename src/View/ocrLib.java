/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.OcrCharacter;
import Model.Person;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

/**
 *
 * @author tamqu
 */
public class ocrLib {
    
    public static ImagePlus borrarBordeIzq(ImagePlus img){
        int x, y;
        x = 0;
        y = 10;
        int r = img.getPixel(x, y)[0];
        if (r == 255){
            while(r == 255){
                x = x + 1;
                
                r = img.getPixel(x, y)[0];
            }
            img.setRoi(x, 0, img.getWidth() - x, img.getHeight());
            img = new Duplicator().run(img);
        }
        return img;
    }
    
    public static ImagePlus borrarBordeDer(ImagePlus img){
        int x, y;
        x = img.getWidth();
        y = 10;
        int r = img.getPixel(x, y)[0];
        if (r == 255){
            while(r == 255){
                x = x - 1;
                
                r = img.getPixel(x, y)[0];
            }
            img.setRoi(0, 0, x, img.getHeight());
            img = new Duplicator().run(img);
        }
        return img;
    }
    
    public static ImagePlus borrarBordeArriba(ImagePlus img){
        int x, y;
        x = derechaNegro(0,30,img)+5;
        y = 0;
        int r = img.getPixel(x, y)[0];
        if (r == 255){
            while(r == 255){
                y = y + 1;
                r = img.getPixel(x, y)[0];
            }
            img.setRoi(0, y + 5, img.getWidth(), img.getHeight());
            img = new Duplicator().run(img);
        }
        return img;
    }
    
    public static ImagePlus borrarBordeAbajo(ImagePlus img){
        int x, y;
        x = 10;
        y = img.getHeight()-1;
        int r = img.getPixel(x, y)[0];
        
        if (r == 255){
            while(r == 255){
                y = y - 1;
                r = img.getPixel(x, y)[0];
            }
            img.setRoi(0, 0, img.getWidth(), y);
            img = new Duplicator().run(img);
        }
        return img;
    }
    
    public static int derechaBlanco(int x, ImagePlus img){
        int y = 10;
        int r = img.getPixel(x, y)[0];
        while(r == 0){
            x = x + 1;
            r = img.getPixel(x, y)[0];
            if(x > img.getWidth()) break;
        }
        return x;
    }
    
    public static int derechaNegro(int x, int y, ImagePlus img){

        int r = img.getPixel(x, y)[0];
        while(r == 255){
            x = x + 1;
            r = img.getPixel(x, y)[0];
            if(x > img.getWidth()) break;
        }
        return x;
    }
       
    public static ImagePlus cutPadding(ImagePlus img){
        int y = 10, min = 10000, max = 0;
        int r;
        
        for(int i = 10; i < img.getWidth()-10; i++){
            y = 10;
            while(y < img.getHeight()){
                r = img.getPixel(i, y)[0];
                if(r == 255){
                    if(y < min){
                        min = y;
                    }
                    break;
                }
                y = y + 1;
                //java.lang.System.out.println(y);
            }
            
            y = img.getHeight() - 10;
            while(y > 0){
                r = img.getPixel(i, y)[0];
                if(r == 255){
                    if(y > max){
                        max = y;
                    }
                    break;
                }
                y = y - 1;
                //java.lang.System.out.println(y);
            }
            
        }
        
        min = min - 10;
        max = max + 10;

        img.setRoi(0, min, img.getWidth(), max - min);
        img = new Duplicator().run(img);
        if (img.getHeight() < 10 || img.getWidth() < 10){
            img = null;
        }
        //java.lang.System.out.println(img);
        //img.show();
        return img;
    }
    
    public static ArrayList<BufferedImage> cutDigits(ImagePlus img, int n){
        ArrayList<BufferedImage> imgs = new ArrayList<>();

        img = borrarBordeArriba(img);
        //Borrar borde izq
        img = borrarBordeIzq(img);
        //Borrar borde der
        img = borrarBordeDer(img);
        img = borrarBordeAbajo(img);
        //img.show();
        
        int x1 = 0, x2 = 0;
        for (int i = 0; i < n; i++){
            //moverme hasta linea
            x2 = derechaBlanco(x1, img);
            img.setRoi(x1, 0, x2 - x1, img.getHeight());
            ImagePlus aux = new Duplicator().run(img);
            aux = cutPadding(aux);
            if(aux != null){
                //aux.show();
                imgs.add(aux.getBufferedImage());
            }else{
                imgs.add(null);
            }
            //avanzar negro
            x1 = derechaNegro(x2, 10, img);
        }
        
        return imgs;
    }
    
    public static ArrayList<OcrCharacter> preprocesamiento_ocr(ITesseract instance_num, String route, int n){
        java.lang.System.out.println("Antes de preprocesamiento");
        String url = route;
        java.lang.System.out.println("Reading from: " + url);
        ImagePlus imgPlus = new ImagePlus(url);
        java.lang.System.out.println("Preprocessing: " + url);
        
        ImageProcessor ip = imgPlus.getProcessor();
        ip.setInterpolationMethod(ImageProcessor.BILINEAR);
        ip = ip.resize(ip.getWidth() * 10, ip.getHeight() * 10);
        BufferedImage img = ip.getBufferedImage();
       
        imgPlus = new ImagePlus("croppedImage", img);
        
        //imgPlus.show();
        //Macro.SetOption("BlackBackground", false);
        IJ.run(imgPlus, "Make Binary", "");
        IJ.run(imgPlus, "Median...", "radius=5");
        java.lang.System.out.println("Cutting: " + url);
        ArrayList<BufferedImage> imgs = cutDigits(imgPlus, n);
        java.lang.System.out.println("Despues de preprocesamiento");

        ArrayList<OcrCharacter> resultado = tesseract(instance_num, imgs);
        
        return resultado;

    }
        
    public static ArrayList<OcrCharacter> tesseract(ITesseract instance, ArrayList<BufferedImage> imgs){
        //String final_result = new String();
        ArrayList<OcrCharacter> final_result = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++){
            BufferedImage img = imgs.get(i);
            if(img != null){
                    //String result = instance.doOCR(img);
                    List<Word> palabra = instance.getWords(img, 0);
                    OcrCharacter letter = new OcrCharacter ();
                    letter.setLetter(palabra.get(0).getText().replace(" ", "").replace("\n\r", "").replace("\r\n", "").replace("\n", "").replace("\r", "").trim());
                    letter.setConfidence(palabra.get(0).getConfidence());
                    java.lang.System.out.println("Caracter: " + letter.getLetter() + " Confianza: " + letter.getConfidence());
                    //java.lang.System.out.print(result + " | ");
                    final_result.add(letter);
                    //final_result += letter.getLetter();
                    //System.out.print(result);
            }else{
                final_result.add(null);
            }
        }
        //
        for (OcrCharacter result : final_result) {
            if(result != null){
                java.lang.System.out.print(result.getLetter());
            }
        }
        java.lang.System.out.println("");
        return final_result;
    }
    
    public static Person ocr(ITesseract instance_num, ITesseract instance_let, String dni, String name, String lastname){
        Person persona;
        ArrayList<OcrCharacter> ocrDni, ocrName, ocrLastname; 
        ocrDni = preprocesamiento_ocr(instance_num, dni, 8);
        ocrName = preprocesamiento_ocr(instance_let, name, 23);
        ocrLastname = preprocesamiento_ocr(instance_let, lastname, 25);
        
        persona = null;//Manager.queryRnvPerson(ocrDni, ocrName, ocrLastname);
        
        return persona;
    }
    
    public static void main(String[] args){
        String route_dni = "test\\auxiliar\\cortes\\9\\padron11\\dni.jpg";
        String route_name = "test\\auxiliar\\cortes\\9\\padron11\\nombre.jpg";
        String route_lastname = "test\\auxiliar\\cortes\\9\\padron11\\apellido.jpg";

        //OCR TESSERACT
        
        ITesseract instance_num = new Tesseract1();
        ITesseract instance_let = new Tesseract1();
        instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        //instance_let.setPageSegMode(TessPageSegMode.PSM_SINGLE_CHAR);
        instance_let.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        preprocesamiento_ocr(instance_num, route_dni, 8);
        //preprocesamiento_ocr(instance_let, route_name, 23);
        //preprocesamiento_ocr(instance_let, route_lastname, 25);
        //tesseract(instance_num, imgs);
        //tesseract(instance_let, imgs);
    }
}
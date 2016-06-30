/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.OcrCharacter;
import Model.Person;
import static View.UploadLib.console;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
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
        y = 25;
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
        y = 0;
        x = derechaNegro(0,50,img)+5;
        //java.lang.System.out.println("Coordenada x,y: " +x + "," + y);
        
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
        x = 15;
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
        //java.lang.System.out.println("Color:" + r);
        while(r == 255){
            x = x + 1;
            r = img.getPixel(x, y)[0];
            if(x > img.getWidth()) break;
        }
        return x;
    }
       
    public static ImagePlus cutPadding(ImagePlus img){
        int y, min = 10000, max = 0;
        int r;
        
        for(int i = 20; i < img.getWidth()-10; i++){
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
        //img.show();
        return img;
    }
    
    public static ImagePlus eraseBlack(ImagePlus img){
        img = borrarBordeIzq(img);
        img = borrarBordeDer(img);
        img = borrarBordeArriba(img);
        img = borrarBordeAbajo(img);
        
        return img;
    }
    
    public static ArrayList<BufferedImage> cutDigits(ImagePlus img, int n){
        ArrayList<BufferedImage> imgs = new ArrayList<>();
        img = borrarBordeArriba(img);
        //img.show();
        img = borrarBordeIzq(img);
        //img.show();
        img = borrarBordeDer(img);
        img = borrarBordeAbajo(img);
        int x1 = 5, x2;
        for (int i = 0; i < n; i++){
            //moverme hasta linea
            
            x2 = derechaBlanco(x1, img);
            
            img.setRoi(x1, 0, x2 - x1, img.getHeight());
            ImagePlus aux = new Duplicator().run(img);
            
            aux = cutPadding(aux);
            aux = eraseBlack(aux);
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
        ArrayList<OcrCharacter> final_result = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++){
            BufferedImage img = imgs.get(i);
            List<Word> palabra = instance.getWords(img, 0);
            if(!palabra.isEmpty()){

                OcrCharacter letter = new OcrCharacter ();
                String interpretado = palabra.get(0).getText().replace(" ", "").replace("\n\r", "").replace("\r\n", "").replace("\n", "").replace("\r", "").trim();
                if(interpretado.equals("")){
                    letter.setLetter("%");
                    letter.setConfidence(100);
                }else{
                    interpretado = interpretado.substring(0, 1);
                    letter.setLetter(interpretado);
                    letter.setConfidence(palabra.get(0).getConfidence());
                }
                final_result.add(letter);
            }else{
                OcrCharacter letter = new OcrCharacter();
                letter.setLetter("%");
                letter.setConfidence(100);
                final_result.add(letter);
            }
        }

        for (OcrCharacter result : final_result) {
            if(result != null){
                java.lang.System.out.println("Letra: " + result.getLetter() + "| Confianza: " + result.getConfidence());
            }
        }
        java.lang.System.out.println("");
        return final_result;
    }
    
    public static Person ocr(JTextArea console,ITesseract instance_num, ITesseract instance_let, String dni, String route_fingerprint){
        ArrayList<Person> personas;
        Person persona;
        ArrayList<OcrCharacter> ocrDni = null; 

        ocrDni = preprocesamiento_ocr(instance_num, dni, 8);

        String queryDni = "";
        for (int i = 0; i < ocrDni.size(); i++) {
            if(i == 0){
                queryDni += "7";
            }else if(ocrDni.get(i).getLetter().equals("0") || ocrDni.get(i).getLetter().equals("%")){
                queryDni += "0";
            }else if(ocrDni.get(i).getLetter().equals("6")){
                queryDni += "6";
            }else if(ocrDni.get(i).getConfidence() > 75){
                queryDni += ocrDni.get(i).getLetter();
            }else{
                queryDni += "_";
            }
        }
        personas = Manager.queryByPerson(queryDni);
        String s  = findPerson(console, personas, route_fingerprint);
        java.lang.System.out.println("Dni encontrado: " + s);
        if(console != null){
            console.append("\nAnalizando: " + s);
            console.update(console.getGraphics());
        }
        /*
        ImagePlus img = new ImagePlus(dni);
        img.show();
        String s = (String)JOptionPane.showInputDialog(frame, "DNI interpretado: "+ queryDni +"\nIngresa DNI:\n", "Input de prueba", JOptionPane.PLAIN_MESSAGE, null, null, "");
        img.close();
        */
                
        //personas = Manager.queryByPerson(ocrDni, ocrName, ocrLastname);
        if (s!=null){
            persona = Manager.queryPersonByDni(s);
        }else{
            persona = null;
        }
        
        return persona;
    }
    
    
    private static String findPerson(JTextArea console, ArrayList<Person> personas, String route_fingerprint) {
        double score, maxscore = 0;
        String dni;
        Person bestChoice = null;
        for (Person persona : personas) {
            
            score = FingerprintLib.huellas_ocr(route_fingerprint, persona.getFingerprint());
            if(score > maxscore){
                maxscore = score;
                bestChoice = persona;
            }
        }
        if(bestChoice == null && personas.isEmpty()){
            dni = null;
        }else if(bestChoice == null && !personas.isEmpty()){
            dni = personas.get(0).getDni();
        }else{
            dni = bestChoice.getDni();
        }
        return dni;
    }
    
    public static void main(String[] args) throws TesseractException, IOException{
        File dll;
        if(java.lang.System.getProperty("os.name").equals("Linux")){
           dll = new File("lib/libopencv_java2412.so");
        }else{
            dll = new File("lib/opencv_java2412.dll");
        }
        
        java.lang.System.load(dll.getAbsolutePath());
        String name = "part.G.original8.6";
        String route_dni = "../cortes/99/" + name + "/dni.jpg";
        String route_fingerprint = "../cortes/99/" + name + "/huella.jpg";
        ArrayList<Person> personas;
        ImagePlus img = new ImagePlus(route_dni);
        img.show();
        //OCR TESSERACT
        ITesseract instance_num = new Tesseract();
        ITesseract instance_let = new Tesseract();
        
        instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        instance_let.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        
        ArrayList<OcrCharacter> ocrDni = preprocesamiento_ocr(instance_num, route_dni, 8);
        
        String queryDni = "";
        for (int i = 0; i < ocrDni.size(); i++) {
            if(i == 0){
                queryDni += "7";
            }else if(ocrDni.get(i).getLetter().equals("0") || ocrDni.get(i).getLetter().equals("%")){
                queryDni += "0";
            }else if(ocrDni.get(i).getConfidence() > 75 && i != 0){
                queryDni += ocrDni.get(i).getLetter();
            }else{
                queryDni += "_";
            }
        }
        
        java.lang.System.out.println(queryDni);
        personas = Manager.queryByPerson(queryDni);
        for (Person person : personas){
            java.lang.System.out.println(person.getDni() + " " + person.getName() + " " + person.getLastname());
        }
        String s  = findPerson(null, personas, route_fingerprint);
        java.lang.System.out.println("Dni encontrado: " + s);
    }

}

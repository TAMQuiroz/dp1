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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
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
            List<Word> palabra = instance.getWords(img, 0);
            if(!palabra.isEmpty()){
                    //String result = instance.doOCR(img);
                    
                    OcrCharacter letter = new OcrCharacter ();
                    letter.setLetter(palabra.get(0).getText().replace(" ", "").replace("\n\r", "").replace("\r\n", "").replace("\n", "").replace("\r", "").trim());
                    letter.setConfidence(palabra.get(0).getConfidence());
                    //java.lang.System.out.println("Caracter: " + letter.getLetter() + " Confianza: " + letter.getConfidence());
                    //java.lang.System.out.print(result + " | ");
                    final_result.add(letter);
                    //final_result += letter.getLetter();
                    //System.out.print(result);
            }else{
                OcrCharacter letter = new OcrCharacter();
                letter.setLetter(" ");
                letter.setConfidence(100);
                final_result.add(letter);
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
    
    public static Person ocr(adherentListi frame,ITesseract instance_num, ITesseract instance_let, String dni, String name, String lastname){
        ArrayList<Person> personas;
        ArrayList<OcrCharacter> ocrDni = null, ocrName, ocrLastname; 

        ocrDni = preprocesamiento_ocr(instance_num, dni, 8);

        //JOptionPane.showMessageDialog(frame, "Error "+ocrDni.get(0).getLetter(), "Alerta", JOptionPane.WARNING_MESSAGE);
        
        String queryDni = "";
        for (int i = 0; i < ocrDni.size(); i++) {
            if(ocrDni.get(i).getConfidence() > 60){
                queryDni += ocrDni.get(i).getLetter();
            }else{
                queryDni += "%";
            }
        }
        
        //ocrName = preprocesamiento_ocr(instance_let, name, 23);
        //ocrLastname = preprocesamiento_ocr(instance_let, lastname, 25);
        
        ImagePlus img = new ImagePlus(dni);
        img.show();
        String s = (String)JOptionPane.showInputDialog(frame, "DNI interpretado: "+ queryDni +"\nIngresa DNI:\n", "Input de prueba", JOptionPane.PLAIN_MESSAGE, null, null, "");
        img.close();
        
        //personas = Manager.queryByPerson(ocrDni, ocrName, ocrLastname);
        Person persona = Manager.queryPersonByDni(s);
        
        return persona;
    }
    
    public static void main(String[] args) throws TesseractException, IOException{
        //String route_dni = "/home/dpclean/NetBeansProjects/cortes/23/part.G.original1.1/dni.jpg";
        String route_dni = "../cortes/23/part.G.original1.1/dni.jpg";
        String route_name = "test/auxiliar/cortes/9/padron17/nombre.jpg";
        String route_lastname = "test/auxiliar/cortes/9/padron17/apellido.jpg";
        
        if(java.lang.System.getProperty("os.name").equals("Linux")){
            java.lang.System.loadLibrary("tess");
        }
        
        //OCR TESSERACT
        ITesseract instance_num = new Tesseract();
        ITesseract instance_let = new Tesseract();
        
        instance_num.setTessVariable("tessedit_char_whitelist", "0123456789");
        instance_let.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        
        BufferedImage img = ImageIO.read(new File(route_dni));
        List<Word> palabra = instance_num.getWords(img, 0);
        java.lang.System.out.println(palabra.get(0).getText());
        /*
        adherentListi frame = new adherentListi();
        Person persona = ocr(frame,instance_num, instance_let, route_dni, route_name, route_lastname);
        if(persona == null){
            java.lang.System.out.println("No se encontro la persona");
        }else{
            java.lang.System.out.println("Nombre: " + persona.getName() + " Apellido: " + persona.getLastname());
        }
        frame.dispose();
        */
    }
}

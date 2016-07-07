/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.AdherentImage;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Line;
import ij.io.FileSaver;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author tamqu
 */
public class UploadLib {
    
    static JTextArea console;
    static JProgressBar status;
    static int idParty;
    static String routeToCortes = "../cortes/";
    static String routeToPadrones = "../padrones/";
    static String outputRoute;
    static int count;
    static ArrayList<AdherentImage> adherents;
    
    public static ImagePlus cutDown(ImagePlus imgOrigen){
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
    
    public static ImagePlus cutLaterals(ImagePlus imgOrigen){
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
    
    public static ImagePlus cutLateralDer(ImagePlus imgOrigen){
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
    
    public static void cutColumns(String route, String n_img, int n, ImagePlus imgOrigen, String extension, int[] index){
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 9 y
        AdherentImage adherent = new AdherentImage();        
        
        //cortar dni
        int aux = index[1] - index[0];
        imgOrigen.setRoi(index[0],0,aux,imgOrigen.getHeight());
        ImagePlus imp = new Duplicator().run(imgOrigen);
        FileSaver fs = new FileSaver(imp);
        String n_out = outputRoute + n_img + n + "/dni." + extension;
        fs.saveAsPng(n_out);
        adherent.setDniSource(n_out);
        //java.lang.System.out.println("Cortando dni en: " + index[0] + " y " + index[1]);
        
        //cortar apellido
        aux = index[3] - index[2];
        imgOrigen.setRoi(index[2],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/apellido." + extension;
        fs.saveAsPng(n_out);
        adherent.setLastNameSource(n_out);
        //java.lang.System.out.println("Cortando apellido en: " + index[2] + " y " + index[3]);
        
        //cortar nombre
        aux = index[4] - index[3];
        imgOrigen.setRoi(index[3],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/nombre." + extension;
        fs.saveAsPng(n_out);
        adherent.setNameSource(n_out);
        //java.lang.System.out.println("Cortando nombre en: " + index[3] + " y " + index[4]);
        
        //cortar firma
        aux = index[6] - index[5];
        imgOrigen.setRoi(index[5],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/firma." + extension;
        fs.saveAsPng(n_out);
        adherent.setSignatureSource(n_out);
        //java.lang.System.out.println("Cortando firma en: " + index[5] + " y " + index[6]);
        
        //cortar huella
        aux = index[8] - index[7];
        imgOrigen.setRoi(index[7],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/huella." + extension;
        fs.saveAsPng(n_out);
        adherent.setFingerprintSource(n_out);
        //java.lang.System.out.println("Cortando huella en: " + index[7] + " y " + index[8]);
        
        java.lang.System.out.println("Guardando en base de datos");
        adherent.setPoliticalParty(idParty);
        adherent.setStatus(0);
        adherents.add(adherent);
        
    }
    
    public static ImagePlus cutRows (int n, ImagePlus imgOrigen, String route, String n_img, String extension, int[] index){
        int x, y, rowSize;
        double r;
        
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        //java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        x = 15;
        y = imgPlus.getHeight() - 10;
        r = imgPlus.getPixel(x,y)[0];
        
        
        while (r == 0){
            y = y - 1;
            r = imgPlus.getPixel(x,y)[0];
        }
        
        rowSize = imgPlus.getHeight() - y;
        
        //Copiar imagen
        imgOrigen.setRoi(0,0, imgOrigen.getWidth(), y);
        ImagePlus img = new Duplicator().run(imgOrigen);
        
        //Cortar Filas
        imgOrigen.setRoi(0, y, imgOrigen.getWidth(), rowSize);
        if(console != null){
            console.append("\nCortando fila " + n);
            console.update(console.getGraphics());
        }
        IJ.run(imgOrigen, "Crop", "");
        
        //Cortar Caracteres de fila
        cutColumns(route, n_img, n, imgOrigen, extension, index);
        
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
    
    public static int[] forwardNumber(ImagePlus imgPlus, int[] index){
        int x = 15;
        int y = 0;

        y = bajar(imgPlus, x, y, 255); //Negro
        y = bajar(imgPlus, x, y, 0); //Blanco
        //java.lang.System.out.println("y: " + y);
        y = bajar(imgPlus, x, y, 255); //Negro
        //java.lang.System.out.println("y: " + y);
        y = y + 2;
        index[9] = y;
        //java.lang.System.out.println("y: " + y);
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
    
    public static int[] forwardDni(ImagePlus imgPlus, int[] index){
        int x = index[0];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[1] = x;
        x = derecha(imgPlus, x, y, 255); //Negro
        index[2] = x;
        
        return index;
    }
    
    public static int[] forwardLastname(ImagePlus imgPlus, int[] index){
        int x = index[2];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[4] = x;
        //java.lang.System.out.println("x:" + x + " y: " + y);
        x = derecha(imgPlus, x, y, 255); //Negro
        index[5] = x;
        
        return index;
    }
    
    public static int[] forwardNames(ImagePlus imgPlus, int[] index){
        double porcentajeApellidos = 52.083333;
        int longitud = index[4] - index[2];
        int longitudApellido = (int) (longitud*porcentajeApellidos/100);
        int x = index[2] + longitudApellido;
        index[3] = x;
        //java.lang.System.out.println(longitudApellido);
        //java.lang.System.exit(0);
        return index;
    }
    
    public static int[] forwardSignature(ImagePlus imgPlus, int[] index){
        int x = index[5];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[6] = x;
        x = derecha(imgPlus, x, y, 255); //Negro
        index[7] = x;
        
        return index;
    }
    
    public static int[] forwardFingerprint(ImagePlus imgPlus, int[] index){
        int x = index[7];
        int y = index[9];

        x = derecha(imgPlus, x, y, 0); //Blanco
        index[8] = x;
        
        return index;
    }
        
    public static int[] countHeader(ImagePlus imgOrigen){
        int[] index = new int[10];
        
        //Duplicando imagen y binarizandola
        ImagePlus imgPlus = new Duplicator().run(imgOrigen);
        java.lang.System.out.println("Binarizando");
        IJ.run(imgPlus, "Make Binary", "");
        
        //Girar imagen
        imgPlus = rotateImage(imgPlus);
        
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 9 y
        java.lang.System.out.println("Saltando numero");
        index = forwardNumber(imgPlus, index);
        //Contar dni
        java.lang.System.out.println("Contando dni");
        index = forwardDni(imgPlus, index);
        //Contar apellidos
        java.lang.System.out.println("Contando apellidos");
        index = forwardLastname(imgPlus, index);
        //Contar nombres
        java.lang.System.out.println("Contando nombres");
        index = forwardNames(imgPlus, index);
        //Contar firma
        java.lang.System.out.println("Contando firmas");
        index = forwardSignature(imgPlus, index);
        //Contar huella
        java.lang.System.out.println("Contando huellas");
        index = forwardFingerprint(imgPlus, index);
        
        return index;
    }
    
    public static ImagePlus rotateImage(ImagePlus imgOrigen){
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
        //java.lang.System.out.println("Angulo: " + angle);
        ImageProcessor img = imgOrigen.getProcessor();
        img.setBackgroundValue(255);
        img.rotate(angle);
        ImagePlus img_rotated = new ImagePlus("rotated", img);
        
        
        FileSaver fs = new FileSaver(img_rotated);
        String n_out = outputRoute + "test.jpg";
        //fs.saveAsPng(n_out);
        
        return img_rotated;
    }
    
    public static void cutBoxes(String route, String n_img, String extension){     
        
        //Abriendo imagen
        String inFile = route + n_img + extension;
        if(console != null){
            console.append("\n=======Abriendo imagen " + n_img + extension + " para binarizar=======");
        }
        java.lang.System.out.println(inFile);
        ImagePlus imgOrigen = new ImagePlus(inFile);
        ImageProcessor img = imgOrigen.getProcessor();
        
        //Agrandando imagen
        img = img.resize(3500);
        ImagePlus imgResize = new ImagePlus("resized",img);

        //Cortar bordes
        imgOrigen = cutLaterals(imgResize);
        imgOrigen = cutDown(imgResize);
        imgOrigen = cutLateralDer(imgResize);
        
        //Contar pixeles
        if(console != null){
            console.append("\n=======Contando cabecera=======");
        }
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 
        int[] index = countHeader(imgResize);

        //Cortar filas
        if(console != null){
            console.append("\n=======Cortando filas de imagen=======");
        }
        for(int n = 1; n < 9 ; n++){
            File directory = new File(outputRoute + n_img + n); 
            directory.mkdir(); 
            imgResize = cutRows(n,imgResize, route, n_img, extension, index);
        }
    }
    
    public static void imprimeLista(){
        for (int i = 0; i < adherents.size(); i++){
            java.lang.System.out.println(adherents.get(i).getDniSource());
        }
    }
    
    public static void cargarPadrones(String route, int id) throws IOException{
        
        adherents = new ArrayList<>();
        idParty = id;
        outputRoute = routeToCortes + id + "/";
        count = 0;
        status.setValue(0);
        String singleName = "";
        File folder = new File(route);
        File[] listOfFiles = folder.listFiles();
        int cantidad = listOfFiles.length;
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                console.append("\n======Analizando " + file.getName() + "======");
                console.update(console.getGraphics());
                java.lang.System.out.println("======Analizando " + file.getName() + "======");
                String[] name = file.getName().split("[.]");
                singleName = "";
                for(int i = 0; i < name.length - 1; i++) singleName = singleName + name[i] + ".";
                if(name[name.length-1].equals("jpg")){
                    File directory = new File(outputRoute);
                    directory.mkdir();                    
                    cutBoxes(route,singleName, name[name.length-1]);
                    console.append("\n======Fin de analisis " + file.getName() + "======");
                    console.update(console.getGraphics());
                    java.lang.System.out.println("======Fin de analisis " + file.getName() + "======");
                }else{
                    console.append("\n======Error en " + file.getName() + ", no es una imagen JPG======");
                    console.update(console.getGraphics());
                    java.lang.System.out.println("======Error en " + file.getName() + ", no es una imagen JPG======");
                }
            }
            count++;
            int percentage = (100*count)/cantidad;
            java.lang.System.out.println("Porcentaje: " + percentage);
            //WIP Subir a bd
            //
            //imprimeLista();
            status.setValue(percentage);
            status.update(status.getGraphics());
        }
        Manager.addAdherentImages(adherents);
        //Actualizar trabajador asignado a partido politico
        Manager.setWorker(idParty, Manager.getSession().getId());
        
        status.setValue(100);
    }
    
    public static void main(String[] args){
        
        adherents = new ArrayList<>();
        idParty = 99;
        outputRoute = routeToCortes + idParty + "/";
        count = 0;
        cutBoxes("../padrones/","part.E.original9.", "jpg");
    }
}

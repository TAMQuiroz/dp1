/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.AdherentImage;
import Model.PoliticalParty;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Line;
import ij.io.FileSaver;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author tamqu
 */
public class UploadLib {
    
    static JTextArea console;
    static JProgressBar status;
    static int idPartido;
    static String routeToCortes = "../cortes/";
    static String routeToPadrones = "../padrones/";
    static String outputRoute;
    static int count;
    static ArrayList<AdherentImage> adherentes;
    
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
        AdherentImage adherent = new AdherentImage();        
        
        //cortar dni
        int aux = index[1] - index[0];
        imgOrigen.setRoi(index[0],0,aux,imgOrigen.getHeight());
        ImagePlus imp = new Duplicator().run(imgOrigen);
        FileSaver fs = new FileSaver(imp);
        String n_out = outputRoute + n_img + n + "/dni" + extension;
        fs.saveAsPng(n_out);
        //adherent.setNameSource(n_out.replace("\\", "/"));
        adherent.setDniSource(n_out);
        //java.lang.System.out.println("Cortando dni en: " + index[0] + " y " + index[1]);
        
        //cortar apellido
        aux = index[3] - index[2];
        imgOrigen.setRoi(index[2],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/apellido" + extension;
        fs.saveAsPng(n_out);
        //adherent.setNameSource(n_out.replace("\\", "/"));
        adherent.setLastNameSource(n_out);
        //java.lang.System.out.println("Cortando apellido en: " + index[2] + " y " + index[3]);
        
        //cortar nombre
        aux = index[4] - index[3];
        imgOrigen.setRoi(index[3],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/nombre" + extension;
        fs.saveAsPng(n_out);
        //adherent.setNameSource(n_out.replace("\\", "/"));
        adherent.setNameSource(n_out);
        //java.lang.System.out.println("Cortando nombre en: " + index[3] + " y " + index[4]);
        
        //cortar firma
        aux = index[6] - index[5];
        imgOrigen.setRoi(index[5],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/firma" + extension;
        fs.saveAsPng(n_out);
        //adherent.setNameSource(n_out.replace("\\", "/"));
        adherent.setSignatureSource(n_out);
        //java.lang.System.out.println("Cortando firma en: " + index[5] + " y " + index[6]);
        
        //cortar huella
        aux = index[8] - index[7];
        imgOrigen.setRoi(index[7],0,aux,imgOrigen.getHeight());
        imp = new Duplicator().run(imgOrigen);
        fs = new FileSaver(imp);
        n_out = outputRoute + n_img + n + "/huella" + extension;
        fs.saveAsPng(n_out);
        //adherent.setNameSource(n_out.replace("\\", "/"));
        adherent.setFingerprintSource(n_out);
        //java.lang.System.out.println("Cortando huella en: " + index[7] + " y " + index[8]);
        
        java.lang.System.out.println("Guardando en base de datos");
        //PoliticalParty p = Manager.queryPoliticalPartyById(idPartido);
        adherent.setPoliticalParty(idPartido);
        adherent.setStatus(0);
        adherentes.add(adherent);
        
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
        console.append("\nCortando fila " + n);
        console.update(console.getGraphics());
        java.lang.System.out.println("Cortando fila " + n);
        IJ.run(imgOrigen, "Crop", "");
        
        /*
        FileSaver fs = new FileSaver(imgOrigen);
        String n_out = outputRoute + n_img + n + "\\" + n_img + "_row_" + n + extension;
        java.lang.System.out.println("=======Guardando imagen en " + n_out + "=======");
        fs.saveAsJpeg(n_out);
        */
        
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
        //java.lang.System.out.println("y: " + y);
        y = bajar(imgPlus, x, y, 255); //Negro
        //java.lang.System.out.println("y: " + y);
        y = y + 2;
        index[9] = y; //Control rotacion
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
    
    public static void cortarCajas(String route, String n_img, String extension){     
        
        String inFile = route + n_img + extension;
        
        console.append("\n=======Abriendo imagen " + n_img + extension + " para binarizar=======");
        java.lang.System.out.println("=======Abriendo imagen " + n_img + extension + " para binarizar=======");
        //java.lang.System.out.println("Path: " + inFile);
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
        String n_out = outputRoute + n_img + "_crop" + extension;
        //fs.saveAsPng(n_out);
        
        
        //Cortar
        console.append("\n=======Contando cabecera=======");
        java.lang.System.out.println("=======Contando cabecera=======");
        //| 0 DNI 1 | 2 Apellidos 3 Nombres 4 | 5 Firma 6 | 7 Huella 8 | 
        int[] index = contarCabecera(imgResize);
        console.append("\n=======Cortando filas de imagen=======");
        java.lang.System.out.println("=======Cortando filas de imagen=======");
        for(int n = 1; n < 9 ; n++){
            File directorio = new File(outputRoute + n_img + n); 
            directorio.mkdir(); 
            imgResize = cortarFilas(n,imgResize, route, n_img, extension, index);
        }
    }
    
    public static void imprimeLista(){
        for (int i = 0; i < adherentes.size(); i++){
            java.lang.System.out.println(adherentes.get(i).getDniSource());
        }
    }
    
    public static void cargarPadrones(String route, int id) throws IOException{
        
        adherentes = new ArrayList<>();
        idPartido = id;
        outputRoute = routeToCortes + id + "/";
        
        count = 0;
        status.setValue(0);
        
        File folder = new File(route);
        File[] listOfFiles = folder.listFiles();
        int cantidad = listOfFiles.length;
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                console.append("\n======Analizando " + file.getName() + "======");
                console.update(console.getGraphics());
                java.lang.System.out.println("======Analizando " + file.getName() + "======");
                String[] name = file.getName().split("[.]");
                if(name[1].equals("jpg")){
                    File directorio = new File(outputRoute);
                    directorio.mkdir();                    
                    cortarCajas(route,name[0],"." + name[1]);
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
            int porcentaje = (100*count)/cantidad;
            java.lang.System.out.println("Porcentaje: " + porcentaje);
            //WIP Subir a bd
            Manager.addAdherentImages(adherentes);
            //imprimeLista();
            status.setValue(porcentaje);
            status.update(status.getGraphics());
        }
        
        //Actualizar trabajador asignado a partido politico
        Manager.setWorker(idPartido, Manager.getSession().getId());
        
        status.setValue(100);
    }
}

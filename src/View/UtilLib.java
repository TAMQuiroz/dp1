/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author erickelme
 */
public class UtilLib {
    public static boolean isSuitable(Person person, long electoralProcessId){
        boolean value = Manager.queryUbigeoByIdAndElectoralProcess(person.getUbigeo(), electoralProcessId);        
        return value;
    }
    public static long findDuplicity(Person person, long electoralProcessId){        
        long politicalPartyId = Manager.queryPersonByDniAndElectoralProcess(person.getDni(), electoralProcessId);
        return politicalPartyId;
    }
    
    public static void deleteImages(AdherentImage adImage){
        File miDir = new File (".");
        String localPath = "";        
        String path = "";   
        String fs = File.separator;
        String string = adImage.getNameSource();
        String[] parts = string.split("\\\\");        
        try {
           //localPath = miDir.getAbsolutePath();
           localPath = miDir.getCanonicalPath();
          }
        catch(Exception e) {
          e.printStackTrace();
        }
        String[] partsProyect = localPath.split("/");
        path = partsProyect[0];
        for ( int j = 1; j < partsProyect.length; j++){
            path = path + fs + partsProyect[j];
        }        
        for ( int i = 0; i < parts.length - 1 ; i++){
            path = path + fs + parts[i];
        }
        java.lang.System.out.println("El directorio " + path);
        File f = new File(path);                         
        deleteDirectory(f); 
        if (f.delete())
            java.lang.System.out.println("El directorio " + path + " ha sido borrado correctamente");
        else
            java.lang.System.out.println("El directorio " + path + " no se ha podido borrar");                
    }
    
    private static void deleteDirectory (File directorio){
        File[] ficheros = directorio.listFiles(); 
        java.lang.System.out.println("Cantida de archivos " + ficheros.length);                        
        for (int x=0;x<ficheros.length;x++){
            if (ficheros[x].isDirectory()) {
                deleteDirectory(ficheros[x]);
            }
            ficheros[x].delete();
        }
        
    }
    
}

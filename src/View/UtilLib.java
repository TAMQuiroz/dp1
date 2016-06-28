/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author erickelme
 */
public class UtilLib {
    public static boolean isSuitable(Person person, long electoralProcessId){
        boolean value = Manager.queryUbigeoByIdAndElectoralProcess(person.getUbigeo(), electoralProcessId);        
        value = value && !(person.isDisabled());
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
        String[] parts = string.split("/");        
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
        java.lang.System.out.println("Cantidad de archivos " + ficheros.length);                        
        for (int x=0;x<ficheros.length;x++){
            if (ficheros[x].isDirectory()) {
                deleteDirectory(ficheros[x]);
            }
            ficheros[x].delete();
            java.lang.System.out.println(ficheros[x].getName());
        }
        
    }
    
    public static void checkStageAllElectoralProcess(){
        ArrayList<ElectoralProcess> listEP = new ArrayList<ElectoralProcess>();
        listEP = Manager.queryAllElectoralProcess();
        int size = listEP.size();
        int stage = -1;
        for (int i = 0 ; i < size ; i++){
            stage = checkStageForElectoralProcess(listEP.get(i));                        
            listEP.get(i).setStage(stage);
            if (stage == 4)
                validatePolitialParties(listEP.get(i));            
        }
        Manager.setProcessListStage(listEP);
    }
    private static int checkStageForElectoralProcess(ElectoralProcess pt){        
        Date now = new Date(); 
        int isLowerSV = pt.getStartRegistrationDate().compareTo(now);       //Debe ser 0 o mas
        int isHigherSV = pt.getEndRegistrationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 5;
        isLowerSV = pt.getStartReceptionDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getEndReceptionDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 0;
        isLowerSV = pt.getStartValidationDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getEndValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 1;
        isLowerSV = pt.getStartExtraReceptionDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getEndExtraReceptionDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 2;
        isLowerSV = pt.getStartExtraValidationDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getEndExtraValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 3;
        isHigherSV = pt.getEndExtraValidationDate().compareTo(now);        //Debe ser 0 o mas
        if ( isHigherSV < 0)
            return 4;
        return -1;
    }
    
    public static int checkStage( long electoralPartyId){        
        
        //La fase se representa con números: 
        // -1: otra etapa; 0 : primera etapa de recepción; 1: primera etapa de validadción
        // 2: segunda etapa extra recepción; 3: segunda etapa extra validación
        // 4: ya se pasó la segunda etapa de validación; 5: etapa de inscripcion
        PoliticalParty pt = Manager.queryPoliticalPartyById(electoralPartyId);               
        Date now = new Date(); 
        int isLowerSV = pt.getElectoralProcess().getStartRegistrationDate().compareTo(now);       //Debe ser 0 o mas
        int isHigherSV = pt.getElectoralProcess().getEndRegistrationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 5;
        isLowerSV = pt.getElectoralProcess().getStartReceptionDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getElectoralProcess().getEndReceptionDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 0;
        isLowerSV = pt.getElectoralProcess().getStartValidationDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getElectoralProcess().getEndValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 1;
        isLowerSV = pt.getElectoralProcess().getStartExtraReceptionDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getElectoralProcess().getEndExtraReceptionDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 2;
        isLowerSV = pt.getElectoralProcess().getStartExtraValidationDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getElectoralProcess().getEndExtraValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            return 3;
        isHigherSV = pt.getElectoralProcess().getEndExtraValidationDate().compareTo(now);        //Debe ser 0 o mas
        if ( isHigherSV < 0)
            return 4;
        return -1;
    }

    static boolean analyze_result(double puntuacion1, double puntuacion2) {
        double prom = (puntuacion1 + puntuacion2) / 2;
        java.lang.System.out.println("Promedio: " + prom);
        if(prom > 70){
            java.lang.System.out.println("Es validado");
            return true;
        }
        java.lang.System.out.println("No es validado");
        return false;
    }
    
    public static void validatePolitialParties(ElectoralProcess ep){        
        int minAdeherents = (int) Math.rint(ep.getPopulation()*ep.getMinPercentage());        
        ArrayList<PoliticalParty> partyList = new ArrayList<PoliticalParty>();
        ArrayList<Adherent> adherentList = new ArrayList<Adherent>();
        partyList = Manager.queryAllPoliticalParties(ep.getId());
        int sizePL = partyList.size();
        int adherentAmount = 0;
        for ( int i = 0; i<sizePL; i++){
            adherentList = Manager.queryAllAdherents(partyList.get(i).getId());
            adherentAmount = adherentList.size();
            if (adherentAmount >= minAdeherents)
                partyList.get(i).setStatus("Validado");                                            
            else
                partyList.get(i).setStatus("Rechazado");                                                    
        }
        Manager.updatePoliticalPartyList(partyList);
    }
    
    
    public static void main(String[] args){
        int stage = checkStage(12);
        java.lang.System.out.println(stage);        
        checkStageAllElectoralProcess();
//        validatePolitialParties(28);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import BusinessModel.Manager;
import Model.*;
import java.util.ArrayList;
import java.util.Date;
import View.Login;
import View.UtilLib;
import java.text.SimpleDateFormat;

/**
 *
 * @author erickelme
 */
public class App {
    public static void main(String [] args) {
        /*
        ElectoralProcess ep = new ElectoralProcess();        
        User u = new User(); ProcessType pt = new ProcessType();
        ArrayList<ElectoralProcess> list = new ArrayList<ElectoralProcess>();
        list = Manager.queryAllElectoralProcess();
        u = Manager.login("71844756", "eeee");        
        long idpt = 4;
        pt = Manager.queryProcessTypeById(idpt);
        ep.setProcessType(pt);
        ep.setName("Elecciones Distritales 2016");
        ep.setDate(new Date());
        ep.setStartExtraReceptionDate(new Date());
        ep.setStartExtraValidationDate(new Date());
        ep.setStartReceptionDate(new Date());
        ep.setStartRegistrationDate(new Date());
        ep.setStartValidationDate(new Date());
        ep.setEndExtraReceptionDate(new Date());
        ep.setEndExtraValidationDate(new Date());
        ep.setEndReceptionDate(new Date());
        ep.setEndRegistrationDate(new Date());
        ep.setEndValidationDate(new Date());
        ep.setPopulation(2900000);
        ep.setStatus("Activo");
        ep.setUser(u);        
        //long id = 1;
        //ep.setId(id);
        Manager.addElectoralProcess(ep);
        //Manager.updateElectoralProcess(ep);
        //System.out.println("Proceso Electoral actualizado!!");
        //Manager.deleteElectoralProcess(id);
        //System.out.println("Proceso Electoral dado de baja!!");
        //System.out.println("El proceso 1 es: " + list.get(0).getName());
        //System.out.println("El proceso 1 es: " + Manager.queryElectoralProcessById(id).getName());
        System.out.println("El usuario es: " + ep.getUser().getName() + " - " + ep.getUser().getLastName() );
        System.out.println("Tiene el perfil de: " + u.getProfile().getName());
        new Login().setVisible(true);
        */
        /*
        long idAd = 10;
        AdherentImage ad = Manager.queryAdherentImageById(idAd);
        UtilLib.deleteImages(ad);        
        */
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String sDate= sdf.format(date);
        /*
        System.out.println("La fecha es: " + sDate);
        long idAd = 4;
        PoliticalParty pt = Manager.queryPoliticalPartyById(idAd);               
        Date now = new Date();
        int isLowerSV = pt.getElectoralProcess().getStartValidationDate().compareTo(now);       //Debe ser 0 o mas
        int isHigherSV = pt.getElectoralProcess().getEndValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            System.out.println("La etapa es primera validación");
        isLowerSV = pt.getElectoralProcess().getStartExtraValidationDate().compareTo(now);       //Debe ser 0 o mas
        isHigherSV = pt.getElectoralProcess().getEndExtraValidationDate().compareTo(now);        //Debe ser negativo
        if ( isLowerSV <= 0 && isHigherSV >= 0)
            System.out.println("La etapa es segunda validación");
        System.out.println("La etapa es cualquiera");
        */
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import BusinessModel.Manager;
import Model.*;
import java.util.Date;

/**
 *
 * @author erickelme
 */
public class App {
    public static void main(String [] args) {
        ElectoralProcess ep = new ElectoralProcess();
        ep.setName("Elecciones Presidenciales 2016");
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
        ep.setMinPercentage(3.5);
        ep.setPopulation(1900000);
        ep.setStatus("Activo");
        
        Manager.addElectoralProcess(ep);
        System.out.println("Proceso Electoral guardado!!");
    }
}

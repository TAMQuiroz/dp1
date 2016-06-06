/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BusinessModel.Manager;
import Model.*;

/**
 *
 * @author erickelme
 */
public class UtilLib {
    public static boolean isSuitable(Person person, long electoralProcessId){
        boolean value = Manager.queryUbigeoByIdAndElectoralProcess(person.getUbigeo(), electoralProcessId);        
        return value;
    }
}

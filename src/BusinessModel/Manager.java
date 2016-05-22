/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;
import Model.*;
import java.util.ArrayList;
/**
 *
 * @author erickelme
 */
public class Manager {
    private static ElectoralProcessDB electoralProcessDB = new ElectoralProcessDB();
    
    
    
    public static void addElectoralProcess(ElectoralProcess ep){
        electoralProcessDB.add(ep);
    }
    public static void updateElectoralProcess(ElectoralProcess ep){
        electoralProcessDB.update(ep);
    }
    public static void deleteElectoralProcess(int electoralProcessId){
        electoralProcessDB.delete(electoralProcessId);
    }
    public static ArrayList<ElectoralProcess> queryAllElectoralProcess(int electoralProcessId){
        return electoralProcessDB.queryAll();
    }
    public static ElectoralProcess queryElectoralProcessById(int electoralProcessId){
        return electoralProcessDB.queryById(electoralProcessId);
    }
    
}

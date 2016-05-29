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
    private static ProfileDB profileDB = new ProfileDB();
    private static UserDB userDB = new UserDB();
    private static ProcessTypeDB processTypeDB = new ProcessTypeDB();
    
    public static void addElectoralProcess(ElectoralProcess ep){
        electoralProcessDB.add(ep);
    }
    public static void updateElectoralProcess(ElectoralProcess ep){
        electoralProcessDB.update(ep);
    }
    public static void deleteElectoralProcess(long electoralProcessId){
        electoralProcessDB.delete(electoralProcessId);
    }
    public static ArrayList<ProcessType> queryAllProcessTypes(){
        return processTypeDB.queryAll();
    }
    public static ProcessType queryProcessTypeById(long processTypeId){
        return processTypeDB.queryById(processTypeId);
    }  
    public static ArrayList<ElectoralProcess> queryAllElectoralProcess(){
        return electoralProcessDB.queryAll();
    }
    public static ElectoralProcess queryElectoralProcessById(long electoralProcessId){
        return electoralProcessDB.queryById(electoralProcessId);
    }    
    public static ArrayList<Profile> queryAllProfiles(){
        return profileDB.queryAll();
    }
    public static Profile queryProfileById(long profileId){
        return profileDB.queryById(profileId);
    }    
    public static User login(String user, String password){
        return userDB.login(user, password);
    }
    public static User queryUserById(long userId){
        return userDB.queryById(userId);
    }
}

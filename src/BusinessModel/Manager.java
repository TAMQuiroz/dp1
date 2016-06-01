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
    private static UbigeoDB ubigeoDB = new UbigeoDB();
    private static PoliticalPartyDB politicalPartyDB = new PoliticalPartyDB();
    private static AdherentImageDB adherentImageDB = new AdherentImageDB(); 
    
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
    public static void addUser(User ep){
        userDB.add(ep);
    }
    public static void updateUser(User ep){
        userDB.update(ep);
    }
    public static void deleteUser(long userId){
        userDB.delete(userId);
    }
    public static ArrayList<User> queryAllUsers(){
        return userDB.queryAll();
    }
    public static User queryUserById(long userId){
        return userDB.queryById(userId);
    }
    public static void addUbigeo(Ubigeo ep){
        ubigeoDB.add(ep);
    }
    public static void updateUbigeo(Ubigeo ep){
        ubigeoDB.update(ep);
    }
    public static void deleteUbigeo(long ubigeoId){
        ubigeoDB.delete(ubigeoId);
    }
    public static ArrayList<Ubigeo> queryAllUbigeos(){
        return ubigeoDB.queryAll();
    }
    public static Ubigeo queryUbigeoById(long ubigeoId){
        return ubigeoDB.queryById(ubigeoId);
    }
    public static void addPoliticalParty(PoliticalParty ep){
        politicalPartyDB.add(ep);
    }
    public static void updatePoliticalParty(PoliticalParty ep){
        politicalPartyDB.update(ep);
    }
    public static void deletePoliticalParty(long politicalPartyId){
        politicalPartyDB.delete(politicalPartyId);
    }
    public static ArrayList<PoliticalParty> queryAllPoliticalParties(long idElectoralProcess){
        return politicalPartyDB.queryAll(idElectoralProcess);
    }
    public static PoliticalParty queryPoliticalPartyById(long politicalPartyId){
        return politicalPartyDB.queryById(politicalPartyId);
    } 
    public static void addAdherentImage(AdherentImage ep){
        adherentImageDB.add(ep);
    }
    public static void updateAdherentImage(AdherentImage ep){
        adherentImageDB.update(ep);
    }
    public static void deleteAdherentImage(long adherentImageId){
        adherentImageDB.delete(adherentImageId);
    }
    public static ArrayList<AdherentImage> queryAllAdherentImages(){
        return adherentImageDB.queryAll();
    }
    public static AdherentImage queryAdherentImageById(long adherentImageId){
        return adherentImageDB.queryById(adherentImageId);
    }
    
}

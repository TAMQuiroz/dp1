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
    private static AdherentDB adherentDB = new AdherentDB();
    private static PersonDB personDB = new PersonDB();
    private static User session;
    
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
    public static boolean queryUbigeoByIdAndElectoralProcess(String ubigeoId, long electoralProcessId){
        Ubigeo ub = ubigeoDB.queryByIdAndElectoralProcess(ubigeoId, electoralProcessId);
        if (ub == null)
            return false;
        return true;
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
    
    public static void setWorker(long politicalPartyId, long workerId){
        politicalPartyDB.setWorker(politicalPartyId, workerId);
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
    public static ArrayList<AdherentImage> queryAllAdherentImages( long politicalPartyIdP){
        return adherentImageDB.queryAll( politicalPartyIdP);
    }
    public static ArrayList<AdherentImage> queryAllAdherentImagesRejected( long politicalPartyIdP){
        return adherentImageDB.queryAllRejected( politicalPartyIdP);
    }
    public static ArrayList<AdherentImage> queryAllAdherentImagesCanceled( long politicalPartyIdP){
        return adherentImageDB.queryAllCanceled( politicalPartyIdP);
    }
    public static AdherentImage queryAdherentImageById(long adherentImageId){
        return adherentImageDB.queryById(adherentImageId);
    }
    
    public static void addAdherent(Adherent ep){
        adherentDB.add(ep);
    }
    public static void updateStatusAdherent(Adherent ep){
        adherentDB.updateStatus(ep);
    }    
    public static ArrayList<Adherent> queryAllAdherents( long politicalPartyIdP){
        return adherentDB.queryAll( politicalPartyIdP);
    }
    public static ArrayList<Adherent> queryAllAdherentsDuplicated( long politicalPartyIdP){
        return adherentDB.queryAllDulpicated(  politicalPartyIdP);
    }
    public static long queryPersonByDniAndElectoralProcess(String dni, long electoralProcess){
        ArrayList<Adherent> list = adherentDB.queryByDni(dni);
        ArrayList<PoliticalParty> listPT = politicalPartyDB.queryAll(electoralProcess);
        int size = list.size();
        int sizePT = listPT.size();
        for (int i = 0; i < size; i++){            
            for (int j = 0; j<sizePT; j++){
                if (list.get(i).getPoliticalParty().getId() == listPT.get(j).getId())
                    return list.get(i).getPoliticalParty().getId();
            }
                    
        }
        return -1;        
    }
    public static Adherent queryAdherentByDniAndPoliticalParty(String dniP, long politicalPartyIdP){
        return adherentDB.queryByDniAndPoliticalParty(dniP, politicalPartyIdP);
    }
    
    public static Adherent queryAdherentById(long adherentId){
        return adherentDB.queryById(adherentId);
    }

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
        Manager.session = session;
    }

    public static ArrayList<AdherentImage> queryAdherentImageNoValidatedbyPartyId(long id) {
        return adherentImageDB.queryAdherentImageNoValidatedbyPartyId(id);
    }

    public static void addAdherentImages(ArrayList<AdherentImage> adherentes) {
        adherentImageDB.addAdherentImages(adherentes);
    }
    public static Person queryPersonByDni(String dni){
        return personDB.queryByDni(dni);
    }
    public static ArrayList<Person> queryByPerson(ArrayList<OcrCharacter> ocrDni, ArrayList<OcrCharacter> ocrName, ArrayList<OcrCharacter> ocrLastname){
        return personDB.queryByPerson(ocrDni, ocrName, ocrLastname);
    }

    public static int queryAmountAdherentImageNoValidatedbyPartyId(long id) {
        return adherentImageDB.queryAmountAdherentImageNoValidatedbyPartyId(id);
    }
            
}

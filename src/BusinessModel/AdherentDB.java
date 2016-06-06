/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOAdherent;
import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DBConnection;
import Model.*;
import java.util.ArrayList;
/**
 *
 * @author erickelme
 */
public class AdherentDB {
    private ArrayList<Adherent> adherentList = new ArrayList<Adherent>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOAdherent daoAdherent = daoFactory.getDAOAdherent();
    
    public AdherentDB(){
        
    }
    
    public void add(Adherent ep){
        daoAdherent.add(ep);
    }
    public void updateStatus(Adherent ep){        
        daoAdherent.updateStatus(ep);        
    }
    
    public ArrayList<Adherent> queryAll(){
        adherentList = daoAdherent.queryAll();
        return adherentList;
    }
    public Adherent queryById( long adherentId){
        return daoAdherent.queryById(adherentId);
    }

    public ArrayList<Adherent> queryByDni(String dni) {
        return daoAdherent.queryByDni(dni);
    }
    
    public Adherent queryByDniAndPoliticalParty(String dniP, long politicalPartyIdP){
        return daoAdherent.queryByDniAndPoliticalParty(dniP, politicalPartyIdP);
    }
    
}

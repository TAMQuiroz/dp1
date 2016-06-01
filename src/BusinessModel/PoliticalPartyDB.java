/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOPoliticalParty;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class PoliticalPartyDB {
    private ArrayList<PoliticalParty> politicalPartyList = new ArrayList<PoliticalParty>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOPoliticalParty daoPoliticalParty = daoFactory.getDAOPoliticalParty();
    
    public PoliticalPartyDB(){
        
    }
    
    public void add(PoliticalParty ep){
        daoPoliticalParty.add(ep);
    }
    public void update(PoliticalParty ep){        
        daoPoliticalParty.update(ep);        
    }
    public void delete(long politicalPartyId){
        daoPoliticalParty.delete(politicalPartyId);
    }
    public ArrayList<PoliticalParty> queryAll(long idElectoralProcess){
        politicalPartyList = daoPoliticalParty.queryAll(idElectoralProcess);
        return politicalPartyList;
    }
    public PoliticalParty queryById( long politicalPartyId){
        return daoPoliticalParty.queryById(politicalPartyId);
    }
}

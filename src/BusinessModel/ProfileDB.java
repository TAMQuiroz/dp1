/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOProfile;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;

/**
 *
 * @author erickelme
 */
public class ProfileDB {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOProfile daoProfile = daoFactory.getDAOProfile();
    
    public ProfileDB(){
        
    }    
    
    public ArrayList<Profile> queryAll(){
        return daoProfile.queryAll();
    }
    
    public Profile queryById(long profileId){
        return daoProfile.queryById(profileId);
    }
}

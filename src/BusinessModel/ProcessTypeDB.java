/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOProcessType;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class ProcessTypeDB {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOProcessType daoProcessType = daoFactory.getDAOProcessType();
    
    public ProcessTypeDB(){
        
    }    
    
    public ArrayList<ProcessType> queryAll(){
        return daoProcessType.queryAll();
    }
    
    public ProcessType queryById(long profileId){
        return daoProcessType.queryById(profileId);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOUser;
import BusinessModel.DAO.DBConnection;
import Model.*;
/**
 *
 * @author erickelme
 */
public class UserDB {    
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOUser daoUser = daoFactory.getDAOUser();
    
     public UserDB(){
        
    }    
    public User login(String user, String password){
        return daoUser.login(user, password);
    }
}

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
import java.util.ArrayList;
/**
 *
 * @author erickelme
 */
public class UserDB {    
    private ArrayList<User> userList = new ArrayList<User>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOUser daoUser = daoFactory.getDAOUser();
    
    public UserDB(){
        
    }    
    public User login(String user, String password){
        return daoUser.login(user, password);
    }
    public void add(User ep){
        daoUser.add(ep);
    }
    public void update(User ep){        
        daoUser.update(ep);        
    }
    public void delete(long userId){
        daoUser.delete(userId);
    }
    public ArrayList<User> queryAll(){
        userList = daoUser.queryAll();
        return userList;
    }
    public User queryById(long userId){
        return daoUser.queryById(userId);
    }
}

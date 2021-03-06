/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.User;
import java.util.ArrayList;

/**
 *
 * @author erickelme
 */
public interface DAOUser {
    void add(User ep);
    void update(User ep);
    void delete(long userId);
    ArrayList<User> queryAll();    
    User login(String user, String password);
    User queryById(long userId);
}

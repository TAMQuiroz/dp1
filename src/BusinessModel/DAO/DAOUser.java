/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.User;

/**
 *
 * @author erickelme
 */
public interface DAOUser {
    User login(String user, String password);
}

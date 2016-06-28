/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.*;
import java.util.ArrayList;

/**
 *
 * @author erickelme
 */
public interface DAOPerson {
    Person queryByDni(String dni);
    ArrayList<Person> queryByPerson(String queryDni);
    void addPeople(ArrayList<Person> personas);
}

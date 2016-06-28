/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOPerson;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class PersonDB {
    private ArrayList<Person> personList = new ArrayList<Person>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOPerson daoPerson = daoFactory.getDAOPerson();
    
    public PersonDB(){
        
    }
    public Person queryByDni(String dni){
        return daoPerson.queryByDni(dni);
    }
    public ArrayList<Person> queryByPerson(String queryDni){
        return daoPerson.queryByPerson(queryDni);
    }

    public void addPeople(ArrayList<Person> personas) {
        daoPerson.addPeople(personas);
    }
}

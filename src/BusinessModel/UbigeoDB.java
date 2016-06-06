/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DAOUbigeo;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class UbigeoDB {
    private ArrayList<Ubigeo> ubigeoList = new ArrayList<Ubigeo>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOUbigeo daoUbigeo = daoFactory.getDAOUbigeo();
    
    public UbigeoDB(){
        
    }
    
    public void add(Ubigeo ep){
        daoUbigeo.add(ep);
    }
    public void update(Ubigeo ep){        
        daoUbigeo.update(ep);        
    }
    public void delete(long ubigeoId){
        daoUbigeo.delete(ubigeoId);
    }
    public ArrayList<Ubigeo> queryAll(){
        ubigeoList = daoUbigeo.queryAll();
        return ubigeoList;
    }
    public Ubigeo queryById( long ubigeoId){
        return daoUbigeo.queryById(ubigeoId);
    }
    public Ubigeo queryByIdAndElectoralProcess(String code, long electoralProcessId){
        return daoUbigeo.queryByIdAndElectoralProcess(code, electoralProcessId);
    }
    
}

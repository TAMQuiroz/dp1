/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.*;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class ElectoralProcessDB {
    private ArrayList<ElectoralProcess> electoralProcessList = new ArrayList<ElectoralProcess>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOElectoralProcess daoElectoralProcess = daoFactory.getDAOElectoralProcess();
    
    public ElectoralProcessDB(){
        
    }
    
    public void add(ElectoralProcess ep){
        daoElectoralProcess.add(ep);
    }
    public void update(ElectoralProcess ep){        
        daoElectoralProcess.update(ep);        
    }
    public void delete(long electoralProcessId){
        daoElectoralProcess.delete(electoralProcessId);
    }
    public ArrayList<ElectoralProcess> queryAll(){
        electoralProcessList = daoElectoralProcess.queryAll();
        return electoralProcessList;
    }
    public ElectoralProcess queryById( long electoralProcessId){
        return daoElectoralProcess.queryById(electoralProcessId);
    }
}

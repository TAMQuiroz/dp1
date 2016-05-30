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
public interface DAOUbigeo {
    void add(Ubigeo ep);
    void update(Ubigeo ep);
    void delete(long ubigeoId);
    ArrayList<Ubigeo> queryAll();        
    Ubigeo queryById(long ubigeoId);
}

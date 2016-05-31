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
public interface DAOAdherentImage {
    void add(AdherentImage ep);
    void update(AdherentImage ep);
    void delete(long adherentImageId);
    ArrayList<AdherentImage> queryAll();
    AdherentImage queryById(long adherentImageId);
}

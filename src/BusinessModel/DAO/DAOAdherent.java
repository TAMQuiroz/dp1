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
public interface DAOAdherent {
    void add(Adherent ep);
    void updateStatus(Adherent ep);    
    ArrayList<Adherent> queryAll();
    Adherent queryById(long adherentId);
    ArrayList<Adherent> queryByDni(String dni);
    Adherent queryByDniAndPoliticalParty(String dniP, long politicalPartyIdP);
}

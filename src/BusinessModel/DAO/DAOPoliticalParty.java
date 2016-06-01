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
public interface DAOPoliticalParty {
    void add(PoliticalParty ep);
    void update(PoliticalParty ep);
    void delete(long politicalPartyId);
    ArrayList<PoliticalParty> queryAll(long idElectoralProcess);
    PoliticalParty queryById(long politicalPartyId);
}

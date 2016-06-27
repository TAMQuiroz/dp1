/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.ElectoralProcess;
import java.util.ArrayList;

/**
 *
 * @author erickelme
 */
public interface DAOElectoralProcess {
    void add(ElectoralProcess ep);
    void update(ElectoralProcess ep);
    void delete(long electoralProcessId);
    ArrayList<ElectoralProcess> queryAll();
    ElectoralProcess queryById(long electoralProcessId);
    void setProcessStage(int etapa, long idProcess);
    void setProcessListStage(ArrayList<ElectoralProcess> epList);
}

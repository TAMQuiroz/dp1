/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.ProcessType;
import java.util.ArrayList;

/**
 *
 * @author erickelme
 */
public interface DAOProcessType {
    ArrayList<ProcessType> queryAll();
    ProcessType queryById(long processTypeId);
}

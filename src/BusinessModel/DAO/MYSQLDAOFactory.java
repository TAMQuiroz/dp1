/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

/**
 *
 * @author erickelme
 */
public class MYSQLDAOFactory extends DAOFactory {

    public MYSQLDAOFactory() {
    }

    @Override
    public DAOElectoralProcess getDAOElectoralProcess() {
        return new MYSQLDAOElectoralProcess();
    }
    
}

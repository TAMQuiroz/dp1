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
public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(int dbType){
		if(dbType == DBConnection.MYSQLSERVER){			
			return new MYSQLDAOFactory();			
		}
		return null;
    }
    public abstract DAOElectoralProcess getDAOElectoralProcess(); //Polimorfismo
    //public abstract DAOCompany getDAOCompany(); //Polimorfismo
    //public abstract DAOPerson getDAOPerson(); //Polimorfismo
    //public abstract DAOSalesman getDAOSalesman(); //Polimorfismo	
    //public abstract DAOSales getDAOSale();
    //public abstract DAOCustomer getDAOCustomer();

}

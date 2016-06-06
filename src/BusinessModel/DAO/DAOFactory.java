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
    public abstract DAOProfile getDAOProfile(); //Polimorfismo
    public abstract DAOUser getDAOUser(); //Polimorfismo    
    public abstract DAOProcessType getDAOProcessType(); //Polimorfismo	
    public abstract DAOUbigeo getDAOUbigeo();
    public abstract DAOPoliticalParty getDAOPoliticalParty();
    public abstract DAOAdherentImage getDAOAdherentImage();
    public abstract DAOPerson getDAOPerson();
    public abstract DAOAdherent getDAOAdherent();
}

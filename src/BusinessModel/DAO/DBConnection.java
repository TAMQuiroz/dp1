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
public class DBConnection {    	
	public static final int MYSQLSERVER = 1;	
        //public static final int SQL_SERVER = 2;	
	//public static final String URL_JDBC_MYSQL = "jdbc:mysql://inti.lab.inf.pucp.edu.pe:1433;databaseName=inf282";	
	//public static final String user = "inf282";
	//public static final String password = "inf282db";
	public static int dbType = MYSQLSERVER;
	
	public static final String URL_JDBC_MYSQL = "jdbc:mysql://http://quilla.lab.inf.pucp.edu.pe:3306/inf226eg";        
	public static final String user = "inf226egusr";
	public static final String password = "vqLHUuKtCnz3fyd6";

}

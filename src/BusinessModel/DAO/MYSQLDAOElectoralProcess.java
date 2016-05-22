/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.ElectoralProcess;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author erickelme
 */
public class MYSQLDAOElectoralProcess implements DAOElectoralProcess{

    @Override
    public void add(ElectoralProcess ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "INSERT INTO electoralProcess (name, date, startRegistrationDate, endRegistrationDate, startReceptionDate, endReceptionDate, "
                        + "startValidationDate, endValidationDate, minPercentage, status, population, startExtraReceptionDate, "
                        + "endExtraReceptionDate, startExtraValidationDate, endExtraValidationDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //pstmt.setInt(1,  p.getId());
                pstmt.setString(1, ep.getName());
                
                java.util.Date utilDate = ep.getDate();			
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(2, sqlDate);
                
                utilDate = ep.getStartRegistrationDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(3, sqlDate);
                
                utilDate = ep.getEndRegistrationDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(4, sqlDate);
                
                utilDate = ep.getStartReceptionDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(5, sqlDate);
                
                utilDate = ep.getEndReceptionDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(6, sqlDate);
                
                utilDate = ep.getStartValidationDate();
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(7, sqlDate);
                
                utilDate = ep.getEndValidationDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(8, sqlDate);
                
                pstmt.setDouble(9, ep.getMinPercentage());
                pstmt.setString(10, ep.getStatus());
                pstmt.setDouble(11, ep.getPopulation());
                
                utilDate = ep.getStartExtraReceptionDate();
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(12, sqlDate);
                
                utilDate = ep.getEndExtraReceptionDate();
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(13, sqlDate);
                
                utilDate = ep.getStartExtraValidationDate();
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(14, sqlDate);
                
                utilDate = ep.getEndExtraValidationDate();			
                sqlDate = new java.sql.Date(utilDate.getTime());			
                pstmt.setDate(15, sqlDate);
                
                pstmt.executeUpdate();
                
                //Paso 4: Ejecutar la sentencia						
                pstmt.executeUpdate();
                //Paso 5:(opc) Procesar los resultado



        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally{
                //Paso 6: (ATENCION1)  CERRAR LA CONEXION
                if (pstmt != null){
                        try {
                                pstmt.close();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
                if(conn != null){
                        try {
                                conn.close();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }			
                }
        }
    }

    @Override
    public void update(ElectoralProcess ep) {
        
    }
    
    @Override
    public void delete(int electoralProcessId) {
        
    }

    @Override
    public ArrayList<ElectoralProcess> queryAll() {
        return null;
    }

    @Override
    public ElectoralProcess queryById(int electoralProcessId) {
        return null;
    }
    

    
}

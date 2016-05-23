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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author erickelmereason: actual and formal argument lists differ in length
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
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE electoralProcess SET name=?, date=?, startRegistrationDate=?, endRegistrationDate=?, startReceptionDate=?, endReceptionDate=?, "
                        + "startValidationDate=?, endValidationDate=?, minPercentage=?, status=?, population=?, startExtraReceptionDate=?, "
                        + "endExtraReceptionDate=?, startExtraValidationDate=?, endExtraValidationDate=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
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
                
                pstmt.setLong(16, ep.getId());                
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
    public void delete(long electoralProcessId) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE electoralProcess SET status=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Inactivo");                
                pstmt.setLong(2, electoralProcessId);                
                
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
    public ArrayList<ElectoralProcess> queryAll() {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<ElectoralProcess> electoralProcessList = new ArrayList<ElectoralProcess>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from electoralProcess";
                pstmt = conn.prepareStatement(sql);			
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        ElectoralProcess ep = new ElectoralProcess();
                        long id = rs.getLong("id"); ep.setId(id);
                        int population = rs.getInt("population"); ep.setPopulation(population);
                        String name = rs.getString("name"); ep.setName(name);
                        String status = rs.getString("status"); ep.setStatus(status);
                        double minPercentage  = rs.getDouble("minPercentage"); ep.setMinPercentage(minPercentage);
                        java.sql.Date date = rs.getDate("date"); ep.setDate(date);
                        java.util.Date dateJava = new java.util.Date(date.getTime()); ep.setDate(dateJava);
                        date = rs.getDate("startRegistrationDate"); 
                        java.util.Date startRegistrationDate = new java.util.Date(date.getTime()); 
                        ep.setStartExtraReceptionDate(startRegistrationDate);
                        date = rs.getDate("endRegistrationDate"); 
                        java.util.Date endRegistrationDate = new java.util.Date(date.getTime());
                        ep.setEndRegistrationDate(endRegistrationDate);
                        date = rs.getDate("startReceptionDate");                        
                        java.util.Date startReceptionDate = new java.util.Date(date.getTime());
                        ep.setStartReceptionDate(startReceptionDate);
                        date = rs.getDate("endReceptionDate");                        
                        java.util.Date endReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndReceptionDate(endReceptionDate);
                        date = rs.getDate("startValidationDate");                        
                        java.util.Date startValidationDate = new java.util.Date(date.getTime());
                        ep.setStartValidationDate(startValidationDate);
                        date = rs.getDate("endValidationDate");                        
                        java.util.Date endValidationDate = new java.util.Date(date.getTime());
                        ep.setEndValidationDate(endValidationDate);
                        date = rs.getDate("startExtraReceptionDate");                        
                        java.util.Date startExtraReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndExtraReceptionDate(startExtraReceptionDate);
                        date = rs.getDate("endExtraReceptionDate");                        
                        java.util.Date endExtraReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndExtraReceptionDate(endExtraReceptionDate);
                        date = rs.getDate("startExtraValidationDate");                        
                        java.util.Date startExtraValidationDate = new java.util.Date(date.getTime());
                        ep.setStartExtraValidationDate(startExtraValidationDate);
                        date = rs.getDate("endExtraValidationDate");                         
                        java.util.Date endExtraValidationDate = new java.util.Date(date.getTime());
                        ep.setEndExtraValidationDate(endExtraValidationDate);                        
                        electoralProcessList.add(ep);
                }



        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally{
                //Paso 6: (ATENCION1)  CERRAR LA CONEXION
                if (pstmt != null) {
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
        return electoralProcessList;
    }

    @Override
    public ElectoralProcess queryById(long electoralProcessId) {
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ElectoralProcess ep = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from electoralProcess where id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, electoralProcessId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) { 
                        ep = new ElectoralProcess();
                        long id = rs.getLong("id"); ep.setId(id);
                        int population = rs.getInt("population"); ep.setPopulation(population);
                        String name = rs.getString("name"); ep.setName(name);
                        String status = rs.getString("status"); ep.setStatus(status);
                        double minPercentage  = rs.getDouble("minPercentage"); ep.setMinPercentage(minPercentage);
                        java.sql.Date date = rs.getDate("date"); 
                        java.util.Date dateJava = new java.util.Date(date.getTime()); 
                        ep.setDate(dateJava);
                        date = rs.getDate("startRegistrationDate"); 
                        java.util.Date startRegistrationDate = new java.util.Date(date.getTime()); 
                        ep.setStartExtraReceptionDate(startRegistrationDate);
                        date = rs.getDate("endRegistrationDate"); 
                        java.util.Date endRegistrationDate = new java.util.Date(date.getTime());
                        ep.setEndRegistrationDate(endRegistrationDate);
                        date = rs.getDate("startReceptionDate");                        
                        java.util.Date startReceptionDate = new java.util.Date(date.getTime());
                        ep.setStartReceptionDate(startReceptionDate);
                        date = rs.getDate("endReceptionDate");                        
                        java.util.Date endReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndReceptionDate(endReceptionDate);
                        date = rs.getDate("startValidationDate");                        
                        java.util.Date startValidationDate = new java.util.Date(date.getTime());
                        ep.setStartValidationDate(startValidationDate);
                        date = rs.getDate("endValidationDate");                        
                        java.util.Date endValidationDate = new java.util.Date(date.getTime());
                        ep.setEndValidationDate(endValidationDate);
                        date = rs.getDate("startExtraReceptionDate");                        
                        java.util.Date startExtraReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndExtraReceptionDate(startExtraReceptionDate);
                        date = rs.getDate("endExtraReceptionDate");                        
                        java.util.Date endExtraReceptionDate = new java.util.Date(date.getTime());
                        ep.setEndExtraReceptionDate(endExtraReceptionDate);
                        date = rs.getDate("startExtraValidationDate");                        
                        java.util.Date startExtraValidationDate = new java.util.Date(date.getTime());
                        ep.setStartExtraValidationDate(startExtraValidationDate);
                        date = rs.getDate("endExtraValidationDate");                         
                        java.util.Date endExtraValidationDate = new java.util.Date(date.getTime());
                        ep.setEndExtraValidationDate(endExtraValidationDate);                                                
                }
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally{
                //Paso 6: (ATENCION1)  CERRAR LA CONEXION
                if (pstmt != null) {
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
        return ep;
    }
    
    
    
}

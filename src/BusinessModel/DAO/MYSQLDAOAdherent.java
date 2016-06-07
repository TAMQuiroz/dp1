/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import BusinessModel.Manager;
import Model.*;
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
 * @author erickelme
 */
public class MYSQLDAOAdherent implements DAOAdherent {
    @Override
    public void add(Adherent ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "INSERT INTO adherent (name, lastname, dni, observation, id_politicalParty) VALUES(?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                
                pstmt.setString(1, ep.getName());                                                
                pstmt.setString(2, ep.getLastName());
                pstmt.setString(3, ep.getDni());                
                pstmt.setString(4, ep.getObservation());                
                pstmt.setLong(5, ep.getPoliticalParty().getId());                 
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
    public void updateStatus(Adherent ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE adherent SET observation=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setString(1, ep.getObservation());                                                                
                pstmt.setLong(2, ep.getId());                   
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
    public ArrayList<Adherent> queryAll(long idPoliticalPartyA) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Adherent> adherentList = new ArrayList<Adherent>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherent where observation=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);   
                pstmt.setString(1, "Validado");
                pstmt.setLong(2, idPoliticalPartyA);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        Adherent adherent = new Adherent();
                        long id = rs.getLong("id"); adherent.setId(id);                       
                        String name = rs.getString("name"); adherent.setName(name);
                        String lastName = rs.getString("lastname"); adherent.setLastName(lastName);                                                
                        String dni = rs.getString("dni"); adherent.setDni(dni);                    
                        String observation = rs.getString("observation"); adherent.setObservation(observation);                        
                        long idPoliticalParty = rs.getLong("id_politicalParty"); 
                        PoliticalParty pt = Manager.queryPoliticalPartyById(idPoliticalParty);
                        adherent.setPoliticalParty(pt);                        
                        adherentList.add(adherent);
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
        return adherentList;
    }
    
    @Override
    public ArrayList<Adherent> queryAllDuplicated(long idPoliticalPartyA) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Adherent> adherentList = new ArrayList<Adherent>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherent where observation=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);   
                pstmt.setString(1, "Duplicado");
                pstmt.setLong(2, idPoliticalPartyA);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        Adherent adherent = new Adherent();
                        long id = rs.getLong("id"); adherent.setId(id);                       
                        String name = rs.getString("name"); adherent.setName(name);
                        String lastName = rs.getString("lastname"); adherent.setLastName(lastName);                                                
                        String dni = rs.getString("dni"); adherent.setDni(dni);                    
                        String observation = rs.getString("observation"); adherent.setObservation(observation);                        
                        long idPoliticalParty = rs.getLong("id_politicalParty"); 
                        PoliticalParty pt = Manager.queryPoliticalPartyById(idPoliticalParty);
                        adherent.setPoliticalParty(pt);                        
                        adherentList.add(adherent);
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
        return adherentList;
    }
    
    @Override
    public Adherent queryById(long adherentId){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        Adherent adherent = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherent where id=?";
                pstmt = conn.prepareStatement(sql);		
                pstmt.setLong(1, adherentId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) {
                        adherent = new Adherent();
                        long id = rs.getLong("id"); adherent.setId(id);                       
                        String name = rs.getString("name"); adherent.setName(name);
                        String lastName = rs.getString("lastname"); adherent.setLastName(lastName);                                                
                        String dni = rs.getString("dni"); adherent.setDni(dni);                    
                        String observation = rs.getString("observation"); adherent.setObservation(observation);                        
                        long idPoliticalParty = rs.getLong("id_politicalParty"); 
                        PoliticalParty pt = Manager.queryPoliticalPartyById(idPoliticalParty);
                        adherent.setPoliticalParty(pt);
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
        return adherent;
    }

    @Override
    public ArrayList<Adherent> queryByDni(String dniP){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Adherent> adherentList = new ArrayList<Adherent>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherent where dni=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setString(1, dniP);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        Adherent adherent = new Adherent();
                        long id = rs.getLong("id"); adherent.setId(id);                       
                        String name = rs.getString("name"); adherent.setName(name);
                        String lastName = rs.getString("lastname"); adherent.setLastName(lastName);                                                
                        String dni = rs.getString("dni"); adherent.setDni(dni);                    
                        String observation = rs.getString("observation"); adherent.setObservation(observation);                        
                        long idPoliticalParty = rs.getLong("id_politicalParty"); 
                        PoliticalParty pt = Manager.queryPoliticalPartyById(idPoliticalParty);
                        adherent.setPoliticalParty(pt);                        
                        adherentList.add(adherent);
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
        return adherentList;
    }
    
    @Override
    public Adherent queryByDniAndPoliticalParty(String dniP, long politicalPartyIdP){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        Adherent adherent = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherent where dni=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);		
                pstmt.setString(1, dniP);
                pstmt.setLong(1, politicalPartyIdP);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) {
                        adherent = new Adherent();
                        long id = rs.getLong("id"); adherent.setId(id);                       
                        String name = rs.getString("name"); adherent.setName(name);
                        String lastName = rs.getString("lastname"); adherent.setLastName(lastName);                                                
                        String dni = rs.getString("dni"); adherent.setDni(dni);                    
                        String observation = rs.getString("observation"); adherent.setObservation(observation);                        
                        long idPoliticalParty = rs.getLong("id_politicalParty"); 
                        PoliticalParty pt = Manager.queryPoliticalPartyById(idPoliticalParty);
                        adherent.setPoliticalParty(pt);
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
        return adherent;
    }
}

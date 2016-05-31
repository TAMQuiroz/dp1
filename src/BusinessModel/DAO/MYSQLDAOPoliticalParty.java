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
public class MYSQLDAOPoliticalParty implements DAOPoliticalParty{
    @Override
    public void add(PoliticalParty ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "INSERT INTO politicalParty (name, legalRepresentative, telehpone, email, status, id_electoralProcess) VALUES(?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //pstmt.setInt(1,  p.getId());
                pstmt.setString(1, ep.getName());
                pstmt.setString(2, ep.getLegalRepresentative());
                pstmt.setString(3, ep.getTelephone());
                pstmt.setString(4, ep.getEmail());
                pstmt.setString(5, ep.getStatus());
                pstmt.setLong(6, ep.getElectoralProcess().getId());                
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
    public void update(PoliticalParty ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "UPDATE politicalParty SET name=?, legalRepresentative=?, telehpone=?, email=?, status=?, id_politicalParty=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                //pstmt.setInt(1,  p.getId());
                pstmt.setString(1, ep.getName());
                pstmt.setString(2, ep.getLegalRepresentative());
                pstmt.setString(3, ep.getTelephone());
                pstmt.setString(4, ep.getEmail());
                pstmt.setString(5, ep.getStatus());
                pstmt.setLong(6, ep.getElectoralProcess().getId());                             
                pstmt.setLong(7, ep.getId()); 
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
    public void delete(long politicalPartyId) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE politicalParty SET status=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Inactivo");                
                pstmt.setLong(2, politicalPartyId);   
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
    public ArrayList<PoliticalParty> queryAll() {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<PoliticalParty> politicalPartyList = new ArrayList<PoliticalParty>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from politicalParty";
                pstmt = conn.prepareStatement(sql);			
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        PoliticalParty ep = new PoliticalParty();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String legalRepresentative = rs.getString("legalRepresentative"); ep.setLegalRepresentative(legalRepresentative);
                        String telephone = rs.getString("telephone"); ep.setTelephone(telephone);                         
                        String email = rs.getString("email"); ep.setEmail(email);                         
                        String status = rs.getString("status"); ep.setStatus(status);                         
                        long electoralProcessId = rs.getLong("id_electoralProcess");                        
                        ElectoralProcess electoralProcess = new ElectoralProcess();
                        electoralProcess = Manager.queryElectoralProcessById(electoralProcessId);
                        ep.setElectoralProcess(electoralProcess);                        
                        politicalPartyList.add(ep);
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
        return politicalPartyList;
    }

    @Override
    public PoliticalParty queryById(long politicalPartyId) {
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        PoliticalParty ep = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from politicalParty where id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, politicalPartyId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) { 
                        ep = new PoliticalParty();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String legalRepresentative = rs.getString("legalRepresentative"); ep.setLegalRepresentative(legalRepresentative);
                        String telephone = rs.getString("telephone"); ep.setTelephone(telephone);                         
                        String email = rs.getString("email"); ep.setEmail(email);                         
                        String status = rs.getString("status"); ep.setStatus(status);                         
                        long electoralProcessId = rs.getLong("id_electoralProcess");                        
                        ElectoralProcess electoralProcess = new ElectoralProcess();
                        electoralProcess = Manager.queryElectoralProcessById(electoralProcessId);
                        ep.setElectoralProcess(electoralProcess);            
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
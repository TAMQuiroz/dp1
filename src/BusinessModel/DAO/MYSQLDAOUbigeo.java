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
public class MYSQLDAOUbigeo implements DAOUbigeo {
    @Override
    public void add(Ubigeo ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "INSERT INTO ubigeo (code, name, description, status, id_electoralProcess) VALUES(?, ?,?,?,?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1,  ep.getCode());
                pstmt.setString(2, ep.getName());
                pstmt.setString(3, ep.getDescription());
                pstmt.setString(4, ep.getStatus());
                pstmt.setLong(5, ep.getElectoralProcess().getId());                
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
    public void update(Ubigeo ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "UPDATE ubigeo SET code=?, name=?, description=?, id_electoralProcess=?, status=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  ep.getCode());
                pstmt.setString(2, ep.getName());
                pstmt.setString(3, ep.getDescription());
                pstmt.setLong(4, ep.getElectoralProcess().getId());             
                pstmt.setString(5, ep.getStatus());
                pstmt.setLong(6, ep.getId()); 
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
    public void delete(long ubigeoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE ubigeo SET status=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Inactivo");                
                pstmt.setLong(2, ubigeoId);   
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
    public ArrayList<Ubigeo> queryAll() {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Ubigeo> ubigeoList = new ArrayList<Ubigeo>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from ubigeo WHERE status=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "Activo");           			
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        Ubigeo ep = new Ubigeo();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String code = rs.getString("code"); ep.setCode(code);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String description = rs.getString("description"); ep.setDescription(description);
                        String status = rs.getString("status"); ep.setStatus(status);                         
                        long electoralProcessId = rs.getLong("id_electoralProcess");                        
                        ElectoralProcess electoralProcess = new ElectoralProcess();
                        electoralProcess = Manager.queryElectoralProcessById(electoralProcessId);
                        ep.setElectoralProcess(electoralProcess);                        
                        ubigeoList.add(ep);
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
        return ubigeoList;
    }

    @Override
    public Ubigeo queryById(long ubigeoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        Ubigeo ep = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from ubigeo where id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, ubigeoId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) { 
                        ep = new Ubigeo();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String code = rs.getString("code"); ep.setCode(code);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String description = rs.getString("description"); ep.setDescription(description);
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
    @Override
    public Ubigeo queryByIdAndElectoralProcess(String codeUbigeo, long electoralProcessIdcodeUbigeo) {
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        Ubigeo ep = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from ubigeo where code=? and id_electoralProcess=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, codeUbigeo);
                pstmt.setLong(2, electoralProcessIdcodeUbigeo);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) { 
                        ep = new Ubigeo();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String code = rs.getString("code"); ep.setCode(code);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String description = rs.getString("description"); ep.setDescription(description);
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
    
    @Override
    public ArrayList<Ubigeo> queryByElectoralProcess( long electoralProcessId ) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Ubigeo> ubigeoList = new ArrayList<Ubigeo>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from ubigeo WHERE id_electoralProcess=? and status=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, electoralProcessId);           			
                pstmt.setString(2, "Activo");           			
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado                
                ElectoralProcess electoralProcess = new ElectoralProcess();
                electoralProcess = Manager.queryElectoralProcessById(electoralProcessId);
                while (rs.next()) {
                        Ubigeo ep = new Ubigeo();
                        long id = rs.getLong("id"); ep.setId(id);                        
                        String code = rs.getString("code"); ep.setCode(code);                        
                        String name = rs.getString("name"); ep.setName(name);
                        String description = rs.getString("description"); ep.setDescription(description);
                        String status = rs.getString("status"); ep.setStatus(status);                                                 
                        ep.setElectoralProcess(electoralProcess);                        
                        ubigeoList.add(ep);
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
        return ubigeoList;
    }
}

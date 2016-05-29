/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import BusinessModel.Manager;
import Model.Profile;
import Model.User;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author erickelme
 */
public class MYSQLDAOUser implements DAOUser{
    @Override
    public User login(String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        User p = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL,
                                                        DBConnection.user,
                                                        DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "SELECT * FROM user "
                                + "WHERE docCode=? and password=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user);
                pstmt.setString(2, password);
                //Paso 4: Ejecutar la sentencia
                rs = pstmt.executeQuery();
                //Paso 5(opc.): Procesar los resultados
                if (rs.next()){
                    p = new User();    
                    long id = rs.getLong("id"); p.setId(id);
                    String name = rs.getString("name"); p.setName(name);
                    String lastName = rs.getString("lastName"); p.setLastName(lastName);                    
                    java.sql.Date date = rs.getDate("bornDay"); 
                    java.util.Date dateJava = new java.util.Date(date.getTime()); 
                    p.setBornDay(dateJava);        
                    String phone = rs.getString("phone"); p.setPhone(phone);                    
                    String docCode = rs.getString("docCode"); p.setDocCode(docCode);
                    String docType = rs.getString("docType"); p.setDocType(docType);                    
                    long idProfile = rs.getLong("id_profile"); 
                    //BUSCAR PROFILE PENDIENTE
                    Profile profile = new Profile();
                    profile = Manager.queryProfileById(idProfile);
                    p.setProfile(profile);
                }		
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                //Paso 6(OJO): Cerrar la conexión
                try { if (pstmt!= null) pstmt.close();} 
                        catch (Exception e){e.printStackTrace();};
                try { if (conn!= null) conn.close();} 
                        catch (Exception e){e.printStackTrace();};						
        }
        return p;	
    }
    @Override
    public User queryById(long userId){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        User user = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from user where id=?";
                pstmt = conn.prepareStatement(sql);		
                pstmt.setLong(1, userId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) {
                        user = new User();
                        long id = rs.getLong("id"); user.setId(id);                       
                        String name = rs.getString("name"); user.setName(name);
                        String lastName = rs.getString("lastName"); user.setLastName(lastName);                    
                        java.sql.Date date = rs.getDate("bornDay"); 
                        java.util.Date dateJava = new java.util.Date(date.getTime()); 
                        user.setBornDay(dateJava);        
                        String phone = rs.getString("phone"); user.setPhone(phone);                    
                        String docCode = rs.getString("docCode"); user.setDocCode(docCode);
                        String docType = rs.getString("docType"); user.setDocType(docType);                    
                        long idProfile = rs.getLong("id_profile"); 
                        //BUSCAR PROFILE PENDIENTE
                        Profile profile = new Profile();
                        profile = Manager.queryProfileById(idProfile);
                        user.setProfile(profile);                                             
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
        return user;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel.DAO;

import Model.*;
import java.util.ArrayList;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author erickelme
 */
public class MYSQLDAOPerson implements DAOPerson {
    @Override
    public Person queryByDni(String dni){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        Person person = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from rnv where dni=?";
                pstmt = conn.prepareStatement(sql);		
                pstmt.setString(1, dni);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) {
                        person = new Person();
                        long id = rs.getLong("id"); person.setId(id);                       
                        String name = rs.getString("name"); person.setName(name);
                        String lastName = rs.getString("lastname"); person.setLastname(lastName);                                                
                        String dniP = rs.getString("dni"); person.setDni(dniP); 
                        String ubigeo = rs.getString("ubigeo"); person.setUbigeo(ubigeo);     
                        String fingerprint = rs.getString("fingerprint"); person.setFingerprint(fingerprint);
                        String signature = rs.getString("signature"); person.setSignature(signature);                                            
                        boolean citizen = rs.getBoolean("citizen"); person.setCitizen(citizen);                       
                        boolean disabled = rs.getBoolean("disabled"); person.setDisabled(disabled);
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
        return person;
    }
    @Override
    public ArrayList<Person> queryByPerson(String queryDni) {
               
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Person> personList = new ArrayList<Person>();

        java.lang.System.out.println("Dni a buscar: " + queryDni);
        
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "SELECT * FROM rnv WHERE dni LIKE '" + queryDni + "'";
                pstmt = conn.prepareStatement(sql); 
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                    Person person = new Person();
                    long id = rs.getLong("id"); person.setId(id);                       
                    String name = rs.getString("name"); person.setName(name);
                    String lastName = rs.getString("lastname"); person.setLastname(lastName);                                                
                    String dniP = rs.getString("dni"); person.setDni(dniP); 
                    String ubigeo = rs.getString("ubigeo"); person.setUbigeo(ubigeo);     
                    String fingerprint = rs.getString("fingerprint"); person.setFingerprint(fingerprint);
                    String signature = rs.getString("signature"); person.setSignature(signature);                                            
                    boolean citizen = rs.getBoolean("citizen"); person.setCitizen(citizen);                       
                    boolean disabled = rs.getBoolean("disabled"); person.setDisabled(disabled);
                    personList.add(person);
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
        return personList;
    }

    @Override
    public void addPeople(ArrayList<Person> personas) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                for(int i = 0; i <  personas.size(); i++){
                    Person ep = personas.get(i);
                    
                    String sql = "INSERT INTO rnv (dni, name, lastname, signature, fingerprint, ubigeo, citizen, disabled) VALUES(?,?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                
                    pstmt.setString(1, ep.getDni());                                                
                    pstmt.setString(2, ep.getName());
                    pstmt.setString(3, ep.getLastname());
                    pstmt.setString(4, ep.getSignature());
                    pstmt.setString(5, ep.getFingerprint()); 
                    pstmt.setString(6, ep.getUbigeo());
                    pstmt.setBoolean(7, ep.isCitizen()); 
                    pstmt.setBoolean(8, ep.isDisabled()); 
                    //Paso 4: Ejecutar la sentencia						
                    pstmt.executeUpdate();
                    
                }    
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
}

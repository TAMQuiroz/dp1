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
    public ArrayList<Person> queryByPerson(ArrayList<OcrCharacter> ocrDni, ArrayList<OcrCharacter> ocrName, ArrayList<OcrCharacter> ocrLastname) {
               
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<Person> personList = new ArrayList<Person>();
        
        String queryDni = "";
        for (int i = 0; i < ocrDni.size(); i++) {
            if(ocrDni.get(i).getConfidence() > 60){
                queryDni += ocrDni.get(i).getLetter();
            }else{
                queryDni += "%";
            }
        }
        
        String name = "";
        for (int i = 0; i < ocrName.size(); i++) {
            if(ocrName.get(i).getConfidence() > 60){
                name += ocrName.get(i).getLetter();
            }else{
                name += "%";
            }
        }
        
        String lastname = "";
        for (int i = 0; i < ocrLastname.size(); i++) {
            if(ocrLastname.get(i).getConfidence() > 60){
                lastname += ocrLastname.get(i).getLetter();
            }else{
                lastname += "%";
            }
        }
        
        java.lang.System.out.println("Dni a buscar: " + queryDni);
        java.lang.System.out.println("Nombre a buscar: " + name);
        java.lang.System.out.println("Apellido a buscar: " + lastname);
        
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from rnv";
                pstmt = conn.prepareStatement(sql);                
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        //completar
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
}

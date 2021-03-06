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
public class MYSQLDAOAdherentImage implements DAOAdherentImage {
    @Override
    public void add(AdherentImage ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "INSERT INTO adherentImage (nameSource, lastnameSource, dniSource, fingerprintSource, signatureSource, id_politicalParty, status) VALUES(?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                
                pstmt.setString(1, ep.getNameSource());                                                
                pstmt.setString(2, ep.getLastNameSource());
                pstmt.setString(3, ep.getDniSource());
                pstmt.setString(4, ep.getFingerprintSource());
                pstmt.setString(5, ep.getSignatureSource());                
                pstmt.setLong(6, ep.getPoliticalParty()); 
                pstmt.setLong(7, ep.getStatus()); 
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
    public void update(AdherentImage ep) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE adherentImage SET nameSource=?, lastnameSource=?, dniSource=?, fingerprintSource=?, "
                        + "signatureSource=?, id_politicalParty=?, status=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setString(1, ep.getNameSource());                                                
                pstmt.setString(2, ep.getLastNameSource());
                pstmt.setString(3, ep.getDniSource());
                pstmt.setString(4, ep.getFingerprintSource());
                pstmt.setString(5, ep.getSignatureSource());                
                pstmt.setLong(6, ep.getPoliticalParty());
                pstmt.setLong(7, ep.getStatus());
                pstmt.setLong(8, ep.getId());                   
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
    public void delete(long adherentImageId) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "DELETE FROM adherentImage WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, adherentImageId);                                
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
    public ArrayList<AdherentImage> queryAll(long idPoliticalPartyA) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<AdherentImage> adherentImageList = new ArrayList<AdherentImage>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherentImage where status=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setLong(1, 0);            
                pstmt.setLong(2, idPoliticalPartyA);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        AdherentImage adherentImage = new AdherentImage();
                        long id = rs.getLong("id"); adherentImage.setId(id);                       
                        String name = rs.getString("nameSource"); adherentImage.setNameSource(name);
                        String lastName = rs.getString("lastnameSource"); adherentImage.setLastNameSource(lastName);                                                
                        String dni = rs.getString("dniSource"); adherentImage.setDniSource(dni);                    
                        String fingerprint = rs.getString("fingerprintSource"); adherentImage.setFingerprintSource(fingerprint);
                        String signature = rs.getString("signatureSource"); adherentImage.setSignatureSource(signature);                                            
                        long idPoliticalParty = rs.getLong("id_politicalParty"); adherentImage.setPoliticalParty(idPoliticalParty);
                        long status = rs.getLong("status"); adherentImage.setStatus(status);
                        adherentImageList.add(adherentImage);
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
        return adherentImageList;
    }
    @Override
    public ArrayList<AdherentImage> queryAllRejected(long idPoliticalPartyA) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<AdherentImage> adherentImageList = new ArrayList<AdherentImage>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherentImage where status=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setLong(1, 1);        
                pstmt.setLong(2, idPoliticalPartyA);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        AdherentImage adherentImage = new AdherentImage();
                        long id = rs.getLong("id"); adherentImage.setId(id);                       
                        String name = rs.getString("nameSource"); adherentImage.setNameSource(name);
                        String lastName = rs.getString("lastnameSource"); adherentImage.setLastNameSource(lastName);                                                
                        String dni = rs.getString("dniSource"); adherentImage.setDniSource(dni);                    
                        String fingerprint = rs.getString("fingerprintSource"); adherentImage.setFingerprintSource(fingerprint);
                        String signature = rs.getString("signatureSource"); adherentImage.setSignatureSource(signature);                                            
                        long idPoliticalParty = rs.getLong("id_politicalParty"); adherentImage.setPoliticalParty(idPoliticalParty);
                        long status = rs.getLong("status"); adherentImage.setStatus(status);
                        adherentImageList.add(adherentImage);
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
        return adherentImageList;
    }
    @Override
    public ArrayList<AdherentImage> queryAllCanceled(long idPoliticalPartyA) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<AdherentImage> adherentImageList = new ArrayList<AdherentImage>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherentImage where status=? and id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);                
                pstmt.setLong(1, 2);         
                pstmt.setLong(2, idPoliticalPartyA);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        AdherentImage adherentImage = new AdherentImage();
                        long id = rs.getLong("id"); adherentImage.setId(id);                       
                        String name = rs.getString("nameSource"); adherentImage.setNameSource(name);
                        String lastName = rs.getString("lastnameSource"); adherentImage.setLastNameSource(lastName);                                                
                        String dni = rs.getString("dniSource"); adherentImage.setDniSource(dni);                    
                        String fingerprint = rs.getString("fingerprintSource"); adherentImage.setFingerprintSource(fingerprint);
                        String signature = rs.getString("signatureSource"); adherentImage.setSignatureSource(signature);                                            
                        long idPoliticalParty = rs.getLong("id_politicalParty"); adherentImage.setPoliticalParty(idPoliticalParty);
                        long status = rs.getLong("status"); adherentImage.setStatus(status);
                        adherentImageList.add(adherentImage);
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
        return adherentImageList;
    }
    
    @Override
    public AdherentImage queryById(long adherentImageId){
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        AdherentImage adherentImage = null;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select* from adherentImage where id=?";
                pstmt = conn.prepareStatement(sql);		
                pstmt.setLong(1, adherentImageId);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                if (rs.next()) {
                        adherentImage = new AdherentImage();
                        long id = rs.getLong("id"); adherentImage.setId(id);                       
                        String name = rs.getString("nameSource"); adherentImage.setNameSource(name);
                        String lastName = rs.getString("lastnameSource"); adherentImage.setLastNameSource(lastName);                                                
                        String dni = rs.getString("dniSource"); adherentImage.setDniSource(dni);                    
                        String fingerprint = rs.getString("fingerprintSource"); adherentImage.setFingerprintSource(fingerprint);
                        String signature = rs.getString("signatureSource"); adherentImage.setSignatureSource(signature);                                            
                        long idPoliticalParty = rs.getLong("id_politicalParty"); adherentImage.setPoliticalParty(idPoliticalParty);
                        long status = rs.getLong("status"); adherentImage.setStatus(status);
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
        return adherentImage;
    }

    @Override
    public ArrayList<AdherentImage> queryAdherentImageNoValidatedbyPartyId(long id) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;       
        ArrayList<AdherentImage> adherentImageList = new ArrayList<AdherentImage>();
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select * from adherentImage where id_politicalParty = ? and status = ?";
                pstmt = conn.prepareStatement(sql); 
                pstmt.setLong(1, id);
                pstmt.setLong(2, 0);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                        AdherentImage adherentImage = new AdherentImage();
                        long id_adherent = rs.getLong("id"); adherentImage.setId(id_adherent);                       
                        String name = rs.getString("nameSource"); adherentImage.setNameSource(name);
                        String lastName = rs.getString("lastnameSource"); adherentImage.setLastNameSource(lastName);                                                
                        String dni = rs.getString("dniSource"); adherentImage.setDniSource(dni);                    
                        String fingerprint = rs.getString("fingerprintSource"); adherentImage.setFingerprintSource(fingerprint);
                        String signature = rs.getString("signatureSource"); adherentImage.setSignatureSource(signature);                                            
                        long idPoliticalParty = rs.getLong("id_politicalParty"); adherentImage.setPoliticalParty(idPoliticalParty);
                        long status = rs.getLong("status"); adherentImage.setStatus(status);
                        adherentImageList.add(adherentImage);
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
        return adherentImageList;
    }

    @Override
    public void addAdherentImages(ArrayList<AdherentImage> adherentes) {
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                for(int i = 0; i <  adherentes.size(); i++){
                    AdherentImage ep = adherentes.get(i);
                    
                    String sql = "INSERT INTO adherentImage (nameSource, lastnameSource, dniSource, fingerprintSource, signatureSource, id_politicalParty, status) VALUES(?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);                
                    pstmt.setString(1, ep.getNameSource());                                                
                    pstmt.setString(2, ep.getLastNameSource());
                    pstmt.setString(3, ep.getDniSource());
                    pstmt.setString(4, ep.getFingerprintSource());
                    pstmt.setString(5, ep.getSignatureSource());                
                    pstmt.setLong(6, ep.getPoliticalParty()); 
                    pstmt.setLong(7, ep.getStatus()); 
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

    @Override
    public int queryAmountAdherentImageNoValidatedbyPartyId(long id) {
        // TODO Auto-generated method stub        
        Connection conn = null;
        PreparedStatement pstmt = null;        
        ResultSet rs = null;
        int amount = 0;
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia
                String sql = "Select count(id) as amount from adherentImage where id_politicalParty = ? and status = ?";
                pstmt = conn.prepareStatement(sql); 
                pstmt.setLong(1, id);
                pstmt.setLong(2, 0);
                //Paso 4: Ejecutar la sentencia						
                rs = pstmt.executeQuery();
                //Paso 5:(opc) Procesar los resultado
                while (rs.next()) {
                    amount = rs.getInt("amount");
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
        return amount;
    }
    
    @Override
    public void cancellAllAdherentImages(long idPoliticalParty){
        Connection conn = null;
        PreparedStatement pstmt = null;	
        try {
                //Paso 1: Registrar el Driver
                DriverManager.registerDriver(new SQLServerDriver());
                //Paso 2: Obtener la conexión
                conn = DriverManager.getConnection(DBConnection.URL_JDBC_MYSQL, DBConnection.user, DBConnection.password);
                //Paso 3: Preparar la sentencia

                String sql = "UPDATE adherentImage SET status=? WHERE id_politicalParty=?";
                pstmt = conn.prepareStatement(sql);     
                pstmt.setLong(1, 2);
                pstmt.setLong(2, idPoliticalParty);                   
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
}

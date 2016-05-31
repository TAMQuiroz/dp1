/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author erickelme
 */
public class AdherentImage {
    private long id;
    private String nameSource;
    private String lastNameSource;
    private String dniSource;
    private String fingerprintSource;
    private String signatureSource;
    private PoliticalParty politicalParty;
    
    
    public void AdherentImage(){
        
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    
    
    /**
     * @return the politicalParty
     */
    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    /**
     * @param politicalParty the politicalParty to set
     */
    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }

    /**
     * @return the nameSource
     */
    public String getNameSource() {
        return nameSource;
    }

    /**
     * @param nameSource the nameSource to set
     */
    public void setNameSource(String nameSource) {
        this.nameSource = nameSource;
    }

    /**
     * @return the lastNameSource
     */
    public String getLastNameSource() {
        return lastNameSource;
    }

    /**
     * @param lastNameSource the lastNameSource to set
     */
    public void setLastNameSource(String lastNameSource) {
        this.lastNameSource = lastNameSource;
    }

    /**
     * @return the dniSource
     */
    public String getDniSource() {
        return dniSource;
    }

    /**
     * @param dniSource the dniSource to set
     */
    public void setDniSource(String dniSource) {
        this.dniSource = dniSource;
    }

    /**
     * @return the fingerprintSource
     */
    public String getFingerprintSource() {
        return fingerprintSource;
    }

    /**
     * @param fingerprintSource the fingerprintSource to set
     */
    public void setFingerprintSource(String fingerprintSource) {
        this.fingerprintSource = fingerprintSource;
    }

    /**
     * @return the signatureSource
     */
    public String getSignatureSource() {
        return signatureSource;
    }

    /**
     * @param signatureSource the signatureSource to set
     */
    public void setSignatureSource(String signatureSource) {
        this.signatureSource = signatureSource;
    }
        
   
}

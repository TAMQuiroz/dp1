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
public class PoliticalParty {
    private long id;
    private String name;
    private String legalRepresentative;
    private String image;
    private String telephone;
    private String email;
    private String status;
    private ElectoralProcess electoralProcess;
    
    
    public void PoliticalParty(){
        
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the legalRepresentative
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * @param legalRepresentative the legalRepresentative to set
     */
    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the electoralProcess
     */
    public ElectoralProcess getElectoralProcess() {
        return electoralProcess;
    }

    /**
     * @param electoralProcess the electoralProcess to set
     */
    public void setElectoralProcess(ElectoralProcess electoralProcess) {
        this.electoralProcess = electoralProcess;
    }
}

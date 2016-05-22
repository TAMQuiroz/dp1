/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author erickelme
 */
public class ElectoralProcess {
    private long id;
    private String name;
    private Date date;
    private Date startRegistrationDate;
    private Date endRegistrationDate;
    private Date startReceptionDate;
    private Date endReceptionDate;
    private Date startValidationDate;
    private Date endValidationDate;
    private Date startExtraReceptionDate;
    private Date endExtraReceptionDate;
    private Date startExtraValidationDate;
    private Date endExtraValidationDate;    
    private double minPercentage;
    private String status;
    private int population;
    private ProcessType processType;
    private User user;
    
    
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the startRegistrationDate
     */
    public Date getStartRegistrationDate() {
        return startRegistrationDate;
    }

    /**
     * @param startRegistrationDate the startRegistrationDate to set
     */
    public void setStartRegistrationDate(Date startRegistrationDate) {
        this.startRegistrationDate = startRegistrationDate;
    }

    /**
     * @return the endRegistrationDate
     */
    public Date getEndRegistrationDate() {
        return endRegistrationDate;
    }

    /**
     * @param endRegistrationDate the endRegistrationDate to set
     */
    public void setEndRegistrationDate(Date endRegistrationDate) {
        this.endRegistrationDate = endRegistrationDate;
    }

    /**
     * @return the startReceptionDate
     */
    public Date getStartReceptionDate() {
        return startReceptionDate;
    }

    /**
     * @param startReceptionDate the startReceptionDate to set
     */
    public void setStartReceptionDate(Date startReceptionDate) {
        this.startReceptionDate = startReceptionDate;
    }

    /**
     * @return the endReceptionDate
     */
    public Date getEndReceptionDate() {
        return endReceptionDate;
    }

    /**
     * @param endReceptionDate the endReceptionDate to set
     */
    public void setEndReceptionDate(Date endReceptionDate) {
        this.endReceptionDate = endReceptionDate;
    }

    /**
     * @return the startValidationDate
     */
    public Date getStartValidationDate() {
        return startValidationDate;
    }

    /**
     * @param startValidationDate the startValidationDate to set
     */
    public void setStartValidationDate(Date startValidationDate) {
        this.startValidationDate = startValidationDate;
    }

    /**
     * @return the endValidationDate
     */
    public Date getEndValidationDate() {
        return endValidationDate;
    }

    /**
     * @param endValidationDate the endValidationDate to set
     */
    public void setEndValidationDate(Date endValidationDate) {
        this.endValidationDate = endValidationDate;
    }

    /**
     * @return the startExtraReceptionDate
     */
    public Date getStartExtraReceptionDate() {
        return startExtraReceptionDate;
    }

    /**
     * @param startExtraReceptionDate the startExtraReceptionDate to set
     */
    public void setStartExtraReceptionDate(Date startExtraReceptionDate) {
        this.startExtraReceptionDate = startExtraReceptionDate;
    }

    /**
     * @return the endExtraReceptionDate
     */
    public Date getEndExtraReceptionDate() {
        return endExtraReceptionDate;
    }

    /**
     * @param endExtraReceptionDate the endExtraReceptionDate to set
     */
    public void setEndExtraReceptionDate(Date endExtraReceptionDate) {
        this.endExtraReceptionDate = endExtraReceptionDate;
    }

    /**
     * @return the startExtraValidationDate
     */
    public Date getStartExtraValidationDate() {
        return startExtraValidationDate;
    }

    /**
     * @param startExtraValidationDate the startExtraValidationDate to set
     */
    public void setStartExtraValidationDate(Date startExtraValidationDate) {
        this.startExtraValidationDate = startExtraValidationDate;
    }

    /**
     * @return the endExtraValidationDate
     */
    public Date getEndExtraValidationDate() {
        return endExtraValidationDate;
    }

    /**
     * @param endExtraValidationDate the endExtraValidationDate to set
     */
    public void setEndExtraValidationDate(Date endExtraValidationDate) {
        this.endExtraValidationDate = endExtraValidationDate;
    }

    /**
     * @return the minPercentage
     */
    public double getMinPercentage() {
        return minPercentage;
    }

    /**
     * @param minPercentage the minPercentage to set
     */
    public void setMinPercentage(double minPercentage) {
        this.minPercentage = minPercentage;
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
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the processType
     */
    public ProcessType getProcessType() {
        return processType;
    }

    /**
     * @param processType the processType to set
     */
    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    public void ElectoralProcess(){
        
    }
}

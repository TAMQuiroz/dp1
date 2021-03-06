/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import BusinessModel.DAO.DAOAdherentImage;
import BusinessModel.DAO.DAOFactory;
import BusinessModel.DAO.DBConnection;
import java.util.ArrayList;
import Model.*;
/**
 *
 * @author erickelme
 */
public class AdherentImageDB {
    private ArrayList<AdherentImage> adherentImageList = new ArrayList<AdherentImage>();
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOAdherentImage daoAdherentImage = daoFactory.getDAOAdherentImage();
    
    public AdherentImageDB(){
        
    }
    
    public void add(AdherentImage ep){
        daoAdherentImage.add(ep);
    }
    public void update(AdherentImage ep){        
        daoAdherentImage.update(ep);        
    }
    public void delete(long adherentImageId){
        daoAdherentImage.delete(adherentImageId);
    }
    public ArrayList<AdherentImage> queryAll( long politicalPartyIdP){
        adherentImageList = daoAdherentImage.queryAll(  politicalPartyIdP);
        return adherentImageList;
    }
    public ArrayList<AdherentImage> queryAllRejected(long politicalPartyIdP){
        adherentImageList = daoAdherentImage.queryAllRejected(politicalPartyIdP);
        return adherentImageList;
    }
    public ArrayList<AdherentImage> queryAllCanceled( long politicalPartyIdP){
        adherentImageList = daoAdherentImage.queryAllCanceled( politicalPartyIdP);
        return adherentImageList;
    }
    public void cancellAllAdherentImages(long idPoliticalParty){
        daoAdherentImage.cancellAllAdherentImages(idPoliticalParty);
    }    
    public AdherentImage queryById( long adherentImageId){
        return daoAdherentImage.queryById(adherentImageId);
    }

    public ArrayList<AdherentImage> queryAdherentImageNoValidatedbyPartyId(long id) {
        return daoAdherentImage.queryAdherentImageNoValidatedbyPartyId(id);
    }

    void addAdherentImages(ArrayList<AdherentImage> adherentes) {
        daoAdherentImage.addAdherentImages(adherentes);
    }

    int queryAmountAdherentImageNoValidatedbyPartyId(long id) {
        return daoAdherentImage.queryAmountAdherentImageNoValidatedbyPartyId(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author ishili
 */
public class HistoriqueConsultationClientAction extends Action {

    public HistoriqueConsultationClientAction(Service service) {
        super(service);
    }

       @Override
    public void execute(HttpServletRequest request) 
    {   
        System.out.println("yoyo");
           List <Consultation> consultations = null;
            HttpSession session = request.getSession(false);
        if (session == null 
         || !"client".equals(session.getAttribute("type"))) {
            // pas de client connecté
            request.setAttribute("success", false);
    
            return;
        }
     try{
                    Client sessionClient  = (Client) session.getAttribute("utilisateur");
                    Client client = service.trouverClientParID(sessionClient.getId());
                    consultations=client.getListeConsultations();
            request.setAttribute("consultations", consultations);
            System.out.println("titi");
            for (Iterator it = consultations.iterator(); it.hasNext();) {
                    Consultation m = (Consultation) it.next();      
                System.out.println("#" + m.getId()+m.getEtat()); }
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueConsultationClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
           
           
    }
    
}
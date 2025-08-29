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
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author ishili
 */
public class HistoriqueconsultempAction extends Action{

    public HistoriqueconsultempAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
    System.out.println("yoyoemp");
           List <Consultation> consultations = null;
            HttpSession session = request.getSession();
        if (session == null 
         || !"employe".equals(session.getAttribute("type"))) {
            // pas de client connecté
            request.setAttribute("success", false);
    
            return;
        }
     try{
                         Employe employe = (Employe) session.getAttribute("utilisateur");
                          if(employe==null)
                       {
                            System.out.println("ezab11");
                       }
                     Consultation c=service.consultationEmploye(employe);
                       if(c==null)
                       {
                            System.out.println("ezab");
                       }
                    System.out.println("assba1");
                        
                    Client client = c.getClient();
                     System.out.println("assba2");
                    consultations=client.getListeConsultations();

            request.setAttribute("consultations", consultations);
            
            
            for (Iterator it = consultations.iterator(); it.hasNext();) {
                    Consultation m = (Consultation) it.next();      
                System.out.println("#" + m.getId()); }
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueConsultationClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
    
    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author machakroun
 */

        

package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class GenererPredictionsAction extends Action {

    public GenererPredictionsAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        boolean success = true;
        
        try {
           Long consultationId = Long.parseLong(request.getParameter("consultationId"));
            int amour = Integer.parseInt(request.getParameter("amour"));
            int sante = Integer.parseInt(request.getParameter("sante"));
            int travail = Integer.parseInt(request.getParameter("travail"));

            Consultation consultation = service.trouverConsultationParID(consultationId);
            if (consultation != null) {
                service.genererPredictions(consultation, amour, sante, travail);
                 request.setAttribute("travail", consultation.getPrediction_travail());
                request.setAttribute("amour", consultation.getPrediction_amour());
                request.setAttribute("sante", consultation.getPrediction_sante());
            }
           

           

        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
    request.setAttribute("success", success);
       // pas de création automatique
        
    }
}

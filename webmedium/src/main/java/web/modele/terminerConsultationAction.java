
        

package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class terminerConsultationAction extends Action {

    public terminerConsultationAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        boolean success = false;
      
        try {
            System.out.println(request.getParameter("consultationId"));
            String idParam = request.getParameter("consultationId");
             String commentaire = request.getParameter("commentaire");

      
        if (idParam != null && idParam.matches("\\d+")) {
            Long consultationId = Long.parseLong(idParam);

            Consultation consultation = service.trouverConsultationParID(consultationId);

             if (consultation != null && commentaire != null) {
                    success = service.finirConsultation(consultation, commentaire);
                }
        } else {
            System.err.println("Paramètre consultationId invalide : " + idParam);
        }
           

           

        } catch (Exception e) {
            e.printStackTrace();
        }
    request.setAttribute("success", success);
       // pas de création automatique
        
    }
}

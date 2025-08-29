
        

package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class lancerConsultationAction extends Action {

    public lancerConsultationAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        boolean success = false;
       
        try {
            System.out.println(request.getParameter("consultationId"));
        String idParam = request.getParameter("consultationId");
      
        if (idParam != null && idParam.matches("\\d+")) {
            Long consultationId = Long.parseLong(idParam);

            Consultation consultation = service.trouverConsultationParID(consultationId);

            if (consultation != null) {
                success = service.commencerConsultation(consultation);
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

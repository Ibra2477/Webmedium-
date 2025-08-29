package web.modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;

public class ContacterMediumAction extends Action {

    public ContacterMediumAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        // 1) On vérifie qu’on a une session client
        HttpSession session = request.getSession(false);
        if (session == null 
         || !"client".equals(session.getAttribute("type"))) {
            // pas de client connecté
            request.setAttribute("success", false);
            return;
        }
        System.out.println("kiko");
        Client client = (Client) session.getAttribute("utilisateur");
        Long mediumId;
       
            mediumId = Long.parseLong(request.getParameter("mediumId"));
        
         Medium medium=service.trouverMediumParID((mediumId));
         System.out.println(mediumId);
         System.out.println(medium.toString());
        System.out.println("yoy\n");
        Consultation c = service.demandeConsultation(client, medium);
        
        if (c != null) {
            // une consultation a bien été créée
            request.setAttribute("success", true);
            request.setAttribute("consultation", c);
            System.out.println("done");
        } else {
            // pas d’employé dispo → on renvoie success = false
            request.setAttribute("success", false);
        }
    }
}

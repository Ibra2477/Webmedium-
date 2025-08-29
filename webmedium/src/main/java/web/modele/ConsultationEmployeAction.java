package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class ConsultationEmployeAction extends Action {

    public ConsultationEmployeAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // pas de création automatique
        if (session != null && session.getAttribute("utilisateur") != null) {
            System.out.println("here");
            Employe employe = (Employe) session.getAttribute("utilisateur");
            Consultation c=service.consultationEmploye(employe);
            if(c!=null){
                request.setAttribute("success", true);
            request.setAttribute("consultation", c);
            System.out.println(c.toString());
            }
            else{
                request.setAttribute("success", false);
            }
            
           
            // Ajoute d'autres champs si besoin
        } else {
            request.setAttribute("success", false);
        }
    }
}

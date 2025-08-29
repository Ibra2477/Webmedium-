package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.Service;

public class InfosEmployeAction extends Action {

    public InfosEmployeAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // pas de création automatique
        if (session != null && session.getAttribute("utilisateur") != null) {
            System.out.println("here");
            Employe employe = (Employe) session.getAttribute("utilisateur");
          
        request.setAttribute("success", true);
        request.setAttribute("employe", employe);
            System.out.println(employe.toString());
            System.out.println(request.getAttribute("employe").toString());
           
            // Ajoute d'autres champs si besoin
        } else {
            request.setAttribute("success", false);
        }
    }
}

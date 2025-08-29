package web.modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.service.Service;

public class DeconnexionAction extends Action {

    public DeconnexionAction(Service service) {
        super(service);
    }
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // ne crée pas de session si elle n'existe pas
        if (session != null) {
            session.invalidate(); // détruit la session
             System.out.println("deconnecté");
        }
        request.setAttribute("success", true);
       
    }


}

package web.modele;



import javax.servlet.http.*;
import metier.modele.Client;
import metier.service.Service;

public class InfosClientAction extends Action {

    public InfosClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // pas de création automatique
        if (session != null && session.getAttribute("utilisateur") != null) {
            System.out.println("here");
            Client client = (Client) session.getAttribute("utilisateur");
          
        request.setAttribute("success", true);
        request.setAttribute("client", client);
            System.out.println(client.toString());
            System.out.println(request.getAttribute("client").toString());
           
            // Ajoute d'autres champs si besoin
        } else {
            request.setAttribute("success", false);
        }
    }
}

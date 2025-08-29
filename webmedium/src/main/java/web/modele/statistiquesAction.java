
package web.modele;



import java.util.List;
import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;

public class statistiquesAction extends Action {

    public statistiquesAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        boolean success = true;
      
        try {
             List<Medium> mediums = service.statistiquesMediums();
        List<Employe> employes = service.statistiquesEmployes();

        request.setAttribute("mediums", mediums);
        request.setAttribute("employes", employes);
      
        
           

           

        } catch (Exception e) {
                     success = false;

            e.printStackTrace();
        }
    request.setAttribute("success", success);
       // pas de création automatique
        
    }
}

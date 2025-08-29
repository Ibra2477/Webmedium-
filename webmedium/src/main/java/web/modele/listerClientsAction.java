
/**
 *
 * @author machakroun
 */


        

package web.modele;



import java.util.List;
import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class listerClientsAction extends Action {

    public listerClientsAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        boolean success = false;
      
        try {
            
           
            

       List<Client> clients = service.listerClients();
        success=clients!=null;
        
        if(success){
            System.out.println("yihii");
             request.setAttribute("clients", clients);
    request.setAttribute("success", clients != null);
        }

           

        } catch (Exception e) {
            e.printStackTrace();
        }
    request.setAttribute("success", success);
       // pas de création automatique
        
    }
}



/**
 *
 * @author machakroun
 */

package web.vue;

import com.google.gson.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
public class listerClientsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);
        if(success){
            System.out.println("yoho");
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            JsonArray jsonListeClients = new JsonArray();
    for (Client c : clients) {
    JsonObject jsonClient = new JsonObject();
    jsonClient.addProperty("nom", c.getNom());
        System.out.println(c.toString());
    jsonClient.addProperty("Id", c.getId());
    jsonClient.addProperty("latitude", c.getLatitude());
      jsonClient.addProperty("longitude", c.getLongitude());
    jsonClient.addProperty("prenom", c.getPrenom());
    jsonClient.addProperty("consultations",c.getListeConsultations().size());
    
jsonListeClients.add(jsonClient);
        }
    jsonContainer.add("clients", jsonListeClients);
    
        }

     
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

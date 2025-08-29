package web.vue;

import com.google.gson.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.Date;
import metier.modele.Client;
import metier.modele.Consultation;
public class ConsultationEmployeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);

        if (success != null && success) {
            System.out.println("hiii");
           Consultation c = (Consultation) request.getAttribute("consultation");
           if (c != null) {
                JsonObject jsonConsultation = new JsonObject();
            Client client = c.getClient();
            JsonObject jsonClient = new JsonObject();
                String nomClient = client.getPrenom() + " " + client.getNom();
                 jsonClient.addProperty("nom", nomClient);
                jsonClient.addProperty("zodiac", client.getZodiac());
jsonClient.addProperty("chinois", client.getChinois());
jsonClient.addProperty("couleur", client.getCouleur());
jsonClient.addProperty("animal", client.getAnimal());
         

               
                            String medium = c.getMedium().getDenomination();

            jsonConsultation.add("client", jsonClient);  // <--- ici
            jsonConsultation.addProperty("medium", medium);
            jsonConsultation.addProperty("idConsultation", c.getId());
               System.out.println("tototootototot "+c.getId());
            jsonContainer.add("consultation", jsonConsultation);

            } else {
                jsonContainer.addProperty("success", false);
                jsonContainer.addProperty("message", "Aucune consultation en cours.");
            }



           
        }

       
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

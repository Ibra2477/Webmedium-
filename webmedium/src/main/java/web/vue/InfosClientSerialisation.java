package web.vue;

import com.google.gson.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.Date;
import metier.modele.Client;
public class InfosClientSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);

        if (success != null && success) {
            System.out.println("uuuuu");
           Client client = (Client) request.getAttribute("client");
           JsonObject jsonClient = new JsonObject();
           jsonClient.addProperty("nom", client.getNom());
jsonClient.addProperty("prenom", client.getPrenom());
jsonClient.addProperty("mail", client.getMail());
jsonClient.addProperty("adresse", client.getAdresse());
jsonClient.addProperty("tel", client.getTel());
jsonClient.addProperty("zodiac", client.getZodiac());
jsonClient.addProperty("chinois", client.getChinois());
jsonClient.addProperty("couleur", client.getCouleur());
jsonClient.addProperty("animal", client.getAnimal());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

String dateString = sdf.format(client.getDate_naissance());
jsonClient.addProperty("date", dateString);

// N'ajoute pas listeConsultations

jsonContainer.add("client", jsonClient);
          System.out.println(jsonContainer.get("client"));

           
        }

       
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

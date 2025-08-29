

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
import metier.modele.Employe;
import metier.modele.Medium;
public class statistiquesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);

     
if(success){
    List<Medium> mediums = (List<Medium>) request.getAttribute("mediums");
        List<Employe> employes = (List<Employe>) request.getAttribute("employes");
        JsonArray mediumsJson = new JsonArray();
        for (Medium medium : mediums) {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("nom", medium.getDenomination());
            jsonMedium.addProperty("nbConsultations", medium.getNbConsultations());
            mediumsJson.add(jsonMedium);
        }
        JsonArray employesJson = new JsonArray();
        for (Employe employe : employes) {
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("id", employe.getId());
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            jsonEmploye.addProperty("nbConsultations", employe.getNbConsultations());
            employesJson.add(jsonEmploye);
        }
          jsonContainer.add("mediums", mediumsJson);
        jsonContainer.add("employes", employesJson);

}
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

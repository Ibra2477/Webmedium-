package web.vue;

import com.google.gson.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.Date;
import metier.modele.Employe;
public class InfosEmployeSerialisation extends Serialisation {

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
           Employe employe = (Employe) request.getAttribute("employe");
            
           JsonObject jsonEmploye = new JsonObject();
           jsonEmploye.addProperty("nom", employe.getNom());
jsonEmploye.addProperty("prenom", employe.getPrenom());
jsonEmploye.addProperty("mail", employe.getMail());

jsonEmploye.addProperty("tlf", employe.getTel());
//jsonEmploye.addProperty("genre", employe.getGenre());

jsonEmploye.addProperty("genre", employe.getGenre().name());


// N'ajoute pas listeConsultations

        jsonContainer.add("employe", jsonEmploye);
          System.out.println(jsonContainer.get("employe"));

           
        }

       
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

package web.vue;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

public class ConnexionSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
          response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();
    Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success != null ? success : false);
        System.out.println("hiiii\n");
              System.out.println("succe:"+success);
         if (success != null && success) {
            HttpSession session = request.getSession(false);
             System.out.println("testtt\n");
            if (session != null) {
                String type = (String) session.getAttribute("type");
                jsonContainer.addProperty("type", type);

                if ("client".equals(type)) {
                    metier.modele.Client client = (metier.modele.Client) session.getAttribute("utilisateur");
                    jsonContainer.addProperty("nom", client.getNom());
                    jsonContainer.addProperty("prenom", client.getPrenom());
                } else if ("employe".equals(type)) {
                    metier.modele.Employe emp = (metier.modele.Employe) session.getAttribute("utilisateur");
                    jsonContainer.addProperty("nom", emp.getNom());
                    jsonContainer.addProperty("prenom", emp.getPrenom());
                }
            }
        }    
        
        
        
        
  out.println(new Gson().toJson(jsonContainer));
        out.close();
        
     
      
    }
}

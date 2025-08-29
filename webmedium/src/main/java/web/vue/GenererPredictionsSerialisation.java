

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
import metier.modele.Client;
import metier.modele.Consultation;
public class GenererPredictionsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);
        if (success) {
        
        JsonObject jsonPreds = new JsonObject();
        jsonPreds.addProperty("amour", (String) request.getAttribute("amour"));
        jsonPreds.addProperty("sante", (String) request.getAttribute("sante"));
        jsonPreds.addProperty("travail", (String) request.getAttribute("travail"));
        jsonContainer.add("predictions", jsonPreds);
    }


     
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

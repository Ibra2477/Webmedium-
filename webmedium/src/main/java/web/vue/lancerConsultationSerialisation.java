package web.vue;

import com.google.gson.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.Date;
import metier.modele.Client;
import metier.modele.Consultation;
public class lancerConsultationSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();

        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);

     
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

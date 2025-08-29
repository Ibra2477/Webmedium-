/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

/**
 *
 * @author ishili
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Consultation;
public class ContacterMediumSerialisation extends Serialisation{

   
      @Override
      public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setContentType("application/json;charset=UTF-8");
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();
     
        
        Boolean success = (Boolean) request.getAttribute("success");
        jsonContainer.addProperty("success", success);

        Consultation consultation = (Consultation) request.getAttribute("consultation");

       
        if (consultation != null) {
            System.out.println("hereee");
            jsonContainer.addProperty("consultation",  true);
             jsonContainer.addProperty("consultationId",  consultation.getId());
             jsonContainer.addProperty("nomMedium",consultation.getMedium().getDenomination());
           
        } else {
            jsonContainer.addProperty("consultation", false);
        }
       
        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}


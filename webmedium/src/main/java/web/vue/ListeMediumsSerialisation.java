/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Medium;
import metier.modele.Spirite;

/**
 *
 * @author ishili
 */
public class ListeMediumsSerialisation extends Serialisation 
{

@Override
public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();
    List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonObject jsonContainer = new JsonObject();

    JsonArray jsonListeMediums = new JsonArray();
    for (Medium m : mediums) {
    JsonObject jsonmedium = new JsonObject();
    jsonmedium.addProperty("Denomination", m.getDenomination());
    jsonmedium.addProperty("Id", m.getId());
    jsonmedium.addProperty("Presentation", m.getPresentation());
    if(m instanceof Spirite){
            jsonmedium.addProperty("Type", "Spirite");
    }
    else if (m instanceof Astrologue){
            jsonmedium.addProperty("Type", "Astrologue");
    }
    else {
            jsonmedium.addProperty("Type", "Cartomancien");
    }

    

    
    jsonListeMediums.add(jsonmedium);
    }
    jsonContainer.add("mediums", jsonListeMediums);
    JsonObject jsonResultat = new JsonObject();
    jsonResultat.add("container", jsonContainer);
    out.println(gson.toJson(jsonResultat));
        out.close();
    }

}

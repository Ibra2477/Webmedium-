/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author imarcil
 */
public class InscriptionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();
         jsonContainer.addProperty("success", (Boolean) request.getAttribute("resultat"));
        if (request.getAttribute("message") != null) {
            jsonContainer.addProperty("message", (String) request.getAttribute("message"));
        }

        out.println(new Gson().toJson(jsonContainer));
        out.close();
    }
}

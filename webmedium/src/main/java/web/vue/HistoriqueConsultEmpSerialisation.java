/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ishili
 */
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
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Consultation;
import metier.modele.Consultation.Etat;
import metier.modele.Spirite;

/**
 *
 * @author ishili
 */
public class HistoriqueConsultEmpSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("uooo");
          response.setContentType("application/json;charset=UTF-8");
        
        
         PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonContainer = new JsonObject();
        List<Consultation> consultations = (List<Consultation>)request.getAttribute("consultations");
        System.out.println("ca");

        
        JsonArray jsonListeConsultations = new JsonArray();
        System.out.println("pp");
        for (Iterator it = consultations.iterator(); it.hasNext();){
            System.out.println("noi");
            Consultation m = (Consultation) it.next(); 
             if (m.getEtat() == Etat.TERMINEE){
                 JsonObject jsonconsultation = new JsonObject();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(m.getDate_consult());
       
        jsonconsultation.addProperty("Date_consult", formatted);
        
        jsonconsultation.addProperty("Id", m.getId());
        jsonconsultation.addProperty("Medium_consult", m.getMedium().getDenomination());
        String s1 =m.getEmploye().getPrenom();
        String s2= m.getEmploye().getNom();
        String s3 = s1.concat(s2);
        jsonconsultation.addProperty("Emp",s3);
        jsonconsultation.addProperty("Commentaire",m.getCommentaire());

        System.out.println("bb");
        jsonListeConsultations.add(jsonconsultation);
            }
        }
       
        
        jsonContainer.add("consultations", jsonListeConsultations);
  
              jsonContainer.addProperty("success", true);
        out.println(gson.toJson(jsonContainer));
            out.close();
        }


   }
    



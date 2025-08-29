/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.service.Service;
import metier.modele.Client;

/**
 *
 * @author imarcil
 */
public class InscrireClientAction extends Action{

    public InscrireClientAction(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        try {
            //recuperer les infos du clients grace au formulaire html 
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String date_naissance= request.getParameter("date_naissance");
            String adresse= request.getParameter("adresse");
            String num_tel= request.getParameter("num_tel");
            String mail= request.getParameter("mail");
            String password= request.getParameter("password");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(date_naissance);
            //creer le client Client 
            Client client= new Client(nom,prenom,date,adresse,num_tel,mail,password);
            System.out.println("Le client a inscrire est " + client.toString());

            //Appeler le service inscrireClient
            Boolean success=  service.inscrireClient(client);
            System.out.println("Le client   est " + client.toString());
            
            request.setAttribute("resultat", success );
            System.out.println(request.getAttribute("resultat"));

        }
        catch (Exception ex){
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
    request.setAttribute("resultat",false);

          
        }
        
    }
    
}

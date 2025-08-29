/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author imarcil
 */
public class ConnexionAction extends Action{

    public ConnexionAction(Service service) {
        super(service);
    }
    
    
    
    
    @Override
    public void execute(HttpServletRequest request) {
        
        try {
            String mail = request.getParameter("mail") ;
            String password =request.getParameter("password");
            
            if (mail.contains("@predict.if")) {
                Employe emp=service.authentifierEmploye(mail, password);
              
                if (emp!=null){
                   
                    System.out.print("employé connecté "+emp.toString());
                    request.setAttribute("success", true);
                    request.setAttribute("type", "employe");
                    HttpSession session = request.getSession(true);
                session.setAttribute("utilisateur", emp);
                session.setAttribute("type", "employe");
                    System.out.println("session creé");
                       
                
                    
                }
                else {
                    System.out.print("connexion échouée");
                    request.setAttribute("type", "error");
                }
                
            }
            else {
                
                Client client=service.authentifierClient(mail, password);
                
                if (client!=null){
                     request.setAttribute("success", true);
                    System.out.print("client connecté "+client.toString());
                    System.out.println("");
                    request.setAttribute("type", "client");
                    HttpSession session = request.getSession(true);
                session.setAttribute("utilisateur", client);
                session.setAttribute("type", "client");
                     System.out.println("session creé");
                
                }
                else {
                    System.out.print("connexion échouée");
                    request.setAttribute("type", "error");
                }
            }
        }
        catch(Exception ex){
                Logger.getLogger(ConnexionAction.class.getName()).log(Level.SEVERE, null, ex);
            
        }    System.out.println("pipp\n");
    }
    
}

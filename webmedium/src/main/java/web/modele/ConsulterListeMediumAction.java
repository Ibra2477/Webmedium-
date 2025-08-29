/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import static console.Main.printlnConsoleIHM;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.modele.Spirite;
import metier.service.Service;

/**
 *
 * @author ishili
 */
public class ConsulterListeMediumAction extends Action
{

    public ConsulterListeMediumAction(Service service) {
        super(service);
    }

    

    
  
    @Override
    public void execute(HttpServletRequest request) 
    {
           List <Medium> mediums;
        try {
            mediums = service.listerMediums();
            request.setAttribute("mediums", mediums);
            for (Iterator it = mediums.iterator(); it.hasNext();) {
                 Medium m = (Medium) it.next();      
                System.out.println("#" + m.getDenomination()); }
        } catch (Exception ex) {
            Logger.getLogger(ConsulterListeMediumAction.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
           
    } } 
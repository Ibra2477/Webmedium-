package web.controleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.service.Service;
import web.modele.ConnexionAction;
import web.modele.ConsultationEmployeAction;
import web.modele.ConsulterListeMediumAction;
import web.modele.ContacterMediumAction;
import web.modele.DeconnexionAction;
import web.modele.GenererPredictionsAction;
import web.modele.HistoriqueConsultationClientAction;
import web.modele.HistoriqueconsultempAction;
import web.modele.InfosClientAction;
import web.modele.InfosEmployeAction;
import web.modele.InscrireClientAction;
import web.modele.lancerConsultationAction;
import web.modele.listerClientsAction;
import web.modele.statistiquesAction;
import web.modele.terminerConsultationAction;
import web.vue.ConnexionSerialisation;
import web.vue.ConsultationEmployeSerialisation;
import web.vue.ContacterMediumSerialisation;
import web.vue.GenererPredictionsSerialisation;
import web.vue.HistoriqueConsultEmpSerialisation;
import web.vue.HistoriqueConsultationClientSerialisation;
import web.vue.InfosClientSerialisation;
import web.vue.InfosEmployeSerialisation;
import web.vue.InscriptionSerialisation;
import web.vue.ListeMediumsSerialisation;
import web.vue.lancerConsultationSerialisation;
import web.vue.listerClientsSerialisation;
import web.vue.statistiquesSerialisation;
import web.vue.terminerConsultationSerialisation;

/**
 *
 * @author ishili
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Service service = new Service() ;
        System.out.println("Trace : todo = " + todo);
        if(todo!=null){
        switch(todo) {
        case "accueil" : {
          
            new ConsulterListeMediumAction(service).execute(request);
            new ListeMediumsSerialisation().appliquer(request, response);
            break;
                }
        case "inscription" : {
          
            new InscrireClientAction(service).execute(request);
            new InscriptionSerialisation().appliquer(request,response);
            break;
            
        }
        case "connexion" : {
    
            new ConnexionAction(service).execute(request);
            new ConnexionSerialisation().appliquer(request, response);
            break;
        }
         case "demandeConsultation" : {
 new ContacterMediumAction(service).execute(request);
            new ContacterMediumSerialisation().appliquer(request, response);
             break;
        }
         case "deconnexion" :{
             new DeconnexionAction(service).execute(request);
             break;
         }
         case "infosClient":{
             new InfosClientAction(service).execute(request);
             new InfosClientSerialisation().appliquer(request,response);
            break;
         }
         case "infosEmploye":{
             new InfosEmployeAction(service).execute(request);
             new InfosEmployeSerialisation().appliquer(request,response);
         break;
         }
         case "consultationEmploye":{
             System.out.println("begin");
              new ConsultationEmployeAction(service).execute(request);
             new ConsultationEmployeSerialisation().appliquer(request,response);
        break;
         }
         case "lancerConsultation":{
             System.out.println("pzndiz");
             new lancerConsultationAction(service).execute(request);
             new lancerConsultationSerialisation().appliquer(request,response);
         break;
         }
         case "terminerConsultation":{
              new terminerConsultationAction(service).execute(request);
             new terminerConsultationSerialisation().appliquer(request,response);
             break;
         }
         case "genererPredictions":{
             new GenererPredictionsAction(service).execute(request);
             new GenererPredictionsSerialisation().appliquer(request,response);
             break;
         }
         case "statistiques":{
              new statistiquesAction(service).execute(request);
             new statistiquesSerialisation().appliquer(request,response);
             break;
         }
         case "listerClients":{
             new listerClientsAction(service).execute(request);
             new  listerClientsSerialisation().appliquer(request,response);
             break;
         }
          case "HistoriqueConsult":{
             new HistoriqueConsultationClientAction(service).execute(request);
             new  HistoriqueConsultationClientSerialisation().appliquer(request,response);
             break;
         }
          case "HistoriqueConsultEmp":{
             new HistoriqueconsultempAction(service).execute(request);
             new  HistoriqueConsultEmpSerialisation().appliquer(request,response);
             break;
         }
         default:{
             
         }
         
            } 
        }   
       
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 @Override
        public void init()
        {
            JpaUtil.creerFabriquePersistance();
        }
        @Override
        public void destroy()
        {
            JpaUtil.fermerFabriquePersistance();
        }
}

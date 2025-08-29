package console;

import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Employe.GenreEmploye;
import metier.modele.Medium;
import metier.modele.Spirite;
import metier.service.Service;


public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        //JpaUtil.desactiverLog();
        JpaUtil.creerFabriquePersistance();
        testerInitialisation(); 
        
        scenarioComplet();
//        testerInscrireClients();
//        testerConsulterListes();
//        testerAuthentifierClients();
//        testerRecupInfosClient();
//        testerDemanderConsultation();
//        testerStatistiques();
        JpaUtil.fermerFabriquePersistance();
    }
    
    private static void scenarioComplet() throws ParseException {
    
        // Récupération de la date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "11-05-1853";
        Date date = sdf.parse(dateInString);
        
        // Inscription d'un client
        Service service = new Service();
        Client c1 = new Client("Hugo", "Victor", date, "31 rue des fleurs", "0698769534", "victor.hugo@gmail.com", "esmeralda" );
        Boolean resultat1 = service.inscrireClient(c1);
        printlnConsoleIHM(resultat1 + " -> Inscription client C1 " + c1);
        
        // Deuxième inscription qui échoue : le mail doit être unique
        Client c2 = new Client("Bernard", "Luc", date, "31 rue des fleurs", "0698769534", "victor.hugo@gmail.com", "esmeralda" );
        Boolean resultat2 = service.inscrireClient(c2);
        printlnConsoleIHM(resultat2 + " -> Inscription client C2 " + c2);
        
        // Deuxième inscription qui réussit
        c2 = new Client("Bernard", "Luc", date, "31 rue des fleurs", "0698769534", "luc.bernard@gmail.com", "esmeralda" );
        resultat2 = service.inscrireClient(c2);
        printlnConsoleIHM(resultat2 + " -> Inscription client C2 " + c2);
        
        //--------------------------------------------- SESSION DIFFERENTE -----------------------------------------------------------
        
        // Victor essaie de s'authentifier mais se trompe de mot de passe : erreur
        Client client1 = service.authentifierClient("victor.hugo@gmail.com", "esme");
        if (client1 != null) {printlnConsoleIHM(client1.toString() + " -> Authentification client 1 " );}
        else {printlnConsoleIHM("Echec de l'inscription" );}
        
        
        // Victor et Luc s'authentifient avec succès
        client1 = service.authentifierClient("victor.hugo@gmail.com", "esmeralda");
        if (client1 != null) {printlnConsoleIHM(client1.toString() + " -> Authentification client 1 " );}
        else {printlnConsoleIHM("Echec de l'inscription" );}
        Client client2 = service.authentifierClient("luc.bernard@gmail.com", "esmeralda");
        if (client2 != null) {printlnConsoleIHM(client2.toString() + " -> Authentification client 2 " );}
        else {printlnConsoleIHM("Echec de l'inscription" );}
        
        // A NE PAS METTRE DANS L'IHM : fonction de test, sert juste à ne laisser qu'un employé disponible
        dispoEmploye();
        
        // Victor affiche la page d'accueil
        List<Medium> mediums = service.listerMediums();
        
        // Affichage mais ce sera mieux fait par l'IHM
        if (mediums == null) {
            printlnConsoleIHM("ERREUR du Service listerMediums");
        } else {
            printlnConsoleIHM("Liste des Mediums (" + mediums.size() + ")");
            for (Medium m : mediums) {
                printlnConsoleIHM("#" + m.getId() + " " + m.getDenomination());
            }
            printlnConsoleIHM("----");
        }
        
        // Victor demande une consultation avec le médium d'id 7 (avec le dernier employé disponible)
        long idMediumChoisi = 7;
        Consultation consult1 = service.demandeConsultation(client1, service.trouverMediumParID(idMediumChoisi));
        
        if (consult1 != null) {
            printlnConsoleIHM(consult1.toString() + " -> Consultation 1 se lance" );
        }
        else {printlnConsoleIHM("Echec de la demande de consultation" );}
        
        // L'employé Henry se connecte
        Employe employe1 = service.authentifierEmploye("henry.martin@predict.if", "1234");
        if (employe1 != null) {printlnConsoleIHM(employe1.toString() + " -> Authentification employé 1 " );}
        else {printlnConsoleIHM("Echec de l'authentification" );}
        
        // L'IHM vérifie s'il a une consultation en attente
        Consultation consultEmploye = service.consultationEmploye(employe1);
        if (consultEmploye != null) {printlnConsoleIHM(consultEmploye.toString() + " -> Consultation employé " );}
        else {printlnConsoleIHM("Pas de consultation employé" );}
        
        // Sur la page de la consultation, il appuie sur le bouton pour la lancer
        service.commencerConsultation(consultEmploye);
        
        // Pendant la consultation de Victor, Luc demande une consultation avec le médium d'id 8 (erreur car plus d'employé disponible)
        idMediumChoisi = 8;
        Consultation consult2 = service.demandeConsultation(client2, service.trouverMediumParID(idMediumChoisi));
        if (consult2 != null) {
            printlnConsoleIHM(consult2.toString() + " -> Consultation 2 se lance" );
            service.commencerConsultation(consult2);
        }
        else {printlnConsoleIHM("Echec de la demande de consultation" );}
        
        // Henry finit sa consultation avec Victor
        service.finirConsultation(consultEmploye, "Commentaire très utile");
    
        // Luc réessaie
        idMediumChoisi = 8;
        consult2 = service.demandeConsultation(client2, service.trouverMediumParID(idMediumChoisi));
        if (consult2 != null) {
            printlnConsoleIHM(consult2.toString() + " -> Consultation 2 se lance" );
        }
        else {printlnConsoleIHM("Echec de la demande de consultation" );}
        
        // L'IHM de Henry vérifie s'il a une consultation en attente
        consultEmploye = service.consultationEmploye(employe1);
        if (consultEmploye != null) {
            printlnConsoleIHM(consultEmploye.toString() + " -> Consultation employé " );
            service.commencerConsultation(consult2);
        }
        else {printlnConsoleIHM("Pas de consultation employé" );}
        
        // Henry finit sa consultation avec Victor
        service.finirConsultation(consultEmploye, "Commentaire très utile");
    }
    
    private static void testerInitialisation() {
        Service service = new Service();
        service.Initialisation();
    }
    
    private static void dispoEmploye() {
        EmployeDao employeDao = new EmployeDao();
        Employe employe;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            for (int i=0; i<2; i++) {
                employe = employeDao.findFirstAvailable(GenreEmploye.H);
                employe.setDisponible(false);
                employeDao.update(employe);
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }        
        
    }
        
    
    private static void testerRecupInfosClient()throws ParseException {
        Service service = new Service();
        
        long id  = 2;
        Client c = service.trouverClientParID(id);
        if (c == null) {
            printlnConsoleIHM("Erreur, aucun client d'id " + id + " trouvé.");
        } else {
            printlnConsoleIHM(c.getNom() + " -> Client d'id : " + id);
        }
        
    }

    private static void testerAuthentifierClients() throws ParseException {
        Service service = new Service();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "11-05-1853";
        Date date = sdf.parse(dateInString);
        Client c1 = new Client("Hugo", "Victor", date, "31 rue des fleurs", "0698769534", "victor.hugo@gmail.com", "esmeralda" );
        
        Client client1 = service.authentifierClient(c1.getMail(), c1.getMotDePasse());
        printlnConsoleIHM(client1.toString() + " -> Authentification client C1 " );
        Client client2 = service.authentifierClient(c1.getMail(), "nana");
        printlnConsoleIHM(client2.toString() + " -> Authentification client C1 " );
        
    }
   
    private static void testerDemanderConsultation() throws ParseException, IOException {
        Service service = new Service();
        
        long idClient = 1;
        long idMedium = 8 ;
        Client c = service.trouverClientParID(idClient);
        Medium m = service.trouverMediumParID(idMedium);
        
        Consultation consult = service.demandeConsultation(c, m);
        if (consult != null) {
            printlnConsoleIHM(consult.toString() + "-> Demande de consultation");
            long id = 11;
            Consultation consultation = service.trouverConsultationParID(id);
            printlnConsoleIHM(consultation.toString());

            service.genererPredictions(consultation, 1, 2, 4);
            printlnConsoleIHM("Génération des prédictions :" + consultation.toString());
            
            service.commencerConsultation(consultation);
            service.finirConsultation(consultation, "c'était super !");
            
        } else {
            printlnConsoleIHM("-> Demande de consultation échouée");
        }
        
//        List<Consultation> consultations = c.getListeConsultations();
//        for (Consultation c1 : consultations) {
//            printlnConsoleIHM("Consultation du client d'id " +c.getId()+ c1.toString());
//        }
    }
    
    private static void testerInscrireClients() throws ParseException, IOException {
        Service service = new Service();

        printlnConsoleIHM("Inscription Client C1");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "11-05-1853";
        Date date = sdf.parse(dateInString);
        Client c1 = new Client("Hugo", "Victor", date, "31 rue des fleurs", "0698769534", "victor.hugo@gmail.com", "esmeralda" );
        Boolean resultat1 = service.inscrireClient(c1);
        printlnConsoleIHM(resultat1 + " -> Inscription client C1 " + c1);
        
        Client c2 = new Client("Bernard", "Luc", date, "31 rue des fleurs", "0698769534", "victor.hugo@gmail.com", "esmeralda" );
        Boolean resultat2 = service.inscrireClient(c2);
        printlnConsoleIHM(resultat2 + " -> Inscription client C1 " + c2);
        
    
//        printlnConsoleIHM("Inscription Client C2");
//        Client c2 = new Client("Zola", "Emile", "emile.zola@gmail.com", "nana");
//        Boolean resultat2 = service.inscrireClient(c2);
//        printlnConsoleIHM(resultat2 + " -> Inscription client C2 " + c2);
//
//        printlnConsoleIHM("Inscription Client C3");
//        Client c3 = new Client("Ada", "Lovelace", "a.lovelace@gmail.com", "augusta");
//        Boolean resultat3 = service.inscrireClient(c3);
//        printlnConsoleIHM(resultat3 + " -> Inscription client C3 " + c3);
//
//        // Cas d'un doublon de mail -> le service doit échouer
//        printlnConsoleIHM("Inscription Client C4");
//        Client c4 = new Client("Arliss", "Lovelace", "a.lovelace@gmail.com", "toto");
//        Boolean resultat4 = service.inscrireClient(c4);
//        printlnConsoleIHM(resultat4 + " -> Inscription client C4 " + c4);
    }

    private static void testerConsulterListes() {
        Service service = new Service();
        List<Client> clients = service.listerClients();

        if (clients == null) {
            printlnConsoleIHM("ERREUR du Service listerClients");
        } else {
            printlnConsoleIHM("Liste des Clients (" + clients.size() + ")");
            
            for (Client c : clients) {
                printlnConsoleIHM("#" + c.getId() + " " + c.getNom().toUpperCase() + " " + c.getPrenom());
            }
            printlnConsoleIHM("----");
        }
        
        List<Employe> employes = service.listerEmployes();

        if (employes == null) {
            printlnConsoleIHM("ERREUR du Service listerEmployes");
        } else {
            printlnConsoleIHM("Liste des Employés (" + employes.size() + ")");
            
            for (Employe e : employes) {
                printlnConsoleIHM("#" + e.getId() + " " + e.getNom().toUpperCase() + " " + e.getPrenom());
            }
            printlnConsoleIHM("----");
        }
        
        List<Medium> mediums = service.listerMediums();

        if (mediums == null) {
            printlnConsoleIHM("ERREUR du Service listerMediums");
        } else {
            printlnConsoleIHM("Liste des Mediums (" + mediums.size() + ")");
            
            for (Medium m : mediums) {
                printlnConsoleIHM("#" + m.getId() + " " + m.getDenomination());
            }
            printlnConsoleIHM("----");
        }
    }
    
    public static void testerStatistiques() {
        Service service = new Service();
        List<Medium> mediums = service.statistiquesMediums();

        if (mediums == null) {
            printlnConsoleIHM("ERREUR du Service statistiquesMediums");
        } else {
            printlnConsoleIHM("Statistiques des Mediums (" + mediums.size() + ")");
            
            for (Medium m : mediums) {
                printlnConsoleIHM("#" + m.getId() + " " + m.getDenomination() + " " + m.getNbConsultations());
            }
            printlnConsoleIHM("----");
        }
        
        List<Employe> employes = service.statistiquesEmployes();

        if (mediums == null) {
            printlnConsoleIHM("ERREUR du Service statistiquesEmployes");
        } else {
            printlnConsoleIHM("Statistiques des Employes (" + mediums.size() + ")");
            
            for (Employe e : employes) {
                printlnConsoleIHM("#" + e.getId() + " " + e.getNom().toUpperCase() + " " + e.getPrenom() + " " + e.getNbConsultations());
            }
            printlnConsoleIHM("----");
        }
    }

    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }

}
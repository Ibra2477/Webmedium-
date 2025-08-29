/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import com.google.maps.model.LatLng;
import static console.Main.printlnConsoleIHM;
import metier.modele.Client;
import util.Message;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import java.util.List;
import dao.JpaUtil;
import dao.MediumDao;
import java.io.IOException;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Employe.GenreEmploye;
import metier.modele.Medium;
import metier.modele.Medium.GenreMedium;
import metier.modele.Spirite;
import util.AstroNetApi;
import util.GeoNetApi;

/**
 *
 * @author naknin
 */
public class Service {

    public Service() {
    }

    public Boolean Initialisation() {
        EmployeDao employeDao = new EmployeDao();
        MediumDao mediumDao = new MediumDao();
        Boolean success = true;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            employeDao.create(new Employe("DUPUIT", "Paul", "paul.dupuit@predict.if", "1234", "06060606", GenreEmploye.H));
            employeDao.create(new Employe("DUBUISSON", "Jean", "jean.dubuisson@predict.if", "1234", "06060605", GenreEmploye.H));  
            employeDao.create(new Employe("DUPUIT", "Jacqueline", "jacqueline.dupuit@predict.if", "1234", "06060607", GenreEmploye.F));
            employeDao.create(new Employe("MARTIN", "Henry", "henry.martin@predict.if", "1234", "06060608", GenreEmploye.H));
            employeDao.create(new Employe("DUVERGER", "Pauline", "pauline.duverger@predict.if", "1234", "06060609", GenreEmploye.F));
            
            mediumDao.create(new Spirite("Mme Irma", GenreMedium.F, "PresIrma", 10, "Cristal"));
            mediumDao.create(new Cartomancien("Maître Jacques", GenreMedium.H, "PresJacques", 30));
            mediumDao.create(new Astrologue("Prince Edouard", GenreMedium.H, "PresEdouard", 0, "Architecte", "1820"));
            
            JpaUtil.validerTransaction();
            
        } catch (Exception ex) {
            success = false;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return success;
    }

    public Consultation consultationEmploye(Employe employe) {
        Consultation c = null;
        ConsultationDao consultationDao = new ConsultationDao();
        try {
            JpaUtil.creerContextePersistance();
            c = consultationDao.findAccepteeByIdEmploye(employe);
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Boolean inscrireClient(Client client) {
        Boolean success = true;
        ClientDao clientDao = new ClientDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            clientDao.create(client);
            AstroNetApi astroApi = new AstroNetApi();

            List<String> profil = astroApi.getProfil(client.getPrenom(), client.getDate_naissance());
            client.setZodiac(profil.get(0));
            client.setChinois(profil.get(1));
            client.setCouleur(profil.get(2));
            client.setAnimal(profil.get(3));
            
            GeoNetApi geoApi = new GeoNetApi();
            LatLng coord = geoApi.getLatLng(client.getAdresse());
            client.setLatitude(coord.lat);
            client.setLatitude(coord.lng);
            
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            success = false;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if (success) {
            // à regarder
            Message.envoyerMail("contact@predict.if", client.getMail(), "Bienvenue chez PREDICT'IF", "Bonjour "+client.getPrenom()+ ", nous vous confirmons"
                    + " votre inscription au service PREDICT'IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique"
                    + " et profiter des dons incroyables de nos mediums");
        } else {
            Message.envoyerMail("contact@predict.if", client.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour "+client.getPrenom()+ ", votre"
                    + " inscription au service PREDICT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
        }
        return success;
    }

    public List<Client> listerClients() {
        List<Client> liste = null;
        ClientDao clientDao = new ClientDao();
        
        try {
            JpaUtil.creerContextePersistance();
            liste = clientDao.findAll();
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return liste;
    }
    
    public List<Medium> listerMediums() {
        List<Medium> liste = null;
        MediumDao mediumDao = new MediumDao();
        
        try {
            JpaUtil.creerContextePersistance();
            liste = mediumDao.findAll();
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return liste;
    }
    
    public List<Employe> listerEmployes() {
        List<Employe> liste = null;
        EmployeDao employeDao = new EmployeDao();
        
        try {
            JpaUtil.creerContextePersistance();
            liste = employeDao.findAll();
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return liste;
    }
    
    public Client trouverClientParID(Long id) {
        Client c = null;
        ClientDao clientDao = new ClientDao();
        
        try {
            JpaUtil.creerContextePersistance();
            c = clientDao.findById(id);
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Client authentifierClient(String mail, String motDePasse) {
        ClientDao clientDao = new ClientDao();
        Client client = null;
        Boolean success = true;
        
        try {
            JpaUtil.creerContextePersistance();
            client = clientDao.findByMail(mail);
            
            if (client == null){
                success = false;
            }
            if (!client.getMotDePasse().equals(motDePasse)) {
                success = false;
            }
                
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if (!success) {
            client = null;
        }
        return client;
    }
    
    public Employe authentifierEmploye(String mail, String motDePasse) {
        EmployeDao employeDao = new EmployeDao();
        Employe employe = null;
        Boolean success = true;
        
        try {
            JpaUtil.creerContextePersistance();
            employe = employeDao.findByMail(mail);
            
            if (employe == null){
                success = false;
            }
            if (!employe.getMotDePasse().equals(motDePasse)) {
                success = false;
            }
                
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if (!success) {
            employe = null;
        }
        return employe;
    }
        
    public Consultation demandeConsultation(Client client, Medium medium) {
        ConsultationDao consultDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        MediumDao mediumDao = new MediumDao();
        ClientDao clientDao = new ClientDao();
        Boolean success = true;
        Employe employe = null;
        Consultation consult = null;
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            employe = employeDao.findFirstAvailable(Employe.GenreEmploye.values()[medium.getGenre().ordinal()]);
            if(employe != null) {
                consult = new Consultation(client, medium, employe);
                consultDao.create(consult);

                client.getListeConsultations().add(consult);
                employe.getListeConsultations().add(consult);
                medium.getListeConsultations().add(consult);
                
                employe.setDisponible(false);
                int nbConsultEmploye = employe.getNbConsultations() + 1 ;
                employe.setNbConsultations(nbConsultEmploye);
                int nbConsultMedium = medium.getNbConsultations() + 1 ;
                medium.setNbConsultations(nbConsultMedium);
                
                employeDao.update(employe);
                mediumDao.update(medium);
                clientDao.update(client);
            } else {
                success =false;
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
       
        if (employe != null && client != null && medium != null && success) {
            Message.envoyerNotification(employe.getTel(), "Bonjour "+ employe.getPrenom()+ ". Consultation requise pour " + client.getPrenom() + " " + client.getNom().toUpperCase() + ". Médium à incarner : " + medium.getDenomination());
        }
        
        if (!success) {
            consult = null;
        }
        return consult;
    }
    
    public Boolean commencerConsultation(Consultation consultation) {
        ConsultationDao consultDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        Boolean success = true;

        try { 
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            consultation.setEtat(Consultation.Etat.EN_COURS);
            consultDao.update(consultation);
            
            JpaUtil.validerTransaction();
        } catch (Exception ex) {  
            success = false;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        if (success) {
            Message.envoyerNotification(consultation.getClient().getTel(), "Bonjour "+ consultation.getClient().getPrenom()+ ". " + consultation.getMedium().getDenomination() + " est prêt(e) pour la consultation, vous pouvez appeler le " + consultation.getEmploye().getTel() + ".");
        }
        return success;
    }
    
    public Boolean finirConsultation(Consultation consultation, String commentaire) {
        ConsultationDao consultDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        Boolean success = true;

        try { 
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            Employe employe = consultation.getEmploye();
            employe.setDisponible(true);
            employeDao.update(employe);
            consultation.setCommentaire(commentaire);
            consultation.setEtat(Consultation.Etat.TERMINEE);
            consultDao.update(consultation);
            
            JpaUtil.validerTransaction();
        } catch (Exception ex) {  
            success = false;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return success;
    }
    
    public Consultation trouverConsultationParID(Long id) {
        Consultation c = null;
        ConsultationDao consultationDao = new ConsultationDao();
        
        try {
            JpaUtil.creerContextePersistance();
            c = consultationDao.findById(id);
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Employe trouverEmployeParID(Long id) {
        Employe e = null;
        EmployeDao employeDao = new EmployeDao();
        
        try {
            JpaUtil.creerContextePersistance();
            e = employeDao.findById(id);
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }
    
    public Medium trouverMediumParID(Long id) {
        Medium m = null;
        MediumDao mediumDao = new MediumDao();
        
        try {
            JpaUtil.creerContextePersistance();
            m = mediumDao.findById(id);
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return m;
    }
    
    public void genererPredictions(Consultation consult, int nivAmour, int nivSante, int nivTravail) throws IOException {
        AstroNetApi astroApi = new AstroNetApi();
        ConsultationDao consultDao = new ConsultationDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            if (consult.getClient() != null) {
                
                List<String> predictions = astroApi.getPredictions(consult.getClient().getCouleur(), consult.getClient().getAnimal(), nivAmour, nivSante, nivTravail);
                consult.setPrediction_amour(predictions.get(0));
                consult.setPrediction_sante(predictions.get(1));
                consult.setPrediction_travail(predictions.get(2));
                
                consultDao.update(consult);
            }
            JpaUtil.validerTransaction();
        } catch (Exception ex) {         
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return;
    }
    
    public List<Medium> statistiquesMediums() {
        List<Medium> liste = null;
            MediumDao mediumDao = new MediumDao();

            try {
                JpaUtil.creerContextePersistance();
                liste = mediumDao.findAllByConsultation();
            } catch (Exception ex) {         
                ex.printStackTrace();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
            return liste;
    }
            
    public List<Employe> statistiquesEmployes() {
        List<Employe> liste = null;
            EmployeDao employeDao = new EmployeDao();

            try {
                JpaUtil.creerContextePersistance();
                liste = employeDao.findAllByConsultation();
            } catch (Exception ex) {         
                ex.printStackTrace();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
            return liste;
    }
}

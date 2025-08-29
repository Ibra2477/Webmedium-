/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author naknin
 */
    

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_consult;
    @Column(nullable = false)
    private Etat etat;
    private String commentaire;
    
    private String prediction_amour;
    private String prediction_travail;
    private String prediction_sante;
    
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Employe employe;

    public Consultation() {
        
    }
    
    public Consultation(Client client, Medium medium, Employe employe) {
        this.client = client;
        this.medium = medium;
        this.employe = employe;
        this.etat = Etat.ACCEPTEE;
        this.date_consult = new Date();
        this.prediction_amour = "";
        this.prediction_sante = "";
        this.prediction_travail = "";
        this.commentaire = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_consult() {
        return date_consult;
    }

    public void setDate_consult(Date date_consult) {
        this.date_consult = date_consult;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPrediction_amour() {
        return prediction_amour;
    }

    public void setPrediction_amour(String prediction_amour) {
        this.prediction_amour = prediction_amour;
    }

    public String getPrediction_travail() {
        return prediction_travail;
    }

    public void setPrediction_travail(String prediction_travail) {
        this.prediction_travail = prediction_travail;
    }

    public String getPrediction_sante() {
        return prediction_sante;
    }

    public void setPrediction_sante(String prediction_sante) {
        this.prediction_sante = prediction_sante;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.date_consult);
        hash = 47 * hash + Objects.hashCode(this.etat);
        hash = 47 * hash + Objects.hashCode(this.client);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consultation other = (Consultation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date_consult, other.date_consult)) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (!Objects.equals(this.client.getId(), other.client.getId())) {
            return false;
        }
        return true;
    }

    private Date Date() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", commentaire=" + commentaire + "Client=" + client.getId() + ", Medium=" + medium.getId() + ", Employe=" + employe.getId() + '}';
    }

    
    
    public enum Etat {
    ACCEPTEE,
    EN_COURS,
    TERMINEE
    }
    
    
}

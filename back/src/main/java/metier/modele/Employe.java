/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import metier.modele.Consultation;

/**
 *
 * @author naknin
 */
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private String tel;
    @Column (nullable = false)
    private GenreEmploye genre;
    @Column (nullable = false)
    private int nbConsultations;
    @Column (nullable = false)
    private Boolean disponible;
    
    @OneToMany(mappedBy="employe")
    private List<Consultation> listeConsultations;
    
    public Employe() {
        
    }

    public Employe(String nom, String prenom, String mail, String motDePasse, String tel, GenreEmploye genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.tel = tel;
        this.genre = genre;
        this.nbConsultations = 0;
        this.disponible = true;
        this.listeConsultations = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public GenreEmploye getGenre() {
        return genre;
    }

    public void setGenre(GenreEmploye genre) {
        this.genre = genre;
    }

    public int getNbConsultations() {
        return nbConsultations;
    }

    public void setNbConsultations(int nbConsultations) {
        this.nbConsultations = nbConsultations;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public List<Consultation> getListeConsultations() {
        return listeConsultations;
    }

    public void setListeConsultations(List<Consultation> listeConsultations) {
        this.listeConsultations = listeConsultations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        hash = 67 * hash + Objects.hashCode(this.mail);
        hash = 67 * hash + Objects.hashCode(this.motDePasse);
        hash = 67 * hash + Objects.hashCode(this.tel);
        hash = 67 * hash + Objects.hashCode(this.genre);
        hash = 67 * hash + this.nbConsultations;
        hash = 67 * hash + Objects.hashCode(this.disponible);
        hash = 67 * hash + Objects.hashCode(this.listeConsultations);
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
        final Employe other = (Employe) obj;
        if (this.nbConsultations != other.nbConsultations) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.motDePasse, other.motDePasse)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.genre != other.genre) {
            return false;
        }
        if (!Objects.equals(this.disponible, other.disponible)) {
            return false;
        }
        if (!Objects.equals(this.listeConsultations, other.listeConsultations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", tel=" + tel + ", genre=" + genre + ", nbConsultations=" + nbConsultations + ", disponible=" + disponible + ", listeConsultations=" + listeConsultations + '}';
    }
    
    
    
    public enum GenreEmploye {
        H,
        F
    }
}

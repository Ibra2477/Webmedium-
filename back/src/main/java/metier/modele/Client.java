/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author naknin
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_naissance;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false)
    private String motDePasse;
    private String adresse;
    private double latitude;
    private double longitude;
    @Column(nullable = false)
    private String tel;

    private String zodiac;
    private String chinois;
    private String couleur;
    private String animal;
    
    @OneToMany(mappedBy="client")
    private List<Consultation> listeConsultations;
    
    public Client() {
    }

    public Client( String nom, String prenom, Date date_naissance, String adresse, String tel, String mail, String motDePasse ) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.tel = tel;
        this.listeConsultations = null;
      
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getChinois() {
        return chinois;
    }

    public void setChinois(String chinois) {
        this.chinois = chinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<Consultation> getListeConsultations() {
        return listeConsultations;
    }

    public void setListeConsultations(List<Consultation> listeConsultations) {
        this.listeConsultations = listeConsultations;
    }
    

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", mail=" + mail + ", motDePasse=" + motDePasse + ", adresse=" + adresse + ", tel=" + tel + ", zodiac=" + zodiac + ", chinois=" + chinois + ", couleur=" + couleur + ", animal=" + animal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.nom);
        hash = 73 * hash + Objects.hashCode(this.prenom);
        hash = 73 * hash + Objects.hashCode(this.date_naissance);
        hash = 73 * hash + Objects.hashCode(this.mail);
        hash = 73 * hash + Objects.hashCode(this.motDePasse);
        hash = 73 * hash + Objects.hashCode(this.adresse);
        hash = 73 * hash + Objects.hashCode(this.tel);
        hash = 73 * hash + Objects.hashCode(this.zodiac);
        hash = 73 * hash + Objects.hashCode(this.chinois);
        hash = 73 * hash + Objects.hashCode(this.couleur);
        hash = 73 * hash + Objects.hashCode(this.animal);
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
        final Client other = (Client) obj;
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
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.zodiac, other.zodiac)) {
            return false;
        }
        if (!Objects.equals(this.chinois, other.chinois)) {
            return false;
        }
        if (!Objects.equals(this.couleur, other.couleur)) {
            return false;
        }
        if (!Objects.equals(this.animal, other.animal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date_naissance, other.date_naissance)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }
    

    
   
    
}


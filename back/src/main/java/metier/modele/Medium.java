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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author naknin
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String denomination;
    @Column (nullable = false)
    private GenreMedium genre;
    @Column (nullable = false)
    private int nbConsultations;
    @Column (nullable = false)
    private String presentation;
    
    @OneToMany(mappedBy="medium")
    private List<Consultation> listeConsultations;
    
    public Medium() {
        
    }

    public Medium(String denomination, GenreMedium genre, String presentation, int nbConsultations) {
        this.denomination = denomination;
        this.genre = genre;
        this.nbConsultations = nbConsultations;
        this.presentation = presentation;
        this.listeConsultations = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public GenreMedium getGenre() {
        return genre;
    }

    public void setGenre(GenreMedium genre) {
        this.genre = genre;
    }

    public int getNbConsultations() {
        return nbConsultations;
    }

    public void setNbConsultations(int nbConsultations) {
        this.nbConsultations = nbConsultations;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
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
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.denomination);
        hash = 29 * hash + Objects.hashCode(this.genre);
        hash = 29 * hash + this.nbConsultations;
        hash = 29 * hash + Objects.hashCode(this.presentation);
        hash = 29 * hash + Objects.hashCode(this.listeConsultations);
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
        final Medium other = (Medium) obj;
        if (this.nbConsultations != other.nbConsultations) {
            return false;
        }
        if (!Objects.equals(this.denomination, other.denomination)) {
            return false;
        }
        if (!Objects.equals(this.presentation, other.presentation)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.genre != other.genre) {
            return false;
        }
        if (!Objects.equals(this.listeConsultations, other.listeConsultations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", nbConsultations=" + nbConsultations + ", presentation=" + presentation + ", listeConsultations=" + listeConsultations + '}';
    }
    
    public enum GenreMedium {
        H,
        F
    }
}

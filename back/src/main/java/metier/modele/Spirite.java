/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import metier.modele.Medium.GenreMedium;

/**
 *
 * @author naknin
 */
@Entity
public class Spirite extends Medium {
    @Column(nullable = false)
    private String support;
    
    public Spirite() {
    }

    public Spirite( String denomination, GenreMedium genre, String presentation, int nbConsultations, String support) {
        super(denomination, genre, presentation, nbConsultations);
        this.support = support;
    }
    
    
}

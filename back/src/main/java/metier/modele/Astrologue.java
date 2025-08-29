/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author naknin
 */
@Entity
public class Astrologue extends Medium {
    @Column(nullable = false)
    private String formation;
    @Column(nullable = false)
    private String promotion;

    public Astrologue() {
    }

    public Astrologue( String denomination, GenreMedium genre, String presentation, int nbConsultations,String formation, String promotion) {
        super(denomination, genre, presentation, nbConsultations);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author naknin
 */
@Entity
public class Cartomancien extends Medium {

    public Cartomancien() {
    }
    
    public Cartomancien(String denomination, GenreMedium genre, String presentation, int nbConsultations) {
        super(denomination, genre, presentation, nbConsultations);
    }
    
}

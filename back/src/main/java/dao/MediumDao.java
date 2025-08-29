/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Medium;

/**
 *
 * @author naknin
 */
public class MediumDao {
    public MediumDao() {
       
    }

    public void create(Medium medium) {
        JpaUtil.obtenirEntityManager().persist(medium);
    } 
    
    public Medium update(Medium medium) {
        return JpaUtil.obtenirEntityManager().merge(medium);
    }
    
    public List<Medium> findAll() {
        String s = "select m from Medium m order by m.denomination asc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public List<Medium> findAllByConsultation() {
        String s = "select m from Medium m order by m.nbConsultations desc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Medium.class);
        return query.getResultList();
    }
        
    public Medium findById(Long id) {
        return JpaUtil.obtenirEntityManager().find(Medium.class, id);
    }
    
}

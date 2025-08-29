/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Consultation;
import metier.modele.Employe;
        

/**
 *
 * @author naknin
 */
public class ConsultationDao {

    public ConsultationDao() {
       
    }

    public void create(Consultation consultation) {
        JpaUtil.obtenirEntityManager().persist(consultation);
    } 
    
    public Consultation update(Consultation consultation) {
        return JpaUtil.obtenirEntityManager().merge(consultation);
    }
    
    public List<Consultation> findAll() {
        String s = "select c from Consultation c order by c.nom, c.prenom asc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Consultation.class);
        return query.getResultList();
    }
        
    public Consultation findById(Long id) {
        return JpaUtil.obtenirEntityManager().find(Consultation.class, id);
    }
    
    public Consultation findAccepteeByIdEmploye(Employe employe) {
        String s = "select c from Consultation c where c.employe = :unEmploye and c.etat = :unEtat";
        Query query = JpaUtil.obtenirEntityManager().createQuery(s);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat", Consultation.Etat.ACCEPTEE);
        
        try {
            Consultation c = (Consultation) query.getSingleResult();
            return (Consultation) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }  
}

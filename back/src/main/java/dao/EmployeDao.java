/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Employe;

/**
 *
 * @author naknin
 */
public class EmployeDao {
    public EmployeDao() {
       
    }

    public void create(Employe employe) {
        JpaUtil.obtenirEntityManager().persist(employe);
    } 
    
    public Employe update(Employe employe) {
        return JpaUtil.obtenirEntityManager().merge(employe);
    }
    
    public List<Employe> findAll() {
        String s = "select e from Employe e order by e.nom, e.prenom asc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Employe.class);
        return query.getResultList();
    }
    
    public List<Employe> findAllByConsultation() {
        String s = "select m from Employe m order by m.nbConsultations desc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Employe.class);
        return query.getResultList();
    }
    
    public Employe findFirstAvailable(Employe.GenreEmploye genre) {
        String s = "select e from Employe e where e.disponible = 1 and e.genre = :unGenre order by e.nom, e.prenom asc ";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Employe.class);
        query.setParameter("unGenre", genre);
        List<Employe> employes = query.getResultList();
        Employe employe = null;
        if (employes.size() != 0) {
            employe = employes.get(0);
        }
        return employe;
    }
        
    public Employe findById(Long id) {
        return JpaUtil.obtenirEntityManager().find(Employe.class, id);
    }
    
    public Employe findByMail(String mail) {
        String jpql = "select e from Employe e where e.mail = :unMail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("unMail", mail);
        Employe e = (Employe) query.getSingleResult();
        return e;
    }

}

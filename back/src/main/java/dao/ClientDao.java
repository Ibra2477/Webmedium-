/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Client;
        

/**
 *
 * @author naknin
 */
public class ClientDao {

    public ClientDao() {
       
    }

    public void create(Client client) {
        JpaUtil.obtenirEntityManager().persist(client);
    } 
    
    public Client update(Client client) {
        return JpaUtil.obtenirEntityManager().merge(client);
    }
    
    public List<Client> findAll() {
        String s = "select c from Client c order by c.nom, c.prenom asc";
        TypedQuery query = JpaUtil.obtenirEntityManager().createQuery(s, Client.class);
        return query.getResultList();
    }
        
    public Client findById(Long id) {
        return JpaUtil.obtenirEntityManager().find(Client.class, id);
    }
    
    public Client findByMail(String mail) {
        String jpql = "select c from Client c where c.mail = :unMail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("unMail", mail);
        Client c = (Client) query.getSingleResult();
        return c;
    }
        
}

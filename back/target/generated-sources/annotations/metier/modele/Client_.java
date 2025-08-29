package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-13T14:23:05")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, String> zodiac;
    public static volatile SingularAttribute<Client, Double> latitude;
    public static volatile SingularAttribute<Client, String> couleur;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> motDePasse;
    public static volatile ListAttribute<Client, Consultation> listeConsultations;
    public static volatile SingularAttribute<Client, String> chinois;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, String> animal;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, Date> date_naissance;
    public static volatile SingularAttribute<Client, Double> longitude;

}
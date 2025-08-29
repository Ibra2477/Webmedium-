package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;
import metier.modele.Consultation.Etat;
import metier.modele.Employe;
import metier.modele.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-13T14:23:05")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> date_consult;
    public static volatile SingularAttribute<Consultation, String> prediction_amour;
    public static volatile SingularAttribute<Consultation, String> prediction_travail;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, String> prediction_sante;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, Etat> etat;
    public static volatile SingularAttribute<Consultation, String> commentaire;

}
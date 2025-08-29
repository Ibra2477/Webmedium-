package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;
import metier.modele.Employe.GenreEmploye;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-13T14:23:05")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> motDePasse;
    public static volatile ListAttribute<Employe, Consultation> listeConsultations;
    public static volatile SingularAttribute<Employe, Integer> nbConsultations;
    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile SingularAttribute<Employe, GenreEmploye> genre;
    public static volatile SingularAttribute<Employe, String> tel;
    public static volatile SingularAttribute<Employe, Long> id;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile SingularAttribute<Employe, Boolean> disponible;

}
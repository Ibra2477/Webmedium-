package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;
import metier.modele.Medium.GenreMedium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-13T14:23:05")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile SingularAttribute<Medium, String> presentation;
    public static volatile ListAttribute<Medium, Consultation> listeConsultations;
    public static volatile SingularAttribute<Medium, Integer> nbConsultations;
    public static volatile SingularAttribute<Medium, GenreMedium> genre;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile SingularAttribute<Medium, String> denomination;

}
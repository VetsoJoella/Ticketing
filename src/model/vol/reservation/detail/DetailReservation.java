package model.vol.reservation.detail;

import model.avion.classe.ClasseAvion;
import model.utilisateur.categorie.Categorie;

public class DetailReservation {
  
    ClasseAvion classeAvion ; 
    Categorie categorie ; 
    int nb ; 

    //Constructeur 
    public DetailReservation(){}

    public DetailReservation(String classeAvion, String nb){
        setNb(nb);
        setClasseAvion(classeAvion);
    }

    public DetailReservation(String classeAvion, int nb){
        setNb(nb);
        setClasseAvion(classeAvion);
    }

    public DetailReservation(String classeAvion, String categorie, String nb){
        setNb(nb);
        setClasseAvion(classeAvion);
        setCategorie(categorie);
    }

    public DetailReservation(String classeAvion, String categorie, int nb){
        setNb(nb);
        setClasseAvion(classeAvion);
        setCategorie(categorie);
    }


    public DetailReservation(ClasseAvion classeAvion, int nb){
        setNb(nb);
        setClasseAvion(classeAvion);
    }


    // Getter et setter 

    public ClasseAvion getClasseAvion() {
        return this.classeAvion;
    }

    public void setClasseAvion(ClasseAvion classeAvion) {
        this.classeAvion = classeAvion;
    }

    public void setClasseAvion(String classeAvion) {
        setClasseAvion(new ClasseAvion(classeAvion));
    }

    public Categorie getCategorie() {
        return categorie ; 
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie ;
    }

    public void setCategorie(String cateString) {
        setCategorie(new Categorie(cateString));
    }

    public int getNb() {
        return this.nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public void setNb(String nb) {
        // this.nb = nb;
        setNb(Integer.valueOf(nb));
    }


}

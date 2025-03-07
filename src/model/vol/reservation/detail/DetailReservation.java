package model.vol.reservation.detail;

import model.avion.classe.ClasseAvion;

public class DetailReservation {
  
    ClasseAvion classeAvion ; 
    int nb ; 

    //Constructeur 
    public DetailReservation(){}

    public DetailReservation(String classeAvion, String nb){
        setNb(nb);
        setClasseAvion(classeAvion);
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

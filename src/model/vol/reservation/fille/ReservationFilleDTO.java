package model.vol.reservation.fille;

public class ReservationFilleDTO {
    
    String id;
    double promotion;
    double prix;
    String categorie;
    String classe;

    // Constructeur à partir d'un objet ReservationFille
    public ReservationFilleDTO(ReservationFille reservationFille) {
        setId(reservationFille.getId()) ;
        setPromotion(reservationFille.getPromotion()) ;
        setPrix(reservationFille.getPrix()) ;
        setCategorie(reservationFille.getCategorie().getNom()) ;
        setClasse(reservationFille.getBillet().getPrixVol().getClasseAvion().getClasse().getType()) ;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
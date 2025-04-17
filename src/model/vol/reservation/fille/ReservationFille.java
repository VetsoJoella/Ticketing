package model.vol.reservation.fille;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.utilisateur.categorie.Categorie;
import model.vol.billet.Billet;
import model.vol.reservation.Reservation;

public class ReservationFille {
    
      
    String id ; 
    float promotion ; 
    Billet billet ; 
    Categorie categorie ;
    float prix ; 

    // Constructeur 
    public ReservationFille(){}

    public ReservationFille(String id){
        setId(id);
    }

    public ReservationFille(String id, float promotion, Billet billet) {
        setId(id);
        setPromotion(promotion);
        setBillet(billet);
    }

    public ReservationFille(String id, float promotion, Billet billet, Categorie categorie) {
        setId(id);
        setPromotion(promotion);
        setBillet(billet);
        setCategorie(categorie);
    }

    public ReservationFille(String id, float promotion, float prix, Billet billet, Categorie categorie) {
        setId(id);
        setPromotion(promotion);
        setBillet(billet);
        setCategorie(categorie);
        setPrix(prix);
    }

    // Getters et setters 
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPromotion() {
        return this.promotion;
    }

    public float getPromotionSansPourcentage() {
        return getPromotion()/100;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public Categorie getCategorie() {
        return categorie ;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie ;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    public Billet getBillet() {
        return this.billet;
    }

    public void setBillet(Billet billet) {
        this.billet = billet;
    }

    double getPrixAvecPromotion() {
        return getBillet().getPrixVol().getPrix() - getBillet().getPrixVol().getPrix()*getPromotionSansPourcentage();
    }

    public static void insert(Connection connexion, Reservation reservation, ReservationFille[] reservationFilles) throws Exception {

        if(reservationFilles == null || reservation == null) return ; 
        String requete = "INSERT INTO reservationFille (id, promotion, idBillet, idReservation, d_prix, idCategorie) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            for (ReservationFille reservationFille : reservationFilles) {
                declaration.setDouble(1, reservationFille.getPromotion());
                declaration.setString(2, reservationFille.getBillet().getId());
                declaration.setString(3, reservation.getId());
                declaration.setDouble(4, reservationFille.getPrixAvecPromotion());
                declaration.setString(5, reservationFille.getCategorie().getId());
                declaration.addBatch(); // Ajouter à la batch
            }
    
            declaration.executeBatch(); 
        } 

    }

    public static ReservationFille[] getByReservation(Connection connexion, String idReservation) throws Exception {

        List<ReservationFille> reservationFilles = new ArrayList<>() ;
        String requete = "SELECT * from v_reservationfille_categorie WHERE idReservation = ? " ;
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            declaration.setString(1, idReservation);
            ResultSet resultat = declaration.executeQuery();

            while(resultat.next()) {
                reservationFilles.add(
                    new ReservationFille(
                        resultat.getString("id"), 
                        resultat.getFloat("promotion"), 
                        resultat.getFloat("d_prix"), 
                        Billet.getById(connexion, resultat.getString("idBillet")), 
                        new Categorie(resultat.getString("idCategorie"), resultat.getString("nom"))
                    )
                );
            }
        }
        return reservationFilles.toArray(new ReservationFille[0]) ;
    }

    public static void deleteByReservation(Connection connexion, Reservation reservation) throws Exception {

        String requete = "DELETE FROM reservationFille where idReservation = ? " ;
        try (PreparedStatement stmt = connexion.prepareStatement(requete)) {
            stmt.setString(1, reservation.getId());
            stmt.executeUpdate() ;
        } 
    }
    
}

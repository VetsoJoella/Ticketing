package model.vol.reservation.fille;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vol.billet.Billet;
import model.vol.reservation.Reservation;

public class ReservationFille {
    
      
    String id ; 
    float promotion ; 
    Billet billet ; 
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
        if (getPromotion()==0) {
            return 1 ; 
        }
        return getPromotion()/100;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
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

    public static void insert(Connection connexion, Reservation reservation, ReservationFille[] reservationFilles) throws Exception {

        if(reservationFilles == null || reservation == null) return ; 
        String requete = "INSERT INTO reservationFille (id, promotion, idBillet, idReservation, d_prix) VALUES (DEFAULT, ?, ?, ?, ?)";
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            for (ReservationFille reservationFille : reservationFilles) {
                declaration.setDouble(1, reservationFille.getPromotion());
                declaration.setString(2, reservationFille.getBillet().getId());
                declaration.setString(3, reservation.getId());
                declaration.setDouble(4, reservationFille.getBillet().getPrixVol().getPrix()*reservationFille.getPromotionSansPourcentage());
                declaration.addBatch(); // Ajouter à la batch
            }
    
            declaration.executeBatch(); 
            connexion.commit(); 
        } 

    }

    public static ReservationFille[] getByReservation(Connection connexion, String idReservation) throws Exception {

        List<ReservationFille> reservationFilles = new ArrayList<>() ;
        String requete = "SELECT * from reservationfille WHERE idReservation = ? " ;
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            declaration.setString(1, idReservation);
            ResultSet resultat = declaration.executeQuery();

            while(resultat.next()) {
                reservationFilles.add(
                    new ReservationFille(
                        resultat.getString("id"), 
                        resultat.getFloat("promotion"), 
                        null)
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

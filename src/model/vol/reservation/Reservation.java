package model.vol.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exception.model.reservation.ReservationException;
import model.avion.Avion;
import model.avion.modele.Modele;
import model.utilisateur.categorie.Categorie;
import model.utilisateur.passager.Passager;
import model.vol.Vol;
import model.vol.billet.Billet;
import model.vol.prix.promotion.Promotion;
import model.vol.reservation.detail.DetailReservation;
import model.vol.reservation.fille.ReservationFille;
import model.vol.ville.Ville;
import util.date.DateUtil;

public class Reservation {
   
    String id ;
    Passager passager ; 
    Timestamp dateReservation ; 
    // DetailReservation[] detailReservations ; 
    ReservationFille[] reservationFilles ;

    // Constructeur 
    public Reservation(){}

    public Reservation(String id){
        setId(id);
    }

    public Reservation(String id, String idPassager, String date, Vol vol) throws ReservationException{
        setId(id);
        setPassager(idPassager);
        setDateReservation(date, vol);
    }

    Reservation(String id, String idPassager, String date) throws ReservationException{
        setId(id);
        setPassager(idPassager);
        setDateReservation(date);
    }

    Reservation(String id, Passager passager, Timestamp date){
        setId(id);
        setPassager(passager);
        setDateReservation(date);
    }

    public Reservation(Passager passager, Timestamp date){
        setPassager(passager);
        setDateReservation(date);
    }

    Reservation(String id, Passager passager, Timestamp date, ReservationFille[] reservationFilles){
        setId(id);
        setPassager(passager);
        setDateReservation(date);
    }

    public Reservation(String id, Passager passager, Timestamp date, ReservationFille[] reservationFilles, Vol vol) throws ReservationException{
        setId(id);
        setPassager(passager);
        setDateReservation(date, vol);
    }

    // Getters et setters 
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Passager getPassager() {
        return this.passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    public void setPassager(String passager) {
        setPassager(new Passager(passager));
    }

    public Timestamp getDateReservation() {
        return this.dateReservation;
    }

    void setDateReservation(Timestamp dateReservation) {
        if(dateReservation==null) this.dateReservation = Timestamp.valueOf(LocalDateTime.now()) ;
        else this.dateReservation = dateReservation;

    }

    public void setDateReservation(Timestamp dateReservation, Vol vol ) throws ReservationException{
        Timestamp dernierReservation = DateUtil.ajouterHeure(vol.getDateHeureDecollage(), -vol.getDernierReservation()) ;
        // System.out.println("Valeur de date annulation "+dateReservation+" valeur de date décollage "+vol.getDateHeureDecollage()+" et dernière annulation "+dernierReservation);

        if(dateReservation.after(vol.getDateHeureDecollage()) || dateReservation.equals(vol.getDateHeureDecollage())) throw new ReservationException(this, "Impossible de faire une reservation sur ce vol : la date de réservation est déjà depassé") ;
        
        if(dateReservation.after(dernierReservation) || dateReservation.equals(dernierReservation)) throw new ReservationException(this, "Impossible de faire une reservation sur ce vol : la reservation est déjà close") ;
        
        this.dateReservation = dateReservation;
    }

    void setDateReservation(String dateReservation) {
        // this.dateReservation = dateReservation;
        try {
            Timestamp timestamp = Timestamp.valueOf(dateReservation);
            setDateReservation(timestamp);
        } catch (Exception e) {
            setDateReservation(Timestamp.valueOf(LocalDateTime.now()));
        }
    }

    public void setDateReservation(String dateReservation, Vol vol) throws ReservationException{
        // this.dateReservation = dateReservation;
        try {
            Timestamp timestamp = Timestamp.valueOf(dateReservation);

            setDateReservation(timestamp, vol);
        } catch (Exception e) {
            throw new ReservationException(this, e.getMessage());
        }
    }

    public int getNbPlaceReservee() {
       return getReservationFilles().length ;
    }
    // public DetailReservation[] getDetailReservations() {
    //     return this.detailReservations;
    // }

    // public void setDetailReservations(DetailReservation[] detailReservations) {
    //     this.detailReservations = detailReservations;
    // }

    public ReservationFille [] getReservationFilles() {
        return this.reservationFilles;
    }

    public void setReservationFilles(ReservationFille[] reservationFilles) {
        this.reservationFilles = reservationFilles;
    }

    // public void setReservationFilles(Vol vol, String[] classeAvions, String[] categories, Integer[] nbs) throws ReservationException {
        
    //     List<DetailReservation> detailReservations = new ArrayList<>() ;
    //     for (int i = 0; i < classeAvions.length; i++) {
    //         detailReservations.add(new DetailReservation(classeAvions[i], categories[i], nbs[i])) ;
    //     }
    //     setReservationFilles(vol, detailReservations.toArray(new DetailReservation[0]));
    // }

    public void setReservationFilles(Vol vol, String[] classeAvions, Categorie[] categories, Integer[] nbs) throws ReservationException {
        
        List<DetailReservation> detailReservations = new ArrayList<>() ;
        for (int i = 0; i < classeAvions.length; i++) {
            DetailReservation detailReservation = new DetailReservation(classeAvions[i], nbs[i]) ;
            detailReservation.setCategorie(categories[i]);
            detailReservations.add(detailReservation) ;
        }
        setReservationFilles(vol, detailReservations.toArray(new DetailReservation[0]));
    }

    public void setReservationFilles(Vol vol, DetailReservation[] detailReservations) throws ReservationException{
        // this.reservationFilles = reservationFilles;

        setDateReservation(getDateReservation(), vol) ;
        List<ReservationFille> reservationFilles = new ArrayList<>() ;
        for (DetailReservation detailReservation : detailReservations) {
            Billet[] disponibles = vol.getBilletDisponibles(detailReservation.getClasseAvion()) ;
            System.out.println("Billet disponible "+disponibles.length+" requis est "+detailReservation.getNb()+" Pour la classe "+detailReservation.getClasseAvion().getId());

            if(disponibles.length<detailReservation.getNb()) throw new ReservationException(this, "Billet insuffisant pour la classe "+detailReservation.getClasseAvion()) ;
            else {
                Promotion promotion = vol.getNbPromotion(detailReservation.getClasseAvion()) ;
                promotion.setAChange(false);
                for (int i = 0; i < detailReservation.getNb(); i++) {
                    ReservationFille reservationFille = new ReservationFille(null, 0, disponibles[i]) ;
                    double prixVol = disponibles[i].getPrixVol().getPrix()*detailReservation.getCategorie().getPromotionSansPourcentage() ;
                    disponibles[i].getPrixVol().prix = (prixVol);
                    if(promotion.getReste()>0){
                        reservationFille.setPromotion(promotion.getPourcentage());
                        promotion.diminuerReste(1);
                        promotion.setAChange(true);
                    }
                    reservationFilles.add(reservationFille);
                    
                }
            }
        }
        setReservationFilles(reservationFilles.toArray(new ReservationFille[0]));
    }

    void insertMere(Connection connexion, Vol vol) throws Exception {

        String requete = "INSERT INTO reservation(id, datereservation, idpassager, idVol) VALUES (DEFAULT, ?, ?, ?)" ;
        try (PreparedStatement declaration = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {    
            declaration.setTimestamp(1, getDateReservation());
            declaration.setString(2, getPassager().getId());
            declaration.setString(3, vol.getId());

            declaration.executeUpdate();
            
            try (ResultSet generatedKeys = declaration.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getString(1));
                } 
            }
        } 


    }

    void insertFille(Connection connexion) throws Exception {
        
        ReservationFille.insert(connexion, this, getReservationFilles());
    }

    public void insert(Connection connexion, Vol vol) throws Exception {
        insertMere(connexion, vol);
        insertFille(connexion);
      
    }

    public static Reservation getById(Connection connexion, String id) throws Exception {

        Reservation reservation = null ; 
        String requete = "SELECT * from reservation WHERE id = ? " ; 

        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();

            if(resultat.next()) {
               reservation = new Reservation(
                resultat.getString("id"),
                resultat.getString("idPassager"),
                resultat.getString("datereservation")
               ) ;
               reservation.setReservationFilles(ReservationFille.getByReservation(connexion, id));
            }
        }
        return reservation ;

    }

    public static Reservation[] getAll(Connection connexion) throws Exception {

        List<Reservation> listes = new ArrayList<>() ;
        String requete = "SELECT * from reservation WHERE id not in (SELECT idReservation from historiqueannulation) " ; 

        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
            ResultSet resultat = declaration.executeQuery();
            Reservation reservation = null ; 

            while(resultat.next()) {
               reservation = new Reservation(
                resultat.getString("id"),
                Passager.getById(connexion, resultat.getString("idPassager")),
                resultat.getTimestamp("datereservation")
               ) ;
               reservation.setReservationFilles(ReservationFille.getByReservation(connexion, reservation.getId()));
               listes.add(reservation) ;
            }
        }
        return listes.toArray(new Reservation[0]) ;

    }

    public Vol getVolRelie(Connection connexion) throws Exception {

        Vol vol = null ;
        String requete = "select * from v_vol_reservation where idReservation = ?" ;
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, getId());
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                vol =  new Vol(
                    resultat.getString("id"), 
                    new Ville(resultat.getString("idvilledepart")), 
                    new Ville(resultat.getString("idvilledestination")), 
                    resultat.getTimestamp("datedecollage"), 
                    resultat.getFloat("d_dernierereservation"), 
                    resultat.getFloat("d_derniereAnnulation"), 
                    new Avion(
                        resultat.getString("idAvion"), 
                        resultat.getString("nom"), 
                        resultat.getDate("dateFabrication"), 
                        new Modele(resultat.getString("idModele"))
                    ));

                    // vol.setPrixVols(connexion);
                    // vol.setBilletDisponibles(connexion);
                    // vol.setPromotions(connexion);
            } else {
                throw new Exception("Aucune ville trouvée avec l'id : " + id);
            }
        }

        return vol ;
    }

    public static Reservation[] getByPassager(Connection connexion, Passager passager) throws Exception {


        List<Reservation> listes = new ArrayList<>() ;
        String requete = "SELECT * from reservation WHERE id not in (SELECT idReservation from historiqueannulation) and idPassager = ? " ; 

        try (PreparedStatement declaration = connexion.prepareStatement(requete)) { 
            declaration.setString(1, passager.getId());  
            System.out.println("Requete est "+requete +" id est "+passager.getId()); 
            ResultSet resultat = declaration.executeQuery();
            Reservation reservation = null ; 

            while(resultat.next()) {
               reservation = new Reservation(
                resultat.getString("id"),
                Passager.getById(connexion, resultat.getString("idPassager")),
                resultat.getTimestamp("datereservation")
               ) ;
               reservation.setReservationFilles(ReservationFille.getByReservation(connexion, reservation.getId()));
               listes.add(reservation) ;
            }
        }
        return listes.toArray(new Reservation[0]) ;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", passager='" + getPassager() + "'" +
            ", dateReservation='" + getDateReservation() + "'" +
            ", reservationFilles='" + getReservationFilles().length + "'" +
            "}";
    }
  
}

package model.vol.reservation.annulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import exception.model.reservation.ReservationException;
import model.vol.Vol;
import model.vol.reservation.Reservation;
import model.vol.reservation.fille.ReservationFille;
import util.date.DateUtil;

public class AnnulationReservation {
    String id ; 
    Timestamp dateAnnulation ; 
    Reservation reservation ; 

    // Constructer
    public AnnulationReservation() {
    }

    AnnulationReservation(String id, Timestamp dateAnnulation, Reservation reservation) throws ReservationException{
        setId(id);
        setDateAnnulation(dateAnnulation);
        setReservation(reservation);
    }

    public AnnulationReservation(String id, Timestamp dateAnnulation, Reservation reservation, Vol vol) throws ReservationException{
        setId(id);
        setDateAnnulation(vol, dateAnnulation);
        setReservation(reservation);
    }
    
    public AnnulationReservation(Connection connexion, String id, Timestamp dateAnnulation, Reservation reservation) throws Exception{
        setId(id);
        setReservation(reservation);
        setDateAnnulation(getReservation().getVolRelie(connexion) ,dateAnnulation);

    }

    public AnnulationReservation(Connection connexion, Timestamp dateAnnulation, Reservation reservation) throws Exception{
        setReservation(reservation);
        setDateAnnulation(getReservation().getVolRelie(connexion), dateAnnulation);
    }

    public AnnulationReservation(Connection connexion, Timestamp dateAnnulation, String idReservation) throws Exception{
        setReservation(connexion, idReservation);
        setDateAnnulation(getReservation().getVolRelie(connexion) ,dateAnnulation);
    }

    // Getter et setter
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDateAnnulation() {
        return this.dateAnnulation;
    }

    public void setDateAnnulation(Vol vol, Timestamp dateAnnulation) throws ReservationException{
        Timestamp derniereAnnulation = DateUtil.ajouterHeure(vol.getDateHeureDecollage(), -vol.getDerniereAnnulation());
        System.out.println("Valeur de date annulation "+dateAnnulation+" valeur de date décollage "+vol.getDateHeureDecollage()+" et dernière annulation "+derniereAnnulation);
        if(dateAnnulation==null) dateAnnulation = Timestamp.valueOf(LocalDateTime.now());
        if(dateAnnulation.after(derniereAnnulation) || dateAnnulation.equals(derniereAnnulation)) throw new ReservationException(getReservation(), "Impossible d'annuler la réservation : la date de dernère annulation est déjà dépassée");
        this.dateAnnulation = dateAnnulation;
    }

    public void setDateAnnulation(Vol vol, String dateAnnulation) throws ReservationException{
        try {
            Timestamp timestamp = Timestamp.valueOf(dateAnnulation);
            setDateAnnulation(vol, timestamp);
        } catch (Exception e) {
            setDateAnnulation(Timestamp.valueOf(LocalDateTime.now()));
        }
    }


    void setDateAnnulation(Timestamp dateAnnulation) throws ReservationException{
        // Timestamp derniereAnnulation = DateUtil.ajouterHeure(getReservation().getDateReservation(), 0);
        
        // if(dateAnnulation.after(derniereAnnulation) || dateAnnulation.equals(derniereAnnulation)) throw new ReservationException(getReservation(), "Impossible d'annuler la réservation : la date de dernère annulation est déjà dépassée");
        this.dateAnnulation = dateAnnulation;
    }

    void setDateAnnulation(String dateAnnulation) throws ReservationException{
        // this.dateAnnulation = dateAnnulation;
        try {
            Timestamp timestamp = Timestamp.valueOf(dateAnnulation);
            setDateAnnulation(timestamp);
        } catch (Exception e) {
            setDateAnnulation(Timestamp.valueOf(LocalDateTime.now()));
        }
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setReservation(Connection connexion, String idReservation) throws Exception {
        setReservation(new Reservation(idReservation));
        setReservation(connexion);
    }

    public void setReservation(String idReservation) {
        // this.reservation = reservation;
        setReservation(new Reservation(idReservation));
    }

    public void setReservation(Connection connection) throws Exception {
        setReservation(Reservation.getById(connection, getReservation().getId()));
    }

    void insertHistoriqueAnnulation(Connection connexion) throws Exception {

        String requete = "INSERT INTO historiqueAnnulation(id, dateAnnulation, idReservation) VALUES (DEFAULT, ?, ?)" ;
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {    
           
            declaration.setTimestamp(1, getDateAnnulation());
            declaration.setString(2, getReservation().getId());
    
            declaration.executeUpdate(); 
        } 
    }

    public void confirmerAnnulation(Connection connexion) throws Exception {

        try {
            
            connexion.setAutoCommit(false);
            ReservationFille.deleteByReservation(connexion, getReservation());
            insertHistoriqueAnnulation(connexion);
            connexion.commit();

        } catch (Exception e) {
            connexion.rollback();
            throw e ;
        }
        
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dateAnnulation='" + getDateAnnulation() + "'" +
            ", reservation='" + getReservation() + "'" +
            "}";
    }

    
}

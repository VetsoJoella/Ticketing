package model.vol.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.vol.reservation.fille.ReservationFille;
import model.vol.reservation.fille.ReservationFilleDTO;

public class ReservationDTO {

    String id;
    String passager;
    Date dateReservation;
    String image;
    ReservationFilleDTO[] reservationFillesDTO;

    // Constructeur à partir d'un objet Reservation
    public ReservationDTO(Reservation reservation) {
        setId(reservation.getId());
        setPassager(reservation.getPassager().getNom());
        setDateReservation(reservation.getDateReservation());
        setImage(reservation.getImage());
        setReservationFillesDTO(reservation.getReservationFilles());
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassager() {
        return passager;
    }

    public void setPassager(String passager) {
        this.passager = passager;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ReservationFilleDTO[] getReservationFillesDTO() {
        return reservationFillesDTO;
    }

    public void setReservationFillesDTO(ReservationFilleDTO[] reservationFillesDTO) {
        this.reservationFillesDTO = reservationFillesDTO;
    }

    public void setReservationFillesDTO(ReservationFille[] reservationFilles) {

        List<ReservationFilleDTO> listes = new ArrayList<>() ;
        for (ReservationFille reservationFille : reservationFilles) {
            listes.add(new ReservationFilleDTO(reservationFille)) ;
        }
        setReservationFillesDTO(listes.toArray(new ReservationFilleDTO[0]));
    }
}
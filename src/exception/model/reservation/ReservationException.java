package exception.model.reservation;

import model.vol.reservation.Reservation;

public class ReservationException extends Exception{
    
    Reservation reservation ; 


    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ReservationException(){}

    public ReservationException(Reservation reservation, String message) {
        super(message);
        setReservation(reservation);
    }

}

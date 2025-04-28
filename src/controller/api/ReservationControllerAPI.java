package controller.api;


import model.ResponseAPI;
import model.avion.Avion;
import model.avion.classe.ClasseAvion;
import model.vol.reservation.Reservation;
import model.vol.reservation.ReservationDTO;

import java.sql.Connection;

import itu.springboot.services.connection.UtilDb;
import itu.springboot.annotation.RestApi;
import itu.springboot.annotation.Url;
import itu.springboot.annotation.Param;
import itu.springboot.annotation.AnnotationController;

@AnnotationController
public class ReservationControllerAPI {
    
    @Url(url = "/api/reservation")
    @RestApi
    public ResponseAPI<Object> getReservationById(UtilDb utilDb, String idReservation) throws Exception{

        try (Connection connection = utilDb.getConnection()) {
            Reservation reservation = Reservation.getById(connection, idReservation) ; 
            ReservationDTO reservationDTO = new ReservationDTO(reservation) ;
            return ResponseAPI.success(200, "Réussie", reservationDTO) ;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseAPI.error(500, idReservation, e.getCause()) ;
        }

    }
}

package test;

import java.sql.Connection;

import model.vol.Vol;
import util.connection.UtilDb;

public class Main {
    
    public static void main(String[] args) {

        try (Connection connection = UtilDb.getConnection()) {
            connection.setAutoCommit(false);
            System.out.println("Connection réussie");

            // Vol vol = Vol.getById(connection, "VOL00002");
            // Passager passager = new Passager("PSG00001");
            // DetailReservation[] detailReservations = new DetailReservation[]{
            //     new DetailReservation("CLS_AVN00002", "4") ,
            //     new DetailReservation("CLS_AVN00001", "2") 
            // };

            // Reservation reservation = new Reservation(null, passager.getId(), "2025-02-24 12:00:00", vol) ; 
            // reservation.setReservationFilles(vol, detailReservations);
            // vol.reserver(connection, reservation);

            // Reservation reservation = Reservation.getById(connection, "RVT00012");
            // Vol vol = reservation.getVolRelie(connection);

            // AnnulationReservation annulationReservation = new AnnulationReservation(null, Timestamp.valueOf("2025-02-26 08:00:00"), reservation, vol);
            // annulationReservation.confirmerAnnulation(connection);


            Vol[] vols = Vol.getByCriteria(connection, "VLL00001", null, null, "2025-03-10", null) ; 
            for (Vol vol2 : vols) {
                System.out.println(vol2.toString());
            }
            
            // connection.commit();
            System.out.println("Données insérées avec succès");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

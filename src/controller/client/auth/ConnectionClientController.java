package controller.client.auth;

import java.sql.Connection;
import java.sql.Timestamp;

import itu.springboot.annotation.AnnotationController;
import itu.springboot.annotation.Url;
import itu.springboot.annotation.Post;
import itu.springboot.annotation.Param;
import itu.springboot.annotation.security.auth.role.Role;
import itu.springboot.services.connection.UtilDb;
import itu.springboot.view.response.ModelView;
import itu.springboot.classes.session.Session;
import itu.springboot.view.response.RedirectAttributes;

import model.avion.Avion;
import model.utilisateur.admin.Admin;
import model.utilisateur.passager.Passager;
import model.vol.Vol;
import model.vol.reservation.Reservation;
import model.vol.reservation.annulation.AnnulationReservation;
import model.vol.ville.Ville;

@AnnotationController
@Role(value = Passager.class)
public class ConnectionClientController {

    // Annulation de réservation d'un client 
    @Url(url = "/client/reservation")
    public String annulationReservation(String id, UtilDb utilDb, RedirectAttributes redirectAttributes) {

        try (Connection connection = utilDb.getConnection()) {
            AnnulationReservation annulationReservation = new AnnulationReservation(connection, null, id);
            annulationReservation.confirmerAnnulation(connection);
            System.out.println(annulationReservation.toString());
            redirectAttributes.addFlashAttribute("message", "Annulation effectuée") ; 
        
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage()) ; 
        }

        return "redirect:/client/reservations" ;
    }

    // Liste des reservations du client
    @Url(url = "/client/reservations")
    public ModelView reservation(Session session, UtilDb utilDb){

        ModelView modelView = new ModelView("/views/client/reservation-liste.jsp");
        try (Connection connexion = utilDb.getConnection()) {
            System.out.println("Valeur de session dans client/reservations est "+(session.get("utilisateur")).getClass());
            Reservation[] reservations = Reservation.getByPassager(connexion, (Passager)session.get("utilisateur")) ;
            modelView.add("reservations", reservations) ;
        } catch (Exception e) {
            modelView.add("message", e.getMessage()) ;
            e.printStackTrace();
        }
        return modelView ; 
    }

    // Liste des vols du client
    @Url(url = "/client/vols")
    public ModelView accueil(Ville depart, Ville destination, Timestamp dateMin, Timestamp dateMax, Avion avion, UtilDb utilDb){

        ModelView modelView = new ModelView("/views/client/vol-liste.jsp") ;
        try (Connection connection = utilDb.getConnection()) {
            System.out.println("Connection réussie");

            Vol[] vols = Vol.getByCriteria(connection, depart, destination, dateMin, dateMax, avion); 
            modelView.add("vols", vols);
            modelView.add("villes", Ville.getAll(connection));
            modelView.add("avions", Avion.getAll(connection));

        } catch(Exception err) {

        }
        return modelView ; 
    }

    //Afficher les détail d'un vol
    @Url(url = "/client/vol")
    public ModelView getDetailVol(String id, UtilDb utilDb) {
        
        ModelView modelView = new ModelView("/views/client/vol-detail.jsp") ; 
        try (Connection connection = utilDb.getConnection()) {
            Vol vol = Vol.getById(connection, id) ; 
            modelView.add("vol", vol) ;

        } catch (Exception e) {
            modelView.add("message", e.getMessage()) ;
            e.printStackTrace();
        }
        return modelView ; 
    }


    @Url(url="/client/reservation")
    @Post 
    public String reserverUnVol(Vol vol, @Param(name="nbs[]") int[] nbs, @Param(name="classeAvions[]")String[] classeAvions, Session session, UtilDb utilDb, RedirectAttributes redirectAttributes) {

        try (Connection connection = utilDb.getConnection()) {
            // System.out.println("Valeur de vol est "+vol);
            // System.out.println("Valeur de nbs et de classe Avion est ");
            // for (int i = 0; i < classeAvions.length; i++) {
            //     System.out.println("Classe avion "+classeAvions[i]+" nb : "+nbs[i]);
            // }
            // vol.setBilletDisponibles(connection) ; vol.setPromotion(connection);
            vol = Vol.getById(connection, vol.getId()) ;
            Reservation reservation = new Reservation((Passager)session.get("utilisateur"), null) ;
            reservation.setReservationFilles(vol, classeAvions, nbs);
            vol.reserver(connection, reservation);

            redirectAttributes.addFlashAttribute("message", "Vol réservé") ;
                 
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage()) ;
            return "redirect:/client/vols" ;
        }
        return "redirect:/client/reservations" ;
    }

    @Url(url = "/client/deconnection")
    public ModelView deconnection(Session session) {

        session.remove("utilisateur") ;
        return new ModelView("/views/client/login-client.jsp") ; 
    }

}

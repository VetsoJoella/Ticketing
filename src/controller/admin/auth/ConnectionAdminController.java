package controller.admin.auth;

import java.sql.Connection;
import java.sql.Timestamp;

import itu.springboot.annotation.AnnotationController;
import itu.springboot.annotation.Param;
import itu.springboot.annotation.Post;
import itu.springboot.annotation.Url;
import itu.springboot.annotation.security.auth.role.Role;
import itu.springboot.classes.session.Session;
import itu.springboot.services.connection.UtilDb;
import itu.springboot.view.response.RedirectAttributes;
import itu.springboot.view.response.ModelView;

import model.avion.Avion;
import model.avion.classe.Classe;
import model.utilisateur.admin.Admin;
import model.vol.Vol;
import model.vol.reservation.Reservation;
import model.vol.reservation.annulation.AnnulationReservation;
import model.vol.ville.Ville;


@AnnotationController
@Role(value = Admin.class)
public class ConnectionAdminController {
    
    
    @Url(url = "/admin/insertionVol")
    @Post
    public String insertionVol(Vol vol, @Param(name ="classeAvions[]") String[] classeAvions, @Param(name="promotions[]") float[] pourcentages, @Param(name="prix[]") double[] prix, @Param(name="nbs[]") int[] nbs, UtilDb utilDb, RedirectAttributes redirectAttributes){
 
        try(Connection connection = utilDb.getConnection()) {
            vol.setPrixVols(classeAvions, prix);
            vol.setPromotions(classeAvions, nbs, pourcentages);
            vol.insert(connection);
            redirectAttributes.addFlashAttribute("message", "ol inséré avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/insertionVol";

    }


    @Role(value = Admin.class)
    @Url(url = "/admin/reservation")
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

        return "redirect:/admin/reservations" ;
    }
   

    @Url(url = "/admin/vol/upt")
    @Post
    public ModelView majVol(Vol vol, @Param(name ="classeAvions[]") String[] classeAvions, @Param(name="promotions[]") float[] pourcentages, @Param(name="prix[]") double[] prix, @Param(name="nbs[]") int[] nbs, UtilDb utilDb){
        
        ModelView modelView = new ModelView("/views/admin/reservation-liste.jsp") ; 
        
        try(Connection connection = utilDb.getConnection()) {
            vol.setPrixVols(classeAvions, prix);
            vol.setPromotions(classeAvions, nbs, pourcentages);
            vol.update(connection);
            vol.updatePromotion(connection);
            modelView.add("message", "Vol mis à jour");
        } catch (Exception e) {
            e.printStackTrace();
            modelView.add("message", e.getMessage());
        }
        return modelView ; 
    } 
    
    @Url(url = "/admin/vols")
    public ModelView accueil(Ville depart, Ville destination, Timestamp dateMin, Timestamp dateMax, Avion avion, UtilDb utilDb) {

        ModelView modelView = new ModelView("/views/admin/vol-modification.jsp") ;
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


    @Url(url = "/admin/vol")
    public ModelView getDetailVol(String id, UtilDb utilDb) {
        
        ModelView modelView = new ModelView("/views/admin/vol-detail.jsp") ; 
        try (Connection connection = utilDb.getConnection()) {
            Vol vol = Vol.getById(connection, id) ; 
            modelView.add("vol", vol) ;

        } catch (Exception e) {
            modelView.add("message", e.getMessage()) ;
            e.printStackTrace();
        }
        return modelView ; 
    }

    @Role(value = Admin.class)
    @Url(url = "/admin/insertionVol")
    public ModelView insertionVol(UtilDb utilDb){

        ModelView modelView = new ModelView("/views/admin/vol-insertion.jsp") ; 
        try(Connection connection = utilDb.getConnection()) {
            
            Ville[] villes = Ville.getAll(connection) ; 
            Avion[] avions = Avion.getAll(connection) ;
            Classe[] classes = Classe.getAll(connection) ; 

            modelView.add("villes", villes) ; 
            modelView.add("avions", avions) ; 
            modelView.add("classes", classes) ; 

        } catch (Exception e) {
            e.printStackTrace();
            modelView.add("message", e.getMessage()) ;
        }
                
        return modelView ; 
    }    


    @Url(url = "/admin/deconnection")
    public ModelView deconnection(Session session) {
        session.remove("utilisateur") ;

        return new ModelView("/views/admin/login-admin.jsp") ; 
    }

    @Url(url = "/admin/reservations")
    // @Post
    public ModelView getReservations(UtilDb utilDb){
        ModelView modelView = new ModelView("/views/admin/reservation-liste.jsp") ; 
        
        try (Connection connection = utilDb.getConnection()) {
            Reservation[] reservations = Reservation.getAll(connection) ;
            modelView.add("reservations", reservations) ;
        } catch (Exception e) {
            e.printStackTrace();
            modelView.add("message", e.getMessage()) ;
        }
        return modelView ; 
    } 
}

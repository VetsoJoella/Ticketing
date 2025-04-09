package controller.client;

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
import model.utilisateur.passager.Passager;
import model.vol.Vol;
import model.vol.reservation.Reservation;
import model.vol.ville.Ville;

@AnnotationController
public class NavigationClientController {
    
    @Url(url = "/client")
    public ModelView index() {

        return new ModelView("/views/client/login-client.jsp");
    }

    @Url(url = "/client")
    @Post
    public String connection(Session session, Passager passager, RedirectAttributes redirectAttributes, UtilDb utilDb){

        System.out.println(passager.toString());
        try(Connection connection = utilDb.getConnection()) {
            passager.se_connecter(connection);
            session.add("utilisateur", passager);
            
        } catch(Exception err) {
            err.printStackTrace();
            redirectAttributes.addFlashAttribute("message", err.getMessage());
            return "redirect:/client"; 
        } 
        return "redirect:/client/reservations" ; 
    }


}

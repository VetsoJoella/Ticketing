package controller.admin;


import java.sql.Connection;
import java.sql.Date;
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
import model.avion.classe.Classe;
import model.utilisateur.admin.Admin;
import model.vol.Vol;
import model.vol.reservation.Reservation;
import model.vol.ville.Ville;

@AnnotationController
public class NavigationAdminController {
    
    public NavigationAdminController() {}

    @Url(url="/admin")
    public ModelView index() {

        ModelView modelView = new ModelView("/views/admin/login-admin.jsp");
        // modelView.add("message", message);

        return modelView ; 
    
    } 

    @Url(url = "/admin")
    @Post
    public String connection(Session session, Admin admin, RedirectAttributes redirectAttributes, UtilDb utilDb){

        System.out.println(admin.toString());
        try(Connection connection = utilDb.getConnection()) {
            admin.se_connecter(connection);
            session.add("utilisateur", admin);
            
        } catch(Exception err) {
            err.printStackTrace();
            redirectAttributes.addFlashAttribute("message", err.getMessage());
            return "redirect:/admin"; 
        } 

        return "redirect:/admin/insertionVol" ; 
    }
    
}

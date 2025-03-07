package controller.admin.auth;

import java.sql.Connection;

import annotation.AnnotationController;
import annotation.Post;
import annotation.Url;
import model.utilisateur.admin.Admin;
import response.RedirectAttributes;
import session.Session;
import util.connection.UtilDb;

@AnnotationController
public class ConnectionAdminController {
    
    @Url(url = "/admin")
    @Post
    public String connection(Session session, Admin admin, RedirectAttributes redirectAttributes ){

        System.out.println(admin.toString());
        // ModelView modelView = new ModelView("redirect:admin/insertionVol") ;
        try (Connection connection = UtilDb.getConnection()) {
           
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

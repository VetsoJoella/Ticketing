package controller.admin;


import java.sql.Connection;

import annotation.AnnotationController;
import annotation.Url;
import annotation.security.auth.role.Role;
import model.utilisateur.admin.Admin;
import model.vol.Vol;
import response.ModelView;
import util.connection.UtilDb;

@AnnotationController
public class NavigationAdminController {
    
    public NavigationAdminController() {}

    @Url(url="/admin")
    public ModelView index() {

        ModelView modelView = new ModelView("/views/admin/login-admin.jsp");
        // modelView.add("message", message);

        return modelView ; 
    
    } 

    @Url(url = "/admin/vols")
    @Role(value = Admin.class)
    public ModelView accueil(){

        ModelView modelView = new ModelView("/views/vol-liste.jsp") ;
        try (Connection connection = UtilDb.getConnection()) {
            connection.setAutoCommit(false);
            System.out.println("Connection réussie");

            Vol[] vols = Vol.getByCriteria(connection, null, null, null, null, null); 
            modelView.add("vols", vols);

        } catch(Exception err) {

        }
        return modelView ; 
    }

    // @Url(url="/admin")
    // @Post
    // public ModelView connection() {

    //     return new ModelView("/views/admin/login-admin.jsp");
    
    // } 

    @Url(url = "/admin/vol")
    @Role(value = Admin.class)
    public ModelView getDetailVol() {
        
        ModelView modelView = new ModelView("/views/admin/vol-detail.jsp") ; 
        return modelView ; 
    }

    // @Auth
    // @Role(value = Admin.class)
    // @Url(url = "/admin/vol")
    @Url(url = "/admin/insertionVol")
    public ModelView insertionVol(){
        ModelView modelView = new ModelView("/views/admin/vol-insertion.jsp") ; 
        return modelView ; 
    }    

    @Url(url = "/admin/vol/upt")
    // @Post
    // @Auth
    // @Role(value = Admin.class)
    public ModelView majVol(){
        ModelView modelView = new ModelView("/views/admin/vol-modification.jsp") ; 
        return modelView ; 
    } 
    
    @Url(url = "/admin/deconnection")
    public ModelView deconnection() {
        return new ModelView("/views/admin/login-admin.jsp") ; 
    }
    
}

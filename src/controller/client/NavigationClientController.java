package controller.client;

import java.sql.Connection;

import annotation.AnnotationController;
import annotation.Get;
import annotation.Post;
import annotation.Url;
import annotation.security.Auth;
import annotation.security.auth.role.Role;
import model.utilisateur.passager.Passager;
import model.vol.Vol;
import response.ModelView;
import util.connection.UtilDb;

@AnnotationController
public class NavigationClientController {
    
    @Url(url = "/client")
    public ModelView index() {

        return new ModelView("/views/client/login-client.jsp");
    }

    // @Url(url = "/client/reservation")
    // // @Post
    // // @Auth
    // // @Role(value = Passager.class)
    // public ModelView reservation(){

    //     ModelView modelView = new ModelView("/views/client/reservation-vol.jsp");

    //     return modelView ; 
    // }

    @Url(url = "/client/vols")
    // @Auth
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


    @Url(url = "/client/vol")
    // @Auth
    public ModelView getDetailVol() {
        
        ModelView modelView = new ModelView("/views/client/vol-detail.jsp") ; 
        return modelView ; 
    }

    @Url(url = "/client/deconnection")
    public ModelView deconnection() {
        return new ModelView("/views/client/login-client.jsp") ; 
    }
}

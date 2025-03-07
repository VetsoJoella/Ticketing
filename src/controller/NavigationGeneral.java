package controller;

import java.sql.Connection;

import annotation.AnnotationController;
import annotation.Post;
import annotation.Url;
import annotation.security.Auth;
import model.vol.Vol;
import response.ModelView;
import util.connection.UtilDb;

@AnnotationController
public class NavigationGeneral {
    
    
 

    @Url(url = "/reservation/annulation")
    @Post
    @Auth 
    public ModelView annulerReservation() {

        ModelView modelView = new ModelView("/views/.jsp");
        return null ;
    }

    @Url(url = "/reservations")
    // @Post
    public ModelView getReservations(){
        ModelView modelView = new ModelView("/views/reservation-liste.jsp") ; 
        return modelView ; 
    } 


}

package controller;

import java.sql.Connection;

import itu.springboot.annotation.AnnotationController;
import itu.springboot.annotation.Post;
import itu.springboot.annotation.RestApi;
import itu.springboot.annotation.Url;
import itu.springboot.annotation.Param;
import itu.springboot.annotation.security.auth.Auth;
import itu.springboot.services.connection.UtilDb;
import itu.springboot.view.response.ModelView;

import model.avion.Avion;
import model.avion.classe.ClasseAvion;

@AnnotationController
public class NavigationGeneral {


    @Url(url = "/reservation/annulation")
    @Post
    @Auth 
    public ModelView annulerReservation() {

        ModelView modelView = new ModelView("/views/.jsp");
        return modelView ;
    }




}

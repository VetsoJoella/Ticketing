package controller.api;

import java.sql.Connection;

import itu.springboot.services.connection.UtilDb;
import itu.springboot.annotation.RestApi;
import itu.springboot.annotation.Url;
import itu.springboot.annotation.Param;
import itu.springboot.annotation.AnnotationController;

import model.avion.Avion;
import model.avion.classe.ClasseAvion;

@AnnotationController
public class GeneralControllerAPI {
    
    @Url(url = "/api/classeAvion")
    @RestApi
    // public ClasseAvion[] listeDesClasses(UtilDb utilDb, @Param(name="idAvion") Avion avion) throws Exception{
    public ClasseAvion[] listeDesClasses(UtilDb utilDb, String idAvion) throws Exception{

        ClasseAvion[] classeAvions = null ; 
        try(Connection connection = utilDb.getConnection()) {
            classeAvions = ClasseAvion.getByAvion(connection, new Avion(idAvion)) ; 
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return classeAvions ; 


    }
}

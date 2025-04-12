package model.utilisateur.categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Categorie {

    private String id;
    private String nom;
    private double promotion;

    // Constructeur avec uniquement l'ID
    public Categorie(String id) {
        setId(id);
    }

    public Categorie(String id, String nom) {
        setId(id);
        setNom(nom);
    }

    public Categorie(String id, String nom, double promotion) {
        setId(id);
        setNom(nom);
        setPromotion(promotion);
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPromotion() {
        return promotion;
    }

    public double getPromotionSansPourcentage() {
        return promotion/100;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public static Categorie[] getAll(Connection connection) throws Exception {
        
        List<Categorie> listes = new ArrayList<>() ;
        
        String requete = "SELECT * from v_categorie_age " ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listes.add(new Categorie(rs.getString("id"), rs.getString("nom"), rs.getDouble("promotion"))) ;
            }
            
        } 
        return listes.toArray(new Categorie[0]) ;
    }

    static Categorie getById(Connection connection, String id) throws Exception {
        
        
        String requete = "SELECT * from v_categorie_age " ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(requete)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return (new Categorie(rs.getString("id"), rs.getString("nom"), rs.getDouble("promotion"))) ;
            }
            return null ;   
        } 
    }

    public static Categorie[] getById(Connection connection, String[] idCategories) throws Exception{

        List<Categorie> listes = new ArrayList<>() ;
        for (String string : idCategories) {
            listes.add(getById(connection, string)) ; 
        }
        return listes.toArray(new Categorie[0]);
    }


    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", promotion=" + promotion +
                '}';
    }
}

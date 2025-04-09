package model.vol.ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ville {

    private String id;  // id est une String
    private String nom; // nom de la ville

    // Constructeur
    public Ville() {}

    public Ville(String id) {
        setId(id);    
    }

    public Ville(String id, String nom) {
        setId(id);
        setNom(nom);
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

    // Méthode pour récupérer une Ville par son id depuis la base de données
    public static Ville getById(Connection connexion, String id) throws Exception {
        String requete = "SELECT * FROM Ville WHERE id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                return new Ville(resultat.getString("id"), resultat.getString("nom"));
            } else {
                throw new Exception("Aucune ville trouvée avec l'id : " + id);
            }
        }
    }

    // Méthode pour récupérer toutes les villes depuis la base de données
    public static Ville[] getAll(Connection connexion) throws Exception {
        String requete = "SELECT * FROM Ville";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            ResultSet resultat = declaration.executeQuery();
            ArrayList<Ville> villes = new ArrayList<>();
            while (resultat.next()) {
                villes.add(new Ville(resultat.getString("id"), resultat.getString("nom")));
            }
            return villes.toArray(new Ville[0]);
        }
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }

}
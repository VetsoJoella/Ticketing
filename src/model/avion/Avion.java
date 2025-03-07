package model.avion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import model.avion.modele.Modele;

public class Avion {
    
    private String id;               // Identifiant de l'avion
    private String nom;              // Nom de l'avion
    private Date dateFabrication;   // Date de fabrication de l'avion
    private Modele modele;          // Modèle de l'avion

    // Constructeur
    public Avion() {    }

    public Avion(String id) {
        this.setId(id);
    }

    public Avion(String id, String nom, Date dateFabrication, Modele modele) {
        this.setId(id);
        this.setNom(nom);
        this.setDateFabrication(dateFabrication);
        this.setModele(modele);
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

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    // Méthode pour récupérer un avion par son id depuis la base de données
    public static Avion getById(Connection connexion, String id) throws Exception {
        String requete = "SELECT * FROM v_avion_modele WHERE id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                Modele modele = new Modele(resultat.getString("id"), resultat.getString("nomModele")); // On suppose que le modèle existe déjà
                return new Avion(
                    resultat.getString("id"),
                    resultat.getString("nom"),
                    resultat.getDate("dateFabrication"),
                    modele
                );
            } else {
                throw new Exception("Aucun avion trouvé avec l'id : " + id);
            }
        }
    }

    // Méthode pour récupérer tous les avions depuis la base de données
    public static Avion[] getAll(Connection connexion) throws Exception {
        String requete = "SELECT * FROM v_avion_modele";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            ResultSet resultat = declaration.executeQuery();
            ArrayList<Avion> avions = new ArrayList<>();
            while (resultat.next()) {
                Modele modele = new Modele(resultat.getString("id"), resultat.getString("nomModele")); // On suppose que le modèle existe déjà
                avions.add(new Avion(
                    resultat.getString("id"),
                    resultat.getString("nom"),
                    resultat.getDate("dateFabrication"),
                    modele
                ));
            }
            return avions.toArray(new Avion[0]);
        }
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", dateFabrication=" + dateFabrication +
                ", modele=" + modele +
                '}';
    }
}
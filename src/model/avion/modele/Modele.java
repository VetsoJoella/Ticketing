package model.avion.modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modele {
    private String id;
    private String nom;

    public Modele(){}

    public Modele(String id) {
        setId(id);
    }

    public Modele(String id, String nom) {
        setId(id);
        setNom(nom);
    }

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

    public static Modele getById(Connection connection, String id) throws Exception {
        String query = "SELECT id, nom FROM modele WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Modele(rs.getString("id"), rs.getString("nom"));
                } else {
                    throw new Exception("Modele non trouvé pour l'id : " + id);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération du Modele", e);
        }
    }

    @Override
    public String toString() {
        return "Modele{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}

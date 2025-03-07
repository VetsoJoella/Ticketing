package model.avion.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Classe {

    private String id;  // id est une String
    private String type; // type de la classe

    // Constructeur
    public Classe(String id) {
        setId(id);
    }

    public Classe(String id, String type) {
        setId(id);
        setType(type);
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Méthode pour récupérer une Classe par son id depuis la base de données
    public void setById(Connection connexion) throws Exception {
        String requete = "SELECT * FROM Classe WHERE id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, this.getId());
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                this.setType(resultat.getString("type")); // Mettre à jour le type avec la valeur de la base de données
            } else {
                throw new Exception("Aucune classe trouvée avec l'id : " + this.getId());
            }
        }
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
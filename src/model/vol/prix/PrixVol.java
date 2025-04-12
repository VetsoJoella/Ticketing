package model.vol.prix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ValeurInvalideException;
import model.avion.Avion;
import model.avion.classe.Classe;
import model.avion.classe.ClasseAvion;
import model.vol.Vol;

public class PrixVol {
    private String id;          // Identifiant du prix
    private ClasseAvion classeAvion; // Classe d'avion associée
    public double prix;        // Prix du vol pour cette classe

    // Constructeur
    public PrixVol(Classe classe, Avion avion, double prix ) throws ValeurInvalideException{
        
        setClasseAvion(new ClasseAvion(classe, 0));
        setPrix(prix);
    }

    public PrixVol( ClasseAvion classeAvion, double prix) throws ValeurInvalideException {
        setClasseAvion(classeAvion);
        setPrix(prix); // Validation du prix dans le constructeur
    }

    public PrixVol(String classeAvion, double prix) throws ValeurInvalideException {
        // setClasseAvion(new ClasseAvion(new Classe(classe), 0));
        setClasseAvion(classeAvion);
        setPrix(prix);
    }

    public PrixVol(String id, ClasseAvion classeAvion, double prix) throws ValeurInvalideException {
        setId(id);
        setClasseAvion(classeAvion);
        setPrix(prix); // Validation du prix dans le constructeur
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClasseAvion getClasseAvion() {
        return classeAvion;
    }

    public void setClasseAvion(ClasseAvion classeAvion) {
        this.classeAvion = classeAvion;
    }

    public void setClasseAvion(String classeAvion) {
        setClasseAvion(new ClasseAvion(classeAvion));
    }

    public double getPrix() {
        return prix;
    }

    // Setter pour le prix avec validation
    public void setPrix(double prix) throws ValeurInvalideException {
        if (prix <= 0) {
            throw new ValeurInvalideException("Le prix doit être supérieur à 0.");
        }
        this.prix = prix;
    }

    public static PrixVol getById(Connection connexion, String id) throws Exception {
        String requete = "SELECT * FROM prixVol WHERE id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                ClasseAvion classeAvion = ClasseAvion.getById(connexion, resultat.getString("idClasseAvion"));
                return new PrixVol(resultat.getString("id"), classeAvion, resultat.getDouble("prix")) ;
            } else {
                throw new Exception("Aucun avion trouvé avec l'id : " + id);
            }
        }
    }

    public static PrixVol[] getByVol(Connection connexion, String idVol) throws Exception {
        
        List<PrixVol> listes = new ArrayList<>() ;
        String requete = "SELECT * FROM prixVol WHERE idVol = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, idVol);
            ResultSet resultat = declaration.executeQuery();
            while (resultat.next()) {
                ClasseAvion classeAvion = ClasseAvion.getById(connexion, resultat.getString("idClasseAvion"));
                listes.add(new PrixVol(resultat.getString("id"), classeAvion, resultat.getDouble("prix"))) ;
            } 
        }
        return listes.toArray(new PrixVol[0]);
    }

    public void insert(Connection connexion, Vol vol) throws Exception {
        
        String requete = "INSERT INTO prixVol(id, prix, idclasseavion, idvol) VALUES (DEFAULT, ?, ?, ?)";

        try (PreparedStatement declaration = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            declaration.setDouble(1, getPrix());
            declaration.setString(2, getClasseAvion().getId());
            declaration.setString(3, vol.getId());

            declaration.executeUpdate();

            try (ResultSet generatedKeys = declaration.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getString(1)); 
                } else {
                    throw new SQLException("Échec de la récupération de l'ID généré.");
                }
            }
        }
    }


    @Override
    public String toString() {
        return "PrixVol{" +
                "id='" + id + '\'' +
                ", classeAvion=" + classeAvion +
                ", prix=" + prix +
                "}";
    }
         
}
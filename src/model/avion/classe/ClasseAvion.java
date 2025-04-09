package model.avion.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.avion.Avion;

public class ClasseAvion {

    private String id;  // Classe de l'avion (ex: Économique, Affaires)
    private Classe classe;  // Classe de l'avion (ex: Économique, Affaires)
    private int nbSiege;    // Nombre de sièges dans cette classe pour l'avion

    // Constructeur
    public ClasseAvion(){}

    public ClasseAvion(Classe classe, int nbSiege) {
        setClasse(classe);
        setNbSiege(nbSiege);
    }

    public ClasseAvion(String id) {
        setId(id) ;
       
    }

    public ClasseAvion(String id, Classe classe, int nbSiege) {
        setId(id) ;
        setClasse(classe);
        setNbSiege(nbSiege);
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getNbSiege() {
        return nbSiege;
    }

    public void setNbSiege(int nbSiege) {
        this.nbSiege = nbSiege;
    }

    // Méthode pour récupérer toutes les classes d'un avion
    public static ClasseAvion[] getByAvion(Connection connexion, Avion avion) throws Exception {
        String requete = "SELECT * FROM ClasseAvion WHERE idAvion = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, avion.getId());
            ResultSet resultat = declaration.executeQuery();
            ArrayList<ClasseAvion> classesAvion = new ArrayList<>();
            while (resultat.next()) {
                Classe classe = new Classe(resultat.getString("idClasse"), null); // On suppose que la classe existe déjà
                classe.setById(connexion);
                classesAvion.add(new ClasseAvion(resultat.getString("id"),classe, resultat.getInt("nbsiege")));
            }
            return classesAvion.toArray(new ClasseAvion[0]);
        }
    }

    // Méthode pour récupérer une classe spécifique d'un avion
    public static ClasseAvion getByAvionClasse(Connection connexion, Avion avion, Classe classe) throws Exception {
        String requete = "SELECT * FROM ClasseAvion WHERE avion_id = ? AND classe_id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, avion.getId());
            declaration.setString(2, classe.getId());
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                return new ClasseAvion(classe, resultat.getInt("nb_siege"));
            } else {
                throw new Exception("Aucune classe trouvée pour cet avion et cette classe.");
            }
        }
    }

    public static ClasseAvion getById(Connection connexion, String id) throws Exception {
        String requete = "SELECT * FROM ClasseAvion WHERE id = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                Classe classe = new Classe(resultat.getString("idClasse")) ;
                classe.setById(connexion);
                return new ClasseAvion(resultat.getString("id"),classe, resultat.getInt("nbSiege"));
            } else {
                throw new Exception("Aucune classe trouvée pour cet avion et cette classe.");
            }
        }
    }

    @Override
    public String toString() {
        return "ClasseAvion{" +
                "classe=" + classe +
                ", nbSiege=" + nbSiege +
                '}';
    }
}
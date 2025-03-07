package model.vol.prix.promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import exception.ValeurInvalideException;
import model.avion.classe.ClasseAvion;
import model.vol.Vol;

public class Promotion {
    private ClasseAvion classeAvion; // Classe d'avion concernée par la promotion
    private int nb;                   // Nombre de promotions disponibles
    private float pourcentage;       // Pourcentage de réduction
    private int reste;                // Nombre de promotions restantes
    private boolean aChange ; 

    // Constructeur
    public Promotion(){}
    
    public Promotion(ClasseAvion classeAvion, int nb, float pourcentage, int reste) throws ValeurInvalideException{
        setClasseAvion(classeAvion);
        setNb(nb);
        setPourcentage(pourcentage);
        setReste(reste);
    }

    // Getters et Setters
    public ClasseAvion getClasseAvion() {
        return classeAvion;
    }

    public void setClasseAvion(ClasseAvion classeAvion) {
        this.classeAvion = classeAvion;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) throws ValeurInvalideException{
        if(nb<0) throw new ValeurInvalideException("Nombre de siège en promotion invalide");
        this.nb = nb;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) throws ValeurInvalideException {
        if(pourcentage<0) throw new ValeurInvalideException("Nombre de siège en promotion invalide");
        this.pourcentage = pourcentage;
    }

    public int getReste() {
        return reste;
    }

    public void setReste(int reste) {
        this.reste = reste;
    }

    public boolean aChange() {
        return aChange;
    }

    public void setAChange(boolean aChange) {
        this.aChange = aChange;
    }

    public void diminuerReste(int reste) {
        setReste(getReste()-reste);
    }

    // Méthode pour insérer une promotion dans la base de données
    public void insert(Connection connexion, Vol vol) throws Exception {
        String requete = "INSERT INTO Promotion (idVol, idClasseAvion, pourcentage, d_reste, nb) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, vol.getId());
            declaration.setString(2, getClasseAvion().getId());
            declaration.setFloat(3, getPourcentage());
            declaration.setInt(4, getReste());
            declaration.setInt(5, getNb());
            declaration.executeUpdate();
        }
    }

    // Méthode pour récupérer les promotions d'un vol
    public static Promotion[] getByVol(Connection connexion, Vol vol) throws Exception {
        String requete = "SELECT * FROM Promotion WHERE idVol = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, vol.getId());
            ResultSet resultat = declaration.executeQuery();
            ArrayList<Promotion> promotions = new ArrayList<>();
            while (resultat.next()) {
                ClasseAvion classeAvion = new ClasseAvion(); // On suppose que la classe existe déjà
                classeAvion.setId(resultat.getString("idClasseAvion"));
                // classeAvion = ClasseAvion.getById(Connection, classeAvion.getId());
                promotions.add(new Promotion(classeAvion, resultat.getInt("nb"), resultat.getFloat("pourcentage"), resultat.getInt("d_reste")));
            }
            return promotions.toArray(new Promotion[0]);
        }
    }

    // Méthode pour mettre à jour une promotion dans la base de données
    public void update(Connection connexion, Vol vol) throws Exception {
        String requete = "UPDATE promotion SET nb = ?, pourcentage = ?, d_reste = ? WHERE idVol = ? and idClasseAvion = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setInt(1, this.getNb());
            declaration.setDouble(2, this.getPourcentage());
            declaration.setInt(3, this.getReste());
            declaration.setString(4, vol.getId());
            declaration.setString(5, this.getClasseAvion().getId());
            declaration.executeUpdate();
        }
    }

    public static void update(Connection connection, Vol vol, Promotion[] promotions) throws Exception {
        
        String requete = "UPDATE promotion SET nb = ?, pourcentage = ?, d_reste = ? WHERE idVol = ? and idClasseAvion = ?";
        
        try (PreparedStatement declaration = connection.prepareStatement(requete)) {    
            for (Promotion promotion : promotions) {
                declaration.setInt(1, promotion.getNb());
                declaration.setDouble(2, promotion.getPourcentage());
                declaration.setInt(3, promotion.getReste());
                declaration.setString(4, vol.getId());
                declaration.setString(5, promotion.getClasseAvion().getId());
                declaration.addBatch(); // Ajouter à la batch
                
            }
    
            declaration.executeBatch(); 
        } 
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "classeAvion=" + classeAvion +
                ", nb=" + nb +
                ", pourcentage=" + pourcentage +
                ", reste=" + reste +
                '}';
    }
}
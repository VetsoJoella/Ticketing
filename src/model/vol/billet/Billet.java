package model.vol.billet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vol.Vol;
import model.vol.prix.PrixVol;

public class Billet {
    
    private String id;          // Identifiant du billet
    private PrixVol prixVol;    // PrixVol associé au billet

    // Constructeur
    public Billet() {
    }

    public Billet(String id, PrixVol prixVol) {
        this.setId(id);
        this.setPrixVol(prixVol);
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PrixVol getPrixVol() {
        return prixVol;
    }

    public void setPrixVol(PrixVol prixVol) {
        this.prixVol = prixVol;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Billet{" +
                "id='" + id + '\'' +
                ", prixVol=" + prixVol +
                '}';
    }

    // Méthode pour insérer un billet dans la base de données
    public void insert(Connection connexion, Vol vol) throws Exception {
        String requete = "INSERT INTO Billet (id, idPrixVol, idVol) VALUES (DEFAULT, ?, ?)";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, this.getPrixVol().getId());
            declaration.setString(2, vol.getId());
            declaration.executeUpdate();
        }
    }

    public void insert(Connection connection, Vol vol, Billet[] billets) throws Exception {

        String requete = "INSERT INTO Billet (id, idPrixVol, idVol) VALUES (DEFAULT, ?, ?)";
        
        try (PreparedStatement declaration = connection.prepareStatement(requete)) {    
            for (Billet billet : billets) {
                declaration.setString(1, billet.getPrixVol().getId());
                declaration.setString(2, vol.getId());
                declaration.addBatch(); // Ajouter à la batch
            }
    
            declaration.executeBatch(); 
        } 
    }
    
    // Méthode pour générer des billets pour une classe d'avion donnée
    public static Billet[] genererBillet(PrixVol prixVol) throws Exception {
      
        ArrayList<Billet> billets = new ArrayList<>();
        while (billets.size()<prixVol.getClasseAvion().getNbSiege()) {
            billets.add(new Billet(null, prixVol)) ;
        }
        return billets.toArray(new Billet[0]) ;
       
    }

    // Méthode pour récupérer les billets associés à un avion
    public static Billet[] getBilletByVol(Connection connexion, Vol vol) throws Exception {
        String requete = "SELECT * FROM Billet WHERE idVol = ?";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, vol.getId());
            ResultSet resultat = declaration.executeQuery();
            ArrayList<Billet> billets = new ArrayList<>();
            while (resultat.next()) {
                // Récupérer le PrixVol associé au billet
                PrixVol prixVol = PrixVol.getById(connexion, resultat.getString("idPrixVol"));
                Billet billet = new Billet(resultat.getString("id"), prixVol);
                billets.add(billet);
            }
            return billets.toArray(new Billet[0]);
        }
    }

    public static Billet[] getBilletDisponible(Connection connexion, Vol vol) throws Exception {
        
        List<Billet> billets = new ArrayList<>() ;
        String requete = "SELECT * from v_billet_disponible where idVol = ? " ;
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, vol.getId());
            ResultSet resultat = declaration.executeQuery();
            while (resultat.next()) {
                billets.add(new Billet(resultat.getString("id"), PrixVol.getById(connexion, resultat.getString("idprixvol"))));
            }
        }

        return billets.toArray(new Billet[0]);
    }

    public static Billet getById(Connection connexion, String id) throws Exception {
        
        String requete = "SELECT * from billet where id = ? " ;
        Billet billet = null ; 

        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            while (resultat.next()) {
                billet = (new Billet(resultat.getString("id"), PrixVol.getById(connexion, resultat.getString("idprixvol"))));
            }
        }
        return billet ; 
    }   
}

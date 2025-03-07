package model.vol.historique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import model.vol.Vol;

public class HistoriqueAnnulation extends Historique {
    
    public HistoriqueAnnulation() {} 

    public HistoriqueAnnulation(Vol vol, float dernierChangement, Timestamp date) {
        super(vol, dernierChangement, date);
    } 

    public HistoriqueAnnulation(String id, Vol vol, float dernierChangement, Timestamp date) {
        super(id, vol, dernierChangement, date);
    } 

    @Override
    public void insert(Connection connexion) throws Exception{
        
        String requete = "INSERT INTO HistoriqueAnnulation (id, idVol, dateChangement, derniereReservation) VALUES (DEFAULT, ?, ?, ?, ?)";
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, getVol().getId());
            declaration.setTimestamp(2, getDateChangement());
            declaration.setFloat(4, getDernierChangement());
            declaration.executeUpdate();
        }

    }
}

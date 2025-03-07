package model.vol.historique;

import java.sql.Connection;
import java.sql.Timestamp;

import model.vol.Vol;

public abstract class Historique {
    
    private String id;               // Identifiant de l'historique
    private Vol vol;  
    private float dernierChangement ;               // Vol concerné par le changement
    private Timestamp dateChangement; 
    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vol getVol() {
        return this.vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public float getDernierChangement() {
        return this.dernierChangement;
    }

    public void setDernierChangement(float dernierChangement) {
        this.dernierChangement = dernierChangement;
    }

    public Timestamp getDateChangement() {
        return this.dateChangement;
    }

    public void setDateChangement(Timestamp dateChangement) {
        this.dateChangement = dateChangement;
    }
    
    public Historique(){}

    public Historique( Vol vol, float dernierChangement, Timestamp date){

        setVol(vol);
        setDernierChangement(dernierChangement);
        setDateChangement(date);
    }

    public Historique(String id, Vol vol, float dernierChangement, Timestamp date){

        setId(id);
        setVol(vol);
        setDernierChangement(dernierChangement);
        setDateChangement(date);
    }

    abstract void insert(Connection connection) throws Exception ;

    @Override
    public String toString() {
        return "HistoriqueDernierChangement{" +
                "id='" + id + '\'' +
                ", vol=" + vol +
                ", dateChangement=" + dateChangement +
                '}';
    }
}

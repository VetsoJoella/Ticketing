package model.vol;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.ValeurInvalideException;
import exception.model.vol.VolException;
import model.avion.Avion;
import model.avion.classe.ClasseAvion;
import model.avion.modele.Modele;
import model.vol.billet.Billet;
import model.vol.prix.PrixVol;
import model.vol.prix.promotion.Promotion;
import model.vol.reservation.Reservation;
import model.vol.ville.Ville;
import util.date.DateUtil;

public class Vol {

    private String id; 
    private Avion avion;                  
    private Ville villeDepart;            
    private Ville villeDestination;    
    private PrixVol[] prixVols ;   
    private Billet[] billets;              
    private Billet[] billetDisponibles;              
    private Promotion[] promotions;        
    private Timestamp dateHeureDecollage;      
    private float dernierReservation;      
    private float derniereAnnulation;      

    // Constructeur

    public Vol(){}

    public Vol(String id){
        setId(id);
    }

    public Vol(Connection connexion) throws Exception {
        setBilletDisponibles(connexion);
        setPromotion(connexion);
        
    }

    public Vol( Ville villeDepart, Ville villeDestination, Timestamp dateHeureDecollage, float dernierReservation, float derniereAnnulation, PrixVol[] prixVols ) throws Exception {

        setPrixVols(prixVols);
        // setBillets(genererBillet(getPrixVols()));
        setVilleDepart(villeDepart);
        setVilleDestination(villeDestination);
        setDateHeureDecollage(dateHeureDecollage);
        setDernierReservation(dernierReservation);
        setDerniereAnnulation(derniereAnnulation);
        
    }

    public Vol(String id, Ville villeDepart, Ville villeDestination, Timestamp dateHeureDecollage, float dernierReservation, float derniereAnnulation, Avion avion) throws VolException {
        setId(id);
        setVilleDepart(villeDepart);
        setVilleDestination(villeDestination);
        setDateHeureDecollage(dateHeureDecollage);
        setAvion(avion);
        setDernierReservation(dernierReservation);
        setDerniereAnnulation(derniereAnnulation);
        billets = new Billet[0];      // Initialiser avec un tableau vide
        promotions = new Promotion[0]; // Initialiser avec un tableau vide
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        setVilleDepart(new Ville(villeDepart));
    }

    public Ville getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(Ville villeDestination) throws VolException{
        if(villeDestination.getId().equalsIgnoreCase(getVilleDepart().getId())) throw new VolException(this, "La ville de départ ne peut pas etre la ville destination");
        this.villeDestination = villeDestination;
    }

    public void setVilleDestination(String villeDestination) throws VolException{
        setVilleDestination(new Ville(villeDestination));
    }

    public Timestamp getDateHeureDecollage() {
        return dateHeureDecollage;
    }

    public void setDateHeureDecollage(Timestamp dateHeureDecollage) {
        this.dateHeureDecollage = dateHeureDecollage;
    }

    public void setDateHeureDecollage(String dateHeureDecollage) {
       System.out.println("Valeur de timestamp à caster est "+dateHeureDecollage);
       setDateHeureDecollage(DateUtil.formatStr(dateHeureDecollage));
    }

    public PrixVol[] getPrixVols() {
        return prixVols;
    }

    public void setPrixVols(PrixVol[] prixVols) throws Exception{
        System.out.println("Appel de setPrixVols "+prixVols.length);
        this.prixVols = prixVols; 
        setBillets(genererBillet(getPrixVols()));

    }

    public void setPrixVols(Connection connexion, String[] classeAvions, double[] prix) throws Exception{

        List<PrixVol> prixVols = new ArrayList<>() ; 
        for (int i = 0; i < prix.length; i++) {
            prixVols.add(new PrixVol(ClasseAvion.getById(connexion, classeAvions[i]), prix[i])) ; 
             
        }
        setPrixVols(prixVols.toArray(new PrixVol[0]));
        // this.prixVols = PrixVols;
    }

    public void setPrixVols(Connection connexion) throws Exception{
        setPrixVols(PrixVol.getByVol(connexion, getId()));
    }

    public Billet[] getBillets() {
        return billets;
    }

    public void setBillets(Billet[] billets) {
        this.billets = billets;
    }

    public Billet[] getBilletDisponibles() {
        return billetDisponibles;
    }

    public Billet[] getBilletDisponibles(ClasseAvion classeAvion){

        List<Billet> billets = new ArrayList<>() ;
        for (Billet billet : getBilletDisponibles()) {
            if(billet.getPrixVol().getClasseAvion().getId().equalsIgnoreCase(classeAvion.getId())) {
                billets.add(billet) ;
            }
        }
        return billets.toArray(new Billet[0]);

    }
    public void setBilletDisponibles(Billet[] billets) {
        this.billetDisponibles = billets;
    }

    public void setBilletDisponibles(Connection connexion) throws Exception {
        setBilletDisponibles(Billet.getBilletDisponible(connexion, this));
    } 

    public Promotion[] getPromotions() {
        return promotions;
    }

    public Promotion getPromotion(PrixVol prixVol) {
        for (Promotion promotion : getPromotions()) {
            if(promotion.getClasseAvion().getId().equalsIgnoreCase(prixVol.getClasseAvion().getId())) return promotion ;
        }
        return new Promotion() ; 
    }

    public void setPromotions(Promotion[] promotions) {
        this.promotions = promotions;
        System.out.println("Longueur de tableau de promotion "+promotions.length);
    }

    public void setPromotions(String[] classeAvions, int[] nbs, float[] pourcentage) throws ValeurInvalideException{

        List<Promotion> promotions = new ArrayList<>() ; 
        for (int i = 0; i < classeAvions.length; i++) {
            promotions.add(new Promotion(classeAvions[i], nbs[i], pourcentage[i])) ; 
        }
        setPromotions(promotions.toArray(new Promotion[0]));
    }

    public void setPromotions(Connection connexion) throws Exception {
        setPromotions(Promotion.getByVol(connexion, this));
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setAvion(String avion) {
        setAvion(new Avion(avion));
    }

    public float getDernierReservation() {
        return dernierReservation;
    }

    public Timestamp getDateDernierReservation() {
        return DateUtil.ajouterHeure(getDateHeureDecollage(), -getDernierReservation());
    }

    public void setDernierReservation(float dernierReservation) {
        this.dernierReservation = dernierReservation;
    }

    public void setDernierReservation(String dernierReservation) {
        setDernierReservation(Float.valueOf(dernierReservation));
    }

    public float getDerniereAnnulation() {
        return derniereAnnulation;
    }

    public Timestamp getDateDerniereAnnulation() {
        return DateUtil.ajouterHeure(getDateHeureDecollage(), -getDerniereAnnulation());
    }


    public void setDerniereAnnulation(float derniereAnnulation) {
        this.derniereAnnulation = derniereAnnulation;
    }

    public void setDerniereAnnulation(String derniereAnnulation) {
        setDerniereAnnulation(Float.valueOf(derniereAnnulation));
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Vol{" +
                "id='" + id + '\'' +
                ", villeDepart=" + villeDepart +
                ", villeDestination=" + villeDestination +
                ", dateHeureDecollage=" + dateHeureDecollage +
                ", avion=" + avion +
                ", dernierReservation=" + dernierReservation +
                '}';
    }

    // Méthode pour générer des billets à partir d'une liste de PrixVol
    Billet[] genererBillet(PrixVol[] prixVols) throws Exception {
        ArrayList<Billet> billetsGeneres = new ArrayList<>();
        for (PrixVol prixVol : prixVols) {
           billetsGeneres.addAll(Arrays.asList(Billet.genererBillet(prixVol)));
        }
        return billetsGeneres.toArray(new Billet[0]);
    }

    public void setBillets(Connection connexion) throws Exception {
       setBillets(Billet.getBilletByVol(connexion, this));
    }

    public void setPromotion(Connection connexion) throws Exception {
       setPromotions(Promotion.getByVol(connexion, this));
    }

    void insererPrixVol(Connection connexion) throws Exception {
        for (PrixVol prixVol : getPrixVols()) {
            prixVol.insert(connexion, this);
        }
        insererBillet(connexion);

    }

    void insererBillet(Connection connexion) throws Exception {
        new Billet().insert(connexion, this, getBillets());
    }

    void insertVol(Connection connexion) throws Exception {
        String requete = "INSERT INTO Vol (id, d_derniereReservation, d_derniereAnnulation, idAvion, idVilleDestination, idVilleDepart, dateDecollage) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement declaration = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {

            declaration.setDouble(1, this.getDernierReservation()); // d_derniereReservation
            declaration.setDouble(2, this.getDerniereAnnulation()); // d_derniereAnnulation
            declaration.setString(3, this.getAvion().getId());      // idAvion
            declaration.setString(4, this.getVilleDestination().getId()); // idVilleDestination
            declaration.setString(5, this.getVilleDepart().getId());      // idVilleDepart
            declaration.setTimestamp(6, this.getDateHeureDecollage()); // dateDecollage

            declaration.executeUpdate();

            try (ResultSet generatedKeys = declaration.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getString(1));
                    // gererHistoriqueAnnulation(connexion);
                    // gererHistoriqueReservation(connexion);
                } else {
                    throw new SQLException("Échec de la récupération de l'ID généré.");
                }
            }
        }
    }

    void insertPromotion(Connection connexion) throws Exception {
        
        if(getPromotions()!=null) {
            for (Promotion promotion : getPromotions()) {
                promotion.insert(connexion, this);
            }
        }
       
    }

    public void insert(Connection connexion) throws Exception {

        try {
            connexion.setAutoCommit(false);
            insertVol(connexion);
            insererPrixVol(connexion);
            insertPromotion(connexion);
            connexion.commit();
        } catch (Exception e) {
            connexion.rollback();
            throw e ;
        } finally {
            connexion.setAutoCommit(true);
        }

    }

    public void ajouterPromotion(Connection connexion,Promotion promotion) throws Exception {

        promotion.insert(connexion, this);
    }

    void updatePromotion(Connection connexion, Promotion promotion) throws Exception {

        promotion.update(connexion, this);        
    }

    public void updatePromotion(Connection connexion) throws Exception {
        for (Promotion promotion : getPromotions()) {
            updatePromotion(connexion, promotion);
        }
    }

    public static Vol[] getByCriteria(Connection connexion, String idDepart, String idDestination, String minDecollage, String maxDecollage, String idAvion) throws Exception {

        Ville depart = null, destination = null ; 
        Timestamp min = null, max = null; 
        Avion avion = null ;
        if(idDepart!=null && !idDepart.isEmpty() && !(idDepart.trim()).isEmpty()) depart = new Ville(idDepart);
        if(idDestination!=null && !idDestination.isEmpty() && !(idDestination.trim()).isEmpty()) destination = new Ville(idDestination);

        if(idAvion!=null && !idAvion.isEmpty()) avion = new Avion(idAvion);
        try {
            min = Timestamp.valueOf(minDecollage); 
        } catch (Exception e) {}

        try {
            max = Timestamp.valueOf(maxDecollage); 
        } catch (Exception e) {}

        return getByCriteria(connexion, depart, destination, min, max, avion);

    }
    
    public static Vol[] getByCriteria(Connection connexion, Ville depart, Ville destination, Timestamp minDecollage, Timestamp maxDecollage, Avion avion) throws Exception {

        System.out.println("Départ "+depart.toString()+" Arrivée "+destination.toString()+" min "+minDecollage +" max "+maxDecollage+" Avion "+avion);

        List<Vol> vols = new ArrayList<>() ;
        String requete = "SELECT * from v_vol_avion where 1=1 " ;

        if(depart!=null && depart.getId()!=null && !depart.getId().isEmpty()) requete+=" and idVilleDepart = ? ";
        if(destination!=null && destination.getId()!=null && !destination.getId().isEmpty()) requete+=" and idVilleDestination = ? ";
        if(minDecollage!=null) requete+=" and datedecollage >= ? ";
        if(maxDecollage!=null) requete+=" and datedecollage <= ? ";
        if(avion!=null && avion.getId()!=null && !avion.getId().isEmpty()) requete+=" and idAvion = ? ";

        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {

            int i = 1 ;
            if(depart!=null && depart.getId()!=null && !depart.getId().isEmpty()) {declaration.setString(i, depart.getId()); i++;}
            if(destination!=null && destination.getId()!=null && !destination.getId().isEmpty()) {declaration.setString(i, destination.getId()); i++;}
            if(minDecollage!=null) {declaration.setTimestamp(i, minDecollage); i++;}
            if(maxDecollage!=null) {declaration.setTimestamp(i, maxDecollage); i++;}
            if(avion!=null && avion.getId()!=null && !avion.getId().isEmpty()) {declaration.setString(i, avion.getId()); i++;}
            
            ResultSet resultat = declaration.executeQuery();
            System.out.println("La requete est "+requete);
            while (resultat.next()) {
                Vol vol =  new Vol(
                    resultat.getString("id"), 
                    Ville.getById(connexion, resultat.getString("idvilledepart")), 
                    Ville.getById(connexion, resultat.getString("idvilledestination")), 
                    resultat.getTimestamp("datedecollage"), 
                    resultat.getFloat("d_dernierereservation"), 
                    resultat.getFloat("d_derniereAnnulation"), 
                    new Avion(
                        resultat.getString("idAvion"), 
                        resultat.getString("nom"), 
                        resultat.getDate("dateFabrication"), 
                        new Modele(resultat.getString("idModele"))
                    ));

                vols.add(vol);

            }
        }

        return vols.toArray(new Vol[0]);
    }

    public static Vol getById(Connection connexion, String id) throws Exception {

        Vol vol = null ;
        String requete = "select * from v_vol_avion where id = ?" ;
        try (PreparedStatement declaration = connexion.prepareStatement(requete)) {
            declaration.setString(1, id);
            ResultSet resultat = declaration.executeQuery();
            if (resultat.next()) {
                vol =  new Vol(
                    resultat.getString("id"), 
                    Ville.getById(connexion, resultat.getString("idvilledepart")), 
                    Ville.getById(connexion, resultat.getString("idvilledestination")), 
                    resultat.getTimestamp("datedecollage"), 
                    resultat.getFloat("d_dernierereservation"), 
                    resultat.getFloat("d_derniereAnnulation"), 
                    new Avion(
                        resultat.getString("idAvion"), 
                        resultat.getString("nom"), 
                        resultat.getDate("dateFabrication"), 
                        new Modele(resultat.getString("idModele"))
                    ));

                    vol.setPrixVols(connexion);
                    vol.setBilletDisponibles(connexion);
                    vol.setPromotions(connexion);
            } else {
                throw new Exception("Aucune ville trouvée avec l'id : " + id);
            }
        }

        return vol ;
    }

    public Promotion getNbPromotion(ClasseAvion classeAvion) {
        for (Promotion promotion : getPromotions()) {
            if(promotion.getClasseAvion().getId().equalsIgnoreCase(classeAvion.getId()) && promotion.getReste()>0 ){
                return promotion ;
            }
        }
        return new Promotion() ;   
    }

    public void reserver(Connection connexion, Reservation reservation) throws Exception {

        connexion.setAutoCommit(false); 
        try {
            reservation.insert(connexion, this);
            List<Promotion> listes = new ArrayList<>() ;
            for (Promotion promotion : getPromotions()) {
                if(promotion.aChange()) listes.add(promotion) ;
            }
            Promotion.update(connexion, this, listes.toArray(new Promotion[0]));
            connexion.commit();
        } catch (Exception e) {
            connexion.rollback();
            throw e ; 
        }

    }

    public void update(Connection connection) throws Exception {

        String requete = "UPDATE vol SET d_dernierereservation = ?, d_derniereannulation = ? WHERE id = ? " ;
        
        try (PreparedStatement declaration = connection.prepareStatement(requete)) { 

            declaration.setFloat(1, getDernierReservation());          
            declaration.setFloat(2, getDerniereAnnulation());   
            declaration.setString(3, getId());   
            declaration.executeUpdate() ;       

        } 
    }

    // public void annuler(Connection connexion, Reservation reservation, Timestamp dateAnnulation) throws ReservationException {

    //     Timestamp dateDerniereAnnulation = DateUtil.ajouterHeure(getDateHeureDecollage(), -getDerniereAnnulation());
    //     if(dateAnnulation.after(dateDerniereAnnulation)) throw new ReservationException(reservation, "Annulation de réservation impossible : la date de dernière annulation était dépassée");
    //     else {
    //         try {
    //             connexion.setAutoCommit(false);
    //             reservation.annuler(connexion) ;
    //             connexion.commit(); 
    //         } catch (Exception e) {
    //             connexion.rollback();
    //             throw e ;
    //         } 
    //     }
    // }

   
}
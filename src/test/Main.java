package test;

import java.lang.reflect.Array;
import java.sql.Connection;

import model.vol.Vol;
// import util.connection.UtilDb;

public class Main {
  
    // public static void main(String[] args) {
    //     // Exemple d'utilisation avec un type primitif
    //     double[] doubleArray = (double[])allocatePrimitiveArray(10, double.class);
    //     System.out.println("Tableau de double alloué : " + doubleArray.getClass().getSimpleName());

    //     // Exemple d'utilisation avec un type objet
    //     String[] stringArray = allocateObjectArray(5, String.class);
    //     System.out.println("Tableau de String alloué : " + stringArray.getClass().getSimpleName());
    // }

    // @SuppressWarnings("unchecked")
    // public static <T> T[] allocateObjectArray(int nb, Class<T> clazz) {
    //     if (clazz.isPrimitive()) {
    //         throw new IllegalArgumentException("La classe ne doit pas être un type primitif.");
    //     }
    //     System.out.println("Appel de allocateObjectArray pour " + clazz.getName());
    //     return (T[]) Array.newInstance(clazz, nb);
    // }

    /**
     * Alloue un tableau de primitifs (pour les types primitifs).
     */
    // public static Object allocatePrimitiveArray(int nb, Class<?> clazz) {
    //     if (!clazz.isPrimitive()) {
    //         throw new IllegalArgumentException("La classe doit être un type primitif.");
    //     }
    //     System.out.println("Appel de allocatePrimitiveArray pour " + clazz.getName());
    //     return Array.newInstance(clazz, nb);
    // }
    //public static void main(String[] args) {

        // try (Connection connection = UtilDb.getConnection()) {
        //     connection.setAutoCommit(false);
        //     System.out.println("Connection réussie");

            // Vol vol = Vol.getById(connection, "VOL00002");
            // Passager passager = new Passager("PSG00001");
            // DetailReservation[] detailReservations = new DetailReservation[]{
            //     new DetailReservation("CLS_AVN00002", "4") ,
            //     new DetailReservation("CLS_AVN00001", "2") 
            // };

            // Reservation reservation = new Reservation(null, passager.getId(), "2025-02-24 12:00:00", vol) ; 
            // reservation.setReservationFilles(vol, detailReservations);
            // vol.reserver(connection, reservation);

            // Reservation reservation = Reservation.getById(connection, "RVT00012");
            // Vol vol = reservation.getVolRelie(connection);

            // AnnulationReservation annulationReservation = new AnnulationReservation(null, Timestamp.valueOf("2025-02-26 08:00:00"), reservation, vol);
            // annulationReservation.confirmerAnnulation(connection);


            // Vol[] vols = Vol.getByCriteria(connection, "VLL00001", null, null, "2025-03-10", null) ; 
            // for (Vol vol2 : vols) {
            //     System.out.println(vol2.toString());
            // }
            
            // // connection.commit();
            // System.out.println("Données insérées avec succès");
            
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    // }

    public static Object allocate(int nb, Class<?> clazz) {
        System.out.println("Allocation d'un tableau de type : " + clazz.getName());

        // Vérifie si le type est un tableau
        if (!clazz.isArray()) {
            throw new IllegalArgumentException("Le type spécifié n'est pas un tableau.");
        }

        // Récupère le type des éléments du tableau
        Class<?> componentType = clazz.getComponentType();

        // Alloue un tableau du type approprié
        if (componentType.isPrimitive()) {
            // Cas des tableaux de primitifs
            return Array.newInstance(componentType, nb);
        } else {
            // Cas des tableaux d'objets
            return Array.newInstance(componentType, nb);
        }
    }
    public static void main(String[] args) {
        // Exemple d'utilisation
        Class<?>[] parameters = { double[].class, String[].class, int[].class };

        Object[] formValues = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isArray()) {
                formValues[i] = allocate(5, parameters[i]);
                System.out.println("Tableau alloué : " + formValues[i].getClass().getSimpleName());
            }
        }

        // Affichage des tableaux alloués
        System.out.println("Tableau de double : " + formValues[0].getClass().getSimpleName());
        System.out.println("Tableau de String : " + formValues[1].getClass().getSimpleName());
        System.out.println("Tableau de int : " + formValues[2].getClass().getSimpleName());
    }}

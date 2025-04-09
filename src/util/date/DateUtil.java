package util.date;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class DateUtil {
    
    public static Timestamp ajouterHeure(Timestamp timestamp, float duree) {

        Duration duration = Duration.ofMillis((long)(duree*3600000));

        Instant instant = timestamp.toInstant() ;
        instant = instant.plus(duration) ; 

        return Timestamp.from(instant) ;
    }

    public static Timestamp formatStr(String dateHeureDecollage){

        // // Définir le format de la date/heure reçue
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // // Convertir la chaîne en LocalDateTime
        // LocalDateTime localDateTime = LocalDateTime.parse(dateHeureDecollage, formatter);

        // // Convertir LocalDateTime en Timestamp
        // Timestamp timestamp = Timestamp.valueOf(localDateTime);

        // // Appeler la méthode setDateHeureDecollage avec le Timestamp
        // return (timestamp);

        LocalDateTime localDateTime = LocalDateTime.parse(dateHeureDecollage);
        return (Timestamp.valueOf(localDateTime));
    }
}

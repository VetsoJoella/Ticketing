// package util.connection;


// import java.sql.Connection;
// import java.sql.SQLException;
// import java.time.Duration;

// import org.apache.commons.dbcp2.BasicDataSource;

// public class UtilDb {

//     private static BasicDataSource dataSource;

//     static {
//         dataSource = new BasicDataSource();
        
//         // Configuration du pool pour PostgreSQL
//         dataSource.setDriverClassName("org.postgresql.Driver");
//         dataSource.setUrl("jdbc:postgresql://localhost:5432/ticketing");
//         dataSource.setUsername("postgres");
//         dataSource.setPassword("postgres");
        
//         // Paramètres du pool
//         dataSource.setMaxTotal(4);
//         dataSource.setMaxIdle(2);
//         dataSource.setMinIdle(2);
//         dataSource.setMaxWait(Duration.ofSeconds(5)); // 10 secondes
        
//         // Paramètres spécifiques à PostgreSQL
//         // dataSource.setConnectionProperties("ssl=true;sslfactory=org.postgresql.ssl.SSLFactory");
//     }

//     public static Connection getConnection() throws SQLException {
//         return dataSource.getConnection();
//     }
// }
package ControlJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {

    //URL de connexion
    private static final String url = "jdbc:postgresql://localhost:5432/test_jdbc";

    //Nom de l'utilisateur
    private static final String user = "postgres";

    //Mot de passe de l'utilisateur
<<<<<<< HEAD
    private static final String passwd = "Sachaenzo@0609";
=======
    private static final String passwd = "root@postgres";
>>>>>>> ecbe9cf (Initial commit)

    //Objet Connection
    private volatile static Connection connect;


    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance() throws SQLException, ClassNotFoundException {

        if(connect == null) {

            Class.forName("org.postgresql.Driver");

            synchronized(Connection.class) { if(connect == null) connect = DriverManager.getConnection(url, user, passwd); }

            System.out.println("Connexion SQL Établie !");
        }
        return connect;
    }
}

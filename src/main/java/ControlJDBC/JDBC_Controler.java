package ControlJDBC;

import java.sql.*;
import java.util.Objects;

public class JDBC_Controler {

    protected static Object autoCommit;

    private static Statement state;
    private static final Object[] result = new Object[2];


    /** PARTIE CONNECTION **/

    // Connection à la base de donnée //
    protected static void Connection() throws SQLException, ClassNotFoundException {

        //Nous appelons la méthode getInstance() pour se connecter à la base de donnée
        JDBC_Connect.getInstance();
    }
    // Connection à la base de donnée //

    // Connection à la base de donnée avec précision 'setAutoCommit(boolean)' //
    @SuppressWarnings("SameParameterValue")
    protected static void Connection(boolean auto) throws SQLException, ClassNotFoundException {

        JDBC_Connect.getInstance().setAutoCommit(auto);
        autoCommit = auto;
    }
    // Connection à la base de donnée avec précision 'setAutoCommit(boolean)' //

    /** PARTIE CONNECTION **/


    @SuppressWarnings("SameParameterValue")
    protected static void showTable(String tablename) throws SQLException, ClassNotFoundException {

        //L'objet ResultSet contient le résultat de la requête SQL
        result[0] = state.executeQuery("SELECT * FROM " + tablename);


        if(result[0] instanceof ResultSet RS) {

            //On récupère les MetaData
            ResultSetMetaData resultMeta = RS.getMetaData();

            if(Objects.equals(tablename, "professor")) {

                System.out.println("- Il y a " + resultMeta.getColumnCount() + " colonnes dans cette table");

                for(int i = 1; i <= resultMeta.getColumnCount(); i++) System.out.println("\t *" + resultMeta.getColumnName(i));


                System.out.println("\nVoici les noms et prénoms : ");
                System.out.println("\n---------------------------------");

                while(RS.next()) {

                    System.out.print("\t" + RS.getInt("prof_id") + "\t |");
                    System.out.print("\t" +  RS.getString("prof_lastname") + "\t |");
                    System.out.print("\t" +  RS.getString("prof_firstname") + "\t |");

                    System.out.println("\n---------------------------------");
                }

            } else {

                System.out.println("\n**********************************");
                //On affiche le nom des colonnes
                for(int i = 1; i <= resultMeta.getColumnCount(); i++) System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

                System.out.println("\n**********************************");

                while(RS.next()) {

                    for(int i = 1; i <= resultMeta.getColumnCount(); i++) System.out.print("\t" + RS.getObject(i).toString() + "\t |");

                    System.out.println("\n---------------------------------");
                }

            }

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un objet est mal moulé !"); }
    }


    protected static void getTableInfo() throws SQLException, ClassNotFoundException {

        String query = "SELECT prof_lastname, prof_firstname, sub_name, cls_name FROM professor";
        query += " INNER JOIN j_sub_prof ON jmp_prof_k = prof_id";
        query += " INNER JOIN subject ON jmp_sub_k = sub_id";
        query += " INNER JOIN j_cls_jmp ON jcm_jmp_k = jmp_id";
        query += " INNER JOIN class ON jcm_cls_k = cls_id AND cls_id IN(1, 7)";
        query += " ORDER BY cls_name DESC, prof_lastname";

        result[0] = state.executeQuery(query);

        String name = "";
        String nameClass = "";

        if(result[0] instanceof ResultSet RS) {

            while(RS.next()) {

                if(!nameClass.equals(RS.getString("cls_name"))) {

                    nameClass = RS.getString("cls_name");
                    System.out.println("Class de " + nameClass + " :");
                }

                if(!name.equals(RS.getString("prof_lastname"))) {

                    name = RS.getString("prof_lastname");
                    System.out.println("\t * " + name + " " + RS.getString("prof_firstname") + " enseigne : ");
                }

                System.out.println("\t\t\t - " + RS.getString("sub_name"));
            }

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un objet est mal moulé !"); }
    }

    @SuppressWarnings("SameParameterValue")
    protected static void getSpecialCharValueFromTable(char character) throws SQLException, ClassNotFoundException {

        //On crée la requête
        String query = "SELECT prof_lastname, prof_firstname FROM professor";

        //Premier trou pour le nom du professeur
        query += " WHERE prof_lastname = " + character;

        //Deuxième trou pour l'identifiant du professeur
        query += " OR prof_id = " + character;

        //On crée l'objet avec la requête en paramètre
        result[0] = JDBC_Connect.getInstance().prepareStatement(query);


        if(result[0] instanceof PreparedStatement RS) {

            //On remplace le premier trou par le nom du professeur
            RS.setString(1, "MAMOU");

            //On remplace le deuxième trou par l'identifiant du professeur
            RS.setInt(2, 2);

            //On affiche la requête exécutée
            System.out.println(RS);

            //On modifie le premier trou
            RS.setString(1, "TOTO");

            //On affiche à nouveau la requête exécutée
            System.out.println(RS);

            //On modifie le deuxième trou
            RS.setInt(2, 159753);

            //On affiche une nouvelle fois la requête exécutée
            System.out.println(RS);

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un objet est mal moulé !"); }
    }


    protected static void showContentTableWithLine() throws SQLException, ClassNotFoundException {

        String query = "SELECT prof_lastname, prof_firstname FROM professor";
        
        //On crée l'objet avec la requête en paramètre
        result[0] = state.executeQuery(query);
        int i = 1;

        if(result[0] instanceof ResultSet RS) {

            System.out.println("\n\t---------------------------------------");
            System.out.println("\tLECTURE STANDARD.");
            System.out.println("\t---------------------------------------");

            while(RS.next()) {

                System.out.println("\tNom : " + RS.getString("prof_lastname")  + " \t prénom : " + RS.getString("prof_firstname"));

                //On regarde si on se trouve sur la dernière ligne du résultat
                if(RS.isLast()) System.out.println("\t\t* DERNIER RESULTAT !\n");
                i++;
            }

            //Une fois la lecture terminée, on contrôle si on se trouve bien à l'extérieur des lignes de résultat
            if(RS.isAfterLast()) System.out.println("\tNous venons de terminer !\n");

            System.out.println("\t---------------------------------------");
            System.out.println("\tLecture en sens contraire.");
            System.out.println("\t---------------------------------------");

            //On se trouve alors à la fin
            //On peut parcourir le résultat en sens contraire
            while(RS.previous()) { System.out.println("\tNom : " + RS.getString("prof_lastname") + " \t prénom : " + RS.getString("prof_firstname"));

                //On regarde si on se trouve sur la première ligne du résultat
                if(RS.isFirst()) System.out.println("\t\t* RETOUR AU DEBUT !\n");
            }

            //On regarde si on se trouve avant la première ligne du résultat
            if(RS.isBeforeFirst()) System.out.println("\tNous venons de revenir au début !\n");

            System.out.println("\t---------------------------------------");
            System.out.println("\tAprès positionnement absolu du curseur à la place N° " + i/2 + ".");
            System.out.println("\t---------------------------------------");

            //On positionne le curseur sur la ligne i/2
            //Peu importe où on se trouve
            RS.absolute(i/2);

            while(RS.next()) System.out.println("\tNom : " + RS.getString("prof_lastname") +" \t prénom : " + RS.getString("prof_firstname"));

            System.out.println("\t---------------------------------------");
            System.out.println("\tAprès positionnement relatif du curseur à la place N° " + (i - (i-2)) + ".");
            System.out.println("\t---------------------------------------");

            //On place le curseur à la ligne actuelle moins i-2
            //Si on n'avait pas mis de signe moins, on aurait avancé de i-2 lignes
            RS.relative(-(i-2));

            while(RS.next()) System.out.println("\tNom : " + RS.getString("prof_lastname") + " \t prénom : " + RS.getString("prof_firstname"));

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un objet est mal moulé !"); }
    }

    protected static void updateTable() throws SQLException, ClassNotFoundException {

        //On va chercher une ligne dans la base de données
        String query = "SELECT prof_id, prof_lastname, prof_firstname FROM professor " + "WHERE prof_lastname = 'MAMOU'";

        result[0] = state.executeQuery(query);

        if(result[0] instanceof ResultSet RS) {

            RS.first();

            //On affiche ce que l'on trouve
            System.out.println("NOM : " + RS.getString("prof_lastname") + " - PRENOM : " + RS.getString("prof_firstname"));

            //On met à jour les champs
            RS.updateString("prof_lastname", "COURTEL");
            RS.updateString("prof_firstname", "Angelo");
            //On valide
            RS.updateRow();

            //On affiche les modifications
            System.out.println("*********************************");
            System.out.println("APRES MODIFICATION : ");
            System.out.println("\tNOM : " + RS.getString("prof_lastname") + " - PRENOM : " + RS.getString("prof_firstname") + "\n");

            //On remet les informations de départ
            RS.updateString("prof_lastname", "MAMOU");
            RS.updateString("prof_firstname", "Daniel");
            //On valide à nouveau
            RS.updateRow();

            //Et voilà !
            System.out.println("*********************************");
            System.out.println("APRES REMODIFICATION : ");
            System.out.println("\tNOM : " + RS.getString("prof_lastname") + " - PRENOM : " + RS.getString("prof_firstname") + "\n");

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("An object is badly casted !"); }
    }

    protected static void updateValuesTable() throws SQLException, ClassNotFoundException {

        //On va chercher une ligne dans la base de données
        String query = "SELECT prof_lastname, prof_firstname FROM professor WHERE prof_lastname ='MAMOU'";

        //On exécute la requête
        result[0] = state.executeQuery(query);

        result[1] = JDBC_Connect.getInstance().prepareStatement("UPDATE professor set prof_firstname = ? WHERE prof_lastname = 'MAMOU'");

        if(result[0] instanceof ResultSet RS && result[1] instanceof PreparedStatement PS) {

            RS.first();

            //On affiche
            System.out.println("\n\tDONNEES D'ORIGINE : ");
            System.out.println("\t-------------------");
            System.out.println("\tNOM : " + RS.getString("prof_lastname") + " - PRENOM : " +  RS.getString("prof_firstname"));

            //On paramètre notre requête préparée
            PS.setString(1, "Gérard");

            //On exécute
            PS.executeUpdate();

            RS = state.executeQuery(query);
            RS.first();
            //On affiche à nouveau
            System.out.println("\n\t\t APRES MAJ : ");
            System.out.println("\t\t * NOM : " + RS.getString("prof_lastname") + " - PRENOM :" + RS.getString("prof_firstname"));

            //On effectue une mise à jour
            PS.setString(1, "Daniel");
            PS.executeUpdate();

            RS = state.executeQuery(query);
            RS.first();
            //On affiche une nouvelle fois
            System.out.println("\n\t\t REMISE A ZERO : ");
            System.out.println("\t\t * NOM : " + RS.getString("prof_lastname") + " - PRENOM :" + RS.getString("prof_firstname"));

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            if(autoCommit instanceof Boolean B) if(!B) JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un ou plusieurs objets sont mal moulés !"); }
    }


    protected static void transactTable() throws SQLException {

        String query = "UPDATE professor SET prof_firstname = 'Cyrille' " + "WHERE prof_lastname = 'MAMOU'";

        result[0] = state.executeQuery("SELECT * FROM professor" + " WHERE prof_lastname = 'MAMOU'");

        if(result[0] instanceof ResultSet RS) {

            RS.first();
            System.out.println("NOM : " + RS.getString("prof_lastname") + " - PRENOM : " + RS.getString("prof_firstname"));

            state.executeUpdate(query);

            RS = state.executeQuery("SELECT * FROM professor WHERE prof_lastname = 'MAMOU'");

            RS.first();
            System.out.println("NOM : " + RS.getString("prof_lastname") + " - PRENOM : " + RS.getString("prof_firstname"));

            /* permet de prendre en compte une requête spécifique si le boolean 'autoCommit' est faux */
            //if(autoCommit instanceof Boolean B) if(!B) ControlJDBC.JDBC_Connect.getInstance().commit();

        } else { System.out.println("Un objet est mal moulé !"); }
    }

    // ** Création d'un objet Statement ** //

    //Objet Statement sans argument(s)
    public static void setState() throws SQLException, ClassNotFoundException { state = JDBC_Connect.getInstance().createStatement(); }

    //Objet Statement avec 2 arguments
    @SuppressWarnings("SameParameterValue")
    public static void setState(int resultSetType, int resultSetConcurrency) throws SQLException, ClassNotFoundException { state = JDBC_Connect.getInstance().createStatement(resultSetType, resultSetConcurrency); }

    //Objet Statement avec 3 arguments
    @SuppressWarnings("SameParameterValue")
    public static void setState(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException, ClassNotFoundException { state = JDBC_Connect.getInstance().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability); }

    // ** Création d'un objet Statement ** //



    //Récupération d'un objet Statement
    public static Statement getState() { return state; }

    //Récupération d'un objet ResultSet ou PreparedStatement
    protected static Object getResult() { return result; }
}

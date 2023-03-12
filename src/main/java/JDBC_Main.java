import ControlJDBC.JDBC_Controler;
import JDBC_DAO.DAO_View;
import ViewJDBC.Window_JBDC;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Main extends JDBC_Controler {
    private static final ImageIcon iconplay = new ImageIcon(JDBC_Main.class.getClassLoader().getResource("run.png"));

    public static void main(String[] args) {

        //***//***// PARTIE GLOBALES SUR LES MÉTHODES DE 'JBDC' //***//***//

        //Essait de récupérer des informations au niveau d'une base de donnée
        try {

            // Connection à la base de donnée
            Connection();

            //***// Différents types de 'Statement' //***//

            // 1er Type ('createStatement' sans argument(s))
            /*setState();*/

            // 2ème Type ('createStatement' avec argument(s) ['int' (ResultSet)])
            setState(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //***// Différents types de 'Statement' //***//


            // *** === Utilisation du premier type de 'Statement' === *** //

            // Affiche les information d'un tableau SQL
            /*showTable("professor");*/


            // Affiche des infomations spécifique d'un tableau SQL spécifique (Voir Méthode)
            /*getTableInfo();*/


            // Remplace un caractère spécifique par une information de la requête du tableau SQL (Voir Méthode)
            /*getSpecialCharValueFromTable('?');*/


            // *** === Utilisation du deuxième type de 'Statement' === *** //

            // Affiche les différents types d'informations d'un tableau SQL à travers les différentes lignes (Voir Méthode)
            /*showContentTableWithLine();*/


            // Met à jour des infomations spécifique d'un tableau SQL
            /*updateTable();*/


            // Met à jour des infomations spécifique d'un tableau SQL avec les fameuses requêtes principoux de SQL
            /*updateValuesTable();*/


            // Gère les transactions des infomations spécifique d'un tableau SQL
            /*transactTable();*/

                                 /* ----------------------------------------------- */


            //***// Se déconnecte de la base de donnée et quitte le programme //***//

            if(getResult() instanceof ResultSet RS) RS.close();
            else if(getResult() instanceof PreparedStatement RS) RS.close();
            getState().close();

            //***// Se déconnecte de la Base de donnée et quitte le programme //***//


        //Retourne une éxception,
        //si le programme n'arrive pas à récupérer des informations au niveau d'une base de donnée
        } catch(ClassNotFoundException | SQLException e) { e.printStackTrace(); }


        //***//***// PARTIE GLOBALES SUR LES MÉTHODES DE 'JBDC' //***//***//


                        /* ------------------------------------------------------------------------ */
                        /* ------------------------------------------------------------------------ */


        //***//**// AUTRES UTILISATIONS //***//***//

        // Fenêtre avec des composants graphqiues permettant l'affichage d'un tableau d'une base de donnée SQL
        /*new Window_JBDC(iconplay);*/

        // Affiche des informations d'une base de donnée avec (DAO) en utilisant plusieurs CLASS et MÉTHODES **/
        /*new DAO_View();*/

        //***//***// AUTRES UTILISATIONS //***//***//
    }
}
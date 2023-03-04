package JDBC_DAO.Factory;

import ControlJDBC.JDBC_Connect;
import JDBC_DAO.DAOClass.*;
import JDBC_DAO.TableClass.Classe;
import JDBC_DAO.TableClass.Eleve;
import JDBC_DAO.TableClass.Matiere;
import JDBC_DAO.TableClass.Professeur;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory extends AbstractDAOFactory {

    protected static Connection conn;

    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return DAO
     */
    public DAO<Classe> getClasseDAO() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new ClasseDAO(conn);
    }

    /**
     * Retourne un objet Professeur interagissant avec la BDD
     * @return DAO
     */
    public DAO<Professeur> getProfesseurDAO() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new ProfesseurDAO(conn);
    }

    /**
     * Retourne un objet Eleve interagissant avec la BDD
     * @return DAO
     */
    public DAO<Eleve> getEleveDAO() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new EleveDAO(conn);
    }

    /**
     * Retourne un objet Matiere interagissant avec la BDD
     * @return DAO
     */
    public DAO<Matiere> getMatiereDAO() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new MatiereDAO(conn);
    }
}

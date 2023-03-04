package JDBC_DAO.Factory;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.TableClass.Classe;
import JDBC_DAO.TableClass.Eleve;
import JDBC_DAO.TableClass.Matiere;
import JDBC_DAO.TableClass.Professeur;

import java.sql.SQLException;

public abstract class AbstractDAOFactory  {

    public static final int DAO_FACTORY = 0;
    public static final int XML_DAO_FACTORY = 1;

    //Retourne un objet Classe interagissant avec la BDD
    public abstract DAO<Classe> getClasseDAO() throws SQLException, ClassNotFoundException;

    //Retourne un objet Professeur interagissant avec la BDD
    public abstract DAO<Professeur> getProfesseurDAO() throws SQLException, ClassNotFoundException;

    //Retourne un objet Eleve interagissant avec la BDD
    public abstract DAO<Eleve> getEleveDAO() throws SQLException, ClassNotFoundException;

    //Retourne un objet Matiere interagissant avec la BDD
    public abstract DAO<Matiere> getMatiereDAO() throws SQLException, ClassNotFoundException;

    //Méthode permettant de récupérer les Factory
    public static AbstractDAOFactory getFactory(int type) {

        switch(type) {

            case DAO_FACTORY: return new DAOFactory();
            case XML_DAO_FACTORY: return new XMLDAOFactory();
            default: return null;
        }
    }
}

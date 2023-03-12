package JDBC_DAO.Factory;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.TableClass.Class;
import JDBC_DAO.TableClass.Student;
import JDBC_DAO.TableClass.Subject;
import JDBC_DAO.TableClass.Professor;

import java.sql.SQLException;

public abstract class AbstractDAOFactory  {

    public static final int DAO_FACTORY = 0;
    public static final int XML_DAO_FACTORY = 1;

                        /* ----------------------------------------------- */

    //Retourne un objet Class interagissant avec la BDD
    public abstract DAO<Class> getClassDao() throws SQLException, ClassNotFoundException;

    //Retourne un objet Professor interagissant avec la BDD
    public abstract DAO<Professor> getProfesseurDAO() throws SQLException, ClassNotFoundException;

    //Retourne un objet Student interagissant avec la BDD
    public abstract DAO<Student> getStudentDao() throws SQLException, ClassNotFoundException;

    //Retourne un objet Subject interagissant avec la BDD
    public abstract DAO<Subject> getSubjectDao() throws SQLException, ClassNotFoundException;

    //Méthode permettant de récupérer les Factory
    public static AbstractDAOFactory getFactory(int type) {

        switch(type) {

            case DAO_FACTORY: return new DAOFactory();
            case XML_DAO_FACTORY: return new XMLDAOFactory();
            default: return null;
        }
    }
}

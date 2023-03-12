package JDBC_DAO.Factory;

import ControlJDBC.JDBC_Connect;
import JDBC_DAO.DAOClass.*;
import JDBC_DAO.TableClass.Class;
import JDBC_DAO.TableClass.Professor;
import JDBC_DAO.TableClass.Student;
import JDBC_DAO.TableClass.Subject;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory extends AbstractDAOFactory {

    protected static Connection conn;

                        /* ----------------------------------------- */

    /**
     * Retourne un objet Class interagissant avec la BDD
     * @return DAO
     */
    public DAO<Class> getClassDao() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new ClassDAO(conn);
    }

    /**
     * Retourne un objet Professor interagissant avec la BDD
     * @return DAO
     */
    public DAO<Professor> getProfesseurDAO() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new ProfessorDAO(conn);
    }

    /**
     * Retourne un objet Student interagissant avec la BDD
     * @return DAO
     */
    public DAO<Student> getStudentDao() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new StudentDAO(conn);
    }

    /**
     * Retourne un objet Subject interagissant avec la BDD
     * @return DAO
     */
    public DAO<Subject> getSubjectDao() throws SQLException, ClassNotFoundException {

        conn = JDBC_Connect.getInstance();
        return new SubjectDAO(conn);
    }
}

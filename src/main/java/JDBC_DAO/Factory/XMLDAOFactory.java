package JDBC_DAO.Factory;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.TableClass.Class;
import JDBC_DAO.TableClass.Professor;
import JDBC_DAO.TableClass.Student;
import JDBC_DAO.TableClass.Subject;

public class XMLDAOFactory extends AbstractDAOFactory {

    public DAO<Class> getClassDao() { return null; }

    public DAO<Student> getStudentDao() { return null; }

    public DAO<Subject> getSubjectDao() { return null; }

    public DAO<Professor> getProfesseurDAO() { return null; }
}

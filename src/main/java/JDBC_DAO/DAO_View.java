package JDBC_DAO;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.Factory.AbstractDAOFactory;
import JDBC_DAO.TableClass.Class;
import JDBC_DAO.TableClass.Professor;
import JDBC_DAO.TableClass.Student;
import JDBC_DAO.TableClass.Subject;

import java.sql.SQLException;

public class DAO_View {

    public DAO_View() {

        try {

            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);

            //Testons des élèves
            DAO<Student> studentDao = adf.getStudentDao();

            for(int i = 1; i < 5; i++) {

                Student student = studentDao.find(i);
                System.out.println("Elève N°" + student.getId() + "  - " + student.getLastName() + " " + student.getFirstName());
            }

            System.out.println("\n********************************\n");

            //Voyons voir les professeurs
            DAO<Professor> profDao = adf.getProfesseurDAO();

            for(int i = 4; i < 8; i++) {

                Professor prof = profDao.find(i);

                System.out.println(prof.getLastName() + " " + prof.getFirstName() + " enseigne : ");

                for(Subject sub : prof.getListMaterial()) System.out.println("\t * " + sub.getName());
            }

            System.out.println("\n********************************\n");

            //Et là, c'est la aClass
            DAO<Class> classDao = adf.getClassDao();

            Class aClass = classDao.find(11);

            System.out.println("Class de " + aClass.getName());
            System.out.println("\nListe des élèves :");

            for(Student student : aClass.getListStudent()) System.out.println("  - " + student.getLastName() + " " + student.getFirstName());

            System.out.println("\nListe des professeurs :");

            for(Professor prof : aClass.getListProfessor()) {

                System.out.println("  - " + prof.getLastName() + " " + prof.getFirstName());

                //Tant qu'à faire, on prend aussi les matières
                for(Subject sub : prof.getListMaterial()) System.out.println("\t\t\t * " + sub.getName());

            }

            System.out.println("\n********************************\n");

            //Un petit essai sur les matières
            DAO<Subject> subjectDao = adf.getSubjectDao();

            Subject sub = subjectDao.find(2);

            System.out.println("\tMATIERE " + sub.getId() + " : " + sub.getName());

        } catch(SQLException | ClassNotFoundException e) { e.printStackTrace(); }
    }
}

package JDBC_DAO;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.Factory.AbstractDAOFactory;
import JDBC_DAO.TableClass.Classe;
import JDBC_DAO.TableClass.Eleve;
import JDBC_DAO.TableClass.Matiere;
import JDBC_DAO.TableClass.Professeur;

import java.sql.SQLException;

public class DAO_View {

    public DAO_View() {

        try {

            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);

            //Testons des élèves
            DAO<Eleve> eleveDao = adf.getEleveDAO();

            for(int i = 1; i < 5; i++) {

                Eleve eleve = eleveDao.find(i);
                System.out.println("Elève N°" + eleve.getId() + "  - " + eleve.getName() + " " + eleve.getFirstname());
            }

            System.out.println("\n********************************\n");

            //Voyons voir les professeurs
            DAO<Professeur> profDao = adf.getProfesseurDAO();

            for(int i = 4; i < 8; i++) {

                Professeur prof = profDao.find(i);

                System.out.println(prof.getName() + " " + prof.getFirstname() + " enseigne : ");

                for(Matiere mat : prof.getListMaterial()) System.out.println("\t * " + mat.getName());
            }

            System.out.println("\n********************************\n");

            //Et là, c'est la classe
            DAO<Classe> classeDao = adf.getClasseDAO();

            Classe classe = classeDao.find(11);

            System.out.println("Classe de " + classe.getName());
            System.out.println("\nListe des élèves :");

            for(Eleve eleve : classe.getListStudent()) System.out.println("  - " + eleve.getName() + " " + eleve.getFirstname());

            System.out.println("\nListe des professeurs :");

            for(Professeur prof : classe.getListProfessor()) {

                System.out.println("  - " + prof.getName() + " " + prof.getFirstname());

                //Tant qu'à faire, on prend aussi les matières
                for(Matiere mat : prof.getListMaterial()) System.out.println("\t\t\t * " + mat.getName());

            }

            System.out.println("\n********************************\n");

            //Un petit essai sur les matières
            DAO<Matiere> matiereDao = adf.getMatiereDAO();

            Matiere mat = matiereDao.find(2);

            System.out.println("\tMATIERE " + mat.getId() + " : " + mat.getName());

        } catch(SQLException | ClassNotFoundException e) { e.printStackTrace(); }
    }
}

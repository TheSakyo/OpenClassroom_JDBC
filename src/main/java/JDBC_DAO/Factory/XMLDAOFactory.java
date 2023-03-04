package JDBC_DAO.Factory;

import JDBC_DAO.DAOClass.DAO;
import JDBC_DAO.TableClass.Classe;
import JDBC_DAO.TableClass.Eleve;
import JDBC_DAO.TableClass.Matiere;
import JDBC_DAO.TableClass.Professeur;

public class XMLDAOFactory extends AbstractDAOFactory {

    public DAO<Classe> getClasseDAO() { return null; }

    public DAO<Eleve> getEleveDAO() { return null; }

    public DAO<Matiere> getMatiereDAO() { return null; }

    public DAO<Professeur> getProfesseurDAO() { return null; }
}

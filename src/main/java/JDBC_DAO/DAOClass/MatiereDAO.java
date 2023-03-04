package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Matiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatiereDAO extends DAO<Matiere> {

    public MatiereDAO(Connection conn) { super(conn); }

    public boolean create(Matiere obj) { return false; }

    public boolean delete(Matiere obj) { return false; }

    public boolean update(Matiere obj) { return false; }

    public Matiere find(int id) {

        Matiere matiere = new Matiere();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM matiere WHERE mat_id = " + id);
            if(result.first()) matiere = new Matiere(id, result.getString("mat_nom"));

        } catch(SQLException e) { e.printStackTrace(); }

        return matiere;
    }
}
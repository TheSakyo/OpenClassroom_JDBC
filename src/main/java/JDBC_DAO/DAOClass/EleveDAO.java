package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Eleve;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EleveDAO extends DAO<Eleve> {

    public EleveDAO(Connection conn) { super(conn); }

    public boolean create(Eleve obj) { return false; }

    public boolean delete(Eleve obj) { return false; }

    public boolean update(Eleve obj) { return false; }

    public Eleve find(int id) {

        Eleve eleve = new Eleve();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM eleve WHERE elv_id = " + id);
            if(result.first()) eleve = new Eleve(id, result.getString("elv_nom"), result.getString("elv_prenom"));

        } catch(SQLException e) { e.printStackTrace(); }

        return eleve;
    }
}

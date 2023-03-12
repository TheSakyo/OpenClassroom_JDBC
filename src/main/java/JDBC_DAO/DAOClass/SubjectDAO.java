package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO extends DAO<Subject> {

    public SubjectDAO(Connection conn) { super(conn); }

    public boolean create(Subject obj) { return false; }

    public boolean delete(Subject obj) { return false; }

    public boolean update(Subject obj) { return false; }

    public Subject find(int id) {

        Subject subject = new Subject();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM subject WHERE sub_id = " + id);
            if(result.first()) subject = new Subject(id, result.getString("sub_name"));

        } catch(SQLException e) { e.printStackTrace(); }

        return subject;
    }
}
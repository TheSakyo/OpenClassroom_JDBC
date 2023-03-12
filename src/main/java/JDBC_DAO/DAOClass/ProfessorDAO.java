package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Professor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO extends DAO<Professor> {

    public ProfessorDAO(Connection conn) { super(conn); }

    public boolean create(Professor obj) { return false; }

    public boolean delete(Professor obj) { return false; }

    public boolean update(Professor obj) { return false; }

    public Professor find(int id) {

        Professor professor = new Professor();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                                .executeQuery("SELECT * FROM professor " + "LEFT JOIN j_sub_prof ON jmp_prof_k = prof_id " +
                                "AND prof_id = "+ id + " INNER JOIN subject ON jmp_sub_k = sub_id");
            if(result.first()) {

                professor = new Professor(id, result.getString("prof_lastname"), result.getString("prof_firstname"));

                result.beforeFirst(); SubjectDAO matDao = new SubjectDAO(this.connect);

                while(result.next()) professor.addMaterial(matDao.find(result.getInt("sub_id")));
            }

        } catch(SQLException e) { e.printStackTrace(); }

        return professor;
    }
}

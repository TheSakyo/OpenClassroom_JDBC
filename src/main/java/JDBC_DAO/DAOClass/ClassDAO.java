package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassDAO extends DAO<Class> {

    public ClassDAO(Connection conn) { super(conn); }

    public boolean create(Class obj) { return false; }

    public boolean delete(Class obj) { return false; }

    public boolean update(Class obj) { return false; }

    public Class find(int id) {

        Class aClass = new Class();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM class WHERE cls_id = " + id);

            if(result.first()) {

                aClass = new Class(id, result.getString("cls_name"));

                result = this.connect.createStatement().executeQuery("SELECT prof_id, prof_lastname, prof_firstname from professor " +
                          "INNER JOIN j_sub_prof ON prof_id = jmp_prof_k " + "INNER JOIN j_cls_jmp ON jmp_id = jcm_jmp_k AND jcm_cls_k = " + id);

                ProfessorDAO profDao = new ProfessorDAO(this.connect);

                while(result.next()) aClass.addProfessor(profDao.find(result.getInt("prof_id")));

                StudentDAO studentDao = new StudentDAO(this.connect);

                result = this.connect.createStatement().executeQuery("SELECT stud_id, stud_lastname, stud_firstname FROM student INNER JOIN class ON stud_cls_k = cls_id AND cls_id = " + id);

                while(result.next()) aClass.addStudent(studentDao.find(result.getInt("stud_id")));
            }

        } catch(SQLException e) { e.printStackTrace(); }

        return aClass;
    }
}

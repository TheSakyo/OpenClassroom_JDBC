package JDBC_DAO.DAOClass;

import JDBC_DAO.TableClass.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO extends DAO<Student> {

    public StudentDAO(Connection conn) { super(conn); }

    public boolean create(Student obj) { return false; }

    public boolean delete(Student obj) { return false; }

    public boolean update(Student obj) { return false; }

    public Student find(int id) {

        Student student = new Student();

        try {

            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM student WHERE stud_id = " + id);
            if(result.first()) student = new Student(id, result.getString("stud_lastname"), result.getString("stud_firstname"));

        } catch(SQLException e) { e.printStackTrace(); }

        return student;
    }
}

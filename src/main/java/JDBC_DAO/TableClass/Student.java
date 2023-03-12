package JDBC_DAO.TableClass;

public class Student {

    //ID
    private int id = 0;

    //Nom de l'élève
    private String lastname = "";

    //Préname de l'élève
    private String firstname = "";


    public Student(){}
    public Student(int id, String lastname, String firstname) {

        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLastName() { return lastname; }

    public void setLastName(String name) { this.lastname = name; }

    public String getFirstName() { return firstname; }

    public void setFirstName(String firstname) { this.firstname = firstname; }
}
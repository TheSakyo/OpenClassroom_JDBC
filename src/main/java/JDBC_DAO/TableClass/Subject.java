package JDBC_DAO.TableClass;

public class Subject {

    //ID
    private int id = 0;

    //Nom du professeur
    private String name = "";

    public Subject(){}
    public Subject(int id, String name) { this.id = id; this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
